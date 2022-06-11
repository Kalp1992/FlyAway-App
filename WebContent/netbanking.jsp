<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Netbanking</title>
<script src="jquery/jquery-latest.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	 $('body').bind('cut copy paste', function (e) {
	        e.preventDefault();
	    });
	   
	    //Disable mouse right click
	    $("body").on("contextmenu",function(e){
	        return false;
	    });
	    window.history.pushState(null, "", window.location.href);        
        window.onpopstate = function() {
            window.history.pushState(null, "", window.location.href);
        };
});
</script>


</head>
<body>
<%
	String totalprice= request.getParameter("totalprice");
String meth = request.getParameter("paymet");
String Noofperson = request.getParameter("Noofperson");
	String bank = request.getParameter("bnk");
	out.write("<h1>Welcome to "+bank+"</h1>");
%>
	<form method = "post" action="netauthentication">
		<input type="hidden" name="bank" value="<%=bank%>">
		<input type="hidden" name="totalprice" value="<%=totalprice%>">
		<input type="hidden" name="paymet" value="<%=meth%>">
		<input type="hidden" name="Noofperson" value="<%=Noofperson%>">
		Username: <input type = "text" name = "uname"></br>
		Password: <input type = "password" name = "pswd"></br>
		<input type = "submit" value = "login">		
	</form>
</body>
</html>