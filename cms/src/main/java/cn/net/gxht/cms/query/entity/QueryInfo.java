package cn.net.gxht.cms.query.entity;

import net.sf.cglib.beans.BeanMap;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/11/17.
 */
public class QueryInfo {
//    private String appKey="916ee64c0f3fe063fbe0ab28cc21f948";
    public static Map<String,Object> getMapFromObject(Object object){
        Map<String,Object> maps=new HashedMap();
        BeanMap beanMap=BeanMap.create(object);
        for(Object key:beanMap.keySet()){
            System.out.println(key.toString()+","+beanMap.get(key));
            if(beanMap.get(key)!=null&&!beanMap.get(key).equals("")){
                maps.put(key.toString(),beanMap.get(key));
            }
        }
        return maps;
    }
}
