/**
 * Created by Administrator on 2017/11/3.
 */
jQuery.support.cors = true;
/**
 * 获取当前主机地址https://www.gxht.net.cn/
 * @returns {string}
 */
function getpathName() {
    // 获取当前网址，如：http://localhost/WebCourse/jsp/login/login.jsp
    var curWwwPath = window.document.location.href;
    // 获取主机地址之后的目录，如： WebCourse/jsp/login/login.jsp
    var pathName = window.document.location.pathName;
    var pos = curWwwPath.indexOf(pathName);
    // 获取主机地址，如： http://localhost
    var pathName = curWwwPath.substring(0, pos);
    return pathName;
}
//获取当前项目的地址
var pathName = getpathName() + "/app_yjdPlatform/";

$(document).ready(function () {
    //给编辑方法绑定事件
    $(".imglist").on("click", ".SudokuEdit,.hotLoansEdit,.loansEdit,.applyCardEdit,.carouselEdit", editObject).on('click', ".delete", deleteObject);
    //保存修改
    $("#myModal").on("click", "#save", save)
    //添加页面对象
    $(".imglist").on('click',".addObject",addObject);
     //展示首页(轮播)
    showObject();
})
/**
 * 添加页面对象
 */
function addObject() {
    var url = pathName + "";
    $("#myModal").modal("show");
    $("#editImg").css("display", "none");
    $("#editTitle").val("");
    $("#editContent").val("");
    $("#editUrl").val("");
    var id=$(this).attr("id");
    var type="";
    if (id=="Sudoku") {
        type="Sudoku";
        $("#myModalLabel").html("增加九宫格");
        $("#sizing-addon1").css("display", "inline-table");
        $("#sizing-addon2").css("display", "none");
        $("#sizing-addon3").css("display", "none");
    } else if (id=="hotLoans") {
        var type="hotLoans";
        $("#myModalLabel").html("增加热门贷款");
        $("#sizing-addon1").css("display", "inline-table");
        $("#sizing-addon2").css("display", "inline-table");
        $("#sizing-addon3").css("display", "inline-table");
    } else if (id=="loans") {
        var type="loans";
        $("#myModalLabel").html("增加贷款列表");
        $("#sizing-addon1").css("display", "inline-table");
        $("#sizing-addon2").css("display", "inline-table");
        $("#sizing-addon3").css("display", "inline-table");
    } else if (id=="applyCard") {
        var type="applyCard";
        $("#myModalLabel").html("增加申请信用卡列表");
        $("#sizing-addon1").css("display", "inline-table");
        $("#sizing-addon2").css("display", "inline-table");
        $("#sizing-addon3").css("display", "inline-table");
    } else {
        var type="";
        $("#myModalLabel").html("增加轮播图");
        $("#sizing-addon1").css("display", "none");
        $("#sizing-addon2").css("display", "none");
        $("#sizing-addon3").css("display", "none");
    }
    $(".imglist").data("imgType",type);
    $("#myModal").data("insertOrEdit", "insert");
    //用户点击按钮跟后台数据对接
}
/**
 * 编辑页面对象
 */
function editObject() {
    $("#myModal").modal("show");
    var id = this.parentNode.firstChild.value;
    var params = {id: id};
    var lunboImg = this.parentNode.firstChild.value;
    var url = "";
    var array = "";
    var type = $(this).attr("class");
    if ($(this).hasClass("SudokuEdit")) {
        url = pathName + "findHengpai.do"
        $.get(url, params, function (result) {
            array = result.data.henpai[0];
            showObjectInModal(array);
        });
    } else if ($(this).hasClass("hotLoansEdit")) {
        url = pathName + "findHotLoans.do";
        $.get(url, params, function (result) {
            array = result.data.pageInfo[0];
            showObjectInModal(array);
        });
    } else if ($(this).hasClass("loansEdit")) {
        url = pathName + "findLoads.do";
        $.get(url, params, function (result) {
            array = result.data.pageInfo[0];
            showObjectInModal(array);
        });
    } else if ($(this).hasClass("applyCardEdit")) {
        url = pathName + "findCards.do";
        $.get(url, params, function (result) {
            array = result.data.pageInfo[0];
            showObjectInModal(array);
        });
    }
    $("#myModal").data("type", type);
    $("#myModal").data("insertOrEdit", "edit");
}
/**
 *
 */
function showObject() {
    var url = new Array();
    url[0] = pathName + "findPictureByType.do?type=轮播";
    url[1] = pathName + "findHengpai.do";
    url[2] = pathName + "findHotLoans.do";
    url[3] = pathName + "findLoads.do";
    url[4] = pathName + "findCards.do";
    var typeArray = new Array();
    typeArray[0] = "lunbo";
    typeArray[1] = "Sudoku";
    typeArray[2] = "hotLoans";
    typeArray[3] = "loans";
    typeArray[4] = "applyCard";
    for (var i = 0; i < typeArray.length; i++) {
        getObject(url[i], typeArray[i]);
    }
}

function getObject(url,type) {
    var tab1 = $("#tab1");
    var tab2 = $("#tab2");
    var tab3 = $("#tab3");
    var tab4 = $("#tab4");
    var tab5 = $("#tab5");
    $.getJSON(url, null, function (result) {
        if (type == "Sudoku") {
            returnArray = result.data.henpai;
            lis = showLoandsOrCard(returnArray, "Sudoku");
            tab2.empty();
            tab2.append(lis);
        } else if (type == "hotLoans") {
            returnArray = result.data.pageInfo;
            lis = showLoandsOrCard(returnArray, "hotLoans");
            tab3.empty();
            tab3.append(lis);
        } else if (type == "loans") {
            returnArray = result.data.pageInfo;
            lis = showLoandsOrCard(returnArray, "loans");
            tab4.empty();
            tab4.append(lis);
        } else if (type == "applyCard") {
            returnArray = result.data.pageInfo;
            lis = showLoandsOrCard(returnArray, "applyCard");
            tab5.empty();
            tab5.append(lis);
        } else {
            returnArray = result.data;
            lis = showLunbo(returnArray, "carousel");
            tab1.empty();
            tab1.append(lis);
        }
    });
}

function showLunbo(array, classType) {
    var lis = "";
    for (var i in array) {
        var li = "<li><img src='" + array[i].img + "' class='lunboImg'/>" +
            "<div><p><input type='hidden' value='" + array[i].id + "'><a class='" + classType + "Edit' style='display: none'>编辑</a><a class='delete'>删除</a></p></div></li>"
        lis += li;
    }
    lis+="<li class='click addObject' id='"+classType+"'><img src='../../images/add3.png'/></li>";
    return lis;
}

function showLoandsOrCard(array, classType) {
    var lis = "";
    for (var i in array) {
        $("#myModal").data("id", array[i].id);
        var li = "<li><img src='" + array[i].img + "' class='CardOrLoansImg'/>" +
            "<div class='eidtInfo'><span>" + array[i].title + "</span>" +
            "<p><input type='hidden' value='" + array[i].id + "'><a class='" + classType + "Edit'>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class='delete'>删除</a></p></div></li>"
        lis += li;
    }
    lis+="<li class='click addObject' id='"+classType+"'><img src='../../images/add3.png'/></li>";
    return lis;
}
//将客户的信息展现在modal上
function showObjectInModal(array) {
    $("#myModal").data("id", array.id);
    $("#editImg").attr("src", array.img);
    $("#sizing-addon1").css("display", "inline-table");
    $("#editTitle").val(array.title);
    $("#editImg").css("display", "block");
    if (array.content) {
        $("#sizing-addon3").css("display", "none");
        $("#sizing-addon2").css("display", "inline-table");
        $("#editContent").val(array.content);
    } else {
        $("#sizing-addon2").css("display", "none");
        $("#sizing-addon3").css("display", "none");
    }
    if (array.url) {
        $("#sizing-addon3").css("display", "inline-table");
        $("#editUrl").val(array.url);
    } else {
        $("#sizing-addon3").css("display", "none");
    }
}
 //修改保存
function save() {
    var url = "";
    var params;
    var saveType = $("#myModal").data("insertOrEdit");
    console.log("saveType",saveType);
    if (saveType == "edit") {
        url = pathName + "appUpdataPageInfo.do";
        params = {
            id: $("#myModal").data("id"),
            classType: $("#myModal").data("type"),
            title: $("#editTitle").val(),
            content: $("#editContent").val(),
            path: $("#editImg").attr("src"),
            url: $("#editUrl").val()
        }
    } else if (saveType == "insert") {
        url = pathName + "addPageListObject.do";
        params = {
            imgType: $(".imglist").data("imgType"),
            title: $("#editTitle").val(),
            content: $("#editContent").val(),
            url: $("#editUrl").val()
        }
    }
    console.log(params);
    $("#apply").ajaxSubmit({
        type: "post",
        url: url,
        data: params,
        success: function (result) {
            if (result.state == 1) {
                showObject();
                $("#myModal").modal('hide');
            } else {
                alert(result.message);
            }
        }
    });
}
//删除
function deleteObject() {
    if (!doConfirm()) {
        return;
    }
    var path = $(this.parentNode.parentNode.parentNode.childNodes[0]).attr("src");
    var id = $(this.parentNode.childNodes[0]).val();
    var classType = $(this.parentNode.childNodes[1]).attr("class");
    var params = {
        path: path,
        id: id,
        classType: classType
    }
    var url = pathName + "/deletePageInfo.do";
    $.get(url, params, function (result) {
        if (result.state == 1) {
            showObject();
        } else {
            alert(result.message);
        }
    });
}