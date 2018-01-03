/**
 * Created by Administrator on 2017/10/11.
 */
var pathName=getRootPath();
$(document).ready(function(){
    getClientAuthenticationInfo();
    $("#clientBody").on("click",".pass,.fail",changeClientState);
    // $("#clientBody").on("click",".icon-download",downloadAuthFile);
})
// function downloadAuthFile(){
//     var url="downloadFile.do";
//     var params={
//         "clientId":$(this).val()
//     }
//     $.post(url,params,function(result){
//         if(result.state==1){
//             alert("下载成功");
//         }else {
//             console.log(result);
//             alert(result.massage);
//         }
//     });
// }
function changeClientState(){
    var url=pathName+"/changeClientState.do";
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
        console.log(result);
        if(result.state==1){
            alert("操作成功");
            getClientAuthenticationInfo();
        }else{
            alert(result.message);
        }
    })
}
function getClientAuthenticationInfo() {
    console.log(pathName)
    var url=pathName+"/findUnAuthenticationClients.do";
    var params={
        state:0,
        currentPage:1
    }
    $.post(url,params,function(result){
        console.log(result);
        if(result.state==1){
            showObjects(result.data.pageInfo);
        }else{
            alert(result.message);
        }
    })
    function showObjects(list){
        var table = $("#clientBody");
        table.empty();
        console.log(list);
        for(var i in list){
            var tds="<tr class='tr"+i%2+"'><td><input type='checkbox' value='"+list[i].id+"'/></td>" +
                "<td>"+list[i].name+"</td>" +
                "<td>"+list[i].cityName+"</td>" +
                "<td>"+list[i].phone+"</td>" +
                "<td>"+list[i].OtherPhone+"</td>" +
                "<td>"+list[i].createDate+"</td>" +
                "<td><a class='icon-download' href="+pathName+"'/downloadFile.do?clientId="+list[i].id+"'>下载</a>"+
                "<td><a href='#' class='pass' value='1'>通过</a> <a href='#' class='fail'value='2'>驳回</a></td></tr>";
                table.append(tds);
        }
    }
}
