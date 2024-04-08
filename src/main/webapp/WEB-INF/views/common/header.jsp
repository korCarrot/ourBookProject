<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />

<script type="text/javascript">
    var loopSearch=true;

    function keywordSearch(){
        if(loopSearch==false)
            return;

        var value=document.frmSearch.searchWord.value;  //검색창에 검색한 내용, 값
        $.ajax({
            type : "get",
            async : true, //false인 경우 동기식으로 처리한다.
            url : "${contextPath}/goods/keywordSearch.do",
            data : {keyword:value},
            success : function(data, textStatus) {
                var jsonInfo = JSON.parse(data);    //문자열 형태로 저장된 JSON 데이터를 JavaScript 객체로 변환
                displayResult(jsonInfo);            // 객체를 displayResult() 함수에 전달하여 결과를 처리
            },
            error : function(data, textStatus) {
                alert("에러가 발생했습니다."+data);
            },
            complete : function(data, textStatus) {
                //alert("작업을완료 했습니다");

            }
        }); //end ajax
    }

    function displayResult(jsonInfo){
        var count = jsonInfo.keyword.length;    //JSON 데이터 개수를 구합니다.
        if(count > 0) {
            var html = '';  //JSON데이터를 차례대로 <a>태그를 이용해 키워드 목록을 만듭니다.
            for(var i in jsonInfo.keyword){
                html += "<a href=\"javascript:select('"+jsonInfo.keyword[i]+"')\">"+jsonInfo.keyword[i]+"</a><br/>";
            }
            var listView = document.getElementById("suggestList");
            listView.innerHTML = html;  //<a>태그로 만든 키워드 목록을 <div> 태그에 차례대로 표시합니다.
            show('suggest');
        }else{
            hide('suggest');
        }
    }

    function select(selectedKeyword) {
        document.frmSearch.searchWord.value=selectedKeyword;
        loopSearch = false;
        hide('suggest');
    }

    function show(elementId) {
        var element = document.getElementById(elementId);
        if(element) {
            element.style.display = 'block';
        }
    }

    function hide(elementId){
        var element = document.getElementById(elementId);
        if(element){
            element.style.display = 'none';
        }
    }

</script>
<body>
<div id="logo">
    <a href="${contextPath}/main/main.do">
        <img width="176" height="80" alt="logo이미지" src="${contextPath}/resources/image/Booktopia_Logo.jpg">
    </a>
</div>

<div id="head_link">
    <ul>
        <c:choose>
            <c:when test="${isLogOn==true and not empty memberInfo }">
                <li><a href="${contextPath}/member/logout.do">로그아웃</a></li>
                <li><a href="${contextPath}/mypage/myPageMain.do">마이페이지</a></li>
                <li><a href="${contextPath}/cart/myCartList.do">장바구니</a></li>
                <li><a href="#">주문배송</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${contextPath}/member/loginForm.do">로그인</a></li>
                <li><a href="${contextPath}/member/memberForm.do">회원가입</a></li>
            </c:otherwise>
        </c:choose>
        <li><a href="#">고객센터</a></li>
        <c:if test="${isLogOn==true and memberInfo.member_id =='admin' }">
            <li class="no_line"><a href="${contextPath}/admin/goods/adminGoodsMain.do">관리자</a></li>
        </c:if>

    </ul>
</div>
<br>

<div id="search" >
    <form name="frmSearch" action="${contextPath}/goods/searchGoods.do" >
        <%--onKeyUp : 사용자가 키보드의 키를 누른 다음 해당 키를 놓았을 때 발생하는 이벤트를 처리 / 타이핑 후 관련 검색어 나타나는 기능 --%>
        <input name="searchWord" class="main_input" type="text"  onKeyUp="keywordSearch()">
        <input type="submit" name="search" class="btn1"  value="검 색" >
    </form>
</div>

<div id="suggest">
    <div id="suggestList"></div>
</div>
</body>
</html>