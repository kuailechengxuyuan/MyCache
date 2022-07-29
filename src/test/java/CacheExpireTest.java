import com.zxqr.cache.bs.CacheBs;
import com.zxqr.cache.core.Cache;
import com.zxqr.cache.core.ICache;
import com.zxqr.cache.expire.CacheExpire;
import org.junit.Assert;


import java.util.concurrent.TimeUnit;

public class CacheExpireTest {
    public static void main(String[] args) throws InterruptedException {
        ICache<String, String> cache = CacheBs.<String,String>newInstance()
                .size(3)
                .build();

        cache.put("1", "1");
        cache.put("2", "2");

        cache.expire("1", 10);
        long s;long s1;
        System.out.println(s=System.currentTimeMillis());
        System.out.println(cache.size());
        //todo 这里一但等待时间太短，就会来不及清理缓存
//        TimeUnit.MILLISECONDS.sleep(111);
        while (true){
            System.out.print(cache.size());
            if(cache.size()==1){
                System.out.println(s1=System.currentTimeMillis());

                break;
            }
        }
        System.out.println(cache.size());
        System.out.println(s1-s);
        System.out.println(cache.keySet());
    }
}
