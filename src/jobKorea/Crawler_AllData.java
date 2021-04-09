package jobKorea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler_AllData {
	public static String URL = "https://www.jobkorea.co.kr/Search/?";
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("찾고자 하는 키워드를 입력하세요 : ");
		String Key_word = sc.next();
		int PageNum = 1;
		Document doc = Jsoup.connect(URL+getParam(Key_word,PageNum)).get();
		Element element = doc.selectFirst(".dev_tot");
		int resultNum = Integer.parseInt(element.text().replace(",", ""));
		System.out.println("총 검색 결과수는 :"+ element.text());
		System.out.println("----------------------------");
		
		FileOutputStream fos = new FileOutputStream(new File("jobkorea.txt"));
		PrintWriter pw = new PrintWriter(fos);
		
		
		while(resultNum>=1200) {
			System.out.println("//////////////////////////////// 페이지 넘버 => "+PageNum+" //////////////////////////////// ");
			resultNum-=30;
			doc = Jsoup.connect(URL+getParam(Key_word,PageNum++)).get();
			Elements elements = doc.select(".post");
			for(Element e : elements	) {
				pw.write(e.text()+"\n");
				System.out.println(e.text());
				System.out.println("----------------------");
			}
			pw.flush();
		}
	}
	public static String getParam(String Key_word, int PageNum) {
		return "stext="+Key_word+"&tabType=recruit&Page_No="+PageNum;
	}
	
}
