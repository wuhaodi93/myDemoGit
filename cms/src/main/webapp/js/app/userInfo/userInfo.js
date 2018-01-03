/**
 * Created by Administrator on 2017/10/11.
 */
var pathName = getRootPath();
$(document).ready(function () {
    var url=pathName + "/findUnAuthUsers.do";
    showPageObject(url);
    $(".tablelist").data("currentPage", 1);
    $("#searchByKeyword").click(searchByKeyword);
    $(".authStatus").change(chooseAuthState);
})
function chooseAuthState() {
    var option=$(".authStatus").find("option:selected");
    var optionValue=option.val();
    if(optionValue==4){
        optionValue=null;
    }
    getAuthDataByState(optionValue)
}
function getAuthDataByState(state){
    $(".tablelist").data("state",state);
    $(".tools").data("keyword",null);
    var url= getRootPath() + "/findUnAuthUsers.do";
    showPageObject(url);
}
function searchByKeyword() {
    var keyword = $("#keyword").val();
    $(".tools").data("keyword", keyword);
    $(".tablelist").data("state",null);
    $(".tablelist").data("currentPage", 1);
    var url=pathName + "/findUnAuthUsers.do";
    showPageObject(url);
}
function getObject(currentPage) {
    var keyword = $(".tools").data("keyword");
    var state = $(".tablelist").data("state");
    var url = pathName + "/findUnAuthUsers.do";
    var params = {
        state:state,
        keyword: keyword,
        currentPage: currentPage
    }
    console.log(state);
    $.post(url, params, function (result) {
        if (result.state == 1) {
            showObjects(result.data.pageInfo);
        } else {
            alert(result.message);
        }
    })
    function showObjects(list) {
        var table = $("#clientBody");
        table.empty();
        var authInfo = "";
        for (var i in list) {
            var topTd = "";
            var backTd = "";
            var holdTd = "";
            if (list[i].authImgs) {
                for (var j in list[i].authImgs) {
                    var imgPrefix = "https://www.gxht.net.cn/app_yjdPlatform/userAuthInfo";
                    var imgPath = list[i].authImgs[j].path;
                    var type = list[i].authImgs[j].typeId==1?"/userIdTop":(list[i].authImgs[j].typeId==2?"/userIdBack":"/userIdHold");
                    var imgSrc=imgPrefix + type + list[i].id + "/" + imgPath;
                    if (j == 0) {
                        topTd = "<img class='tableImage' src='" + imgSrc + "'>";
                    } else if (j == 1) {
                        backTd = "<img class='tableImage' src='" + imgSrc + "'>";
                    } else {
                        holdTd = "<img class='tableImage' src='" + imgSrc + "'>";
                    }
                }
            }
            var tds = "<tr class='tr" + i % 2 + "'><td><input type='checkbox' value='" + list[i].id + "'/></td>" +
                "<td>" + list[i].account + "</td>" +
                "<td>" + list[i].phone + "</td>" +
                "<td>" + (list[i].authName ? list[i].authName : "") + "</td>" +
                "<td>" + (list[i].authCardNo ? list[i].authCardNo : "") + "</td>" +
                "<td>" + list[i].authStatuName + "</td>" +
                "<td>" + (list[i].authTime ? list[i].authTime : "") + "</td>" +
                "<td>" + topTd + "</td>" +
                "<td>" + backTd + "</td>" +
                "<td>" + holdTd + "</td>" +
                "</tr>"
            table.append(tds);
        }
    }
}

