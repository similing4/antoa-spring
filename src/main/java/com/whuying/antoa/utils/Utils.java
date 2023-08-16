package com.whuying.antoa.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;

@Service
public class Utils {

	private static Environment environment;

	@Autowired
	public Utils(Environment environment) {
		Utils.environment = environment;
	}

	public static HttpServletRequest request() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		Assert.notNull(servletRequestAttributes, "RequestAttributes不能为null");
		return servletRequestAttributes.getRequest();
	}

	public static String readJSONFromRequest(HttpServletRequest request) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		return sb.toString();
	}

	public static String md5(String text) {
		return DigestUtils.md5DigestAsHex(text.getBytes()).toLowerCase();
	}

	public static <T> T config(String path, T defaultValue, Class<T> type) {
		System.out.println(path + ":" + environment.getProperty(path));
		return environment.getProperty(path, type, defaultValue);
	}

	public static String view(String templatePath, Map<String, Object> context) throws FileNotFoundException {
		JetEngine engine = JetEngine.create();
		JetTemplate template = engine.getTemplate("/" + environment.getProperty("antoa.template_file_path", "template") + "/" + templatePath);
		StringWriter writer = new StringWriter();
		template.render(context, writer);
		// 输出结果
		return writer.toString();
	}
}
