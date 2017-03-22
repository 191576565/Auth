package com.tianjian.platform.tools;

public class ToolYeqc {
	/**
	    * Html转换为TextArea文本
	    * @return
	    */
	    public static String HtmlToText(String str) {
	        if (str == null) {
	            return "";
	        }else if (str.length() == 0) {
	            return "";
	        }
	        str = str.replaceAll("<br />", "&#13;&#10;");
	        str = str.replaceAll("<br />", "&#13;&#10;");    
	        return str;
	    }

	    /**
	    * TextArea文本转换为Html:写入数据库时使用
	    */
	    public static String Text2Html(String str) {
	        if (str == null) {
	            return "";
	        }else if (str.length() == 0) {
	            return "";
	        }
	        str = str.replaceAll("\n", "<br />");
	        str = str.replaceAll("\r", "<br />");
	        str = str.replaceAll("&#13;&#10;", "<br />");
	        return str;
	    }
}
