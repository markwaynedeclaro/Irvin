package org.irvin.UserManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

	Connection con = null;

	public UserRepository() 
	{
		String url = "jdbc:mysql://tms.cubhkvpwiemb.ap-southeast-2.rds.amazonaws.com:3306/TMS";
		String username = "MoksDb";
		String password = "MoksDbPassword";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public List<User> getUsers() 
	{
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users";
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) 
			{
				User u = new User();
				u.setEmployeeID(rs.getString("employee_id"));
				u.setPassword("");
				u.setFirstName(rs.getString("first_name"));
				u.setMiddleName(rs.getString("middle_name"));
				u.setLastName(rs.getString("last_name"));
				u.setLevelID(rs.getString("level_id"));
				u.setTeamID(rs.getString("team_id"));
				u.setSupervisorID(rs.getString("supervisor_id"));
				
				users.add(u);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return users;
	}
	
	
	public User getUser(String id) 
	{
		User u = new User();
		String sql = "SELECT * FROM users WHERE employee_id = ?";
		PreparedStatement preparedStatement = null;
		try
		{
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) 
			{
				u.setEmployeeID(rs.getString("employee_id"));
				u.setPassword("");
				u.setFirstName(rs.getString("first_name"));
				u.setMiddleName(rs.getString("middle_name"));
				u.setLastName(rs.getString("last_name"));
				u.setLevelID(rs.getString("level_id"));
				u.setTeamID(rs.getString("team_id"));
				u.setSupervisorID(rs.getString("supervisor_id"));
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return u;
	}	
	
	
	public void create(User user) 
	{
		String sql = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try
		{
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, user.getEmployeeID());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getMiddleName());
			preparedStatement.setString(5, user.getLastName());
			preparedStatement.setString(6, user.getTeamID());
			preparedStatement.setString(7, user.getSupervisorID());
			preparedStatement.setString(8, user.getLevelID());
			preparedStatement.executeUpdate();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}	

	public void update(User user) 
	{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE users SET ");
		sql.append(" employee_password=?, first_name=?, middle_name=?, last_name=?, ");
		sql.append(" team_id=?, supervisor_id=?, level_id=? ");
		sql.append("WHERE employee_id=?");
		PreparedStatement preparedStatement = null;
		try
		{
			preparedStatement = con.prepareStatement(sql.toString());
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getMiddleName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getTeamID());
			preparedStatement.setString(6, user.getSupervisorID());
			preparedStatement.setString(7, user.getLevelID());
			preparedStatement.setString(8, user.getEmployeeID());
			preparedStatement.executeUpdate();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}	
	
	
	public void delete(User user) 
	{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM users ");
		sql.append("WHERE employee_id=?");
		PreparedStatement preparedStatement = null;
		try
		{
			preparedStatement = con.prepareStatement(sql.toString());
			preparedStatement.setString(1, user.getEmployeeID());
			preparedStatement.executeUpdate();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}		
}
