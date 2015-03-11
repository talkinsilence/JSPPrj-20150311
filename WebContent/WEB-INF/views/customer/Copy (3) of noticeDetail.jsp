﻿<%@page import="java.sql.Date"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%-- <%@page import="java.sql.*"%>로 받아도 됨. --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String _code = request.getParameter("c");
System.out.println(_code);

String sql = "SELECT * FROM NOTICES WHERE CODE = "+_code+""; 

String url = "jdbc:oracle:thin:@win.newlecture.com:1521:orcl";
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con = DriverManager.getConnection(url, "HR", "class6");
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(sql);

rs.next();

//Model 마련하기 
/* String title = rs.getString("TITLE");
Date regdate = rs.getDate("REGDATE");
String writer = rs.getString("WRITER");
int hit= rs.getInt("HIT");
String content = rs.getString("CONTENT"); */

pageContext.setAttribute("title", rs.getString("TITLE"));
pageContext.setAttribute("regdate", rs.getDate("REGDATE"));
pageContext.setAttribute("writer", rs.getString("WRITER"));
pageContext.setAttribute("hit", rs.getInt("HIT"));
pageContext.setAttribute("content", rs.getString("CONTENT"));


rs.close();
st.close();
con.close();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="css/bind.css" rel="stylesheet" type="text/css" />
    <link href="css/noticeDetail.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="modernizr.js"></script>
</head>
<body>
    <header id="header">
        <div class="content-wrapper">
            <h1 id="logo"><a href=""><img src="../images/logo.png" alt="뉴렉처 온라인" /></a></h1>
            <section class="header-item-container">
                <h1 class="hidden">머리말</h1>
                <nav id="main-menu">
                    <h1 class="hidden">메인메뉴</h1>
                    <ul class="clearfix">
                        <li class="main-menu-item"><a class="main-menu-item-text" href="">학습가이드</a></li>
                        <li class="main-menu-item"><a class="main-menu-item-text" href="">뉴렉과정</a></li>
                        <li class="main-menu-item"><a class="main-menu-item-text" href="">강좌선택</a></li>
                    </ul>
                </nav>

                <div id="lecture-search-form">
                    <h3 class="hidden">강좌검색 폼</h3>
                    <form>
                        <fieldset>
                            <legend class="hidden">검색필드</legend>
                            <label id="lecture-search-input-label" for="subject">과정검색</label>
                            <input id="lecture-search-input" class="g1" type="text" name="subject" />
                            <input id="lecture-search-button" type="submit" value="검색" />
                        </fieldset>
                    </form>
                </div>

                <nav id="member-menu">
                    <h1 class="hidden">회원메뉴</h1>
                    <ul class="clearfix">
                        <li class="member-menu-item"><a class="member-menu-item-text" href="../index.html">홈</a></li>
                        <li class="member-menu-item"><a class="member-menu-item-text" href="../joinus/login.html">로그아웃</a></li>
                        <li class="member-menu-item"><a class="member-menu-item-text" href="../joinus/join.html">회원가입</a></li>
                    </ul>
                </nav>

                <nav id="my-menu">
                    <h1 class="hidden">고객센터메뉴</h1>
                    <ul class="clearfix">
                        <li class="my-menu-item"><a class="my-menu-item-text" href=""><img src="../images/txt-mypage.png" alt="마이페이지" /></a></li>
                        <li class="my-menu-item"><a class="my-menu-item-text" href="notice.html"><img src="../images/txt-customer.png" alt="고객센터" /></a></li>
                    </ul>
                </nav>
            </section>
        </div>
    </header>

    <div id="visual">
        <div class="content-wrapper">

        </div>
    </div>

    <div id="body">
        <div class="content-wrapper clearfix">
            <aside id="customer" class="aside-menu-group">
                <h1 class="aside-menu-group-title">고객센터</h1>

                <nav class="aside-main-menu">
                    <h1 class="hidden">고객센터 메뉴</h1>
                    <ul>
                        <li class="aside-menu-item"><a href="">공지사항</a></li>
                        <li class="aside-menu-item"><a href="">1:1고객문의</a></li>
                        <li class="aside-menu-item"><a href="">학습안내</a></li>
                    </ul>
                </nav>

                <nav class="aside-menu">
                    <h1>추천사이트</h1>
                    <ul class="aside-menu-list">
                        <li><img src="../images/answeris.png" alt="앤서이즈" /></li>
                        <li><img src="../images/w3c.png" alt="W3C" /></li>
                        <li><img src="../images/microsoft.png" alt="마이크로소프트" /></li>
                    </ul>
                </nav>

            </aside>

            <main id="main">
                <h2 id="main-title">공지사항</h2>

                <div id="breadcrumb">
                    <h3 class="hidden">현재경로</h3>
                    <ol>
                        <li class="breadcrumb-item">home</li>
                        <li class="breadcrumb-item">고객센터</li>
                        <li class="breadcrumb-item">공지사항</li>
                    </ol>
                </div>

                <!-------------<페이지 컨텐트 영역>-------------------------------------------------------------------->

                <article id="notice-detail" class="space-top-l">
                    <h1 class="hidden">공지사항 내용</h1>
                
                <!--   View에는 최소한의 코드만 둔다. -->
                    <dl>
                        <dt class="detail-cell title newrow">제목</dt>
                        <dd class="detail-cell text-highlight">${title}</dd>
                        <dt class="detail-cell title newrow">작성일</dt>
                        <dd class="detail-cell">${regdate}</dd>
                        <dt class="detail-cell title newrow">작성자</dt>
                        <dd class="detail-cell half-cell">${writer}</dd>
                        <dt class="detail-cell title">조회수</dt>
                        <dd class="detail-cell half-cell">${hit}</dd>
                        <dt class="detail-cell title newrow">첨부파일</dt>
                        <dd class="detail-cell"></dd>
                        <dt class="hidden">내용</dt>
                        <dd class="content newrow">${content}                        
                        </dd>
                    </dl>
                 
                    <p id="button-container" class="space-top text-center">
                        <a id="btn-list" href="notice.html">목록</a>
                    </p>
                </article>
                

                <!-------------</페이지 컨텐트 영역>-------------------------------------------------------------------->

            </main>
        </div>
    </div>

    <footer id="footer">
        <div class="content-wrapper clearfix">
            <div id="logo-footer-container">
                <h2 id="logo-footer"><img src="../images/logo-footer.png" alt="회사정보" /></h2>
            </div>
            <div id="company-info-container">
                <div id="company-info">
                    <h3 class="hidden">소유자정보</h3>
                    <dl class="clearfix">
                        <dt class="company-info-item item-title item-newline">주소</dt>
                        <dd class="company-info-item item-data">서울특별시 동대문구</dd>
                        <dt class="company-info-item item-title item-newline">관리자메일</dt>
                        <dd class="company-info-item item-data">admin@newlecture.com</dd>
                        <dt class="company-info-item item-title">전화번호</dt>
                        <dd class="company-info-item item-data">02-111-0000</dd>
                        <dt class="company-info-item item-title item-newline">상호</dt>
                        <dd class="company-info-item item-data">뉴렉처</dd>
                    </dl>
                </div>

                <div id="copyright">
                    <h3 class="hidden">저작권정보</h3>
                    <p>Copyright@newlecture.com 2013-2015 ....</p>
                </div>
            </div>
        </div>
    </footer>

    <!--<aside id="quick-menu">
        <h1>QUICK MENU</h1>
        <nav>
            <h1>자주가는 메뉴</h1>
            <ul>
                <li>강의실</li>
                <li>채팅방</li>
                <li>스케줄</li>
            </ul>
        </nav>

        <nav>
            <h1>관리자 메뉴</h1>
            <p>QUICK MENU 관리하기</p>
        </nav>

        <nav>
            <h1>스크롤 메뉴</h1>
            <p>top</p>
        </nav>
    </aside>-->
</body>
</html>

