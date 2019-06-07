package com.dev.core.action;

import com.dev.core.model.User;
import com.dev.core.utils.JsonResult;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class BasicAction extends ActionSupport {

    @Getter
    protected JsonResult result;
   // protected Logger logger = Logger.getLogger(BasicAction.class);

    /**
     * 获取当前系统用户
     *
     * @return
     */
    protected User getUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return null;
        }

        return user;
    }

    public void setUser(User user) {
        HttpServletRequest request= ServletActionContext
                .getRequest();
        request.getSession().setAttribute("user",user);
    }


    /**
     * 系统日志
     *
     * @param j
     * @param obj
     * @param request
     * @param session
     */
   /* public void writeSysLog(JsonResult result, Object obj, HttpServletRequest request, HttpSession session) {
        try {
            JsonResult json = result;
            if (json == null) {
                json = new JsonResult();
                json.setMessage("");
            }
            // 系统登录用户
            User user=getUser(request);
          *//*      // 系统访问地址
            String requestPath = PbUtils.getRequestPath(request);// 用户访问的资源地址
            // 登入人IP
            String operIp = GetIpUtil.getIpAddr(request);//request.getRemoteAddr();

            SysLog sysLog = new SysLog();
            sysLog.setOperIp(operIp);// 登录人IP
            sysLog.setUuid(UUID.randomUUID().toString());// uuid
            sysLog.setOperDt(PbUtils.getCurrentTime());// 操作日期

            // 用户编号
            if (!PbUtils.isEmpty((sessionInfo.getOperInf().getOperCd()))) {
                sysLog.setOperUsrId(sessionInfo.getOperInf().getOperCd());
            }

            // 根据URL获取操作对象
            SysFunctionInf functionInf = sysFunctionInfService.getFunction(requestPath);
            if (functionInf != null) {
                // 操作方法
                if (!PbUtils.isEmpty(functionInf.getFunctionNm())) {
                    sysLog.setOperMethod(functionInf.getFunctionNm());
                }

                // 所属模块
                if (!PbUtils.isEmpty(functionInf.getMenuNm())) {
                    sysLog.setOperModule(functionInf.getMenuNm());
                }
            }

            // 操作结果
            if (!PbUtils.isEmpty(json.getMsg())) {
                sysLog.setResultMsg(json.getMsg());
            }

            // 操作内容
            sysLog.setCompareInf(PbUtils.getObjReflect(obj));

            // 旧对象
            if (j.getOldObj() != null) {
                String compareInf = sysLog.getCompareInf() + "-----原先数据如下：-->";
                compareInf += PbUtils.getObjReflect(j.getOldObj());
                sysLog.setCompareInf(compareInf);
            }
            // 获取所有主键
            String[] pks = sysLogService.getAllTablePK();

            // 操作关键字
            sysLog.setOperDesc(PbUtils.getObjReflectKeword(obj, pks));

            // 新增日志
            sysLogService.add(sysLog);
*//*
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getStackTrace());
        }
    }
*/
    /**
     * 系统日志
     *
     * @param j
     *            json对象
     * @param obj
     *            javabean对象
     * @param method
     *            方法名称
     * @param module
     *            所属模块
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
 /*   public void writeSysLog(Json j, Object obj, String method, String module, HttpServletRequest request, HttpSession session) {
        try {
            Json json = j;
            if (json == null) {
                json = new Json();
                json.setMsg("");
            }

            // 系统登录用户
            SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Contans.SESSION_BEAN);
            // 登入人IP
            String operIp = getIpAddr(request);

            SysLog sysLog = new SysLog();
            sysLog.setOperIp(operIp);// 登录人IP
            sysLog.setUuid(UUID.randomUUID().toString());// uuid
            sysLog.setOperDt(PbUtils.getCurrentTime());// 操作日期

            // 用户编号
            if (!PbUtils.isEmpty((sessionInfo.getOperInf().getOperCd()))) {
                sysLog.setOperUsrId(sessionInfo.getOperInf().getOperCd());
            }

            // 操作方法
            if (!PbUtils.isEmpty(method)) {
                sysLog.setOperMethod(method);
            }

            // 所属模块
            if (!PbUtils.isEmpty(module)) {
                sysLog.setOperModule(module);
            }

            // 操作结果
            if (!PbUtils.isEmpty(json.getMsg())) {
                sysLog.setResultMsg(json.getMsg());
            }

            // 操作内容
            sysLog.setCompareInf(PbUtils.getObjReflect(obj));

            // 旧对象
            if (j.getOldObj() != null) {
                String compareInf = sysLog.getCompareInf() + "-----原先数据如下：-->";
                compareInf += PbUtils.getObjReflect(j.getOldObj());
                sysLog.setCompareInf(compareInf);
            }

            // 获取所有主键
            String[] pks = sysLogService.getAllTablePK();

            // 操作关键字
            sysLog.setOperDesc(PbUtils.getObjReflectKeword(obj, pks));

            // 新增日志
            sysLogService.add(sysLog);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getStackTrace());
        }

    }
*/

    //解析请求的Json数据
    protected String getRequestPostData() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();

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
    }
}
