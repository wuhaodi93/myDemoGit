/**
 * Created by Administrator on 2017/11/3.
 */
$(document).ready(function () {
    console.log($("#myModal").data("target")+$("#myModal").data("id"));
    getMerchantInfo();
});
function getMerchantInfo(){
    var url=getRootPath()+"/findMerchantEnterApplyInfo.do";
    var params={
        applyInfoId:$("#myModal").data("id")
    };
    $.get(url,params,function(result){
        var merchant =  result.data.pageInfo[0];
        console.log(merchant);
        $(".title").html(merchant.title);
        $(".content").html(merchant.content);
        $(".merchantImg").attr("src",merchant.img);
        $(".companyName").html(merchant.companyName);
        $(".enterType").html(merchant.applyType);
        $(".enterCity").html(merchant.cityName);
        $(".enterTime").html(merchant.createDate);
        $(".merchantAccount").html(merchant.userName);
        $(".merchantPhone").html(merchant.phone);

    });
}