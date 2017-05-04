package com.ytu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *  Title：对配置信息进行管理
 *	ProjectName: admin
 *	ClassName: PropertiesManger
 *  Copyright: Copyright © 2012
 *  @author: 烽火
 *  @version 1.0 2012-12-26 下午04:14:04
 */
public class PropertiesManger {
	
	/**
	* getProperties(这里用一句话描述这个方法的作用)   
	* @param String propName 配置文件的名称
	* @return Properties Properties对象   
	*/
	
	// 根据文件 读取流读取配置文件
	public static Properties getProperties(String propName){
		
		Properties prop = new Properties();
		try {
			InputStream in = new FileInputStream(new File(propName));
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 return prop;
	}
	
	// 获得base配置文件
	public static Properties getBaseProperties(){
		
		Properties prop = new Properties();
		try {
			InputStream inStream = PropertiesManger.class.getClassLoader()
					.getResourceAsStream("com/ytu/config/base.properties");
			prop.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 return prop;
	}
	
	public static Properties getSkinProperties(){
		
		// 读取基本配置将配置文件中的位置，即名字信息读出
		Properties basePro = getBaseProperties();
		String filePath_str = basePro.getProperty("config_file_path");
		String fileName_str = basePro.getProperty("config_file_name");
		
		File path = new File(filePath_str);
		if (!path.exists()){		// 若路径不存在，创建
			path.mkdirs();
		}
		File proFile = new File(filePath_str+File.separator+fileName_str);
		if (!proFile.exists()){		// 若文件不存在，创建
			try {
				proFile.createNewFile();
				
				// 将配置文件复制到指定的路径
				InputStream in = PropertiesManger.class.getClassLoader()
						.getResourceAsStream("com/ytu/config/skin_blue.properties");
				OutputStream out = new FileOutputStream(proFile);
				
				byte[] buff = new byte[1024];
				int len = -1;
				
				while((len=in.read(buff)) != -1){
					out.write(buff, 0, len);
					out.flush();
				}
				
				in.close();
				out.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Properties skinPro = getProperties(filePath_str+File.separator+fileName_str);
		if (skinPro == null){
			return basePro;
		}
		
		return skinPro;
	}
}
