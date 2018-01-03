/**
 * Created by Administrator on 2017/10/10.
 */
var pathName=getRootPath();
$(document).ready(function(){
    var lastTd="<td><a href='#' class='pass' value='1'>通过</a> <a href='#' class='fail'value='2'>驳回</a></td></tr>";
    getUserAppliedInfo(0,lastTd);
    $(".MerchantBody").on("click",".pass,.fail",changeObjectState);
})
function changeObjectState(){
    var url=pathName+"/changeMerchantState.do";
    var state="";
    if($(this).hasClass("pass")){
        state=2;
    }else if($(this).hasClass("fail")){
        state=3;
    }
    var params={
        state:state,
        id:this.parentNode.parentNode.firstChild.firstChild.value
    };
    console.log(params);
    $.post(url,params,function(result){
        if(result.state==1){
            alert("操作成功");
            var lastTd="<td><a href='#' class='pass' value='1'>通过</a> <a href='#' class='fail'value='2'>驳回</a></td></tr>";
            getUserAppliedInfo(0,lastTd);
        }else{
            alert(result.message);
        }
    })
}