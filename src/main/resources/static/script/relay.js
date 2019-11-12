var relay = document.getElementById("relay");
		var relayChart = echarts.init(relay);
		var app = {};
		option = null;
		option = {
			title : {
				text : '继电器状态饼图',
				subtext : '分布情况',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : [ '正常设备', '预警设备', '报警设备' ]
			},
			series : [ {
				name : '继电器状态分布图',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '50%' ],
				label: {
	                normal: {
	                    formatter: '{b} : (数量： {c}，比例： {d}%)'
	                }
	            },
				data : [],
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			} ]
		};
		
		$(function(){//加载options数据
			relayDevice();
		   });
		setInterval(relayDevice, 5*60*1000);//定时器
		
		function relayDevice(){
			var servicedata=[];
	         $.ajax({
	             type: "POST",
	             url:'/echarts/getEcharts',
	             data: {"deviceName": "RELAY"},
	             dataType: 'json',
	             contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	             success: function (data) {
	                 if(data){
	                	 servicedata = [{
	     					value : data.deviceNormal,
	     					name : '正常设备'
	     				}, {
	     					value : data.deviceEwarn,
	     					name : '预警设备'
	     				}, {
	     					value : data.deviceWarn,
	     					name : '报警设备'
	     				}];

	                 }
	                 relayChart.setOption({
	                     series: [{
	                         // 根据名字对应到相应的系列
	                         name: '设备异常分布',
	                         data: servicedata
	                     }]
	                 });
	             }
	         })
		}
		
		if (option && typeof option === "object") {
			relayChart.setOption(option, true);
		}
		
		  
	     function everyClick_relay(param,i,txt){    //程序这边的url需要传入
	     if(param.seriesIndex==0&&param.dataIndex==i){
	    	 var warn = '';
	    	 if(param.dataIndex==0){
	    		 warn = 'normal';
	    	 }else if(param.dataIndex==1){
	    		 warn = 'ewarn';
	    	 }else{
	    		 warn = 'warn';
	    	 }
	    	 self.parent.addTab('设备管理','/deviceDetail?warn='+warn+'&deviceName=RELAY');
	         //confirm("确定打开新链接？"+txt)&&window.open (url,'_parent','height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	        }
	     }
		//增加监听事件
		  function eConsole_relay(param) {
			  //var mes = '【' + param.type + '】';
		        if (typeof param.seriesIndex != 'undefined') {
		       // mes += '  seriesIndex : ' + param.seriesIndex;
		       // mes += '  dataIndex : ' + param.dataIndex;
		    if (param.type == 'click') {    
		   var peiLenght= option.legend.data.length;
		    // alert(peiLenght);// 获取总共给分隔的扇形数
		    for(var i=0;i<peiLenght;i++){
		     everyClick_relay(param,i,option.legend.data[i])
		     } 
		    }else{
		       
		        document.getElementById('hover-console').innerHTML = 'Event Console : ' + param.dataIndex;
		     //alert();
		     }
		           
		    }
		     }
		   relayChart.on("click", eConsole_relay);
		   relayChart.on("hover", eConsole_relay);