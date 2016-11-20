<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport"    content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">


        <title>News Aggregator</title>

        <link rel="shortcut icon" href="images/favicon.png">

        <link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
        <link rel="stylesheet" href="assest/css/bootstrap.min.css">
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
                    <a class="navbar-brand" href="#"><img src="assest/images/logo.png"  width="150" style="margin-top: -17px;" alt="News Aggregator"></a>
                </div>
                
            </div>
        </div> 
        <!-- /.navbar -->

        <header id="head" class="secondary"></header>

        <!-- container -->
        <div class="container">


            <div class="row">

                <!-- Article main content -->
                <article class="col-xs-12 maincontent">
                    <header class="page-header">
                        <h1 class="page-title">Reset Password</h1>
                    </header>

                    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                        <div class="panel panel-default">
                            <div class="panel-body">


                                <c:if test="${requestScope.resetPasswordErr ne null}">
                                    <c:forEach items="${resetPasswordErr}" var="current">
                                        <div class="alert alert-danger fade in">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <strong>Required!</strong>
                                            <c:out value="${current}" />
                                        </div>
                                    </c:forEach>
                                    <hr>
                                </c:if>



                                <form action="Controller" method="post">

                                    <input type="hidden" name="action" value="addnewpassword" />
                                    <input type="hidden" name="userid" value="${requestScope.userid}" />

                                    <div class="top-margin">
                                        <label>New Password <span class="text-danger">*</span></label>
                                        <input type="password" class="form-control" name="newpassword">
                                    </div>
                                    <div class="top-margin">
                                        <label>Confirm New Password <span class="text-danger">*</span></label>
                                        <input type="password" class="form-control" name="confirmnewpassword">
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-lg-4 text-right">
                                            <button class="btn btn-action" type="submit">Change Password</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                    </div>

                </article>
                <!-- /Article -->

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