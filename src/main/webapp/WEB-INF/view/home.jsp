<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
     <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/reset.css" >
    <link rel="stylesheet" href="/css/home.css" >
 
 
    <link rel="stylesheet" href="/css/header.css" >
    <link rel="stylesheet" href="/css/footer.css" >
    <link rel="stylesheet" href="/css/nav.css" >

    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    
    
    
</head>
<body>

<c:if test="${!empty msg }">
	<script>
		alert('${msg}');
	</script>
</c:if>


<c:if test="${!empty noAddress }">
	<script>
		swal('${noAddress }');
	</script>
</c:if>



	<header>
	    <%@ include file="/WEB-INF/view/include/header2.jsp" %>
	</header>
    <!-- 헤더 -->

    <!-- 콘텐츠 -->
    
    <div class="wrap">
	    <main>
	    	<section class="address_search">
	    		
				<form action="/" method="post">
					<div id="search_box">
						<div>
	                   		<input type="hidden" id="sample2_postcode" placeholder="우편번호" name="address1" readonly>
							 <input type="text" onclick="sample2_execDaumPostcode()" value="${address.address2 }" id="sample2_address" readonly  placeholder="주소를 입력해 주세요 (두정동)"  name="address2" ><br>
							 <input type="hidden" id="sample2_detailAddress" placeholder="상세주소" name="address3" readonly>
							 <input type="hidden" id="sample2_extraAddress" placeholder="참고항목" name="address4" readonly>
						 </div>
						 
						 <div class="search_btn">
						 	<label for="search_btn">
						 		<i class="fas fa-search"></i>
						 	</label>
						 	
						 	<input type="submit" name="search"  value="" id="search_btn">
						 	
						 </div>
                    
                		<%@ include file="/WEB-INF/view/include/addressSearch.jsp" %>
					</div>
				</form>
	    	</section>
	    
	        <section class="category_box" >
	           
	            <div class="box">
	            <ul class="category">
	
	                <li>
		                <ul>
			                <li>
			                	<a href="/storeList/100/${address.address1 }">
				                	<span>
				                		<img src="/img/pizza2.png" alt="이미지">
				                	</span>
			                		<span>피자</span>
			                	</a>
		                	</li>
		                	
			                <li>
				                <a href="/storeList/103/${address.address1 }">
				                	<span>
						                <img src="/img/bunsik1.png" alt="이미지">
					                </span>
					                <span>분식</span>
				                </a>
			                </li>
			                
			                <li>
				                <a href="/storeList/104/${address.address1 }">
					                <span>
						                <img src="/img/dessert2.png" alt="이미지">
					                </span>
					                <span>카페/디저트</span>
				                </a>
			                </li>
		                	
		                	
			                <li>
			                	<a href="/storeList/105/${address.address1 }">
				                	<span>
				                		<img src="/img/cutlet1.png" alt="이미지">
			                		</span>
			                		<span>돈까스/일식</span>
		                		</a>
	                		</li>
		                	
			                <li>
			                	<a href="/storeList/101/${address.address1 }">
				               	 	<span>
				                		<img src="/img/chicken1.png" alt="이미지">
			                		</span>
			                		<span>치킨</span>
		                		</a>
	                		</li>
	                	</ul>
	                
	                </li>
	                
	                <li>
	                	<ul>
			                <li>
			                	<a href="/storeList/106/${address.address1 }">
					                <span>
				                		<img src="/img/chinese1.png" alt="이미지">
		    	            		</span>
			                		<span>중국집</span>
		                		</a>
	                		</li>
		                
			                <li>
				                <a href="/storeList/107/${address.address1 }">
				                	<span>
						                <img src="/img/jockbal1.png" alt="이미지">
				              	 	</span>
					                <span>족발/보쌈</span>
			              	 	</a>
			                </li>
	                
			                <li>
			                	<a href="/storeList/108/${address.address1 }">
					                <span>
				                		<img src="/img/jockbal2.png" alt="이미지">
			                		</span>
			                		<span>야식</span>
		                		</a>
	                		</li>
	                
			                <li>
			                	<a href="/storeList/109/${address.address1 }">
					                <span>
			                			<img src="/img/jockbal3.png" alt="이미지">
			                		</span>
			                		<span>한식</span>
			                	</a>
			                </li>
			                
			                <li>
				                <a href="/storeList/102/${address.address1 }">
			                		<span>
					               		<img src="/img/hamburger4.png" alt="이미지">
			               			</span>
			               			<span>패스트푸드</span>
			               		</a>
			               	</li>
			               	
	                	</ul>
	                </li>
	            </ul>
	            </div>
	
	        </section>
	    </main>
    </div>
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


    
</body>
</html>