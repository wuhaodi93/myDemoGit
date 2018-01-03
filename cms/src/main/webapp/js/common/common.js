/**
 * Created by Administrator on 2017/10/28.
 */
function getRootPath() {
    // 获取当前网址，如：http://localhost/WebCourse/jsp/login/login.jsp
    var curWwwPath = window.document.location.href;
    // 获取主机地址之后的目录，如： WebCourse/jsp/login/login.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    // 获取主机地址，如： http://localhost
    var localhostPath = curWwwPath.substring(0, pos);
    // 获取带"/"的项目名，如：/WebCourse
    var projectName = pathName.substring(0,
        pathName.substr(1).indexOf('/') + 1);
    return projectName;
}
$(document).ready(function(){
    //点击图片，图片放大
    var pathName=getRootPath();
    $(".showImg").load(pathName+"/app/common/imgModal.html");
    $(".tablelist").on('click',".tableImage",showTableImageInModal);
})

function showTableImageInModal(){
    var  src=$(this).attr("src");
    $("#imageModal").modal("show");
    $(".showTableImage").attr("src",src);
}
function doConfirm(){
    if(confirm("确定要执行操作码?")){
        return true;
    }else{
        return false;
    }
}