package com.openxava.naviox.actions;

import org.openxava.actions.*;
import org.openxava.util.*;
import org.openxava.*;

import com.openxava.naviox.impl.*;

abstract public class ForwardToOriginalURIBaseAction extends ViewBaseAction implements IForwardAction {
	
	private String forwardURI = null;

	protected void forwardToOriginalURI() throws Exception {
		String originalURI = getRequest().getParameter("originalURI");
		if (originalURI == null) {
			forwardURI = "/";
		}
		else {
			int idx = originalURI.indexOf("/", 1);			
			if (!originalURI.endsWith("/SignIn") && idx > 0 && idx < originalURI.length()) {
				forwardURI = originalURI.startsWith("/" + "repymes")?originalURI.substring(idx):originalURI;
			}
			else {
				forwardURI = "/";
			}
		}
		addPermalinkParameters();  
		System.out.println("ForwardURI-->"+forwardURI);
		forwardURI = SignInHelper.refineForwardURI(getRequest(), forwardURI); 
	
	}
	
	private void addPermalinkParameters() { 
		String detail = getRequest().getParameter("detail");
		if (!Is.emptyString(detail)) {
			forwardURI = forwardURI + "?detail=" + detail;  
		}
		else {
			String action = getRequest().getParameter("action");
			if (!Is.emptyString(action)) {
				forwardURI = forwardURI + "?action=" + action;  
			}			
		}
	}

	public String getForwardURI() {
		return forwardURI;
	}

	public boolean inNewWindow() {
		return false;
	}

}