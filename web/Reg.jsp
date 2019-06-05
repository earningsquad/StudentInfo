<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>

<head>
    <title>学生管理</title>
    <jsp:include page="inc.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css"></link>
    <style>
        body {
            text-align: center
        }
    </style>
</head>
<body class="loginbody">
<div id="top">
    <h1 style="margin-top:25px;color:#FFFFFF">用户注册</h1>
</div>
<div style="height: 100%;overflow: auto;">

    <form class="layui-form" action="" style="margin:auto;background: white; width: 80%;">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input">
            </div>
        </div>



        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password1" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">验证密码</label>
            <div class="layui-input-inline">
                <input type="text" name="password2" lay-verify="required" placeholder="再次输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>





        <div class="layui-form-item" pane="">
            <label class="layui-form-label">爱好</label>
            <div class="layui-input-block">
                <input type="checkbox" name="like1[singing]" lay-skin="primary" title="唱" checked="">
                <input type="checkbox" name="like1[dancing]" lay-skin="primary" title="跳">
                <input type="checkbox" name="like1[rap]" lay-skin="primary" title="RAP">
                <input type="checkbox" name="like1[basketball]" lay-skin="primary" title="篮球">

            </div>
        </div>



        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男" checked=""><div class="layui-unselect layui-form-radio layui-form-radioed"><i class="layui-anim layui-icon"></i><div>男</div></div>
                <input type="radio" name="sex" value="女" title="女"><div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i><div>女</div></div>
                <input type="radio" name="sex" value="禁" title="禁用" disabled=""><div class="layui-unselect layui-form-radio layui-radio-disbaled layui-disabled"><i class="layui-anim layui-icon"></i><div>禁用</div></div>
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

</div>
</body>
<script type="text/javascript">

    layui.use(['form', 'layedit', 'laydate','jquery'], function(){
        var form = layui.form;
        var $ = layui.jquery;


//监听提交
        form.on('submit(btnSubmit)', function(data){
            var operCd = $('#Login_operCd').val();//用户名
            var operPwd = $('#Login_operPwd').val();//密码


            if (operCd == '') {
                pubUtil.msg("请输入用户名!", layer, 2, function() {
                }, 3 * 1000);
                return false;
            }

            if (operPwd == '') {
                pubUtil.msg("请输入密码!", layer, 2, function() {
                }, 3 * 1000);
                return false;
            }

            var index = layer.load(1);//开启进度条
            $.ajax({
                url : '<%=request.getContextPath()%>/operInf/execute.do',
                data :{
                    operCd:$("#Login_operCd").val(),
                    operPwd:$("#Login_operPwd").val(),
                    code:$("#Login_code").val()
                },
                type : 'POST',//默认以get提交，以get提交如果是中文后台会出现乱码
                dataType : 'json',
                success : function(obj) {
                    layer.close(index);//关闭
                    if (obj && obj.success) {
                        layer.close(index);//关闭
                        window.location.href = "<%=request.getContextPath()%>/"+"main.jsp";
                    } else {
                        layer.close(index);//关闭
                        changeValidateCode();//修改验证码
                        $('#Login_operPwd').val('');//密码
                        $('#Login_code').val('');//验证码
                        pubUtil.msg(obj.msg, layer, 2, function() {
                        }, 3 * 1000);
                    }
                }
            });
            return false;
        });


    });


</script>
</html>
