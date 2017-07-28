import redis.clients.jedis.Jedis;

/**
 * Created by JinJian on 17-5-9.
 */
public class JedisTest {

    public static void main(String[] args) {
        Jedis client = new Jedis("localhost", 6379);

    }
}
