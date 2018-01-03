/**
 * Created by Administrator on 2017/12/27.
 */
var pathName = getRootPath()
$(document).ready(function () {
    $(".queryBtn").click(doSericaAndIEMI)
    $(".queryHistoryBtn").click(doQueryHistory)
    //绑定查看按钮点击事件
})
function doCheckHistory(btn) {
    console.log($(btn).val())
    $("#resultPhone").html("")
    $("#myModal").modal('show')
    var path = $(btn).val()
    var url = pathName + "/readPhoneFileByUrl.do"
    $.post(url, {path: path}, function (res) {
        console.log(res)
        showResultInfoForModel(res.data)
    })

}
function doQueryHistory() {
    $("#phone_history tbody").html("")
    var sn = $("#sericaAndIEMI").val()
    $("#div1").css("display", "none")
    $(".queryHistory").css("display", "block")
    $(".div2").css("display", "block")
    var url = pathName + "/doReadObjectFileBySn.do"
    var param = {
        sn: sn
    }
    $.getJSON(url, param, function (res) {
        console.log(res)
        var setTotalCount = res.data.pageObject.phoneamount;
        var allPage = res.data.pageObject.allPage;
        if (res.data.pageInfo.length <= 0) {
            alert("没有对应的记录")
            $(".div2").css("display", "none")
            return;
        }
        if (res.state == 1) {
            $('#box').paging({
                initPageNo: 1, // 初始页码
                totalPages: allPage, //总页数
                totalCount: '合计' + setTotalCount + '条数据', // 条目总数
                slideSpeed: 600, // 缓动速度。单位毫秒
                jump: true, //是否支持跳转
                callback: function (page) { // 回调函数
                    getPageInfo(page, param);
                }
            })
        } else {
            alert(res.message);
        }
        showResultHistory(res.data.pageInfo)
        $(".div2").css("display", "none")
    })
}
function doSericaAndIEMI() {
    $("#result").html("")
    $("#div1").css("display", "block")
    $(".queryHistory").css("display", "none")
    var testStr = $("#sericaAndIEMI").val();
    var IMEI = /^[0-9]{15}$/;
    var sn = /^[0-9a-zA-Z]{11,12}$/;
    var url = pathName + "/doGetSericaAndIEMIInfo.do"
    if (testStr.length > 0) {
        if (!IMEI.test(testStr) && !sn.test(testStr)) {
            alert("请输入正确的序列号或IMEI码")
        } else {
            $(".div2").css("display", "block")
            $.getJSON(url, {sericaAndIEMI: testStr}, function (result) {
                //console.log(result)
                if ($.parseJSON(result.data).code != '0') {
                    alert("查询失败:" + "\n" + "原因:" + $.parseJSON(result.data).message)
                    $(".div2").css("display", "none")
                    return;
                } else {
                    if (result.data != null) {
                        showResultInfo($.parseJSON(result.data).data)
                        $(".div2").css("display", "none")
                    } else {
                        alert("查询失败")
                    }
                }
            })
        }
    } else {
        alert("内容不能为空")
    }
}
//显示查询结果
function showResultInfoForModel(rs) {

    $tr = $("<tr><td>序列号:</td><td>" + rs.sn + "</td></tr>" +
        "<tr><td>IMEI:</td><td>" + rs.imei + "</td></tr>" +
        "<tr><td>型号:</td><td>" + rs.model + "</td></tr>" +
        "<tr><td>容量:</td><td>" + rs.storage + "</td></tr>" +
        "<tr><td>颜色:</td><td>" + changeChinese(rs.color) + "</td></tr>" +
        "<tr><td>激活锁:</td><td>" + (rs.fmi == 'On' ? '开启' : '关闭') + "</td></tr>" +
        "<tr><td>设备状态:</td><td>" +
        (rs.status == 'normal' ? '正常' : (rs.status == 'refurbished' ? '官换机' : (rs.status == 'repaired' ? '官修机(官翻机)' : (rs.status == 'exception' ? '异常机' : (rs.status == 'diagnostic' ? '诊断案例' : '其他'))))) + "</td></tr>" +
        "<tr><td>首次激活日期  :</td><td>" + rs.purchase.date + "</td></tr>" +
        "<tr><td>保修结束日期:</td><td>" + rs.coverage + "</td></tr>" +
        "<tr><td>保修剩余:</td><td>" + (rs.daysleft == 'expired' ? '已过期' : (rs.daysleft + '天')) + "</td></tr>" +
        "<tr><td>技术支持:</td><td>" + (rs.support == 'expired' ? '已过期' : rs.support) + "</td></tr>" +
        "<tr><td>是否延保:</td><td>" + (rs.applecare == 'false' ? '否' : '是') + "</td></tr>" +
        "<tr><td>维修状态:</td><td>" + (rs.repair == 'none' ? '无' : (rs.repair == 'once repaired' ? '曾经维修' : '正在维修')) + "</td></tr>" +
        "<tr><td>销售代码:</td><td>" + rs.sales + "</td></tr>" +
        "<tr><td>出长日期:</td><td>" + rs.manufacture.date + "</td></tr>" +
        "<tr><td>产地:</td><td>" + rs.manufacture.factory + "</td></tr>"
    )
    $tr.appendTo($("#resultPhone"))
}
//将结果显示到模态框上
function showResultInfo(rs) {

    $tr = $("<tr><td>序列号:</td><td>" + rs.sn + "</td></tr>" +
        "<tr><td>IMEI:</td><td>" + rs.imei + "</td></tr>" +
        "<tr><td>型号:</td><td>" + rs.model + "</td></tr>" +
        "<tr><td>容量:</td><td>" + rs.storage + "</td></tr>" +
        "<tr><td>颜色:</td><td>" + changeChinese(rs.color) + "</td></tr>" +
        "<tr><td>激活锁:</td><td>" + (rs.fmi == 'On' ? '开启' : '关闭') + "</td></tr>" +
        "<tr><td>设备状态:</td><td>" +
        (rs.status == 'normal' ? '正常' : (rs.status == 'refurbished' ? '官换机' : (rs.status == 'repaired' ? '官修机(官翻机)' : (rs.status == 'exception' ? '异常机' : (rs.status == 'diagnostic' ? '诊断案例' : '其他'))))) + "</td></tr>" +
        "<tr><td>首次激活日期  :</td><td>" + rs.purchase.date + "</td></tr>" +
        "<tr><td>保修结束日期:</td><td>" + rs.coverage + "</td></tr>" +
        "<tr><td>保修剩余:</td><td>" + (rs.daysleft == 'expired' ? '已过期' : (rs.daysleft + '天')) + "</td></tr>" +
        "<tr><td>技术支持:</td><td>" + (rs.support == 'expired' ? '已过期' : rs.support) + "</td></tr>" +
        "<tr><td>是否延保:</td><td>" + (rs.applecare == 'false' ? '否' : '是') + "</td></tr>" +
        "<tr><td>维修状态:</td><td>" + (rs.repair == 'none' ? '无' : (rs.repair == 'once repaired' ? '曾经维修' : '正在维修')) + "</td></tr>" +
        "<tr><td>销售代码:</td><td>" + rs.sales + "</td></tr>" +
        "<tr><td>出长日期:</td><td>" + rs.manufacture.date + "</td></tr>" +
        "<tr><td>产地:</td><td>" + rs.manufacture.factory + "</td></tr>"
    )
    $tr.appendTo($("#result"))
}
//显示历史记录结果
function showResultHistory(list) {
    console.log(list[0].sn)
    for (var i = 0; i < list.length; i++) {
        $tr = $("<tr><td>" + list[i].sn + "</td><td>" + list[i].queryBy + "</td><td>" + list[i].createTime + "</td><td><button class='btn btn-primary btn-sm check' style='float: right' value='" + list[0].infoFilePath + "' onclick='doCheckHistory(this)'>查看</button></td></tr>")
        $tr.appendTo($("#phone_history tbody"))
    }

}
function getPageInfo(currentPage, param) {
    var url = pathName + "/doReadObjectFileBySn.do"
    param.currentPage = currentPage
    $.post(url, param, function (result) {
        console.log(result)
        if (result.state == 1) {
            $("#phone_history tbody").html("")
            showResultHistory(result.data.pageInfo);
        } else {
            alert(result.message);
        }
    })
}
//将颜色英文转换为对应中文
function changeChinese(str) {
    var arrE = new Array("Jet Black", "Rose Gold", "Space Gray", "Gray", "Black", "White", "Gold", "Silver", "Blue", "Pink", "Green", "Yellow", "Red")
    var arrC = new Array("亮黑色", "玫瑰金色", "深空灰色", "灰色", "黑色", "白色", "金色", "银色", "蓝色", "粉色", "绿色", "黄色", "红色")
    return arrC[arrE.indexOf(str)]
}