/**
 * Created by Administrator on 2017/11/3.
 */
var pathName=getRootPath();
$(document).ready(function () {
    console.log($("#myModal").data("target")+$("#myModal").data("id"));
    getUserInfo();
});
function getUserInfo(){
    var url=getRootPath()+"/findUserInfo.do";
    var id=$("#myModal").data("id");
    var params={
        id:id
    };
    $.get(url,params,function(result){
        $(".name").html(result.data.account);
        $(".phone").html(result.data.phone);
        $(".authStatus").html(result.data.authStatuName);
        $(".userImg").attr('src',result.data.img);
    });
}