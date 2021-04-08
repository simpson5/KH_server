package admin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import member.model.vo.Member;
import movie.model.vo.Movie;
import movie.model.vo.MovieNow;

public class AdminDAO {
	
	private Properties prop = new Properties();
	
	public AdminDAO(){
	try {
        //클래스객체 위치찾기 : 절대경로를 반환한다. 
        String fileName = AdminDAO.class.getResource("/sql/admin/admin-query.properties").getPath();
        prop.load(new FileReader(fileName));
        
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
	
}
	

	public List<Member> selectMemberList(Connection conn, int cPage, int numPerPage) {
		 List<Member> list = new ArrayList<>();
	        PreparedStatement pstmt = null;
	        ResultSet rset = null;
	        
	        String query = prop.getProperty("selectMemberListByPaging");
	        
	        try{
	            //미완성쿼리문을 가지고 객체생성. 
	            pstmt = conn.prepareStatement(query);
	            pstmt.setInt(1, (cPage-1)*numPerPage+1);
	            pstmt.setInt(2, cPage*numPerPage);
	            
	            
	            //쿼리문실행
	            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
	            rset = pstmt.executeQuery();
	            
	            while(rset.next()){
	                Member m = new Member();
	                //컬럼명은 대소문자 구분이 없다.
	                m.setMemberId(rset.getString("MEMBERID"));
	                m.setPassword(rset.getString("PASSWORD"));
	                m.setLastName(rset.getString("LASTNAME"));
	                m.setFirstName(rset.getString("FIRSTNAME"));
	                m.setEmail(rset.getString("EMAIL"));
	                m.setPhone(rset.getString("PHONE"));
	                m.setGender(rset.getString("gender"));
	                m.setAge(rset.getInt("age"));
	                m.setInterests(rset.getString("INTERESTS"));
	                m.setPoint(rset.getInt("POINT"));
	                m.setEnrollDate(rset.getDate("ENROLLDATE"));
	                
	                list.add(m);
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            close(rset);
	            close(pstmt);
	        }
	        
	        
	        return list;

	}


	public int selectTotalContent(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContent");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			
//			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalContent;
	}


	public List<Member> selectMemberByMemberId(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = null;
		String query = prop.getProperty("selectMemberByMemberIdByPaging");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			//(공식1)
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while (rset.next()) {
				Member m = new Member();
				// 컬럼명은 대소문자 구분이 없다.
				m.setMemberId(rset.getString("MEMBERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setLastName(rset.getString("LASTNAME"));
				m.setFirstName(rset.getString("firstname"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setGender(rset.getString("gender"));
                m.setAge(rset.getInt("age"));
				m.setInterests(rset.getString("INTERESTS"));
				m.setPoint(rset.getInt("point"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				list.add(m);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public List<Member> selectMemberByMemberName(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByMemberNameByPaging");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(query);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setString(2, "%"+searchKeyword+"%");
			pstmt.setInt(3,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(4, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setLastName(rset.getString("lastname"));
				m.setFirstName(rset.getString("firstname"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setGender(rset.getString("gender"));
                m.setAge(rset.getInt("age"));
				m.setInterests(rset.getString("interests"));
				m.setPoint(rset.getInt("point"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public int selectTotalContentByMemberId(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByMemberId");
		int totalContent = 0;

//		System.out.println(query);
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
//			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}


	public int selectTotalContentByMemberName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByMemberName");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setString(2, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
//			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}


	public List<Member> selectMemberByMemberPhone(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByMemberPhoneByPaging");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(query);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setLastName(rset.getString("lastname"));
				m.setFirstName(rset.getString("firstname"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setGender(rset.getString("gender"));
                m.setAge(rset.getInt("age"));
				m.setInterests(rset.getString("interests"));
				m.setPoint(rset.getInt("point"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public List<Member> selectMemberByMemberEmail(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByMemberEmailByPaging");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(query);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setLastName(rset.getString("lastname"));
				m.setFirstName(rset.getString("firstname"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setGender(rset.getString("gender"));
                m.setAge(rset.getInt("age"));
				m.setInterests(rset.getString("interests"));
				m.setPoint(rset.getInt("point"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	
	public List<Member> selectMemberByMemberGender(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByMemberGenderByPaging");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(query);
			//미완성쿼리에 데이터 전달
			pstmt.setString(1, searchKeyword);
			
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setLastName(rset.getString("lastname"));
				m.setFirstName(rset.getString("firstname"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setGender(rset.getString("gender"));
                m.setAge(rset.getInt("age"));
				m.setInterests(rset.getString("interests"));
				m.setPoint(rset.getInt("point"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<Member> selectMemberByMemberAge(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByMemberAgeByPaging");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(query);
			//미완성쿼리에 데이터 전달
			pstmt.setInt(1, Integer.parseInt(searchKeyword));
			
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setLastName(rset.getString("lastname"));
				m.setFirstName(rset.getString("firstname"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setGender(rset.getString("gender"));
                m.setAge(rset.getInt("age"));
				m.setInterests(rset.getString("interests"));
				m.setPoint(rset.getInt("point"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Member> selectMemberByMemberPoint(Connection conn, int searchKeyword1, int searchKeyword2, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByMemberPointByPaging");
		
		try {
			//statement객체 생성. 미완성 쿼리 전달
			pstmt = conn.prepareStatement(query);
			//미완성쿼리에 데이터 전달
			pstmt.setInt(1, searchKeyword1);
			pstmt.setInt(2, searchKeyword2);
			
			pstmt.setInt(3,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(4, cPage*numPerPage);//end rownum
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			list = new ArrayList<>();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setLastName(rset.getString("lastname"));
				m.setFirstName(rset.getString("firstname"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setGender(rset.getString("gender"));
                m.setAge(rset.getInt("age"));
				m.setInterests(rset.getString("interests"));
				m.setPoint(rset.getInt("point"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public int selectTotalContentByMemberEmail(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByMemberEmail");
		int totalContent = 0;

//		System.out.println(query);
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
//			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}


	public int selectTotalContentByMemberPoint(Connection conn, int searchKeyword1, int searchKeyword2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByMemberPoint");
		int totalContent = 0;

//		System.out.println(query);
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, searchKeyword1);
			pstmt.setInt(2, searchKeyword2);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
//			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}


	
	public int selectTotalContentByMemberGender(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByMemberGender");
		int totalContent = 0;

//		System.out.println(query);
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
//			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	
	public int selectTotalContentByMemberAge(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByMemberAge");
		int totalContent = 0;

//		System.out.println(query);
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(searchKeyword));
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
//			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	public int selectTotalContentByMemberPhone(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByMemberPhone");
		int totalContent = 0;

//		System.out.println(query);
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
//			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	
	public int updateMemberFromAdmin(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateMemberFromAdmin");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getLastName());
			pstmt.setString(2, m.getFirstName());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getInterests());
			pstmt.setString(6, m.getGender());
			pstmt.setInt(7, m.getAge());
			pstmt.setInt(8, m.getPoint());
			pstmt.setString(9, m.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int deleteMemberFromAdmin(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteMemberFromAdmin");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
	public int insertMovie(Connection conn, Movie m) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMovie");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, m.getMovieCode());
			pstmt.setString(2, m.getMovieTitle());
			pstmt.setInt(3, m.getRunningTime());
			pstmt.setString(4, m.getDirector());
			pstmt.setString(5, m.getActor());
			pstmt.setString(6, m.getDescription());
			pstmt.setDate(7, m.getReleaseDate());
			pstmt.setString(8, m.getOriginalFileName());
			pstmt.setString(9, m.getRenamedFileName());
			pstmt.setString(10, m.getMovieVideo());
			pstmt.setString(11, m.getGenres());
			pstmt.setString(12, m.getSchedule());
			
			
			
			result = pstmt.executeUpdate();
			
			
//			System.out.println("insertMovie@Admindao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int updateMovie(Connection conn, Movie m) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateMovie");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, m.getMovieTitle());
			pstmt.setInt(2, m.getRunningTime());
			pstmt.setString(3, m.getDirector());
			pstmt.setString(4, m.getActor());
			pstmt.setString(5, m.getDescription());
			pstmt.setDate(6, m.getReleaseDate());
			pstmt.setString(7, m.getOriginalFileName());
			pstmt.setString(8, m.getRenamedFileName());
			pstmt.setString(9, m.getMovieVideo());
			pstmt.setString(10, m.getGenres());
			pstmt.setString(11, m.getSchedule());

			pstmt.setString(12, m.getMovieCode());
			
			
			result = pstmt.executeUpdate();
			
			
//			System.out.println("updateMovie@Admindao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int deleteMovie(Connection conn, String movieCode) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMovie");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, movieCode);
	

			result = pstmt.executeUpdate();
			
			
//			System.out.println("deleteMovie@Admindao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int insertMovieNow(Connection conn, MovieNow mn) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMovieNow");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, mn.getMovieCode());
			pstmt.setInt(2, mn.getScreenCode());
			pstmt.setString(3, mn.getShowTurn());
			pstmt.setString(4, mn.getStartTime());
			pstmt.setString(5, mn.getEndTime());
			pstmt.setDate(6, mn.getShowDate());
			pstmt.setInt(7, mn.getRemainSeats());
			
			result = pstmt.executeUpdate();
			
			
//			System.out.println("insertMovie@Admindao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteMovieNow(Connection conn, String movieCode) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMovieNow");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, movieCode);
			
			result = pstmt.executeUpdate();
			
			
//			System.out.println("insertMovie@Admindao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public List<String> selectByMovieName(Connection conn, String srchMovie) {
		List<String> movieList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectByMovieName");
		
		try {
			//statement객체 생성.
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%"+srchMovie+"%");
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기
			movieList = new ArrayList<>();
			while(rset.next()) {
				movieList.add(rset.getString("movie_title"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return movieList;
	}


	public String selectMovieCode(Connection conn, String movieTitle) {
		String movieCode = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMovieCode");
		
		try {
			//statement객체 생성.
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, movieTitle);
			
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset의 결과 list에 옮기기

			while(rset.next()) {
				movieCode = rset.getString("movie_code");

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return movieCode;
	}


	public Map<Integer, Integer> selectMonthSales(Connection conn, int year) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMonthSales");
		Map<Integer, Integer> list = new HashMap<Integer,Integer>();

	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, year);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				String strDay = rset.getString("d");
				String splitDay = strDay.substring(5, 7);
				System.out.println(splitDay);
				int day = Integer.parseInt(splitDay);
				int sum = rset.getInt("s");
				list.put(day,sum);
				
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


}
