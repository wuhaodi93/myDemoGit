/**
 * Created by Administrator on 2017/11/14.
 */
var pathName = getRootPath();
$(document).ready(function () {
    $(".loadChooseList").load(pathName+"/app/infoManage/chooselist.html");
    $(".chart-container").load(pathName+"/app/infoManage/chart.html")
})
function chooseCommonDataType(optionValue) {
    var urlLine="";
    var urlPie="";
    var chartId="showCommonData";
    if(optionValue==3){
        urlLine=pathName+"/findMerchantEnterDataInMonth.do";
    }else if(optionValue==4){
        urlLine=pathName+"/findAuthDataInMonth.do";
    }else if(optionValue==5){
        urlLine=pathName+"/findUserRegisterDataInMonth.do";
    }
    getLocalData(urlLine,chartId);
}

function getLocalData(url,chartId){
    $.get(url,null,function (result) {
        var categories=result.data.categories;
        var series=result.data.series;
        showMyLineChart(categories,series,chartId);
    });
}

function showMyLineChart(categories,series,chartId){
    var ctx = document.getElementById(chartId).getContext("2d");
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: categories,
            datasets:[]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
    myChart.update();
    var datesets=new Array();
    var colors=new Array();
    colors[0]="#4169E1";
    colors[1]="#0A0A0A";
    colors[2]="#9400D3";
    colors[3]="#FF00FF";
    colors[4]="#00FF00";
    colors[5]="#473C8B";
    colors[6]="#8B0000";
    colors[7]="#87CEFF";
    colors[8]="#CD6600";
    for(var i=0;i<series.length;i++){
        var dataset={
            label: null,
            data: null,//数据
            borderColor: null,//线的颜色蓝色
            backgroundColor: "#FFFFFF",//线与x轴交叉的颜色
            borderWidth: 2.5,//线条的宽度
            pointBackgroundColor: null,//点的填充颜色
            pointBorderColor: null,//点的边框颜色
            pointHoverBackgroundColor: null,//点悬浮时的颜色
            pointHoverRadius: 5,//悬停点的半径。
            showLine: true,//如果为false，则不为此数据集绘制该行。
            spanGaps: false,//如果为true，则将在无数据或零数据的点之间绘制行。如果是false，NaN数据点会在行中创建一个中断
            fill: false,//Boolean/String	如何填写下面的区域
            pointHitRadius: 5,//反映鼠标事件的非显示点的像素大小。
            steppedLine: false//如果线显示为阶梯线。
        }
        dataset.data=series[i].data;
        dataset.label=series[i].name;
        dataset.pointHoverBackgroundColor=colors[i];
        dataset.borderColor=colors[i];
        dataset.pointBackgroundColor=colors[i];
        dataset.pointBorderColor=colors[i];
        datesets[i]=dataset;
    }
    myChart.data.datasets=datesets;
    myChart.update();
}