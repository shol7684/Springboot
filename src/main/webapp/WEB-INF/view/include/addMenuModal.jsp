<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!------------------- 메뉴 추가 모달 --------------->
    <div class="add_menu_modal register modal" >
		<form action="/addMenu" method="post" enctype="multipart/form-data">
		
		<div id="modal_header">
	    	<h1>메뉴 추가</h1>
			<button class="closeA" type="button">×</button>
		</div>
        	<div class="modal_box modal2">
    
			    <input type="hidden" value="${storeVO.storeNum }" name="storeNum">
			    <input type="hidden" value="${storeVO.storeName }" name="storeName">
			    
	            
	             <ul>
	                <li>
	                    <label for="menu_name">
	                        <h2>메뉴 이름</h2>
	                    </label>
	                    <div class="input_area"><input type="text" id="menu_name" name="menuName" required ></div>
	    			</li>
	                <li>
	                    <label for="menu_price">
	                        <h2>메뉴 가격</h2>
	                    </label>
	                    <div class="input_area"><input type="number" pattern="\d*" id="menu_price" name="menuPrice" required></div>
	                </li>
	
	      
	                <li class="option_box">
		                <div>
	                        <h2>메뉴 옵션</h2>
	                        <button type="button" class="add_option">+ 옵션 추가</button>
	                     </div>
	                        
                        <div class="option">
                        	<div>
	                        	<span>옵션 1 </span>
	                        	<input type="text" name="optionName">
                        	</div>
                        	<div>
	                        	<span>가격</span>
	                        	<input type="number" pattern="\d*" name="optionPrice"  >
                        	</div> 
	                    </div>
	                     <div id="option_box">   
	                        
	                    </div>
	                    
	                </li>
	   
	    
	                <li>
	                    <label for="menu_dec">
	                        <h2>메뉴 소개</h2>
	                    </label>
	                    <div class="input_area"><input type="text" id="menu_dec" name="menuDec"></div>
	                </li>
	    
	                <li>
	                    <label for="img">
	                        <h2>메뉴 이미지</h2>
	                    </label>
				    	
				    	<div id="img_area">
				    		<input type="file" class="img" name="img">
				    		
			                <div class="img_box">
				    			<img class="preview">
				    			<button type="button" class="img_close"> x</button>
					    	</div>
	                    </div>
	                    
	                </li>
	        
	            </ul>
	    
	        </div> 
	    
			<div class="btn_box">
				<button class="closeB" type="button">취소</button>
				<input type="submit" value="추가">
			</div>
	    </form>
    </div>
     <!-------------------  메뉴 추가 모달 --------------->
     
    <!------------------- 메뉴 수정 모달 --------------->
    <div class="add_menu_modal modify modal" >
		<form action="/modifyMenu" method="post" enctype="multipart/form-data">
		
		<div id="modal_header">
	    	<h1>메뉴 수정</h1>
			<button class="closeA" type="button">×</button>
		</div>
        	<div class="modal_box modal2">
    
			    <input type="hidden" value="${storeVO.storeNum }" name="storeNum">
			    <input type="hidden" value="${storeVO.storeName }" name="storeName">
			    <input type="hidden" value="" name="menuNum">
			    <input type="hidden" value="" name="menuImg">
			    <input type="hidden" value="" name="menuThumb">
			    
			    
	            
	             <ul>
	                <li>
	                    <label for="menu_name">
	                        <h2>메뉴 이름</h2>
	                    </label>
	                    <div class="input_area"><input type="text" value="1" id="menu_name" name="menuName" required></div>
	    			</li>
	                <li>
	                    <label for="menu_price">
	                        <h2>메뉴 가격</h2>
	                    </label>
	                    <div class="input_area"><input type="number" pattern="\d*" id="menu_price" name="menuPrice"></div>
	                </li>
	
	      
	                <li class="option_box">
		                <div>
	                        <h2>메뉴 옵션</h2>
	                        <button type="button" class="add_option">+ 옵션 추가</button>
	                     </div>
                     <div id="option_box">
	                     <div class="option">
                        	<div>
	                        	<span>옵션 1 </span>
	                        	<input type="text" name="optionName">
                        	</div>
                        	<div>
	                        	<span>가격</span>
	                        	<input type="number" pattern="\d*" name="optionPrice">
                        	</div>
	                    </div>
                    </div>
                    
                    
                    
	                    
	                </li>
	   
	    
	                <li>
	                    <label for="menu_dec">
	                        <h2>메뉴 소개</h2>
	                    </label>
	                    <div class="input_area"><input type="text" id="menu_dec" name="menuDec"></div>
	                </li>
	    
	                <li>
	                    <label for="img">
	                        <h2>메뉴 이미지</h2>
	                    </label>
	                    
				    	<div id="img_area">
				    		<input type="file" class="modify_img" name="img">
				    		
			                <div class="img_box">
				    			<img class="preview">
				    			<button type="button" class="img_close"> x</button>
					    	</div>
	                    </div>
	                </li>
	        
	            </ul>
	    
	        </div> 
	    
			<div class="btn_box">
				<button class="closeB" type="button">취소</button>
				<input type="submit" value="수정">
			</div>
	    </form>
    </div>
     <!-------------------  메뉴 수정 모달 --------------->
     <script>


 	
	
    </script> 
     
     
     
     
     
     