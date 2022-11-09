import java.sql.*;
public class DropingTable {

    private Connection database;
    private Statement statement;

    public DropingTable(){

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
            String query = new String("DROP TABLE customeraddress");
            statement = database.createStatement();
            statement.execute(query);
            System.out.println("Done");
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

    public static void main(String[] args) {
        DropingTable drop = new DropingTable();
    }
}
