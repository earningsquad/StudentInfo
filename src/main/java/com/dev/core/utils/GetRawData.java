package com.dev.core.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GetRawData {
    public static String getPostRawData(HttpServletRequest request)  {
        //HttpServletRequest request //= ServletActionContext.getRequest();
        try {

            int contentLength = request.getContentLength();
            if(contentLength<0){
                return null;
            }
            byte buffer[] = new byte[contentLength];
            for (int i = 0; i < contentLength;) {
                int len = request.getInputStream().read(buffer, i, contentLength - i);
                if (len == -1) {
                    break;
                }
                i += len;
            }
            return new String(buffer, "utf-8");
        }catch (IOException e){
            e.printStackTrace();
            return "异常";
        }
    }
}
