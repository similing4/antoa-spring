package com.whuying.web.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.whuying.antoa.controller.AntOAController;
import com.whuying.antoa.db.DB;
import com.whuying.antoa.utils.DBCreateOperator;
import com.whuying.antoa.utils.DBEditOperator;
import com.whuying.antoa.utils.DBListOperator;
import com.whuying.antoa.utils.Grid;
import com.whuying.antoa.utils.GridCreateForm;
import com.whuying.antoa.utils.GridEditForm;
import com.whuying.antoa.utils.GridList;

@RestController
@RequestMapping("/api/admin/user")
public class UserAntOAController extends AntOAController {

	@Override
	public void grid(Grid grid) {
		GridList gridList = grid.list(new DBListOperator(DB.table("_pls_game")) {
		});
		gridList.columnText("id", "ID");
		gridList.columnText("name", "名称");
		GridCreateForm gridCreateForm = grid.createForm(new DBCreateOperator(DB.table("_pls_game")) {});
		gridCreateForm.columnText("name", "名称");
		gridCreateForm.columnPictureLocal("test", "测试");
		GridEditForm gridEditForm = grid.editForm(new DBEditOperator(DB.table("_pls_game")) {});
		gridEditForm.columnHidden("id");
		gridEditForm.columnText("name", "名称");
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
