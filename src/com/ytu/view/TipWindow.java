package com.ytu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
/**
 * 
 * 显示信息泡泡
 * @author: 烽火
 * 
 * 构造方法参数：
 * Icon backIcon, int locationX, int locationY, String message, Integer tipWindow_WIDTH, JDialog jDialog
 * Icon backIcon 背景图片，可为null
 * int locationX, int locationY 泡泡显示位置
 * String message 显示的文字信息
 * Integer tipWindow_WIDTH 泡泡显示长度，可为null
 * JDialog jDialog 父对话框，避免被覆盖时，使用
 *
 * 方法：
 * public void closeTip(); 关闭泡泡
 */
public class TipWindow extends JDialog{

	private static final long serialVersionUID = -8776295177197811070L;

	private Icon backIcon = null;
	private String message = "";
	private JLabel backImage = null;
	private JPanel backPanel = null;
	// 显示气泡的位置
	private int locationX;
	private int locationY;
	
	private JLabel message_JLabel = null;
	// 泡泡的大小
	private int TipWindow_WIDTH;
	private int TipWindow_HEIGHT;
	public int getTipWindow_WIDTH() {
		return TipWindow_WIDTH;
	}
	
	private boolean IS_Visible = false;
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		IS_Visible = b;
	}
	// 修改部分属性，并更改窗体样式
	public void setAll(Icon backIcon, int locationX, int locationY, String message, Integer tipWindow_WIDTH){
		this.backIcon = backIcon;
		this.message = message;
		this.locationX = locationX;
		this.locationY = locationY;
		if (backIcon != null){
			backImage.setIcon(backIcon);
		}else{
			backImage.setIcon(null);
		}
		
		if (backIcon != null){
			TipWindow_WIDTH = backIcon.getIconWidth();
			TipWindow_HEIGHT = backIcon.getIconHeight();
		}else{
			TipWindow_WIDTH = message.length()*12+13;
			TipWindow_HEIGHT = 19;
		}
		if (tipWindow_WIDTH != null){
			TipWindow_WIDTH = tipWindow_WIDTH;
		}
		message_JLabel.setText(message);
		backImage.setSize(TipWindow_WIDTH, TipWindow_HEIGHT);
		this.setSize(TipWindow_WIDTH, TipWindow_HEIGHT);
		this.setLocation(locationX, locationY);
		
	}
	
	public void setBackIcon(Icon backIcon) {
		this.backIcon = backIcon;
		backImage.setIcon(backIcon);
		TipWindow_WIDTH = backIcon.getIconWidth();
		TipWindow_HEIGHT = backIcon.getIconHeight();
		backImage.setBorder(null);
		backImage.setSize(TipWindow_WIDTH, TipWindow_HEIGHT);
		this.setSize(TipWindow_WIDTH, TipWindow_HEIGHT);
	}
	public void setMessage(String message) {
		this.message = message;
		message_JLabel.setText(message);
	}
	public void setLocationX(int locationX) {
		this.locationX = locationX;
		this.setLocation(locationX, locationY);
	}
	public void setLocationY(int locationY) {
		this.locationY = locationY;
		this.setLocation(locationX, locationY);
	}
	public void setTipWindow_WIDTH(int tipWindow_WIDTH) {
		this.TipWindow_WIDTH = tipWindow_WIDTH;
		this.setSize(tipWindow_WIDTH, TipWindow_HEIGHT);
	}
	public int getTipWindow_HEIGHT() {
		return TipWindow_HEIGHT;
	}
	
	// 构造方法
	public TipWindow(Icon backIcon, int locationX, int locationY, String message, Integer tipWindow_WIDTH, JDialog jDialog){
		super(jDialog, false);
		this.backIcon = backIcon;
		this.message = message;
		this.locationX = locationX;
		this.locationY = locationY;
		this.setAlwaysOnTop(true);	// 总在前
		this.setFocusableWindowState(false);	// 不让其获得焦点
		
		if (backIcon != null){
			TipWindow_WIDTH = backIcon.getIconWidth();
			TipWindow_HEIGHT = backIcon.getIconHeight();
		}else{
			TipWindow_WIDTH = message.length()*12+13;
			TipWindow_HEIGHT = 19;
		}
		if (tipWindow_WIDTH != null){
			TipWindow_WIDTH = tipWindow_WIDTH;
		}
		
		init();
	}
	public TipWindow(Icon backIcon, int locationX, int locationY, String message, Integer tipWindow_WIDTH){
		this.backIcon = backIcon;
		this.message = message;
		this.locationX = locationX;
		this.locationY = locationY;
		this.setAlwaysOnTop(true);
		this.setFocusableWindowState(false);
		
		if (backIcon != null){
			TipWindow_WIDTH = backIcon.getIconWidth();
			TipWindow_HEIGHT = backIcon.getIconHeight();
		}else{
			TipWindow_WIDTH = message.length()*12+13;
			TipWindow_HEIGHT = 19;
		}
		if (tipWindow_WIDTH != null){
			TipWindow_WIDTH = tipWindow_WIDTH;
		}
		
		init();
	}
	
	private void init(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
//			e.printStackTrace();
		}
		// 设置背景
		if (backIcon != null){
			backImage = new JLabel(backIcon);
			backImage.setOpaque(false);
		}else{
			backImage = new JLabel();
			backImage.setBorder(BorderFactory.createEtchedBorder());
		}
		backImage.setBounds(0, 0, TipWindow_WIDTH, TipWindow_HEIGHT);
		backPanel = (JPanel) this.getContentPane();
		backPanel.setOpaque(false);
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(backImage, new Integer(Integer.MIN_VALUE));
		backPanel.setLayout(new BorderLayout());
		
		// 添加显示的文字信息
		message_JLabel = new JLabel(this.message, JLabel.CENTER);
		message_JLabel.setFont(new Font("宋体", 0, 12));
		message_JLabel.setForeground(new Color(87, 87, 87));
		
		// 将信息添加到对话框
		this.add(message_JLabel);
		
		// 去掉窗口样式
		this.setUndecorated(true);
		this.setBounds(locationX, locationY, TipWindow_WIDTH, TipWindow_HEIGHT);
//		this.setVisible(true);
	}
	
	// 关闭泡泡
	public void closeTip(){
		// 开启线程关闭气泡
		final TipWindow tipWindow = this;
		IS_Visible = false;
		new Thread(){
			@Override
			public void run() {
				try {
					for (int i = 0; i < tipWindow.getTipWindow_WIDTH(); i+=1){
						Thread.sleep(6);
						tipWindow.setSize(tipWindow.getTipWindow_WIDTH(), tipWindow.getTipWindow_HEIGHT()-i);
						if (IS_Visible){
							tipWindow.setSize(tipWindow.getTipWindow_WIDTH(), tipWindow.getTipWindow_HEIGHT());
							return;
						}
					}
				} catch (Exception e1) {
//					e1.printStackTrace();
					tipWindow.setVisible(false);
				}
				tipWindow.setVisible(false);
//				tipWindow.dispose();
			}
		}.start();
	}
	// 立即关闭气泡
	public void closeTipRapid(){
		this.setVisible(false);
		this.IS_Visible = false;
	}
}
