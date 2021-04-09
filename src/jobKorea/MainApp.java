package jobKorea;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
[크롤링 준비]
1. MAVEN 프로젝트로 만들면서 jsoup추가

 */

public class MainApp {
	private static String URL = "https://www.jobkorea.co.kr/Search/?";
	
	public static void main(String[] args) throws IOException {
		String KEY_WORD = "jquery";
		
		// 1. Document를 가져온다
		Document doc = Jsoup.connect(URL+getParameter(KEY_WORD, 3)).get();
		
		// 2. 목록을 가져온다.
		//System.out.println(""+ doc.toString());
		Elements elements = doc.select(".post");
		
		// 3. 목록(배열)에서 정보를 가져온다
		int index = 1;
		for(Element e : elements) {
			System.out.println(index++ + " : " + e.text());
			System.out.println("------------------------------");
		}
	}
	/**
	 * URL 완성
	 * @param KEY_WORD
	 * @param PAGE
	 * @return
	 */
	public static String getParameter(String KEY_WORD, int PAGE) {
		String params = "stext="+KEY_WORD+"&"+
						"tabType="+ "recruit"+"&"+
						"Page_No="+PAGE+"";
		return params;
	}

}
