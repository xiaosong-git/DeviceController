var pingavg = [];
var pingloss=[];
var cpu = [];
var memory = [];
var freshtime = [];
$(function() {
	findDevice();
	});

	function clearform(){
	}

	function findDevice(){//查詢按鈕触发的事件
		var pars = {
				begTime : $("input[name='begtime']").val(),
				endTime : $("input[name='endtime']").val()
			};
		initTable(pars);
		$('#tableShowData').datagrid('reload');
		//获取数据导入折线图中
		$.ajax({
		    type: "POST",
		    url:'/deviceDetail/oneDeviceListToPIC',
		    data: {
				"begTime" : $("input[name='begtime']").val(),
				"endTime" : $("input[name='endtime']").val()
			},
		    dataType: 'json',
		    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    success: function (data) {
		    	pingavg = [];
        		pingloss=[];
        		cpu = [];
        		memory = [];
        		freshtime = [];
		        if(data){
		        	var jsonobj = eval(data);
		        	for(var i=0;i<jsonobj.length; i++){
		        		pingavg.push(jsonobj[i].pingAvg);
		        		pingloss.push(jsonobj[i].pingLoss);
		        		cpu.push(jsonobj[i].cpu);
		        		memory.push(jsonobj[i].memory);
		        		freshtime.push(jsonobj[i].freshTime);
		        	}
		        }
		        loadnetwork();
		        loadperformance();
		        
		    }
		})
		}
	setInterval(findDevice, 5*60*1000);

	//实现DataGird控件的绑定操作
	function initTable(pars) {
		$('#selectDeviceTable').datagrid({ //定位到Table标签，Table标签的ID是tableShowData
			fitColumns : true,
			url : '/deviceDetail/oneDeviceList', //指向后台的Action来获取当前用户的信息的Json格式的数据
			title : '设备详情列表',
			iconCls: 'icon-save',
            height: 342,
            nowrap: true,
            loadMsg: '正在加载用户的信息...',
            autoRowHeight: false,
            striped: true,
            collapsible: true,
            pagination: true,
            rownumbers: true,//添加列数字
            //sortName: 'ID',    //根据某个字段给easyUI排序
            //sortOrder: 'asc',
            remoteSort: false,
            queryParams: pars,  //异步查询的参数
            pageList: [1, 10, 15, 20, 25,30],//分页的分组设置
            pageSize: 10,//每页的默认值大小
		    frozenColumns : [ [ {
				title : 'id',
				field : 'id',
				width : 40,
				hidden : true
			} ] ],
		    columns:[[{
			    field:'deviceName',
			    title:'设备名称',
			    width:120,
			    formatter:function(value){
				    if(value =='FACE'){
					    return '人脸识别仪('+value+')';
					}else if(value =='RELAY'){
						return '继电器('+value+')';
					}else if(value =='QRCODE'){
						return '二维码识别器('+value+')';
					}else{
						return '上位机('+value+')';
					}
			    }
			},{
				field:'deviceIp',
				title:'ip地址',
				width:120
			},{
				field:'pingAvg',
				title:'ping平均速度',
				width:90
			},{
				field:'pingLoss',
				title:'丢包率',
				width:90,
				formatter:function(value){
					return value+'%'
				}
			},{
				field:'cpu',
				title:'cpu使用率',
				width:90,
				formatter:function(value){
					return value+'%'
				}
			},{
				field:'memory',
				title:'内存使用率',
				width:90,
				formatter:function(value){
					return value+'%'
				}
			},{
				field:'pingStatus',
				title:'网络状态',
				width:90,
				styler: function(value, row, index){
				    if (value == "error") {
				        return 'background-color:red;';
				    }
				}
			},{
				field:'telnetStatus',
				title:'telnet状态',
				width:90,
				styler: function(value, row, index){
				    if (value == "error") {
				        return 'background-color:red;';
				    }
				}
			},{
				field:'freshTime',
				title:'刷新时间',
				width:90
			}]]    
		});
	}