package com.openxava.naviox.impl;

import java.util.*;

import javax.servlet.http.*;

import org.openxava.hibernate.*;
import org.openxava.jpa.*;
import org.openxava.util.*;
import org.openxava.view.*;

import org.openxava.hibernate.*;
import org.openxava.jpa.*;
import org.openxava.util.*;
import org.openxava.view.*;

import com.openxava.naviox.*;
import com.openxava.naviox.model.*;

/**
 * 
 * @author Javier Paniza
 */

public class SignInHelper {
	
	public static void init(HttpServletRequest request, View view) { 
			String organization = "repymes";
			XPersistence.setDefaultSchema(organization);
			XHibernate.setDefaultSchema(organization); 
	}
	
	
	public static void signIn(HttpServletRequest request, String userName) { 
		User user = User.find(userName);
		HttpSession session = request.getSession(); 
		session.setAttribute("naviox.user", user.getName());
		session.setAttribute("xava.user", user.getName()); 
		UserInfo userInfo = toUserInfo(user.getName());
		session.setAttribute("xava.portal.userinfo", userInfo);
		Users.setCurrentUserInfo(userInfo);
	
		user.setLastLoginDate(new Date()); 
		
		session.removeAttribute("naviox.showssearchmodules"); 
	}
	
	public static boolean isAuthorized(String userName, String password) { 
		return isAuthorized(userName, password, new Messages());
	}

	/**
	 * @since 5.4 
	 */
	public static boolean isAuthorized(String userName, String password, Messages errors) {  
		return isAuthorized(userName, password, errors, "unauthorized_user");
	}	
		
	/**
	 * @since 5.4 
	 */	
	public static boolean isAuthorized(String userName, String password, Messages errors, String unauthorizedMessage) {
		User user = User.find(userName);
		if (user == null) {
			errors.add(unauthorizedMessage);
			errors.add("recover_password"); 
			return false;
		}
		boolean authorized = user.isAuthorized(password);
		System.out.println("Usuario autorizado??  "+authorized);
		if (!authorized) errors.add(unauthorizedMessage);

		return authorized;
	}	 

	private static UserInfo toUserInfo(String userName) {
		User user = User.find(userName);
		UserInfo info = new UserInfo();
		info.setId(user.getName());
		info.setEmail(user.getEmail());;
		return info;
	}
	
	public static String refineForwardURI(HttpServletRequest request, String forwardURI) {  
		//return OrganizationURIs.refine(request, forwardURI); 
		return "/m/Empresa";
	}	
	

}

