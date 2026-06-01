<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <title>Online Shopping Mall</title>

    <style>

        *{
            box-sizing: border-box;
            font-family: Arial;
        }

        .container{
            width: 1000px;
            margin: auto;
        }

        .header{
            background: #8cc63f;
            color: white;
            padding: 20px;
        }

        .menu{
            background: #333;
            padding: 10px;
        }

        .menu a{
            color: white;
            margin-right: 15px;
            text-decoration: none;
        }

        .content{
            display: flex;
            margin-top: 10px;
        }

        .article{
            width: 75%;
            padding: 10px;
        }

        .aside{
            width: 25%;
            padding: 10px;
        }

        .row{
            display: flex;
            flex-wrap: wrap;
        }

        .footer{
            clear: both;
            background: #ddd;
            padding: 15px;
            text-align: center;
            margin-top: 10px;
        }

    </style>

</head>

<body>

<div class="container">

    <!-- HEADER -->
    <div class="header">
        <h1>Online Shopping Mall</h1>
    </div>

    <!-- MENU -->
    <div class="menu">
        <%@ include file="menu.jsp" %>
    </div>

    <!-- CONTENT -->
    <div class="content">

        <!-- DANH SÁCH SẢN PHẨM -->
        <div class="article">

            <div class="row">

                <jsp:include page="item.jsp">
                    <jsp:param name="name" value="Vay da hoi"/>
                    <jsp:param name="image" value="sp1.jpg"/>
                </jsp:include>

                <jsp:include page="item.jsp">
                    <jsp:param name="name" value="Ao khoac"/>
                    <jsp:param name="image" value="sp2.jpg"/>
                </jsp:include>

                <jsp:include page="item.jsp">
                    <jsp:param name="name" value="Quan jeans"/>
                    <jsp:param name="image" value="sp3.jpg"/>
                </jsp:include>

                <jsp:include page="item.jsp">
                    <jsp:param name="name" value="Ao the thao"/>
                    <jsp:param name="image" value="sp4.jpg"/>
                </jsp:include>

                <jsp:include page="item.jsp">
                    <jsp:param name="name" value="Giay bup be"/>
                    <jsp:param name="image" value="sp5.jpg"/>
                </jsp:include>

                <jsp:include page="item.jsp">
                    <jsp:param name="name" value="Tui xach"/>
                    <jsp:param name="image" value="sp6.jpg"/>
                </jsp:include>

            </div>

        </div>

        <!-- BÊN PHẢI -->
        <div class="aside">

            <%@ include file="login.jsp" %>

            <br>

            <%@ include file="category.jsp" %>

        </div>

    </div>

    <!-- FOOTER -->
    <div class="footer">
        FPT Polytechnic ©2020. All rights reserved.
    </div>

</div>

</body>
</html>