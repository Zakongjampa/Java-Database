import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDbConn {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password ="Ipians";
        Connection con = null;
        Statement statement;
        ResultSet result = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Unable to connect to JDBC/ODBC driver" + e);
            System.exit(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Can not connect to the database");
            System.exit(2);
        }

        try {
            String query = "show tables;";
            statement = con.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            
            System.exit(3);
        }


        try {
            if(!result.next()){
                System.out.println("No data");
                System.exit(4);
            }else{
                do{
                   String col = result.getString(1);
                   System.out.println(col);
                }while(result.next());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





        
    }
}
