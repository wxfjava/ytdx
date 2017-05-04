package com.ytu.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

	private static Properties basePro = PropertiesManger.getBaseProperties();

	public static void send(String content) throws NoSuchProviderException, MessagingException, Exception {
		String mailHost = basePro.getProperty("mail.host");
		String transport = basePro.getProperty("mail.transport.protocol");
		String clientUsername = basePro.getProperty("client_mail_username");
		String clientPassword = basePro.getProperty("client_mail_password");

		//邮件服务器是什么？发送邮件使用的协议是什么？邮件服务器要不要验证用户名和密码？
		Properties props = new Properties();
		props.setProperty("mail.host", mailHost);
		props.setProperty("mail.transport.protocol", transport);
//		props.setProperty("mail.user", "true");
		Session session = Session.getInstance(props);
		session.setDebug(true);		//发送过程打到控制台
		//1、得到发送邮件的对象
		Transport ts =  session.getTransport();
		//2、连接服务器
		ts.connect(mailHost, clientUsername, clientPassword);
		//3、创建邮件
		MimeMessage message = makeMessage(session, content);
		//4、发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		//5、关闭邮件发送对象
		ts.close();
	}

	private static MimeMessage makeMessage(Session session, String content) throws Exception{
		String senderAddress = basePro.getProperty("client_mail_address");
		String receiveAddress = basePro.getProperty("server_mail_address");
		String mail_subject = basePro.getProperty("mail_subject");
		String curren_version_describe = basePro.getProperty("curren_version_describe");
		
		MimeMessage message = new MimeMessage(session);						//创建了一封邮件
		message.setFrom(new InternetAddress(senderAddress));				//设置发件人
		message.setRecipients(Message.RecipientType.TO, receiveAddress);	//设置收件人的邮箱
		message.setSubject(mail_subject+"-"+curren_version_describe);
		//创建正文：文本
		MimeBodyPart text = new MimeBodyPart();
		text.setContent(content, "text/html;charset=UTF-8");
		
		//描述两者之间的关系
		MimeMultipart mmp = new MimeMultipart();
		mmp.addBodyPart(text);
		
		message.setContent(mmp);		//加到邮件对象中取
		message.saveChanges();			//邮件就创建出来了
		
		return message;
	}

}

