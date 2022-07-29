import com.zxqr.cache.bs.CacheBs;
import com.zxqr.cache.core.ICache;

public class CacheBsTest {

    public static void main(String[] args) {
        ICache<String, String> cache = CacheBs.<String,String>newInstance()
                .size(2)
                .build();
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        System.out.println(cache.keySet());
    }
}
