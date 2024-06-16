package com.guangze.hermes.common.constants;

/**
 * @description: 正则表达式常量
 * @author: daiguangze
 * @create: 2024-06-17 01:11
 **/
public interface RegExceptionConstant {

    /**
     * 小写英文字母开头，后跟小写英文字母、数字、下划线
     */
    String LOWER_LETTER_START_IDENTIFIER_REGEX = "^[a-z][a-z0-9_]*$";

    /**
     * 英文字母开头，后跟英文字母、数字、下划线的字段名或表名
     */
    String LETTER_START_IDENTIFIER_REGEX = "^[a-zA-Z][a-zA-Z0-9_]*$";

    /**
     * 小写字母开头，中间可跟小写字母、数字、短横线，尾字符不能为短横线
     */
    String LOWER_LETTER_OR_NUMBER_START_IDENTIFIER_REGEX = "[a-z]([-a-z0-9]*[a-z0-9])?";

    String LOWER_LETTER_OR_NUMBER_START_IDENTIFIER_REGEX_DESC = "只能包含小写字母、数字和短横线-（首字符只能为字母，尾字符只能为字母或数字）";

    /**
     * 小写字母或数字开头，中间可跟小写字母、数字、小数点，不能以小数点结尾
     */
    String LOWER_LETTER_NUMBER_START_IDENTIFIER_REGEX = "^[a-z0-9][a-z0-9.]*[a-z0-9]$";

    String LOWER_LETTER_NUMBER_START_IDENTIFIER_REGEX_DESC = "小写字母或数字开头，中间可跟小写字母、数字、小数点，不能以小数点结尾";

    /**
     * 小写字母开头，中间可跟小写字母、数字、短横线、小数点，不能以短横线和小数点结尾
     */
    String LOWER_LETTER_START_NO_DASH_END_IDENTIFIER_REGEX = "^[a-z][a-z0-9-.]*[a-z0-9]$";

    String LOWER_LETTER_START_NO_DASH_END_IDENTIFIER_REGEX_DESC = "小写字母开头，中间可跟小写字母、数字、短横线、小数点，不能以短横线和小数点结尾";

    /**
     * 正整数
     */
    String POSITIVE_NUMBER_REGEX = "^[1-9]\\d*$";

    /**
     * 邮箱
     */
    String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 驼峰命名
     */
    String HUMP_NAMED_REGEX = "^([A-Z][a-z0-9]*)+$";

    /**
     * 变量命名
     */
    String VARIABLE_NAME_REGEX = "^[_a-zA-Z][_a-zA-Z0-9]*$";

    /**
     * 年-月-日格式
     */
    String DATE_REGEX = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";

    /**
     * 年-月-日 时:分:秒格式
     */
    String TIME_REGEX = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))) "
            + "([01]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9]";

    /**
     * 年-月时间格式
     */
    String YEAR_MONTH_REGEX = "^[1-2]\\d{3}-((0[1-9])|(1[0-2]))$";

    /**
     * 年-季度格式
     */
    String QUARTER_REGEX = "^[1-2]\\d{3}-[q|Q][1-4]*$";

    /**
     * 年格式
     */
    String YEAR_REGEX = "(?:(?:19|20)[0-9]{2})";


    /**
     * 带括号的整数表达式，主要用于获取etl_date时间表达式中的整数
     */
    String ETL_DATE_NUM_REGEX = "\\((-?\\d+)\\)";

    /**
     * SQL语句中的单个条件
     */
    String SQL_SINGLE_CONDITION_REGEX = "(\\w+)(=|>=|>|<=|<)\\S+";

    /**
     * SQL语句中连接多个条件的AND符号
     */
    String SQL_CONDITION_AND_REGEX = "(?i)\\s+and\\s+";

}
