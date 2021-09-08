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
    <link rel="stylesheet" href="/css/admin.css" >
    <link rel="stylesheet" href="/css/modal.css" >
    
    <link rel="stylesheet" href="/css/header.css" >
    <link rel="stylesheet" href="/css/footer.css" >
    
    <link rel="stylesheet" href="/css/storeRegiModal.css" >
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
    <script type="text/javascript" src="/js/imgPreview.js" ></script>
    <script type="text/javascript" src="/js/openModal.js" ></script>
    
    
</head>
<body>
    <!-- 매장 등록 모달창 -->
	<%@ include file="/WEB-INF/view/include/storeRegiModal.jsp" %>
 	<!-- 매장 등록 모달창 -->

 	<!-- -------------------------------------------------------------------------------------------- -->
	<header>
		<%@ include file="/WEB-INF/view/include/header2.jsp" %>
	</header>

	<!-- ------------------------접수모달 ------------------------->
 	<div class="accept_modal modal">
		<div id="modal_header">
			<h1>접수 대기</h1>    
			<button class="closeA">×</button>
		</div>
             
		<div class="accept_modal_box">
			<section>
				<article class="delevery_info">
					<h2>배달 정보</h2>
					<div class="order_cancle"><button>주문거부하기</button></div>
	                <div class="info_area">
	                    <div>
	                        <span>배송지</span>
	                        <span>천안시 서북구 두정동</span>
	                        <span>상세주소 ...................</span>
	                    </div>
		                    
	                    <div>
	                        <span>연락처 010 2885 7684</span>
	                    </div>
	                </div>
	            </article>
	
	            <article class="request">
	                <h2>요청사항</h2>
	                <div>
	                    <textarea>수저 필요없어요</textarea>
	                </div>    
	            </article>
	            <article class="menu">
	                <h2>주문 상세</h2>
	                <ul>
	                    <li>
	                    </li>
	                    
	                </ul>    
	            </article>
	        </section>
		</div>
	        
	    <div class="btn_box">
	        <button class="cancle">취소</button>
	        <button class="accept_next">주문 접수</button>
	    </div>
</div>

<!-- ------------------------접수모달 ------------------------->

	<div class="order_cancle_modal modal">
		<div id="modal_header">
			<h1>주문거부</h1>
			<button class="closeA">×</button>
		</div>
		
		<div class="modal_box">
			<h2>주문거부 사유를 선택해주세요</h2>
			<ul>
				<li data-value = "배달불가지역" >배달불가지역</li>
				<li data-value = "재료소진" >재료소진</li>
			</ul>
			<ul>
				<li data-value = "배달지연" >배달지연</li>
				<li data-value = "기타" >기타</li>
			</ul>
		</div>
		
		<div class="btn_box">
	        <button class="cancle">취소</button>
	        <button class="order_cancle_btn">거부하기</button>
	    </div>
	</div>




<!-- ------------------------ 배달시간 ------------------------->
    <div class="delevery_timer modal"> 
    	<div id="modal_header">
	        <h1>접수 대기</h1>
	        <button class="closeA">×</button>
        </div>
             
        <section>
            <article>
                <h2>배달 시간을 선택해주세요</h2>
            </article>

            <article>
                <ul>
                    <li data-value = 20>20분</li>
                    <li data-value = 30 id="select">30분</li>
                    <li data-value = 40>40분</li>
                    <li data-value = 50>50분</li>
                    <li data-value = 60>60분</li>
                    <li data-value = 90>90분</li>
                </ul>
            </article>

            <article>
                <div>
                    <button class="cancle">취소</button>
                    <button id="accept">접수하기</button>
                </div>
                
            </article>
        </section> 


    </div>

<!-- ------------------------ 배달시간 ------------------------->

<div class="wrap">
<!-- ------------------------ 상단 메뉴 ------------------------->
    <section class="tab">
            <ul class="box">
                <li>주문 접수</li>
                <li>매장 관리</li>
                <li class="sales_check">매출 확인</li>
                <li class="home">홈으로</li>
            </ul>
    </section>

<!-- ------------------------ 상단 메뉴 ------------------------->

<!-- ------------------------ 왼쪽 메뉴 ------------------------->
    <aside>
        <ul class="aside_tab">
            <li class="select2">
            	<div>접수 대기</div>
           	</li>
           	
            <li>처리 중</li>
            <li>완료</li>
            <!-- <li>주문 조회</li> -->
         </ul>
    </aside>
<!-- ------------------------ 왼쪽 메뉴 ------------------------->



    <main>
        <div id="cont_box">
            <ul>
                <!-- 주문접수 > 접수 대기 -->
                <li class="order_list first">
                    <ul>
                        <!-- 주문목록 1개 -->
                        <li class="order_box">
                        </li>
                        <!-- 주문목록 1개 -->
                    </ul>
                </li>
                   <!-- 주문접수 > 접수 대기 -->


                    <!-- 주문접수 > 처리 중  -->
				<li class="order_list second">
					<ul>
                   </ul>
                </li> 
               
                    <!-- 주문접수 > 처리 중 -->

                    <!-- 주문접수 > 완료 -->
				<li class="order_list third">
					<ul>
					</ul>
				</li>
                    <!-- 주문접수 > 완료 -->
							
						
                    <!-- 주문접수 > 주문조회 -->
   
                    <!-- 주문접수 > 주문조회 -->
            </ul>
        </div>
        </main>
<!-- ------------------------ 매장관리 눌렀을대 ------------------------->
        
        <section class="manage">                
		<div class="store">
			<div class="store_reg_btn"><button>매장 등록</button></div><hr>
			<div class="store_list">
				<h1>매장 리스트</h1><hr>
		
				<ul id="store_list">
		       
				<c:forEach items="${storeList }" var="storeList">
					<li>
						<div>
			                <a href="/admin/detail/${storeList.storeNum }">
			                	<span class="store_img_box">
				                    <img src="${storeList.storeImg }" alt="이미지">
			                	</span>
			                
			                    <span class="inf">
									<h2>${storeList.storeName }</h2>
			                        
			                        <span><c:set var="score" value="${Math.round(storeList.score)  }" /> </span>
			                        
				                        <c:forEach begin="0" end="4" var="i">
					                		<c:if test="${i <= score }">
						                		<i class="fas far fa-star"></i>
					                		</c:if>
					                		
					                		<c:if test="${i > score }">
						                		<i class="far fa-star"></i>
					                		</c:if>
					                	</c:forEach><br>
			                        
			                        <span>
			                        	<span>리뷰 ${storeList.reviewCount }</span><span>ㅣ</span><span>사장님 댓글 ${storeList.bossComment }</span>
			                        </span>
			                        
			                        <span>
			                      	 	<span>예상배달시간 ${storeList.deleveryTime }분</span>
			                       		<span>최소주문금액 <fm:formatNumber value="${storeList.minDelevery }" pattern="###,###" />원</span>
			                        </span>
			                       <span>
				                       <span>배달팁 <fm:formatNumber value="${storeList.deleveryTip }" pattern="###,###" />원</span>
				                       <span>영업시간 ${storeList.busiHours1 }시 ~ ${storeList.busiHours2 }시 </span>
			                       </span>
			                       
			                   </span>
			               </a>
		               </div>
		           </li>
		      </c:forEach>
		
		        </ul>
		    </div>
		</div>
	</section>
<!-- ------------------------ 매장관리 눌렀을대 ------------------------->
    
</div>

   	<!-- 푸터 -->
     <footer>
		<%@ include file="/WEB-INF/view/include/footer.jsp" %>
    </footer> 
	<!-- 푸터 -->



 	<script>
 	
$(document).ready(function(){
   	 
	$(".home").click(function(){
		location.href = "/";
    });  //홈으로 이동
        
	const modal = $(".modal");
	
                	 
	// ------------------매장관리 모달---------------------

	const storeRegBtn = $(".store_reg_btn  button");  //매장등록 버튼
	const storeRegModal =  $(".store_reg_modal"); 
	
	let size = $(window).width();
	
	$(window).resize(function(){
		size = $(window).width();
		console.log(size);
	})
	
	storeRegBtn.click(function(){
		
		openModal(storeRegModal,size);
		
	});
	
	  
	// ------------------매장관리 모달---------------------
    
	// ------------------주문 접수하기 모달---------------------
		
		
		// 주문 수락할 주문번호
        let acceptOrderNum = 0;
        
     	// 주문 접수 1번모달
		$(document).on("click", ".accept", function(){ 
			const acceptModal = $(".accept_modal");  
			
			acceptOrderNum = $(this).parent().siblings().find(".order_num").val();
			
			openModal(acceptModal,size);
                         
			let data = {
				orderNum : acceptOrderNum
			};
	                         
		$.ajax({
       		url : "/admin/orderDetail",
       	    type : "post",
       	    data : data,
       	    success : function(result){
       	    	
				const index = result["orderDetailMenu"].length;
				const optionIndex = result["orderDetailOption"].length;
				const userAddress1 = result["orderDetailMenu"][0]["userAddress2"];
				const userPhone = result["orderDetailMenu"][0]["userPhone"];
				let userAddress2 = result["orderDetailMenu"][0]["userAddress3"];
				let request = result["orderDetailMenu"][0]["request"];
				
				if(userAddress2 == null) {
					userAddress2 = "";
				}
				
				if(request == null) {
					request = "";
				}
				
				let menuHtml = "";
				
				for(var i =0;i<index;i++) {
    				const menuName =  result["orderDetailMenu"][i]["menuName"];
    				const menuPrice =  result["orderDetailMenu"][i]["menuPrice"];
    				const amount =  result["orderDetailMenu"][i]["amount"];
    				let option = ""; 
    					
    				
					for(var j=0;j<optionIndex;j++) {
						const optionCartNum = result["orderDetailOption"][j]["cartNum"]; 
						const menuCartNum = result["orderDetailMenu"][i]["cartNum"];
						const menuOption = result["orderDetailOption"][j]["menuOption"]; 
						const menuOptionPrice = result["orderDetailOption"][j]["menuOptionPrice"]; 
						
						if(optionCartNum == menuCartNum )	
							option += `<li id="option"><span>ㆍ` + menuOption + " " + menuOptionPrice.toLocaleString() +  `원</span></li>`
							
					}	
    				
					menuHtml +=  `<li><span>` + menuName + " " + menuPrice.toLocaleString() + `원</span><span> (` + amount + `개)</span><br></li>`
                           		 + option;
    			}  
				
				
				  let ht = `<div id="address">
								<span>` + userAddress1 + `</span><br>
								<span>` + userAddress2 + `</span>
			    			</div>
			        		<div>
			           			<span>연락처 ` + userPhone + `</span>
			        		</div>`;
	        		
 				 let ht2 = `<h2>요청사항</h2>
 				 			<div>` + request  + `</div>`;
 				 			
        		$(".accept_modal_box .info_area").html(ht);
 				$(".accept_modal_box .request").html(ht2);
 				$(".accept_modal_box ul").html(menuHtml);
    	
	        		} // success
	        	}); // ajax
 				        	
   
    	}); // accept
    	
    	// 주문 접수 1번모달 주문거부하기
    	$(".order_cancle button").click(function(){
    		const orderCancleModal = $(".order_cancle_modal");
    		
    		openModal(orderCancleModal, size);
    		
   		});

    	// 주문거부 사유 선택
    	$(".order_cancle_modal li").click(function(){
	   		let orderNum = acceptOrderNum; // 주문접수 클릭시 주문번호 가져오기
    		
	   		console.log(orderNum);
   			$(".order_cancle_modal li").removeAttr("id" , "select");
   			$(this).attr("id" , "select");
   			
			let cancleReason = $(".order_cancle_modal").find("#select").data().value;
   			
			
			// 거부하기 클릭
   			$(".order_cancle_btn").off().click(function(){
   				let data = {cancleReason : cancleReason, orderNum : orderNum};
   				
   				
   				orderList("/admin/orderCancle",data,0); 
   				
	   			
   			}) // order_cancle_btn
   			
   		})
    	
   	
   		
   		
    	
    	let time = 0; // 예상 배달시간
    	
    	// 주문 접수 2번모달
		$(".accept_next").click(function(){
	    	const timer = $(".delevery_timer");
			
			openModal(timer, size);
			
			time = $("#select").data()["value"];
		});
    	
    	// 접수 2번모달 시간선택
		$(".delevery_timer li").click(function(){
			$(this).siblings().removeAttr("id" ,"select");
               $(this).attr("id" ,"select");
               time = $(this).data()["value"];
       	});
		
    	// 접수 2번모달에서 주문 수락
    	 $("#accept").click(function(){
     		let data = {
				orderNum : acceptOrderNum,
				time : time,
				index : 0
 		    };
 	    	
     		orderList("/admin/accept",data,0); 
          });
				   
    	
       // 배달 완료하기      		
    	$(document).on("click", ".complete", function(){ 
    		
    		swal("배달 완료후 눌러주세요", {
				  buttons: ["취소", "배달 완료"],
				}).then((value) => {
					
					 if(value == true) {
						 let orderNum = $(this).parent().parent().find(".order_num").val();
				    		
				    		let data = {
									orderNum : orderNum
				        	};
				    		
				    		orderList("/admin/complete",data,index); 
					 } 
				});
			
    	}); // 완료 버튼 클릭
   	// ------------------주문 접수하기 모달---------------------
    	
				    	
   	
   	/* ---------------------- 왼쪽 탭 클릭 ----------------------- */
   	const contBox = $("#cont_box > ul > li ");
   	const store = $(".store");
   	const manage = $(".manage");
   	const aside = $("aside");
	const asideTab = $(".aside_tab > li ");
	let index =0; // aside tab 인덱스
        
    asideTab.click(function(){
		index = $(this).index();
		
		asideTab.removeClass("select2");
		
		$(this).addClass("select2");
		
		contBox.hide();
		contBox.eq(index).show();
		
		clearTimeout(list);
        
		orderList("/admin/orderList",{index : index },index);// 접수대기 주문 목록

       	if(index == 0 ) {
       		refreshOrderList(0);
       	} 
    });
     
       /* ---------------------- 왼쪽 탭 클릭 ----------------------- */
			            
       /* ---------------------- 상단 탭 클릭 ----------------------- */
          
		const tab = $("ul.box > li");  // 상단 탭 쿠키로 현재 탭 기억

          $.ajax({

			url:"/tabCookie",
			type:"post",
			success : function(result){
				console.log("저장쿠키 = " +  result);
				
				if(result == 1) {		// 매장 관리 탭
					store.show();
					manage.show();
					$("#cont_box").hide();
					clearInterval(list);
					
					console.log("매장관리 탭")
					
				} else if(result == 0){				// 주문 접수 탭 
					contBox.show();
					aside.show();
					
					refreshOrderList(0);
					
				}
				
				tab.eq(result).attr("id","select");
			}
		}) // ajax

        
         tab.click(function(){
        	 console.log("list = " + list);
        	 
             let tabNum = $(this).index();

             if(tabNum != 2 && tabNum != 3) { // tabNum 3 = 홈으로 버튼
            	 
	             tab.removeAttr("id" , "#select");
	             $(this).attr("id","select");
	
	             if(tabNum == 0 ) { //주문접수 눌렀을때
	                 $("aside").show();
	                 $("#cont_box").show();
	                 contBox.eq(index).show();
	                 manage.hide();
	                 if(index == 0 ) {
		                 refreshOrderList(0);
	                 }
	                 
	
	             } else if(tabNum == 1 ) { //매장관리 눌렀을때
	                $("#cont_box").hide();
	                manage.show();
	                contBox.hide();
	             
	                 $("aside").hide();
	                 
	                 clearTimeout(list);
	             }
	             
	             let data = {
	             		tabNum : tabNum
	             }
	         	$.ajax({
					url:"/saveTabCookie",
					type:"post",
					data : data,
					success : function(){
					}
				})
             }

         });
		
		$(".sales_check").click(function(){
			location.href = "/admin/sales"
		})
	/* ----------------------------- 상단 탭 클릭 ---------------------------- */
				    	
	
	/* -------------------------- 매장 등록 이미지 미리보기---------------------- */		
         
    	imgPreview(document.querySelector("#img"));
    	
	/* -------------------------- 매장 등록 이미지 미리보기---------------------- */ 
         
         
	/* -------------------------------- 함수 -------------------------------- */
           	
   	function orderList(url,data,index) {
   		const targetArr = [".order_list.first ul" , ".order_list.second ul" , ".order_list.third ul"];	 
        const btnNameArr = ["접수하기" , "완료" , ""];
        const btnClassArr = ["accept" , "complete" , "end" ];
        
        let target = targetArr[index];
        let btnName = btnNameArr[index];
        let btnClass = btnClassArr[index];
        
		$.ajax({
    		url : url,
    	    type : "post",
    	    data : data,
    	    success : function(result){
	 			
 			
				let ht = ""; // 메뉴리스트 html
				let next = "";  // 다음번 메뉴리스트의 주문번호 
				let total = 0;
	 			const length = result["orderList"].length;
				
 				for(var i =0;i<length;i++) {
 					
 					const date = new Date(result["orderList"][i]["orderDate"]);
 					const acceptTime = result["orderList"][i]['acceptTime'];
 					const year = moment(acceptTime).format("YYYY"); 
 					const month = moment(acceptTime).format("MM"); 
 					const day = moment(acceptTime).format("DD"); 
 					const hour = moment(acceptTime).format("HH"); 
 					const minute = moment(acceptTime).format("mm"); 
 					
					console.log("주문 접수 시간" + year + "년 "  + month + "월 " + day + "일 " + hour +  "시"  + minute + "분" );
    			
					const deleveryTime = result["orderList"][i]["deleveryTime"];
					const today = new Date();
					const setTime = new Date(year ,month -1, day, hour, minute);
					const time = deleveryTime - (( today.getTime() -setTime.getTime()) / 1000 / 60 ) ;
					
					if(index == 1 ){
					    if(time < 0) {
							btnName = "완료";
						} else {
							console.log(time);
							btnName = time.toFixed(0) + "분"; // 배달 예상시간 실시간 표시
						} 
					}
			
					total = result["orderList"][i]["menuPrice"];
					amount = result["orderList"][i]["amount"];
					
					let orderNum = result["orderList"][i]["orderNum"]; // 메뉴의 주문번호
					let cartNum = result["orderList"][i]["cartNum"]; // 메뉴의 카트번호
					let optionName = []; // 메뉴의 옵션 목록담을 배열
		
					let optionOrderNum = "";
					let optionCartNum = ""; // 같은 주문번호일때 메뉴 구분
					
			
					if(result["orderListOption"] != null) {
						
						for(var j = 0;j<result["orderListOption"].length;j++) {
							optionOrderNum = result["orderListOption"][j]["orderNum"]; // 옵션의 주문번호
							optionCartNum = result["orderListOption"][j]["cartNum"];	// 옵션의 카트번호
						
							if(orderNum == optionOrderNum ) { // 메뉴 주문번호 == 옵션 주문번호일때
								if(optionName[optionCartNum] == null) { 
									optionName[optionCartNum] = "";
								}
								// 옵션[카트번호]에 옵션들을 담는다
								optionName[optionCartNum] += result["orderListOption"][j]["menuOption"] + ",";
								
							}
						} 
					}
					
					
			
					  if(optionName != "") { 
							console.log("optionName = " + optionName);
							console.log("optionName = " + !optionName[0]);
						for(var j =0;j<optionName.length;j++) {
							
							if(!optionName[0]) continue;
							
							if(optionName[j].endsWith(",")) {  // 옵션이름이 , 로 끝나면
								optionName[j] = optionName[j].substring(0, optionName[j].length-1); 
							}
							
						}
					 }   
				/* 	console.log(typeof(optionName) );
					 if(optionName.endsWith(",")) {
						 console.log(3323323);
						 
					 } */
					  
					
					let menu = [];
					let menuCount = 0;
					
					for(var j =0; j<length;j++) {
						cartNum = result["orderList"][j]["cartNum"];
						
						if(orderNum == result["orderList"][j]["orderNum"]) {
							if(optionName[cartNum] != null && optionName[cartNum] != "" ) {
								menu.push(result["orderList"][j]["menuName"] + "[" + optionName[cartNum] + "]" );
							} else {
								menu.push(result["orderList"][j]["menuName"] );
							}
							menuCount ++;
						}
					}
			
			
					if(next != result["orderList"][i]["orderNum"] ) {
								
						ht += `<li class="order_box">
									<div class="time">
						    			<div>\${moment(date).format("MM월 DD일") }</div>
							    		<div>\${moment(date).format("hh:mm") }</div> 
						    		</div>
					   	
						    		<div class="info">
		                          		<div style="font-weight: bold;">
		                       			<span>
		                      				<span>[메뉴 \${menuCount }개] \${result["orderList"][i]["total"] }원</span> 
		                      				<span class="payMethod"> \${result["orderList"][i]['payMethod'] }</span>
		                    			</span>
		                       		</div>
					                        		
		                       		<div style="font-weight: bold;">\${menu }</div>
		                       		<div style="font-weight: bold;">\${result["orderList"][i]["userAddress2"] }</div>
		                       		
		                       		<div>\${result["orderList"][i]['storeName'] }</div>
		                          		<input type="hidden" value="\${result["orderList"][i]['orderNum'] }" class="order_num" name="orderNum" >
				                  	</div>
					                 			
					                <div class="button_box">
					                 	<input type="button" value="\${btnName }" class="\${btnClass } btn">
					                 </div>
								</li>`;
		 						
		 					next = result["orderList"][i]["orderNum"];
		 					
					}
						
 				} // for i
 	           $(target).html( ht );
    		} // success
    	}); // ajax
	}; // orderList

	
	
	let list = null; 
	// 접수대기 목록 새로고침	
	function refreshOrderList(index){
        setTimeout(function refresh(){
        	orderList("/admin/orderList",{index : index },0);// 접수대기 주문 목록
        	list = setTimeout(refresh , 5000);
        	console.log("list1 = " + list);
        },0);
	}
	
	
	
		    
		$(".closeB").click(function(){
			closeModal(size);
	    });
		    
		$(".modal_bg").click(function(){
	   		closeModal(size);	
	    });
		    
	    $(".closeA").click(function(){
			closeModal(size);
	    });
		    
		$("#accept").click(function(){
			closeModal(size);
		});
		
		$(".cancle").click(function(){
			closeModal(size);
		})
                    	      
 	     function closeModal(size){
			
			$(".modal_bg").hide();
 			modal.scrollTop(0);
 			$(".modal2").scrollTop(0);
 	        $(".modal_box").scrollTop(0);
 	        
 	        $(".accept_modal_box").scrollTop(0);  /////
 	        
 	        
 	        $("body").css("overflow" , "visible");
 	        
 	        if(size > 767) {
  	        	modal.css("transition" , "0s").css("top" , "100%");
 	        } else {
  	        	modal.css("transition" , "0.2s").css("top" , "100%");
 	        }
 	        

 	    };
                    	    

                    	
	}); // document.ready
		
	</script>                 


</body>
</html>