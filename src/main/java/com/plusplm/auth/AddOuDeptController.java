package com.plusplm.auth;

import java.util.ArrayList;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AddOuDeptController {

	@Autowired
	private LdapTemplate ldapTemplate;

	// @RequestMapping(value = "/ou", method = RequestMethod.POST)
	// public @ResponseBody String createou(@RequestBody Map<Object, Object>
	// request) {
	// String ou = (String) request.get("ou");
	//
	// Name dn = LdapNameBuilder.newInstance().add("ou", "people").add("ou",
	// ou).build();
	// DirContextAdapter context = new DirContextAdapter(dn);
	//
	// context.setAttributeValues("objectclass", new String[] { "top",
	// "organizationalUnit" });
	//
	// ldapTemplate.bind(context);
	// return "OK";
	// }

	@ResponseBody
	public String addDept(ArrayList<String> parDeptList, String deptId) {
		Object body = null;
		HttpStatus status = null;
		// String ou = (String) request.get("ou");

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

		// nameBuild = nameBuild.add("ou", "d").add("ou","b");
		dn = nameBuild.build();

		// dn = LdapNameBuilder.newInstance().add("ou", "people").add("ou", ou).build();
		DirContextAdapter context = new DirContextAdapter(dn);

		context.setAttributeValues("objectclass", new String[] { "top", "organizationalUnit" });

		try {
			ldapTemplate.bind(context);
			status = HttpStatus.OK;
			body = "OK";
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			body = "Entry Already Exists";
		}
		return null;
	}

	@ResponseBody
	public String delDept(ArrayList<String> parDeptList, String deptId) {
		Object body = null;
		HttpStatus status = null;
		// String ou = (String) request.get("ou");

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

		dn = nameBuild.build();
		try {
			ldapTemplate.unbind(dn);
			status = HttpStatus.OK;
			body = "OK";
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			body = "Entry Already Exists";
		}
		return null;
	}

	@ResponseBody
	public String modDept(ArrayList<String> oldParDeptList, ArrayList<String> newParDeptList, String deptId) {
		Object body = null;
		HttpStatus status = null;

		String oldDnString = null;
		String newDnString = null;

		LdapNameBuilder oldNameBuild = LdapNameBuilder.newInstance().add("ou", "people");
		String oldParDeptId = null;
		if (oldParDeptList.size() != 0) {
			for (int i = 0; i < oldParDeptList.size(); i++) {
				oldParDeptId = oldParDeptList.get(i);
				oldNameBuild = oldNameBuild.add("ou", oldParDeptId);
			}
			oldNameBuild = oldNameBuild.add("ou", deptId);
		} else {
			oldNameBuild = oldNameBuild.add("ou", deptId);
		}
		oldDnString = oldNameBuild.build().toString();

		LdapNameBuilder newNameBuild = LdapNameBuilder.newInstance().add("ou", "people");
		String newParDeptId = null;
		if (newParDeptList.size() != 0) {
			for (int i = 0; i < newParDeptList.size(); i++) {
				newParDeptId = newParDeptList.get(i);
				newNameBuild = newNameBuild.add("ou", newParDeptId);
			}
			newNameBuild = newNameBuild.add("ou", deptId);
		} else {
			newNameBuild = newNameBuild.add("ou", deptId);
		}
		newDnString = newNameBuild.build().toString();

		final Name oldDn = LdapNameBuilder.newInstance(oldDnString).build();
		final Name newDn = LdapNameBuilder.newInstance(newDnString).build();

		try {
			ldapTemplate.rename(oldDn, newDn);
			status = HttpStatus.OK;
			body = "OK";
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			body = "Entry Already Exists";
		}
		return null;
	}

}
