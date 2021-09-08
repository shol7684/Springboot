<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>  

	<div class="modal_bg"></div>

	<div class="addCart_modal modal"  >
	
    	<span id="modal_header">
			<button class="closeA">×</button>
			<h1>메뉴 상세</h1>
    	</span>
	            
		<div class="modal_box" >
            
	        <img src="" alt="이미지" class="menu_img" >
	        <h2 class="menu_name">메뉴 이름</h2>
	        <div class="menu_dec"></div>
	        <div class="price"><span>가격</span><span class="menu_price" >0</span></div>

			<div id="option">
				<h2>옵션 선택</h2>
	            <ul>
	                <li>
		                <div class="option_box">
		                	<span></span>
		                	<span></span>
	                	</div>
	              	</li>
	     
	            </ul>
		    </div>        
	
	            <div class="amount">
	                <span class="amount_text">수량</span>
	                
	                <span class="amount_box">
                        <span class="btn"><button class="minus">-</button></span>
                        <span><input type="number" id="amount" min="1" value="1" readonly ></span>
                        <span class="btn"><button class="plus">+</button></span>
                    </span>
	            
	            </div>
	
			</div>
			
			<div id="btn_box">
				<div class="min_delevery">배달최소주문금액 <fm:formatNumber value="${storeVO.minDelevery }" pattern="###,###" />원 </div>
	            <div class="sum"><span>총 주문금액</span><span class="menu_sum">0</span></div>
           		
           		<button class=closeB type="button">취소</button>
           		<button class="add_cart" type="button">장바구니에 담기 </button>
            </div>
		
	</div>

    
    
    
	 