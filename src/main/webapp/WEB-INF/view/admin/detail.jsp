<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
    <!-- 관리자 가게 상세 -->
    <meta charset="UTF-8">
    <title>Document</title>
    <meta name = "viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scaleable=0">
    <link rel="stylesheet" href="/css/reset.css" > 
    <link rel="stylesheet" href="/css/modal.css" > 
    
    <link rel="stylesheet" href="/css/addMenuModal.css" >
    <link rel="stylesheet" href="/css/detail.css" >
    
    <link rel="stylesheet" href="/css/storeRegiModal.css" >
    
    <link rel="stylesheet" href="/css/footer.css" > 
    <link rel="stylesheet" href="/css/header.css" >
    
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    
    <script type="text/javascript" src="/js/storeDetail.js" ></script>
    <script type="text/javascript" src="/js/imgPreview.js" ></script>
    <script type="text/javascript" src="/js/openModal.js" ></script>
    <script type="text/javascript" src="/js/adminDetail.js" ></script>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3fe0c2eaecb263f09df91a81c2ec64a0&libraries=services,clusterer,drawing"></script>
	
</head>

<body>  

	<!---------------- 메뉴 모달 ------------->
	<%@ include file="/WEB-INF/view/include/menuModal.jsp" %>
    <!---------------- 메뉴 모달 ------------->

    
    <!------------------- 정보 수정 모달 --------------->
   	<%@ include file="/WEB-INF/view/include/storeModifyModal.jsp" %>
    <!------------------- 정보 수정 모달 --------------->
    
    <!------------------- 메뉴 추가 모달 --------------->
   	<%@ include file="/WEB-INF/view/include/addMenuModal.jsp" %>
    <!------------------- 메뉴 추가 모달 --------------->

    <!-------------------- 헤더 ---------------------->
   	<header>
		<%@ include file="/WEB-INF/view/include/header2.jsp" %>
	</header>
    <!-------------------- 헤더 ---------------------->
 
   
   <!-- 메인 -->
	<%@ include file="/WEB-INF/view/include/storeDetail.jsp" %>
   <!-- 메인 -->
   

	<!-- 푸터 -->
    <footer>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
    </footer>
	<!-- 푸터 -->
	


	    




        

</body>
</html>