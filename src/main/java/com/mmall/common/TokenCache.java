package com.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by lll on 19/3/24.
 */
public class TokenCache {

    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX ="token_";

    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)//缓存初始化容量
            .maximumSize(10000)//缓存容量超过时Guava使用LRU算法移除
            .expireAfterAccess(12, TimeUnit.HOURS)//缓存有效期12小时
            .build(new CacheLoader<String, String>() {
                //匿名实现类
                //默认的数据加载实现:调用get时,key没有对应的值,就调用这个方法加载
                @Override
                public String load(String s) throws Exception {
                    //避免判断时null.equals出现空指针异常,使用字符串null
                    return "null";
                }
            });

    public static void setKey(String key, String value){
        localCache.put(key,value);
    }

    public static String getKey(String key){
        String value = null;
        try {
            value = localCache.get(key);
            if("null".equals(value)){
                return null;
            }
            return value;
        } catch (Exception e) {
            logger.error("localCache get error", e);
        }
        return null;
    }
}
