package com.plusplm.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void store(MultipartFile file) throws IOException;
	
	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResourece(String filename);

}
