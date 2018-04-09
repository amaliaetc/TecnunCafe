import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class ProductEdit extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        toClient.println(Utils.header("My Account"));
        toClient.println("<form action='UserUpdate' method='GET'>");
        toClient.println("<table border='1'>");
        String id = req.getParameter("UserID");
        UserData user = UserData.getUser(connection, id);
        toClient.println("<tr><td>User Id</td>");
        toClient.println("<td>" + user.UserID + "</td></tr>");
        toClient.println("<tr><td>Name</td>");
        toClient.println("<td><input name='Name' value='" + user.Name + "'></td></tr>");
        toClient.println("<tr><td>Surname</td>");
        toClient.println("<td><input name='Surname' value='" + user.Surname + "'></td>");
        toClient.println("<tr><td>Phone</td>");
        toClient.println("<td><input name='Phone' value='" + user.Phone + "'></td>");
		toClient.println("<tr><td>Email</td>");
        toClient.println("<td><input name='Email' value='" + user.Email + "'></td>");
		toClient.println("<tr><td>Password</td>");
        toClient.println("<td><input name='Password' value='" + user.Password + "'></td>");
        toClient.println("</tr>");
        toClient.println("</table>");
        toClient.println("<input type='submit'>");
        toClient.println("</form>");
        toClient.println(Utils.footer("My Account"));
        toClient.close();
    }
}