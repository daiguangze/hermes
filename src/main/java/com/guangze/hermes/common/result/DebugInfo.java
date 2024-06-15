package com.guangze.hermes.common.result;

import com.google.common.base.Throwables;
import com.guangze.hermes.common.utils.DateUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * @program: hermes
 * @description: 调试信息
 * @author: daiguangze
 * @create: 2024-06-16 05:36
 **/
public class DebugInfo {

    /**
     * 请求上下文
     */
    private final String context;

    /**
     * 异常堆栈
     */
    private final String stackTrace;

    public DebugInfo(String context, Throwable throwable) {
        this.context = context;
        this.stackTrace = Throwables.getStackTraceAsString(throwable);
    }


    /**
     * 获取异常堆栈信息
     */
    private String getStackTraceAsString(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    @Override
    public String toString() {
        String pattern =
                "[DEBUG] %s\n" +
                        "[DEBUG] context:\n" +
                        "%s\n" +
                        "[DEBUG] stackTrace:\n" +
                        "%s\n";
        String date = DateUtils.dateFormatToMillisecond(new Date());
        return String.format(pattern, date, context, stackTrace);
    }
}
