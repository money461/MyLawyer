package com.tz.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;



public class ValidateUtil {
	
	public static boolean phoneValidate(String phone){	
		if(StringUtils.isNotBlank(phone)){
			String pattern="^(1)\\d{10}$";
		      // 创建 Pattern 对象
		      Pattern r = Pattern.compile(pattern);
		      // 现在创建 matcher 对象
		     Matcher m = r.matcher(phone);
		     if(m.matches()){
					return true;
				}
		   }
		return false;
		}
	public static boolean passWordValidate(String passWord){	
		if(StringUtils.isNotBlank(passWord)){
			String pattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,19}$";
		      // 创建 Pattern 对象
		      Pattern r = Pattern.compile(pattern);
		      // 现在创建 matcher 对象
		     Matcher m = r.matcher(passWord);
		     if(m.matches()){
					return true;
				}
		   }
		return false;
		}
	public static void main(String[] args) {
		
		System.out.println(phoneValidate("13333333333"));
	}
}
