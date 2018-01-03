/**
 * Created by Administrator on 2017/11/22.
 */
/**
 * Created by Administrator on 2017/10/11.
 */
var pathName = getRootPath();
$(document).ready(function () {
    // $("#searchByKeyword").click(searchByKeyword);
    var url = getRootPath() + "/findQueryRecord.do";
    showPageObject(url);
    $(".queryBtn").click(returnQueryInfoPage);
    $(".seachform input").change(searchByKeyword);
})
function returnQueryInfoPage() {
    var name = $("#name").val();
    var phone = $("#phone").val();
    var idCard = $("#idCard").val();
    var qq = $("#qq").val();
    var email = $("#email").val();
    if (!name && !phone && phone.length < 11 && !idCard && idCard.length < 18) {
        alert("请输入正确的参数!!!");
        return;
    }
    $("#queryForm").submit();
}
function searchByKeyword(){
    var name=$("#name").val();
    var phone=$("#phone").val();
    var idCard=$("#idCard").val();
    var keyword=name+","+phone+","+idCard;
    $(".tools").data("keyword",keyword);
    var url= getRootPath() + "/findQueryRecord.do";
    showPageObject(url);
}
function getObject(currentPage) {
    var keyword = $(".tools").data("keyword");
    var state = $(".tablelist").data("state");
    var params = {
        keyword: keyword,
        currentPage: currentPage,
        state: state
    }
    var url = getRootPath() + "/findQueryRecord.do";
    $.post(url, params, function (result) {
        console.log(result)
        if (result.state == 1) {
            showObjects(result.data.pageInfo);
        } else {
            alert(result.message);
        }
    })
    function showObjects(list) {
        var table = $(".historyBody");
        table.empty();
        for (var i in list) {
            var tds = "<tr class='tr" + i % 2 + "'><td><input type='checkbox' value='" + list[i].id + "'/></td>" +
                "<td>" + list[i].name + "</td>" +
                "<td>" + list[i].phone + "</td>" +
                "<td>" + list[i].cardNo + "</td>" +
                "<td>" + list[i].query_qq + "</td>" +
                "<td>" + list[i].query_email + "</td>" +
                "<td>" + list[i].createTime + "</td>" +
                "<td>" + list[i].queryBy + "</td>" +
                "<td><a href='" + pathName + "/skipToQueryInfoJsp.do?id=" + list[i].id + "&state=1'>查看</a></td></tr>";
            table.append(tds);
        }
    }
}
