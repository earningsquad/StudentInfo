function select_file(){
    $("#file").trigger("click");
}
function fileUpload(){

    var formData = new FormData();

    formData.append('file', $('#file')[0].files[0]);
    $.ajax({
        url : '/filesupload',
        type : 'POST',
        data : formData,
        contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置
        processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post)
        dataType: 'json',//这里是返回类型，一般是json,text等
        clearForm: true,//提交后是否清空表单数据
        success: function(data) {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
            alert('上传成功');
        },
        error: function(data, status, e) {  //提交失败自动执行的处理函数。
            console.error(e);
        }
    });
}