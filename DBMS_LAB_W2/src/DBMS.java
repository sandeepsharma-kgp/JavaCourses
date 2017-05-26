import java.sql.*;

/**
 * Created by BREEZY on 24-Jan-17.
 */
        //import java.math.*;
public class DBMS {
    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String url= "jdbc:mysql://10.5.18.103/13MI31025";
    static final String user = "13MI31025";
    static final String pass = "cse12";
    public static void main(String args[])
    {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(url,user,pass);
            stmt = conn.createStatement();
            String s1,s2,s3,s4,s5,sp=" ",result;
            ResultSet q;
            // Query 1:List all the Courses taught by the teacher - “PPC”.
            s1="Select * From Course Where E_Name='PPC'";
            q=stmt.executeQuery(s1);
            while(q.next()){
                result=q.getString("Dept")+sp+q.getString("Cid")+sp+q.getString("Cname");
                //Display Values
                System.out.println(result);
            }

            //Query 2: List all students registered in the courses taught by “PPC”.
            s2="select S_Name,S_Roll_No from Class where E_Name='PPC'";
            q=stmt.executeQuery(s2);
            while(q.next()){
                result=q.getString("S_Name")+sp+q.getString("S_Roll_No");
                //Display Values
                System.out.println(result);
            }
            //Query 3: List the timings of all courses in Class-Room “NC142”.
            s3="select Cid,Day,Start_t,Hour from Timings where Room_No='NC142'";
            q=stmt.executeQuery(s3);
            while(q.next()){
                result=q.getString("Cid")+sp+q.getString("Day")+sp+q.getString("Start_t")+sp+q.getString("Hour");
                //Display Values
                System.out.println(result);
            }
            //Query 4: List the name of the students who received the highest marks in the courses taught by “PPC”
            s4="select Student.S_Name,Student.Roll_No,max(Marks.Overall) as Max_Marks,Course.Cid from Marks,Class,Student,Course where Marks.Cid=Class.Cid and Marks.Roll_No=Class.Roll_No and Class.E_Name='PPC' and Marks.Roll_No=Student.Roll_No and Marks.Cid=Course.Cid group by Course.Cid";
            q=stmt.executeQuery(s4);
            while(q.next()){
                result=q.getString("S_Name")+sp+q.getString("Roll_No")+sp;
                float g=q.getFloat("Max_Marks");
                //Display Values
                System.out.print(result);
                System.out.println(g);
            }
            //Query 5:List the students who have received a grade of “EX” in the largest number of courses.
            s5="select a.Roll_No,a.S_Name,max(Ex_Count) as f from ( select Student.S_Name,Grade_Card.Roll_No,count(Grade) as EX_count from Grade_Card,Student where Grade_Card.Roll_No=Student.Roll_No and Grade='EX' group by Grade_Card.Roll_No) a group by a.Roll_No and a.S_Name";
            q=stmt.executeQuery(s5);
            while(q.next()){
                result=q.getString("S_Name")+sp+q.getString("Roll_No")+sp;
                int b=q.getInt("f");
                //Display Values
                System.out.print(result);
                System.out.println(b);
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}