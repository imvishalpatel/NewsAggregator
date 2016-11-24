<%-- 
    Document   : SearchResult
    Created on : 24 Nov, 2016, 10:58:25 AM
    Author     : pritesh
--%>

<%@page import="model.Discussion"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	
	
	<title>News Aggregator</title>

	<link rel="shortcut icon" href="assest/images/favicon.png">
	
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
				
				<li><a href="notification_page.html" style="padding-left: 39px;">Notification</a></li>
				
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
						<li><a href="profile_page.html">User Profile</a></li>
						<li><a href="change_password.html">Change Password</a></li>
						<li><a href="signIn_Page.html">Logout</a></li>
					</ul>
				</li>
					 
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div> 
	<!-- /.navbar -->

	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">


		<div class="row">
			
			<!-- Article main content -->
			<article class="col-md-8 maincontent" style="margin-top: 44px;">
				<div class="list-group">
					<div id="list" class="list-group-item">
					  <h4 class="list-group-item-heading">
						Search By Discussion
					  </h4><hr/>
					  <div id="lst-desc">
					  	
								
						<table border="0" id="Comment" width="800">
                                                    <% 
            List<Discussion> topic = (List<Discussion>) request.getAttribute("searchResult_discussion");
            for(Discussion d : topic){
          
           
        %>
							<tr id="desc-1">
								<td>
									<a href="#"><% out.print(d.getTopic()); %></a>
								</td>
							</tr>
							<% } %>  
						</table>
						<br/>
	  				  </div>
					</div>
				</div>
				<br/>
				<br/>
				<div class="list-group">
					<div id="list" class="list-group-item">
					  <h4 class="list-group-item-heading">
						Search By User
					  </h4><hr/>
					  <div id="lst-desc">
					  	
								
						<table border="0" id="Comment" width="800">
                                                    <% 
            List<User> users = (List<User>) request.getAttribute("searchResult_users");
            for(User u : users){
                      
        %>
							<tr id="desc-1">
								<td>
									<a href="#"> <% out.print(u.getUsername()); %> </a>
								</td>
								<td>
									<span id="time-stamp-1" style="color:#3399FF;"><% out.print(u.getLastAcessTime()); %></span>
								</td>
							</tr>
							<% } %>
						</table>
						<br/>
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
	<script src="js/headroom.min.js"></script>
	<script src="js/jQuery.headroom.min.js"></script>
	<script src="js/custom.js"></script>
</body>
</html>
