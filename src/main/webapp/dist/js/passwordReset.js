webpackJsonp([13],{0:function(t,n,e){t.exports=e(69)},3:function(t,n){},28:function(t,n){},69:function(t,n,e){e(28),e(3);var o=(e(1),e(4)),s={show:function(t){$(".error-item").show(),$(".error-msg").text(t)},hide:function(){$(".error-item").hide()}},i=0,a={data:{username:"",question:"",answer:"",password:"",passwordReset:""},init:function(){this.onLode(),this.bindEvent(),$(".user-con").find("input").text("")},onLode:function(){i=0,this.loadUsername()},loadUsername:function(){$(".step-con").hide(),$(".step-username").show()},loadQuestion:function(){$(".step-con").hide(),$(".step-question").show()},loadReset:function(){$(".step-con").hide(),$(".step-Reset").show()},bindEvent:function(){var t=this;$(".submit").click(function(){t.submit(t)}),$(".step-con").keyup(function(n){13===n.keyCode&&t.submit(t)})},submit:function(t){var n=t;if(0===i){var e=n.data.username=$("#username").val();e?o.getQuestion(n.data,function(t){s.hide(),$(".question").text(t.data),n.data.question=t.data,n.loadQuestion(),i++},function(){s.show()}):s.show("请输入用户名")}if(1===i){var a=n.data.answer=$("#anwser").val();a&&o.checkAnwser(n.data,function(t){n.data.forgetToken=t.data,n.loadReset(),i++},function(t){s.show("回答错误")})}if(2===i){var u=n.data.passwordNew=$("#password").val(),r=$("#passwordComfirm").val();u&&u.length>=6?u===r?o.reset(n.data,function(t){window.location.href="./result.html?type=password-reset"},function(){s.show("密码修改失败")}):s.show("两次输入的密码不一致"):s.show("密码应该大于六位")}}};a.init()}});