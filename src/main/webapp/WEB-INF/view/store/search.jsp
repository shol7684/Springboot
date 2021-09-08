<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/css/reset.css" >
	<link rel="stylesheet" href="/css/header.css" >
	<link rel="stylesheet" href="/css/nav.css" >
	<link rel="stylesheet" href="/css/footer.css" >
	<link rel="stylesheet" href="/css/search.css" >
	
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
	
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/view/include/header2.jsp" %>
	</header>




	<main>
	  	<form action="/store/search" method="post" onsubmit="return check()" >
		  	<div class="input_box">
			  	<div>
					<label for="submit">
						<i class="fas fa-search"></i>
					</label>
					<input type="submit" id="submit">
				</div>
				<div>	
					<input type="text" class="search" name="keyword" value="${searchWord }" placeholder="어떤 가게를 찾으시나요?">
					<div class="info">현재 주소지를 기준으로 검색됩니다.</div>
					<input hidden="address1" value="${address.address1 }" name="address1">
					<input hidden="address2" value="${address.address2 }" name="address2">
					<input hidden="address3" value="${address.address3 }" name="address3">
					<input hidden="address4" value="${address.address4 }" name="address4">
				</div>
				<div>
					<button type="button" class="word_delete"><i class="fas fa-times"></i></button>
				</div>
			
			</div>
		</form>
	
		<div class="search_word_head">
			<h2>최근 검색어</h2>
			<button>전체삭제</button>
		</div>
		<div class="search_word">
			<ul>
				<c:forEach items="${searchList }" var="searchList">
				
					<li><span>${searchList }</span><button><i class="fas fa-times"></i></button></li>
					
				</c:forEach>
			</ul>
		</div>
		
		
		 <div class="box">

                <ul class="store">
                	
                	<c:forEach items="${storeList }" var="storeList"  varStatus="status" >
                
                    <li >
	                    <div>
	                        <a href="/store/detail/${storeList.storeNum }">           
	                            <img src="${storeList.storeThumb }" alt="이미지">
	                            <div class="inf">
	                                <h2>${storeList.storeName }</h2>
	                                <div>
	                                	<span>평점 ${storeList.score }</span>
	                                	
	                                	<span class="score_box">
	                                	
	                                	<c:set var="score" value="${Math.round(storeList.score) -1 }" />
	                                	<c:if test="${score <= 0}" >
	                                		<c:set var="score" value="0" />
	                                		
	                                	</c:if>
	                                	
	                                	<c:forEach begin="0" end="${score }" >
											<i class="fas far fa-star"></i>
					                   	</c:forEach>
					                   	
				               			</span>
	                                </div>
	                             
	                                
	                                <div><span>리뷰 ${storeList.reviewCount }</span><span>ㅣ</span><span>사장님 댓글 ${storeList.bossComment }</span></div>
	                                <div><span>배달시간 ${storeList.deleveryTime }분</span><span>최소주문금액 <fm:formatNumber value="${storeList.minDelevery }"  pattern="###,###" />원</span></div>
	                                <div>배달팁 <fm:formatNumber value="${storeList.deleveryTip }"  pattern="###,###" />원</div>
	                            </div>
	                        </a>
                        </div>
                    </li>
                    
                    </c:forEach>
                    
                    <li class="temp">
                    </li>
                </ul>
            </div>
	
	</main>

  
  
  	<nav>
		<%@ include file="/WEB-INF/view/include/nav.jsp" %>
	</nav>
	
	<footer>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	</footer>
  
 
 <script>
 	const searchList = "${searchList }";
 	
 	inputCheck();
 	
 	if(searchList == "[]") {
 		$(".search_word li").css("display" , "none");
 	} 
 
 	
 	$("input[name='keyword']").keyup(function(key){
 		
 		console.log(key.keyCode);
 		
 		inputCheck();
 		
 		// 모바일 검색버튼
 		if(key.keyCode == 13) {
 			$("#submit").click();
 		}
 	})	
 	
 	
 	// 검색창 텍스트 지우기버튼
 	$(".word_delete").click(function(){
 		$("input[name='searchWord']").val("");
 		
 		inputCheck();
 	})
 	
 	
 	// 검색어 전체삭제
 	$(".search_word_head button").click(function(){
 		 $.ajax({
	    		url : "/searchWordDeleteAll",
	    	    type : "post",
	    	    async: false,  
	    	    success : function(result){
					$(".search_word li").css("display" , "none");
	    	    	
				   	 
	    		} // success
	    	}); // ajax
 	})
 	
 	
 	// 검색어 1개 삭제
	$(document).on("click", ".search_word li button", function(){ 
		const keyword = $(this).siblings().html();
		
		console.log(keyword);
		
		$.ajax({
    		url : "/searchWordDelete",
    	    type : "post",
    	    data : {keyword : keyword},
    	    success : function(result){
    	    	console.log("result = " + result) ;
			   	 
    	    	let ht = "";
    	    	
    	    	for(var i=0; i<result.length;i++) {
    	    		ht += `<li><span> \${result[i] }</span><button><i class="fas fa-times"></i></button></li>`;
    	    	}
    	    	
    	    	$(".search_word ul").html(ht);
    	    	
    		} // success
    	}); // ajax
 	})
	
 	
	//	최근 검색어 클릭시 재검색
	$(document).on("click" ,".search_word span" , function(){
		
		$("input[name='searchWord']").val($(this).text());
		
		$("#submit").click();
		
		inputCheck();
		
	})
		
	function inputCheck(){
 		$("input[name='searchWord']").val() == "" ? 
 				$(".word_delete").css("display" , "none") : $(".word_delete").css("display" , "block") 
 	}
		
 	
 	function check() {
 		if($(".search").val() == "" )
	 		return false;
 	}
 </script>
  
  
</body>
</html>