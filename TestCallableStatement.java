import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class TestCallableStatement {
    public static void main(String[] args) {
        String url = "jdbc:http://localhost:3305/demo";
        String uname ="root";
        String pword = "Ipians";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection database = DriverManager.getConnection(url, uname, pword);
            Statement statement = database.createStatement();
            String query = "Show tables;";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                String column = result.getString(1);
                System.out.println(column);
            }

            query = "{ CALL first_name (?)}";
            CallableStatement cStatement = database.prepareCall(query);
            cStatement.registerOutParameter(1, Types.VARCHAR);




        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
