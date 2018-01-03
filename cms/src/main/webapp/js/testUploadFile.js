$(document).ready(function () {
    $("#uploadFormId").on("click","#uploadFile",uploadFile)
})
function uploadFile() {
    var params=getParams();
    console.log(params);
    var url="uploadFile.do";
    $("#uploadFormId").ajaxSubmit({
        type:"post",
        url:url,
        data:params,
        success:function(result){
            if(result.state==1){
                alert("文件上传成功");
            }else{
                alert(result.message);
            }
        }
    });
}
function getParams() {
    var param={
        "clientId":1
    }
    return param;
}
