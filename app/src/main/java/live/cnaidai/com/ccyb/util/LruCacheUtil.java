package live.cnaidai.com.ccyb.util;

import android.graphics.drawable.Drawable;
import android.util.LruCache;

/**
 * Created by ChristLu on 16/11/7.
 */

public class LruCacheUtil {
    /**
     * 缓存图片信息
     */
    private LruCache<String,Drawable> mBitmapCache;

    private LruCache<String,Float> mLruCache ;

    public LruCacheUtil() {
        mBitmapCache = new LruCache<String,Drawable>(2*1024*1024);

        mLruCache = new LruCache<String,Float>(2*1024*1024);
    }

    /**
     * 添加进入缓存列表
     * @param key
     * @param value
     */
    public void addBitmapLruCache(String key, Drawable value) {
        mBitmapCache.put(key, value);
    }




    /**
     * 从缓存列表中拿出来
     *
     * @param key
     * @return
     */
    public Drawable getBitmapLruCache(String key) {
        return mBitmapCache.get(key);
    }



    public void addAspectRadioLruCache(String key ,Float aspectRadio){
        mLruCache.put(key,aspectRadio);
    }

    public Float getAspectLruCache(String key){
        return  mLruCache.get(key);
    }



}
