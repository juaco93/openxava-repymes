package com.openxava.naviox.actions;

import org.openxava.util.*;

import com.openxava.naviox.impl.*;
import com.openxava.naviox.model.*;

public class SignInAction extends ForwardToOriginalURIBaseAction {
	
	public void execute() throws Exception {		
		SignInHelper.init(getRequest(), getView()); 
		if (getErrors().contains()) return; 
		String userName = getView().getValueString("user");
		String password = getView().getValueString("password");
		
		//System.out.println("User "+userName + "Pass "+password);
		
		if (Is.emptyString(userName, password)) { 
			addError("unauthorized_user"); 
			return;
		}		
		
		//System.out.println("Password encriptado-->"+User.encrypt(password));
		
		if (!SignInHelper.isAuthorized(userName, password, getErrors())) {
			return;									
		}		
		SignInHelper.signIn(getRequest(), userName); 
		getView().reset();
		getContext().resetAllModulesExceptCurrent(getRequest()); 
		forwardToOriginalURI();
	}
	
}