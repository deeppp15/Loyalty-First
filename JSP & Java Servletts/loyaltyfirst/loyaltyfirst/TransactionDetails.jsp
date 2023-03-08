<%@page import="java.sql.*" %>
<%      
        String tref=request.getParameter("tref");
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"dvora","asoohook");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select t.t_date, t.t_points, p.prod_name, p.prod_points, pt.quantity from transactions t inner join transactions_products pt on pt.tref=t.tref inner join products p on p.prod_id=pt.prod_id where t.tref='"+tref+"'");
        String output="";
        while(rs.next())
        {
            output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4)+","+rs.getObject(5)+"#";
        }
			  conn.close();
        out.print(output);
%>
