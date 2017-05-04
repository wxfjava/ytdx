package com.ytu.util;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ytu.po.UpdateResultType;

public class CheckUpdate {
	public static UpdateResultType isLatest(String oldVersion){
		
		String url = "http://pan.baidu.com/share/link?shareid=478898&uk=103277935";

        Connection connection = Jsoup.connect(url);
        connection.timeout(5000);
        connection.header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
//      connection.data("domainid", "2");
        connection.method(Connection.Method.POST);
        try {
            Document doc = connection.post();
            if (doc == null) {
            	return UpdateResultType.NET_ERROR;
            }

            Elements links = doc.select("h2");
            if (links == null){
            	return UpdateResultType.CAN_NOT_UPDATE;
            }
            
            for (Element e : links){
            	String version = e.html();
            	version = version.toLowerCase();
            	version = version.substring(version.indexOf('v'));
            	
            	oldVersion = oldVersion.toLowerCase();
            	
            	if (oldVersion != null && version != null){
            		if (oldVersion.equals(version)){
            			return UpdateResultType.IS_LATEST;
            		}
            	}
            }

        } catch (IOException e1) {
//        	e1.printStackTrace();
        	return UpdateResultType.NET_ERROR;
        }
		return UpdateResultType.NOT_LATEST;
	}
	
	public static void main(String[] args) {
		System.out.println(isLatest("v5.1"));
	}
}
