<%-- 
    Document   : UserProfile
    Created on : 19 Nov, 2016, 5:12:04 PM
    Author     : vishal
--%>
<%@page import="model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport"    content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">


        <title>Profile Page</title>

        <link rel="shortcut icon" href="assest/images/favicon.png">

        <link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
        <link rel="stylesheet" href="assest/css/bootstrap.min.css">
        <link rel="stylesheet" href="assest/css/font-awesome.css">
        <link rel="stylesheet" href="assest/css/font-awesome.min.css">

        <!-- Custom styles for our template -->
        <link rel="stylesheet" href="assest/css/bootstrap-theme.css" media="screen" >
        <link rel="stylesheet" href="assest/css/style.css">



        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <!-- Fixed navbar -->
        <div class="navbar navbar-inverse navbar-fixed-top headroom" >
            <div class="container">
                <div class="navbar-header">
                    <!-- Button for smallest screens -->
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                    <a class="navbar-brand" href="#"><img src="images/logo.png"  width="150" style="margin-top: -17px;" alt="News Aggregator"></a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav pull-right" style="margin-left: 0px; width: 667px;">
                        <div class="col-lg-6" style="margin-top: 10px;">
                            <div class="input-group">
                                <input class="form-control" placeholder="Search" type="text">
                                <span class="input-group-btn">
                                    <form class="form-inline" method="post" action="#">
                                        <button class="btn btn-default" type="submit" style="margin-top: -1px; height: 37px; padding: 4px 19px; border-left-width: 0px; border-right-width: 0px; margin-left: -1px;">
                                            <i class="fa fa-search" aria-hidden="true"></i>
                                        </button>
                                    </form>
                                </span>
                            </div>
                        </div>

                        <li><a href="index.html" style="padding-left: 39px;">Notification</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Discussion <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="private_dis_page.html">Private Discussion</a></li>
                                <li><a href="exclusive_dis_page.html">Exclusive Discussion</a></li>
                                <li><a href="public_discussion_page.html">Public Discussion</a></li>							
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"><b class="caret"></b></i></a>
                            <ul class="dropdown-menu" style="padding: 5px 1px; width: 151px;">
                                <li><a href="UserProfile.jsp">Profile</a></li>
                                <li><a href="ChangePassword.jsp">Change Password</a></li>
                                <li><a href="Controller?action=logout">Logout</a></li>
                            </ul>
                        </li>
                        <!--<li class="active" style="margin-left: 29px;"><a class="btn" href="signin_Page.html">LOGOUT</a></li>-->
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div> 
        <!-- /.navbar -->

        <header id="head" class="secondary"></header>

        <!-- container -->
        <div class="container">
            <div class="row">
                <article class="col-md-8 maincontent" style="width: 1168px; margin-top: 51px;">

                    <ul class="nav nav-tabs" style="font-size:18px ">
                        <li class="active"><a data-toggle="tab" href="#">${sessionScope.LOGGED_IN_USER.firstName} ${sessionScope.LOGGED_IN_USER.lastName}</a></li>
                    </ul>
                    <br/>
                    <div class="tab-content">
                        <div id="user_profile_1" class="tab-pane fade in active">
                            <div class="col-md-12">
                                <!--check whether any success message-->
                                <c:if test="${requestScope.userProfileSucc ne null}">
                                    <c:forEach items="${userProfileSucc}" var="current">
                                        <div class="alert alert-success fade in">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <strong>!</strong>
                                            <c:out value="${current}" />
                                        </div>
                                    </c:forEach>
                                    <hr>
                                </c:if>

                                <!--check whether any error message-->
                                <c:if test="${requestScope.userProfileErr ne null}">
                                    <c:forEach items="${userProfileErr}" var="current">
                                        <div class="alert alert-danger fade in">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <strong>Required!</strong>
                                            <c:out value="${current}" />
                                        </div>
                                    </c:forEach>
                                    <hr>
                                </c:if>

                                <form name="user_profile_edit" action="Controller" method="post" style="margin-top: 32px; margin-left: -11px;">
                                    <input type="hidden" name="action" value="saveuserprofile" />

                                    <table border="0" cellpadding="5">
                                        <tr>
                                            <td width="150">
                                                First name : 
                                            </td>
                                            <td>
                                                <input type="text" name="firstname"  value="${sessionScope.LOGGED_IN_USER.firstName}" id="fname_1" style="width:200px;">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="150">
                                                Last name : 
                                            </td>
                                            <td>
                                                <input type="text" name="lastname"  value="${sessionScope.LOGGED_IN_USER.lastName}" id="lname_1" style="width:200px;">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="150">
                                                Username : 
                                            </td>
                                            <td>
                                                <input type="text" name="username" value="${sessionScope.LOGGED_IN_USER.username}" style="width:200px;" id="uname_1"readonly>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Email
                                            </td>
                                            <td>
                                                <input type="text" id="mail_id_1" name="email" value="${sessionScope.LOGGED_IN_USER.email}" style="width:200px;"readonly>
                                            </td>
                                        </tr>
                                       
                                        <tr>
                                            <td>
                                                Total Posts    : 
                                            </td>
                                            <td>
                                                <input type="number" name="totalPosts" id="total_post_1" style="width:200px; -moz-appearance:textfield;" readonly>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Total Comments   : 
                                            </td>
                                            <td>
                                                <input type="number" name="totalComments" id="total_cmnt_1" style="width:200px; -moz-appearance:textfield;" readonly>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Total Upvote discussion    : 
                                            </td>
                                            <td>
                                                <input type="number" name="totalUpvoteDiscussion" id="total_upvote_1" style="width:200px; -moz-appearance:textfield;" readonly>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Total Downvote discussion    : 
                                            </td>
                                            <td>
                                                <input type="number" name="totalDownvoteDiscussion" id="total_downvote_1" style="width:200px; -moz-appearance:textfield;" readonly>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><div style="margin-top: 50px; margin-left: -1px;">
                                                    <button id="btn_save" type="submit" class="btn btn-primary btn-large">Save</button>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>

                                </form>							

                            </div>		
                        </div>				 
                    </div>
                </article>
            </div>
        </div>	<!-- /container -->

        <footer id="footer" class="top-space">

            <div class="footer1">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 panel contact"> 
                            <h3 class="panel-title">Contact Info</h4>
                                <div class="panel-body">
                                    <p>Developed by Team Black</p>
                                    <ul>
                                        <li><i class="fa fa-phone"></i>+91-940-808-0936</li>
                                        <li><a href="#"><i class="fa fa-envelope-o"></i> 201512002@daiict.ac.in</a></li>
                                        <li><i class="fa fa-flag"></i>DA-IICT, Gandhinagar</li>
                                    </ul> 
                                </div>
                        </div>

                        <div class="col-md-3 panel">
                            <h3 class="panel-title">Follow Us</h3>
                            <div class="panel-body">
                                <p class="follow-me-icons">
                                    <a href="signin_Page.html"><i class="fa fa-twitter fa-2"></i></a>
                                    <a href="signin_Page.html"><i class="fa fa-github fa-2"></i></a>
                                    <a href="signin_Page.html"><i class="fa fa-facebook fa-2"></i></a>

                                </p>	
                            </div>
                        </div>

                    </div> <!-- /row of panels -->
                </div>
            </div>

            <div class="footer2">
                <div class="container">
                    <div class="row">

                        <div class="col-md-9 panel"></div>

                        <div class="col-md-3 panel">
                            <div class="panel-body">
                                <p class="text-right">
                                    Copyright &copy; 2016. Developed by Team Black 
                                </p>
                            </div>
                        </div>

                    </div> <!-- /row of panels -->
                </div>
            </div>

        </footer>	




        <!-- JavaScript libs are placed at the end of the document so the pages load faster -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="assest/js/headroom.min.js"></script>
        <script src="assest/js/jQuery.headroom.min.js"></script>
        <script src="assest/js/custom.js"></script>
    </body>
</html>

