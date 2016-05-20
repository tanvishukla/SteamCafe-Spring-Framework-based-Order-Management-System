package com.TOMSystem.controller;

////////
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.TOMSystem.model.Item;
import com.TOMSystem.model.User;
import com.TOMSystem.service.ItemService;
import com.TOMSystem.service.UserService;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	@RequestMapping("/")
	public String HomePage(Map<String, Object> map) {
		return "login";
	}

	// Method to validate user & navigate to the User Home page or stay on Login
	// Page after Authentication
	@RequestMapping(value = "/authUser", method = RequestMethod.POST)
	String authUser(@ModelAttribute User user, BindingResult result, @RequestParam String action,
			Map<String, Object> map, Model model, HttpServletRequest request) {

		System.out.println("In authUser");
		System.out.println("User email is :" + user.getEmail());
		System.out.println("User password is :" + user.getPassword());
		
		HttpSession session = request.getSession();
		

		// Admin Login Authentication
		if (user.getEmail().equals("admin") && user.getPassword().equals("admin"))
		{

			session.setAttribute("userId", "admin");
			// Return the Admin Homepage
			return "addItem";
		}
		// User Login Authentication
		else {

			// Search this user in from database
			User searchedUser = userService.getUser(user.getEmail());

			// If this user is not present, or this user has not been verified,
			// or password is incorrect
			// then display invalid login message on the login page

			if (searchedUser == null || searchedUser.isEnabled() == false
					|| searchedUser.getPassword().equals(user.getPassword()) == false) {
				model.addAttribute("email", "Invalid Login");

				return "login";
			}

			// If user is present in database and is verified
			else {
				// Add to the model the name
				model.addAttribute("email", user.getEmail());

				// If some items present in the database
				if (itemService.getAllItems().size() != 0) {
					// Then return the item list to the user home page
					model.addAttribute("ItemList", itemService.getAllItems());
				}
				
								
				Item item= new Item();
				map.put("item", item);
				map.put("itemList", itemService.getAllItems());
				map.put("drinksList", itemService.getDrinks());
				map.put("appetizerList", itemService.getAppetizers());
				map.put("maincourseList", itemService.getMainCourse());
				map.put("dessertList", itemService.getDesserts());
				//setting session attribute to user email
				session.setAttribute("userId", user.getEmail());
				return "items";			
				}

		}

	}

	// Navigate to Sign Up Page
	@RequestMapping(value = "/SignupPage", method = RequestMethod.POST)
	public String GetSignupPage(Map<String, Object> map) {
		return "SignUp";
	}

	@RequestMapping(value = "/Signup", method = RequestMethod.POST)
	public String Signup(@ModelAttribute User user, BindingResult result, @RequestParam String action,
			Map<String, Object> map) throws MessagingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		System.out.println(user.getName());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		User userResult = new User();
		if ((userService.getUser(user.getEmail()) != null)) {
			System.out.println("inside already exist" + user.getEmail());
			map.put("userAlreadyExistError", "User already exist!!!Either login or signup with new emailId");
			return "SignUp";
		} else {
			userService.add(user);

			System.out.println("Added to database successfully");
			userResult = userService.getUser(user.getEmail());

			String text = userResult.getEmail();
			String key = "Bar12345Bar12345"; // 128 bit key

			// Create key and cipher
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");

			// encrypt the text
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(text.getBytes());
			System.out.println("Access Token is");
			String accessToken = new String(encrypted.toString());
			System.err.println(accessToken);

			userResult.setActivation_token(accessToken);
			userService.edit(userResult);

			String to = userResult.getEmail();
			String subject = "subject";
			final String from = "tomsystemcmpe275@gmail.com";
			final String password = "TOMSystem";

			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getInstance(props, new GMailAuthenticator(from, password));
			// session.setDebug(true);
			Transport transport = session.getTransport();
			InternetAddress addressFrom = new InternetAddress(from);

			// SmtpAuthenticator authentication = new SmtpAuthenticator();

			MimeMessage message = new MimeMessage(session);

			message.setSender(addressFrom);
			message.setSubject(subject);
			String messageBody = "<div style=\"color:red;\">Welcome to TOMSystem</div><br><div>Please find below link and token to verify yourself</div><br><div>Your Access Token is :"
					+ accessToken + "</div>";
			String url = "http://localhost:8080/TOMSystem/Verify";
			messageBody = messageBody.concat("<a href=" + '"' + url + '"' + ">Verify your account</a>");
			System.out.println("Message body is...." + messageBody);
			message.setContent(messageBody, "text/html; charset=utf-8");
			// message.setContent(messageBody, "text/plain");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			transport.connect();
			Transport.send(message);
			transport.close();

			System.out.println("Email Sent successfully....");
		}

		map.put("email", "Sign Up Successfull!!PLease verify and then login");
		map.put("user", userResult);
		return "login";
	}

	// Navigate to Verify User
	@RequestMapping("/Verify")
	public String VerifyUser(Map<String, Object> map) {
		System.out.println("Inside verification function");
		return "UserVerification";
	}

	// Verifying Access Token of User
	@RequestMapping("/verifyAccess")
	public String VerifyUserAccess(Map<String, Object> map, @RequestParam String accessToken) {
		System.out.println("Access Token Entered is " + accessToken);
		User userResult = new User();

		userResult = userService.getUserFromAccessToken(accessToken);
		if (userResult != null) {

			userResult.setEnabled(true);
			userService.edit(userResult);
			map.put("VerifiedUser", userResult.getEmail() + " verified. Now you can access your account");
			return "login";
		} else {
			map.put("VerifiedUser", "Please enter valid Token");
			return "UserVerification";
		}
	}

}
