package com.plusplm.auth;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@RestController
@RequestMapping("/api/v1")

public class LdapLoginController33 {

	private String key = "2b06ce7d-5c60-41ad:fRvtU+JoxdSlUqWezh2jojiiQcH+lFs032MzeIcAw8g="; // something like
																							// 2b06ce7d-5c60-41ad:fRvtU+JoxdSlUqWezh2jojiiQcH+lFs032MzeIcAw8g=
	private String InstanceID = "3635013a-2594-4640"; // something like 3635013a-2594-4640

	private String keyId;
	private String keySecret;

	public LdapLoginController33() {
		String[] keyParts = key.split(":");
		this.keyId = keyParts[0];
		this.keySecret = keyParts[1];
	}

	@Autowired
	private LdapTemplate ldapTemplate;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK) // request method 'post' not supported spring security
	public ResponseEntity authenticate(@RequestBody Map<Object, Object> request) {
		Object body = null;
		HttpStatus status = null;

		String uid = (String) request.get("uid");
		String userPassword = (String) request.get("userPassword");

		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "person")).and(new EqualsFilter("uid", uid));
		boolean tf = ldapTemplate.authenticate(DistinguishedName.EMPTY_PATH, filter.toString(), userPassword);

		String login = String.valueOf(tf);

		if (login.equals("true")) {
			Map<String, Object> map = new HashMap<>();
			Instant now = Instant.now(); // 표준 시간
			Instant nowTime = now.plusSeconds(32400); // 한국 시간
			Instant expireTime = nowTime.plusSeconds(600); // 토큰 만료 10분
			String token = generateToken(uid, nowTime, expireTime);

			map.put("access_token", token);
			map.put("expires_in", expireTime.toString());
			map.put("expires_in2", expireTime.toEpochMilli());
			map.put("uid", uid);
			status = HttpStatus.OK;
			body = map;
		} else {
			status = HttpStatus.BAD_REQUEST;
			body = "User or UserPassword not found";
		}

		return ResponseEntity.status(status).body(body);
	}

	private String generateToken(String uid, Instant nowTime, Instant expireTime) {

		String kimsowon = Jwts.builder().claim("instance", this.InstanceID).setIssuer(this.keyId)
				.setIssuedAt(Date.from(nowTime)).setExpiration(Date.from(expireTime)).setSubject(uid)
				.signWith(SignatureAlgorithm.HS256, "kimsowon").compact();

		return kimsowon;
	}

}
