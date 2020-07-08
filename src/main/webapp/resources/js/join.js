'use strict'

$(document).ready(function(){
	var pwj = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/; 
	var emailj = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var namej = /^[가-힣A-Za-z0-9]{2,12}$/;
	
	var emailb, pwb, nmb; 
	emailb=pwb=nmb=false;
	
	$(uh).on({
		keyup : function(){
			// 이메일정규식
			if(emailj.test($('#useremail').val())){
				console.log($('#useremail').val());
				$('#emailuh').text('');
				
				$.ajax({
					url : '/emailchk.do?useremail='+$('#useremail').val(),
					type : 'get',
					success : function(emailchkrs){
						if(emailchkrs == 1){
							$('#emailuh').text('이미있는 이메일입니다.');
						}
						else{
							$('#emailuh').text('사용가능한 이메일입니다.');
							emailb = true;}
					}
				})
			}
			else{
				$('#emailuh').text('이메일 형식이 올바르지 않습니다.');
			}
			// 비밀번호정규식
			if($('#userpw').val()!=""){
				if(pwj.test($('#userpw').val())){
					$('#pwuh').text('');
				}
				else{
					$('#pwuh').text('영어 대소문자,숫자 혼합 8~16자 이내로 입력')
					$('#pwchkuh').text('');
				}
			}
			// 비밀번호일치
			if($('#userpwchk').val()!=""){
				if($('#userpw').val() == $('#userpwchk').val()){
					$('#pwchkuh').text('password ilchi');
					pwb = true;
				}
				else{
					$('#pwchkuh').text('password bul-ilchi');	
				}
			}
			// 닉네임정규식
			if($('#username').val()!=""){
				if(namej.test($('#username').val())){
					$('#nameuh').text('');
					$.ajax({
						url : '/namechk.do?username='+$('#username').val(),
						type : 'get',
						success : function(namechkrs){
							console.log(namechkrs);
							if(namechkrs == 1){
								$('#nameuh').text('이미있는 이름입니다.');
							}
							else{
								$('#nameuh').text('사용가능한 이름입니다.');
								nmb = true;}
						}
					})
					
				}
				else{
					$('#nameuh').text('2~12자 이내 작성')
				}
			}
			
			if(emailb==true&&pwb==true&&nmb==true){
				$("#joinsubmit").removeAttr("disabled");
			}
			else{
				$("#joinsubmit").attr("disabled","disabled")
			}
		}
	});

});