/**
 * Created by Administrator on 2017/10/11.
 */
var pathName = getRootPath();
$(document).ready(function () {
    $("#clientBody").on("click", ".pass,.fail", setClientState);
    var url = pathName + "/findUnAuthUsers.do?state=0";
    showPageObject(url);
})
function setClientState() {
    var url = pathName + "/AppUpdateUserState.do";
    var state = "";
    var params;
    if ($(this).hasClass("pass")) {
        state = 1;
        params = {
            state: state,
            id: this.parentNode.parentNode.firstChild.firstChild.value
        };
    } else {
        state = 2;
        var failedMessage = this.parentNode.parentNode.lastChild.firstChild.value;
        if (failedMessage.length >= 15) {
            alert("失败原因不要超过15个字");
            return;
        }
        if (!failedMessage) {
            alert("请填写驳回信息!");
            return;
        }
        params = {
            failedMessage: failedMessage,
            state: state,
            id: this.parentNode.parentNode.firstChild.firstChild.value
        };
    }
    $.post(url, params, function (result) {
        if (result.state == 1) {
            alert("操作成功");
            var currentPage = $(".tablelist").data("currentPage");
            getObject(currentPage);
        } else {
            alert(result.message);
        }
    })
}
function getObject(currentPage) {
    var url = pathName + "/findUnAuthUsers.do";
    var params = {
        state: 0,
        currentPage: currentPage
    }
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
                    var type = list[i].authImgs[j].typeId == 1 ? "/userIdTop" : (list[i].authImgs[j].typeId == 2 ? "/userIdBack" : "/userIdHold");
                    var imgSrc = imgPrefix + type + list[i].id + "/" + imgPath;
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
                "<td>" + list[i].authName + "</td>" +
                "<td>" + list[i].authCardNo + "</td>" +
                "<td>" + topTd  + "</td>" +
                "<td>" + backTd + "</td>" +
                "<td>" + holdTd + "</td>" +
                "<td>" + list[i].authTime + "</td>" +
                "<td><a href='#' class='pass' value='1'>通过</a> <a href='#' class='fail'value='2'>驳回</a></td>" +
                "<td><input type='text' class='failedMessage' style='height: 35px' width='350px'/></td></tr>";
            table.append(tds);
        }
    }
}
