package com.TOMSystem.controller;

//import java.net.PasswordAuthentication;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.TOMSystem.User.User;
import com.TOMSystem.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
		
	//The function
	@RequestMapping("/")
	public String HomePage(Map<String,Object> map)
	{
		return "login";
	}
	

	@RequestMapping(value="/authUser" , method = RequestMethod.POST)
	String authUser(@ModelAttribute User user, BindingResult result,@RequestParam String action,Map<String, Object> map,Model model)
	{
		
		System.out.println("In authUser");
		System.out.println("User email is :"+user.getEmail());
		System.out.println("User password is :"+user.getPassword());
		User searchedUser = userService.getUser(user.getEmail());
		//System.out.println("User password is :"+searchedUser.getName());
		if(searchedUser==null)
		{
			model.addAttribute("email","Invalid Login");
			
			return "login";
		}
		else{
		model.addAttribute("email",user.getEmail());
		return "menu";
		}
		
	}

	@RequestMapping(value="/LoginPage", method=RequestMethod.POST)
	public String GetLoginPage(Map<String,Object> map)
	{
		
		return "UserHome";
	}
	
	@RequestMapping(value="/SignupPage", method=RequestMethod.POST)
	public String GetSignupPage(Map<String,Object> map)
	{
		return "SignUp";
	}
	
	@RequestMapping(value="/Signup", method=RequestMethod.POST)
	public String Signup(@ModelAttribute User user, BindingResult result,@RequestParam String action,Map<String, Object> map) throws MessagingException
	{
		System.out.println(user.getName());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		User userResult=new User();
		if((userService.getUser(user.getEmail())!=null))
		{
			System.out.println("inside already exist"+user.getEmail());
			map.put("userAlreadyExistError","User already exist!!!Either login or signup with new emailId");
			return "SignUp";
		}
		else
		{
			userService.add(user);
			System.out.println("Added to database successfully");
			 userResult=userService.getUser(user.getEmail());
			 
			//////////////////////////////////// 
			 String to = user.getEmail();
			    String subject = "subject";
			    String msg ="email text....";
			    final String from ="tomsystemcmpe275@gmail.com";
			    final  String password ="TOMSystem";

			    Properties props = new Properties();  
			    props.setProperty("mail.transport.protocol", "smtp");     
			    props.setProperty("mail.host", "smtp.gmail.com");  
			    props.put("mail.smtp.auth", "true");  
			    props.put("mail.smtp.port", "465");  
			    props.put("mail.debug", "true");  
			    props.put("mail.smtp.socketFactory.port", "465");  
			    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
			    props.put("mail.smtp.socketFactory.fallback", "false"); 
			    props.put("mail.smtp.starttls.enable", "true");

			    Session session = Session.getInstance(props, new GMailAuthenticator(from, password));
			   //session.setDebug(true);  
			   Transport transport = session.getTransport();  
			   InternetAddress addressFrom = new InternetAddress(from);  

			  // SmtpAuthenticator authentication = new SmtpAuthenticator();
			      
			MimeMessage message = new MimeMessage(session);
			   
			  // MimeMessage message = new MimeMessage(session);  
			   message.setSender(addressFrom);  
			   message.setSubject(subject);  
			   String messageBody = "<div style=\"color:red;\">Welcome to TOMSystem</div><br><div>Please find below link and token to verify yourself</div>";
			   String url="http://localhost:8080/TOMSystem/Verify";
			   messageBody= messageBody.concat("<a href="+'"'+ url +'"'+">Verify your account</a>");
			   System.out.println("Message body is...."+messageBody);
			   message.setContent(messageBody, "text/html; charset=utf-8");
			  // message.setContent(messageBody, "text/plain");  
			   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

			   transport.connect();  
			   Transport.send(message);  
			   transport.close();

				 System.out.println("Email Sent successfully...."); 
				 }
			 
			 map.put("email","Sign Up Successfull!!PLease verify and then login");	
			 map.put("user", userResult);
				 return "login";
		}
	
	@RequestMapping(value="/Verify", method=RequestMethod.GET)
	public String VerifyUser(@ModelAttribute User user, BindingResult result,@RequestParam String action,Map<String, Object> map)
	{
		System.out.println("Inside verification function");
		return "UserVerification";
	}
	
	
	}


