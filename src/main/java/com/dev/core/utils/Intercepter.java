package com.dev.core.utils;


import com.dev.core.anno.GetUser;
import com.dev.core.anno.LoginRequired;
import com.dev.core.anno.RawPostData;
import com.dev.core.anno.RoleRequired;
import com.dev.core.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Intercepter implements Interceptor {

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //System.out.println("拦截器");

        String methodName=actionInvocation.getProxy().getMethod();

        Method currentMethod=null;
        User user= (User) ServletActionContext
                .getRequest().getSession().getAttribute("user");
      try {
          currentMethod=actionInvocation.getAction()
                  .getClass().getMethod(methodName, null);
      }catch (NoSuchMethodException e){
/*
          currentMethod=actionInvocation.getProxy().getAction()
                  .getClass().getMethod(methodName,User.class);
          currentMethod=actionInvocation.getProxy().getAction()
                  .getClass().getMethod(methodName,String.class);
          =actionInvocation.getProxy().getAction()*/
         Method[] methods=actionInvocation.getProxy().getAction().getClass().getDeclaredMethods();
                 for (Method method:methods){
                  if (method.getName().equals(methodName))
                     currentMethod=method;
                 }
      }

      //  Annotation[][] annotations=currentMethod.getParameterAnnotations();




        if (currentMethod.isAnnotationPresent(LoginRequired.class)){



            if (user!=null){
                if (currentMethod.isAnnotationPresent(RoleRequired.class)){
                  RoleRequired roleRequired=currentMethod.getAnnotation(RoleRequired.class);
                  String role=roleRequired.value();
                    if (!user.getRole().equals(role)){
                        return "noPermission";
                    }
                }



              //  return actionInvocation.invoke();
            }else {
                return "noLogin";
            }
        }

        Parameter[] parameters=currentMethod.getParameters();
        Object[] params=new Object[2];

        if (parameters.length<3){
            int i;
            for ( i=0;i<parameters.length;i++){
                Parameter parameter=parameters[i];
                if (parameter.isAnnotationPresent(GetUser.class))
                {
                    params[i]=user;
                }
                else if (parameter.isAnnotationPresent(RawPostData.class))
                {
                    params[i]=GetRawData.getPostRawData(ServletActionContext
                            .getRequest());
                }
            }
            if (i==1)
             return (String) currentMethod.invoke(actionInvocation.getProxy().getAction(),params[0]);
            if (i==2)
            return (String) currentMethod.invoke(actionInvocation.getProxy().getAction(),params[0],params[1]);
        }

        return actionInvocation.invoke();
    }
}
