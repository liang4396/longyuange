;(function( $,window,undefined ) {

    MyDialog= function(){
        this.openType="default";
        this.isLockedHeight=false;
        this.lockedHeight="500px";
        this.dialog={};
    }


    MyDialog.defaults={
        id : "myDialog_"+new Date().getTime(),
        lock : true,
        width : '600px',
        padding:"2px 3px",
        title : "窗口",
        left : '60%',
        top : "50px",
        drag : true,
        resize : false,
        // zIndex:9997,
        ok : false,
        okVal : "确定",
        cancel : true,
        cancelVal : "关闭"
    }

    MyDialog.message={
        id : 'message_'+new Date().getTime(),
        content : '无内容',
        icon : "warning",
        lock : true,
        left : '50%',
        top : '8%',
        ok :false,
        okVal : "确定",
        cancel : true,
        cancelVal : "关闭"
    }
    
    
    MyDialog.fn = MyDialog.prototype = {

        constructor: MyDialog,

        showDialog:function(config){
        	
            var _defaults=MyDialog.defaults;
        	var scrollTop=document.body.scrollTop==0?document.documentElement.scrollTop:document.body.scrollTop;
        	
        	
            config=config||{};

            for (var i in _defaults) {
                if (config[i] === undefined) config[i] = _defaults[i];
            };
            
            config.top=scrollTop+parseInt(config.top)+"px";
            
            this.dialog = $.dialog(config);
            
            MyDialog.activeDialog=this;
            return this;
        },

        showMessage:function(config){
            
        	var _message=MyDialog.message;

            config=config||{};

            for (var i in _message) {
                if (config[i] === undefined) config[i] = _message[i];
            };


            $.dialog(config);
            
            return this;
        },
        
        content:function(text){
        	this.dialog&&this.dialog.content(text);
        	return this;
        },
        
        scrollTo:function($elem){
          
            var that=this;
            
            if(that.dialog.config){
                var $div=that.dialog.DOM.content;
                var $content=$div.find(":first-child"), scrollTo = $($elem, $content);
                var top=document.documentElement.scrollTop||document.body.scrollTop;
                var scrollTop=$content.offset().top-top;

                $scrollDiv=that.isLockedHeight?$div:$('html,body');
                
                if (!scrollTo.offset()) {
                    $scrollDiv.animate({
                        scrollTop :  scrollTop-90
                    });
                }else{

                    $scrollDiv.animate({
                        scrollTop : scrollTo.offset().top - top -90
                    });
                }

            }else{
                throw new Error("没有对话框！");
            }
            return this;
        },
        
        initHeight:function(height){
        	height=height||"30px";
            var $div=this.dialog.DOM.content;

            $div.css({"height":height,"overflow":"hidden"});
            return this;
        },
        
        heightLock:function(height){
         
            height=height||this.lockedHeight;
            var $div=this.dialog.DOM.content;

            $div.css({"max-height":height,"overflow-y":"auto","overflow-x":"hidden"});
            
            this.lockedHeight=height;
            this.isLockedHeight=true;
            
            return this;

        },

        heightAuto:function(){
            var $div=this.dialog.DOM.content;
            var $content=$div.find(":first-child");
            var contentHeight =$content.height();
            var contentWidth = $content.width();
            $div.animate({
                height : contentHeight
            });
            return this;
        },

        showAnimate:function(type){
            var that=this;
            var $div=that.dialog.DOM.content;
            var $border=that.dialog.DOM.border;
            var $content=$div.find(":first-child");
            var top=parseInt(that.dialog.config.top);
            var contentHeight =$content.height();
            var contentWidth = $content.width();
          
            if(type=="border-slide"){
               

            }else if(type==""){

            }else{
               // $div.css("height","200px");
                $div.animate({
                  scrollTop:0,
                  height :!that.isLockedHeight?contentHeight:that.lockedHeight<contentHeight?that.lockedHeight:contentHeight
                 // width:"97%"
                },500);
            }
            return this;
        },

        close:function(){
        	var that=this;
            if(that.dialog.close){
            	that.dialog.close();
            }else{
                throw new Error("没有对话框可以关闭！");
            }
            return this;
        }
    }

})(jQuery,window);

