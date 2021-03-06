/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package driver;

import window.java.Traveller;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import window.java.FlightData;
import window.java.User;
/**
 *
 * @author akgy2
 */
public class DriverFlight {
    //stores the status of the operation
    private int flag;
    //stores the User object returned by getFormDetails()
    private User u;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private Traveller t= new Traveller();
    private FlightData fd[];
   public DriverFlight(){
        con =null;
        st= null;
        
       
        try{
            
            //registering the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        }
        catch(ClassNotFoundException e)
                {
            System.out.println("Driver registration error!");
            System.exit(1);
        }
        try{
            //loading the properties file
            InputStream is ;
            is= DriverClass.class.getResourceAsStream("/driver/userdb.properties");
            Properties prop = new Properties();
            if(is!=null)
            {//prop.load(new FileInputStream("userdb.properties"));
            prop.load(is);
                System.out.println("User id"+prop.getProperty("user"));
                System.out.println("Password "+prop.getProperty("password"));
                System.out.println("url "+prop.getProperty("dburl"));
            }
            else{System.out.println("Properties file error");}
            
            String user = prop.getProperty("user");
            String password= prop.getProperty("password");
            String dburl= prop.getProperty("dburl");
            
            con= DriverManager.getConnection(dburl, user, password);
            
             System.out.println("Creating statement...");
                st = con.createStatement();
                String sql;
                //sample query
                sql = "SELECT * FROM flight";
                rs = st.executeQuery(sql);
                
                while(rs.next())
                    {
                        String fno= rs.getString("Flight_Number");
                        System.out.println("FLight Numbers:"+fno);
                    }
                
                
           // rs.close();
           // st.close();
           // con.close();
                }
        catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
        }
        catch(IOException e){
            System.out.println("Unknown error");
            e.printStackTrace();
        }
        
        finally{
      //finally block used to close resources
            /*try{
            if(st!=null)
              st.close();
         }
           catch(SQLException se2){
            }// nothing we can do
      try{
         if(con!=null)
            con.close();
      }catch(SQLException se){
         se.printStackTrace();
      
      }*///end finally try
   }//end try
    
    }//end of constructor
    
    public void close()
    {
       try{
           if(con!=null)
            con.close();} catch(SQLException e){}
        try {
                if(st!=null)
            st.close();} catch(SQLException e){}
        try {   if(st!=null)
                rs.close();}catch(SQLException e){}
    }
   
    public void setTravellerData(Traveller t)
    {
        this.t=t;
    }
    
    public FlightData[] getRecords()
    {
        rs
    }
    
    FlightData fd=new FlightData();
   fd.setFlightNumber(rs.getString("Flight_Number"))
}

if(!rs.next())
{

}
else
{
    rs.first();
int i=0;

while(rs.next())
{
    fd[i]
}
}