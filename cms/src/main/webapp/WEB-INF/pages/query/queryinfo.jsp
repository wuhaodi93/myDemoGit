<!DOCTYPE html>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8" %>
    <script src="js/jquery/jquery-3.2.1.min.js"></script>
    <script src="js/select-ui.min.js"></script>
    <script src="js/jquery/jquery.idTabs.min.js"></script>
    <script src="bootStrap/js/bootstrap.min.js"></script>
    <script src="js/common/common.js"></script>
    <script src="query/queryinfo.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="0">
    <style>
        body {
            background-color: transparent;;
        }

        .usual1 li {
            float: left;
            margin: 0px 10px 10px 10px;
            border: 0px solid black;
            list-style-type: none;
            list-style-position: inherit;
            padding: 10px 35px 10px 35px;
            background-color: #eeeeee;
        }

        .usual1 {
            margin-top: 5px;
        }

        .usual1 li a {
            text-decoration: none;
            color: black;
            font-weight: bold;
        }

        .sonTab {
            clear: both;
        }

        .line_01 {
            height: 1px;
            border-top: 1.5px solid #c4c4c4;
            text-align: center;
            margin: 5px 50px 0 50px;
        }

        .head1 h2 {
            margin: 10px 0px 5px 50px;
            text-align: center;
        }

        .head2 h4, .headRight h4 {
            margin: 25px 0px 10px 50px;
        }

        .sonTab {
            margin-top: 80px;
        }

        .table {
            margin-left: 50px;
            margin-top: 10px;
        }

        table {
            border-collapse: collapse;
            background-color: #f9f9f9;
            border-color: #c4c4c4;
        }

        .key, .value {
            font-size: large;
            padding: 7px;
        }

        .value {
            font-weight: bold;
        }

        .headRight td {
            text-align: center;
        }

        th {
            padding: 7px;
            background-color: #eeeeee;
        }

        .key {
            background-color: #eeeeee;
        }

        td img {
            width: 30px;
            height: 30px;
        }

        .blanklistDetailBody td {
            font-size: large;
            text-align: center;
            height: 38px;
        }

        .div2 {
            margin: 250px 550px;
        }

        .userSearchedHistory {
            margin-right: 50px;
        }
        .debitAndCredit{
            margin-right: 330px;
        }
        caption, .blanklist_detail {
            text-align: left;
            font-weight: bold;
            margin-bottom: 12px !important;
            margin-top: 15px !important;
        }
        .important{
            color: red;
        }
    </style>
    <script type="text/javascript">
        var pathName = getRootPath();
        $(function () {
            getData();
            var result = "";
        })
        function getData() {
            var name = "<%=session.getAttribute("name") %>";
            var phone = "<%=session.getAttribute("phone") %>";
            var idCard = "<%=session.getAttribute("idCard") %>";
            var qq = "<%=session.getAttribute("qq")%>";
            var email = "<%=session.getAttribute("email")%>"
            //发送请求
            var id = "<%=session.getAttribute("id")%>"
            var state =
                    <%=session.getAttribute("state")%>
                    console.log("state" + state);
            var url = pathName + "/" + (state ? "readObjectFileById.do" : "queryObjectInfo.do")
            console.log(url);
            var params = {
                name: name,
                phone: phone,
                cardNo: idCard,
                query_qq: qq,
                query_email: email,
                id: id,
                state: state
            };
            console.log(params);
            $.get(url, params, function (result) {
                if (result.state == 1) {
                    showData(result);
                    $(".div1").css("display", "block");
                    $(".div2").css("display", "none");
                } else {
                    alert(result.message);
                }
            })
        }
        function showData(result) {
            var data = result.data;
            var honey = data.honey;
            if (!honey.result.success) {
                alert(honey.result.message);
                return;
            }
            var honeyData = honey.result.data;
            var p2p = data.p2p;
            var debitAndCredit = data.debitAndCredit;
            var user_gray = honeyData.user_gray;
            var user_phone_suspicion = honeyData.user_phone_suspicion;
            var user_idcard_suspicion = honeyData.user_idcard_suspicion;
            var user_searched_history_by_orgs = honeyData.user_searched_history_by_orgs;
            var user_blacklist = honeyData.user_blacklist;
            var user_register_orgs = honeyData.user_register_orgs;
            getUserBasicData(data);
            getUserGrayData(user_gray);
            getPhoneSuspicionData(user_phone_suspicion);
            getIdCardSuspicionData(user_idcard_suspicion);
            getUserSearchedHistory(user_searched_history_by_orgs);
            getUserBlanklistData(user_blacklist);
            getUserRegistData(user_register_orgs);
            getP2PData(p2p);
            getDebitAndCreditData(debitAndCredit);
        }

        function getDebitAndCreditData(debitAndCredit) {
            var resp_data = debitAndCredit.result.resp_data;
            var resp_data_body;
            var debitAndCredit_meg;
            var debitAndCredit_Code;
            if (resp_data.hasOwnProperty("body")) {
                resp_data_body = resp_data.body;
            }
            if (resp_data.hasOwnProperty("msg")) {
                debitAndCredit_meg = resp_data.msg;
                debitAndCreditCode = resp_data.code;
            }
            if (debitAndCreditCode != 1000) {
                if ("1001" == debitAndCredit_Code) {
                    debitAndCredit_meg = "请求参数为空";
                }
                if ("1010" == debitAndCreditCode) {
                    debitAndCredit_meg = "姓名输入错误";
                }
                if ("1011" == debitAndCreditCode) {
                    debitAndCredit_meg = "身份证输入错误";
                }
                if ("1029" == debitAndCreditCode) {
                    debitAndCredit_meg = "请求数据失败,有可能是身份证不正确";
                }
                if ("1030" == debitAndCreditCode) {
                    debitAndCredit_meg = "账户余额不足";
                }
                $(".debitAndCredit .noData").html("<p style='margin-bottom: 80px'>" + debitAndCredit_meg + "</p>");
                $(".debitAndCredit tr").css("display", "none");
                return;
            }
            var amount = resp_data_body.amount;
            var busiDate = resp_data_body.busi_date;
            var industry = resp_data_body.industry;
            $(".debitAndCredit .amount").html(amount);
            $(".debitAndCredit .busiDate").html(busiDate);
            if (industry == "BAK") {
                industry += "(银行)";
            } else if (industry == "MCL") {
                industry += "(小贷)";
            } else if (industry == "ASM") {
                industry += "(资产管理)";
            } else if (industry == "TRU") {
                industry += "(信托)";
            } else if (industry == "LEA") {
                industry += "(租赁)";
            } else if (industry == "CRF") {
                industry += "(众筹)";
            } else if (industry == "INV") {
                industry += "(投资)";
            } else if (industry == "CNS") {
                industry += "(消费金融)";
            } else if (industry == "INS") {
                industry += "(保险)";
            } else if (industry == "THR") {
                industry += "(第三方)";
            } else if (industry == "OTH") {
                industry += "(其他)";
            } else if (industry == "0") {
                industry += "(无权限)";
            }
            $(".debitAndCredit .industry").html(industry);
        }
        ;

        function getP2PData(p2p) {
            var p2p_query_time = p2p.result.result.query_time;
            var business_res_detail = p2p.result.result.query_reports[0].business_res_detail[0];
            var webloan_hit_count = business_res_detail.webloan_hit_count;
            var webloan_hit_pname = business_res_detail.webloan_hit_pname;
            $(".webloan_hit_name").html(webloan_hit_pname);
            $(".webloan_hit_count").html(webloan_hit_count);
            $(".webloan_update").html(getLocalTime(p2p_query_time.toString().substr(0, 10)));
        }

        function getUserRegistData(user_register_orgs) {
            var user_register_phone = user_register_orgs.phone_num;
            $(".registPhone").html(user_register_phone);
            var register_cnt = user_register_orgs.register_cnt;
            $(".registAmount").html(register_cnt);
            var register_orgs_statistics = user_register_orgs.register_orgs_statistics;
            var register_orgs_statistics_length = register_orgs_statistics.length;
            if (register_orgs_statistics.length == 0) {
                $(".registDetail .table").html("该用户没有注册相关应用");
                return;
            } else {
                $(".registType").attr("rowspan", register_orgs_statistics_length);
                $(".registTypes .typeValue").html(register_orgs_statistics[0].label);
                $(".registTypes .registAccount").html(register_orgs_statistics[0].count);
                for (i = 1; i < register_orgs_statistics_length; i++) {
                    var tr = "<tr>" +
                            "<td class='value'>" + register_orgs_statistics[i].label + "</td>" +
                            "<td class='key'>数量:</td>" +
                            "<td class='value'>" + register_orgs_statistics[i].count + "</td>" +
                            "</tr>";
                    $(".registTypes").after(tr)
                }
            }
        }

        function getUserBlanklistData(user_blacklist) {
            var blacklist_category = user_blacklist.blacklist_category;
            var blacklist_details = user_blacklist.blacklist_details;
            var blacklist_name_with_idcard = user_blacklist.blacklist_name_with_idcard;
            var blacklist_name_with_phone = user_blacklist.blacklist_name_with_phone;
            var blacklist_update_time_name_idcard = user_blacklist.blacklist_update_time_name_idcard;
            var blacklist_update_time_name_phone = user_blacklist.blacklist_update_time_name_phone;
            if (!blacklist_name_with_idcard && !blacklist_name_with_idcard) {
                $(".userBlanklistDetail .table").html("<p>该用户没有在黑名单中</p>");
                return;
            }
            if (blacklist_name_with_idcard) {
                $(".nameAndCardNoState").css("color", "red");
            }
            if (blacklist_name_with_phone) {
                $(".nameAndPhoneState").css("color", "red")
            }
            $(".BlanklistState .nameAndCardNoState").html(blacklist_name_with_idcard ? "是" : "否");
            $(".BlanklistState .ncUpdateTime").html(blacklist_update_time_name_idcard || "无");
            $(".BlanklistState .nameAndPhoneState").html(blacklist_name_with_phone ? "是" : "否");
            $(".BlanklistState .npUpdateTime").html(blacklist_update_time_name_phone || "无");
            var blacklist_category_result = "";
            for (var i = 0; i < blacklist_category.length; i++) {
                if (i != 0) {
                    blacklist_category[i] = "、" + blacklist_category[i];
                }
                blacklist_category_result += blacklist_category[i];
            }
            $(".blacklistCategory").html(blacklist_category_result);
            //黑名单详细
            var blanklistDetailHead = $(".blanklistDetailHead");
            blanklistDetailHead.empty();
            var blanklistDetailBody = $(".blanklistDetailBody");
            blanklistDetailBody.empty();
            if (blacklist_details.length > 0) {
                $(".blanklistCaption").html("<p class='blanklist_detail'>详细记录:</p>");
            }
            for (var i = 0; i < blacklist_details.length; i++) {
                var details_key = blacklist_details[i].details_key;
                var details_value = blacklist_details[i].details_value
                if (details_key == "fraud_type") {
                    details_key = "欺诈类型代号";
                } else if (details_key == "company_name") {
                    details_key = "公司名称";
                } else if (details_key == "company_address") {
                    details_key = "公司地址";
                } else if (details_key == "confirmation_date") {
                    details_key = "欺诈时间";
                }
                details_key = "<th>" + details_key + "</th>";
                details_value = "<td>" + details_value + "</td>";
                blanklistDetailHead.append(details_key);
                blanklistDetailBody.append(details_value);
            }
        }

        function getUserGrayData(user_gray) {
            var user_gray_phone = user_gray.user_phone;
            $(".grayPhone").html(user_gray_phone);
            var contacts_class1_blacklist_cnt = user_gray.contacts_class1_blacklist_cnt;
            $(".contacts_class1_blacklist_cnt").html(contacts_class1_blacklist_cnt)
            var contacts_class2_blacklist_cnt = user_gray.contacts_class2_blacklist_cnt;
            $(".contacts_class2_blacklist_cnt").html(contacts_class2_blacklist_cnt);
            var phone_gray_score = user_gray.phone_gray_score;
            $(".grayPoint").html(phone_gray_score);
            var contacts_class1_cnt = user_gray.contacts_class1_cnt;
            $(".contacts_class1_cnt").html(contacts_class1_cnt);
            var contacts_router_cnt = user_gray.contacts_router_cnt;
            $(".contacts_router_cnt").html(contacts_router_cnt);
            var contacts_router_ratio = user_gray.contacts_router_ratio;
            $(".contacts_router_ratio").html(contacts_router_ratio);
            if (!contacts_router_ratio) {
                $(".userGrayData .noData").html("<p>该用户灰度信息良好!</p>");
                $(".userGrayData tr").css("display", "none");
                return;
            }
        }

        function getUserBasicData(data) {
            console.log(data);
            var auth = data.auth;
            var debitAndCredit = data.debitAndCredit;
            var honey = data.honey;
            var railWay = data.railWay;
            if(auth.result.error_code){
                $(".isok").html(auth.result.reason);
            }else{
                var isok = auth.result.result.isok;
                if(!isok){
                    auth.css("color","red");
                }
                $(".isok").html(isok ? "是" : "否");
            }
            var railWayResult = railWay.result.result;
            if (!railWayResult) {
                $(".railWay").css("color", "red");
            }
            $(".railWay").html(!railWayResult ? "是" : "否");
            var user_basic = honey.result.data.user_basic;
            var user_age = user_basic.user_age;
            $(".age").html(user_age);
            var user_city = user_basic.user_city;
            $(".city").html(user_city);
            var user_gendar = user_basic.user_gender;
            $(".gendar").html(user_gendar);
            var user_idcard = user_basic.user_idcard;
            $(".idCard").html(user_idcard);
            var user_phone = user_basic.user_phone;
            $(".phone").html(user_phone);
            var user_idcard_valid = user_basic.user_idcard_valid;
            if (!user_idcard_valid) {
                $(".idCardValid").css("color", "red");
            }
            $(".idCardValid").html(user_idcard_valid ? "是" : "否")
            var user_name = user_basic.user_name;
            $(".name").html(user_name);
            var user_phone_city = user_basic.user_phone_city;
            $(".phoneCity").html(user_phone_city);
            var user_phone_province = user_basic.user_phone_province;
            var user_province = user_basic.user_province;
            $(".province").html(user_province);
            var user_region = user_basic.user_region;
            $(".region").html(user_region);
            var user_phone_operator = user_basic.user_phone_operator;
            $(".phoneOperator").html(user_phone_operator);
            var updateTime = honey.result.data.update_time;
            $(".basicInfoUpdateTime").html(getLocalTime(updateTime.toString().substr(0, 10)));
        }

        function getLocalTime(nS) {
            return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
        }

        function getPhoneSuspicionData(user_phone_suspicion) {
            //获取用户手机号绑定与使用信息数据
            var phone_with_other_idCards = user_phone_suspicion.phone_with_other_idcards;
            var phone_with_other_idCards_length = phone_with_other_idCards.length;
            if (phone_with_other_idCards_length == 0) {
                $(".phoneWithIdCards .cardValue").html("无");
                $(".phoneWithIdCards .bindingTime").html("无");
            } else {
                $(".phoneWithIdCard").attr("rowspan", phone_with_other_idCards_length);
                $(".phoneWithIdCards .cardValue").html(phone_with_other_idCards[0].susp_idcard).css("color","red");
                $(".phoneWithIdCards .bindingTime").html(phone_with_other_idCards[0].susp_updt);
            }
            for (i = 1; i < phone_with_other_idCards_length; i++) {
                var tr = "<tr>" +
                        "<td class='value'>" + phone_with_other_idCards[i].susp_idcard + "</td>" +
                        "<td class='key'>绑定的时间:</td>" +
                        "<td class='value'>" + phone_with_other_idCards[i].susp_updt + "</td>" +
                        "</tr>";
                $(".phoneWithIdCards").after(tr)
            }

            var phone_with_other_names = user_phone_suspicion.phone_with_other_names;
            var phone_with_other_names_length = phone_with_other_names.length;
            if (phone_with_other_names_length == 0) {
                $(".phoneWithNames .nameValue").html("无");
                $(".phoneWithNames .bindingTime").html("无");
            } else {
                $(".phoneWithName").attr("rowspan", phone_with_other_names_length);
                $(".phoneWithNames .nameValue").html(phone_with_other_names[0].susp_name).css("color","red");
                $(".phoneWithNames .bindingTime").html(phone_with_other_names[0].susp_updt);
            }
            for (i = 1; i < phone_with_other_names_length; i++) {
                var tr = "<tr>" +
                        "<td class='value'>" + phone_with_other_names[i].susp_name + "</td>" +
                        "<td class='key'>绑定的时间:</td>" +
                        "<td class='value'>" + phone_with_other_names[i].susp_updt + "</td>" +
                        "</tr>";
                $(".phoneWithNames").after(tr)
            }

            var phone_applied_in_orgs = user_phone_suspicion.phone_applied_in_orgs;
            var phone_applied_in_orgs_length = phone_applied_in_orgs.length;
            if(phone_applied_in_orgs_length==0){
                $(".phoneWithOrgs .orgValue").html("无");
                $(".phoneWithOrgs .bindingTime").html("无");
            }else{
                $(".phoneWithOrg").attr("rowspan", phone_applied_in_orgs_length);
                $(".phoneWithOrgs .orgValue").html(phone_applied_in_orgs[0].susp_org_type);
                $(".phoneWithOrgs .bindingTime").html(phone_applied_in_orgs[0].susp_updt);
            }
            for (i = 1; i < phone_applied_in_orgs_length; i++) {
                var tr = "<tr>" +
                        "<td class='value'>" + phone_applied_in_orgs[i].susp_org_type + "</td>" +
                        "<td class='key'>使用时间:</td>" +
                        "<td class='value'>" + phone_applied_in_orgs[i].susp_updt + "</td>" +
                        "</tr>";
                $(".phoneWithOrgs").after(tr)
            }
        }

        function getIdCardSuspicionData(user_idcard_suspicion) {
            var idcard_applied_in_orgs = user_idcard_suspicion.idcard_applied_in_orgs;
            var idcard_applied_in_orgs_length = idcard_applied_in_orgs.length;
            if(idcard_applied_in_orgs_length==0){
                $(".idCardWithOrg").attr("rowspan", "无");
                $(".idCardWithOrgs .orgValue").html("无");
            }else{
                $(".idCardWithOrg").attr("rowspan", idcard_applied_in_orgs_length);
                $(".idCardWithOrgs .orgValue").html(idcard_applied_in_orgs[0].susp_org_type);
                $(".idCardWithOrgs .bingdingTime").html(idcard_applied_in_orgs[0].susp_updt);
            }
            for (i = 1; i < idcard_applied_in_orgs_length; i++) {
                var tr = "<tr>" +
                        "<td class='value'>" + idcard_applied_in_orgs[i].susp_org_type + "</td>" +
                        "<td class='key'>绑定的时间:</td>" +
                        "<td class='value'>" + idcard_applied_in_orgs[i].susp_updt + "</td>" +
                        "<td class='value'></td>" +
                        "<td class='value'></td>" +
                        "<td class='value'></td>" +
                        "<td class='value'></td>" +
                        "</tr>";
                $(".idCardWithNames").after(tr);
            }
            var idcard_with_other_names = user_idcard_suspicion.idcard_with_other_names;
            var idcard_with_other_names_length = idcard_with_other_names.length;
            if (idcard_with_other_names_length == 0) {
                $(".idCardWithOtherNames .nameValue").html("无");
                $(".idCardWithOtherNames .bingdingTime").html("无");
            } else {
                $(".idCardWithOtherName").attr("rowspan", idcard_with_other_names_length);
                $(".idCardWithOtherNames .nameValue").html(idcard_with_other_names[0].susp_name).css("color","red");
                $(".idCardWithOtherNames .bingdingTime").html(idcard_with_other_names[0].susp_updt);
            }
            for (i = 1; i < idcard_with_other_names_length; i++) {
                var tr = "<tr>" +
                        "<td class='value'>" + idcard_with_other_names[i].susp_name + "</td>" +
                        "<td class='key'>绑定的时间:</td>" +
                        "<td class='value'>" + idcard_with_other_names[i].susp_updt + "</td>" +
                        "<td class='value'></td>" +
                        "<td class='value'></td>" +
                        "<td class='value'></td>" +
                        "<td class='value'></td>" +
                        "</tr>";
                $(".idCardWithOtherNames").after(tr);
            }
            var idcard_with_other_phones = user_idcard_suspicion.idcard_with_other_phones;

            idcard_with_other_phones_length = idcard_with_other_phones.length;
            if (idcard_with_other_phones_length == 0) {
                $(".idCardWithOtherPhones .phoneValue").html("无");
                $(".idCardWithOtherPhones .cityName").html("无");
                $(".idCardWithOtherPhones .phoneOperator").html("无");
                $(".idCardWithOtherPhones .bingdingTime").html("无");
            } else {
                $(".idCardWithOtherPhone").attr("rowspan", idcard_with_other_phones_length);
                $(".idCardWithOtherPhones .phoneValue").html(idcard_with_other_phones[0].susp_phone).css("color","red");
                $(".idCardWithOtherPhones .cityName").html(idcard_with_other_phones[0].susp_phone_city);
                $(".idCardWithOtherPhones .phoneOperator").html(idcard_with_other_phones[0].susp_phone_operator);
                $(".idCardWithOtherPhones .bingdingTime").html(idcard_with_other_phones[0].susp_updt);
            }
            for (i = 1; i < idcard_with_other_phones_length; i++) {
                var tr = "<tr>" +
                        "<td class='value'>" + idcard_with_other_phones[i].susp_phone + "</td>" +
                        "<td class='key'>手机号所属城市:</td>" +
                        "<td class='value'>" + idcard_with_other_phones[i].susp_phone_city + "</td>" +
                        "<td class='key'>手机号运营商:</td>" +
                        "<td class='value'>" + idcard_with_other_phones[i].susp_phone_operator + "</td>" +
                        "<td class='key'>绑定的时间:</td>" +
                        "<td class='value'>" + idcard_with_other_phones[i].susp_updt + "</td>" +
                        "</tr>";
                $(".idCardWithOtherPhones").after(tr);
            }
        }

        function getUserSearchedHistory(user_searched_history_by_orgs) {
            var table = $(".searchedHistory tbody");
            var hasSearchedRecord = false;
            for (var i in user_searched_history_by_orgs) {
                if (!user_searched_history_by_orgs[i].org_self) {
                    var tr = "<tr><td class='value'>" + user_searched_history_by_orgs[i].searched_org + "</td><td class='value'>" + user_searched_history_by_orgs[i].searched_date + "</td></tr>";
                    table.append(tr);
                    hasSearchedRecord = true;
                }
            }
            if (!hasSearchedRecord) {
                $(".userSearchedHistory .noData").html("<p>暂无数据!</p>");
                $(".userSearchedHistory thead").css("display", "none");
            }
        }

    </script>
<body>
<div class="div2"><img src="images/wait.gif"></div>
<div>
</div>
<div class="div1" style="display: none">
    <div class="head1">
        <h2>服务查询结果</h2>
    </div>
    <div class="usual1">
        <ul>
            <li><a href="#userBaseInfo">基本信息</a></li>
            <li><a href="#userGrayData">灰度数据</a></li>
            <li><a href="#userPhoneImpeach">手机号绑定与使用信息</a></li>
            <li><a href="#userIdCardImpeach">身份证绑定与使用信息</a></li>
            <li><a href="#userSearchedHistory">被查询历史</a></li>
            <li><a href="#userBlanklistDetail">黑名单</a></li>
            <li><a href="#userRegisterDetail">注册情况</a></li>
            <li><a href="#p2p">p2p数据</a></li>
            <li><a href="#debitAndCredit">央信多头借贷</a></li>
        </ul>

    </div>
    <div class="sonTab">
        <div id="tab1">
            <div class="head2 userBaseInfo">
                <div class="table">
                    <table width="75%" border="1" align="left">
                        <caption><a name="userBaseInfo">基本信息:</a></caption>
                        <tr>
                            <td class="key">姓名:</td>
                            <td class="value name"></td>
                            <td class="key">性别:</td>
                            <td class="value gendar"></td>
                            <td class="key ">年龄:</td>
                            <td class="value age"></td>
                        </tr>
                        <tr>
                            <td class="key">户籍所在省:</td>
                            <td class="value province"></td>
                            <td class="key">户籍所在城市:</td>
                            <td class="value city"></td>
                            <td class="key">户籍所在县:</td>
                            <td class="value region"></td>
                        </tr>
                        <tr>
                            <td class="key">手机号:</td>
                            <td class="value phone"></td>
                            <td class="key">手机号运营商:</td>
                            <td class="value phoneOperator"></td>
                            <td class="key">手机号所属城市:</td>
                            <td class="value phoneCity"></td>
                        </tr>
                        <tr>
                            <td class="key">身份证:</td>
                            <td class="value idCard"></td>
                            <td class="key">身份证是否可用:</td>
                            <td class="value idCardValid"></td>
                            <td class="key">信息更新时间:</td>
                            <td class="value basicInfoUpdateTime"></td>
                        </tr>
                        <tr>
                            <td class="key">三年内是否有铁路出行:</td>
                            <td class="value railWay"></td>
                            <td class="key">身份认证是否一致</td>
                            <td class="value isok"></td>
                            <td class="key"></td>
                            <td class="value"></td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="headRight userSearchedHistory">
                <div class="table">
                    <table width="20%" border="1" class="searchedHistory" align="right">
                        <caption><a name="userSearchedHistory">被查询的历史:</a><span
                                style="display: inline;font-weight: normal" class="noData"></span></caption>
                        <thead>
                        <th>机构名称</th>
                        <th>查询时间</th>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>

            <div class="head2 userPhoneImpeach">
                <div class="table">
                    <table width="75%" border="1">
                        <caption><a name="userPhoneImpeach">手机号绑定与使用信息:</a></caption>
                        <tr class="phoneWithIdCards">
                            <td class="key phoneWithIdCard">手机号绑定的其它身份证:</td>
                            <td class="value cardValue"></td>
                            <td class="key">绑定的时间:</td>
                            <td class="value bindingTime"></td>
                        </tr>
                        <tr class="phoneWithNames">
                            <td class="key phoneWithName">手机号绑定的其他姓名:</td>
                            <td class="value nameValue"></td>
                            <td class="key">绑定的时间:</td>
                            <td class="value bindingTime"></td>
                        </tr>
                        <tr class="phoneWithOrgs">
                            <td class="key phoneWithOrg">手机号使用过的机构:</td>
                            <td class="value orgValue"></td>
                            <td class="key">使用时间:</td>
                            <td class="value bindingTime"></td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="head2 userIdCardImpeach">
                <div class="table">
                    <table width="75%" border="1">
                        <caption><a name="userIdCardImpeach">身份证绑定与使用信息:</a></caption>
                        <tr class="idCardWithOtherPhones">
                            <td class="key idCardWithOtherPhone">身份证绑定其它手机号:</td>
                            <td class="value phoneValue"></td>
                            <td class="key">手机号所属城市:</td>
                            <td class="value cityName"></td>
                            <td class="key">手机号运营商:</td>
                            <td class="value phoneOperator"></td>
                            <td class="key">绑定的时间:</td>
                            <td class="value bingdingTime"></td>
                        </tr>
                        <tr class="idCardWithOtherNames">
                            <td class="key idCardWithOtherName">身份证绑定其他姓名:</td>
                            <td class="value nameValue"></td>
                            <td class="key">绑定的时间:</td>
                            <td class="value bingdingTime"></td>
                            <td class="key"></td>
                            <td class="value"></td>
                            <td class="key"></td>
                            <td class="value"></td>
                        </tr>
                        <tr class="idCardWithOrgs">
                            <td class="key idCardWithOrg">身份证使用过的机构:</td>
                            <td class="value orgValue"></td>
                            <td class="key">使用时间:</td>
                            <td class="value bingdingTime" id="useTime"></td>
                            <td class="key"></td>
                            <td class="value"></td>
                            <td class="key"></td>
                            <td class="value"></td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="head2 userBlanklistDetail">
                <h4><a name="userBlanklistDetail">黑名单:</a></h4>
                <div class="table">
                    <table width="75%" border="1">
                        <tr class="BlanklistState">
                            <td class="key">姓名和身份证是否在黑名单:</td>
                            <td class="value nameAndCardNoState"></td>
                            <td class="key">更新时间:</td>
                            <td class="value ncUpdateTime"></td>
                        </tr>
                        <tr class="BlanklistState">
                            <td class="key">姓名和手机号是否在黑名单:</td>
                            <td class="value nameAndPhoneState"></td>
                            <td class="key">更新时间:</td>
                            <td class="value npUpdateTime"></td>
                        </tr>
                        <tr>
                            <td class="key">黑名单类型:</td>
                            <td class="value blacklistCategory important"></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                    <table width="75%" border="1" style="margin-top: 5px">
                        <thead class="blanklistDetailHead">
                        </thead>
                        <tbody class="blanklistDetailBody">
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="head2 registDetail">
                <h4><a name="userRegisterDetail">注册情况:</a></h4>
                <div class="table">
                    <table width="75%" border="1" align="left">
                        <tr>
                            <td class="key">手机号:</td>
                            <td class="value registPhone"></td>
                            <td class="key">注册数量:</td>
                            <td class="value registAmount"></td>
                        </tr>
                        <tr class="registTypes">
                            <td class="key registType" rowspan="3">类型:</td>
                            <td class="value typeValue"></td>
                            <td class="key">数量:</td>
                            <td class="value registAccount"></td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="head2 userGrayData">
                <div class="table">
                    <table width="75%" border="1" align="left">
                        <caption><a name="userGrayData">灰度数据:</a><span style="display: inline;font-weight: normal"
                                                                       class="noData"></span></caption>
                        <tr>
                            <td class="key">相关手机号:</td>
                            <td class="value grayPhone"></td>
                            <td class="key">用户灰度值:</td>
                            <td class="value grayPoint important"></td>
                            <td class="key"></td>
                            <td class="value"></td>
                        </tr>
                        <tr>
                            <td class="key">直接联系人黑名单数量:</td>
                            <td class="value contacts_class1_blacklist_cnt important"></td>
                            <td class="key">间接联系人黑名单数量:</td>
                            <td class="value contacts_class2_blacklist_cnt important"></td>
                            <td class="key"></td>
                            <td class="value"></td>
                        </tr>
                        <tr>
                            <td class="key">一阶联系人总数:</td>
                            <td class="value contacts_class1_cnt"></td>
                            <td class="key">引起的二阶黑名单数量</td>
                            <td class="value contacts_router_cnt important"></td>
                            <td class="key">引起占比</td>
                            <td class="value contacts_router_ratio important"></td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="p2p head2">
                <div class="table">
                    <table width="42%" border="1" align="left">
                        <caption><a name="p2p">p2p数据:</a></caption>
                        <tr>
                            <td class="key">用户关注的p2p平台数量</td>
                            <td class="value webloan_hit_count important"></td>
                        </tr>
                        <tr>
                            <td class="key">用户关注的p2p平台名称</td>
                            <td class="value webloan_hit_name important"></td>
                        </tr>
                        <tr>
                            <td class="key">更新时间</td>
                            <td class="value webloan_update"></td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="debitAndCredit head2">
                <div class="table">
                    <table width="38%" border="1" align="right">
                        <caption><a name="debitAndCredit">央信多头借贷:<span style="display: inline;font-weight: normal"
                                                                       class="noData"></span></a></caption>
                        <tr>
                            <td class="key">命中机构数目:</td>
                            <td class="value amount important"></td>
                        </tr>
                        <tr>
                            <td class="key">机构所属行业:</td>
                            <td class="value industry important"></td>
                        </tr>
                        <tr>
                            <td class="key">发生时间:</td>
                            <td class="value busiDate"></td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>