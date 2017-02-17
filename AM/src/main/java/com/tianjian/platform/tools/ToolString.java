package com.tianjian.platform.tools;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import com.jfinal.log.Log;
//import com.platform.mvc.user.User;

/**
 * 瀛楃涓插鐞嗗父鐢ㄦ柟娉�
 */
public abstract class ToolString {

	private static final Log log = Log.getLog(ToolString.class);

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤闈炶礋鏁存暟锛堟鏁存暟 + 0锛�
	 */
	public final static String regExp_integer_1 = "^\\d+$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤姝ｆ暣鏁�
	 */
	public final static String regExp_integer_2 = "^[0-9]*[1-9][0-9]*$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤闈炴鏁存暟锛堣礋鏁存暟  + 0锛�
	 */
	public final static String regExp_integer_3 = "^((-\\d+) ?(0+))$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤璐熸暣鏁�
	 */
	public final static String regExp_integer_4 = "^-[0-9]*[1-9][0-9]*$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤鏁存暟
	 */
	public final static String regExp_integer_5 = "^-?\\d+$";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤闈炶礋娴偣鏁帮紙姝ｆ诞鐐规暟 + 0锛�
	 */
	public final static String regExp_float_1 = "^\\d+(\\.\\d+)?$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤姝ｆ诞鐐规暟
	 */
	public final static String regExp_float_2 = "^(([0-9]+\\.[0-9]*[1-9][0-9]*) ?([0-9]*[1-9][0-9]*\\.[0-9]+) ?([0-9]*[1-9][0-9]*))$"; 
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤闈炴娴偣鏁帮紙璐熸诞鐐规暟 + 0锛�
	 */
	public final static String regExp_float_3 = "^((-\\d+(\\.\\d+)?) ?(0+(\\.0+)?))$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤璐熸诞鐐规暟
	 */
	public final static String regExp_float_4 = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*) ?([0-9]*[1-9][0-9]*\\.[0-9]+) ?([0-9]*[1-9][0-9]*)))$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤娴偣鏁�
	 */
	public final static String regExp_float_5 = "^(-?\\d+)(\\.\\d+)?$";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤鐢�26涓嫳鏂囧瓧姣嶇粍鎴愮殑瀛楃涓�
	 */
	public final static String regExp_letter_1 = "^[A-Za-z]+$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤鐢�26涓嫳鏂囧瓧姣嶇殑澶у啓缁勬垚鐨勫瓧绗︿覆
	 */
	public final static String regExp_letter_2 = "^[A-Z]+$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤鐢�26涓嫳鏂囧瓧姣嶇殑灏忓啓缁勬垚鐨勫瓧绗︿覆
	 */
	public final static String regExp_letter_3 = "^[a-z]+$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤鐢辨暟瀛楀拰26涓嫳鏂囧瓧姣嶇粍鎴愮殑瀛楃涓�
	 */
	public final static String regExp_letter_4 = "^[A-Za-z0-9]+$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤鐢辨暟瀛椼��26涓嫳鏂囧瓧姣嶆垨鑰呬笅鍒掔嚎缁勬垚鐨勫瓧绗︿覆
	 */
	public final static String regExp_letter_5 = "^\\w+$";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤email鍦板潃
	 */
	public final static String regExp_email = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤url
	 */
	public final static String regExp_url_1 = "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤url
	 */
	public final static String regExp_url_2 = "[a-zA-z]+://[^\\s]*";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤涓枃瀛楃
	 */
	public final static String regExp_chinese_1 = "[\\u4e00-\\u9fa5]";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤鍙屽瓧鑺傚瓧绗�(鍖呮嫭姹夊瓧鍦ㄥ唴)
	 */
	public final static String regExp_chinese_2 = "[^\\x00-\\xff]"; 

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤绌鸿
	 */
	public final static String regExp_line = "\\n[\\s ? ]*\\r";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤HTML鏍囪
	 */
	public final static String regExp_html_1 = "/ <(.*)>.* <\\/\\1> ? <(.*) \\/>/";
	
	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤棣栧熬绌烘牸
	 */
	public final static String regExp_startEndEmpty = "(^\\s*) ?(\\s*$)";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤甯愬彿鏄惁鍚堟硶(瀛楁瘝寮�澶达紝鍏佽5-16瀛楄妭锛屽厑璁稿瓧姣嶆暟瀛椾笅鍒掔嚎)
	 */
	public final static String regExp_accountNumber = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$"; 

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤鍥藉唴鐢佃瘽鍙风爜锛屽尮閰嶅舰寮忓 0511-4405222 鎴� 021-87888822
	 */
	public final static String regExp_telephone = "\\d{3}-\\d{8} ?\\d{4}-\\d{7}";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鑵捐QQ鍙�, 鑵捐QQ鍙蜂粠10000寮�濮�
	 */
	public final static String regExp_qq = "[1-9][0-9]{4,}";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤涓浗閭斂缂栫爜
	 */
	public final static String regExp_postbody = "[1-9]\\d{5}(?!\\d)";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鍖归厤韬唤璇�, 涓浗鐨勮韩浠借瘉涓�15浣嶆垨18浣�
	 */
	public final static String regExp_idCard = "\\d{15} ?\\d{18}";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細IP
	 */
	public final static String regExp_ip = "\\d+\\.\\d+\\.\\d+\\.\\d+";

	/**
	 * 甯哥敤姝ｅ垯琛ㄨ揪寮忥細鎵嬫満鍙�
	 */
	public final static String regExp_mobile = "^0?(13[0-9]|15[012356789]|18[01236789]|14[57])[0-9]{8}$";
		
	/**
	 * 瀛楃缂栫爜
	 */
	public final static String encoding = "UTF-8";
	
	/**
	 * 楠岃瘉瀛楃涓叉槸鍚﹀尮閰嶆寚瀹氭鍒欒〃杈惧紡
	 * @param content
	 * @param regExp
	 * @return
	 */
	public static boolean regExpVali(String content, String regExp){
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(content);
		return matcher.matches();
	}

	/**
	 * double绮惧害璋冩暣
	 * @param doubleValue 闇�瑕佽皟鏁寸殑鍊�123.454
	 * @param format 鐩爣鏍峰紡".##"
	 * @return
	 */
	public static String decimalFormat(double doubleValue, String format){
		DecimalFormat myFormatter = new DecimalFormat(format);  
		String formatValue = myFormatter.format(doubleValue);
		return formatValue;
	}
	
	/**
	 * Url Base64缂栫爜
	 * 
	 * @param data  寰呯紪鐮佹暟鎹�
	 * @return String 缂栫爜鏁版嵁
	 * @throws Exception
	 */
	public static String encode(String data) {
		String str = null;
		try {
			// 鎵ц缂栫爜
			byte[] b = Base64.encodeBase64URLSafe(data.getBytes(encoding));
			str = new String(b, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * Url Base64瑙ｇ爜
	 * 
	 * @param data
	 *            寰呰В鐮佹暟鎹�
	 * @return String 瑙ｇ爜鏁版嵁
	 * @throws Exception
	 */
	public static String decode(String data) {
		String str = null;
		try {
			// 鎵ц瑙ｇ爜
			byte[] b = Base64.decodeBase64(data.getBytes(encoding));
			str = new String(b, encoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * URL缂栫爜锛坲tf-8锛�
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncode(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 鏍规嵁鍐呭绫诲瀷鍒ゆ柇鏂囦欢鎵╁睍鍚�
	 * @param contentType 鍐呭绫诲瀷
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType)){
			fileExt = ".jpg";
			
		} else if ("audio/mpeg".equals(contentType)){
			fileExt = ".mp3";
			
		} else if ("audio/amr".equals(contentType)){
			fileExt = ".amr";
			
		} else if ("video/mp4".equals(contentType)){
			fileExt = ".mp4";
			
		} else if ("video/mpeg4".equals(contentType)){
			fileExt = ".mp4";
		}
		
		return fileExt;
	}

	/**
	 * 鑾峰彇bean鍚嶇О
	 * @param bean
	 * @return
	 */
	public static String beanName(Object bean) {
		String fullClassName = bean.getClass().getName();
		String classNameTemp = fullClassName.substring(fullClassName.lastIndexOf(".") + 1, fullClassName.length());
		return classNameTemp.substring(0, 1) + classNameTemp.substring(1);
	}
	
	public final static Pattern referer_pattern = Pattern.compile("@([^@^\\s^:]{1,})([\\s\\:\\,\\;]{0,1})");//@.+?[\\s:]
	 
	/**
	 * 澶勭悊鎻愬埌鏌愪汉 @xxxx
	 * @param msg  浼犲叆鐨勬枃鏈唴瀹�
	 * @param referers 浼犲嚭琚紩鐢ㄥ埌鐨勪細鍛樺悕鍗�
	 * @return 杩斿洖甯︽湁閾炬帴鐨勬枃鏈唴瀹�
	 */
	public static String userLinks(String contents, List<String> userReferers) {
	    StringBuilder html = new StringBuilder();
	    int lastIdx = 0;
	    Matcher matchr = referer_pattern.matcher(contents);
	    while (matchr.find()) {
	        String origion_str = matchr.group();
	        //System.out.println("-->"+origion_str);
	        String userName = origion_str.substring(1, origion_str.length()).trim();
	        //char ch = str.charAt(str.length()-1);
	        //if(ch == ':' || ch == ',' || ch == ';')
	        //  str = str.substring(0, str.length()-1);
	        //System.out.println(str);
	        html.append(contents.substring(lastIdx, matchr.start()));
	         
//	        User user = User.cacheGetByUserName(userName);
//	        if(user != null){
//	            html.append("<a href='http://www.xx.com/"+user.getStr("username")+"' class='referer' target='_blank'>@");
//	            html.append(userName.trim());
//	            html.append("</a> ");
//	            if(userReferers != null && !userReferers.contains(user.getPKValue())){
//	            	userReferers.add(user.getPKValue());
//	            }
//	        } else {
//	        	log.error("鐢ㄦ埛涓嶅瓨鍦� userName = " + userName);
//	            html.append(origion_str);
//	        }
	        lastIdx = matchr.end();
	        //if(ch == ':' || ch == ',' || ch == ';')
	        //  html.append(ch);
	    }
	    html.append(contents.substring(lastIdx));
	    return html.toString();
	}

	/**
	 * 棣栧瓧姣嶈浆灏忓啓
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
        if(Character.isLowerCase(s.charAt(0))){
        	return s;
        } else {
        	return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
	
	/**
	 * 棣栧瓧姣嶈浆澶у啓
	 * @param s
	 * @return
	 */
    public static String toUpperCaseFirstOne(String s) {
        if(Character.isUpperCase(s.charAt(0))){
        	return s;
        } else {
        	return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
    
}
