/**
 * Created by Administrator on 2017/10/11.
 */
var pathName=getRootPath();
$(document).ready(function () {
    $(".MerchantBody").on("click", ".pass,.fail", setClientState);
    var url=getRootPath()+"/findMerchantEnterApplyInfo.do?state=1";
    //调用分页page.js方法获取数据分页展现数据
    showPageObject(url);
})
/**
 * 改变客户的状态
 */
function setClientState() {
    var url = getRootPath()+"/appChangeMerchantState.do";
    var state = "";
    var params;
    if ($(this).hasClass("pass")) {
        state = 2;
        params = {
            state: state,
            id: this.parentNode.parentNode.firstChild.firstChild.value
        };
    } else {
        state = 3;
        var failedMessage=this.parentNode.parentNode.lastChild.firstChild.value;
        if(failedMessage.length>=15){
            alert("失败原因不要超过15个字");
            return;
        }
        if(!failedMessage){
            alert("请填写驳回信息!");
            return;
        }
        params = {
            failedMessage:failedMessage,
            state: state,
            id: this.parentNode.parentNode.firstChild.firstChild.value
        };
    }
    $.post(url, params, function (result) {
        if (result.state == 1) {
            alert("操作成功");
            var currentPage=$(".tablelist").data("currentPage");
            getObject(currentPage);
        } else {
            alert(result.message);
        }
    })
}
//获取当前页面的数据
function getObject(currentPage) {
    var url = getRootPath()+"/findMerchantEnterApplyInfo.do";
    var params = {
        state: 1,
        currentPage: currentPage
    }
    $.post(url, params, function (result) {
        if (result.state == 1) {
            showObjects(result.data.pageInfo);
        } else {
            alert(result.message);
        }
    })
    //展现当前页面的数据
    function showObjects(list) {
        var table = $(".MerchantBody");
        table.empty();
        for (var i in list) {
            var businessLicenseTd=list[i].businessLicense?"<img class='tableImage' src='"+list[i].businessLicense+"'>":"无";
            var companyNameTd=list[i].companyName?list[i].companyName:"无";
            var tds = "<tr class='tr" + i % 2 + "'><td><input type='checkbox' value='" + list[i].id + "'/></td>" +
                "<td>" + list[i].title + "</td>" +
                "<td>" + companyNameTd + "</td>" +
                "<td><img class='tableImage' src='"+list[i].img+"'></td>" +
                "<td>" + list[i].merchantType + "</td>" +
                "<td>"+businessLicenseTd+"</td>" +
                "<td>" + list[i].content + "</td>" +
                "<td>" + list[i].applyType + "</td>" +
                "<td>" + list[i].cityName + "</td>" +
                "<td>" + list[i].createDate + "</td>" +
                "<td>" + list[i].userName + "</td>" +
                "<td>" + list[i].phone + "</td>" +
                "<td><a href='#' class='pass' value='2'>通过</a> <a href='#' class='fail'value='3'>驳回</a></td>" +
                "<td><input type='text' class='failedMessage' style='height: 35px' width='350px'/></td></tr>";
            table.append(tds);
        }
    }
}
