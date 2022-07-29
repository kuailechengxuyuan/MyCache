import com.zxqr.cache.core.Cache;
import com.zxqr.cache.evict.CacheEvictFIFO;

import java.util.LinkedHashMap;

public class CacheTest {
    public static void main(String[] args) throws Exception{

        Cache<String, String> cache = new Cache<>();
        cache.evict(new CacheEvictFIFO<>());
        cache.sizeLimit(10);
        cache.map(new LinkedHashMap<String,String>()).put("key","value");
        System.out.println(cache.get("key"));
    }
}
