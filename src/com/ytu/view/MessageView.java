package com.ytu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MessageView extends JDialog{

	private static final long serialVersionUID = 2010297450562458252L;
	// 构造函数传过来的参数
	private Icon[] faceIcons = null;
	private Icon faceIcon = null;
	private Icon[] progressIcons = null;
	private Icon backIcon = null;
	private String message = "";
	private Integer messageLen = null;
	// 显示窗口的位置
	
	private JLabel backImage = null;
	private JPanel backPanel = null;
	
	private JLabel message_JLabel = null;
	private JLabel faces_JLabel = null;
	private JLabel progress_JLabel = null;
	private JPanel message_JPanel = null;
	// 信息显示窗口的大小
	private int MessageView_WIDTH;
	private int MessageView_HEIGHT;
	
	// 停止线程的标志
	private boolean IS_STOP_THREAD = false;
	
	public int getMessageView_WIDTH() {
		return MessageView_WIDTH;
	}
	public int getMessageView_HEIGHT() {
		return MessageView_HEIGHT;
	}
	
	// 构造方法
	public MessageView(Icon backIcon, String message, JFrame owner){
		super(owner,true);
		this.backIcon = backIcon;
		this.message = message;
		this.setAlwaysOnTop(true);
		MessageView_WIDTH = backIcon.getIconWidth();
		MessageView_HEIGHT = backIcon.getIconHeight();
		
		init();
	}
	
	public MessageView(Icon backIcon, Icon faceIcon, Icon[] progressIcons, String message, Integer messageLen, JFrame owner){
		super(owner,true);
		this.backIcon = backIcon;
		this.faceIcon = faceIcon;
		this.progressIcons = progressIcons;
		this.message = message;
		this.messageLen = messageLen;
		this.setAlwaysOnTop(true);
		MessageView_WIDTH = backIcon.getIconWidth();
		MessageView_HEIGHT = backIcon.getIconHeight();
		
		init();
	}
	
	public MessageView(Icon backIcon, Icon faceIcon, Icon[] progressIcons, String message, Integer messageLen, JDialog owner){
		super(owner,true);
		this.backIcon = backIcon;
		this.faceIcon = faceIcon;
		this.progressIcons = progressIcons;
		this.message = message;
		this.messageLen = messageLen;
		this.setAlwaysOnTop(true);
		MessageView_WIDTH = backIcon.getIconWidth();
		MessageView_HEIGHT = backIcon.getIconHeight();
		
		init();
	}
	
	public MessageView(Icon backIcon, Icon[] faceIcons, Icon[] progressIcons, String message, JFrame owner){
		super(owner,true);
		this.backIcon = backIcon;
		this.faceIcons = faceIcons;
		this.progressIcons = progressIcons;
		this.message = message;
		this.setAlwaysOnTop(true);
		MessageView_WIDTH = backIcon.getIconWidth();
		MessageView_HEIGHT = backIcon.getIconHeight();
		
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
		}else{
			backImage = new JLabel();
			backImage.setBorder(BorderFactory.createEtchedBorder());
		}
		backImage.setBounds(0, 0, MessageView_WIDTH, MessageView_HEIGHT);
		backPanel = (JPanel) this.getContentPane();
		backPanel.setOpaque(false);
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(backImage, new Integer(Integer.MIN_VALUE));
		backPanel.setLayout(new BorderLayout());
		// 去掉窗口样式
		this.setUndecorated(true);
		this.setSize(MessageView_WIDTH, MessageView_HEIGHT);
		
		message_JPanel = new JPanel(null);
		message_JPanel.setBounds(0, 0, MessageView_WIDTH, MessageView_HEIGHT);
		message_JPanel.setOpaque(false);
		this.add(message_JPanel);
		
		// 将信息添加到组件
		// 添加显示的文字信息
		message_JLabel = new JLabel(this.message, JLabel.CENTER);
		message_JLabel.setFont(new Font("宋体", 0, 13));
//		message_JLabel.setSize(100, 30);
		if (messageLen != null){
			message_JLabel.setSize(messageLen, 30);
		}else{
			message_JLabel.setSize(100, 30);
		}
		
		message_JLabel.setLocation((MessageView_WIDTH-100)/2-10, 50);
		message_JLabel.setForeground(new Color(87, 87, 87));
		
		// 显示非组合图片
		if (faceIcon != null){
			faces_JLabel = new JLabel(faceIcon);
			faces_JLabel.setSize((MessageView_WIDTH-100)/2, (MessageView_WIDTH-100)/2);
			faces_JLabel.setLocation(0,15);
			
			message_JPanel.add(faces_JLabel);
			
		}
		
		// 将信息添加到对话框
		message_JPanel.add(message_JLabel);
	}
	
	// 显示信息
	public void showMessage(int locationX, int locationY){
		this.IS_STOP_THREAD = false;
		this.setSize(MessageView_WIDTH, MessageView_HEIGHT);
		this.setLocation(locationX, locationY);
		
		// 显示第一个图片
		if (faceIcons != null && faceIcons[0] != null){
			final Icon[] faceIcons_t = faceIcons;
			
			if (faces_JLabel == null){
				faces_JLabel = new JLabel(faceIcons[0]);
				faces_JLabel.setSize((MessageView_WIDTH-100)/2, (MessageView_WIDTH-100)/2);
				faces_JLabel.setLocation(0,15);
				
				message_JPanel.add(faces_JLabel);
			}
			
			new Thread(){
				@Override
				public void run() {
					try {
						while(!IS_STOP_THREAD){
							for (int i = 0; i < faceIcons_t.length; i++){
								Thread.sleep(400);
								faces_JLabel.setIcon(faceIcons_t[i]);
							}
						}
					} catch (Exception e1) {
//						e1.printStackTrace();
					}
				}
			}.start();
		}
		
		// 显示第二个图片
		if (progressIcons != null && progressIcons[0] != null){
			final Icon[] progressIcons_t = progressIcons;
			
			if (progress_JLabel == null){
				progress_JLabel = new JLabel(progressIcons[0]);
				progress_JLabel.setSize((MessageView_WIDTH-100)/2, (MessageView_WIDTH-100)/2);
				progress_JLabel.setLocation(MessageView_WIDTH-(MessageView_WIDTH-100)/2-20,15);
				
				message_JPanel.add(progress_JLabel);
			}
			
			new Thread(){
				@Override
				public void run() {
					try {
						while(!IS_STOP_THREAD){
							for (int i = 0; i < progressIcons_t.length; i++){
								Thread.sleep(100);
								progress_JLabel.setIcon(progressIcons_t[i]);
							}
						}
					} catch (Exception e1) {
//						e1.printStackTrace();
					}
				}
			}.start();
		}
		this.setVisible(true);
	}
	
	// 关闭信息显示
	public void closeView(){
		// 开启线程关闭信息显示
		final MessageView messageView = this;
		messageView.IS_STOP_THREAD = true;
		
		new Thread(){
			@Override
			public void run() {
				try {
					for (int i = 0; i < messageView.getMessageView_WIDTH(); i+=8){
						Thread.sleep(15);
						messageView.setSize(messageView.getMessageView_WIDTH(), messageView.getMessageView_HEIGHT()-i);
					}
				} catch (Exception e1) {
//					e1.printStackTrace();
					messageView.setVisible(false);
				}
				
				messageView.setVisible(false);
			}
		}.start();
	}
	public void closeViewRapid(){
		this.IS_STOP_THREAD = true;
		this.setVisible(false);
	}
}