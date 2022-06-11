<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
HttpSession hs = request.getSession();
String meth = (String)hs.getAttribute("paymet");
String Noofperson = (String)hs.getAttribute("Noofperson");
String totalprice = (String)hs.getAttribute("totalprice");
String card_no= (String)hs.getAttribute("cardno");
System.out.print("pincno:"+card_no);
System.out.print("pincost:"+totalprice);
System.out.print("pinmeth:"+meth);
%>
<form method='post' action='pin'>
<input type="hidden" name="card_no" value="<%=card_no%>">
<input type="hidden" name="paymet" value="<%=meth%>">
<input type="hidden" name="Noofperson" value="<%=Noofperson%>">
<input type="hidden" name="totalprice" value="<%=totalprice%>">
<input type='text' name='pinvalue' placeholder='PIN Number'>
<input type= "submit" value="submit pin">
</form>
</body>
</html>