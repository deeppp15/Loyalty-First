<%@page import="java.sql.*" %>
<%      
        String cid=request.getParameter("cid");
		    String prizeid=request.getParameter("prizeid");
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"dvora","asoohook");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select p.p_description,p.points_needed,r.r_date,e.center_name from redemption_history r inner join customers c on r.cid=c.cid inner join prizes p on r.prize_id=p.prize_id inner join exchgcenters e on r.center_id=e.center_id where c.cid="+cid+" and p.prize_id="+prizeid);
        String output="";
        while(rs.next())
        { 
            output += rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4)+"#";  
        }
			  conn.close();
        out.print(output);
%>
