import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestPropertiesDBCon {
    public static void main(String[] args) {
        Connection db = null;
        Properties props = new Properties(); 
        // If there is need of other information before making the connection then we have to use the properties file and sent it to the databases
        
        String url = "jdbc:mysql://localhost:3306/world";
        

        try (FileInputStream fin = new FileInputStream("DBproperties.txt")) {
            props.load(fin);
        } catch (IOException e) {
            System.err.println("Error loading propFile");
            System.err.println(e.getMessage());
            System.exit(1);
        }

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            db = DriverManager.getConnection(url, props);
        }catch(ClassNotFoundException e){
            System.err.println("Unable to load JDBC / ODBC "+e.getMessage());
            System.exit(2);
        } catch (SQLException e) {
            System.err.println("Error in making the connection");
            System.err.println("Check username and password ");
            System.err.println(e.getMessage());
            System.exit(3);
        }


        
    }
}
