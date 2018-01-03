/**
 * Created by Administrator on 2017/10/10.
 */
var rootPath = getRootPath();
$(document).ready(function () {
    getObject();
    $("#userBody").on("click", ".modify", modifyUserInfo).on("click", ".delete", deleteUser)
    $("#addUser").click(showAddUserModal);
    $("#insertCMSUser").click(insertCMSUser);
})
function deleteUser() {
    //获得用户id
    //发送请求
    if(!doConfirm()){
        return;
    }
    var id = this.parentNode.parentNode.firstChild.firstChild.value;
    var url = rootPath + "/deleteCmsUser.do";
    var params = {
        id: id
    }
    $.get(url, params, function (result) {
        getObject();
    })
}
function modifyUserInfo() {
    //点击修改按钮弹出modal框
    //获取用户id
    //根据用户id获得用户信息
}
function insertCMSUser() {
    var url = rootPath + "/insertCmsUser.do";
    var account = $("#cmsUserName").val();
    var password = $("#cmsUserPassword").val();
    var roleId = $(".chooseRole").val();
    var params = {
        name: account,
        password: password,
        roleId: roleId
    }
    $.get(url, params, function (result) {
        console.log(result);
        if (result.state == 1) {
            $("#myModal").modal("hide");
            getObject();
        } else {
            alert(result.message);
        }
    });
}
function showAddUserModal() {
    $("#myModal").modal("show");
    $("#myModalLabel").html("添加用户");
    var url = rootPath + "/user/html/addUser.html";
    $(".modal-body").load(url, null, function () {

    });
}
function getObject() {
    console.log(rootPath);
    var url = rootPath + "/findUsers.do";
    var params = {}
    $.post(url, params, function (result) {
        if (result.state == 1) {
            showUsers(result.data.pageInfo);
        } else {
            alert(result.message);
        }
    })
}
function showUsers(list) {
    var table = $("#userBody");
    table.empty();
    for (var i in list) {
        var tds = "<tr class='tr" + i % 2 + "'><td><input type='checkbox' value='" + list[i].userId + "'/></td>" +
            "<td class='imgtd'>" + list[i].name + "</td>" +
            "<td>" + list[i].roles[0].roleDescription + "</td>" +
            "<td>" + list[i].createTime + "</td>" +
            "<td><a class='modify'>修改</a>&nbsp;&nbsp;<a class='delete'>删除</a></td></tr>";
        table.append(tds);
    }
}