package com.whuying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.whuying.antoa.db.Seeder;

@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {
		JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
		SpringApplication.run(MainApplication.class, args);
		Seeder.seed();
	}
}