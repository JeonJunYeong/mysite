package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.douzone.mysite.vo.UserVo;

public class UserRepository {
	
public boolean insert(UserVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into user values(null,?,?,?,?,now())";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
		
			int count = pstmt.executeUpdate();
			
			result = count ==1;
			
			
		}catch (SQLException e) {
			System.out.println("ERROR:"+e);
		}finally {
			//6.자원정리
			try {
				if(pstmt !=null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
public boolean update(UserVo vo) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		conn = getConnection();
		String sql="";
		int count=0;
		if("".equals(vo.getPassword())) {
		sql = "update user set name=?,gender=? where no=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,vo.getName());
		pstmt.setString(2,vo.getGender());
		pstmt.setLong(3,vo.getNo());
		count = pstmt.executeUpdate();
			
		}else {
			sql = "update user set name=?,gender=?,password=? where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,vo.getName());
			pstmt.setString(2,vo.getGender());
			pstmt.setString(3, vo.getPassword());
			pstmt.setLong(4,vo.getNo());
			count = pstmt.executeUpdate();
		}
		
		result = count ==1;
		System.out.println(result);
		
	}catch (SQLException e) {
		System.out.println("ERROR:"+e);
	}finally {
		//6.자원정리
		try {
			if(pstmt !=null) {
				pstmt.close();
			}
			if(conn !=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	return result;
}


public UserVo findByEmailAndPassword(UserVo vo) {
	UserVo userVo=null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		conn=getConnection();
		String sql= "select no,name from user where email = ? and password = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getEmail());
		pstmt.setString(2, vo.getPassword());
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			Long no = rs.getLong(1);
			String name = rs.getString(2);
			
			userVo=new UserVo();
			userVo.setNo(no);
			userVo.setName(name);
		}
	
	}catch (SQLException e) {
		System.out.println("ERROR:"+e);
	}finally {
		//6.자원정리
		try {
			if(rs != null) {
				rs.close();
			}			
			if(pstmt !=null) {
				pstmt.close();
			}
			if(conn !=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return userVo;
}
public UserVo findByNo(long no) {
	UserVo userVo=null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		conn=getConnection();
		String sql= "select name,password,gender,email from user where no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, no);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			String name=rs.getString(1);
			String password=rs.getString(2);
			String gender=rs.getString(3);
			String email=rs.getString(4);
			
			userVo=new UserVo();
			userVo.setPassword(password);
			userVo.setGender(gender);
			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setNo(no);
			
			
		}
	
	}catch (SQLException e) {
		System.out.println("ERROR:"+e);
	}finally {
		//6.자원정리
		try {
			if(rs != null) {
				rs.close();
			}			
			if(pstmt !=null) {
				pstmt.close();
			}
			if(conn !=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return userVo;
}


private Connection getConnection() throws SQLException {

	Connection conn = null;
	
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		
		String url = "jdbc:mysql://192.168.1.114:3307/webdb";
		conn = DriverManager.getConnection(url, "webdb", "webdb");
	} catch (ClassNotFoundException e) {
		System.out.println("드라이버 로딩 실패:" + e);
	} 
	
	return conn;
}
}
