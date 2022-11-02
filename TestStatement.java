import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestStatement {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/demo";
        String username = "root";
        String password = "Ipians";
        Connection db = null;
        Statement statement = null;
        ResultSet result;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            db = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String query = "Show tables;";
            statement = db.createStatement();
            // It will give us the result of only one query 
            result = statement.executeQuery(query);


            while (result.next()) {
                String col = result.getString(1);
                System.out.println(col);
            }

            // query = "insert into users (first_name, last_name, username, password) values ('tenzin', 'yeshi', 'tenyesh', '321')";

            // Here we have munipulate the data by updating or inserting a new row 
            // int resultInt = statement.executeUpdate(query);

            // if(resultInt ==  1){
            //     System.out.println("Updated Successfully");
            // }


            query = "Select * from todos;";
            boolean resultBool = statement.execute(query);
            // use to execute multiple line of code 

            if(!resultBool){
                System.err.println("Some error occurs");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}