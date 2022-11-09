import java.sql.*;

public class gettingColumns {
    private Connection database;
    private Statement statement;
    private ResultSet results;

    public gettingColumns(){
        String url ="jdbc:mysql://localhost:3306/customer";
        String uname ="root";
        String pword = "Ipians";

        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            database = DriverManager.getConnection(url, uname, pword);

        } catch (ClassNotFoundException e) {
            System.err.println("Unable to connect to JDBC / ODBC bridge "+e);
            System.exit(1);
        } catch (SQLException e) {
            System.err.println("Cannot Connect to database "+e);
            System.exit(2);
        }

        try{
        //    String query = new String("Select lastName from Customers");
        //  QUERY TO GET A SINGLE COLUMNS 

        //    String query= "Select FirstName, LastName from Customers";
            // CODE TO GET MULTIPLE COLUMNS 
            String query  = "Select firstName, lastName, DateOfFirstPrder from customers where customerNumber = 1";
           statement =database.createStatement();
           results = statement.executeQuery(query);
           displayResults(results);
           statement.close();
        }catch(SQLException e){
            System.err.println("SQL error "+ e);
            if(database != null){
                try {
                    database.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        System.exit(3);
    }

    public void displayResults(ResultSet results) throws SQLException {
        boolean records = results.next();

        if (!records) {
            System.out.println("No data returned");
            return;
        }

        try {
            do { 
                downRow(results);
            } while (results.next());
        } catch (SQLException e) {
            System.err.println("Data displaying error " + e);

            if (database != null) {
                database.close();
            }
            System.exit(4);
        }

    }

    public void downRow(ResultSet result) {
        try {
            String firstName = result.getString(1);
            String lastName = result.getString(2);
            String name = firstName +" "+ lastName;

            String date = result.getString(3);
            System.out.println("Name : "+name);
            System.out.println("Date of First Order : "+date);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
