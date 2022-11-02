import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestPrepareStatement {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/demo";
        String uname = "root";
        String pword = "Ipians";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection database = DriverManager.getConnection(url, uname, pword);
            Statement statement = database.createStatement();
            String query = "show tables;";

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String column = result.getString(1);
                System.out.println(column);
            }

            query = "Select * from users where first_name= ?  and username = ? ";
            PreparedStatement prep = database.prepareStatement(query);
            prep.setString(1, "jamp");
            prep.setString(2, "jampa");

            result = prep.executeQuery();

            while(result.next()){
                String firstName = result.getString(2);
                String secondName = result.getString(3);
                String password = result.getString(5);
                System.out.println(firstName +" "+secondName+" is password is "+password);
            }

            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e){
            System.err.println("Error! ...  MySQL Connection username and password maybe different");
        }

    }
}
