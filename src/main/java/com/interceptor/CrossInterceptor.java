package com.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 跨域处理拦截器
 */
@Component
public class CrossInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		String method = request.getMethod().toLowerCase();
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));//支持跨域请求
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type");
		response.setHeader("Access-Control-Allow-Credentials", "true");//是否支持cookie跨域
		response.setHeader("Access-Control-Expose-Headers", "TK");
		if (method.equals("options")){
			return false;
		}

		HttpSession session=request.getSession();
		response.setHeader("TK",session.getId());

		System.out.println(request.getRequestURL());
		return true;
	}
}
