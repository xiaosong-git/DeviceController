package com.xs.dc.inteceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年9月24日 下午3:03:08 
* 类说明 
*/
@Component
public class AddInterceptor implements WebMvcConfigurer{
	public void addInterceptors(InterceptorRegistry registry){
		// 注册拦截器
        LoginCheckInteceptor loginInterceptor = new LoginCheckInteceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/**");
        // 排除路径
        //loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/login");
        loginRegistry.excludePathPatterns("/loginout");
        // 排除资源请求
        loginRegistry.excludePathPatterns("/css/login/*.css");
        loginRegistry.excludePathPatterns("/js/login/**/*.js");
        loginRegistry.excludePathPatterns("/image/login/*.png");
    }
}
