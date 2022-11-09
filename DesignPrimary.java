import java.sql.*;

public class DesignPrimary {
    private Connection database;
    private Statement statement;

    public DesignPrimary(){

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
            String query = new String("Create table Orders("+
            "OrderNumber Char(30) NOT NULL,"+
            "CustomerNumber Char(30),"+
            "ProductNumber char(30),"+
            "constraint PRIMARY KEY ORDERS_PK (OrderNumber))");
            System.out.println(query);

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
