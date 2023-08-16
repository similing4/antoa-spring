package com.whuying.antoa.utils.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ClassName: ListRowButtonNavigateWithParamChange
 * 描述: 根据传入行的指定字段进行变化后进行页面跳转
 */
public class ListRowButtonNavigateWithParamChange extends ListRowButtonNavigate {

	private static final long serialVersionUID = 7010498064101373031L;
	/**
     * 行数据对应需要的传入列
     */
    private Map<String, String> destCol;

    /**
     * ListRowButtonNavigateNormal constructor.
     * @param String baseUrl 按钮点击后需要跳转到或需要调用API的不带参数的URL
     * @param String buttonText 按钮显示的文本
     * @param Map<String, String> destColSourceColMap 目标页面的字段=>当前选中行的字段
     * @param String buttonType 按钮类型
     */
    public ListRowButtonNavigateWithParamChange(String baseUrl, String buttonText, Map<String, String> destColSourceColMap, String buttonType) {
        super(baseUrl, buttonText, buttonType);
        this.destCol = destColSourceColMap;
    }

    @Override
    public List<UrlParamCalculatorParamItem> calcButtonParam(UrlParamCalculator calculator) {
    	List<UrlParamCalculatorParamItem> ret = new ArrayList<>();
        for(Entry<String, String> source : this.destCol.entrySet())
            ret.add(new UrlParamCalculatorParamItem(source.getKey(), calculator.getRowParamByKey(source.getValue()).val));
        return ret;
    }

    @Override
    public boolean judgeIsShow(UrlParamCalculator $calculator) {
        return true;
    }
}
