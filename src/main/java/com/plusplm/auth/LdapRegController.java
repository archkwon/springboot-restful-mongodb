package com.plusplm.auth;

import java.util.ArrayList;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapRegController {

	@Autowired
	private LdapTemplate ldapTemplate;

	@ResponseBody
	public String regist(ArrayList<String> parDeptList, String deptId, String id, String password, String sirName,
			String commName) {
		String uid = id;
		String userPassword = password;

		String sn = sirName;
		String cn = commName;

		Name dn = null;
		LdapNameBuilder nameBuild = LdapNameBuilder.newInstance().add("ou", "people");
		String parDeptId = null;
		if (parDeptList.size() != 0) {
			for (int i = 0; i < parDeptList.size(); i++) {
				parDeptId = parDeptList.get(i);
				nameBuild = nameBuild.add("ou", parDeptId);
			}
			nameBuild = nameBuild.add("ou", deptId);

		} else {
			nameBuild = nameBuild.add("ou", deptId);
		}
		nameBuild = nameBuild.add("uid", uid);
		dn = nameBuild.build();

		DirContextAdapter context = new DirContextAdapter(dn);

		context.setAttributeValues("objectclass", new String[] { "top", "person", "organizationalPerson",
				"inetOrgPerson", "shadowAccount", "sambaSamAccount", "posixAccount" // SAMBA 설정
		});
		context.setAttributeValue("cn", cn);
		context.setAttributeValue("sn", sn);
		context.setAttributeValue("uid", uid);
		// context.setAttributeValue("userPassword", digestSHA(userPassword)); // 패스워드
		// 암호화 base64 추가해야 됨
		context.setAttributeValue("userPassword", userPassword); // plain 패스워드\

		// SAMBA 설정
		context.setAttributeValue("gidNumber", "0");
		context.setAttributeValue("homeDirectory", "");
		context.setAttributeValue("sambaSID", "");
		context.setAttributeValue("uidNumber", "0");

		ldapTemplate.bind(context);
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
