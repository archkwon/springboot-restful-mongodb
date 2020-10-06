package com.plusplm.auth;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.web.bind.annotation.ResponseBody;

public class LdapAuthCheck {

	@Autowired
	private LdapTemplate ldapTemplate;

	// Repository별 ldap 권한 체크
	public @ResponseBody boolean ldapAuthCheck(String uid, String shipCode, String dirName) {
		Name dn = LdapNameBuilder.newInstance().add("ou", "dept").add("ou", "user").add("uid", uid).build();
		
		Name 수정중 = LdapNameBuilder.newInstance().add("ou", "Repository").add("ou", shipCode).add("ou", dirName).add("uid", uid).build();

		boolean tf = false;
		try {
			// 권한 있음
			tf = ldapTemplate.lookupContext(dn) != null;
			return tf;

		} catch (NameNotFoundException expected) {
			// 권한 없음
			return tf;
		}
	}

}
