package com.github.lemon.wabby.util;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

/**
 * <p> 创建时间 2021/6/5 </p>
 *
 * @author Hami Lemon
 * @version v1.0
 */
public class TimeFormatUtilTest {
    private LocalDateTime time =
            LocalDateTime.of(2020, 7, 27,
                    12, 22, 12, 102);

    @Test
    public void format() {
        String r = TimeFormatUtil.format(time);
        assertThat(r).as("默认格式")
                .isNotEmpty()
                .isNotBlank()
                .isEqualTo("2020-07-27 12:22:12");
    }

    @Test
    public void testFormat() {
        String r = TimeFormatUtil.format(time, "yyyy-MM-dd HH:mm");
        assertThat(r).as("自定义格式：yyyy-MM-dd HH:mm")
                .isNotEmpty()
                .isNotBlank()
                .isEqualTo("2020-07-27 12:22");
    }
}