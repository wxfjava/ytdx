package com.ytu.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
/**
 * 复制某文本到浏览器
 * @author Administrator
 *
 */
public class ClipBoardUtil {
	
	// 将字符串复制到黏贴板
	public static void copyToClipBoard(String str){
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();	//获得系统粘贴板
		
		StringSelection textInfo = new StringSelection(str);
		clipboard.setContents(textInfo, null);	//将textInfo加入到粘贴
	}
}
