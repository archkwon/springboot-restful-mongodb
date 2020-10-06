package com.plusplm.auth;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OuDeptRestController {

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

	@RequestMapping(value = "/addDept", method = RequestMethod.POST)
	public ResponseEntity createDept(@RequestBody Map<Object, Object> request) {
		Object body = null;
		HttpStatus status = null;
		String ou = (String) request.get("ou");

		Name dn = LdapNameBuilder.newInstance().add("ou", "people").add("ou", ou).build();
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
		return ResponseEntity.status(status).body(body);
	}

}
