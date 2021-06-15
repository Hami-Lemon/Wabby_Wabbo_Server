package com.github.lemon.wabby.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间格式化的工具类,用于格式化{@link LocalDateTime}(仅是对{@link DateTimeFormatter}的简单封装)
 *
 * <p> 创建时间 2021/6/5 </p>
 *
 * @author Hami Lemon
 * @version v1.0
 */
public class TimeFormatUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(TimeFormatUtil.class);

    private TimeFormatUtil() {
    }

    /**
     * 格式化时间，格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param time 时间 {@link LocalDateTime}类型
     * @return 格式化后的时间
     */
    public static String format(LocalDateTime time) {
        return format(time, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 按自定义的格式，格式化时间
     *
     * @param time   时间 {@link LocalDateTime}类型
     * @param format 格式
     * @return 格式化后的时间
     */
    public static String format(LocalDateTime time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LOGGER.debug("format {} with pattern {}", time, format);
        return formatter.format(time);
    }

}
