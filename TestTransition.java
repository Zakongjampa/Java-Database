import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTransition {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/demo";
        String uname ="root";
        String pword = "Ipians";
        Connection database = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            database = DriverManager.getConnection(url, uname, pword);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try{
            database.setAutoCommit(false);
            String query = "Update user set first_name = 'Tenzin' where id=2;";
            String query1 = "update user set first_name= 'Phurbu', last_name='Lhamo' where id=3;";
            Statement statement1 = database.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Statement statement2 = database.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int result1 = statement1.executeUpdate(query);
            int result2 = statement2.executeUpdate(query1);

            database.commit();
            System.out.println("Result one "+ result1);
            System.out.println("Result two "+ result2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
