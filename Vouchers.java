import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class Vouchers extends HttpServlet {
	
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        toClient.println(Utils.header("Vouchers"));
        toClient.println("<form action='BuyVoucher' method='GET'>");
        String id = req.getParameter("UserID");
        toClient.println("<p align='center'");
        toClient.println("<div class='menu'><h1>Choose your voucher:</h1></div>");
        toClient.println("<div class='vouchers'><input type='radio' name='meals' value='5'>5 meals - 30 euro</div>");
        toClient.println("<div class='vouchers'><input type='radio' name='meals' value='10'>10 meals - 58 euro</div>");
		toClient.println("<div class='vouchers'><input type='radio' name='meals' value='15'>15 meals - 80 euro</div>");
		toClient.println("<div class='vouchers'><input type='radio' name='meals' value='20'>20 meals - 105 euro</div>");
		toClient.println("<input type='submit'>");
		toClient.println("</p>");
        toClient.println("</form>");
        toClient.println(Utils.footer("Vouchers"));
        toClient.close();
    }
}