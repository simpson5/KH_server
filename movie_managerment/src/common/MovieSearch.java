package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MovieSearch {

	public static String movieSearch(String searchWord, int start) throws IOException {
        //java코드로 특정 url에 요청보내고 응답받기
        //기본 자바 API를 활용한 방법
        String clientID="t7q_cskKv7S89kSnqFc0"; //네이버 개발자 센터에서 발급받은 clientID입력
        String clientSecret = "loMgJVkGPq";        //네이버 개발자 센터에서 발급받은 clientSecret입력
        
        String text = null;
        try { 
        	text = URLEncoder.encode(searchWord, "UTF-8");
        } catch (UnsupportedEncodingException e) { 
        	throw new RuntimeException("검색어 인코딩 실패",e); 
        }

        String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text + "&display=" + 10 + "&start=" + start + "&" ;
        
        URL url = new URL(apiURL); //API 기본정보의 요청 url을 복사해오고 필수인 query를 적어줍니당! 
        
        URLConnection urlConn=url.openConnection(); //openConnection 해당 요청에 대해서 쓸 수 있는 connection 객체 
        
        urlConn.setRequestProperty("X-Naver-Client-ID", clientID);
        urlConn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
    
        String msg = null;
        String str = "";
        while((msg = br.readLine())!=null)
        {
            str += msg;
        }
        return str;
	}

}
