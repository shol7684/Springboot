<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/css/reset.css" >
    <link rel="stylesheet" href="/css/orderDetail.css" >
    
    <link rel="stylesheet" href="/css/footer.css" >
    <link rel="stylesheet" href="/css/nav.css" >
    <link rel="stylesheet" href="/css/header.css" >
    
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/view/include/header2.jsp" %>
	</header>
	

	<section class="title">
		<h1>주문 상세</h1>
	</section>
	
	<main>
		<div class="detail_box">
			<div class="order_cont">
				<div>${orderDetail[0].deleveryState }</div>
				<div class="store_name">${orderDetail[0].storeName }</div>
				
				<div class="order_info">
					<div>주문일시 : <fm:formatDate value="${orderDetail[0].orderDate }" pattern="yyyy년 MM월 dd일 (E) a hh:mm"/> </div>
					<div>주문번호 : ${orderDetail[0].orderNum }</div>
				</div>
			</div>
			
			
			<ul class="order_menu">
			
				<c:set value="0" var="sum"></c:set>
				
				<c:forEach items="${orderDetail }" var="orderDetail">
				
				<c:set value="${orderDetail.menuPrice }" var="menuPrice" />
				
					<li>
						<div>
							<span>${orderDetail.menuName } ${orderDetail.amount }개</span>
						</div>
						<ul>
	                        <li>
	                         기본가격 <fm:formatNumber value="${orderDetail.menuPrice }" />원
	                        </li>
	                        
	                        
	                        <c:forEach items="${orderMenuOption }" var="orderMenuOption">
	                        	
	                        	<c:if test="${orderMenuOption.cartNum == orderDetail.cartNum}">
	                        
			                        <li>
			                         	<span>${orderMenuOption.menuOption }</span>
			                         	<span><fm:formatNumber value="${orderMenuOption.menuOptionPrice }" pattern="###,###" />원</span>   
			                        </li>
			                        
			                        <c:set value="${menuPrice + orderMenuOption.menuOptionPrice  }" var="menuPrice" />
		                        </c:if>
		                        
	                        </c:forEach>
	                        <li class="menu_price_sum">
	                        	<fm:formatNumber value="${menuPrice * orderDetail.amount }" pattern="###,###" />원
	                        	<!-- 메뉴 + 옵션 가격 -->
	                        </li>
	                    </ul>
						<hr>
					</li>
					
				<c:set value="${sum + menuPrice * orderDetail.amount}" var="sum"></c:set>
				<!-- 메뉴 총합 가격 -->
				</c:forEach>
			</ul>
			
	
	        <div class="price">
	            <div>
	                <span>총 주문금액 </span><span><fm:formatNumber value="${sum }" pattern="###,###" />원</span>
	            </div>
	            <div>
	                <span>배달팁 </span><span><fm:formatNumber value="${orderDetail[0].deleveryTip }" pattern="###,###" />원</span>
	            </div>
	            <div>
	            	<c:if test="${orderDetail[0].usePoint != 0 }">
	                <span>포인트 사용 </span><span>-<fm:formatNumber value="${orderDetail[0].usePoint }" pattern="###,###" />원</span>
	                </c:if>
	            </div>
	            <hr>
	        </div>
	
	        <div class="total">
	            <div>
	                <span>총 결제금액 </span><span class="sum"><fm:formatNumber value="${sum + orderDetail[0].deleveryTip - orderDetail[0].usePoint  }" pattern="###,###" />원</span>
	            </div>
	            <div>
	                <span>결제방법 </span><span>${orderDetail[0].payMethod }</span>
	            </div>
	        </div>
	        <hr>
	        <hr>
			
	
	        <div class="address">
	            <div>배달주소</div>
	                <ul>
	                    <li>${orderDetail[0].userAddress1 }</li>
	                    <li>${orderDetail[0].userAddress2 }</li>
	                    <li>${orderDetail[0].userAddress3 }</li>
	                </ul>
	            <hr>
	
	        </div>
	
	        <div>
	            <div>전화번호</div>
	            <div>${orderDetail[0].userPhone }</div>
	        	<hr>
	
	        </div>
			
	        <div>
	            <div>요청사항</div>
	            <div>${orderDetail[0].request }</div>
	        	<hr>
	
	        </div>
		</div>
	</main>



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