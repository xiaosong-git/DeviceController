function loadnetwork(){
	console.log(freshtime);
//初始化echarts实例
var network = echarts.init(document.getElementById('network'));
//指定图标的配置和数据
var networkoption = {
	    title: {
	        text: '网络状态图'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['ping平均值','丢包率']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '10%',
	        containLabel: true
	    },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: freshtime
	    },
	    yAxis: {
	        type: 'value'
	    },
	    series: [{
            name:'ping平均值',
            type:'line',
            data:pingavg
        },
        {
            name:'丢包率',
            type:'line',
            data:pingloss
        }]
	};

//使用制定的配置项和数据显示图表
network.setOption(networkoption);
}