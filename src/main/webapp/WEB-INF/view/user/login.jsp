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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" 
		integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    
</head>
<body>
    <main>

	<c:if test="${!empty loginFail }">
		<script>
			console.log('${loginFail}');
			swal('${loginFail}');
		</script>
	</c:if>
    	
    
        
        <div class="login_box">
            
                <a href="/"><img src="../img/bamin2.png" alt="이미지"></a>    

            
            <form action="/login" method="post">



               <div class="input_aera"><input type="email" name="userId"  value="shol7684@naver.com" required placeholder="이메일을 입력해 주세요" ></div>
               <div class="input_aera"><input type="password" name="userPwd" value="1111" required placeholder="비밀번호를 입력해 주세요"></div>


               <input type="submit" value="로그인" class="login_btn" >
            
			<div class="search">
				<div>
					<i class="fas fa-check-square" ></i>
					<input type="checkbox" id="continue_login" name="continueLogin"> 
					<label for="continue_login">로그인 유지하기</label> 
				</div>
				
	            <div>
	            	<span><a href="">아이디</a></span>
		            <span> ㅣ </span>
		            <span><a href="">비밀번호 찾기</a></span>
		            
	            </div>
	            
            </div>
	
            </form>
			<div id="kakko_id_login">
				<a href="/kakkoLogin">
					 <img src="/img/kakao_login_button.png">
				</a>
			</div>
			
			
			<div class="join"><a href="/join" >회원 가입하러 가기</a></div>
        </div>

    </main>
    
    
    <script>
    	$(".search i").click(function(){
    	
    		if($("input[type='checkbox']").is(":checked")) {
	    		$("input[type='checkbox']").prop("checked" , false);
	    		$(this).css("color" , "#999999");
	    		
    		} else {
	    		$("input[type='checkbox']").prop("checked" , true);
	    		$(this).css("color" , "#2AC1BC");
    		}
    		
    		
    	})
    	
    	$("input[type='checkbox']").change(function(){
    		
    		console.log($("input[type='checkbox']").is(":checked"));
    		
    		if($("input[type='checkbox']").is(":checked")) {
	    		$(".search i").css("color" , "#2AC1BC");
    		} else {
	    		$(".search i").css("color" , "#999999");
    		}
    	})
 
    
    
    </script>
    
    
</body>
</html>