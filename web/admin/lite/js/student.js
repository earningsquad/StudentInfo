$(function() {
    allStu()
})

function deleteStu(id){
    $.ajax({
        url : "http://localhost:8080/deleteStu",
        type : "POST",
        data : {'stuId':id},
        success : function(result) {
                allStu();
        },
        error:function(msg){
            $(".notice").html('Error:'+msg);
        }
    })


}
function allStu(){
    $("#showDetail").children().remove()
    $.get("/findStu", function(data,status){
        $.each(data.students,function (key,val) {
            var flag = $.isEmptyObject(val.warn)
            var  warn = "<td><h6 class=\"text-muted  text-success\"><i class=\"fa fa-circle font-10 m-r-10\"></i>学业预警</h6></td>"
            if (flag) {
                warn=" <td><h6 class=\"text-muted  text-warn\"><i class=\"fa fa-circle font-10 m-r-10\"></i>无警告</h6></td>"
            }
            $("#showDetail").append("<tr>\n" +
                "          <td>"+val.id +"</td>\n" +
                "          <td>"+val.classNumber +"</td>\n" +
                "          <td>"+val.name +"</td>\n" +
                warn  +
                "          <td><button class=\"btn  waves-effect btn-default\" onclick=\"Edit("+val.id+","+val.user.id+")\">编辑记录</button>" +
                "&nbsp&nbsp<button class=\"btn  waves-effect btn-default\" onclick=\"deleteStu("+val.id+")\">删除记录</button></td>\n" +
                "          </tr>"
            )
        })
    });
}
function Edit(id,uid) {
  $("#flag").val(id)
    $("#uid").val(uid)
    $.ajax({
        url : "http://localhost:8080/findTheStu",
        type : "POST",
        data : {'stuId':id},
        success : function(result) {
            $("#userName").val(result.user.userName)
            $("#name").val(result.name)
            $("#password").val(result.user.password)
            $("#warn").val(result.warn)

        },
        error:function(msg){
            $(".notice").html('Error:'+msg);
        }
    })
    start()
}