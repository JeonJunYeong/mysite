package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardRepository {

public boolean insert(BoardVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into board values(null,?,?,?,now(),?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getHit());
			
			pstmt.setLong(4, vo.getG_no());
			pstmt.setLong(5,vo.getO_no());
			pstmt.setLong(6, vo.getDepth());
			pstmt.setLong(7, vo.getUserNo());
		
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

public boolean update(BoardVo vo) {
	
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		conn = getConnection();
		
		String sql = "update board set title=?,contents=? where no=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContents());
		pstmt.setLong(3, vo.getNo());
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

public boolean delete(long no) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		conn = getConnection();
		
		String sql = "delete from board where no =?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, no);
		
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
public List<BoardVo> findAll(){
	List<BoardVo> result =new ArrayList<BoardVo>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		conn=getConnection();
		String sql= "select title,contents,hit,reg_date,g_no,o_no,depth,user_no,no from board order by g_no desc ,o_no asc";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		//5 결과 가져오기 
		while(rs.next()) {
			
			String title = rs.getString(1);
			String contents = rs.getString(2);
			long hit = rs.getLong(3);
			String date = rs.getString(4);
			long gNo = rs.getLong(5);
			long oNo = rs.getLong(6);
			long depth = rs.getLong(7);
			long userNo = rs.getLong(8);
			long no=rs.getLong(9);
			
			
			BoardVo vo = new BoardVo();
			
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setHit(hit);
			vo.setReg_date(date);
			vo.setG_no(gNo);
			vo.setO_no(oNo);
			vo.setDepth(depth);
			vo.setUserNo(userNo);
			vo.setNo(no);
			
			result.add(vo);
			
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
		return result;
}
public BoardVo findByNo(long no) {
	
	BoardVo result =new BoardVo();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		conn=getConnection();
		String sql= "select title,contents,user_no,g_no,o_no,depth from board where no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, no);
		
		rs = pstmt.executeQuery();
		
		//5 결과 가져오기 
		if(rs.next()) {
				
			String title=rs.getString(1);
			String contents=rs.getString(2);
			long num = rs.getLong(3);
			
			long gNo = rs.getLong(4);
			long oNo = rs.getLong(5);
			long depth = rs.getLong(6);
			
			
			result.setTitle(title);
			result.setContents(contents);
			result.setUserNo(num);
			result.setG_no(gNo);
			result.setO_no(oNo);
			result.setDepth(depth);
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
	
	
	return result;
}

public long findgNo() {
	
	long gNo = 0 ;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		conn=getConnection();
		String sql= "select max(g_no) from board";
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		//5 결과 가져오기 
		if(rs.next()) {
				
			gNo=rs.getLong(1);
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
	
	
	return gNo;
}

public long findMaxDepth(long gNo) {
	
	long maxNo = 0 ;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try {
		conn=getConnection();
		String sql= "select max(depth) from board where g_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, gNo);
		rs = pstmt.executeQuery();
		
		//5 결과 가져오기 
		if(rs.next()) {
				
			maxNo=rs.getLong(1);
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
	
	
	return maxNo;
}

public boolean depthUpdate(long nowDepth) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		conn = getConnection();
		
		String sql = "update board set depth = ?  where depth =?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, nowDepth-1);
		pstmt.setLong(1, nowDepth);
		
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


public boolean allDelete(long gNo) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		conn = getConnection();
		
		String sql = "delete from board where g_no =?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, gNo);
		
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

private Connection getConnection() throws SQLException{
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url="jdbc:mysql://127.0.0.1:3306/webdb";
			conn=DriverManager.getConnection(url,"webdb","webdb");
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}
		
		return conn;
	}
	
}
