webpackJsonp([12],{0:function(r,t,e){r.exports=e(71)},3:function(r,t){},30:function(r,t){},71:function(r,t,e){e(30),e(3);var a=e(1);$(function(){var r=a.getUrlParam("type")||"default";$(".resultCon."+r+"-success").show();if("payment"===a.getUrlParam("type")){var t=a.getUrlParam("orderNo");$(".order").attr("href",$(".order").attr("href")+t)}})}});