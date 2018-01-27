package dao.classes;

import dao.interfaces.UserDao;
import model.classes.UserImpl;
import model.interfaces.User;

import java.sql.*;

public class UserDaoImpl implements UserDao{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql/localhost:3306/dbim";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "test";

    public UserImpl getUser(int id) {
        Connection conn = null;
        Statement stmt = null;
        UserImpl usr = new UserImpl();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM user WHERE id = "+id;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Retrieve by column name
                usr.setUserId(rs.getInt("id"));
                usr.setUsername(rs.getString("Name" ));
                usr.setPassword(rs.getString("Password" ));
                usr.setActive(rs.getBoolean("active"));
            }

        } catch (SQLException se) {
            se.printStackTrace();;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usr;
    }
}
