package com.sarthak.lruCache;

/**
 * Hello world!
 *
 */
public class CacheDriver 
{
    public static void main( String[] args )
    {
        LRUCache cache = new LRUCache(5);
        
        //Order a
        cache.put("a", 1);
        
        //Order b -> a
        cache.put("b", 2);
        
        //Order c -> b -> a
        cache.put("c", 3);
        
        //Order d -> c -> b -> a
        cache.put("d", 4);
        
        // Order e -> d -> c -> b -> a
        cache.put("e", 5);
        
        //Order a -> e -> d -> c -> b //Transfer a to top
        cache.getValue("a");
        
        //Order b -> a -> e -> d -> c // Transfer b to top
        cache.getValue("b");
        
        //Order f -> b -> a -> e -> d //Remove c and add f to top
        cache.put("f", 6);
        
        //Order d -> f -> b -> a -> e //Transfer d to top and update value
        cache.put("d", 2);
        
        //Order g -> d -> f -> b -> a //Remove e and add g to top
        cache.put("g", 8);
        
        System.out.println(cache.getValue("f"));
    }
}
