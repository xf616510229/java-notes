import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisHashCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring data Redis Test
 * <p>
 *
 * @author Feathers
 * @date 2017-11-16 14:43
 */
@SuppressWarnings("all")
@ContextConfiguration(locations = {"classpath:spring/redis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringRedisTemplateTest extends TestCase {

    @Autowired
    private RedisTemplate redisTemplate;

    @Before
    public void initRedisTemplate() {
        // 存储键值默认会出现\xac\xed\x00\x05t\x00乱码,这是因为模板类在操作redis时默认使用JdkSerializationRedisSerializer来进行序列化
        // 解决办法
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
    }

    @Test
    public void key() throws Exception {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("first", "hello world");
        Object o = valueOperations.get("first");
        System.out.println(o);
        Assert.assertEquals("hello world", o);
    }

    @Test
    public void hash() throws Exception {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "tom");
        map.put("age", "26");
        hash.putAll("tomMap", map);

        System.out.println(hash.entries("tomMap"));
    }

    @Test
    public void list() throws Exception {
        //添加 一个 list 列表
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush("tomList", "tom");
        list.rightPush("tomList", "26");

        System.out.println(list.range("tomList", 0, 1));
    }

    @Test
    public void set() throws Exception {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add("tomSet", "tom");
        set.add("tomSet", "23");
        set.add("tomSet", "173cm");

        System.out.println(set.members("tomSet"));
    }

    @Test
    public void orderSet() throws Exception {
        // 有序set集合
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add("tomZset", "tom", 0);
        zset.add("tomZset", "23", 1);
        zset.add("tomZset", "173cm", 2);
        //输出有序 set 集合
        System.out.println(zset.rangeByScore("tomZset", 0, 2));
    }

    @Test
    public void executeKey() {

        Boolean setResult = (Boolean) redisTemplate.execute(new RedisCallback() {
            @Nullable
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.set("exeKey".getBytes(), "exeValue".getBytes());
            }
        });
        System.out.println(setResult);

        Object getResult = redisTemplate.execute(new RedisCallback<String>() {
            @Nullable
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return new String(redisConnection.get("exeKey".getBytes()));
            }
        });
        System.out.println(getResult);
    }

    @Test
    public void executeHash() {
        redisTemplate.execute(new RedisCallback() {
            @Nullable
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisHashCommands redisHashCommands = redisConnection.hashCommands();
                redisHashCommands.hSet("exeHash".getBytes(), "name".getBytes(), "tom".getBytes());
                redisHashCommands.hSet("exeHash".getBytes(), "age".getBytes(), "12".getBytes());
                return null;
            }
        });
    }
}


/*
setNX 和 set的区别
setNX是仅当key不存在的时候,设置值
set 无论key是否存在,都进行设置
 */