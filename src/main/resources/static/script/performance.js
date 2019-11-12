function loadperformance(){
//初始化echarts实例
var performance = echarts.init(document.getElementById('performance'));
//指定图标的配置和数据
var performanceoption = {
		 title: {
		        text: '设备性能图'
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['cpu使用率','内存使用率']
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
		        type: 'value',
		        axisLabel: {  
                    show: true,  
                    interval: 'auto',  
                    formatter: '{value} %'  
                    }, 
		    },
		    series: [{
	            name:'cpu使用率',
	            type:'line',
	            data:cpu
	        },
	        {
	            name:'内存使用率',
	            type:'line',
	            data:memory
	        }]
	};

//使用制定的配置项和数据显示图表
performance.setOption(performanceoption);
}