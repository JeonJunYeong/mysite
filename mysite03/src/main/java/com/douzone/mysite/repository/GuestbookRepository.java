package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.GuestBookRepositoryException;
import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.GuestbookVo;;

@Repository
public class GuestbookRepository {

public boolean insert(GuestbookVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into guestbook values(null,?,?,?,now())";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());
			
		
			int count = pstmt.executeUpdate();
			
			result = count ==1;
			
			
		}catch (SQLException e) {
			throw new GuestBookRepositoryException(e.getMessage());
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
	
public boolean delete(GuestbookVo vo) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		conn = getConnection();
		
		String sql = "delete from guestbook where no= ? and password=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, vo.getNo());
		pstmt.setString(2, vo.getPassword());
		
		
		
		int count = pstmt.executeUpdate();
		
		result = count ==1;
		
		
	}catch (SQLException e) {
		throw new GuestBookRepositoryException(e.getMessage());
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


	public List<GuestbookVo> findAll(){
		List<GuestbookVo> result =new ArrayList<GuestbookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			String sql= "select name,contents,reg_date,no from guestbook order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//5 결과 가져오기 
			while(rs.next()) {
				
				String name =rs.getString(1);
				String contents =rs.getString(2);
				String reg_date=rs.getString(3);
				long no=rs.getLong(4);
				GuestbookVo vo = new GuestbookVo();
				vo.setName(name);
				vo.setContents(contents);
				vo.setReg_date(reg_date);
				vo.setNo(no);
				result.add(vo);
				
			}
		
		}catch (SQLException e) {
			throw new GuestBookRepositoryException(e.getMessage());
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
			return result;
	}
	
	public boolean delete(Long no, String password) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete from guestbook where no= ? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			
			
			
			int count = pstmt.executeUpdate();
			
			result = count ==1;
			
			
		}catch (SQLException e) {
			throw new GuestBookRepositoryException(e.getMessage());
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
	
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mysql://192.168.1.114:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			
			throw new GuestBookRepositoryException("드라이버 로딩 실패: "+e);
		} 
		
		return conn;
	}

	
	
}
