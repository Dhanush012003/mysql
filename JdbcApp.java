package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class JdbcApp {

	public static void main(String[] args) {
		// STEP 1: Load the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//step:2 Establishing connection
		Connection con = null;

					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:8888/emploo","root","123456789");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			if(con != null)
				System.out.println("Success");
			else
				System.out.println("Not Connected");
			
			//Step:3 Creating Statement
			Scanner sc=new Scanner(System.in);
			int empid = sc.nextInt();
			String empName=sc.next();
			int empSalary=sc.nextInt();
			String sql="insert into employee value(?,?,?)";
			
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setInt(1,empid);
				ps.setString(2,empName);
				ps.setInt(3,empSalary);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("**Values are added successfully**");
			
			String viewSql="select * from employee";
	
			try {
				PreparedStatement view =con.prepareStatement(viewSql);
				ResultSet rs=view.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getInt("empid"));
					System.out.println(rs.getString("empName"));
					System.out.println(rs.getInt("empSalary"));}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				

			
	}
}