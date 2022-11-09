import java.sql.*;


public class TableIndexing {
    private Connection database;
    private Statement statement;

    public TableIndexing(){

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
            // Primary index 
            // String query = "CREATE UNIQUE INDEX CUSTNUM "+
            // "ON customerAddress(CustomerNumber)";

            statement = database.createStatement();
            // statement.execute(query);
            // System.out.println("Done");

            // Creating secondary index 
            // String query = "CREATE Index CustZip On CustomerAddress(CustomerZip)";
            // statement.execute(query);

            // CREATING CLUSTERED INDEX
            String query = new String("Create Index CustName "+
            "on Customers(lastName, FirstName)");
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
