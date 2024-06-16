package com.guangze.hermes.common.exception.asserts;

import com.guangze.hermes.common.constants.RegExceptionConstant;
import com.guangze.hermes.common.exception.BaseException;
import com.guangze.hermes.common.exception.RespInfo;
import com.guangze.hermes.common.result.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @description: 异常断言接口
 * @author: daiguangze
 * @create: 2024-06-17 01:17
 **/
public interface BaseExceptionAssert extends RespInfo {

    /**
     * 构造异常对象, 由具体的异常枚举类实现
     *
     * @param args 额外参数
     * @return BaseException
     */
    BaseException newException(Object... args);

    /**
     * 构建新的Result对象,与构建异常的区别为构建result会由controller正常返回,但是异常会被捕获后重新构造成result返回
     *
     * @param args 额外参数
     * @return Result
     */
    default Result newResult(Object... args) {
        String message = MessageFormat.format(this.getMessage(), args);
        return Result.error(this.getCode(), message);
    }

    /**
     * 不做任何断言,直接抛出异常
     *
     * @param args 额外参数
     */
    default void throwException(Object... args) {
        throw newException(args);
    }

    /**
     * 断言target非null , 若target为null,则抛出异常
     * @param target 目标对象
     * @param args 额外参数
     */
    default void assertNotNull(Object target, Object... args) {
        if (target == null) {
            throw newException(args);
        }
    }

    /**
     * 断言target中每一个对象都非null
     * @param target 目标对象序列
     */
    default void assertAnyNotNull(Object... target) {
        Object firstNotNonNull = ObjectUtils.firstNonNull(target);
        if (firstNotNonNull == null){
            throw newException();
        }
    }

    /**
     * 断言target为null。若target非null，则抛出异常
     *
     * @param target 目标对象
     * @param args   其他参数
     */
    default void assertNull(Object target, Object... args) {
        if (target != null) {
            throw newException(args);
        }
    }

    /**
     * 断言target字符串不为空。若target为空，则抛出异常
     *
     * @param target 目标字符串
     * @param args   其他参数
     */
    default void assertNotBlank(String target, Object... args) {
        if (StringUtils.isBlank(target)) {
            throw newException(args);
        }
    }

    /**
     * 断言target字符串匹配正则表达式。若target不匹配，则抛出异常
     *
     * @param target 目标字符串
     * @param regex  正则表达式
     */
    default void assertMatchRegex(String target, String regex, String argName) {
        if (target == null || !target.matches(regex)) {
            throw newException(target, argName);
        }
    }

    default void assertMatchRegex(String target, String regex, String argName, String regexDesc) {
        if (target == null || !target.matches(regex)) {
            throw newException(target, argName, regexDesc);
        }
    }

    default void assertByRegex(String target, String matchRegex, String forbidRegex, String argName, String regexDesc) {
        if (target == null || !target.matches(matchRegex) || target.matches(forbidRegex)) {
            throw newException(target, argName, regexDesc);
        }
    }

//    default BaseException newException(Throwable t, Object... args) {
//        String message = MessageFormat.format(this.getMessage(), args);
//        return new BaseException(this, message, t);
//    }

    /**
     * 断言target为true。若target为false，则抛出异常
     *
     * @param target 目标布尔值
     * @param args   其他参数
     */
    default void assertTrue(boolean target, Object... args) {
        if (!target) {
            throw newException(args);
        }
    }

    /**
     * 断言target为false。若target为true，则抛出异常
     *
     * @param target 目标布尔值
     * @param args   其他参数
     */
    default void assertFalse(boolean target, Object... args) {
        if (target) {
            throw newException(args);
        }
    }

    /**
     * 断言target是非空集合。若target为空集合，则抛出异常
     *
     * @param target 断言目标
     * @param args   其他参数
     */
    default void assertNotEmpty(Collection target, Object... args) {
        if (CollectionUtils.isEmpty(target)) {
            throw newException(args);
        }
    }

    /**
     * 断言target是空集合。若target为非空集合，则抛出异常
     *
     * @param target 断言目标
     * @param args   其他参数
     */
    default void assertEmpty(Collection target, Object... args) {
        if (CollectionUtils.isNotEmpty(target)) {
            throw newException(args);
        }
    }

    /**
     * 断言target为非负数字。若target非数字格式，则抛出异常
     *
     * @param target 断言的目标对象
     * @param args   其他参数
     */
    default void assertPositiveNumber(String target, Object... args) {
        String regex = RegExceptionConstant.POSITIVE_NUMBER_REGEX;
        if (target == null || !target.trim().matches(regex)) {
            throw newException(args);
        }
    }

    /**
     * 断言source和target不相同。若相同，则抛出异常
     *
     * @param source 原始对象
     * @param target 目标
     * @param args   其他参数
     */
    default void assertNotEqual(Object source, Object target, Object... args) {
        if (Objects.equals(source, target)) {
            throw newException(args);
        }
    }

//    /**
//     * 断言整型的source和target是否相同，如果不同则抛出异常
//     *
//     * @param target 断言的目标对象
//     * @param args 其他参数
//     */
//    default void assertEqual(int source, int target, Object... args) {
//        if (source != target) {
//            throw newException(args);
//        }
//    }
//
//    /**
//     * 断言long型的source和target是否相同，如果不同则抛出异常
//     *
//     * @param target 断言的目标对象
//     * @param args 其他参数
//     */
//    default void assertEqual(long source, long target, Object... args) {
//        if (source != target) {
//            throw newException(args);
//        }
//    }

    /**
     * 断言source和target相同。若不相同，则抛出异常
     *
     * @param source 原始对象
     * @param target 目标
     * @param args   其他参数
     */
    default void assertEqual(Object source, Object target, Object... args) {
        if (!Objects.equals(source, target)) {
            throw newException(args);
        }
    }

    /**
     * 断言target大于0。若target小于等于0则抛出异常
     *
     * @param target 断言目标对象
     * @param args   其他参数
     */
    default void assertGreaterZero(Long target, Object... args) {
        if (target == null || target <= 0) {
            throw newException(args);
        }
    }

    default void assertLessThan(Long source, Long target, Object... args) {
        if (source == null || target == null || source >= target) {
            throw newException(args);
        }
    }

    default void assertLessThan(Double source, Double target, Object... args) {
        if (source == null || target == null || source >= target) {
            throw newException(args);
        }
    }

    default void assertLessThan(Integer source, Integer target, Object... args) {
        if (source == null || target == null || source >= target) {
            throw newException(args);
        }
    }

    default void assertGreaterEqZero(Long target, Object... args) {
        if (target == null || target < 0) {
            throw newException(args);
        }
    }

    default void assertGreaterEqZero(Integer target, Object... args) {
        if (target == null || target < 0) {
            throw newException(args);
        }
    }

    default void assertGreaterZero(Integer target, Object... args) {
        if (target == null || target <= 0) {
            throw newException(args);
        }
    }

    default void assertInZero2One(Float target, Object... args) {
        if (target == null || target <= 0 || target > 1) {
            throw newException(args);
        }
    }

    /**
     * 断言target属于expected其中之一。若不满足，则抛出异常
     *
     * @param target   断言目标对象
     * @param expected 期望的对象
     */
    default void assertIn(Object target, Object... expected) {
        Set set = new HashSet();
        for (Object obj : expected) {
            if (obj instanceof Set) {
                set.addAll((Set) obj);
            } else {
                set.add(obj);
            }
        }

        if (!set.contains(target)) {
            throw newException(target, set);
        }
    }

    /**
     * 断言target以prefix为前缀。若不满足，则抛出异常
     *
     * @param target 目标对象
     * @param prefix 前缀
     */
    default void assertPrefix(String target, String prefix) {
        if (target == null || !target.startsWith(prefix)) {
            throw newException(target, prefix);
        }
    }

    /**
     * 断言target以prefix为前缀，suffix为后缀。若不满足，则抛出异常
     *
     * @param target 目标对象
     * @param prefix 前缀
     * @param suffix 后缀
     * @param args   其他参数
     */
    default void assertPrefixSuffix(String target, String prefix, String suffix, Object... args) {
        if (target == null || !target.startsWith(prefix) || !target.endsWith(suffix)) {
            throw newException(args);
        }
    }

    /**
     * 断言target可成功转成Integer，若不满足，则抛出异常
     *
     * @param target 断言目标对象
     * @param args   其他参数
     */
    default void assertInteger(String target, Object... args) {
        try {
            Integer.valueOf(target);
        } catch (Exception e) {
            throw newException(args);
        }
    }

    /**
     * 断言target可成功转成Long，若不满足，则抛出异常
     *
     * @param target 断言目标对象
     * @param args   其他参数
     */
    default void assertLong(String target, Object... args) {
        try {
            Long.valueOf(target);
        } catch (Exception e) {
            throw newException(args);
        }
    }

    /**
     * 断言target可成功转成Float，若不满足，则抛出异常
     *
     * @param target 断言目标对象
     * @param args   其他参数
     */
    default void assertFloat(String target, Object... args) {
        try {
            Float.valueOf(target);
        } catch (Exception e) {
            throw newException(args);
        }
    }

    /**
     * 断言target可成功转成Double，若不满足，则抛出异常
     *
     * @param target 断言目标对象
     * @param args   其他参数
     */
    default void assertDouble(String target, Object... args) {
        try {
            Double.valueOf(target);
        } catch (Exception e) {
            throw newException(args);
        }
    }

    /**
     * 断言 Float 对象是否在 （begin， end]范围内
     *
     * @param target 断言目标对象
     * @param begin  下限值（开）
     * @param end    上限值（闭）
     */
    default void assertFloatInRange(Float target, Float begin, Float end, Object... args) {
        if (target == null || target <= begin || target > end) {
            throw newException(args);
        }
    }

    /**
     * 断言target可成功转成Byte，若不满足，则抛出异常
     *
     * @param target 断言目标对象
     * @param args   其他参数
     */
    default void assertByte(String target, Object... args) {
        try {
            Byte.valueOf(target);
        } catch (Exception e) {
            throw newException(args);
        }
    }

    /**
     * 断言target是否不属于notExpected, 如果属于，则抛出异常
     *
     * @param target      断言目标对象
     * @param notExpected 不期望的对象
     * @param args        其他参数
     */
    default void assertNotIn(String target, Collection<String> notExpected, Object... args) {
        if (notExpected.contains(target)) {
            throw newException(args);
        }
    }

    /**
     * 断言target不属于expected其中之一。若不满足，则抛出异常
     *
     * @param target      目标对象
     * @param notExpected 不期望的对象
     */
    default void assertNotContain(Object target, Object... notExpected) {
        Set set = new HashSet();
        for (Object obj : notExpected) {
            if (obj instanceof Set) {
                set.addAll((Set) obj);
            } else {
                set.add(obj);
            }
        }

        if (set.contains(target)) {
            throw newException(target, set);
        }
    }

    default void assertRequestSucceed(Result result, Object... args) {
        if (result == null || !result.isSuccess()) {
            throw newException(args);
        }
    }

}
