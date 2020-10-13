package cn.edu.dgut.integration.seckill.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() throws IOException {
        InputStream in = new ClassPathResource("redisson-single.yml").getInputStream();
        return Redisson.create(Config.fromYAML(in));

    }

}
