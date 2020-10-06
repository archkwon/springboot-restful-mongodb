package com.plusplm.auth;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.plusplm.SystemInfor;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@RestController

public class LdapLoginController {

	private String key = "2b06ce7d-5c60-41ad:fRvtU+JoxdSlUqWezh2jojiiQcH+lFs032MzeIcAw8g="; // something like
																							// 2b06ce7d-5c60-41ad:fRvtU+JoxdSlUqWezh2jojiiQcH+lFs032MzeIcAw8g=
	private String InstanceID = "3635013a-2594-4640"; // something like 3635013a-2594-4640

	private String keyId;
	private String keySecret;

	public LdapLoginController() {
		String[] keyParts = key.split(":");
		this.keyId = keyParts[0];
		this.keySecret = keyParts[1];
	}

	@Autowired
	private LdapTemplate ldapTemplate;

	@ResponseBody
	public String authenticate(String id, String password) throws Exception {
		
		
		Object body = null;
		HttpStatus status = null;

		String uid = id;
		String userPassword = SystemInfor.Base64decoder(password);

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
		return "";
	}

	private String generateToken(String uid, Instant nowTime, Instant expireTime) {

		String kimsowon = Jwts.builder().claim("instance", this.InstanceID).setIssuer(this.keyId)
				.setIssuedAt(Date.from(nowTime)).setExpiration(Date.from(expireTime)).setSubject(uid)
				.signWith(SignatureAlgorithm.HS256, "kimsowon").compact();

		return kimsowon;
	}

}
