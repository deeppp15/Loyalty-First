import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.lang.*;
import java.sql.*;

@WebServlet("/login")
public class Login extends HttpServlet 
{

       public void doGet(HttpServletRequest request,HttpServletResponse response)
    {
        try
        {
            PrintWriter out=response.getWriter();
            String username=request.getParameter("user");
            String pass=request.getParameter("pass");
            if(username.isBlank() || username.isEmpty() || pass.isBlank() || pass.isEmpty())
            {   
                out.print("Please enter all values");
            }
            else
            {
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
                Connection conn = DriverManager.getConnection(url,"dvora","asoohook");
                Statement stmt = conn.createStatement();
                ResultSet rs=stmt.executeQuery("select cid from login where username='"+username+"' and passwd='"+pass+"'");
                String output;
                
                if(rs.next())
                {
                    output = "Yes:"+rs.getObject(1).toString();
                }
                else
                {  
                        output = "No";
                }
                
                out.print(output);
                conn.close();
            }
        }
        catch(Exception e)
        {
            
        }
        
    }
}