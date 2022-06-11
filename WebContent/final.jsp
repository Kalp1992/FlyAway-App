<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.Connection"%>
    <%@ page import="com.flyaway.util.ConnectionManagerImpl"%>
     <%@ page import="java.sql.PreparedStatement"%>
     <%@ page import="java.sql.ResultSet"%>
      <%@ page import="java.math.BigDecimal"%>
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
<%Connection connection;
connection = new ConnectionManagerImpl().getConnection();
String card_no = (request.getParameter("cardno"));
int mm = Integer.parseInt(request.getParameter("MM"));
int yy = Integer.parseInt(request.getParameter("YY"));
int cvv = Integer.parseInt(request.getParameter("cvv"));
String totalprice = request.getParameter("totalprice");
String name = request.getParameter("name");
String Noofperson = (request.getParameter("Noofperson"));
String meth = (request.getParameter("paymet"));
System.out.println("fmeth"+meth);
System.out.println("mm"+mm);
System.out.println("yy"+yy);
System.out.println("cvv"+cvv);
System.out.println("totalprice"+totalprice);
PreparedStatement st = null;
ResultSet rs = null;
st = connection.prepareStatement("select mm,yy,cvv,type from cardtype where card_no = ?;");
st.setBigDecimal(1, new BigDecimal(card_no));
rs = st.executeQuery();
int mon=0;
int year=0;
int cv=0;
String type="";
RequestDispatcher rd = null;
while(rs.next()){
	mon = rs.getInt(1);
	year = rs.getInt(2);
	cv = rs.getInt(3);
	type = rs.getString(4);
	
	System.out.println("mon"+mon);
	System.out.println("year"+year);
	System.out.println("cv"+cv);
	HttpSession hs = request.getSession();
if((mon == mm) && (yy == year) && (cvv == cv)){
	hs.setAttribute("cardno", card_no);
	hs.setAttribute("totalprice", totalprice);
	hs.setAttribute("paymet", meth);
	hs.setAttribute("Noofperson", Noofperson);
	System.out.print("hfmeth: "+meth);
	rd =  request.getRequestDispatcher("pin.jsp");
	rd.forward(request, response);
}
else{
	rd = request.getRequestDispatcher("failure.jsp");
	rd.forward(request, response);
}
}
%>


</body>
</html>