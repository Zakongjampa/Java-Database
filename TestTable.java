import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTable {
    private Connection database;
    private Statement statement;

    public TestTable(){

        String url ="jdbc:mysql://localhost:3306/demo";
        String uname = "root";
        String pword ="Ipians";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            database = DriverManager.getConnection(url, uname, pword);
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to connect to JDBC / ODBC bridge "+e);
            System.exit(1);
        } catch (SQLException e) {
            System.err.println("Can't connect to database "+e);
            if(database != null){
                try {
                    database.close();
                } catch (SQLException e1) {
                    System.err.println(e.getMessage());
                }
            }
            System.exit(2);
        }


        try{
            // creating a table in SQL
            String query = new String("CREATE TABLE CustomerAddress ("+
            "CustomerNumber CHAR(30) NOT NULL,"+
            "CustomerStreet CHAR(30) NOT NULL,"+
            "CustomerCity CHAR(30) NOT NULL,"+
            "CustomerZip CHAR(30) NOT NULL DEFAULT '562109')"
            );

            statement = database.createStatement();
            statement.execute(query);
            statement.close();
        }catch(SQLException e){
            System.err.println("SQL error : "+e);

            if(database != null){
                try{
                    database.close();
                }catch(SQLException e1){
                    System.err.println(e1.getMessage());
                }
            }
            System.exit(3);
        }


        if(database != null){
            try {
                database.close();
            } catch (SQLException e) {
                System.err.println("closing err"+ e);
            }
        }


    }
    
}
