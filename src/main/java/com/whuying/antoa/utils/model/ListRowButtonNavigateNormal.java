package com.whuying.antoa.utils.model;

import java.util.ArrayList;
import java.util.List;

public class ListRowButtonNavigateNormal extends ListRowButtonNavigate {

	private static final long serialVersionUID = 7596480294586216033L;
	/**
	 * 行数据对应需要的传入列
	 */
	private String destCol;

	/**
	 * ListRowButtonNavigateNormal constructor.
	 * 
	 * @param String baseUrl 按钮点击后需要跳转到或需要调用API的不带参数的URL
	 * @param String buttonText 按钮显示的文本
	 * @param String destCol 行数据对应需要的传入列
	 * @param String buttonType 按钮类型
	 */
	public ListRowButtonNavigateNormal(String baseUrl, String buttonText, String destCol) {
		this(baseUrl, buttonText, destCol, "primary");
	}

	/**
	 * ListRowButtonNavigateNormal constructor.
	 * 
	 * @param String baseUrl 按钮点击后需要跳转到或需要调用API的不带参数的URL
	 * @param String buttonText 按钮显示的文本
	 * @param String destCol 行数据对应需要的传入列
	 * @param String buttonType 按钮类型
	 */
	public ListRowButtonNavigateNormal(String baseUrl, String buttonText, String destCol, String buttonType) {
		super(baseUrl, buttonText, buttonType);
		this.destCol = destCol;
	}

	@Override
	public List<UrlParamCalculatorParamItem> calcButtonParam(UrlParamCalculator calculator) {
		List<UrlParamCalculatorParamItem> ret = new ArrayList<>();
		ret.add(calculator.getRowParamByKey(this.destCol));
		return ret;
	}

	@Override
	public boolean judgeIsShow(UrlParamCalculator $calculator) {
		return true;
	}
}