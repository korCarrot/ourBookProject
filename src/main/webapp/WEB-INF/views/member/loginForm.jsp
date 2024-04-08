<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<%-- http-equiv="Content-Type": 이 속성은 HTTP 헤더와 동등한 역할을 합니다. 여기서는 브라우저에게 문서의 콘텐츠 유형을 명시적으로 지정하도록 합니다. --%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%--  로그인 실패시  --%>
    <c:if test='${not empty message }'>
        <script>
            window.onload=function()
            {
                result();
            }

            function result(){
                alert("아이디나  비밀번호가 틀립니다. 다시 로그인해주세요");
            }
        </script>
    </c:if>
</head>

<body>
<H3>회원 로그인 창</H3>
<DIV id="detail_table">
    <form action="${contextPath}/member/login.do" method="post">
        <table>
            <tr class="dot_line">
                <td class="fixed_join">아이디</td>
                <td><input name="member_id" type="text" size="20" /></td>
            </tr>
            <tr class="solid_line">
                <td class="fixed_join">비밀번호</td>
                <td><input name="member_pw" type="password" size="20" /></td>
            </tr>
        </table>
        <br><br>
        <INPUT	type="submit" value="로그인">
        <INPUT type="button" value="초기화">

        <br><br>
        <a href="#">아이디 찾기</a>  |
        <a href="#">비밀번호 찾기</a> |
        <a href="${contextPath}/member/memberForm.do">회원가입</a>    |
        <a href="#">고객 센터</a>
    </form>
</DIV>
</body>
</html>