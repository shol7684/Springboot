<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">



<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>



<style type="text/css">
        body { margin: 0;}
        
        .container {
            /* transition: all 0.5s; */
        }
        .box {
            /* 아래 두줄은 제이쿼리 부분에서 box에 css 주는거 없을때 필요  */
            /* float: left;
            width: calc(100% / 4); */
            height: 100vh;

            font-size: 100px;
            line-height: 100vh;
            text-align: center;

            border: 10px solid;
            box-sizing: border-box;

        }
</style>


</head>
<body>

	    <div class="container">
        <div class="box">box0</div>
        <div class="box">box1</div>
        <div class="box">box2</div>
        <div class="box">box3</div>
    </div>
	
	
	
	
	
	<script type="text/javascript">
		/* 	$("body").on('scroll touchmove mousewheel', function(e){
	
			e.preventDefault();
			
			e.stopPropagation(); 
			
			return false;
			
			}, {passive: false}) */
			
		$("div").on("mouseover" , function() {
			console.log("안");
			
			/* $("body").css("overflow" , "hidden"); */
			
			
			
			
			
		})



//codediver.tistory.com/46 [코드 다이버]
		/* $("div").on("touchstart", function() {
			$("div").css("transition", "0.2s").css("width", "300px");

		})

		$("div").on("touchend", function() {
			$("div").css("transition", "0.2s").css("width", "200px");

		}) */

		$("span").click(function() {
			const index = $(this).parent().index();


			$("input[type=checkbox]").eq(index).prop("checked", true);
			console.log($(this).siblings().is(":checked"));
		});
	
	 $(document).ready(function(){
            let d_width = 0; // 브라우저 가로
            let d_height = 0; // 문서 전체의 높이

            function tmp() {
                // container의 가로사이즈(화면가로 * box 개수)
                let con_width = $(window).outerWidth() * $('.box').length; 

                $('.container').css({
                    width: con_width,
                    height: '100vh',
                    position: 'fixed',
                    top: 0,
                    left: 0
                });

                // css에서 해도 상관없다.
                $('.box').css({
                    width: con_width / $('.box').length,
                    height: '100vh',
                    float: 'left'
                });

                // box들을 위로 끌어올렸기 때문에 body의 높이는 100vh나 마찬가지인 상태. 
                // 그래서 억지로 전체 box들의 세로크기 만큼 body에 줘야한다.(스크롤 내리기위함) 
                // 이때 높이는 가로영역의 비율과 동일하게 준다. (이후 리미트를 주게 됨으로써 비율의 값이 정해진다.)
                $('body').css({
                    height: '100vh'
                });

                let w_width = $(window).width(); // 화면의 가로값
                let w_height = $(window).height() // 화면의 세로값

                // 스크롤 될때의 리미트
                d_width = con_width - w_width; // 전체 가로값 - 현재 화면의 가로값
                d_height = $('body').height() - w_height // 전체 세로값 - 현재 화면의 세로값
            }

            tmp();
 
            let array = [];
            for(let i=0; i<$('.box').length; i++) {
                array[i] = $('.box').eq(i).offset().left
            }

            let chk = true;
            $('.box').on('mousewheel DOMMouseScroll', function(){

                if(chk) {
                    // 휠 일정시간동안 막기

                    // 휠 방향 감지(아래: -120, 위: 120)
                    let w_delta = event.wheelDelta / 120;
                    
                    // 휠 아래로
                    if(w_delta < 0 && $(this).next().length > 0) {
                        $('.container').animate({
                            left: -array[$(this).index()+1]
                        }, 0)
                    }
                    // 휠 위로
                    else if(w_delta > 0 && $(this).prev().length > 0) {
                        $('.container').animate({
                            left: -array[$(this).index()-1]
                        }, 0)
                    }
                }
            });

			//브라우저를 resize했을시를 대비해 박스의 크기는 다시 구해준다.
            $(window).resize(function(){
                for(let i=0; i<$('.box').length; i++) {
                    array[i] = $('.box').eq(i).offset().left
                }

                tmp();
            })

        });
    </script>

</body>
</html>