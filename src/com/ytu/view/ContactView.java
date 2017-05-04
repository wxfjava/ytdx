package com.ytu.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.ytu.util.PropertiesManger;
import com.ytu.util.SendMail;

public class ContactView extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5994046388673912187L;

	// 加载皮肤配置文件
	Properties skinPro = PropertiesManger.getSkinProperties();
	
	private JLabel backImage = null;
	private JPanel backPanel = null;
	
	private JLabel close_JLabel = null;
	private JLabel send_JLabel = null;
	
	private JTextField username_JTextField = null;
	private JTextArea message_JTextArea = null;
	
	private JPanel north_JPanel = null;
	private JPanel center_JPanel = null;
	private JPanel south_JPanel = null;
	
	private JScrollPane message_JScrollPane = null;
	
	// 加载北部布局相关资源
	Icon close_ImageIcon_normal = new ImageIcon(getClass().getResource("/com/ytu/resource/btn_close_normal.png"));
	Icon close_ImageIcon_down = new ImageIcon(getClass().getResource("/com/ytu/resource/btn_close_down.png"));
	Icon close_ImageIcon_highlight = new ImageIcon(getClass().getResource("/com/ytu/resource/btn_close_highlight.png"));
	
	Icon send_ImageIcon_normal = new ImageIcon(getClass().getResource("/com/ytu/resource/button-normal.png"));
	Icon send_ImageIcon_down = new ImageIcon(getClass().getResource("/com/ytu/resource/button-click.png"));
	Icon send_ImageIcon_highlight = new ImageIcon(getClass().getResource("/com/ytu/resource/button-hold.png"));
	
	ImageIcon  contact_background = new ImageIcon(ContactView.class.getResource(skinPro.getProperty("background_contact_image_url")));
	// 信息框相关资源
	Icon background_message = new ImageIcon(ContactView.class.getResource(skinPro.getProperty("background_message_image_url")));
	
//	Icon smile_naughtyIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/smile_naughty.gif"));
	Icon smile_cryIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/smile_cry.gif"));
	Icon smile_teethIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/smile_teeth.gif"));
	Icon smile_foolIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/smile_fool.gif"));
	
	Icon[] progressIcons = {
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_1.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_2.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_3.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_4.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_5.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_6.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_7.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_8.png"))};
	MessageView messageView = null;
	// 显示窗口的大小
	private int ContactView_WIDTH;
	private int ContactView_HEIGHT;
	
	public int getContactView_WIDTH() {
		return ContactView_WIDTH;
	}
	public int getContactView_HEIGHT() {
		return ContactView_HEIGHT;
	}
	
	// 构造方法
	public ContactView(int locationX, int locationY, JFrame owner){
		super(owner, true);
		this.ContactView_WIDTH = contact_background.getIconWidth();
		this.ContactView_HEIGHT = contact_background.getIconHeight();
		
		// 设置整体的背景
		backImage = new JLabel(contact_background);
		backImage.setBounds(0, 0, ContactView_WIDTH, ContactView_HEIGHT);
		backPanel = (JPanel) this.getContentPane();
		backPanel.setOpaque(false);
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(backImage, new Integer(Integer.MIN_VALUE));
		backPanel.setLayout(new BorderLayout());
		// 去掉窗口样式
		this.setUndecorated(true);
		this.setSize(ContactView_WIDTH, ContactView_HEIGHT);
		this.setLocation(locationX, locationY);
		
		init();
	}
	
	private void init(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
//			e.printStackTrace();
		}
//		try {
//           UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
//       } catch (Exception e) {
//       }
		
		// 北部布局
		north_JPanel = new JPanel();
		north_JPanel.setLayout(null);
		north_JPanel.setOpaque(false);
//		north_JPanel.setBackground(Color.RED);
		north_JPanel.setPreferredSize(new Dimension(this.ContactView_WIDTH, (close_ImageIcon_normal.getIconHeight())+5));
		
		close_JLabel = getJLabelByIcon(null, close_ImageIcon_normal);
		close_JLabel.setLocation(this.ContactView_WIDTH-close_ImageIcon_normal.getIconWidth(), 0);
		
		north_JPanel.add(close_JLabel);
		
		// 中间布局
		center_JPanel = new JPanel(null);
		center_JPanel.setOpaque(false);
//		center_JPanel.setBackground(Color.RED);
		
		username_JTextField = new JTextField();
		username_JTextField.setOpaque(false);
		username_JTextField.setSize(ContactView_WIDTH-50, 25);
		username_JTextField.setLocation(25, 0);
		
		message_JTextArea = new JTextArea();
		message_JTextArea.setOpaque(false);
		message_JTextArea.setLineWrap(true);	
		
		message_JScrollPane = new JScrollPane(message_JTextArea);
		message_JScrollPane.setSize(ContactView_WIDTH-20, 150);
		message_JScrollPane.setLocation(10, 27);
		message_JScrollPane.setOpaque(false);
		message_JScrollPane.getViewport().setOpaque(false);
		
		center_JPanel.add(username_JTextField);
		center_JPanel.add(message_JScrollPane);
		
		// 南部布局
		south_JPanel = new JPanel(null);
		south_JPanel.setPreferredSize(new Dimension(ContactView_WIDTH, 30));
		south_JPanel.setOpaque(false);
//		south_JPanel.setBackground(Color.BLUE);
		
		send_JLabel = getJLabelByIcon("发送", send_ImageIcon_normal);
		send_JLabel.setLocation(ContactView_WIDTH-send_ImageIcon_normal.getIconWidth()-20, 0);
		
		south_JPanel.add(send_JLabel);
		
		
		// 添加各个布局
		this.add(north_JPanel, "North");
		this.add(center_JPanel, "Center");
		this.add(south_JPanel, "South");
		handle(this);
		this.setVisible(true);
	}
	
	// 标签的相关事件
	private void handle(final ContactView jFrame){
		final TipWindow bubble = new TipWindow(null, 0, 0, "", null, jFrame);

		// 关闭按钮相关事件
		close_JLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				closeView();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				bubble.closeTip();
				
				close_JLabel.setIcon(close_ImageIcon_down);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bubble.closeTip();
				
				close_JLabel.setIcon(close_ImageIcon_normal);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bubble.setAll(null, e.getXOnScreen(), e.getYOnScreen()+20, "关闭", null);
				bubble.setVisible(true);
				
				close_JLabel.setIcon(close_ImageIcon_highlight);
			}
		});
		
		// 发送标签
		send_JLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				send_JLabel.setIcon(send_ImageIcon_highlight);
				
				final Point p = jFrame.getLocation();
				
				messageView = new MessageView(background_message,smile_foolIcon,progressIcons,"正在发送中...",null,jFrame);
				new Thread(){
					public void run() {
						try {
							String usernameContent = username_JTextField.getText();
							usernameContent = "<font color='red'>"+usernameContent+"</font>";
							String messageContent = message_JTextArea.getText().replaceAll("\n", "<br/>");
//							
							String content = usernameContent+"<br/><hr/>"+messageContent;
							
							SendMail.send(content);
							messageView.closeViewRapid();
//							try {
//								Thread.sleep(2000);
//								messageView.closeView();
//							} catch (InterruptedException e) {
//								messageView.closeView();
//								e.printStackTrace();
//							}
							messageView = new MessageView(background_message,smile_teethIcon,null,"发送成功,等我联系你吧...",160,jFrame);
							new Thread(){
								@Override
								public void run() {
									try {
										Thread.sleep(1800);
										messageView.closeView();
										jFrame.closeView();
									} catch (InterruptedException e) {
										messageView.closeView();
//										e.printStackTrace();
									}
								}
							}.start();
							messageView.showMessage(p.x+50, p.y+50);
						} catch (Exception e) {
							// 发送失败
							messageView.closeViewRapid();
							messageView = new MessageView(background_message,smile_cryIcon,null,"发送失败，请稍后再试...",160,jFrame);
							new Thread(){
								@Override
								public void run() {
									try {
										Thread.sleep(2000);
										messageView.closeView();
									} catch (InterruptedException e) {
										messageView.closeView();
//										e.printStackTrace();
									}
								}
							}.start();
							messageView.showMessage(p.x+50, p.y+50);
						}
					}
				}.start();
				messageView.showMessage(p.x+50, p.y+50);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				send_JLabel.setIcon(send_ImageIcon_down);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				send_JLabel.setIcon(send_ImageIcon_normal);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				send_JLabel.setIcon(send_ImageIcon_highlight);
			}
		});
		// 个人信息
		username_JTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bubble.closeTipRapid();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bubble.closeTipRapid();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bubble.setAll(null, e.getXOnScreen(), e.getYOnScreen()+20, "请输入您的个人信息（以便让我知道你是谁）", null);
				bubble.setVisible(true);
			}
		});
		// 信息area
		message_JTextArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bubble.closeTipRapid();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bubble.closeTipRapid();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bubble.setAll(null, e.getXOnScreen(), e.getYOnScreen()+20, "请输入您要发送的内容（不要乱发哦）", null);
				bubble.setVisible(true);
			}
		});
	}
	
	// 关闭发送窗口显示
	public void closeView(){
		// 开启线程关闭
		final ContactView contactView = this;
		
		new Thread(){
			@Override
			public void run() {
				try {
					for (int i = 0; i < contactView.getContactView_WIDTH(); i+=10){
						Thread.sleep(6);
						contactView.setSize(contactView.getContactView_WIDTH(), contactView.getContactView_HEIGHT()-i);
					}
				} catch (Exception e1) {
					contactView.setSize(contactView.getContactView_WIDTH(), contactView.getContactView_HEIGHT());
					contactView.setVisible(false);
//					e1.printStackTrace();
				}
				contactView.setSize(contactView.getContactView_WIDTH(), contactView.getContactView_HEIGHT());
				contactView.setVisible(false);
			}
		}.start();
	}
	
	// 根据图标返回一个标签
	public JLabel getJLabelByIcon(String text, Icon icon){
		JLabel jLabel = null;
		if (text != null){
			jLabel = new JLabel(text,icon,JLabel.CENTER);
			jLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
			jLabel.setHorizontalTextPosition(JLabel.CENTER);
		}else{
			jLabel = new JLabel(icon);
			jLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
		}
		
		return jLabel;
	}
}