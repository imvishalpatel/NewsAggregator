<%-- 
    Document   : AdminSpam
    Created on : Nov 22, 2016, 2:04:42 PM
    Author     : BHAVESH GOYAL
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.PublicDiscussion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <a class="navbar-brand" href="#"><img src="assest/images/logo.png"  width="150" style="margin-top: -17px;" alt="News Aggregator"></a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav pull-right" style="margin-left: 0px; width: 667px;">


                        <li><a href="#" style="padding-left: 39px; margin-left:400px;">Notification</a></li>


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

                <article class="col-md-8 maincontent" style="margin-top: 44px;">
                    <%
                        if (request.getAttribute("arpb") == null) {
                            response.sendRedirect("Controller?action=viewspam");
                        }
                        try {
                            PublicDiscussion pb;
                            ArrayList<String> tag = new ArrayList<String>();
                            ArrayList<PublicDiscussion> arpb = (ArrayList<PublicDiscussion>) request.getAttribute("arpb");
                            for (int i = 0; i < arpb.size(); i++) {
                                pb = arpb.get(i);
//                if(pb.getUsertId().equals("bgoyal2222"))
//                {

//                 out.println( pb.getUsertId());
//                out.println(pb.getTopic());
//                 out.println("<td>Content:"+ pb.getContent()+"</td>");
//                  out.println("<td>Tags:"+ pb.getTagsString()+"</td>");
//                out.println("<td>Category:"+ pb.getCategoryString()+"</td>");
//                 
                    %>	
                    <div class="list-group">
                        <h4 class="list-group-item-heading">
                            <%out.println(pb.getTopic());%>
                        </h4>
                        <p class="list-group-item-text">
                            <%out.println(pb.getContent());%><br/><br/>

                        <h5>
                            <%
                                tag = pb.getTags();

                                for (int j = 0; j < tag.size(); j++) {
                                    out.println("<span id='tg11' class='badge'>#" + tag.get(j) + "</span>");
                                //<span id="tg12"class="badge">#Plitical</span>

                                }

                            %>
                            <span id="t13" class="badge" style="float:right;"><%out.println(pb.getUsername());%></span>
                        </h5>
                        <button class="btn btn-xs" id="spam_org1"><a href="Controller?action=acceptspam&postid=<%out.print(pb.getid());%>" style="text-decoration:none;">Accept as spam</a></button>
                        <button class="btn btn-xs" id="spam_org2"><a href="Controller?action=rejectspam&postid=<%out.print(pb.getid());%>" style="text-decoration:none;">Reject as spam</a></button>
                        </p>
                    </div>
                    <%

                            }
                        } catch (Exception e) {
                            out.println(e.getMessage() + " SPAM MANAGE page");
                        }
                    %>
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
        <script src="js/headroom.min.js"></script>
        <script src="js/jQuery.headroom.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>
