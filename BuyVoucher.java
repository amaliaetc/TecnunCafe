import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class BuyVoucher extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("UserID"));
		int numMeals = Integer.parseInt(req.getParameter("meals"));
        VoucherData voucher = new VoucherData(
                    id,
                    numMeals,
                    Integer.parseInt(req.getParameter("Avaliable"))
        );
        int n = UserData.NewVoucher(connection, voucher);

        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        toClient.println(Utils.header("Order updated"));
        OrderEdit.printOrder(toClient, connection, orderId);
        toClient.println(Utils.footer("Order updated"));
        toClient.close();
    }
}