package com.ytu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CheckUseCount {
	
	public static boolean isCanUse() throws IOException{
		
		Properties basePro = PropertiesManger.getBaseProperties();
		Properties skinPro = PropertiesManger.getSkinProperties();
		
		String filePath_str = basePro.getProperty("config_file_path");
		String fileName_str = basePro.getProperty("config_file_name");
		
		int queryCount = Integer.parseInt(skinPro.getProperty("query_count"));
		if (queryCount <= 0){
			return false;
		}
		
		skinPro.setProperty("query_count", queryCount-1+"");
		FileOutputStream out = new FileOutputStream(filePath_str+File.separator+fileName_str);
		skinPro.store(out, null);
		
		out.close();
		return true;
	}
}
