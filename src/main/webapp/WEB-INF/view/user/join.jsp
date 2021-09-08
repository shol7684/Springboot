<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/reset.css" >
    <link rel="stylesheet" href="/css/login.css" >
    
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <c:if test="${!empty joinFail }">
    	<script>
    		swal('${joinFail }');
    	</script>
    </c:if>
    <main>
        
        <div class="login_box">
            
            <a href="/"><img src="../img/bamin2.png" alt="이미지"></a>
            
             <c:if test="${empty kakkoIdCheck }">
	             <form action="/join" method="post">
	            
		               <div class="input_aera"><input type="email" name="userId" class="user_id"  value="" required placeholder="이메일을 입력해 주세요" ></div>
		               <span class="id_check"></span>
		               <div class="input_aera"><input type="password" class="password1" name="userPwd" value="" required placeholder="비밀번호를 입력해 주세요"></div>
		               <div class="input_aera"><input type="password" class="password2" value="" required placeholder="비밀번호를 한번더 입력해 주세요"></div>
		               <span class="password_check"></span>
		               
		               <div class="input_aera"><input type="text" class="nickname" name="nickname" value="" required placeholder="사용하실 닉네임을 입력해 주세요"></div>
		               <span class="nickname_check"></span>
		               
		               <div class="input_aera">
		                    <input type="number" name="userPhone" value="" class="phone" required placeholder="'-' 없이 입력해 주세요" maxlength="13" >
		               </div>
	                    <span class="phone_check"></span>
		                
		               <input type="submit" value="회원가입" class="login_btn" >
		
	            	</form>
         	  </c:if>
            
      
      	
      	
      	<!-- 카카오 아이디로 처음 가입할때 -->
            <c:if test="${kakkoIdCheck == 0 }">
            
	            <form action="/kakkoJoin" method="post">
		            
					<div><input type="hidden" name="userId" class="user_id"  value="${userEmail }" required placeholder="이메일을 입력해 주세요" ></div>
					
					<div>휴대폰번호를 입력해주세요</div>
	                <div class="input_aera"><input type="text" name="userPhone" value="" class="phone"  
	                    required placeholder="'-' 없이 입력해 주세요" maxlength="13" ></div>
	            
	            	<div><input type="hidden" class="nickname" name="nickname"  value="${nickname }"  required placeholder="사용하실 닉네임을 입력해 주세요"></div>
	            	<!-- <span class="nickname_check"></span> -->
			
					<input type="submit" value="회원가입" id="login_btn"  >
				</form>
            </c:if>
            
            
            <!-- 카카오 이메일과 동일한 계정이 있을때 -->
            <c:if test="${kakkoIdCheck == 1}"> 
            
	            <form action="/kakkoConnetion" method="post">
		            
	            	<div>
	            		동일한 메일 주소로 가입된 계정이 있어요<br>
	            		휴대폰 인증 생략
	            	 </div>
	            	 <input type="hidden" name="userPhone" value="${userCheck.userPhone }">
	            	 <input type="hidden" name="userId" value="${userCheck.userId }">
	            	 <input type="hidden" name="nickname" value="${nickname }">
		
			
					<input type="submit" value="연결" id="kako_connetion" >
				</form>
           </c:if> 
            
            <script type="text/javascript">
				$(".login_btn").css("background" , "#ddd");
				$(".login_btn").attr('disabled', true); 
            	  		
				$(".nickname").focusout(function(){
					
					const nickname = $(".nickname").val();
					const nicknameCheck = $(".nickname_check");
            		const regNickname =  /^[가-힣|a-z|A-Z|0-9|]+$/;

            		if(nickname == null || nickname == ""){
            			nicknameCheck.html("닉네임을 입력 해주세요");
            			inputCheck();
            			
            			return false;
            		}
            		
            		if(!regNickname.test(nickname)){
            			nicknameCheck.html("닉네임은 한글, 영어, 숫자만 4 ~10자리로 입력 가능합니다.");
            			inputCheck();
            			
            			return false;
            		}
            		
            		console.log("test=" + regNickname.test(nicknameCheck));
	            		
            		let data = {
            				functionNumber :0,
            				nickname:nickname
            	       };
            		
            		const impossibilityMsg = "이미 사용중이거나 탈퇴한 닉네임입니다";
            		const possibilityMsg = "사용 가능합니다";
            		const target = $(".nickname_check");
            		
            		overlapCheck(data, target, impossibilityMsg , possibilityMsg);
       	        	
       	        	
				}); // function
				
				$(".user_id").focusout(function(){
            		
					const email = $(".user_id").val();
					const emailCheck = $(".id_check");
					const regEmail = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
            		
            		if(email == null || email == ""){
            			emailCheck.html("이메일을 입력해 주세요");
            			return;
            		}
            		
            		if(!regEmail.test(email)){
            			emailCheck.html("사용 불가능합니다");
            			return;
            		}
            		
            		let data = {
            	    		userId : $(".user_id").val(),
            	    		functionNumber : 1
            	        	};
            	    
               		const impossibilityMsg = "이미 사용중인 이메일입니다";
            		const possibilityMsg = "사용 가능합니다";
            		const target = $(".id_check");
            		
            		overlapCheck(data, target, impossibilityMsg , possibilityMsg);
    	        	
    	        	
				}); // function
				
				
				$(".password1").focusout(function(){
					pwdCheck();
					
					inputCheck();
					
				});
				
				$(".password2").focusout(function(){
					pwdCheck();
					
					inputCheck();
					
				});
				
				
				$(".phone").focusout(function(){
					
					const regPhone = /[0-9]{10,12}$/;
					const phoneCheck = $(".phone_check");
					
					!regPhone.test($(".phone").val()) ? phoneCheck.html("휴대폰번호를 확인해 주세요") : phoneCheck.html("");  
					
					inputCheck();
				});
				
				
				function overlapCheck(data, target, imimpossibilityMsg, impossibilityMsg){
					$.ajax({
       	        		url : "/overlapCheck",
       	        	    type : "post",
       	        	    data : data,
       	        	    async : false,
       	        	    success : function(result){
       	        	    	 
       	        	    	if(result == 1) {
       	        	    		target.html(imimpossibilityMsg);
       	        	    	} else {
       	        	    		target.html(impossibilityMsg);
       	        	    	} 
   	        	    		inputCheck();
       	        		} // success
       	        	}); // ajax
				}
				
				
				function pwdCheck(){
					const password1 = $(".password1").val();
					const password2 = $(".password2").val();
					
					if( password1 != "" && password2 != "" ) {
						
						password1 != password2 ? $(".password_check").html("비밀번호를 확인해 주세요") : $(".password_check").html("");
					}
				}
				
				function inputCheck(){
					
					if($(".id_check").html() == "사용 가능합니다" &&
							$(".password1").val() != "" &&
							$(".password2").val() != "" &&
							$(".phone").val() != "" &&
							$(".password_check").html() == "" &&
							$(".nickname_check").html() == "사용 가능합니다" &&
							$(".phone_check").html() == "") {
						
						 $(".login_btn").css("background" , "#2AC1BC");
      	    			 $(".login_btn").attr('disabled', false);
					} else {
						
						$(".login_btn").css("background" , "#ddd");
       	    		 	$(".login_btn").attr('disabled', true); 
					}
				}	
					
            </script>

        </div>

    </main>
</body>
</html>