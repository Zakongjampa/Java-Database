import java.sql.*;

public class InsertingRow {
    private Connection database;
    private Statement statement;

    public InsertingRow(){

        String url ="jdbc:mysql://localhost:3306/customer";
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

            String query = "Insert into Customers ( CustomerNumber, FirstName, LastName, DateOfFirstPrder)"+
            "values (1, 'Tenzin', 'Dolkar', CURRENT_DATE)";
            // Here Inserting date into it
            // " Values ( 1, 'Mary', 'Smith', '2001-10-01')";


            // query =  "Insert into Customers (CustomerNumber, FirstName, LastName, DateOfFirstPrder)"+
            // " Values (2, 'Bob', 'Johns', CURRENT_TIME)";

            // query = "insert into customers values(3, 'Phurbu', 'Lhamo', CURRENT_TIMESTAMP())";

            // INSERT CURRENT / DATE AND TIME IN DATABASE USING 
            // BY USING ACTUAL DATE AND TIME 
            // BY USING CURRENT_DATE
            // BY USING CURRENT_TIME 
            // BY USING CURRENT_TIMESTAMP()

            
            statement = database.createStatement();
            statement.executeUpdate(query);
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
