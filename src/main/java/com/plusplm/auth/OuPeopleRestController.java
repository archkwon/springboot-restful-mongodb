package com.plusplm.auth;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OuPeopleRestController {

	@Autowired
	private LdapTemplate ldapTemplate;

	@RequestMapping(value = "/ldapRegist", method = RequestMethod.POST)
	public @ResponseBody String regist(@RequestBody OuPeopleModel request) {
		String uid = request.getUid();
		String cn = request.getCn();
		String sn = request.getSn();
		String userPassword = request.getUserPassword();
		Name dn = LdapNameBuilder.newInstance().add("ou", "people").add("uid", uid).build();
		DirContextAdapter context = new DirContextAdapter(dn);

		context.setAttributeValues("objectclass",
				new String[] { "top", "person", "organizationalPerson", "inetOrgPerson",
						"shadowAccount", "sambaSamAccount", "posixAccount" //SAMBA 설정
		});
		context.setAttributeValue("cn", cn);
		context.setAttributeValue("sn", sn);
		context.setAttributeValue("uid", uid);
		// context.setAttributeValue("userPassword", digestSHA(userPassword)); // 패스워드
		// 암호화 base64 추가해야 됨
		context.setAttributeValue("userPassword", userPassword); // plain 패스워드\
		
		//SAMBA 설정
		context.setAttributeValue("gidNumber", "0");
		context.setAttributeValue("homeDirectory", "");
		context.setAttributeValue("sambaSID", "");
		context.setAttributeValue("uidNumber", "0");
		

		ldapTemplate.bind(context);
		return "OK";
	}

	@RequestMapping(value = "/ldapModify/{uid}", method = RequestMethod.PUT)
	public @ResponseBody String modify(@PathVariable String uid, @RequestBody OuPeopleModel request) {
		// public void modify(final String username, final String password) {

		String cn = request.getCn();
		String sn = request.getSn();
		String userPassword = request.getUserPassword();
		Name dn = LdapNameBuilder.newInstance().add("ou", "people").add("uid", uid).build();
		DirContextOperations context = ldapTemplate.lookupContext(dn);

		context.setAttributeValues("objectclass",
				new String[] { "top", "person", "organizationalPerson", "inetOrgPerson" });
		context.setAttributeValue("cn", cn);
		context.setAttributeValue("sn", sn);
		// context.setAttributeValue("userPassword", digestSHA(userPassword)); // 패스워드
		// 암호화
		context.setAttributeValue("userPassword", userPassword); // plain 패스워드
		ldapTemplate.modifyAttributes(context);

		return "OK";
	}



	// Base64 암호화
	// private String digestSHA(final String password) {
	// String base64;
	// try {
	// MessageDigest digest = MessageDigest.getInstance("SHA");
	// digest.update(password.getBytes());
	// base64 = Base64.getEncoder().encodeToString(digest.digest());
	// } catch (NoSuchAlgorithmException e) {
	// throw new RuntimeException(e);
	// }
	// return "{SHA}" + base64;
	// }

}
