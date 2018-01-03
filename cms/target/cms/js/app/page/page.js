/**
 * Created by Administrator on 2017/11/6.
 */
/**
 * 初始化分页并展现首页数据
 * @param url
 */
function showPageObject(url) {
    var keyword = $(".tools").data("keyword");
    var state = $(".tablelist").data("state");
    var params = {
        keyword: keyword,
        currentPage: 1,
        state: state
    }
    console.log(params)
    $.post(url, params, function (result) {
        console.log(result)
        var setTotalCount = result.data.pageObject.amount;
        var allPage = result.data.pageObject.allPage;
        if (result.state == 1) {
            $('#box').paging({
                initPageNo: 1, // 初始页码
                totalPages: allPage, //总页数
                totalCount: '合计' + setTotalCount + '条数据', // 条目总数
                slideSpeed: 600, // 缓动速度。单位毫秒
                jump: true, //是否支持跳转
                callback: function (page) { // 回调函数
                    getObject(page);
                }
            })
        } else {
            alert(result.message);
        }
    })
}