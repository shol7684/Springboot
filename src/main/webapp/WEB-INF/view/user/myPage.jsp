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
    <link rel="stylesheet" href="/css/myPage.css" >
    
    <link rel="stylesheet" href="/css/nav.css" >
    <link rel="stylesheet" href="/css/header.css" >
    <link rel="stylesheet" href="/css/footer.css" >
    
    
        
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    

</head>
<body>

	<c:if test="${!empty msg }">
		<script>
			swal('${msg }');
		</script>
	</c:if>
    
    <c:if test="${!empty update }">
    	<script>
			swal('${update }');
		</script>
    
    </c:if>
    

	
	<header>
	    <%@ include file="/WEB-INF/view/include/header2.jsp" %>
	</header>
    

    <section class="title">
        <h1>my 배민</h1>
    </section>
	    

     <!-- 콘텐츠 -->
    <main>
        <div class="container">

            <div class="content">
				
				<div class="content_box">
					<c:if test="${empty user}">
	                	<span class="user">
	                		<a href="/login">로그인 해주세요</a>
	                	</span>
	                </c:if>
	                
					<c:if test="${!empty user}">
	                	<span class="user">
	                		<a href="/myPage/info">${user.userId }</a>
	                		<button type="button" class="logout">로그아웃</button>
	                	</span>
	                </c:if>
	
	                <ul>
	                    <li><a href="/myPage/point"><span><img src="../img/icon11.png" alt="이미지"></span><span>포인트</span></a></li>
	                    <li><a class="updating" href="/myPage/coupon" onclick="return false;" ><span><img src="../img/icon22.png" alt="이미지"></span><span>쿠폰함</span></a></li>
	                    <li><a class="updating" href="/myPage/gift" onclick="return false;" ><span><img src="../img/icon33.png" alt="이미지"></span><span>선물함</span></a></li>
	                </ul>
	                
	                <ul>
	                    <li><a href="/myPage/dibs"><span></span><img src="../img/icon44.png" alt="이미지"><span>찜한가게</span></a></li>
	                    <li><a href="/orderList"><span></span><img src="../img/icon55.png" alt="이미지"><span>주문내역</span></a></li>
	                    <li><a href="/myPage/myReview"><span></span><img src="../img/icon66.png" alt="이미지"><span>리뷰관리</span></a></li>
	                </ul>
                
                </div>
            </div>
        </div>
        
    </main>
    <!-- 콘텐츠 -->

     
    <!-- 하단 메뉴 -->
    <nav>
    	<%@ include file="/WEB-INF/view/include/nav.jsp" %>
    </nav>
    <!-- 하단 메뉴 -->

	<!-- 푸터 -->
    <footer>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
    </footer>
	<!-- 푸터 -->
	
	<script type="text/javascript">

	$(".updating").click(function(){
		swal("업데이트 중 입니다");
	})
	
	$(".logout").click(function(){
		location.href = "/logout2";
	})
	</script>


    


</body>
</html>