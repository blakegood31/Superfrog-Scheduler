import java.sql.*;

public class DatabaseConnector{

    private Connection connection;

    public DatabaseConnector(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Superfrog_Scheduler", null, null);
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }
    }

    public void testQuery(){
        try{
            Statement statement;
            statement = this.connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                "select * from student");
            System.out.println(resultSet);
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
    
}
