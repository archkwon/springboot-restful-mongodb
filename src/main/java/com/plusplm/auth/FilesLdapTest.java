package com.plusplm.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.plusplm.storage.StorageProperties;
import com.plusplm.storage.StorageService;

@RestController
@RequestMapping("/api/v1")
public class FilesLdapTest {
	private StorageService storageService;

	private StorageProperties properties;
	
	@Autowired
	public FilesLdapTest(StorageService storageService) {
		this.storageService = storageService;

	}

	// 선주, 선급, 관청 프로필 출력
	@RequestMapping(value = "/test/{filename:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		
		// LDAP S
		String location = properties.getImgLocation().toString();
		
		// LDAP E
		
		Resource file = storageService.loadAsResourece(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
