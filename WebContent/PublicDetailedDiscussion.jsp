<%-- 
    Document   : PublicDetailedDiscussion
    Created on : Nov 17, 2016, 8:47:43 AM
    Author     : BHAVESH GOYAL
--%>

<%@page import="model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.PublicDiscussion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
	<link rel="stylesheet" href="assest/css/font-awesome.css">
	<link rel="stylesheet" href="assest/css/font-awesome.min.css">
<a href="detailed_discussion_list.html"></a>
	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assest/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assest/css/style.css">

	
	
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
	
</head>

<<body>
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="#"><img src="assest/images/logo.png" width="150" style="margin-top: -17px;" alt="News Aggregator"></a>
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
						<li><a href="profile_page.html">User Profile</a></li>
						<li><a href="change_password.html">Change Password</a></li>
						<li><a href="signIn_Page.html">Logout</a></li>
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
			<ul class="nav navbar-nav pull-right" style="margin-top: 51px;">
				<nav id="filter" class="col-md-12 text-center">
					<ul>
						<li><a href="new_discussion_page.html" class="btn-theme btn-small">New Discussion</a></li>
						<li><a href="my_discussion_list.html" class="btn-theme btn-small">My Discussion</a></li>
						<li><a href="detailed_discussion_list.html" class="current btn-theme btn-small">Discussion List</a></li>
						<!--<li><a href="#" class="btn-theme btn-small">Print</a></li>-->
					</ul>
				</nav>
			</ul>
			<aside class="col-md-2 sidebar sidebar-left" style="margin-top: 94px; margin-left: -59px;">
				<div class="row panel">
					<div class="col-xs-10">
						<h5>News Feed</h5>
					</div>
					<div class="row panel">
						<div class="col-xs-8">
							<ul>
								<a href="#"><li><h5>Education</h5></li></a>
								<a href="#"><li><h5>Technology</h5></li></a>
								<a href="#"><li><h5>Sports</h5></li></a>
								<a href="#"><li><h5>Politics</h5></li></a>
								<a href="#"><li><h5>Movies</h5></li></a>
							</ul>
						</div>
					</div>
					
				</div>
			</aside>
			<article class="col-md-8 maincontent" style="margin-top: 44px;">
                                     <%
            try{
                     ArrayList<String> tag=new ArrayList<String>();
            
              PublicDiscussion pb = (PublicDiscussion)request.getAttribute("pb");
              
              
          // for(int i=0;i<arpb.size();i++)    
            //{
                
                
                 //pb=arpb.get(i);
               //  out.println("<tr>");
                 //out.println("<td>Post id:"+ pb.getid()+"</td>");
              //   out.println(pb.getUsername());
             
               //  out.println(pb.getContent());
                 // out.println("<td>Tags:"+ pb.getTagsString()+"</td>");
               // out.println("<td>Category:"+ pb.getCategoryString()+"</td>");
                 
              //   out.println("</tr>");
                 
           // }   
           
        %>

				<div class="list-group">
					<div id="list" class="list-group-item">
					  <h4 class="list-group-item-heading">
						<%   out.println(pb.getTopic());%><span id="t1" class="badge" style="float:right;"><%out.println(pb.getViewCount());%> Views</span>
					  </h4>
					  <div id="desc">
					  	<table border="0" id="main_discussion">
							<tr>
								<td>
									<p class="list-group-item-text">
										<%
                 out.println(pb.getContent());%><br/><br/>
												
										<h5>
											 <% 
                                                tag=pb.getTags();
                                               
                                                for(int j=0;j<tag.size();j++)
                                                {
					out.println("<span id='tg11' class='badge'>#"+tag.get(j)+"</span>");
							//<span id="tg12"class="badge">#Plitical</span>
                                                        
                                                }      
                                                    
                                                    %>

											<img src="" alt="User_Pic" style="margin-left: 421px;"/><a href="#" style="float:right;"><%out.println(pb.getUsername());%></a>
										</h5>
										<a class="fa fa-thumbs-up" aria-hidden="true" style="text-decoration:none; cursor:pointer"></a><%out.println(pb.getUpVotes());%>&nbsp;
										<a class="fa fa-thumbs-down" aria-hidden="true" style="text-decoration:none; cursor:pointer"></a><%out.println(pb.getDownVotes());%>&nbsp;
										<a href="my_discussion_list.html" id="spam_org1">Mark as spam</a>
									</p>
								</td>
							</tr>
						</table><br/>
						<h4><div id="cmt_title" style="color:#3399CC;">Comment Section</div></h4><hr/>
						<div id="add_comment">
							<form id="post_answer_textarea">
								<textarea id="post-dis" cols="75" rows="4" placeholder="Enter the Post" style="border-radius: 5px;"></textarea>
							</form>
							<div style="margin-top: 50px; margin-left: -13px;">
								<button id="btn_post" class="btn btn-info" style="padding-left: 40px; margin-left: 13px; padding-top: 10px; margin-top: -66px;">Post Comment</button>
							</div>
						</div>													
						<table border="0" id="Comment" width="800">
							
                                                            <%ArrayList<Comment> comm=pb.getComments();
                                                            
                                                            
                                                            for(int j=0;j<comm.size();j++)
                                                {
                                                    
                                                
                                                            %>
                                                            <tr >
								<td>
                                                                    <%out.println(comm.get(j).getComment());
                                                                        %> - <a href="#"><%out.println(comm.get(j).getUserName());
                                                                        %></a>
									<br/>
									<a class="fa fa-thumbs-up" aria-hidden="true" style="text-decoration:none; cursor:pointer">Upvote</a>&nbsp;
									<a class="fa fa-thumbs-down" aria-hidden="true" style="text-decoration:none; cursor:pointer">Downvote</a>&nbsp;&nbsp;
									<a href="my_discussion_list.html" id="spam_org1">Mark as spam</a>&nbsp;&nbsp;&nbsp;
									<a href="#" id="cmt_on_cmt">add a comment</a>
									<hr/>
								</td>
							</tr>
							<%}%>
						</table>
						<br/>
	  				  </div>
					</div>
				</div>
				<br/>
				                                          <%
                                                
                                                 }
            catch (Exception e) {
            out.println(e.getMessage()+"test page");
        }
                                                %>
	
			</article>
			<aside class="col-md-2 sidebar sidebar-right" style="padding-left: 15px; margin-left: 44px;">
				<div class="col-xs-10">
						<h5>Trending Topics</h5>
				</div>
				<div class="row panel">
					<div class="col-xs-12">
						<ul>
							<a href="#"><li><h5>Indian Army's sergical strike in PoK</h5></li></a>
							<a href="#"><li><h5>PM reviews Indus water trety</h5></li></a>
							<a href="#"><li><h5>Google unveils Pixel smartphones</h5></li></a>
							<a href="#"><li><h5>PM reviews Indus water trety</h5></li></a>
							<a href="#"><li><h5>Google unveils Pixel smartphones</h5></li></a>
							<a href="#"><li><h5>PM reviews Indus water trety</h5></li></a>
							<a href="#"><li><h5>Google unveils Pixel smartphones</h5></li></a>
							<a href="#"><li><h5>PM reviews Indus water trety</h5></li></a>
							<a href="#"><li><h5>Google unveils Pixel smartphones</h5></li></a>
						</ul>
					</div>
				</div>
			</aside>
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