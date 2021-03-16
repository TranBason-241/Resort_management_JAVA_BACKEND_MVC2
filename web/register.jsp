<%-- 
    Document   : register
    Created on : Nov 5, 2020, 2:43:07 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign Up</title>

        <!-- Font Icon -->
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>

        <div class="main">

            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Sign up</h2>
                            <form action="MainController" method="POST" class="register-form" id="register-form">
                                <div class="form-group">

                                    <input type="text" name="name" value="" placeholder="Your Name" />
                                    <h4 style="margin-top: 0;margin-bottom: 0;color: red">${requestScope.ERROR.getNameError()}</h4>
                                </div>
                                <div class="form-group">

                                    <input type="text" name="id"  value="" placeholder="UserID" />
                                    <h4 style="margin-top: 0;margin-bottom: 0;color: red">${requestScope.ERROR.getIdError()}</h4>
                                </div>
                                <div class="form-group">

                                    <input type="email" name="email" id="email" value="" placeholder="Your Email" />
                                    <h4 style="margin-top: 0;margin-bottom: 0;color: red">${requestScope.ERROR.getEmailEror()}</h4>
                                </div>
                                <div class="form-group">

                                    <input type="password" name="pass" id="pass" value="" placeholder="Password" />
                                    <h4 style="margin-top: 0;margin-bottom: 0;color: red">${requestScope.ERROR.getPass1()}</h4>
                                </div>
                                <div class="form-group">

                                    <input type="password" name="re_pass" id="re_pass" value="" placeholder="Repeat your password" />
                                    <h4 style="margin-top: 0;margin-bottom: 0;color: red">${requestScope.ERROR.getPass2()}</h4>
                                </div>

                                <div class="form-group form-button">
                                    <input type="submit" name="btnAction"  class="form-submit" value="register" />
                                    <h4  style="margin-top: 0;margin-bottom: 0;color: green">${requestScope.success}</h4>
                                     <input type="submit" name="btnAction"  class="form-submit" value="backToLogin" />
                                </div>
                            </form>
                        </div>
                        <!-- <div class="signup-image">
                            <figure><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRTHeejRD7eonbJ70L4WhHyFgjpsA7fqOWAhA&usqp=CAU" alt="sing up image"></figure>
                             <a href="#" class="signup-image-link">I am already member</a> -->
                    </div>
                </div>
        </div>
    </section>

    <!-- Sing in  Form -->


</div>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>