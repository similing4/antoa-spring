package com.whuying.web.web;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.whuying.antoa.utils.Utils;

@RestController
public class IndexController {
	@RequestMapping(value = { "/", "/index.html" }, method = RequestMethod.GET)
	public String index() throws FileNotFoundException {
		Map<String, Object> data = new HashMap<>();
		data.put("title", "AntOA");
		data.put("developer", "Developed By Shengxinyu");
		return Utils.view("index.html", data);
	}
}
