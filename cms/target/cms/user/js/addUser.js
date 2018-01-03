/**
 * Created by Administrator on 2017/11/9.
 */
$(document).ready(function(){
    getRoles();
})
var pathName=getRootPath();
function getRoles(){
    $("#cmsUserName").val("");
    $("#cmsUserPassword").val("");
    $(".chooseRole").html("");
    var url=pathName+"/findAllRole.do";
    var params;
    $.get(url,params,function (result) {
        var list=result.data;
        if(result.state==1){
            for(var  i in list){
                $(".chooseRole").append("<option value='"+list[i].roleId+"'>"+list[i].roleDescription+"</option>");
            }
        }else {
            alert(result.message);
        }
    })
}