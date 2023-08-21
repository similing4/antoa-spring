package com.whuying.antoa.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.SelectJoinStep;
import org.jooq.UpdateSetMoreStep;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.jooq.Condition;
import org.jooq.DeleteUsingStep;
import org.jooq.InsertSetMoreStep;
import org.jooq.InsertSetStep;

@Service
public class DB {
	
	DB(){}
	
	@Autowired
    public DB(JdbcTemplate database){
		DB.database = database;
    }
	
	public static JdbcTemplate database = null;

	static class Step {
		String operator;
		Object[] params;

		Step(String operator, Object[] params) {
			this.operator = operator;
			this.params = params;
		}

		boolean isType(String type) {
			if (type == null && operator == null)
				return true;
			if (type == null || operator == null)
				return false;
			return type.equals(this.operator);
		}
	}

	private List<Step> steps = new ArrayList<Step>();

	public DB clone() {
		DB ret = new DB();
		ret.steps.addAll(steps);
		return ret;
	}

	public static DB table(String table) {
		DB db = new DB();
		db.steps.add(new Step("select", new Object[] { table }));
		return db;
	}

	public static List<Map<String, Object>> executeSelect(JdbcTemplate database, String sql, Object[] params) {
		List<Map<String, Object>> arr = database.queryForList(sql, params);
		for(Map<String, Object> row : arr)
			convertRow(row);
		return arr;
	}
	
	private static void convertRow(Map<String, Object> row){
		for(String key : row.keySet()) {
			if(row.get(key) instanceof java.sql.Timestamp)
				row.put(key, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((java.sql.Timestamp)row.get(key)));
			if(row.get(key) instanceof Boolean)
				row.put(key, ((Boolean)row.get(key)) ? 1 : 0);
		}
	}

	public DB where(String wh) {
		steps.add(new Step("where", new Object[] { DSL.condition(wh) }));
		return this;
	}

	public DB whereIn(String key, Object[] value) {
		steps.add(new Step("where", new Object[] { DSL.field(key).in(value) }));
		return this;
	}

	public DB where(String key, Object value) {
		return where(key, "=", value);
	}

	public DB where(String key, String operator, Object value) {
		if ("=".equals(operator))
			steps.add(new Step("where", new Object[] { DSL.field(key).eq(value) }));
		else if (">".equals(operator))
			steps.add(new Step("where", new Object[] { DSL.field(key).gt(value) }));
		else if ("<".equals(operator))
			steps.add(new Step("where", new Object[] { DSL.field(key).lt(value) }));
		else if (">=".equals(operator))
			steps.add(new Step("where", new Object[] { DSL.field(key).ge(value) }));
		else if ("<=".equals(operator))
			steps.add(new Step("where", new Object[] { DSL.field(key).le(value) }));
		else if ("like".equals(operator))
			steps.add(new Step("where", new Object[] { DSL.field(key).like(value + "") }));
		return this;
	}

	public DB groupBy(String... keys) {
		steps.add(new Step("groupBy", keys));
		return this;
	}

	public DB having(String key, Object value) {
		return having(key, "=", value);
	}

	public DB having(String key, String operator, Object value) {
		if ("=".equals(operator))
			steps.add(new Step("having", new Object[] { DSL.field(key).eq(value) }));
		else if (">".equals(operator))
			steps.add(new Step("having", new Object[] { DSL.field(key).gt(value) }));
		else if ("<".equals(operator))
			steps.add(new Step("having", new Object[] { DSL.field(key).lt(value) }));
		else if (">=".equals(operator))
			steps.add(new Step("having", new Object[] { DSL.field(key).ge(value) }));
		else if ("<=".equals(operator))
			steps.add(new Step("having", new Object[] { DSL.field(key).le(value) }));
		return this;
	}

	public DB limit(int limit) {
		steps.add(new Step("limit", new Object[] { limit }));
		return this;
	}

	public DB leftJoin(String table, String field1, String operator, String field2) {
		steps.add(new Step("leftJoin", new Object[] { table, field1, operator, field2 }));
		return this;
	}

	public DB setFields(String... fields) {
		steps.add(new Step("setFields", fields));
		return this;
	}

	public DB orderBy(OrderByBean... orderByItems) {
		steps.add(new Step("orderBy", orderByItems));
		return this;
	}

	public DB orderBy(String column, String desc) {
		steps.add(new Step("orderBy", new Object[] { new OrderByBean(column,
				"asc".equals(desc.toLowerCase()) ? OrderByBean.OrderByType.ASC : OrderByBean.OrderByType.DESC) }));
		return this;
	}

	private String arrayJoin(Object[] arr) {
		StringBuilder builder = new StringBuilder();
		for (Object item : arr)
			builder.append("| " + item + " |");
		return builder.toString();
	}

	private InsertSetMoreStep<Record> generateInsertStep(List<Map<String, Object>> params) {
		String table = null;
		for (Step step : steps) {
			if (step.isType("select"))
				table = step.params[0] + "";
		}
		InsertSetStep<Record> step = DSL.using(SQLDialect.MYSQL).insertInto(DSL.table(table));
		InsertSetMoreStep<Record> step2 = null;
		int index = 0;
		for(Map<String, Object> item : params) {
			for(Map.Entry<String, Object> column : item.entrySet())
				if(step2 != null)
					step2 = step2.set(DSL.field(column.getKey()), column.getValue());
				else
					step2 = step.set(DSL.field(column.getKey()), column.getValue());
			index++;
			if(step2 != null&&index < params.size())
				step2.newRecord();
		}
		return step2;
	}

	private UpdateSetMoreStep<Record> generateUpdateStep(Map<String, Object> param) {
		String table = null;
		for (Step step : steps) {
			if (step.isType("select"))
				table = step.params[0] + "";
		}
		UpdateSetMoreStep<Record> ret = DSL.using(SQLDialect.MYSQL).update(DSL.table(table)).set(param);
		for (Step step : steps) {
			if (step.isType("where"))
				ret.where((Condition) step.params[0]);
			if (step.isType("limit"))
				ret.limit((int) step.params[0]);
			if (step.isType("leftJoin"))
				throw new RuntimeException("Cannot use leftJoin in delete method!");
			if (step.isType("groupBy"))
				throw new RuntimeException("Cannot use groupBy in delete method!");
			if (step.isType("having"))
				throw new RuntimeException("Cannot use having in delete method!");
			if (step.isType("orderBy"))
				ret.orderBy(Arrays.asList(step.params).stream().map(t -> {
					OrderByBean bean = (OrderByBean) t;
					if (bean.type == OrderByBean.OrderByType.ASC)
						return DSL.field(bean.column).asc();
					return DSL.field(bean.column).desc();
				}).collect(Collectors.toList()));
		}
		return ret;
	}

	private DeleteUsingStep<Record> generateDeleteStep() {
		String table = null;
		for (Step step : steps) {
			if (step.isType("select"))
				table = step.params[0] + "";
		}
		DeleteUsingStep<Record> ret = DSL.using(SQLDialect.MYSQL).deleteFrom(DSL.table(table));
		for (Step step : steps) {
			if (step.isType("where"))
				ret.where((Condition) step.params[0]);
			if (step.isType("limit"))
				ret.limit((int) step.params[0]);
			if (step.isType("leftJoin"))
				throw new RuntimeException("Cannot use leftJoin in delete method!");
			if (step.isType("groupBy"))
				throw new RuntimeException("Cannot use groupBy in delete method!");
			if (step.isType("having"))
				throw new RuntimeException("Cannot use having in delete method!");
			if (step.isType("orderBy"))
				ret.orderBy(Arrays.asList(step.params).stream().map(t -> {
					OrderByBean bean = (OrderByBean) t;
					if (bean.type == OrderByBean.OrderByType.ASC)
						return DSL.field(bean.column).asc();
					return DSL.field(bean.column).desc();
				}).collect(Collectors.toList()));
		}
		return ret;
	}

	private SelectJoinStep<Record> generateSelectStep() {
		String table = null;
		List<Object> fields = new ArrayList<Object>();
		for (Step step : steps) {
			if (step.isType("setFields"))
				fields = Arrays.asList(step.params);
			if (step.isType("select"))
				table = step.params[0] + "";
		}
		SelectJoinStep<Record> ret = DSL.using(SQLDialect.MYSQL)
				.select(fields.stream().map(t -> DSL.field(t + "")).collect(Collectors.toList())).from(table);
		for (Step step : steps) {
			if (step.isType("where"))
				ret.where((Condition) step.params[0]);
			if (step.isType("limit"))
				ret.limit((int) step.params[0]);
			if (step.isType("leftJoin")) {
				String _table, field1, operator, field2;
				_table = (String) step.params[0];
				field1 = (String) step.params[1];
				operator = (String) step.params[2];
				field2 = (String) step.params[3];
				if ("=".equals(operator))
					ret.leftJoin(_table).on(DSL.field(field1).eq(DSL.field(field2)));
				else if (">".equals(operator))
					ret.leftJoin(_table).on(DSL.field(field1).gt(DSL.field(field2)));
				else if ("<".equals(operator))
					ret.leftJoin(_table).on(DSL.field(field1).lt(DSL.field(field2)));
				else if (">=".equals(operator))
					ret.leftJoin(_table).on(DSL.field(field1).ge(DSL.field(field2)));
				else if ("<=".equals(operator))
					ret.leftJoin(_table).on(DSL.field(field1).le(DSL.field(field2)));
			}
			if (step.isType("groupBy"))
				ret.groupBy(
						Arrays.asList(step.params).stream().map(t -> DSL.field(t + "")).collect(Collectors.toList()));
			if (step.isType("having"))
				ret.having((Condition) step.params[0]);
			if (step.isType("orderBy"))
				ret.orderBy(Arrays.asList(step.params).stream().map(t -> {
					OrderByBean bean = (OrderByBean) t;
					if (bean.type == OrderByBean.OrderByType.ASC)
						return DSL.field(bean.column).asc();
					return DSL.field(bean.column).desc();
				}).collect(Collectors.toList()));
		}
		return ret;
	}

	public List<Map<String, Object>> get() {
		SelectJoinStep<Record> db = generateSelectStep();
		System.out.println("SQL select execute: " + db.getSQL() + System.lineSeparator() + "SQL select bind values: "
				+ arrayJoin(db.getBindValues().toArray()));
		List<Map<String, Object>> arr = database.queryForList(db.getSQL(), db.getBindValues().toArray());
		for(Map<String, Object> row : arr)
			convertRow(row);
		return arr;
	}

	public Map<String, Object> first() {
		this.limit(1);
		SelectJoinStep<Record> db = generateSelectStep();
		System.out.println("SQL select execute: " + db.getSQL() + System.lineSeparator() + "SQL select bind values: "
				+ arrayJoin(db.getBindValues().toArray()));
		List<Map<String, Object>> arr = database.queryForList(db.getSQL(), db.getBindValues().toArray());
		if (arr.size() == 0)
			return null;
		for(Map<String, Object> row : arr)
			convertRow(row);
		return arr.get(0);
	}

	public long count() {
		this.setFields("count(1) as c");
		SelectJoinStep<Record> db = generateSelectStep();
		System.out.println("SQL select execute: " + db.getSQL() + System.lineSeparator() + "SQL select bind values: "
				+ arrayJoin(db.getBindValues().toArray()));
		return (long) database.queryForMap(db.getSQL(), db.getBindValues().toArray()).get("c");
	}

	public double sum(String column) {
		this.setFields("sum(" + column + ") as c");
		SelectJoinStep<Record> db = generateSelectStep();
		System.out.println("SQL select execute: " + db.getSQL() + System.lineSeparator() + "SQL select bind values: "
				+ arrayJoin(db.getBindValues().toArray()));
		return (double) database.queryForMap(db.getSQL(), db.getBindValues().toArray()).get("c");
	}

	private HttpServletRequest request() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		Assert.notNull(servletRequestAttributes, "RequestAttributes不能为null");
		return servletRequestAttributes.getRequest();
	}

	public PaginateResult paginate(int pageSize, int page) {
		long totalRecord = this.clone().count();
		String p = request().getParameter("page");
		if (p != null)
			try {
				page = Integer.parseInt(p);
			} catch (Exception e) {
			}
		SelectJoinStep<Record> db = generateSelectStep();
		db.limit((page - 1) * pageSize, pageSize);
		System.out.println("SQL select execute: " + db.getSQL() + System.lineSeparator() + "SQL select bind values: "
				+ arrayJoin(db.getBindValues().toArray()));
		PaginateResult ret = new PaginateResult();
		ret.data = database.queryForList(db.getSQL(), db.getBindValues().toArray());
		for(Map<String, Object> row : ret.data)
			convertRow(row);
		ret.current_page = page;
		ret.last_page = (totalRecord + pageSize - 1) / pageSize;
		ret.per_page = pageSize;
		ret.to = page * pageSize;
		ret.total = totalRecord;
		ret.path = request().getRequestURL().toString();
		ret.first_page_url = request().getRequestURL().toString() + "?page=1";
		ret.last_page_url = request().getRequestURL().toString() + "?page=" + ret.last_page;
		ret.next_page_url = null;
		ret.prev_page_url = null;
		if(ret.current_page < ret.last_page)
			ret.next_page_url = request().getRequestURL().toString() + "?page=" + (ret.current_page + 1);
		if(ret.current_page > 1)
			ret.prev_page_url = request().getRequestURL().toString() + "?page=" + (ret.current_page - 1);
		return ret;
	}

	public int delete() {
		DeleteUsingStep<Record> db = generateDeleteStep();
		System.out.println("SQL delete execute: " + db.getSQL() + System.lineSeparator() + "SQL delete bind values: "
				+ arrayJoin(db.getBindValues().toArray()));
		return database.update(db.getSQL(), db.getBindValues().toArray());
	}

	public int update(Map<String, Object> param) {
		UpdateSetMoreStep<Record> db = generateUpdateStep(param);
		System.out.println("SQL update execute: " + db.getSQL() + System.lineSeparator() + "SQL update bind values: "
				+ arrayJoin(db.getBindValues().toArray()));
		return database.update(db.getSQL(), db.getBindValues().toArray());
	}

	public int insert(Map<String, Object> values) {
		List<Map<String, Object>> v = new ArrayList<>();
		v.add(values);
		InsertSetMoreStep<Record> db = generateInsertStep(v);
		System.out.println("SQL insert execute: " + db.getSQL() + System.lineSeparator() + "SQL insert bind values: "
				+ arrayJoin(db.getBindValues().toArray()));
		return database.update(db.getSQL(), db.getBindValues().toArray());
	}
	
	public static int queryUpdate(String sql, Object... args) {
		if(args == null)
			args = new Object[] {};
		return database.update(sql, args);
	}

	public static List<Map<String, Object>> querySelect(String sql, Object... args) {
		if(args == null)
			args = new Object[] {};
		List<Map<String, Object>> arr = database.queryForList(sql, args);
		for(Map<String, Object> row : arr)
			convertRow(row);
		return arr;
	}

	public List<String> pluck(String column) {
		this.setFields(column);
		return this.get().stream().map(row -> {
			return row.get(column) + "";
		}).collect(Collectors.toList());
	}
}
