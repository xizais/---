package com.example.teamassistantbackend.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * 空字符串
     */
    private static final String NULLSTR = "";

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';

    /**
     * 获取参数不为空值
     *
     * @param value defaultValue 要判断的value
     * @return value 返回值
     */
    public static <T> T nvl(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * 比较2个字符，该字符通过分隔符组合，次序可能不一样，但只要数量和分割后的内容一样就表示一样
     *
     * @param str1
     * @param str2
     * @param separator
     * @return
     */
    public static boolean compareTwoStringbySeparator(String str1, String str2, String separator) {
        boolean result = false;
        boolean bSame = false;
        //根据分隔符拆分
        String[] splitArr1 = str1.split(separator);
        String[] splitArr2 = str2.split(separator);
        //遍历比较，所有相同，才能返回true
        if (splitArr1.length == splitArr2.length) {
            for (int i = 0; i < splitArr1.length; i++) {
                String strItm1 = splitArr1[i];
                bSame = false;
                for (int i1 = 0; i1 < splitArr2.length; i1++) {
                    String strItm2 = splitArr2[i];
                    if (StringUtils.equalsAnyIgnoreCase(strItm1, strItm2)) {
                        bSame = true;
                        break;
                    }
                }
                if (!bSame) {
                    break;
                }
            }
        }
        if (bSame) {
            result = true;
        }
        return result;
    }

    /**
     * 判断字符串中是否包含中文
     *
     * @param str 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        return m.find();
    }


    /**
     * 字符串是否包含中文
     *
     * @param str 待校验字符串
     * @return true 包含中文字符 false 不包含中文字符
     * @throws
     */
    public static boolean isContainChinesePlus(String str) {

        if (StringUtils.isEmpty(str)) {
            return true;
        }
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * * 判断一个Collection是否为空， 包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * 判断一个Collection是否非空，包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     *                * @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * * 判断一个对象数组是否非空
     *
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * * 判断一个对象是否是数组类型（Java基本型别的数组）
     *
     * @param object 对象
     * @return true：是数组 false：不是数组
     */
    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * 去空格
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @return 结果
     */
    public static String substring(final String str, int start) {
        if (str == null) {
            return NULLSTR;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @param end   结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return NULLSTR;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return NULLSTR;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params   参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... params) {
        if (isEmpty(params) || isEmpty(template)) {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    /**
     * 功能描述:
     * 〈用占位符格式化字符串：
     * 例子：
     * 调用 StringUtils.formatOccupy(“ 我是{0}，我是{0}的好朋友”,"张三")
     * 输入：“ 我是{0}，我是{0}的好朋友”
     * 输出：“ 我是张三，我是张三的好朋友”
     * 〉
     *
     * @param template: 占位符需要从0开始
     * @param params:
     * @Author by dfb
     * @Date 2020-09-01
     * @return: java.lang.String
     */
    public static String formatOccupy(String template, Object... params) {
        if (isEmpty(params) || isEmpty(template)) {
            return template;
        }

        String tempStr = template;

        for (int argIndex = 0; argIndex < params.length; ++argIndex) {
            tempStr = tempStr.replaceAll("\\{" + Convert.toStr(argIndex) + "\\}", StrUtil.utf8Str(params[argIndex]));
        }
        return tempStr;
    }

    /**
     * 下划线转驼峰命名
     */
    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // 前置字符是否大写
        boolean preCharIsUpperCase = true;
        // 当前字符是否大写
        boolean curreCharIsUpperCase = true;
        // 下一字符是否大写
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1)) {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                sb.append(SEPARATOR);
            } else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * 驼峰式命名法 例如：user_name->userName
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    public static String getRandomPassword(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()_+-=";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String getMOAStr(String str, String cMask1, String cMask2) {
        String result = str;
        Integer ileft = str.indexOf(cMask1);
        Integer iRight = str.lastIndexOf(cMask2);
        result = str.substring(ileft + 1, iRight);
        return result;
    }

    public static String converUUIDtoGuid(String uuid) {
        if (StringUtils.isBlank(uuid)) {
            return "{" + UUID.randomUUID().toString() + "}";
        }
        if (!StringUtils.equalsGuid(uuid.substring(0, 1), "{")) {
            uuid = "{" + uuid;
        }
        if (!StringUtils.equalsGuid(uuid.substring(uuid.length() - 1, uuid.length()), "}")) {
            uuid = uuid + "}";
        }
        return uuid;
    }

    public static String converGuidtoUUID(String guid) {
        if (StringUtils.isBlank(guid)) {
            return UUID.randomUUID().toString();
        }
        if (StringUtils.equalsGuid(guid.substring(0, 1), "{")) {
            guid = substring(guid, 1, guid.length());
        }
        if (StringUtils.equalsGuid(guid.substring(guid.length() - 1, guid.length()), "}")) {
            guid = substring(guid, 0, guid.length() - 1);
        }
        return guid;
    }


    public static Boolean equalsGuid(String guid1, String guid2) {
        Boolean result = false;
        if (StringUtils.isBlank(guid1) || StringUtils.isBlank(guid2)) {
            return result;
        }
        if (StringUtils.equalsIgnoreCase(guid1, guid2)) {
            result = true;
        } else {
            String g1 = null;
            String g2 = null;
            if (guid1 != null) {
                if (!StringUtils.equals(guid1.substring(0, 1), "{"))
                    g1 = "{" + guid1 + "}";
                else
                    g1 = guid1;
            }
            if (guid2 != null) {
                if (!StringUtils.equals(guid2.substring(0, 1), "{"))
                    g2 = "{" + guid2 + "}";
                else
                    g2 = guid2;
            }
            if (StringUtils.equalsIgnoreCase(g1, g2))
                result = true;
        }
        return result;
    }

    /**
     * 转换guid  isIncludeBraces=true标识输出带{}的GUID isIncludeBraces=false表示输出不带{}的GUID
     *
     * @param guid
     * @param isIncludeBraces
     * @return
     */
    public static String converGuid(String guid, Boolean isIncludeBraces) {
        String result = guid;
        if (isIncludeBraces) {
            if (!StringUtils.equals(result.substring(0, 1), "{"))
                result = "{" + result;
            if (!StringUtils.equals(result.substring(result.length() - 1, result.length()), "}"))
                result = result + "}";
        } else {
            if (!StringUtils.equals(result.substring(0, 1), "{"))
                result = result.substring(1);
            if (!StringUtils.equals(result.substring(result.length() - 1, 1), "}"))
                result = result.substring(0, result.length() - 2);
        }

        return result;
    }


    /**
     * 转换guid  isIncludeBraces=true标识输出带{}的GUID isIncludeBraces=false表示输出不带{}的GUID
     *
     * @param guid
     * @param isIncludeBraces
     * @return
     */
    public static String converGuidToUpper(String guid, Boolean isIncludeBraces) {
        return converGuid(guid, isIncludeBraces).toUpperCase();
    }

    /**
     * 转换guid  isIncludeBraces=true标识输出带{}的GUID isIncludeBraces=false表示输出不带{}的GUID
     *
     * @param guid
     * @param isIncludeBraces
     * @return
     */
    public static String converGuidToLower(String guid, Boolean isIncludeBraces) {
        return converGuid(guid, isIncludeBraces).toLowerCase();
    }


    /**
     * 将字符串中的数字和字符提取出来
     *
     * @param splitStr
     * @return
     */
    public static JSONObject splitNum(String splitStr) {
        JSONObject jsonObject = new JSONObject();
        String resultStr = "";
        String OtherStr = "";
        int resultNum = -1;
        if (splitStr != null || "".equals(splitStr)) {
            //去空格
            splitStr = splitStr.trim();
            for (int i = 0; i < splitStr.length(); i++) {
                if (splitStr.charAt(i) >= 48 && splitStr.charAt(i) <= 57) {
                    resultStr += splitStr.charAt(i);
                } else {
                    OtherStr += splitStr.charAt(i);
                }
            }
            resultNum = Integer.parseInt(resultStr);
        }
        jsonObject.put("cVouchChar", OtherStr);
        jsonObject.put("cVouchNum", resultNum);
        return jsonObject;
    }

    /**
     * 将guid中的"-"和"{}"去掉
     *
     * @param splitStr
     * @return
     */
    public static String SplitGUID(String splitStr) {
        String resultStr = "";
        if (splitStr != null || "".equals(splitStr)) {
            //去空格
            splitStr = splitStr.trim();
            for (int i = 0; i < splitStr.length(); i++) {
                if (Character.isLetterOrDigit(splitStr.charAt(i))) {
                    resultStr += splitStr.charAt(i);
                }
            }
        }
        return resultStr;
    }


    /**
     * 功能描述:
     * 〈获取字符串的MD5〉
     *
     * @param str:传入的字符串
     * @Author by dfb
     * @Date 2020-10-27
     * @return: java.lang.String
     */
    public static String getMD5(String str) {
        return SecureUtil.md5(str);
    }


    public static boolean isNum(String str) {

        Boolean StrResult = str.matches("-?[0-9]+.?[0-9]*");

        //        Pattern pattern = Pattern.compile("[0-9]*");
        return StrResult;
    }


    /**
     * 功能描述:
     * 〈获取新Guid〉
     *
     * @Author by dfb
     * @Date 2021-01-14
     * @return: java.lang.String
     */
    public static String getNewUUID() {
        return UUID.randomUUID().toString();
    }


    /**
     * 功能描述:
     * 〈将BigDecimal数据格式化成金额格式的字符串〉
     *
     * @param mCLWaitCollectMoney:
     * @Author by dfb
     * @Date 2021-02-24
     * @return: java.lang.String
     */
    public static String bigDecimalFormatMoney(BigDecimal mCLWaitCollectMoney) {
        if (mCLWaitCollectMoney == null) {
            mCLWaitCollectMoney = new BigDecimal(0);
        }
        DecimalFormat df = new DecimalFormat("###,###,###,###,##0.00");
        return df.format(mCLWaitCollectMoney);
    }

    /**
     * 功能描述:
     * 〈获取uuid〉
     *
     * @Author by dfb
     * @Date 2020-10-27
     * @return: java.lang.String
     */
    public static String getNewUUId() {
        return UUID.randomUUID().toString();
    }


    /**
     * 功能描述:
     * 〈将BigDecimal数据转化成字符串，保留两位小数〉
     *
     * @param mCLWaitCollectMoney:
     * @Author by dfb
     * @Date 2021-02-24
     * @return: java.lang.String
     */
    public static String bigDecimalToString(BigDecimal mCLWaitCollectMoney) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(mCLWaitCollectMoney);
    }

    /**
     * 获取当前时间的格式转换后的字符串
     *
     * @param date       时间
     * @param dateFormat 转换后格式
     * @return
     */
    public static String GetDate(Date date, String dateFormat) {
        if (date == null) {
            date = new Date();
        }
        if (dateFormat == null) {
            dateFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    public static void splitCondition(JSONObject condition, String splitSymbol, String splitField, String conditionField) {
        splitCondition(condition, splitSymbol, splitField, conditionField, true, null, true);
    }

    /**
     * 用于分割字段后写入条件参数列表中
     *
     * @param condition      json数据集
     * @param splitSymbol    切割符号
     * @param splitField     切割字段
     * @param conditionField 返回字段
     * @param isDelete       是否删除源字段
     * @param special        特殊处理
     * @param bQuotation     是否需要引号
     * @return
     */
    public static void splitCondition(JSONObject condition, String splitSymbol, String splitField, String conditionField, Boolean isDelete, String special, Boolean bQuotation) {
        if (condition == null) {
            new JSONObject();
        }
        String splitFieldValue = condition.getString(splitField);
        if (StringUtils.isNotBlank(splitFieldValue)) {
            String[] splitArr = splitFieldValue.split(splitSymbol);
            if (splitArr.length > 1 || special != null) {
                String conditionFieldValue = "";
                for (int i = 0; i < splitArr.length; i++) {
                    String splitStr = splitArr[i];
                    //取括号外的值
                    if (StringUtils.equals(special, "getValueByBracketsOut")) {
                        splitStr = splitStr.substring(splitStr.indexOf("]") + 1);
                    }
                    //取括号内的值
                    if (StringUtils.equals(special, "getValueByBracketsInside")) {
                        splitStr = splitStr.substring(splitStr.indexOf("[") + 1, splitStr.indexOf("]"));
                    }
                    if (bQuotation) {
                        conditionFieldValue += "'" + splitStr + "'";
                    } else {
                        conditionFieldValue += splitStr;
                    }
                    if (i != splitArr.length - 1) {
                        conditionFieldValue += ",";
                    }
                }
                condition.put(conditionField, conditionFieldValue);
                if (isDelete) {
                    condition.remove(splitField);
                }

            }
        }
    }

    /**
     * 用于分割字段后写入条件参数列表中
     * “202;财务;201” --> and(conditionField like '%202%' or conditionField like '%财务%')
     *
     * @param condition      条件参数
     * @param splitSymbol    切割符号
     * @param splitField     参数字段
     * @param conditionField 返回的参数字段
     * @return
     */
    public static void splitConditionFuzzy(JSONObject condition, String splitSymbol, String splitField, String conditionField, String SQLStr, String special) {
        if (condition == null)
            return;
        String splitFieldValue = condition.getString(splitField);
        if (splitFieldValue != null) {
            String[] splitArr = splitFieldValue.split(splitSymbol);
            if (splitArr.length > 1) {
                String conditionFieldValue = "";
                for (int i = 0; i < splitArr.length; i++) {
                    String splitStr = splitArr[i];
                    //取括号外的值
                    if (StringUtils.equals(special, "getValueByBracketsOut")) {
                        splitStr = splitStr.substring(splitStr.indexOf("]") + 1);
                    }
                    //取括号内的值
                    if (StringUtils.equals(special, "getValueByBracketsInside")) {
                        splitStr = splitStr.substring(splitStr.indexOf("[") + 1, splitStr.indexOf("]"));
                    }
                    conditionFieldValue += SQLStr + " like '%" + splitStr + "%'";
                    if (i != splitArr.length - 1) {
                        conditionFieldValue += " or ";
                    }
                }
                condition.put(conditionField, conditionFieldValue);
                condition.remove(splitField);
            }


        }
    }

    /**
     * 数组去重复数据
     *
     * @param list
     * @return
     * @throws Exception
     */
    public static JSONObject ListRemoveRepeat(List<String> list, boolean bCondition) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (list == null || list.size() == 0) {
            return null;
        }
        List resultList = new ArrayList();
        Set set = new HashSet();
        set.addAll(list);
        resultList.addAll(set);
        if (bCondition) {
            String resultStr = "";
            for (int i = 0; i < resultList.size(); i++) {
                resultStr += "'" + resultList.get(i) + "'";
                if (i != resultList.size() - 1) {
                    resultStr += ",";
                }
            }
            jsonObject.put("RepeatList", resultStr);
        } else {
            jsonObject.put("RepeatList", resultList);
        }
        return jsonObject;
    }


    /**
     * 获取字符串的第N次出现的位置
     *
     * @param
     * @return
     */
    public static int getCharlocation(String str, String symbol, int iCount) {
        Matcher slashMatcher = Pattern.compile(symbol).matcher(str);
        int mIdx = 0;
        while (slashMatcher.find()) {
            mIdx++;
            if (mIdx == iCount) {
                break;
            }
        }
        return slashMatcher.start();
    }


    public static int checkStrCount(String sheetNameList, String sheetName) {
        String[] split = sheetNameList.split(";");
        int count = 0;
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (StringUtils.equals(s, sheetName)) {
                count++;
            }
        }
        return count;
    }


    /**
     * 构造函数
     *
     * @param number
     * @throws NumberFormatException
     */
    public static String ChineseUpperCaser(float number) throws NumberFormatException {
        return ChineseUpperCaser(String.valueOf(number));
    }

    /**
     * 构造函数
     *
     * @param number
     * @throws NumberFormatException
     */
    public static String ChineseUpperCaser(double number) throws NumberFormatException {
        return ChineseUpperCaser(String.valueOf(number));
    }

    /**
     * 构造函数
     *
     * @param number
     * @throws NumberFormatException
     */
    public static String ChineseUpperCaser(int number) throws NumberFormatException {
        return ChineseUpperCaser(String.valueOf(number));
    }

    /**
     * 构造函数
     *
     * @param number
     * @throws NumberFormatException
     */
    public static String ChineseUpperCaser(long number) throws NumberFormatException {
        return ChineseUpperCaser(String.valueOf(number));
    }

    /**
     * 构造函数
     *
     * @param number
     * @throws NumberFormatException
     */
    public static String ChineseUpperCaser(String number) throws NumberFormatException {

        /**
         * 用于存储整数部分
         */
        String integerPart = number.split(".").length == 0 ? number : number.split(".")[0];

        /**
         * 用于存储小数部分
         */
        String floatPart = number.split(".").length == 0 ? null : number.split(".")[1];

        /**
         * 用于存储0-9大写的哈希表
         */
        final Map<String, String> ZerotoNineHt;

        /**
         * 用于存储十百千大写的哈希表
         */
        final Map<Integer, String> thHuTenHt;

        /**
         * 用于存储万亿兆大写的哈希表
         */
        final Map<Integer, String> wanYiZhaoHt;

        ZerotoNineHt = new Hashtable<String, String>();

        ZerotoNineHt.put("0", "零");
        ZerotoNineHt.put("1", "一");
        ZerotoNineHt.put("2", "二");
        ZerotoNineHt.put("3", "三");
        ZerotoNineHt.put("4", "四");
        ZerotoNineHt.put("5", "五");
        ZerotoNineHt.put("6", "六");
        ZerotoNineHt.put("7", "七");
        ZerotoNineHt.put("8", "八");
        ZerotoNineHt.put("9", "九");

        thHuTenHt = new Hashtable<Integer, String>();
        thHuTenHt.put(0, "");
        thHuTenHt.put(1, "十");
        thHuTenHt.put(2, "百");
        thHuTenHt.put(3, "千");

        wanYiZhaoHt = new Hashtable<Integer, String>();
        wanYiZhaoHt.put(0, "");
        wanYiZhaoHt.put(1, "万");
        wanYiZhaoHt.put(2, "亿");
        wanYiZhaoHt.put(3, "兆");


        String retval = "";

        if (integerPart != null) {
            retval += parseIntegerPart(integerPart, ZerotoNineHt, thHuTenHt, wanYiZhaoHt);
        }

        if (floatPart != null) {
            retval += parseFloatPart(floatPart, ZerotoNineHt);
        }

        // 辟分以给整数部分和小数部分赋值
        String[] arr = retval.split("[.]");
        if (arr.length == 2) {
            // 有小数点
            integerPart = arr[0];
            floatPart = arr[1];
        } else {
            // 无小数点
            integerPart = arr[0];
        }
        String OutStr = floatPart == null ? integerPart: integerPart + floatPart;
        return OutStr;
    }


    /**
     * 得到整数部分的汉字大写表示
     *
     * @return
     */
    private static String parseIntegerPart(String integerPart, Map<String, String> ZerotoNineHt, Map<Integer, String> thHuTenHt, Map<Integer, String> wanYiZhaoHt) {
        String retval = "";

        // 将整数部分逆序，因为需要反向读取
        String reverseIntegerPart = "";

        for (int i = integerPart.length() - 1; i > -1; i--) {
            reverseIntegerPart += integerPart.charAt(i);
        }

        // 将整数部分按四位分段
        Pattern p = Pattern.compile("\\d{4}", Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(reverseIntegerPart);
        StringBuffer sb = new StringBuffer();

        boolean result = m.find();
        while (result) {
            // 每找到四位放一个逗号
            m.appendReplacement(sb, m.group(0) + ",");
            result = m.find();
        }
        m.appendTail(sb);

        // 按逗号劈分，得到四位分组数据的数组
        String[] arr = sb.toString().split(",");

        int j;
        String str;
        for (int i = arr.length - 1; i >= 0; i--) {
            String temp = arr[i];

            // 阿拉伯数字转大写汉字加单位（千百十）
            for (j = temp.length() - 1; j >= 0; j--) {
                str = String.valueOf(temp.charAt(j));
                retval += ZerotoNineHt.get(str) + thHuTenHt.get(j);
            }

            retval = retval.replaceAll("(零)($)", "$2");// 零在末尾则去掉
            // 加单位（兆亿万）
            retval += getWanYiZhao(i, wanYiZhaoHt);
        }

        // 零替换
        retval = retval.replaceAll("(零[千百十])", "零");
        retval = retval.replaceAll("(零{2,})", "零");
        retval = retval.replaceAll("(零)($)", "$2");// 零在末尾则去掉

        return retval;
    }

    /**
     * 得到小数部分的汉字大写表示
     *
     * @return
     */
    private static String parseFloatPart(String floatPart, Map<String, String> ZerotoNineHt) {
        String retval = "点";

        for (int i = 0; i < floatPart.length(); i++) {
            String temp = String.valueOf(floatPart.charAt(i));

            retval += ZerotoNineHt.get(temp);
        }

        return retval;
    }

    private static String getWanYiZhao(int level, Map<Integer, String> wanYiZhaoHt) {
        String retval = "";

        do {
            retval += wanYiZhaoHt.get(level % 4);
            level -= 3;
        } while (level > 3);

        return retval;
    }

    public static void main(String[] args) {
        System.out.println(ChineseUpperCaser(150));
    }


}