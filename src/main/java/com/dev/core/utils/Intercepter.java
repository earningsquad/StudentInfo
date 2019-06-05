package com.dev.core.utils;


import com.dev.core.anno.LoginRequired;
import com.dev.core.anno.RoleRequired;
import com.dev.core.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;

import java.lang.reflect.Method;

public class Intercepter implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        String methodName=actionInvocation.getProxy().getMethod();
        Method currentMethod=actionInvocation.getAction()
                .getClass().getMethod(methodName, null);


        if (currentMethod.isAnnotationPresent(LoginRequired.class)){
            User user= (User) ServletActionContext
                    .getRequest().getSession().getAttribute("user");
            if (user!=null){
                if (currentMethod.isAnnotationPresent(RoleRequired.class)){
                  RoleRequired roleRequired=currentMethod.getAnnotation(RoleRequired.class);
                  String role=roleRequired.value();
                    if (!user.getRole().equals(role)){
                        return "noPermission";
                    }
                }

                return actionInvocation.invoke();
            }else {
                return "noLogin";
            }
        }

        return null;
    }
}
