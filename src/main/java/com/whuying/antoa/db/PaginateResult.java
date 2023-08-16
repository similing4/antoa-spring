package com.whuying.antoa.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaginateResult {
	public List<Map<String, Object>> data = new ArrayList<>();
	public int current_page;
	public long last_page;
	public int per_page;
	public int to;
	public long total;
	public String path;
	public String first_page_url;
	public String last_page_url;
	public String next_page_url;
	public String prev_page_url;
}
