webpackJsonp([15],{0:function(s,t,o){s.exports=o(75)},34:function(s,t){},75:function(s,t,o){o(34);var n=o(2),a=o(1),r=o(4),i={init:function(){this.onload(),this.bindEvent()},onload:function(){n.init({name:"user-pass-updata"})},bindEvent:function(){var s=this;$("#submit").click(function(){s.submit()})},submit:function(){var s=$("#password-old").val(),t=$("#password-new").val(),o=$("#password-confirm").val(),n={passwordOld:s,passwordNew:t,passwordComfirm:o};this.validate(n).status&&r.passwordUpdata({passwordOld:n.passwordOld,passwordNew:n.passwordNew},function(){a.doLogin()},function(s){a.errorHint(s)})},validate:function(s){var t={status:!1,msg:""};return a.validate(s.passwordOld,"notEmpty")?!a.validate(s.passwordNew,"notEmpty")||s.passwordNew.length<6?(t.msg="请输入不少于6位的新密码",a.errorHint(t.msg),t):a.validate(s.passwordComfirm,"notEmpty")?s.passwordNew!==s.passwordComfirm?(t.msg="两次密码不一致",a.errorHint(t.msg),t):(t.status=!0,t):(t.msg="确认密码不能为空",a.errorHint(t.msg),t):(t.msg="请输入原密码",a.errorHint(t.msg),t)}};$(function(){i.init()})}});