package com.tianzhi.shop520.util;

/**
 * Created by wisdomnt on 2017/5/20.
 */

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {
    public static boolean isEmpty(CharSequence str) {
        if (str == null || "".equals(str) || "null".equals(str)
                || "NULL".equals(str)) {
            return true;
        }
        return false;
    }
    /**
     * 判断是否是手机号码
     * @param inputText
     * @return
     */
    public static boolean isPhoneNum(String inputText) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }
    /**
     * 日期格式化
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 星期
     */
    public static String week() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String format = dateFm.format(date);
        return format;

    }

    /**
     *            输入的车牌
     * @return
     *
     *         车牌的正则
     */
    public static boolean isCarCodeName(String carCodeName) {
//      Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$");
        Pattern p = Pattern.compile("^[(\u4e00-\u9fa5)|(a-zA-Z)]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4,6}[a-zA-Z_0-9_\u4e00-\u9fa5]$");

        Matcher m = p.matcher(carCodeName);
        return m.matches();
    }

    /**
     * 验证身份证
     * @param IDStr
     * @return "YES" 代表合法的身份证 ,其他值代表错误信息
     * @throws ParseException
     */
    public static String IDCardValidate(String IDStr) {
        String tipInfo = "YES";// 记录错误信息
        String Ai = "";

        if(null == IDStr || IDStr.trim().isEmpty())
            return "身份证号码长度应该为15位或18位。";

        // 判断号码的长度 15位或18位
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            tipInfo = "身份证号码长度应该为15位或18位。";
            return tipInfo;
        }
        // 18位身份证前17位位数字，如果是15位的身份证则所有号码都为数字
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            tipInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return tipInfo;
        }
        // 判断出生年月是否有效
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 日期
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            tipInfo = "身份证出生日期无效。";
            return tipInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                tipInfo = "身份证生日不在有效范围。";
                return tipInfo;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            tipInfo = "身份证月份无效";
            return tipInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            tipInfo = "身份证日期无效";
            return tipInfo;
        }
        // 判断地区码是否有效
        Hashtable areacode = GetAreaCode();
        // 如果身份证前两位的地区码不在Hashtable，则地区码有误
        if (areacode.get(Ai.substring(0, 2)) == null) {
            tipInfo = "身份证地区编码错误。";
            return tipInfo;
        }
        if (isVarifyCode(Ai, IDStr) == false) {
            tipInfo = "身份证校验码无效，不是合法的身份证号码";
            return tipInfo;
        }
        return tipInfo;
    }

    /*
     * 判断第18位校验码是否正确 第18位校验码的计算方式： 1. 对前17位数字本体码加权求和 公式为：S = Sum(Ai * Wi), i =
     * 0, ... , 16 其中Ai表示第i个位置上的身份证号码数字值，Wi表示第i位置上的加权因子，其各位对应的值依次为： 7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2 2. 用11对计算结果取模 Y = mod(S, 11) 3. 根据模的值得到对应的校验码
     * 对应关系为： Y值： 0 1 2 3 4 5 6 7 8 9 10 校验码： 1 0 X 9 8 7 6 5 4 3 2
     */
    private static boolean isVarifyCode(String Ai, String IDStr) {
        String[] VarifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum = sum + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = sum % 11;
        String strVerifyCode = VarifyCode[modValue];
        Ai = Ai + strVerifyCode;
        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                return false;
            }
        }
        return true;
    }



    /**
     * 功能：判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天
     *
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
}

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 验证日期字符串是否是YYYY-MM-DD格式
     *
     * @param str
     * @return
     */
    public static boolean isDataFormat(String str) {
        boolean flag = false;
        // String
        // regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regxStr);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }
    // 身份证号码验证：end

    private String getAlpha(String str) {

        if(StringUtils.isEmpty(str))
            return "#";
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }


    /**
     * 金额转字符串
     *
     * @param amt
     *            double类型 金额
     * @param point
     *            保留小数位数
     * @return
     */
    public static String amtToStr(double amt, int point) {
        try {
            return new BigDecimal(amt).setScale(point,
                    BigDecimal.ROUND_HALF_DOWN) + "";
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }
    /**
     * 判断字符串是否全为字母
     *
     * @param str
     * @return
     */
    public static boolean isAllLetter(String str) {
        char[] charArray = str.toCharArray();
        String letter = "[a-zA-Z]+";
        for (int i = 0; i < charArray.length; i++) {
            if (!String.valueOf(charArray[i]).matches(letter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否全为数字
     *
     * @param str
     * @return
     */
    public static boolean isAllNumber(String str) {
        char[] charArray = str.toCharArray();
        String number = "[0-9]+";
        for (int i = 0; i < charArray.length; i++) {
            if (!String.valueOf(charArray[i]).matches(number)) {
                return false;
            }
        }
        return true;
    }
    /**
     * 判断字符串是密码
     *
     * @param str
     * @return
     */
    public static boolean isPwd(String str){
        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,19}$\n");
        Matcher ispwd = pattern.matcher(str);
        if (ispwd.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是手机号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNum(String mobiles) {


        Pattern p = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");

        Matcher m = p.matcher(mobiles);
        return m.matches();

    }
}