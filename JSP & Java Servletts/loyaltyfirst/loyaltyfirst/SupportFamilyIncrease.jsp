<%@page import="java.sql.*" %>
<%      
        String cid=request.getParameter("cid");
		    String tref=request.getParameter("tref");
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"dvora","asoohook");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select c.family_id,pa.percent_added,t.t_points from customers c inner join point_accounts pa on pa.cid=c.cid inner join transactions t on t.cid=c.cid where c.cid="+cid+" and t.tref='"+tref+"'");
        String output="";
        while(rs.next())
        {
            
            output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+"#";
            
            }
			    conn.close();
        out.print(output);

%>
