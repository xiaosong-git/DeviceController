package com.xs.dc.filter;
/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月28日 下午6:26:25 
* 类说明 
*/
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class FormContentFilter extends OncePerRequestFilter {

	// 该过滤器仅针对如下三种HTTP method生效
	private static final List<String> HTTP_METHODS = Arrays.asList("PUT", "PATCH", "DELETE");

	private FormHttpMessageConverter formConverter = new AllEncompassingFormHttpMessageConverter();


	/**
	 * Set the converter to use for parsing form content.
	 * 设置分析表单内容的转换器,缺省使用的是一个 AllEncompassingFormHttpMessageConverter
	 * >By default this is an instance of AllEncompassingFormHttpMessageConverter.
	 */
	public void setFormConverter(FormHttpMessageConverter converter) {
		Assert.notNull(converter, "FormHttpMessageConverter is required");
		this.formConverter = converter;
	}

	/**
	 * The default character set to use for reading form data.
	 * 设置表单内容分析时使用的字符集
	 * 该方法是 getFormConverter.setCharset(charset) 的快捷方法
	 */
	public void setCharset(Charset charset) {
		this.formConverter.setCharset(charset);
	}


	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 分析表单内容获取参数 params 
		MultiValueMap<String, String> params = parseIfNecessary(request);
		if (!CollectionUtils.isEmpty(params)) {
			// 如果 params 不为空，则封装请求为一个FormContentRequestWrapper然后继续过滤器链的调用
			filterChain.doFilter(new FormContentRequestWrapper(request, params), response);
		}
		else {
			// 如果 params 为空，使用原来的请求继续过滤器链的调用
			filterChain.doFilter(request, response);
		}
	}

	// 分析表单内容获取参数
	@Nullable
	private MultiValueMap<String, String> parseIfNecessary(HttpServletRequest request) throws IOException {
		// 必须是 : PUT, DELETE , PATCH
		// 并且必须是 : application/x-www-form-urlencoded
		if (!shouldParse(request)) {
			return null;
		}

		// 获取请求主体流，分析其中的参数为MultiValueMap<String, String>并返回
		HttpInputMessage inputMessage = new ServletServerHttpRequest(request) {
			@Override
			public InputStream getBody() throws IOException {
				return request.getInputStream();
			}
		};
		return this.formConverter.read(null, inputMessage);
	}

	private boolean shouldParse(HttpServletRequest request) {
		// 仅仅支持 PUT, DELETE , PATCH
		if (!HTTP_METHODS.contains(request.getMethod())) {
			return false;
		}
		// 仅仅支持 application/x-www-form-urlencoded
		try {
			MediaType mediaType = MediaType.parseMediaType(request.getContentType());
			return MediaType.APPLICATION_FORM_URLENCODED.includes(mediaType);
		}
		catch (IllegalArgumentException ex) {
			return false;
		}
	}


	// 封装请求和指定的参数，让指定的参数呈现为被封装请求的参数
	private static class FormContentRequestWrapper extends HttpServletRequestWrapper {

		private MultiValueMap<String, String> formParams;

		public FormContentRequestWrapper(HttpServletRequest request, MultiValueMap<String, String> params) {
			super(request);
			this.formParams = params;
		}

		@Override
		@Nullable
		public String getParameter(String name) {
			// 注意，这里还是优先使用request的queryString参数，只是queryString不存在时才从formParams中获取
			String queryStringValue = super.getParameter(name);
			String formValue = this.formParams.getFirst(name);
			return (queryStringValue != null ? queryStringValue : formValue);
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			Map<String, String[]> result = new LinkedHashMap<>();
			Enumeration<String> names = getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				result.put(name, getParameterValues(name));
			}
			return result;
		}

		@Override
		public Enumeration<String> getParameterNames() {
			Set<String> names = new LinkedHashSet<>();
			names.addAll(Collections.list(super.getParameterNames()));
			names.addAll(this.formParams.keySet());
			return Collections.enumeration(names);
		}

		// 获取指定属性的所有值，会合并queryString中的值和外部指定的formParams中的值到一起然后返回
		@Override
		@Nullable
		public String[] getParameterValues(String name) {
			String[] parameterValues = super.getParameterValues(name);
			List<String> formParam = this.formParams.get(name);
			if (formParam == null) {
				return parameterValues;
			}
			if (parameterValues == null || getQueryString() == null) {
				return StringUtils.toStringArray(formParam);
			}
			else {
				List<String> result = new ArrayList<>(parameterValues.length + formParam.size());
				result.addAll(Arrays.asList(parameterValues));
				result.addAll(formParam);
				return StringUtils.toStringArray(result);
			}
		}
	}

}
