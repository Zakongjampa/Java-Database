import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestResultSet {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/demo";
        String uname = "root";
        String pword = "Ipians";
        Connection database=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            database = DriverManager.getConnection(url, uname, pword);
           
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to load JDBC / ODBC ...... driver");
            System.err.println(e.getMessage());
        }catch(SQLException e){
            System.err.println("Error in username or password!");
            System.err.println(e.getMessage());
        }   
        
        try (Statement statement = database.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            String query = "Select * from users;";

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String firstName = result.getString(2);
                String secondName = result.getString(3);
                String password = result.getString(5);

                String combined = firstName + " " + secondName + "'s password is " + password;
                System.out.println(combined);

                if(result.getString(1).equals("3")){
                    // We can use the result directly to update the row from result
                    // UPDATABLE RESULTSET
                    result.updateString("first_name", "Sonam");
                    result.updateString("last_name", "Dolma");
                    result.updateString("username", "sonDol");
                    result.updateString("password", "test");
                    result.updateRow();
                }
            }

            // result.deleteRow();
            // this code will delete a row from the table. 

            result.close();
        } catch (SQLException e) {
           System.out.println("Error in SQL");
           System.out.println(e.getMessage());
        }

    }
}
