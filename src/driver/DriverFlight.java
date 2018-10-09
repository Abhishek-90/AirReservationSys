import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
public class DriverFlight
{
    private int flag;
    //stores the User object returned by getFormDetails()
    
    private Connection con;
    private Statement st;
    private Traveller t;
    
    DriverFlight()
    {
        con =null;
        st= null;
        
        try
        {
            //registering the driver
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(ClassNotFoundException e)
                {
            System.out.println("Driver registration error!");
            System.exit(1);
        }
        // add username password and url of Flight Records DATABASE
        
        try
        {
         Properties prop = new Properties();
         prop.load(new FileInputStream("FlightDb.properites"));
            
        String user = prop.getProperty("user");
        String password= prop.getProperty("password");
        String dburl= prop.getProperty("dburl");
        con= DriverManager.getConnection(dburl, user, password);
        }
        
        catch(Exception e)
        {
            
        }
        
        public void setFlightData()
        {
            
        }
        
    }
}

