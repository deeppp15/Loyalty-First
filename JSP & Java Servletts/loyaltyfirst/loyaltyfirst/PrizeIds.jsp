<%@page import="java.sql.*" %>
<%      
      String cid=request.getParameter("cid");
      DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
      String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
      Connection conn=DriverManager.getConnection(url,"dvora","asoohook");
      Statement stmt=conn.createStatement();
      ResultSet rs=stmt.executeQuery("select distinct prize_id from redemption_history where cid="+cid);
      String output="";
      while(rs.next())
      {
            output += rs.getObject(1)+"#";
      }
			conn.close();
      out.print(output);
%>
