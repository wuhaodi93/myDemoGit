/**
 * Created by Administrator on 2017/11/10.
 */
$(document).ready(function () {
    $("#moblink-href")[0].click();
    console.log(isWeiXin());
    if(!isWeiXin()){
        $(".zhezhao").css("display","none");
    }
});
function isWeiXin() {
    var ua = window.navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        return true;
    } else {
        return false;
    }
}