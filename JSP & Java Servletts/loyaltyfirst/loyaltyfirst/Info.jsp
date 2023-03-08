<%@page import="java.sql.*" %>
<%      
        String cid=request.getParameter("cid");
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"dvora","asoohook");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select c.cname,p.num_of_points from point_accounts p inner join customers c on c.cid=p.cid where c.cid="+cid);
        String output="";
        if(rs.next())
        {
            output=rs.getObject(1)+","+rs.getObject(2)+"#";    
        }
		conn.close();
        out.print(output);
%>
