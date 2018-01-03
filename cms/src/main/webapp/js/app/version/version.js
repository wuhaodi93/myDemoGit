/**
 * Created by Administrator on 2017/11/11.
 */
var pathName = getRootPath();
$(document).ready(function () {
    var url = pathName + "/getAppVersionInfo.do";
    showPageObject(url);
    $("#addObject").click(addObject);
    $("#save").click(updateVersion);
});
function addObject() {
    $("#myModal").modal("show");
    var url = pathName + "/app/version/updateVersion.html";
    $(".modal-body").load(url, null, function () {
    });
}

function searchByKeyword() {
    var keyword = $("#keyword").val();
    $(".tools").data("keyword", keyword);
    $(".tablelist").data("currentPage", 1);
    var url = pathName + "/getAppVersionInfo.do";
    showPageObject(url);
}

function updateVersion() {
    var version = $("#version").val();
    var remark = $("#remark").val();
    var params = {
        version: version,
        remark: remark
    }
    console.log(params);
    var url = pathName + "/updateAppVersion.do";
    $("#updateVersion").ajaxSubmit({
        type: "post",
        url: url,
        data: params,
        success: function (result) {
            if (result.state == 1) {
                getObject(1);
                $("#myModal").modal('hide');
            } else {
                alert(result.message);
            }
        }
    });
}

function getObject(currentPage) {
    var keyword = $(".tools").data("keyword");
    var url = pathName + "/getAppVersionInfo.do";
    var params = {
        keyword: keyword,
        currentPage: currentPage
    }
    $.post(url, params, function (result) {
        console.log(result);
        if (result.state == 1) {
            showObjects(result.data.pageInfo);
        } else {
            alert(result.message);
        }
    })
}

function showObjects(list) {
    var table = $("#versionUpdateInfo");
    table.empty();
    for (var i in list) {
        var tds = "<tr class='tr" + i % 2 + "'><td><input type='checkbox' value='" + list[i].id + "'/></td>" +
            "<td>" + list[i].version + "</td>" +
            "<td>" + list[i].name + "</td>" +
            "<td>" + list[i].uploadUser + "</td>" +
            "<td>" + list[i].createTime + "</td>" +
            "<td>" + list[i].downloadTimes + "</td>" +
            "<td><a>删除 </a><a> 修改</a></td>" +
            "<td>" + list[i].remark + "</td>" +
            "</tr>"
        table.append(tds);
    }
}