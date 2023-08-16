package com.whuying.antoa.utils.model;

public class EnumOption {
	/**
     * 是否可选中
     */
    public boolean disabled;
    /**
     * 选项选中后传给后端的值
     */
    public String value;
    /**
     * 选项展示的内容
     */
    public String title;
    

    /**
     * EnumOption 的构造方法
     * @param String $value 选项选中后传给后端的值
     * @param String $title 选项展示的内容
     */
    public EnumOption(String value, String title) {
    	this(value, title, false);
    }

    /**
     * EnumOption 的构造方法
     * @param String $value 选项选中后传给后端的值
     * @param String $title 选项展示的内容
     * @param boolean disabled 是否禁用该选项，默认为false，即可以选中
     */
    public EnumOption(String value, String title, boolean disabled) {
        this.value = value;
        this.title = title;
        this.disabled = disabled;
    }
}
