import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnection{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password ="Ipians";
        Connection db = null;
        Statement statement= null;
        ResultSet result;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            db = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try{
            String query = "Show tables;";
            statement = db.createStatement();
            result = statement.executeQuery(query);
            
            while(result.next()){
                String col = result.getString(1);
                System.out.println(col);
            }
        }catch(SQLException error){
            error.printStackTrace();
        }
    }
}