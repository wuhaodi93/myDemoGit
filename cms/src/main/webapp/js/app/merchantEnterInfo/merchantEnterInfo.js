/**
 * Created by Administrator on 2017/10/11.
 */
var pathName = getRootPath();
$(document).ready(function () {
    $("#searchByKeyword").click(searchByKeyword);
    var url= getRootPath() + "/findMerchantEnterApplyInfo.do";
    //获取分页的首页数据并展现 调用page.js
    showPageObject(url);
    chooseApplyState();
    $(".authStatus").change(chooseApplyState);
    $(".applyStatus").change(chooseMerchantState);
})
function chooseMerchantState(){
    var option=$(".applyStatus").find("option:selected");
    var optionValue=option.val();
    if(optionValue==0){
        optionValue=null;
    }
    getPointMerchantState(optionValue)
}
function getPointMerchantState(state) {
    $(".tablelist").data("merchantState",state);
    $(".tools").data("keyword",null);
    var url= getRootPath() + "/findMerchantEnterApplyInfo.do";
    showPageObject(url);
}
function chooseApplyState() {
    var option = $(".authStatus").find("option:selected");
    var optionValue=option.val();
    if(optionValue==0){
        optionValue=null;
    }
    getPointEnterInfo(optionValue);
}
function searchByKeyword(){
    var keyword=$("#keyword").val();
    $(".tools").data("keyword",keyword);
    $(".tablelist").data("state",null);
    $(".tablelist").data("merchantState",null);
    var url= getRootPath() + "/findMerchantEnterApplyInfo.do";
    showPageObject(url);
}
function getPointEnterInfo(state) {
    $(".tablelist").data("state",state);
    $(".tools").data("keyword",null);
    var url= getRootPath() + "/findMerchantEnterApplyInfo.do";
    showPageObject(url);
}
function getObject(currentPage) {
    var keyword=$(".tools").data("keyword");
    var state=$(".tablelist").data("state");
    var merchantState=$(".tablelist").data("merchantState");
    var params={
        merchantType:merchantState,
        keyword:keyword,
        currentPage:currentPage,
        state:state
    }
    var url = getRootPath() + "/findMerchantEnterApplyInfo.do";
    $.post(url, params, function (result) {
        if (result.state == 1) {
            showObjects(result.data.pageInfo);
        } else {
            alert(result.message);
        }
    })
    function showObjects(list) {
        var table = $(".MerchantBody");
        table.empty();
        for (var i in list) {
            var businessLicenseTd = list[i].businessLicense ? "<img class='tableImage' src='" + list[i].businessLicense + "'>" : "无";
            var companyNameTd = list[i].companyName ? list[i].companyName : "无";
            var failedMessageTd = list[i].failedMessage ? list[i].failedMessage : "";
            var tds = "<tr class='tr" + i % 2 + "'><td><input type='checkbox' value='" + list[i].id + "'/></td>" +
                "<td>" + list[i].title + "</td>" +
                "<td>" + companyNameTd + "</td>" +
                "<td><img class='tableImage' src='" + list[i].img + "'></td>" +
                "<td>" + list[i].merchantType + "</td>" +
                "<td>" + list[i].statuName + "</td>" +
                "<td>" + businessLicenseTd + "</td>" +
                "<td>" + list[i].content + "</td>" +
                "<td>" + list[i].applyType + "</td>" +
                "<td>" + list[i].cityName + "</td>" +
                "<td>" + list[i].createDate + "</td>" +
                "<td>" + list[i].userName + "</td>" +
                "<td>" + list[i].phone + "</td>" +
                "<td>" + failedMessageTd + "</td></tr>";
            table.append(tds);
        }
    }
}
