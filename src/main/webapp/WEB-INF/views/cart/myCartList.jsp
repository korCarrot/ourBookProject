<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="myCartList"  value="${cartMap.myCartList}"  />  <%--장바구니 정보--%>
<c:set var="myGoodsList"  value="${cartMap.myGoodsList}"  />    <%--상품 상세 정보--%>

<c:set  var="totalGoodsNum" value="0" />  <!--주문 개수 -->
<c:set  var="totalDeliveryPrice" value="0" /> <!-- 총 배송비 -->
<c:set  var="totalDiscountedPrice" value="0" /> <!-- 총 할인금액 -->

<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        //구매하고자 하는 상품 체크 -> 총 가격 수정
        function calcGoodsPrice(bookPrice,obj){
            var totalPrice,final_total_price,totalNum;
            var goods_qty=document.getElementById("select_goods_qty");
            //alert("총 상품금액"+goods_qty.value);
            var p_totalNum=document.getElementById("p_totalNum");
            var p_totalPrice=document.getElementById("p_totalPrice");
            var p_final_totalPrice=document.getElementById("p_final_totalPrice");
            var h_totalNum=document.getElementById("h_totalNum");
            var h_totalPrice=document.getElementById("h_totalPrice");
            var h_totalDelivery=document.getElementById("h_totalDelivery");
            var h_final_total_price=document.getElementById("h_final_totalPrice");
            if(obj.checked==true){
                //	alert("체크 했음")

                totalNum=Number(h_totalNum.value)+Number(goods_qty.value);
                //alert("totalNum:"+totalNum);
                totalPrice=Number(h_totalPrice.value)+Number(goods_qty.value*bookPrice);
                //alert("totalPrice:"+totalPrice);
                final_total_price=totalPrice+Number(h_totalDelivery.value);
                //alert("final_total_price:"+final_total_price);

            }else{
                //	alert("h_totalNum.value:"+h_totalNum.value);
                totalNum=Number(h_totalNum.value)-Number(goods_qty.value);
                //	alert("totalNum:"+ totalNum);
                totalPrice=Number(h_totalPrice.value)-Number(goods_qty.value)*bookPrice;
                //	alert("totalPrice="+totalPrice);
                final_total_price=totalPrice-Number(h_totalDelivery.value);
                //	alert("final_total_price:"+final_total_price);
            }

            h_totalNum.value=totalNum;

            h_totalPrice.value=totalPrice;
            h_final_total_price.value=final_total_price;

            p_totalNum.innerHTML=totalNum;
            p_totalPrice.innerHTML=totalPrice;
            p_final_totalPrice.innerHTML=final_total_price;
        }

        // 장바구니 상품 개수 수정
        function modify_cart_qty(goods_id,bookPrice,index){
            //alert(index);
            var length=document.frm_order_all_cart.cart_goods_qty.length;
            var _cart_goods_qty=0;
            if(length>1){ //카트에 제품이 한개인 경우와 여러개인 경우 나누어서 처리한다.
                _cart_goods_qty=document.frm_order_all_cart.cart_goods_qty[index].value;
            }else{
                _cart_goods_qty=document.frm_order_all_cart.cart_goods_qty.value;
            }

            var cart_goods_qty=Number(_cart_goods_qty);
            //alert("cart_goods_qty:"+cart_goods_qty);
            //console.log(cart_goods_qty);
            $.ajax({
                type : "post",
                async : false, //false인 경우 동기식으로 처리한다.
                url : "${contextPath}/cart/modifyCartQty.do",
                data : {
                    goods_id:goods_id,
                    cart_goods_qty:cart_goods_qty
                },

                success : function(data, textStatus) {
                    //alert(data);
                    if(data.trim()=='modify_success'){
                        alert("수량을 변경했습니다!!");
                    }else{
                        alert("다시 시도해 주세요!!");
                    }

                },
                error : function(data, textStatus) {
                    alert("에러가 발생했습니다."+data);
                },
                complete : function(data, textStatus) {
                    //alert("작업을완료 했습니다");

                }
            }); //end ajax
        }

        function delete_cart_goods(cart_id){
            var cart_id=Number(cart_id);    //장바구니 번호
            var formObj=document.createElement("form");
            var i_cart = document.createElement("input");
            i_cart.name="cart_id";  //속성 설정
            i_cart.value=cart_id;   //장바구니 번호 값

            formObj.appendChild(i_cart);
            document.body.appendChild(formObj);
            formObj.method="post";
            formObj.action="${contextPath}/cart/removeCartGoods.do";
            formObj.submit();
        }

        // 상품 주문하기
        function fn_order_each_goods(goods_id,goods_title,goods_sales_price,fileName){
            var total_price,final_total_price,_goods_qty;
            var cart_goods_qty=document.getElementById("cart_goods_qty");   //장바구니 상품 개수

            _order_goods_qty=cart_goods_qty.value; //장바구니에 담긴 개수 만큼 주문한다.
            var formObj=document.createElement("form");
            var i_goods_id = document.createElement("input");
            var i_goods_title = document.createElement("input");
            var i_goods_sales_price=document.createElement("input");
            var i_fileName=document.createElement("input");
            var i_order_goods_qty=document.createElement("input");

            i_goods_id.name="goods_id";
            i_goods_title.name="goods_title";
            i_goods_sales_price.name="goods_sales_price";
            i_fileName.name="goods_fileName";
            i_order_goods_qty.name="order_goods_qty";

            i_goods_id.value=goods_id;
            i_order_goods_qty.value=_order_goods_qty;
            i_goods_title.value=goods_title;
            i_goods_sales_price.value=goods_sales_price;
            i_fileName.value=fileName;

            formObj.appendChild(i_goods_id);
            formObj.appendChild(i_goods_title);
            formObj.appendChild(i_goods_sales_price);
            formObj.appendChild(i_fileName);
            formObj.appendChild(i_order_goods_qty);

            document.body.appendChild(formObj);
            formObj.method="post";
            formObj.action="${contextPath}/order/orderEachGoods.do";
            formObj.submit();
        }

        function fn_order_all_cart_goods(){
//	alert("모두 주문하기");
            var order_goods_qty;
            var order_goods_id;
            var objForm=document.frm_order_all_cart;
            var cart_goods_qty=objForm.cart_goods_qty;
            var h_order_each_goods_qty=objForm.h_order_each_goods_qty;
            var checked_goods=objForm.checked_goods;    //상품 주문 여부를 체크하는 체크박스 객체를 가져옴
            var length=checked_goods.length;            //주문용으로 선택한 총 상품 개수를 가져옴


            //alert(length);
            if(length>1){
                for(var i=0; i<length;i++){ //여러 상품 주문시 하나의 상품에 대해 '상품번호:주문수량' 문자열을 만든 후 전체 상품 정보를 배열로 전송
                    if(checked_goods[i].checked==true){
                        order_goods_id=checked_goods[i].value;
                        order_goods_qty=cart_goods_qty[i].value;
                        cart_goods_qty[i].value="";
                        cart_goods_qty[i].value=order_goods_id+":"+order_goods_qty;
                        //alert(select_goods_qty[i].value);
                        console.log(cart_goods_qty[i].value);
                    }
                }
            }else{  //상품을 하나만 주문할 경우 문자열로 전송
                order_goods_id=checked_goods.value;
                order_goods_qty=cart_goods_qty.value;
                cart_goods_qty.value=order_goods_id+":"+order_goods_qty;
                //alert(select_goods_qty.value);
            }

            objForm.method="post";
            objForm.action="${contextPath}/order/orderAllCartGoods.do";
            objForm.submit();
        }

    </script>
</head>
<body>
<table class="list_view" align="center">
    <tr style="background:#33ff00" >
        <td class="fixed" >구분</td>
        <td colspan=2 class="fixed">상품명</td>
        <td>정가</td>
        <td>판매가</td>
        <td>수량</td>
        <td>합계</td>
        <td>주문</td>
    </tr>
<c:choose>
    <c:when test="${ empty myCartList }">   <%--장바구니 확인--%>
        <tr>
            <td colspan=8 class="fixed">    <%--상품명이 colspan=2라서 총 colspan=8--%>
                <strong>장바구니에 상품이 없습니다.</strong>
            </td>
        </tr>
    </c:when>
    <c:otherwise>   <%--장바구니에 상품이 있다면--%>
<%--   cnt.count-1은 JSTL의 <c:forEach> 태그에서 현재 반복되고 있는 항목의 인덱스를 나타냅니다.
일반적으로 컬렉션의 인덱스는 0부터 시작합니다. 그러나 cnt.count는 1부터 시작합니다. 따라서 cnt.count-1을 사용하여 실제 컬렉션의 인덱스에 접근합니다.     --%>
        <tr>
        <form name="frm_order_all_cart">
        <c:forEach var="item" items="${myGoodsList }" varStatus="cnt">  <%--상품 상세 정보 - 장바구니 내 상품이 1개 이상--%>
            <c:set var="cart_goods_qty" value="${myCartList[cnt.count-1].cart_goods_qty}" />
            <c:set var="cart_id" value="${myCartList[cnt.count-1].cart_id}" />
            <td><input type="checkbox" name="checked_goods"  checked  value="${item.goods_id }"  onClick="calcGoodsPrice(${item.goods_sales_price },this)"></td>
            <td class="goods_image">
                <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
                    <img width="75" alt="" src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.fileName}"  />
                </a>
            </td>
            <td>
                <h2>
                    <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">${item.goods_title }</a>
                </h2>
            </td>
            <td class="price"><span>${item.goods_price }원</span></td>
            <td>
                <strong>
                    <fmt:formatNumber  value="${item.goods_sales_price*0.9}" type="number" var="discounted_price" />
                        ${discounted_price}원(10%할인)
                </strong>
            </td>   <%--modify_cart_qty 매개변수에 Expression expected가 나서 ''를 넣어줬음--%>
            <td>    <%--size=3는 입력 필드의 너비를 설정합니다. 여기서는 3개의 문자를 표시할 수 있도록 합니다. 입력 필드의 실제 너비를 지정하는 것이 아니라, 보이는 글자 수를 제어--%>
                <input type="text" id="cart_goods_qty" name="cart_goods_qty" size=3 value="${cart_goods_qty}"><br>
                <a href="javascript:modify_cart_qty('${item.goods_id }','${item.goods_sales_price*0.9 }','${cnt.count-1 }');" >
                    <img width=25 alt=""  src="${contextPath}/resources/image/btn_modify_qty.jpg">
                </a>
            </td>
            <td>
                <strong>
                    <fmt:formatNumber  value="${item.goods_sales_price*0.9*cart_goods_qty}" type="number" var="total_sales_price" />
                        ${total_sales_price}원
                </strong> </td>
            <td>
                <%--  상품 주문 --%>
                <a href="javascript:fn_order_each_goods('${item.goods_id }','${item.goods_title }','${item.goods_sales_price}','${item.fileName}');">
                    <img width="75" alt=""  src="${contextPath}/resources/image/btn_order.jpg">
                </a><br>
                <%-- 나중에 주문--%>
                <a href="#">
                    <img width="75" alt=""
                         src="${contextPath}/resources/image/btn_order_later.jpg">
                </a><br>
                    <%-- 리스트에 넣기 --%>
                    <a href="#">
                    <img width="75" alt=""
                         src="${contextPath}/resources/image/btn_add_list.jpg">
                </A><br>
                <%-- 장바구니에서 삭제하기 --%>
                <a href="javascript:delete_cart_goods('${cart_id}');">
                <img width="75" alt=""
                     src="${contextPath}/resources/image/btn_delete.jpg">
                </a>
            </td>
            </tr>
            <c:set  var="totalGoodsPrice" value="${totalGoodsPrice+item.goods_sales_price*0.9*cart_goods_qty }" />
            <c:set  var="totalGoodsNum" value="${totalGoodsNum+1 }" />
        </c:forEach>
<div class="clear"></div>
</c:otherwise>
</c:choose>
</table><!-- 테이블이 끝나는 위치가 이상함. c:choose 뒤에 끝나야 할텐데 앞에 있어서 이동시킴-->
<br>
<br>
<table  width=80%   class="list_view" style="background:#cacaff">

    <tr  align=center  class="fixed" >
        <td class="fixed">총 상품수 </td>
        <td>총 상품금액</td>
        <td>  </td>
        <td>총 배송비</td>
        <td>  </td>
        <td>총 할인 금액 </td>
        <td>  </td>
        <td>최종 결제금액</td>
    </tr>
    <tr cellpadding=40  align=center >
        <td id="">
            <p id="p_totalGoodsNum">${totalGoodsNum}개 </p>
            <input id="h_totalGoodsNum"type="hidden" value="${totalGoodsNum}"  />
        </td>
        <td>
            <p id="p_totalGoodsPrice">
                <fmt:formatNumber  value="${totalGoodsPrice}" type="number" var="total_goods_price" />
                ${total_goods_price}원
            </p>
            <input id="h_totalGoodsPrice"type="hidden" value="${totalGoodsPrice}" />
        </td>
        <td>
            <img width="25" alt="" src="${contextPath}/resources/image/plus.jpg">
        </td>
        <td>
            <p id="p_totalDeliveryPrice">${totalDeliveryPrice }원  </p>
            <input id="h_totalDeliveryPrice"type="hidden" value="${totalDeliveryPrice}" />
        </td>
        <td>
            <img width="25" alt="" src="${contextPath}/resources/image/minus.jpg">
        </td>
        <td>
            <p id="p_totalSalesPrice">
                ${totalDiscountedPrice}원
            </p>
            <input id="h_totalSalesPrice"type="hidden" value="${totalSalesPrice}" />
        </td>
        <td>
            <img width="25" alt="" src="${contextPath}/resources/image/equal.jpg">
        </td>
        <td>
            <p id="p_final_totalPrice">
                <fmt:formatNumber  value="${totalGoodsPrice+totalDeliveryPrice-totalDiscountedPrice}" type="number" var="total_price" />
                ${total_price}원
            </p>
            <input id="h_final_totalPrice" type="hidden" value="${totalGoodsPrice+totalDeliveryPrice-totalDiscountedPrice}" />
        </td>
    </tr>

</table>
<center>
    <br><br>
    <a href="javascript:fn_order_all_cart_goods()">
        <img width="75" alt="" src="${contextPath}/resources/image/btn_order_final.jpg">
    </a>
    <a href="#">
        <img width="75" alt="" src="${contextPath}/resources/image/btn_shoping_continue.jpg">
    </a>
</center>

</body>
</html>
