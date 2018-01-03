/**
 * Created by Administrator on 2017/10/11.
 */
var pathName=getRootPath();
function getUserAppliedInfo(state,lastTd) {
    var url = pathName+"/findUserAppliedInfo.do";
    var params = {
        state: state,
        currentPage: 1
    }
    console.log(lastTd);
    console.log(params);
    $.post(url, params, function (result) {
        console.log(result);
        if (result.state == 1) {
            showMerchants(result.data.pageInfo,lastTd);
        } else {
            alert(result.message);
        }
    })
}
    function showMerchants(list,lastTd) {
        var table = $(".MerchantBody");
        table.empty();
        console.log(list);
        for (var i in list) {
            var tds = "<tr class='tr"+i%2+"'><td><input type='checkbox' value='" + list[i].id + "'/></td>" +
                "<td>" + list[i].clientName + "</td>" +
                "<td>" + list[i].title + "</td>" +
                "<td>" + list[i].content + "</td>" +
                "<td>" + list[i].enterTitle + "</td>" +
                "<td>" + list[i].createTime + "</td>" +lastTd;
            table.append(tds);
        }
    }