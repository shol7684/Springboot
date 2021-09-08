<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>  

 <div id="wrap">

	    <nav>
	        <h1>${storeVO.storeName }</h1>
	
               <div class="inf">
                   <div>
	                   <span class="score_box">
							<i class="far fa-star"></i>
	                   		<i class="far fa-star"></i>
	                   		<i class="far fa-star"></i>
	                   		<i class="far fa-star"></i>
	                   		<i class="far fa-star"></i>
	                   		<span>${storeVO.score }</span>	
	                   		      
               			</span><br>
	                   
                  		<c:if test="${storeVO.goodCheck == 0 }">
		                   <span><i class="far fa-heart" ></i> 찜 </span>
		                   <span class="count">${storeVO.dibsCount }</span>
	                   </c:if>
	                   
	                   <c:if test="${storeVO.goodCheck != 0 }">
		                   <span><i class="fas fa-heart" ></i> 찜 </span>
		                   <span class="count">${storeVO.dibsCount }</span>
	                   </c:if> 
		                   <span class="good"></span>
	                   
                   </div>
                   <div><span> 리뷰 ${storeVO.reviewCount } ㅣ </span><span>사장님 댓글 ${storeVO.bossCommentCount }</span></div>
                   
                   <div>최소주문금액 <fm:formatNumber value="${storeVO.minDelevery }" pattern="###,###" /> 원</div>
                   <div>예상 배달시간 ${storeVO.deleveryTime  }분</div>
                   <div>배달팁 <fm:formatNumber value="${storeVO.deleveryTip }" pattern="###,###" />원</div>
                   
                   
                   <div id="admin_button_box">
	                   <button class="inf_modify">정보 수정</button>
	                   <button class="add_menu">메뉴 추가</button>
	                   <button class="delete_menu">메뉴 삭제</button>
                   </div>
	    
               </div>
	    </nav>
	    
	    
	    
	         
	<!-- ---------------------------------- 모바일 카트 ---------------------------------- -->
   	<div class="cart_img">
   		<div class="cart_img_box">
   			<i class="fas fa-shopping-cart"></i>
   			<span class="cart_count"></span>
   		</div>
   	</div>
  	<!-- ---------------------------------- 모바일 카트 ---------------------------------- -->
    
	<!---------------------------------------- 장바구니 -------------------------------------- -->    
	    
	    <aside id="cart">
	        <div class="cart">	
	            <h2>장바구니</h2>
	            <i class="far fa-trash-alt deleteAll" ></i>
	            
	            <div class="cart_list">
		            <ul>
						<li>
			            </li>
		            </ul>
	            
	            </div>
	            
	            <div class="order_btn_box">
	            	<div class="total"></div>
		            <button class="order_btn" disabled>주문하기</button>
	            </div>
	        </div>
	        
	    </aside>
	<!---------------------------------------- 장바구니 -------------------------------------- -->    
	   

	    <main>
	    	<div class="offset"></div>
	        <ul class="tab ">
	            <li class="select">메뉴</li>
	            <li>정보</li>
	            <li>리뷰</li>
	        </ul>
	
	<!-- ----------------------------------- 메뉴 탭 ------------------------------------- -->
        <ul class="menu">
        <c:forEach items="${menuList }" var="menuList" >
            <li>
                <input type="checkbox" class="menu_delete_checkbox" name="deleteNumber" value="${menuList.menuNum }">
                <div class="in">
                    <h2>${menuList.menuName } </h2>
                    <div><img src="${menuList.menuThumb }" alt="이미지"></div>
                    <div><fm:formatNumber value="${menuList.menuPrice }" pattern="###,###" />원 </div>
                    
                    
                     <input type="hidden" value="${menuList.storeNum }" name="storeNum" id ="store_num"  >
		             <input type="hidden" value="${menuList.menuNum }" name="menuNum" id ="menu_num"   >
		             <input type="hidden" value="${menuList.menuName }" name="menuName" id ="menu_name" >
		             <input type="hidden" value="${menuList.storeName }" name="storeName" id ="store_name"   >
		             <input type="hidden" value="${menuList.menuPrice }" name="menuPrice" id ="menu_price"   >
		             <input type="hidden" value="${menuList.menuDec }" name="menuDec" id ="menu_dec"   >
		             <input type="hidden" value="${menuList.menuImg }" name="menuImg" id ="menu_img"   >
		             <input type="hidden" value="${menuList.menuThumb }" name="menuThumb" id ="menu_thumb"   >
                </div>
             </li>
        </c:forEach>     
        </ul>
	<!-- ----------------------------------- 메뉴 탭 ------------------------------------- -->
	
	
		<!-- ----------------------------------- 정보 탭 ------------------------------------- -->
       <ul class="info" >

			<li>
				<div>
					<h2>찾아 오시는 길</h2>
					
					<div id="map_box">
						<div id="map"></div>
						
						<div id="position_box">
							<button class="storePosition" ><i class="far fa-dot-circle"></i> 가게 위치로</button>
							<button class="userPosition"> <i class="far fa-dot-circle"></i> 내 위치로</button>
						</div>
					</div>
					
					<h2>위치안내</h2>
					<div>${storeVO.storeAddress2 }  ${storeVO.storeAddress3 }</div>
				</div>
			</li>
			
            <li>
            	<div>
	               <h2>가게 소개</h2>
	               <div> ${storeVO.storeDes }</div>
               </div>
            </li>
            
            <li>
            	<div>
	            	<h2>영업 정보</h2>
	            	
	            	<div class="info_detail_title">
	            		<div>상호명</div>
	            		<div>영업시간</div>
	            		<div>전화번호</div>
	            		
	            	</div>
	            	
	            	<div class="info_detail">
	            		<div>${storeVO.storeName }</div>
	            		<div><span>${storeVO.busiHours1 }시 ~</span><span>${storeVO.busiHours2 }시 </span></div>
	            		<div>${storeVO.storePhone }</div>
	            		
	            	</div>
            	</div>
            </li>
            
            <li>
            	<div>
	            	<h2>가계 통계</h2>
	            	<div class="info_detail_title">
		            	<div>최근 주문수</div>
		            	<div>전체 리뷰 수</div>
		            	<div>찜</div>
	            	</div>
	            	
	            	<div class="info_detail">
	            		<div>${storeVO.orderCount }</div>
	            		<div>${storeVO.reviewCount }</div>
	            		<div>${storeVO.dibsCount }</div>
	            	</div>
	            </div>	
            </li>
            
            
        </ul>
	<!-- ----------------------------------- 정보 탭 ------------------------------------- -->    

	
	<!-- ----------------------------------- 리뷰 탭 ------------------------------------- -->        
        <ul class="comment" >
        	
        	<li>
        		<div class="score_info">
        			
        			<div>
        				<div class="score">${storeVO.score }</div>
	                   		
        				<div>
       						
	                   		
	        				<c:forEach begin="0" end="4" var="i">
	        				
	        					<c:if test="${storeVO.score >= i }">
	        						<i class="fas fa-star"></i>
	        					</c:if>
	        				
	        					<c:if test="${storeVO.score < i }">
	        						<i class="far fa-star"></i>
	        					</c:if>
	        					
	        				</c:forEach>
        				
        				</div>
        				
        					
        			</div>
        			
        			<div class="score_count">
        				<div> 
        					<div>5점</div>
        					
        					<div class="graph_box">
        						<div class="graph_background"></div>
        						<div class="graph five"></div>
        					</div>
        					
        					<div class="review_count">${storeVO.fiveScore }</div>
        				</div>
        				
        				<div> 
        					<div>4점</div>
        					<div class="graph_box">
        						<div class="graph_background"></div>
        						<div class="graph four"></div>
        					</div>
        					<div class="review_count">${storeVO.fourScore }</div>
        				</div>
        				
        				<div> 
        					<div>3점</div>
        					<div class="graph_box">
        						<div class="graph_background"></div>
        						<div class="graph three"></div>
        					</div>
        					<div class="review_count">${storeVO.threeScore }</div>
        				</div>
        				
        				<div> 
        					<div>2점</div>
        					<div class="graph_box">
        						<div class="graph_background"></div>
        						<div class="graph two"></div>
        					</div>
        					<div class="review_count">${storeVO.twoScore }</div>
        				</div>
        				
        				<div> 
        					<div>1점</div>
        					<div class="graph_box">
        						<div class="graph_background"></div>
        						<div class="graph one"></div>
        					</div>
        					<div class="review_count">${storeVO.oneScore }</div>
        				</div>
        				
        				
        			</div>
        		
        		</div>
        	</li>
        
        
        
        	<c:forEach items="${reviewList }" var="reviewList">
            <li>
            	<div class="client">
            		
            		<div class="review_header">
            			<div>
			                <div class="nickname">${reviewList.nickname }</div>
			                <div>
			                	<span><c:set var="score" value="${Math.round(reviewList.score)  }" /> </span>
			                	
				                	<c:forEach begin="0" end="4" var="i">
				                		<c:if test="${i < score }">
					                		<i class="fas far fa-star"></i>
				                		</c:if>
				                		
				                		<c:if test="${i >= score }">
					                		<i class="far fa-star"></i>
				                		</c:if>
				                	</c:forEach>
			                	
			                	<span><fm:formatDate value="${reviewList.regiDate }" pattern="yyyy-MM-dd" /> </span>
			                </div>
	                	</div>
	                	
		                 <div>
		                
		                	<c:if test="${!empty reviewList.bossComment}">
		                		<button class="reply_btn reply " >댓글 수정하기</button>
		                	</c:if>
		                	
		                	<c:if test="${empty reviewList.bossComment}">
		                		<button class="reply_btn reply " >댓글 달기</button>
		                	</c:if>
		                	
		                	
		                	<input type="hidden" value="${reviewList.orderNum }" class="order_num">
		                </div>
	                </div> 
		                
		                
	                <div>
		                <c:if test="${!empty reviewList.reviewImg }">
		                	<div><img src="${reviewList.reviewImg }" alt="이미지" class="review_img"></div>
		                </c:if>
	                
	                	<div>${reviewList.content } </div>
	                
	                </div>
	                
                </div>
                
                <c:if test="${!empty reviewList.bossComment }">	
                <div class="boss">
                	<div class="nickname">사장님</div>
                	<div class="boss_content">${reviewList.bossComment }</div>
                </div>
                </c:if>
                
                <div class="temp"></div>
                
                 <div class="boss input">
        		 	<div class="nickname">사장님</div>
        			<div class='boss_content'>
        				<textarea class="comment_area" spellcheck="false"></textarea>
        			</div>
        			
        			<div>
        				<button class="boss_reply reply" >댓글 달기</button>
        				<input type="hidden" value="${reviewList.orderNum }" class="order_num">
        			</div>
       			</div>
                
                
            </li>
		</c:forEach>

        </ul>
	<!-- ----------------------------------- 리뷰 탭 ------------------------------------- -->
	
	
	</main>
</div>






	
	<input type="hidden" value="${storeVO.storeNum }" class="store_num">
	<input type="hidden" value="${storeVO.storeName }" class="store_name">
    <input type="hidden" value="${storeVO.goodCheck }" class="dibs_check">
	<input type="hidden" value="${userId  }" class="user_id">
	<input type="hidden" value="${storeVO.deleveryTip }" class="delevery_tip">
	<input type="hidden" value="${storeVO.minDelevery }" class="min_delevery">
	<input type="hidden" value="${storeVO.cate }" class="category"> 
	<input type="hidden" value="${storeVO.busiHours1 }" class="business_hour1"> 
	<input type="hidden" value="${storeVO.busiHours2 }" class="business_hour2"> 
	<input type="hidden" value="${storeVO.storeAddress2 }" class="store_address">
	<input type="hidden" value="${address.address2 }" class="user_address">
	
	<input type="hidden" value="${storeVO.reviewCount }" id="review_count">
	<input type="hidden" value="${storeVO.fiveScore }" id="five_score">
	<input type="hidden" value="${storeVO.fourScore }" id="four_score">
	<input type="hidden" value="${storeVO.threeScore }" id="three_score">
	<input type="hidden" value="${storeVO.twoScore }" id="two_score">
	<input type="hidden" value="${storeVO.oneScore }" id="one_score">
	<input type="hidden" value="${storeVO.score }" id="score">

	
    <script>
	var storeAddress = $(".store_address").val();
	var storeName = $(".store_name").val();
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	
	mapOption = {
	    center: new kakao.maps.LatLng(33.25110701, 126.570667), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
	};  
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(storeAddress, function(result, status) {
		
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	        
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });
	
	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:3px 0;">' + storeName + '</div>'
	        });
	        infowindow.open(map, marker);
	
	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	        
	        
	        $(".storePosition").click(function(){
	        	map.panTo(coords);  
	        })
	        
	        
	    } 
			 
	});    
	
	
	
	var userAddress = $(".user_address").val();
	
	if(userAddress != "" ) {
		$(".userPosition").css("display" , "inline");
		
	  // 주소로 좌표를 검색합니다
	 	geocoder.addressSearch(userAddress, function(result, status) {
	 		
	 	    // 정상적으로 검색이 완료됐으면 
	 	     if (status === kakao.maps.services.Status.OK) {
	 	
	 	        coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	 	        
	 	        // 결과값으로 받은 위치를 마커로 표시합니다
	 	        var marker = new kakao.maps.Marker({
	 	            map: map,
	 	            position: coords
	 	        });
	 	        
	 	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	 	        var infowindow = new kakao.maps.InfoWindow({
	 	            content: '<div style="width:150px;text-align:center;padding:3px 0;">' + "배달받을위치" + '</div>'
	 	        });
	 	        infowindow.open(map, marker);
	 	        
	 	      	$(".userPosition").click(function(){
		        	map.panTo(coords);  
		        })
	 	    } 
	 	}); 
		
	}


    </script>


   
 

 