package cn.kevin.redis;

import com.google.common.io.Files;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author yongkang.zhang
 * created at 14/08/2018
 */
@Component
public class RedisComponent implements InitializingBean {

    @Autowired
    private RedisConfiguration redisConfiguration;

    private JedisPool jedisPool;

    private String unlockScript;

    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool(new JedisPoolConfig(), this.redisConfiguration.getHost());
        File file = new File(getClass().getResource("/unlock.lua").getPath());
        unlockScript = Files.toString(file, StandardCharsets.UTF_8);
        // unlockScript = jedisPool.getResource().scriptLoad(unlockScript);
    }

    public void setValue(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
    }

    public String getData(String key) {
        return jedisPool.getResource().get(key);
    }

    public String setLock(String key, String uuid) {
        return jedisPool.getResource().set(key, uuid, "NX", "EX", 100);
    }

    public String unlock(String key, String uuid) {
        System.out.println("----->1111");
        String s = jedisPool.getResource().eval(unlockScript, Lists.newArrayList(key),
            Lists.newArrayList(uuid)).toString();
        System.out.println("----->2222");
        return s;
    }
}
