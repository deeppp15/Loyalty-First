<%@page import="java.sql.*" %>
<%      
        String cid=request.getParameter("cid");
		String fid=request.getParameter("fid");
		String npoints=request.getParameter("npoints");
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"dvora","asoohook");
        Statement stmt=conn.createStatement();
		stmt.setQueryTimeout(3);
		String q="update point_accounts set num_of_points=num_of_points+ "+npoints+" where family_id ="+fid+" and cid!="+cid;
		int rs = stmt.executeUpdate(q); 
		if(rs >= 1) 
		{ 
			out.print("1"); 
		} else 
		{ 
			out.print("0"); 
		}
		conn.close();
%>