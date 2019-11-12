package com.xs.dc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年9月24日 上午11:25:30 
* 类说明 
*/
public class LoginFilter implements Filter{
	public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        filterChain.doFilter(request, response);


        /*String path = request.getServletPath();
        if(path.contains("/user/sendCode")
                || path.contains("/user/register")|| path.contains("/user/login")
                || path.contains("/user/forget") || path.contains("/user/isVerify")){
            filterChain.doFilter(request,response);
            return;
        }else{
            String token = request.getParameter("token");

            if(StringUtils.isNotBlank(token)){
                Map<String,Object> user = userService.getUserByToken(token);
                if(user != null){
                    filterChain.doFilter(request,response);
                    return;
                }
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result","你还未登录");
            jsonObject.put("code","500");
            PrintWriter writer = response.getWriter();
            writer.append(jsonObject.toJSONString());
            writer.flush();
            writer.close();
        }*/

    }

    public void destroy() {

    }
}
