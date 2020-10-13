package cn.edu.dgut.integration.seckill.test;

import cn.edu.dgut.integration.seckill.redis.BaseRedis;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.*;

import javax.annotation.Resource;

@SpringBootTest
public class RedisUtilTest {


    @Resource
    private BaseRedis baseRedis;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

//    @Test
    public void test(){
        baseRedis.set("test","test",200);
        baseRedis.set("test2","test2");
        redisTemplate.opsForValue().set("test3",200);
    }

    @Autowired
    private RedisConnectionFactory connectFactory;

    /**
     * 经过一番测试，RedisTemplate,就不要使用非int类型进行set，然后自增，会失败。
     * 如果超过int的考虑用StringRedisTemplate自增
     * StringRedisTemplate与RedisTemplate 数据不共通的原因是 KeySerializer 不同。
     */

//    @Test
    public void testSerializer(){

        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(connectFactory);
        template.afterPropertiesSet();

        ValueOperations<String, Object> operations = template.opsForValue();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        operations.set("StringRedisSerializer", "1");

        template.setKeySerializer(new GenericToStringSerializer<String>(String.class));
        template.setValueSerializer(new GenericToStringSerializer<String>(String.class));
        operations.set("GenericToStringSerializer", "1");


        template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        operations.set("GenericJackson2JsonRedisSerializer", "1");

        template.setKeySerializer(new Jackson2JsonRedisSerializer<String>(String.class));
        template.setValueSerializer(new Jackson2JsonRedisSerializer<String>(String.class));
        operations.set("Jackson2JsonRedisSerializer", "1");

        template.setKeySerializer(new JdkSerializationRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        operations.set("JdkSerializationRedisSerializer", "1");

        template.setKeySerializer(new FastJsonRedisSerializer<>(Object.class));
        template.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
//        template.setValueSerializer(new StringRedisSerializer());
        operations.set("FastJsonRedisSerializer", 1);
        operations.increment("FastJsonRedisSerializer",1);


    }

    @Test
    public void testTemplate(){
        stringRedisTemplate.opsForValue().set("key","1");
        stringRedisTemplate.opsForValue().increment("key",2);
        redisTemplate.opsForValue().set("key2",1);
        redisTemplate.opsForValue().increment("key2",3);
    }

    @Test
    public void testTemplate2(){
//        redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);
//        redisTemplate.opsForValue().set("key3", 1L);
//        redisTemplate.opsForValue().increment("key3",3);
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        System.out.println(redisTemplate.opsForValue().get("key") + ":" + redisTemplate.opsForValue().get("key2"));
    }

}
