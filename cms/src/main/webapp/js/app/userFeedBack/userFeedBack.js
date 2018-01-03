/**
 * Created by Administrator on 2017/11/6.
 */
/**
 * Created by Administrator on 2017/10/11.
 */
var pathName=getRootPath();
$(document).ready(function () {
    var url=pathName+"/findUserFeedBack.do";
    showPageObject(url);
    $(".tablelist").data("currentPage",1);
})
function getObject(currentPage) {
    var url = pathName+"/findUserFeedBack.do";
    var params = {
        currentPage: currentPage
    }
    $.post(url, params, function (result) {
        if (result.state == 1) {
            if(result.data.pageObject.currentPage>result.data.pageObject.allPage){
                if(result.data.pageObject.currentPage>1){
                    alert("没有更多内容");
                }
                $(".tablelist").data("currentPage",--result.data.pageObject.currentPage);
            }else {
                showObjects(result.data.pageInfo);
            }
        } else {
            alert(result.message);
            $(".currentPage").html(result.data.pageObject.currentPage);
            $(".allPage").html(result.data.pageObject.allPage);
        }
    })
    function showObjects(list) {
        var table = $("#feedBack");
        table.empty();
        for (var i in list) {
            var tds = "<tr class='tr" + i % 2 + "'><td><input type='checkbox' value='" + list[i].id + "'/></td>" +
                "<td>" + list[i].name + "</td>" +
                "<td>" + list[i].content + "</td></tr>"
                table.append(tds);
        }
    }
}

