package com.application.memdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import java.nio.file.Files;


@SpringBootApplication
public class MemdbApplication implements CommandLineRunner {
	@Value("classpath:data.sql")
	private Resource resource;
	public static void main(String[] args) {
		SpringApplication.run(MemdbApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

		// Code to print class paths
		// System.out.println(System.getProperty("java.class.path"));
		// String content = new String(Files.readAllBytes(resource.getFile().toPath()));
		// System.out.println("File Content:" + content);
	}
}
