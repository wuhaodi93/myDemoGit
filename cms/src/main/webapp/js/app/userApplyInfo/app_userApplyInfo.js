/**
 * Created by Administrator on 2017/10/11.
 */
var pathName=getRootPath();
$(document).ready(function () {
    var url=pathName+"/appFindUserApplication.do";
    showPageObject(url);
    $(".userApplyInfoBody").on("click",".userInfoDetail,.merchantInfoDetail",showUserOrMerchantInfo);
    $(".tablelist").data("currentPage",1);
    $("#searchByKeyword").click(searchByKeyword);
})
function searchByKeyword(){
    var keyword=$("#keyword").val();
    $(".tools").data("keyword",keyword);
    $(".tablelist").data("currentPage",1);
    var url=pathName+"/appFindUserApplication.do";
    showPageObject(url);
}
function showUserOrMerchantInfo() {
    var url;
    var params;
    var id = $(this.firstChild).val();
    console.log(id);
    if($(this).hasClass("userInfoDetail")){
        var url=pathName+"/app/userApplyInfo/userEdit.html";
        $("#myModal").data("target","user");
        params={
            target:"user"
        }
        $("#myModalLabel").html("申请人信息");
    }else if($(this).hasClass("merchantInfoDetail")){
        var url=pathName+"/app/userApplyInfo/merchantEdit.html";
        params={
            target:"merchant"
        }
        $("#myModal").data("target","merchant");
        $("#myModalLabel").html("商家信息");
    }
    $("#myModal").data("id",id);
    $(".modal-body").load(url,params,function () {
        $("#myModal").modal("show");
    })
}
function getObject(currentPage) {
    var url = pathName+"/appFindUserApplication.do";
    var keyword=$(".tools").data("keyword");
    var params = {
        currentPage: currentPage,
        keyword:keyword
    }
    $.post(url, params, function (result) {
        if (result.state == 1) {
            showObjects(result.data.pageInfo);
        } else {
            alert(result.message);
        }
    })
    function showObjects(list) {
        var table = $(".userApplyInfoBody");
        table.empty();
        for (var i in list) {
            var tds = "<tr class='tr" + i % 2 + "'><td><input type='checkbox' value='" + list[i].id + "'/></td>" +
                "<td class='userInfoDetail'><input type='hidden' value='"+list[i].applicationUserId+"'><u>" + list[i].applicationUserName + "</u></td>" +
                "<td>" + list[i].applicationUserPhone + "</td>" +
                "<td class='merchantInfoDetail'><input type='hidden' value='"+list[i].applyInfoId+"'><u>" + list[i].title + "</td>" +
                "<td>" + list[i].createTime + "</td>" +
                "</tr>";
            table.append(tds);
        }
    }
}
