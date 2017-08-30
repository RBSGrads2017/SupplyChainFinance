package com.rbs.scm.teama_login.model.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.rbs.scm.teama_login.model.beans.*;
public class SessionUtility {
	public static Session sessionValidation(HttpServletRequest request) {
		HttpSession hs = request.getSession(false);
		Session s = null;
		try{
			s = (Session) hs.getAttribute("session");
		}catch(Exception e) {
			return null;
		}
		return s;
	}
	
}
