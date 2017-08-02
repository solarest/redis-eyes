import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;

/**
 * Created by JinJian on 17-5-9.
 */
public class JedisTest {

    public static void main(String[] args) {
        Jedis client = new Jedis("localhost", 6379);
        ScanParams params = new ScanParams();
        params.count(100);
        params.match("*/docid");
        System.out.println(client.scan("0", params).getResult());
    }
}
