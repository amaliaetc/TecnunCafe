import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserData {
    String UserID;
    String Name;
    String Surname;
	String Phone;
	String Email;
	String Password;
	String VoucherID;
	String Meals;
	String Avaliable;
    
    UserData (String UserID, String Name, String Surname, String Phone, String Email, String Password) {
        this.UserID=UserID;
		this.Name=Name;
		this.Surname=Surname;
		this.Phone=Phone;
		this.Email=Email;
		this.Password=Password;
    }
	VoucherData (String UserID,String VoucherID,String Meals,String Avaliable) {
		this.UserID=UserID;
		this.VoucherID=VoucherID;
		this.Meals=Meals;
		this.Avaliable=Avaliable;
	}
	
    public static UserData getUser(Connection connection, String id) {
        String sql = "Select UserID, Name, Surname, Phone, Email, Password FROM Users";
        sql += " WHERE UserID=?";
        System.out.println("getUser: " + sql);
        UserData user = null;;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                user = new UserData(
                    result.getString("UserID"),
                    result.getString("Name"),
                    result.getString("Surname"),
					result.getString("Phone"),
					result.getString("Email"),
					result.getString("Password")
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getUser: " + sql + " Exception: " + e);
        }
        return user;
    }
	
	public static int UpdateData(Connection connection, UserData user) {
        String sql ="UPDATE Users "
            + "SET Name = ?, Surname = ?, Phone = ?"
            + " WHERE UserID= ?";
        System.out.println("UpdateData: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,user.Name);
            stmtUpdate.setInt(2,user.Surname);
            stmtUpdate.setFloat(3,user.Phone);
            stmtUpdate.setString(4,user.UserID);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in UpdateData: " + sql + " Exception: " + e);
        }
        return n;
    }
	
	public static VoucherData UserVouchers(Connection connection, String id) {
        String sql = "Select UserID, VoucherID, Meals, Avaliable FROM Vouchers";
        sql += " WHERE UserID=?";
        System.out.println("UserVouchers: " + sql);
        VoucherData voucher = null;;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                voucher = new VoucherData(
                    result.getString("UserID"),
                    result.getString("VoucherID"),
                    result.getString("Meals"),
					result.getString("Avaliable")
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in UserVouchers: " + sql + " Exception: " + e);
        }
        return voucher;
    }
    
	public static int NewVoucher(Connection connection, VoucherData voucher) {
        String sql ="INSERT INTO [Vouchers] (UserID, Meals, Avaliable) "
            + "VALUES (?, ?, ?)";
        System.out.println("updateProduct: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setInt(1,voucher.UserID);
            stmtUpdate.setInt(2,voucher.Meals);
            stmtUpdate.setInt(3,voucher.Avaliable);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in NewVoucher: " + sql + " Exception: " + e);
        }
        return n;
    }
}