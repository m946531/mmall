package com.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.org.mozilla.javascript.internal.EcmaError;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zyx
 * @Date: 2019/5/9 16:55
 * @Version 1.0
 */
public class TokenCache {
    private static Logger logger=LoggerFactory.getLogger(TokenCache.class);
    public static final String TOKEN_PREFIX="token_";
    //lru 算法
    private static LoadingCache<String,String> localCache= CacheBuilder.newBuilder().initialCapacity(1000)
            .maximumSize(10000).expireAfterAccess(2, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                //默认的数据加载实现，当调用get取值时候，如果key没有命中，就调用load
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });
    public static void setKey(String key,String value){
        localCache.put(key,value);
    }
    public static String getKey(String key){
        String value=null;
        try{
            value=localCache.get(key);
            if("null".equals(value)){
                return null;
            }
            return value;
        }catch (Exception e){
            logger.error("localCache get error",e);
        }
        return null;
    }

//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        localCache.put("1","abc");
//        localCache.put("2","efg");
//
//        System.out.println(localCache.size());
//        System.out.println(localCache.get("1"));
//        Thread.sleep(1000);
//        System.out.println(localCache.get("1"));
//        Thread.sleep(2000);
//        System.out.println(localCache.get("1"));
//        System.out.println(localCache.size());
//    }
}
