	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NKMage</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Login and Registration Form with HTML5 and CSS3" />
        <meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/style1.css" />
		<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
</head>
<body>
 <div class="container">
            <div class="codrops-top">
                
                    <strong> <br>  </strong>
            
                <span class="right">
                   
                </span>
                <div class="clr"></div>
            </div><!--/ Codrops top bar -->
            <header>
                <h1>nkmage.tech <span>please write down your message for us</span></h1>

            </header>
            <section>				
               
                    
                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div id="login" class="animate form">
                           <br>
                            <form  action="book_add" method="post"> 
                                <h1>Message</h1> 
                                <p> 
                                    <label for="password" class="youpasswd" >Write Down Your Message </label><br><br><br>
                                    <textarea  name="message" rows="7" cols="50"></textarea>
                                </p>
                                <p class="login button"> 
                                    <input type="submit" value="Submit" /> 
								</p>
                               
                            </form>
                        </div>

                        
					
                </div>  
            </section>
        </div>
    </body>
</html>