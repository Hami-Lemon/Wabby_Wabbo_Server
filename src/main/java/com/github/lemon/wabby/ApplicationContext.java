package com.github.lemon.wabby;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <p> 创建时间 2021/6/10 </p>
 *
 * @author Hami Lemon
 * @version v1.0
 */
@Configuration
public class ApplicationContext {
    @Bean(name = "tipsCache")
    public Cache<Integer, Integer> tipsCache() {
        return Caffeine.newBuilder()
                .build(key -> 0);
    }

    @Bean("commentsCache")
    public Cache<Integer, Integer> commentsCache() {
        return Caffeine.newBuilder()
                .build(key -> 0);

    }
}
