package com.whuying.web.admin;

import java.util.Arrays;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whuying.antoa.controller.AntOAController;
import com.whuying.antoa.db.DB;
import com.whuying.antoa.utils.DBCreateOperator;
import com.whuying.antoa.utils.DBEditOperator;
import com.whuying.antoa.utils.DBListOperator;
import com.whuying.antoa.utils.Grid;
import com.whuying.antoa.utils.model.EnumOption;

@RestController
@RequestMapping("/api/admin/user")
public class UserAntOAController extends AntOAController {

	@Override
	public void grid(Grid grid) {
		grid.list(new DBListOperator(DB.table("user")) {})
	        .columnText("id", "ID")
	        .columnPicture("user_icon", "头像", "50", "50")
	        .columnText("username", "用户名")
	        .columnEnum("status", "用户状态", Arrays.asList(
	            new EnumOption("0", "正常"),
	            new EnumOption("1", "禁用")
	        ))
	        .columnText("create_time", "创建时间");
	    grid.createForm(new DBCreateOperator(DB.table("user")) {
	    })
	        .columnPictureLocal("user_icon", "头像")
	        .columnText("username", "用户名")
	        .columnPassword("password", "密码")
	        .columnRadio("status", "用户状态", Arrays.asList(
	            new EnumOption("0", "正常"),
	            new EnumOption("1", "禁用")
	        ));
	    grid.editForm(new DBEditOperator(DB.table("user")) {
	    })
	        .columnHidden("id")
	        .columnPictureLocal("user_icon", "头像")
	        .columnText("username", "用户名")
	        .columnPassword("password", "密码")
	        .columnRadio("status", "用户状态", Arrays.asList(
	            new EnumOption("0", "正常"),
	            new EnumOption("1", "禁用")
	        ))
	        .columnTimestamp("create_time", "创建时间");
	}

	@Override
	public String statistic() {
		return "";
	}

	@Override
	protected boolean checkPower(String uid) {
		return true;
	}
}
