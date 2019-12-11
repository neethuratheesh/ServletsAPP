package com.h2kinfosys.teachers.service;

import java.sql.Connection;

//import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;
//import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

public class TeacherDAO {
	
	private static String url = "jdbc:mysql://localhost:3306/sakila";
	private static String userName = "root";
	private static String password = "shreya@123";
	private  String query = "select * from teacher";
	private  String insertquery = "Insert into teacher (teacher_id,first_name,last_name,skill)"
			+"values(?,?,?,?)"; 
	
	public static Connection getConnection(){
		Connection conn = null;
		
	
		
			
			//Driver mysqldriver = new Driver();
			//DriverManager.registerDriver(mysqldriver);
			try {
				//Driver mysqldriver = new Driver();
				//DriverManager.registerDriver(mysqldriver);
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url,userName,password);
			} 			
							
			
		
		
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;
	}
	
	public TeacherDTO getTeacherID(String id) {
		TeacherDTO teacher = null;
		Connection conn = getConnection();
		try {
			PreparedStatement pstats = conn.prepareStatement(query);
			pstats.setInt(1,Integer.parseInt(id));
			ResultSet rs = pstats.executeQuery();
			while (rs.next()) {
				teacher = new TeacherDTO();
				teacher.setTeacherID(rs.getInt("teacher_id"));
				teacher.setFirstName(rs.getString("first_name"));
				teacher.setLastName(rs.getString("last_name"));
				teacher.setSkill(rs.getString("skill"));
				
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}
	
	public int  saveTeacher(TeacherDTO teacher) {
		int teacherID = 0;
		Connection conn = getConnection();
		try {
			PreparedStatement pstats = conn.prepareStatement(insertquery);
			pstats.setInt(1,teacher.getTeacherID());
			pstats.setString(2, teacher.getFirstName());
			pstats.setString(3, teacher.getLastName());
			pstats.setString(4, teacher.getSkill());
			int rowsaffected = pstats.executeUpdate();
			System.out.println("No of rows affected :" +rowsaffected);
			PreparedStatement pstat = conn.prepareStatement(query);
		    ResultSet rs = pstat.executeQuery();
			if (rs!=null) {
				while(rs.next()) {
					teacherID = rs.getInt("teacher_id");
				}
			}
			
			
			conn.close();	
			
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return teacherID;
		
	}

}
