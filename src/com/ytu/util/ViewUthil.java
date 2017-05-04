package com.ytu.util;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ViewUthil {
	
	// 给定窗口宽度，返回在屏幕上的x坐标
	public static int getPositionX(int width){
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();   
        int x = (d.width - width)/2;   
		
        return x;
	}
	
	// 给定窗口高度，返回在屏幕上的y坐标
	public static int getPositionY(int height){
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();   
        int y = (d.height - height)/2;   
		
        return y;
	}
}
