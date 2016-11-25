<%-- 
    Document   : PublicMyDiscussion
    Created on : Nov 15, 2016, 8:04:27 PM
    Author     : BHAVESH GOYAL
--%>

<%@page import="Constants.GlobalConstants"%>
<%@page import="model.User"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="trend.Trend"%>
<%@page import="crawling.NewsModel"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="model.PublicDiscussion"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
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
				<a class="navbar-brand" href="#"><img src="assest/images/logo.png" width="150" style="margin-top: -17px;" alt="News Aggregator"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right" style="margin-left: 0px; width: 667px;">
					<div class="col-lg-6" style="margin-top: 10px;">
					<div class="input-group">
                                            <form action="Controller" method="post">
                                                <input type="hidden" name="action" value="search"/>
						  <input name="searchvalue" type="text" class="form-control" placeholder="Search for..." style="width: 297px;">
						  <span class="input-group-btn">
							<button class="btn btn-secondary" type="submit" style="margin-top: 1px; height: 32px; padding: 4px 19px; border-left-width: 0px; border-right-width: 0px; margin-left: -81px; width: 84px; border-bottom-width: 1px;">Search</button>
						  </span>
                                            </form>
						</div>
				</div>
				
				<li><a href="index.html" style="padding-left: 39px;">Notification</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Discussion <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="Controller?action=privatepost">Private Discussion</a></li>
						<li><a href="exclusive_dis_page.html">Exclusive Discussion</a></li>
						<li><a href="Controller?action=publiclist">Public Discussion</a></li>							
					</ul>
				</li>
				
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"><b class="caret"></b></i></a>
					<ul class="dropdown-menu" style="padding: 5px 1px; width: 151px;">
						     <li><a href="Controller?action=userprofile">User Profile</a></li>
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
			<ul class="nav navbar-nav pull-right" style="margin-top: 51px;">
				<nav id="filter" class="col-md-12 text-center">
					<ul>
						<li><a href="Controller?action=publicpost" class="btn-theme btn-small">New Discussion</a></li>
						<li><a href="Controller?action=publiclist" class="btn-theme btn-small">Discussion List</a></li>
						<li><a href="#" class="current btn-theme btn-small">My Discussion</a></li>
						
                                                <!--<li><a href="#" class="btn-theme btn-small">Print</a></li>-->
					</ul>
				</nav>
			</ul>
			<aside class="col-md-2 sidebar sidebar-left" style="margin-top: 94px; margin-left: -100px;">
				<div class="row panel">
					<div class="col-xs-12">
						<h3>News Feed</h3>
					</div>
                                    <%List<NewsModel> newsList = (List<NewsModel>) request.getServletContext().getAttribute("newsList");
if(newsList!=null){
	
		//System.out.println(nm);
	
%>
<div class="row panel">
						<div class="col-xs-10" >
                                                    
							<ul>
                                                            <%for(NewsModel nm : newsList){%>
								<a href="Controller?action=publicpost&topic=<%out.println(nm.getTitle()+"&content="+nm.getDescription());%>"><li><h5><%out.println(nm.getTitle());%></h5></li></a>
								<%}%>
							</ul>
						</div>
					</div>
					<%}%>
				</div>
			</aside>
			<article class="col-md-8 maincontent" style="margin-top: 44px;">
				<div class="list-group">
                                    
            
        <%
String username=((User)request.getSession().getAttribute(GlobalConstants.LOGGED_IN_USER)).getUsername();
                                        

if(request.getAttribute("arpb")==null)
            response.sendRedirect("Controller?action=publicmydiscussion");
            try{
             PublicDiscussion pb; 
            ArrayList<String> tag=new ArrayList<String>();
            ArrayList<PublicDiscussion> arpb = (ArrayList<PublicDiscussion>)request.getAttribute("arpb");
           for(int i=0;i<arpb.size();i++)    
            {
                pb=arpb.get(i);
                if(pb.getUsername().equals(username))
                {
                 
                 
//                 out.println( pb.getUsertId());
//                out.println(pb.getTopic());
//                 out.println("<td>Content:"+ pb.getContent()+"</td>");
//                  out.println("<td>Tags:"+ pb.getTagsString()+"</td>");
//                out.println("<td>Category:"+ pb.getCategoryString()+"</td>");
//                 
                 
            
        %>
        
					<a href="Controller?action=publicdetailview&postid=<%out.println(pb.getid()+"&uname="+username);%>" class="list-group-item">
					  <h4 class="list-group-item-heading">
						<%out.println(pb.getTopic());%><span id="t1" class="badge" style="float:right;"><%out.println(pb.getViewCount());%> views</span>
					  </h4>
					  <p class="list-group-item-text">
						<%out.println(pb.getContent());%>
                                                <div id="time_post_1" style="color:#3399CC; float:left"><%out.println(pb.getDateString());%></div>
                                                <br/><br/>
								
						<h5>
                                                    <% 
                                                tag=pb.getTags();
                                               
                                                for(int j=0;j<tag.size();j++)
                                                {
					out.println("<span id='tg11' class='badge'>#"+tag.get(j)+"</span>");
							//<span id="tg12"class="badge">#Plitical</span>
                                                        
                                                }      
                                                    
                                                    %>
							<span id="t13" class="badge" style="float:right;"><%out.println(pb.getUsername());%></span>
						</h5>
						<!--Content for the topic-->
					  </p>
					</a>
					<%
                                                        }
            }   
            }
            catch (Exception e) {
            out.println(e.getMessage()+" my discussion page");
        }
                                        
                                        %>
				</div>
			</article>
			<aside class="col-md-2 sidebar sidebar-right" style="padding-left: 15px; margin-left: 44px;">
				<div class="col-xs-10">
						<h3>Trending Topics</h3>
				</div>
				<div class="row panel">
                                   <%
                                   Trend tr=new Trend();
                                    
                                    ArrayList<PublicDiscussion> list=(ArrayList<PublicDiscussion>)request.getServletContext().getAttribute("trending");
                                    
                                   
                                   %>
					<div class="col-xs-12">
						<ul>
                                                    <%
                                                    for(PublicDiscussion pdn : list)
                                                    {
                                                        
%>
							<a href="Controller?action=publicdetailview&postid=<%out.println(pdn.getid()+"&uname="+username);%>"><li><h5><%=pdn.getTopic()%></h5></li></a>
						
                                                        <%}%>
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
