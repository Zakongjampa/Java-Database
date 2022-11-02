import java.sql.*;

public class ModelB {
    private Connection database;
    private Statement statement;
    private ResultSet results;

    public ModelB(){
        String url ="jdbc:mysql://localhost:3306/world";
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
            //code here
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

    public void displayResults(ResultSet results) throws SQLException{
        boolean records = results.next();

        if(!records){
            System.out.println("No data returned");
            return;
        } 

        try{
            do{
                downRow(results);
            }while(results.next());
        }catch(SQLException e){
            System.err.println("Data displaying error "+e);

            if(database != null){
                database.close();
            }
            System.exit(4);
        }
        
    }


    public void downRow(ResultSet result){
        //enter the downRow Code here
    }

    public static void main(String[] args) {
        ModelB sql = new ModelB();
        System.exit(0);
    }
}
