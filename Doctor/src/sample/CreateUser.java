package sample;
//STEP 1. Import required packages
import java.sql.*;
public class CreateUser {


        // JDBC driver name and database URL
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://localhost/doctor";

        //  Database credentials
        static final String USER = "root";
        static final String PASS = "";

        public static void main(String[] args) {
            Connection conn = null;
            Statement stmt = null;
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                String sql;
                sql = "SELECT * FROM users";
                ResultSet rs = stmt.executeQuery(sql);

                //STEP 5: Extract data from result set
                while(rs.next()){
                    //Retrieve by column name
                    String email  = rs.getString("email");
                    String password = rs.getString("password");
                    System.out.println(email+"\n"+password);
                }
                //STEP 6: Clean-up environment
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
        }//end main
    public static boolean addDoctor(String f_name,String l_name,String edu_info,String spec,String address, String nid,int fee ,String email, String password){
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            PreparedStatement ps=conn.prepareStatement("INSERT INTO `doctors`(`f_name`, `l_name`, `edu_info`, `spec`, `address`, `nid`, `fee`, `email`, `password`) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1,f_name);
            ps.setString(2,l_name);
            ps.setString(3,edu_info);
            ps.setString(4,spec);
            ps.setString(5,address);
            ps.setString(6,nid);
            ps.setInt(7,fee);
            ps.setString(8,email);
            ps.setString(9,password);
            int status=ps.executeUpdate();
            conn.close();


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
            return false;
    }

    public  static boolean addPatient(String f_name,String l_name,String blood_group,int weight, String gender , int age ,String email, String password){
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            PreparedStatement ps=conn.prepareStatement("INSERT INTO `patients`(`f_name`, `l_name`, `blood_group`, `weight`, `gender`, `age`, `email`, `password`)  VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1,f_name);
            ps.setString(2,l_name);
            ps.setString(3,blood_group);
            ps.setInt(4,weight);
            ps.setString(5,gender);
            ps.setInt(6,age);
            ps.setString(7,email);
            ps.setString(8,password);
            int status=ps.executeUpdate();
            conn.close();


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return false;

    }


    }//end FirstExample