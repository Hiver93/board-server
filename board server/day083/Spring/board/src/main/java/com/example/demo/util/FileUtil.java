package com.example.demo.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
	
	private static final String DIR_PATH = "src/main/resources/static/images/";

	public static void saveFile(String fileName, byte[] bytes) throws IOException {
		Path path = Paths.get(DIR_PATH + fileName);
		
		Files.createDirectories(path.getParent());
		Files.write(path, bytes);
	}
}
