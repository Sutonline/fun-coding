package cn.kevin.redis;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yongkang.zhang
 * created at 14/08/2018
 */
@Component
public class RedisComponent implements InitializingBean {

    @Autowired
    private RedisConfiguration redisConfiguration;

    private JedisPool jedisPool;

    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool(new JedisPoolConfig(), this.redisConfiguration.getHost());
    }

    public void setValue(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
    }

    public String getData(String key) {
        return jedisPool.getResource().get(key);
    }
}
