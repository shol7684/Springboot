<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="modal_bg" style="display: none;"></div>

<div class="store_reg_modal modal" >
	<div id="modal_header">
		<h1>정보 수정</h1>
		<button class="closeA" type="button">×</button>
	</div>
	<form action="/storeModify" method="post" enctype="multipart/form-data">
	    <div class="modal_box modal2">
	     
			<input type="hidden" value="${storeVO.storeNum }" name="storeNum">
	     
	         <ul>
	            <li>
	                <label for="cate">
	                    <h2>카테고리</h2>
	                </label>	
	
	                <div>
	                    <select id="cate" name="cate">
	                        <option value="100">피자</option>
	                        <option value="101">치킨</option>
	                        <option value="102">패스트푸드</option>
	                        <option value="103">분식</option>
	                        <option value="104">카페/디저트</option>
	                        <option value="105">돈까스/일식</option>
	                        <option value="106">중국집</option>
	                        <option value="107">족발/보쌈</option>
	                        <option value="108">야식</option>
	                        <option value="109">한식</option>
	                    </select>
	                </div>
	            </li>
	
	            <li>
	                <label for="storeName" >
	                    <h2>매장 이름</h2>
	                </label>
	                <div><input type="text" id="storeName" name="storeName" value="${storeVO.storeName}" required></div>
	
	            <li class="location">
	                <label for="location">
	                    <h2>매장 위치</h2>
	                </label>
					
	                    <div class="sample">
	                        <input type="button" onclick="sample2_execDaumPostcode()" value="우편번호 찾기" id="sample2_btn" ><br>
	                        <input type="text" value="${storeVO.storeAddress1}" onclick="sample2_execDaumPostcode()" id="sample2_postcode" placeholder="우편번호" readonly name="storeAddress1" required>
	
	                        <input type="text" value="${storeVO.storeAddress2}" onclick="sample2_execDaumPostcode()" id="sample2_address" placeholder="주소" readonly name="storeAddress2" required><br>
	                        <input type="text" value="${storeVO.storeAddress3}" id="sample2_detailAddress" placeholder="상세주소" name="storeAddress3">
	                        <input type="hidden" id="sample2_extraAddress" placeholder="참고항목" readonly>
	
	                       <%@ include file="/WEB-INF/view/include/addressSearch.jsp" %>
	                    </div>
	                            <!-- -------------------------- -->
	
	
	            </li>
	
	            <li>
	                <label for="storePhoneNumber">
	                    <h2>매장 전화번호</h2>
	                </label>
	                <div><input type="number"pattern="\d*"  value="${storeVO.storePhone }" id="storePhoneNumber" name="storePhone" required></div>
	            </li>
	
	            <li class="business_hour">
                    <h2>영업시간</h2>
                    
	                <div>
	                     <span>
	                        <select name="busiHours1" id="business_hour1" required>
	                            <option value="0">0</option>
	                            <option value="1">1</option>
	                            <option value="2">2</option>
	                            <option value="3">3</option>
	                            <option value="4">4</option>
	                            <option value="5">5</option>
	                            <option value="6">6</option>
	                            <option value="7">7</option>
	                            <option value="8">8</option>
	                            <option value="9">9</option>
	                            <option value="10">10</option>
	                            <option value="11">11</option>
	                            <option value="12">12</option>
	                            <option value="13">13</option>
	                            <option value="14">14</option>
	                            <option value="15">15</option>
	                            <option value="16">16</option>
	                            <option value="17">17</option>
	                            <option value="18">18</option>
	                            <option value="19">19</option>
	                            <option value="20">20</option>
	                            <option value="21">21</option>
	                            <option value="22">22</option>
	                            <option value="23">23</option>
	                        </select>
	                    </span>
	                    <span> ~ </span>
	                    <span>
	                        <select name="busiHours2" id="business_hour2" required>
                                <option value="0">0</option>
	                            <option value="1">1</option>
	                            <option value="2">2</option>
	                            <option value="3">3</option>
	                            <option value="4">4</option>
	                            <option value="5">5</option>
	                            <option value="6">6</option>
	                            <option value="7">7</option>
	                            <option value="8">8</option>
	                            <option value="9">9</option>
	                            <option value="10">10</option>
	                            <option value="11">11</option>
	                            <option value="12">12</option>
	                            <option value="13">13</option>
	                            <option value="14">14</option>
	                            <option value="15">15</option>
	                            <option value="16">16</option>
	                            <option value="17">17</option>
	                            <option value="18">18</option>
	                            <option value="19">19</option>
	                            <option value="20">20</option>
	                            <option value="21">21</option>
	                            <option value="22">22</option>
	                            <option value="23">23</option>
	                        </select>
	                    </span>
	                </div>
	                
	              
	                
	              
	            </li>
	
	            <li>
	                <label for="minDeleveryPrice">
	                    <h2>최소 배달금액</h2>
	                </label>
	                <div><input type="number" pattern="\d*"  value="${storeVO.minDelevery }" id="minDeleveryPrice" name="minDelevery">원</div>
	            </li>
	            
	            <li>
	                <label for="deleveryTip">
	                    <h2>배달팁</h2>
	                </label>
	                <div><input type="number" pattern="\d*" value="${storeVO.deleveryTip }" id="deleveryTip" name="deleveryTip">원</div>
	            </li>
	            
	            <li>
	                <label for="deleveryTime">
	                    <h2>예상 배달시간</h2>
	                </label>
	                <div><input type="number" pattern="\d*" value="${storeVO.deleveryTime }" id="deleveryTime" name="deleveryTime">분</div>
	            </li>
	            
	             <li>
	                <label for="store_des">
	                    <h2>가게 정보</h2>
	                </label>
	                <!-- <div class="store_des"><input type="" id="store_des"></div> -->
	                <div class="store_des"><textarea id="store_des" name="storeDes" >${storeVO.storeDes }</textarea></div>
	                
	            </li> 
	
	            <li>
                    <h2>매장 이미지 첨부</h2>
                    
                    <div id="img_area">
		                <input type="hidden" name="storeImg" value="${storeVO.storeImg }" >
		                <input type="hidden" name="storeThumb" value="${storeVO.storeThumb }" >
			    		<input type="file" id="modify_img" name="img" >
		                <div class="img_box">
			    			<img class="preview">
			    			<button type="button" class="img_close"> x</button>
				    	</div>
                    </div>
	                
	            </li>
	    
	        </ul>
		</div>
		
        <div class="btn_box">
            <button type="button" class="closeB">취소</button>
            <input type="submit" value="수정">
        </div>
    </form>
</div>
    	<script type="text/javascript">

    	
    	
                
                
         
    	
    	
    	
    	
</script>
