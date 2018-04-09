import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class UserUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String id = req.getParameter("UserID");
        UserData user = new UserData(
                    id,
                    req.getParameter("Name"),
                    req.getParameter("Surname"),
                    req.getParameter("Phone")
                );
        int n = UserData.UpdateData(connection, user);
        res.sendRedirect("ProductEdit?id=" + idStr + "&a=" + Math.random());
    }
}