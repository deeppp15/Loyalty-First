<%@page import="java.sql.*" %>
<%      
      String cid = request.getParameter("cid");
      DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
      String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
      Connection conn = DriverManager.getConnection(url,"dvora","asoohook");
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select t.tref,t.t_date,t.t_points,t.amount from customers c inner join transactions t on t.cid=c.cid where c.cid="+cid+" order by t.t_date desc");
      String output = "";
      while(rs.next())
      {
          output+=rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4)+"#";   
      }
			conn.close();
      out.print(output);
%>
