package com.dev.core.utils;

import com.dev.core.anno.GetUser;
import com.dev.core.model.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class MemberDetailHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /** 登录用户信息session中的key */
    private String              sessionKey         = "user";

    /** 登录用户信息的class类型 */
    private Class<?>            sessionMemberClass = User.class;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 过滤出符合条件的参数，这里指的是加了MemberDetail注解的参数
        if (parameter.hasParameterAnnotation(GetUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        // 获取session中存放的用户信息
        Object sessionMember = WebUtils.getSessionAttribute(request, sessionKey);

        if (null == sessionMember){
            return null;
        }

        // session中对象与加了MemberDetail注解的参数的类型相同是赋值给这个参数
        Class<?> klass = parameter.getParameterType();
        if (klass.isAssignableFrom(sessionMemberClass)){
            return sessionMember;
        }

        return null;
    }

}
