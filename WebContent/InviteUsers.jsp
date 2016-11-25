<%-- 

--%>
<%@page import="model.Notifications"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Constants.GlobalConstants"%>
<%@page import="model.PrivateDiscussion"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <style type='text/css'>
            
     .scrollable-menu {
    height: auto;
    max-height: 300px;
    overflow-x: hidden;
    width:400px;
}
        </style>
        <meta charset="utf-8">
        <meta name="viewport"    content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">

        <title>Invite Users</title>

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
                    <a class="navbar-brand" href="#"><img src="assest/images/logo.png"  width="150" style="margin-top: -17px;" alt="News Aggregator"></a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav pull-right" style="margin-left: 0px; width: 667px;">


                       
                       
<%User u = (User)request.getSession().getAttribute(GlobalConstants.LOGGED_IN_USER);
ArrayList<Notifications> list = u.getNotification();

%>
                         <li class="dropdown">                   
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="padding-left: 50px;">Notification<b class="caret"></b></a>
                        <ul class="dropdown-menu scrollable-menu">
                            <%
                            String details;
                               if(list!=null){
                                   details="<table border=2> ";
                                   for(int i=0;i<list.size();i++){
                                       Notifications n = list.get(i);
                                       
                                       details = "<li><h5><font color=white>"+n.getText()+"</font></h5>";
                                       details+="<tr><td align=left><form action='Controller?action=acceptinvitation' method='POST'><input name='topic' id='topic' type=hidden value="+ n.getReference() +"><input type=submit value=Accept></form></td>";
                                       details+="<td align=right><a href='Controller?action=publiclist'><input type=button value=Ignore align=right></a></td></tr></li><br><Br>";
                                       out.println(details);
                                   }
                                   details+="</table>";
                               }
                               else{
                                   details=null;
                                       details = "<tr><td><li><a>No Notifications</a></li></td></tr></table>"; //Value mae n.getReference dena hae jisme POST ka ID hoga. usse next page pe redirect karke we will call Show Post and rediect user to details page
                                       //details+="<table width='100%'> <tr><td align=left><form action='Controller?action=acceptinvitation' method='POST'><input name=first type=hidden value=Sunil><input name=last type=hidden value=Patel><input type=submit value=Accept></form></td>";
                                       //details+="<td align=right><input type=submit value=Ignore></td></tr><tr><td align=left><form action='Controller?action=acceptinvitation' method='POST'><input name=first type=hidden value=Gaurav><input name=last type=hidden value=Majmudar><input type=submit value=Accept></form></td>";
                                       //details+="<td align=right><input type=submit value=Ignore></td></tr></table></li>";
                                       out.println(details);
                                }
                            %>
                                							
                            </ul>
                        </li>
                        
                        
                        
                        
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
                                <li><a href="Controller?action=logout">Logout</a></li>
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
			<ul class="nav navbar-nav pull-right" style="margin-top: 51px;">
				<nav id="filter" class="col-md-12 text-center">
					<ul>
						<li><a href="my_discussion_list.html" class="current btn-theme btn-small">My Discussion</a></li>
						<li><a href="detailed_discussion_list.html" class="btn-theme btn-small">Discussion List</a></li>
						<li><a href="#" class="btn-theme btn-small">Most Viewed Post</a></li>
						<!--<li><a href="#" class="btn-theme btn-small">Print</a></li>-->
					</ul>
				</nav>
			</ul>
			<article class="col-md-8 maincontent" style="width: 1168px;">
				
				<ul class="nav nav-tabs" style="font-size:18px ">
					<li class="active"><a data-toggle="tab" href="#new_post">Invitee List</a></li>
				</ul>
				<div class="tab-content">
					<div id="new_post" class="tab-pane fade in active">
					  <div class="col-md-12">
                                              
                                                     

							

                                              <%
                                                  HashMap<String, User> uList = (HashMap<String, User>)request.getAttribute("userList");
                                                  PrivateDiscussion pb = (PrivateDiscussion)request.getAttribute("pb_obj");
                                                  String topic_id = String.valueOf(request.getAttribute("post_id"));
                                                  //out.println("<br><Br>AAYA  gayaa "+topic_id+"<br><Br>");
                                                  out.println("<form action='Controller?action=detailedprivatepost' method='POST' style='margin-top: 2px;'>");
                                                  out.println("<h3 style='color:#3366FF;'>List of Users : </h3>");
                                                   
                                                  if(uList!=null){
                                                      //out.println(uList.size());
                                                  out.println("<input type=hidden name='post_id123' value='"+topic_id+"'>");
							out.println("<h3><div id='topic_desc' style='color:#3366FF;'>Topic : "+pb.getTopic()+" </div></h3>");                      
                                                    for(String uid : uList.keySet()){
                                                        User user=uList.get(uid);
                                                          //out.println(user.getFirstName());
                                                        out.println("<input type=checkbox name='users' value='"+user.getUsername()+"'>"+user.getFirstName()+" "+user.getLastName());
                                                        out.println("<br>");
                                                    }
                                                  }
                                                  else
                                                      out.println("NULL aayu");
                                                  %>
						
						<div style="margin-top: 50px; margin-left: -13px;">
							<!--<a href="private_dis_pag"e.html" id="private_post_nxt" role="button" class="btn btn-primary btn-large">Finish</a>-->
                                                        <!--<button type="submit" class="btn btn-action">Invite</button>-->
                                                       
                                                        
                                                        <input type="submit" value=submit>
						</div>	
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
