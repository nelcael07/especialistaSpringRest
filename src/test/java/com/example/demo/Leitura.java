package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

@Component
public class Leitura {
	public String getContentFromResource(String resouceName) {
		try {
			InputStream stream = ResourceUtils.class.getResourceAsStream(resouceName);
			return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
