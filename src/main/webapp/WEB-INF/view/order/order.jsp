<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<meta name = "viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scaleable=0">

    <link rel="stylesheet" href="/css/reset.css" >
    <link rel="stylesheet" href="/css/order.css" >
    
    <link rel="stylesheet" href="/css/footer.css" >
    <link rel="stylesheet" href="/css/nav.css" >
    <link rel="stylesheet" href="/css/header.css" >
    
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    
</head>
<body>
	<header>
		<%@ include file="/WEB-INF/view/include/header2.jsp" %>
	</header>
	

	<section class="title">
		<h1>주문하기</h1>
	</section>
	
	<main>
		
	<form action="/order" method="post" onsubmit="return check()" >
	<ul>
		<li>
			<div class="order_info">
				<h2>${cartList[0].storeName }</h2><hr>
				<h2>주문정보</h2>
				
				<ul>
					<c:set var="total" value="0" > </c:set>
		
					<c:forEach items="${cartList }" var="cartList">
						<li>
							<input type="hidden" value="${cartList.menuName }" name="menuName">
							<input type="hidden" value="${cartList.menuPrice }" name="menuPrice">
							<input type="hidden" value="${cartList.amount }" name="amount">
						
						
						
							<div>${cartList.menuName }</div>
							<div class="price">ㆍ기본가격 <fm:formatNumber value="${cartList.menuPrice }"  pattern="###,###" />원</div>
							
							<c:set value="0" var="optionTotal" />
							<c:if test="${!empty cartList.menuOption }">
							<c:forEach begin="0" end="${fn:length(cartList.menuOption) -1 }" var="i">
								<input type="hidden" value="${cartList.menuOption[i] }" name="menuOption">
							
								<div class="menu_option"> 
									<span>ㆍ${cartList.menuOption[i] }</span>
									<span><fm:formatNumber  value="${cartList.menuOptionPrice[i] }" pattern="###,###" />원</span>
								</div>
														
								<c:set value="${optionTotal + cartList.menuOptionPrice[i] }" var="optionTotal" />
							</c:forEach> 
							</c:if>
						
							<c:set var="total" value="${total + (optionTotal + cartList.menuPrice) * cartList.amount}" ></c:set>
							
							<div class="amount">
								<div class="sum">
									<fm:formatNumber value="${(cartList.menuPrice + optionTotal) * cartList.amount }" pattern="###,###" />원
								</div>
								<!-- 메뉴 하나 총합 -->
								<span class="amount_box">
									<input type="hidden" id="menuPrice" value="${cartList.menuPrice + optionTotal }" >
									<!-- 메뉴 가격 스크립트로 전달 -->
									
			                       <span><button type="button" class="minus">-</button></span>
			                       <span><input type="number" id="amount" min="1" value="${cartList.amount }" readonly ></span>
			                       <span><button type="button"  class="plus">+</button></span>
			                   </span>
							</div>
							
						</li><hr>
						
					</c:forEach>
					
				</ul>
			</div>
		</li>
	
		<li class="delevery_cont">
		
			<div class="delevery_info">
				<h2>배달정보 </h2>
				<div class="address1">주소 : ${address.address2 }</div>
				
				<input type="hidden" value="${cartList[0].storeNum }" name="storeNum"> 
				<input type="hidden" value="${cartList[0].storeName }" name="storeName"> 
				<input type="hidden" value="${address.address1 }" name="userAddress1">
				<input type="hidden" value="${address.address2 }" name="userAddress2">
				
				
				<div>상세 주소</div>
				<div class="input_area"><input type="text" value="${address.address3 }"  name="userAddress3"> </div>
				 
				<div>전화번호</div>
				<c:if test="${!empty user  }">
					<div class="input_area"> <input type="text" value="${user.userPhone }" name="userPhone" required> </div>
				</c:if>
				<c:if test="${empty user  }">
					<div class="input_area"> <input type="text" value="" name="userPhone" required> </div>
				</c:if>
			</div>
		<hr>
		</li>
		
		<li class="request">
			<div>요청사항</div>
				<textarea rows="5" cols="50" name="request"></textarea> 
			<hr>
		</li>
		
		
		<li>
			<h2>결제수단</h2>
				<input type="radio" checked="checked" value="신용카드" name="payMethod" >신용카드
				<input type="radio" value="현장결제" name="payMethod">현장결제
			<hr>
		</li>
		
		<li class="point_area">
			<h2>포인트</h2>
			
			<div class="point">
			
				<div class="point_click">
					<c:if test="${!empty user  }">
						<span><fm:formatNumber value="${user.point }"  pattern="###,###" />원 사용 가능</span>
					</c:if>
					
					<c:if test="${empty user  }">
						<span >로그인후 사용 가능합니다.</span>
					</c:if>
					
					<span class="icon"> <i class="fas fa-chevron-down"></i>  </span>
				</div>
				
				<div class="point_input_box" >
					<span>
						<input type="number" name="usePoint" min="0" class="point_input" placeholder="사용 할 포인트"  >
						<button class="use_point" type="button">사용하기</button>
					</span>
				</div>
			
			</div><hr>
				
		</li>
		
		<li class="pay">
		
			<div class="order_price">주문금액 : <fm:formatNumber value="${total }"  pattern="###,###" />원</div>
			<div>배달팁 <fm:formatNumber value="${cartList[0].deleveryTip }"  pattern="###,###" />원</div>
			
			<div class="point_dis"><span>포인트 할인 </span><span></span> </div>
				
			<div class="total">
				<fm:formatNumber value="${total + cartList[0].deleveryTip }"  pattern="###,###" />원 결제하기</div>
			
			<input type="hidden" value="${total  }" name="total" id="total"> 
			<input type="hidden" value="${cartList[0].deleveryTip }" name="deleveryTip"> 
			
			
			<input type="submit" value="주문하기" class="order_btn">
		</li>
		
		
		
		</ul>
		
	</form>
	
		
	</main>


	<script type="text/javascript">
	var deleveryTip = ${cartList[0].deleveryTip };
	var total = Number(${total});
	
   	
	$(".amount_box button").click(function(){
		var amount = $(this).parent().siblings().find("#amount");
		var amountVal = Number($(this).parent().siblings().find("#amount").val());
		
		
		var cartNum = $(this).parent().parent().parent().parent("li").index() / 2;
		var menuPrice = Number($(this).parent().siblings("#menuPrice").val());
		
		var sumText = $(this).parent().parent().siblings(".sum");
		
		
		console.log("cartNum = " + cartNum );
		console.log("amount = " + amount.val() );
		
		
		if($(this).hasClass("plus")){
			console.log("플");
			amount.val(amountVal + 1 );
			
			total = total + menuPrice;
		} else {
			console.log("마");
			if(amount.val() > 1) {
				amount.val(amountVal - 1 );
				
				total = total - menuPrice;
			}
		}
		amountVal = amount.val();
		
    	    	console.log(total);
		
		
		console.log("amount = " + amount.val() );
		$.ajax({
    		url : "/amountModify",
    	    type : "post",
    	    data : {cartNum : cartNum, amount :  amountVal },
    	    success : function(){
    	    	
    	    	
    	    	sumText.html((menuPrice * amountVal).toLocaleString() + "원"); // 메뉴 하나 가격
    	    	
    	    	$(".order_price").html("주문금액 : " + total.toLocaleString() + "원" );
    	    	$(".total").html((deleveryTip + total).toLocaleString() + "원 결제하기");
    	    	$("#total").val(total);
    	    	
    		},
    		error : function(){
    			alert("다시 시도해주세요");
    		}
    	}); // ajax
		
		
	})
	

	
	
		if('${user}' != null && '${user}' != ""){
		
			$(".point_click").click(function(){
				$(".point_input_box").fadeToggle(200);
			});
			
			$(".use_point").click(function(){
				
				var point = Number($(".point_input").val());
				
				if(point != 0) {
					$(".total").html("");
					
					var ht = (deleveryTip + total - point).toLocaleString() +"원 결제하기"; 
					         
					$(".total").html(ht);
					
					$(".point_dis").show();
					$(".point_dis").html("포인트 할인 -" + point.toLocaleString() + "원");
					
				}
			});
			
		 	$(".point_input").focusout(function(){
		 		var total = ${total};
				var userPoint = Number('${user.point }');
				
				if($(this).val() > userPoint)
					$(this).val(userPoint);
				if($(this).val() > total)
					$(this).val(total);
				if($(this).val() < 0)
					$(this).val(null);
			});
		
			
		} else {
			 swal("", {
				  buttons: ["비회원으로 주문하기", "로그인"],
				}).then((value) => {
					 console.log(value);
					 if(value == true) {
						 location.href = "/login";
					 }  else {
						 console.log(13442);
						 
					 }
				});
			
			
 			 $(".point_area .point").css("border" , "1px solid #ddd"); 
 			 $(".point_area .point").css("cursor" , "default"); 
 			 $(".point_area span").css("color" , "#ddd"); 
 			 $(".point_area span").css("cursor" , "default"); 

		}			
	

		function check(){
			if($(".point_input").val() == null || $(".point_input").val() == "" ) {
				$(".point_input").val(0);
				return true;
			} 
		};
	    
		
		

		
	</script>


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






		
		

		
	
		<div>주문 내역</div>
		
		

		<hr>
	

		
		
		<div>${cartList }</div>
	<div>${address }</div>
