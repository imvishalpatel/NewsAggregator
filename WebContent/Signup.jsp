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
        <script type="text/javascript">
            function checkFname(fnm)
            {

                //alert(document.getElementById("fname").value);
                //alert(document.getElementById("fname").value.length);
                if (document.getElementById("fname").value.length < 3 || document.getElementById("fname").value.length > 20)
                {
                    //document.getElementById("fname").value = "";
                    document.getElementById("fname").style.borderColor = "red";
                    document.getElementById("fname").focus();
                } else
                {
                    document.getElementById("fname").style.borderColor = "green";
                    document.getElementById("lname").focus();
                }
            }
            function checkEmptyFname()
            {
                if (document.getElementById("fname").value == "")
                {
                    document.getElementById("fname").style.borderColor = "red";
                    document.getElementById("fname").focus();
                } else
                {
                    document.getElementById("fname").style.borderColor = "green";
                    document.getElementById("lname").focus();
                }
            }
            function checkLname(lnm)
            {

                //alert(document.getElementById("lname").value);
                //alert(document.getElementById("lname").value.length);
                if (document.getElementById("lname").value.length < 4 || document.getElementById("lname").value.length > 20)
                {
                    //document.getElementById("lname").value = "";
                    document.getElementById("lname").style.borderColor = "red";
                    document.getElementById("lname").focus();
                } else
                {
                    document.getElementById("lname").style.borderColor = "green";
                    document.getElementById("uname").focus();
                }
            }
            function checkEmptyLname()
            {
                if (document.getElementById("lname").value == "")
                {
                    document.getElementById("lname").style.borderColor = "red";
                    document.getElementById("lname").focus();
                } else
                {
                    document.getElementById("lname").style.borderColor = "green";
                    document.getElementById("uname").focus();
                }
            }
            function checkEmptyUname()
            {
                if (document.getElementById("uname").value == "")
                {
                    document.getElementById("uname").style.borderColor = "red";
                    document.getElementById("uname").focus();
                } else
                {
                    document.getElementById("uname").style.borderColor = "green";
                    document.getElementById("eid").focus();
                }
            }
            function onlyAlphabets(e, t)
            {
                try
                {
                    if (window.event)
                    {
                        var charCode = window.event.keyCode;
                    } else if (e)
                    {
                        var charCode = e.which;
                    } else
                    {
                        return true;
                    }
                    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
                        return true;
                    else
                        return false;
                } catch (err)
                {
                    alert(err.Description);
                }
            }
            function validateUname(unameField)
            {
                //alert(unameField.length);
                if (document.getElementById("uname").value.length > 5 || document.getElementById("uname").value.length < 21)
                {
                    var reg = /^[a-zA-Z0-9]+$/;
                    if (reg.test(document.getElementById("uname").value) == false)
                    {
                        alert("Invalid User Name");
                        document.getElementById("uname").value = "";
                        document.getElementById("uname").style.borderColor = "red";
                        document.getElementById("uname").focus();
                    } else
                    {
                        document.getElementById("uname").style.borderColor = "green";
                    }
                } else
                {
                }
            }
            function validateEmail(emailField)
            {
                var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

                if (reg.test(emailField) == false)
                {
                    alert("Invalid Email Address");
                    document.getElementById("eid").value = "";
                    document.getElementById("eid").style.borderColor = "red";
                    document.getElementById("eid").focus();
                } else
                {
                    document.getElementById("eid").style.borderColor = "green";
                }

            }
            function validatePassWord()
            {
                var regEx = /^[a-zA-Z0-9]+$/;
                var pwd = document.getElementById("pwd").value;
                //alert(pwd+"\n"+pwd.length);
                var plen = pwd.length;
                if (regEx.test(pwd))
                {
                    if (plen < 8 || plen > 16)
                    {
                        alert("Please insert valid data....\n*Alphanumeric Value.\n*Length Must be 8-15.");
                        document.getElementById("pwd").value = "";
                        document.getElementById("pwd").style.borderColor = "red";
                        document.getElementById("pwd").focus();
                    } else
                    {
                        document.getElementById("pwd").style.borderColor = "green";
                    }
                } else
                {
                    alert("Please insert valid data....\n*Alphanumeric Value.\n*Length Must be 8-15.");
                    document.getElementById("pwd").value = "";
                    document.getElementById("pwd").style.borderColor = "red";
                    document.getElementById("pwd").focus();
                }
            }
            function checkEmptyPwd()
            {
                if (document.getElementById("pwd").value == "")
                {
                    document.getElementById("pwd").style.borderColor = "red";
                    document.getElementById("pwd").focus();
                } else
                {
                    document.getElementById("pwd").style.borderColor = "green";
                    document.getElementById("cpwd").focus();
                }
            }
            function matchPass()
            {

                if (document.getElementById("pwd").value == "")
                {
                    document.getElementById("pwd").focus();
                } else
                {
                    var pwd = document.getElementById("pwd").value;
                    var cpwd = document.getElementById("cpwd").value;


                    var regEx = /^[a-zA-Z0-9]+$/;

                    var plen = pwd.length;

                    if (regEx.test(cpwd))
                    {
                        if (pwd == cpwd)
                        {
                            document.getElementById("cpwd").style.borderColor = "green";
                        } else
                        {
                            alert("Both the passwords are not matching...");
                            document.getElementById("pwd").value = "";
                            document.getElementById("cpwd").value = "";
                            document.getElementById("pwd").style.borderColor = "red";
                            document.getElementById("cpwd").style.borderColor = "red";
                            document.getElementById("pwd").focus();

                        }
                    }
                    /*else
                     {
                     alert("Please insert valid data....\n*Alphanumeric Value.\n*Length Must be 8-10.");
                     document.getElementById("cpwd").value = "";
                     document.getElementById("cpwd").style.borderColor = "red";
                     document.getElementById("pwd").focus();
                     }*/

                }


            }
        </script>
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
                        <h1 class="page-title">Registration</h1>
                    </header>

                    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <h3 class="thin text-center">Registration for new profile</h3>
                                <p class="text-center text-muted">
                                    If you are registered , then <a href="Controller?action=viewlogin">Login</a> to the application. 										  							</p>
                                <hr>
                                <!--check whether any validation error-->
                                <c:if test="${requestScope.signupErr ne null}">
                                    <div class="alert alert-danger fade in">
                                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                        <c:forEach items="${signupErr}" var="current">
                                            <strong>Required!</strong>
                                            <c:out value="${current}" />
                                            <br />
                                        </c:forEach>
                                    </div>
                                </c:if>
                                <!--validation end-->
                                <form action="Controller" method="post">
                                    <input type="hidden" name="action" value="adduser" />
                                    <div class="top-margin">
                                        <label>First Name</label>
                                        
                                        <input type="text" class="form-control" name="firstname" id="fname" value="${requestScope.firstname}" onKeyPress="return onlyAlphabets(event,this);" onChange="checkFname(this.id);">
                                    </div>
                                    <div class="top-margin">
                                        <label>Last Name</label>
                                        
                                        <input type="text" class="form-control" name="lastname" value="${requestScope.lastname}" id="lname" onKeyPress="return onlyAlphabets(event,this);" onFocus="checkEmptyFname();" onChange="checkLname(this.id);">
                                    </div>
                                    <div class="top-margin">
                                        <label>Username <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" name="username" value="${requestScope.username}" id="uname" maxlength="21" onFocus="checkEmptyLname();" onChange="validateUname(this.value);" required>
                                        
                                    </div>
                                    <div class="top-margin">
                                        <label>Email Address <span class="text-danger">*</span></label>
                                        
                                        <input type="text" class="form-control" name="email" value="${requestScope.email}" id="eid" maxlength="51" onFocus="checkEmptyUname();" onchange="validateEmail(this.value)" required>
                                    </div>

                                    <div class="row top-margin">
                                        <div class="col-sm-6">
                                            <label>Password <span class="text-danger">*</span></label>
                                            <input type="password" class="form-control" name="password" id="pwd" onchange="validatePassWord();" required>
                                            
                                        </div>
                                        <div class="col-sm-6">
                                            <label>Confirm Password <span class="text-danger">*</span></label>
                                            
                                            <input type="password" class="form-control" id="cpwd" onFocus="checkEmptyPwd();" onchange="matchPass();" required>
                                        </div>
                                    </div>

                                    <hr>

                                    <div class="row">
                                        <div class="col-lg-4">
                                            <button class="btn btn-action" type="submit">Register</button>
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