package com.ytu.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.ytu.po.UpdateResultType;
import com.ytu.util.CheckUpdate;
import com.ytu.util.CheckUseCount;
import com.ytu.util.ClipBoardUtil;
import com.ytu.util.GetUserInfo;
import com.ytu.util.PropertiesManger;
import com.ytu.util.ViewUthil;

public class QueryView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 778406062913410695L;
	// 配置信息
	final Properties basePro = PropertiesManger.getBaseProperties();
	// 加载皮肤配置文件
	Properties skinPro = PropertiesManger.getSkinProperties();

	private JLabel close_JLabel = null;
	private JLabel minimize_JLabel = null;
	private JLabel skin_JLabel = null;
	private JLabel menu_JLabel = null;
	
	private JLabel feng_JLabel = null;
	private JLabel title_JLabel = null;
	
	private JTextField username_JTextField = null;
	private JLabel query_Btn_JLabel = null;
	
	private JLabel copy_JLabel = null;
	private JLabel download_JLabel = null;
	private JLabel contactMe_JLabel = null;
	
	private JTextArea result_JTextArea = null;
	
	private JPanel north_JPanel = null;
	private JPanel center_JPanel = null;
	private JPanel south_JPanel = null;
	
	private JPanel result_JPanel = null;
	private JScrollPane result_JScrollPane = null;
	private JPanel bottom_JPanel = null;
	
	// 加载北部布局相关资源
	Icon close_ImageIcon_normal = new ImageIcon(getClass().getResource("/com/ytu/resource/btn_close_normal.png"));
	Icon close_ImageIcon_down = new ImageIcon(getClass().getResource("/com/ytu/resource/btn_close_down.png"));
	Icon close_ImageIcon_highlight = new ImageIcon(getClass().getResource("/com/ytu/resource/btn_close_highlight.png"));
	Icon minimize_ImageIcon_normal = new ImageIcon(getClass().getResource("/com/ytu/resource/minimize.png"));
	Icon minimize_ImageIcon_down = new ImageIcon(getClass().getResource("/com/ytu/resource/minimize_press.png"));
	Icon minimize_ImageIcon_highlight = new ImageIcon(getClass().getResource("/com/ytu/resource/minimize_hover.png"));
	Icon skin_ImageIcon_normal = new ImageIcon(getClass().getResource("/com/ytu/resource/btn_Skin_normal.png"));
	Icon skin_ImageIcon_down = new ImageIcon(getClass().getResource("/com/ytu/resource/btn_Skin_down.png"));
	Icon skin_ImageIcon_highlight = new ImageIcon(getClass().getResource("/com/ytu/resource/btn_Skin_highlight.png"));
	Icon menu_ImageIcon_normal = new ImageIcon(getClass().getResource("/com/ytu/resource/Menu_Normal.png"));
	Icon menu_ImageIcon_down = new ImageIcon(getClass().getResource("/com/ytu/resource/Menu_Pushed.png"));
	Icon menu_ImageIcon_highlight = new ImageIcon(getClass().getResource("/com/ytu/resource/Menu_Mouseover.png"));

	Icon feng_ImageIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/feng.png"));
	
	Icon query_ImageIcon_normal = new ImageIcon(getClass().getResource("/com/ytu/resource/button-normal.png"));
	Icon query_ImageIcon_down = new ImageIcon(getClass().getResource("/com/ytu/resource/button-click.png"));
	Icon query_ImageIcon_highlight = new ImageIcon(getClass().getResource("/com/ytu/resource/button-hold.png"));
	// 底部资源
	Icon copy_ImageIcon_normal = new ImageIcon(getClass().getResource("/com/ytu/resource/copy_normal.png"));
	Icon copy_ImageIcon_down = new ImageIcon(getClass().getResource("/com/ytu/resource/copy_edit_down.png"));
	Icon copy_ImageIcon_highlight = new ImageIcon(getClass().getResource("/com/ytu/resource/copy_edit_over.png"));
	Icon download_ImageIcon_normal = new ImageIcon(getClass().getResource("/com/ytu/resource/download_normal.png"));
	Icon download_ImageIcon_down = new ImageIcon(getClass().getResource("/com/ytu/resource/download_down.png"));
	Icon download_ImageIcon_highlight = new ImageIcon(getClass().getResource("/com/ytu/resource/download_mouseover.png"));
	Icon contactMe_ImageIcon_normal = new ImageIcon(getClass().getResource("/com/ytu/resource/contactMe_normal.png"));
//	Icon contactMe_ImageIcon_down = new ImageIcon(getClass().getResource("/com/ytu/resource/contactMe_down.png"));
	Icon contactMe_ImageIcon_highlight = new ImageIcon(getClass().getResource("/com/ytu/resource/contactMe_hover.png"));

	// 加载显示信息窗口相关资源
	Icon background_message = new ImageIcon(getClass().getResource(skinPro.getProperty("background_message_image_url")));
	Icon smile_naughtyIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/smile_naughty.gif"));
	Icon smile_cryIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/smile_cry.gif"));
	Icon smile_teethIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/smile_teeth.gif"));
	Icon smile_thinkingIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/smile_thinking.gif"));
	Icon smile_worriedIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/smile_worried.gif"));
	Icon smile_foolIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/smile_fool.gif"));
	
	Icon daxiaoIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/daxiao.png"));
	Icon blueMobileIcon = new ImageIcon(getClass().getResource("/com/ytu/resource/faces/blue_mobile.png"));
	Icon[] faceIcons = {
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/se_1.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/se_2.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/se_3.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/se_4.png"))};
	Icon[] progressIcons = {
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_1.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_2.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_3.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_4.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_5.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_6.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_7.png")),
			new ImageIcon(getClass().getResource("/com/ytu/resource/faces/progress_8.png"))};
	private MessageView messageView = null;
	// 设置背景图
	ImageIcon  background = new ImageIcon(getClass().getResource(skinPro.getProperty("background_image_url")));
	JLabel backImage;
	JPanel backPanel;
	
	// 初始化界面范围
	public final int JFRAME_WIDTH = background.getIconWidth();
	public final int JFRAME_HEIGHT =  background.getIconHeight();
	// 鼠标的在空件上的位置
	public static int M_X;
	public static int M_Y;
	
	public QueryView(){
		init();
		
		handle(this);
	}
	
	// 初始化
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
		
		// 设置整体的背景图
		backImage = new JLabel(background);
		backImage.setBounds(0, 0, this.JFRAME_WIDTH,this.JFRAME_HEIGHT);
		backPanel = (JPanel) this.getContentPane();
		backPanel.setOpaque(false);
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(backImage, new Integer(Integer.MIN_VALUE));
		backPanel.setLayout(new BorderLayout());
		
		// 北部布局
		north_JPanel = new JPanel();
		north_JPanel.setLayout(null);
		north_JPanel.setOpaque(false);
		north_JPanel.setPreferredSize(new Dimension(this.JFRAME_WIDTH, (close_ImageIcon_normal.getIconHeight())+15));
//		north_JPanel.setBackground(Color.RED);
		
		feng_JLabel = getJLabelByIcon(null, feng_ImageIcon);
		feng_JLabel.setLocation(3, 2);
		title_JLabel = new JLabel();
		title_JLabel.setSize(110, close_ImageIcon_normal.getIconHeight()+10);
		title_JLabel.setLocation(feng_ImageIcon.getIconWidth()+5, -2);
		title_JLabel.setText("计费查询 V 10.0");
		title_JLabel.setFont(new Font("宋体",0,11));
		
		close_JLabel = getJLabelByIcon(null, close_ImageIcon_normal);
		close_JLabel.setLocation(this.JFRAME_WIDTH-close_ImageIcon_normal.getIconWidth(), 0);
		
		minimize_JLabel = getJLabelByIcon(null, minimize_ImageIcon_normal);
		minimize_JLabel.setLocation(this.JFRAME_WIDTH-close_ImageIcon_normal.getIconWidth()-minimize_ImageIcon_normal.getIconWidth()+1, 0);
		
		skin_JLabel = getJLabelByIcon(null, skin_ImageIcon_normal);
		skin_JLabel.setLocation((int) minimize_JLabel.getLocation().getX()-skin_ImageIcon_normal.getIconWidth(), 0);
		
		menu_JLabel = getJLabelByIcon(null, menu_ImageIcon_normal);
		menu_JLabel.setLocation((int) skin_JLabel.getLocation().getX()-menu_ImageIcon_normal.getIconWidth(), 0);
		
		// 添加标签
		north_JPanel.add(feng_JLabel);
		north_JPanel.add(title_JLabel);
		north_JPanel.add(close_JLabel);
		north_JPanel.add(minimize_JLabel);
		north_JPanel.add(skin_JLabel);
		north_JPanel.add(menu_JLabel);
		
		// 中间布局
		center_JPanel = new JPanel(null);
		center_JPanel.setOpaque(false);
//		center_JPanel.setBackground(Color.RED);
		
		username_JTextField = new JTextField();
		username_JTextField.setOpaque(false);
		username_JTextField.setSize(150, 25);
		username_JTextField.setLocation(15, 0);
		query_Btn_JLabel = getJLabelByIcon("查询", query_ImageIcon_normal);
		query_Btn_JLabel.setLocation(JFRAME_WIDTH-query_ImageIcon_normal.getIconWidth()-15, 2);
		
		center_JPanel.add(username_JTextField);
		center_JPanel.add(query_Btn_JLabel);
		
		
		// 南部布局
		south_JPanel = new JPanel(null);
		south_JPanel.setPreferredSize(new Dimension(JFRAME_WIDTH, 330));
		south_JPanel.setOpaque(false);
		
		result_JTextArea = new JTextArea();
//		result_JTextArea.setText("xxxxx");
		result_JTextArea.setEditable(false);
		result_JTextArea.setOpaque(false);
		result_JTextArea.setLineWrap(true);			// 自动换行
//		result_JTextArea.setWrapStyleWord(true);	// 单词适应
		
		result_JScrollPane = new JScrollPane(result_JTextArea);
		result_JScrollPane.setSize(JFRAME_WIDTH-20,305);
		// 将JScrollPane背景设为透明
		result_JScrollPane.setOpaque(false);
		result_JScrollPane.getViewport().setOpaque(false);
		result_JScrollPane.setBorder(null);
		
		// 查询结果面板
		result_JPanel = new JPanel(null);
		result_JPanel.setSize(JFRAME_WIDTH-20, 305);
		result_JPanel.setOpaque(false);
		result_JPanel.setLocation(10, 0);
		result_JPanel.setBorder(BorderFactory.createEtchedBorder());
		
		// 底部面板
		bottom_JPanel = new JPanel(null);
		bottom_JPanel.setSize(JFRAME_WIDTH, 25);
		bottom_JPanel.setLocation(0, 305);
		bottom_JPanel.setOpaque(false);
		
		copy_JLabel = getJLabelByIcon(null, copy_ImageIcon_normal);
		copy_JLabel.setLocation(JFRAME_WIDTH-30, 5);
		
		download_JLabel = getJLabelByIcon(null, download_ImageIcon_normal);
		download_JLabel.setLocation(JFRAME_WIDTH-60, 3);
		
		contactMe_JLabel = getJLabelByIcon(null, contactMe_ImageIcon_normal);
		contactMe_JLabel.setLocation(JFRAME_WIDTH-95, 5);
		
		bottom_JPanel.add(copy_JLabel);
		bottom_JPanel.add(download_JLabel);
		bottom_JPanel.add(contactMe_JLabel);
		
		// 添加控件到南部面板
		result_JPanel.add(result_JScrollPane);
		south_JPanel.add(result_JPanel);
		south_JPanel.add(bottom_JPanel);
		
		// 添加面板到窗体
		this.add(north_JPanel, "North");
		this.add(center_JPanel, "Center");
		this.add(south_JPanel, "South");
		// 去掉窗体的样式
		this.setAlwaysOnTop(true);		// 设置总在前
		this.setUndecorated(true);
		this.setIconImage(new ImageIcon(getClass().getResource("/com/ytu/resource/ytdx_ico.jpg")).getImage());
//		this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
		
//		this.setExtendedState(this.ICONIFIED); //最小化 
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //最大化 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(ViewUthil.getPositionX(this.JFRAME_WIDTH), ViewUthil.getPositionY(this.JFRAME_HEIGHT), this.JFRAME_WIDTH, this.JFRAME_HEIGHT);
		this.setVisible(true);
	}
	
	private void handle(final QueryView jFrame){
		
		final TipWindow bubble = new TipWindow(null, 0, 0, "", null);
		final TipWindow bubble2 = new TipWindow(null, 0, 0, "", null);
		// 关闭按钮相关事件
		close_JLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					for (int i = 0; i < jFrame.JFRAME_WIDTH; i+=10){
						Thread.sleep(6);
						jFrame.setSize(jFrame.JFRAME_WIDTH, jFrame.JFRAME_WIDTH-i);
					}
				} catch (Exception e1) {
//					e1.printStackTrace();
					System.exit(0);
				}
				
				System.exit(0);
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

		// 最小化按钮相关事件
		minimize_JLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				try {
					double wh_ratio = (double)jFrame.JFRAME_WIDTH/jFrame.JFRAME_HEIGHT;
					
					for (int i = 0; i < jFrame.JFRAME_HEIGHT; i+=10){
						Thread.sleep(6);
						jFrame.setSize((int)((jFrame.JFRAME_HEIGHT-i)*wh_ratio), jFrame.JFRAME_HEIGHT-i);
					}
				} catch (Exception e1) {
//					e1.printStackTrace();
					jFrame.setSize(jFrame.JFRAME_WIDTH, jFrame.JFRAME_HEIGHT);
					jFrame.setExtendedState(JFrame.ICONIFIED);
				}
				
				jFrame.setSize(jFrame.JFRAME_WIDTH, jFrame.JFRAME_HEIGHT);
				jFrame.setExtendedState(JFrame.ICONIFIED);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				bubble.closeTipRapid();
				
				minimize_JLabel.setIcon(minimize_ImageIcon_down);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bubble.closeTip();
				
				minimize_JLabel.setIcon(minimize_ImageIcon_normal);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bubble.setAll(null, e.getXOnScreen(), e.getYOnScreen()+20, "最小化", null);
				bubble.setVisible(true);
				
				minimize_JLabel.setIcon(minimize_ImageIcon_highlight);
			}
		});
		
		// 皮肤按钮相关事件
		skin_JLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Properties basePro = PropertiesManger.getBaseProperties();
				// 获取当前皮肤名字
				String old_skin_name = skinPro.getProperty("skin_name");
				String count = PropertiesManger.getSkinProperties().getProperty("query_count");
				
				InputStream in = null;
				
				int i = 1;
				while (true){
					String skinName = basePro.getProperty("skin_name_"+i++);
					if (skinName != null){
						if (skinName.equals(old_skin_name)){		// 将获取的名字与现状的名字相比
							skinName = basePro.getProperty("skin_name_"+i);
							if (skinName == null){					// 若找到看下一个是否为空
								// 若下一个为空，获取第一个
								skinName = basePro.getProperty("skin_name_1");
							}
							in = PropertiesManger.class.getClassLoader()
									.getResourceAsStream("com/ytu/config/skin_"+skinName+".properties");
							break;
						}
					}else{
						break;
					}
				}
				
				// 将修改后的信息保存到配置文件中
				try {
					String filePath_str = basePro.getProperty("config_file_path");
					String fileName_str = basePro.getProperty("config_file_name");
					FileOutputStream out = new FileOutputStream(filePath_str+File.separator+fileName_str);
					
					byte[] buff = new byte[1024];
					int len = -1;
					
					while((len=in.read(buff)) != -1){
						out.write(buff, 0, len);
						out.flush();
					}
					
					in.close();
					out.close();
					
					Properties newSkinPro = PropertiesManger.getSkinProperties();
					newSkinPro.setProperty("query_count", count);
					FileOutputStream out2 = new FileOutputStream(filePath_str+File.separator+fileName_str);
					newSkinPro.store(out2, null);
					
					out2.close();
				} catch (IOException e1) {
//					e1.printStackTrace();
				}
				// 显示更改后的皮肤
				skinPro = PropertiesManger.getSkinProperties();
				
				ImageIcon  new_background = new ImageIcon(getClass()
						.getResource(skinPro.getProperty("background_image_url")));
				backImage.setIcon(new_background);
				
				skin_JLabel.setIcon(skin_ImageIcon_highlight);
				
				// 重新加载需要更新皮肤的图片
				new Thread(){
					public void run() {
						jFrame.background_message = new ImageIcon(getClass().getResource(skinPro.getProperty("background_message_image_url")));;
					}
				}.start();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				bubble.closeTipRapid();
				
				skin_JLabel.setIcon(skin_ImageIcon_down);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bubble.closeTip();
				
				skin_JLabel.setIcon(skin_ImageIcon_normal);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bubble.setAll(null, e.getXOnScreen(), e.getYOnScreen()+20, "更换皮肤", null);
				bubble.setVisible(true);
				
				skin_JLabel.setIcon(skin_ImageIcon_highlight);
			}
		});
		
		// 更新按钮相关事件
		menu_JLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				menu_JLabel.setIcon(menu_ImageIcon_highlight);
				
				final Point p = jFrame.getLocation();
				messageView = new MessageView(background_message,smile_worriedIcon,progressIcons,"检测更新中...", null, jFrame);
				
				new Thread(){
					public void run() {
						// 检测更新
						UpdateResultType updateResultType = CheckUpdate.isLatest(basePro.getProperty("curren_version"));
						
						// 分析查询结果
						if (updateResultType == UpdateResultType.IS_LATEST){
							messageView.closeViewRapid();
							
							messageView = new MessageView(background_message,smile_teethIcon,null,"亲，你的是最新版本哦...", 180, jFrame);
							// 关闭提示信息
							new Thread(){
								public void run() {
									try {
										Thread.sleep(2000);
									} catch (InterruptedException e) {
//										e.printStackTrace();
										messageView.closeView();
									}
									messageView.closeView();
								}
							}.start();
							messageView.showMessage(p.x-10, p.y+100);
						}else if (updateResultType == UpdateResultType.NET_ERROR){
							messageView.closeViewRapid();
							
							messageView = new MessageView(background_message,smile_cryIcon,null,"亲，网路出问题了吧...", 180, jFrame);
							// 关闭提示信息
							new Thread(){
								public void run() {
									try {
										Thread.sleep(2000);
									} catch (InterruptedException e) {
//										e.printStackTrace();
										messageView.closeView();
									}
									messageView.closeView();
								}
							}.start();
							messageView.showMessage(p.x-10, p.y+100);
						}else if (updateResultType == UpdateResultType.NOT_LATEST){
							messageView.closeViewRapid();
							
							messageView = new MessageView(background_message,smile_foolIcon,null,"亲，不是最新版本哦...", 180, jFrame);
							// 关闭提示信息
							new Thread(){
								public void run() {
									try {
										Thread.sleep(2000);
									} catch (InterruptedException e) {
//										e.printStackTrace();
										messageView.closeView();
									}
									messageView.closeView();
								}
							}.start();
							messageView.showMessage(p.x-10, p.y+100);
						}else{
							messageView.closeView();
						}
					
				}}.start();
				
				messageView.showMessage(p.x-10, p.y+100);
				messageView = null;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				bubble.closeTipRapid();
				bubble2.closeTipRapid();
				
				menu_JLabel.setIcon(menu_ImageIcon_down);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bubble.closeTip();
				bubble2.closeTip();
				
				menu_JLabel.setIcon(menu_ImageIcon_normal);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				String version = basePro.getProperty("curren_version_describe");
				String versionLen = basePro.getProperty("curren_version_str_len");
				
				bubble.setAll(null, e.getXOnScreen(), e.getYOnScreen()+20,"检测更新（需联网）", null);
				bubble2.setAll(null, e.getXOnScreen(), e.getYOnScreen()+37,"当前版本："+version, Integer.parseInt(versionLen));
				bubble.setVisible(true);
				bubble2.setVisible(true);
				
				menu_JLabel.setIcon(menu_ImageIcon_highlight);
			}
		});
		
		// 中部布局
		// 查询按钮相关事件
		query_Btn_JLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				query_Btn_JLabel.setIcon(query_ImageIcon_highlight);
				querUser(jFrame);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				query_Btn_JLabel.setIcon(query_ImageIcon_down);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				query_Btn_JLabel.setIcon(query_ImageIcon_normal);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				query_Btn_JLabel.setIcon(query_ImageIcon_highlight);
			}
		});
		username_JTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				bubble.closeTip();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bubble.setAll(null, e.getXOnScreen(), e.getYOnScreen()+20, "输入学号，如：2011585043", 150);
				bubble.setVisible(true);
			}
		});
		username_JTextField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10){
					query_Btn_JLabel.setIcon(query_ImageIcon_normal);
					querUser(jFrame);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10){
					query_Btn_JLabel.setIcon(query_ImageIcon_down);
				}
			}
		});
		// 添加底部Label鼠标事件
		copy_JLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				copy_JLabel.setIcon(copy_ImageIcon_highlight);
				
				ClipBoardUtil.copyToClipBoard(result_JTextArea.getText());
			}
			@Override
			public void mousePressed(MouseEvent e) {
				bubble.closeTipRapid();
				
				copy_JLabel.setIcon(copy_ImageIcon_down);
				bubble.setAll(daxiaoIcon, e.getXOnScreen(), e.getYOnScreen()+20, "   复制成功，可以粘贴了哦", 160);
				bubble.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bubble.closeTip();
				
				copy_JLabel.setIcon(copy_ImageIcon_normal);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bubble.setAll(null, e.getXOnScreen(), e.getYOnScreen()+20, "复制所有", null);
				bubble.setVisible(true);
				
				copy_JLabel.setIcon(copy_ImageIcon_highlight);
			}
		});
		// 下载其他版本
		download_JLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				download_JLabel.setIcon(download_ImageIcon_highlight);
				
				// 打开链接
		        String osName = System.getProperties().getProperty("os.name");
		        String url = basePro.getProperty("check_updateurl");
		        try {
			        if (osName.indexOf("Linux") != -1) {
			            
						Runtime.getRuntime().exec("htmlview");
						
			        } else if (osName.indexOf("Windows") != -1){
			            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+url);
			        } else {
			        }
		        } catch (IOException e1) {
//					e1.printStackTrace();
		        	bubble.setAll(null, e.getXOnScreen(), e.getYOnScreen()+20, "出问题了！", null);
		        	bubble.setVisible(true);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				bubble.closeTipRapid();
				
				download_JLabel.setIcon(download_ImageIcon_down);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bubble.closeTip();
				
				download_JLabel.setIcon(download_ImageIcon_normal);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bubble.setAll(null, e.getXOnScreen(), e.getYOnScreen()+20, "下载其他版本（需联网）", null);
				bubble.setVisible(true);
				
				download_JLabel.setIcon(download_ImageIcon_highlight);
			}
		});
		// 联系我
		contactMe_JLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				contactMe_JLabel.setIcon(contactMe_ImageIcon_highlight);
				Point p = jFrame.getLocation();
				
				new ContactView(p.x-60,p.y+50,jFrame);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				bubble.closeTipRapid();
				
				contactMe_JLabel.setIcon(contactMe_ImageIcon_normal);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bubble.closeTip();
				
				contactMe_JLabel.setIcon(contactMe_ImageIcon_normal);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				bubble.setAll(blueMobileIcon, e.getXOnScreen(), e.getYOnScreen()+20, "   联系我.反馈BUG，建议.", 160);
				bubble.setVisible(true);
				
				contactMe_JLabel.setIcon(contactMe_ImageIcon_highlight);
			}
		});
		
		// 北部布局，拖动窗口移动事件添加
		north_JPanel.addMouseMotionListener(new MouseMotionAdapter() {
			// 按下并拖动
			public void mouseDragged(MouseEvent e) {
				int X = QueryView.M_X;
				int Y = QueryView.M_Y;

				jFrame.setLocation(e.getXOnScreen()-X, e.getYOnScreen()-Y);
			}
		});
		north_JPanel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				QueryView.M_X = e.getX();
				QueryView.M_Y = e.getY();
			}
		});
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
	
	private void querUser(final QueryView jFrame) {
		final Point p = jFrame.getLocation();
		String isCheck = basePro.getProperty("is.chect.count");
		if (!"no".equals(isCheck)){
			// 检测用户是否可用
			try {
				boolean isCanUse = CheckUseCount.isCanUse();
				if (!isCanUse){
					messageView = new MessageView(background_message,smile_cryIcon,null,"对不起，您的使用次数已用完.",180,jFrame);
					new Thread(){
						public void run() {
							try{
								Thread.sleep(2000);
								messageView.closeView();
							} catch (Exception e1) {
								messageView.closeView();
							}
						}
					}.start();
					messageView.showMessage(p.x-10, p.y+100);	// 会堵塞当前窗口线程
					return;
				}
			} catch (IOException e2) {
				messageView = new MessageView(background_message,smile_cryIcon,null,"出错了...",100,jFrame);
				new Thread(){
					public void run() {
						try{
							Thread.sleep(2000);
							messageView.closeView();
						} catch (Exception e1) {
							messageView.closeView();
						}
					}
				}.start();
				messageView.showMessage(p.x-10, p.y+100);	// 会堵塞当前窗口线程
			}
		}
		
		messageView = new MessageView(background_message,smile_naughtyIcon,progressIcons,"努力查询中...", null, jFrame);
		new Thread(){
			public void run() {
				boolean isHave = false;
				try {
					// 查询账号代码
					String stunum = username_JTextField.getText();
					String user = null;
//					System.out.println(stunum);
					for (int i = 0; i < 50; i++){
						if (i < 10){
							user = stunum +"0"+i;
						}else{
							user = stunum+i;
						}
						//System.out.println(user);
						String queryResult = GetUserInfo.getInfo(user);
						
						if (queryResult != null){
							isHave = true;
							result_JTextArea.append(queryResult+"\r\n");
						}
					}// 查询结束
					result_JTextArea.append("------------------\r\n");
					messageView.closeViewRapid();
					
					if (isHave){
						messageView = new MessageView(background_message,smile_teethIcon,null,"学弟学妹对不住了...",180,jFrame);
						new Thread(){
							public void run() {
								try{
									Thread.sleep(2000);
									messageView.closeView();
								} catch (Exception e1) {
									messageView.closeView();
								}
							}
						}.start();
						messageView.showMessage(p.x-10, p.y+100);	// 会堵塞当前窗口线程
					}else{
						messageView = new MessageView(background_message,smile_cryIcon,null,"太不给力了，学弟学妹聪明了.", 180, jFrame);
						new Thread(){
							public void run() {
								try{
									Thread.sleep(2000);
									messageView.closeView();
								} catch (Exception e1) {
									messageView.closeView();
								}
							}
						}.start();
						messageView.showMessage(p.x-10, p.y+100);	// 会堵塞当前窗口线程
					}
					
				} catch (Exception e1) {
					messageView.closeViewRapid();
					messageView = new MessageView(background_message,smile_foolIcon,null,"请在烟大校内网使用...", 180, jFrame);
					new Thread(){
						public void run() {
							try{
								Thread.sleep(2000);
								messageView.closeView();
							} catch (Exception e1) {
								messageView.closeView();
							}
						}
					}.start();
					messageView.showMessage(p.x-10, p.y+100);	// 会堵塞当前窗口线程
				}
			}
		}.start();
		messageView.showMessage(p.x-10, p.y+100);	// 会堵塞当前窗口线程
	}
	
}