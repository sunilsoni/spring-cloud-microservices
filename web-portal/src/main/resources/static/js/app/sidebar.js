$(function () {
   $('#side-menu .nav li').off('click').on('click',function(){
	   $(this).siblings().find('a').removeClass('active');
	   $(this).find('a').addClass('active');
   });
   $('#side-menu .nav li:first').trigger('click');
});