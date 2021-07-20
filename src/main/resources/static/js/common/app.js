/**
 * 
 */
var App=function(undefined){
	
	var characterArray = ["色情","法轮功","藏独立","打倒共产党","打倒共产主义","打倒胡锦涛","打倒江泽民","打倒江主席",
	                      "打倒温家宝","打倒中共","大法弟子","大法轮","大纪元","大游行","当代八乱","电话拦截器","电话窃听",
	                      "发票代开","多党执政","发票代开","法轮大法","法轮功","反革命","反共","反华","反日","共产党专制",
	                      "共奴","国际声援西藏","红楼遗秘","胡江内斗","拉登=LA登","六四事件","毛主席复活","灭绝罪","民运分子",
	                      "人类灭亡时间表","推翻共产党","西藏独立","西藏国","西藏基金会","西藏正义","新疆独立","修炼大法",
	                      "支持西藏独立","中共专制","中共走狗","自杀手册","自由西藏","办理各类文凭证件","出售冰毒","出售冰毒配方",
	                      "出售车牌隐形喷剂","冰毒配方","出售短信群发器","出售仿真枪","出售高考答案","出售假币","出售假币假钞",
	                      "出售假人品币","出售军用手枪","出售喷雾型迷药","出售手枪","出售套牌车","传奇私服","大麻","代办国外文凭",
	                      "代开发票","代考","网上办证刻章","A片","销售枪支弹药","军用手枪","性福","bt动漫","白粉","SEX情色论坛","性交",
	                      "包办色情娱乐服务","成人电影","成人动画","成人激情小说","成人论坛","成人漫画","成人片","成人情色","成人网站导航",
	                      "城市激情聊天室","催情粉","催情药","黄色小电影","火药制作","激情片下载","激情视频","激情图片","激情一刻","监听王",
	                      "疆独立","警用器材","考试作弊器","猎枪销售","露穴","裸聊","裸照","蒙汗药","迷幻药","迷魂药","迷奸","迷药","明星裸体照",
	                      "中国成人网","反对共产党","打到共产党"];
	
	function noDialogValidateChar(text){
		if(text){
			var param = decodeURIComponent(text);
			 for (var i = 0; i < characterArray.length; i++) {
				if(param.indexOf(characterArray[i])!==-1){
					return false;
				}
			}
		 }
		return true;
	}
	
	function validateChar(text){
		if(text){
			var param = decodeURIComponent(text);
			 for (var i = 0; i < characterArray.length; i++) {
				if(param.indexOf(characterArray[i])!==-1){
					new MyDialog().showMessage({
						id:"no",
						icon : "warning",
						content : '<strong>内容中含有非法字符:'+characterArray[i]+'</strong>'
					});
					return false;
				}
			}
		 }
		return true;
	}
	
	//显示操作结果
	function showResult(data,fn){
		if(typeof(data)==="string"){
		    eval("data="+data);
		}
		if(data.result=="success"){
			showMessage("succeed",data.msg,fn);
		}else if(data.result=="warning"){
			showMessage("warning",data.msg);
		}else {
			showMessage("error",data.msg);
		}
	}
	
	//显示提示
	function showMessage(type,message,fn){
		let appDialog = new MyDialog().showMessage({
			icon : type,
			content : '<strong>' + message + '</strong>',
			cancel:fn
		});
		
		//设置定时器
		setTimeout(function () {
			$("#aui_close").click()
		}, 1000);
	}
	
	//显示询问
	function showConfirm(type,message,fn){
		new MyDialog().showMessage({
			icon : type,
			content : '<strong>' + message + '</strong>',
			ok:fn,
			cancelVal : "取消"
		});
	}
	
	//删除
	function tableDelete(url,$form,fn){
		var count=$form.find('td input[type="checkbox"]:checked').length;
		if(count<1){
			showMessage("warning","请至少选择一条记录");
		}else{
			showConfirm("question","确定要删除"+count+"条记录吗？",function(){
				submit(url,$form,function(data){
					showResult(data,fn);
				});
			});
		}
		
	}
	
	//显示一个对话框
	function showDialog(s){
		var md=new MyDialog();
		md.showDialog(s);
        return md;
	}
	
	function showAjaxDialog(s){
		var url=s.url;
		var param=s.data||{};
		var md=new MyDialog();
		md.showDialog(s);
		$.post(url,param,function(data){
			//s.content=data;
			md.initHeight().content(data).showAnimate();
			//md.content(data);
		});
		
		return md;
	}
	
	return{
		"showDialog":function(s){
			return showDialog(s);
		},
		"showAjaxDialog":function(s){
			return showAjaxDialog(s);
		},
		"showConfirm":function(type,message,fn){
			showConfirm(type,message,fn);
		},
		"showMessage":function(type,message,fn){
			showMessage(type,message,fn);
		},
		"showResult":function(data,fn){
			showResult(data,fn);
		},
		"validateChar":function(text){
			return validateChar(text);
		},
		"noDialogValidateChar":function(text){
			return noDialogValidateChar(text);
		}
	}
	
}();