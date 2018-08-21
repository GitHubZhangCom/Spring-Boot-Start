package com.demo.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

/**
 * JSON输出工具类
 * 
 * <pre>
 * 提供api统一的结果处理
 * </pre>
 * 
 * @author xn039045
 * @createDate 2016年5月12日
 * 
 */
public class JsonUtil {

    private static Gson gson = null;
    
	// 日志记录
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    
    static {
        GsonBuilder builder = new GsonBuilder();

        // 将long类型转换为字符串--更为通用
        builder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        builder.registerTypeAdapter(JsonObject.class, new JsonDeserializer<Object>() {

            public Object deserialize(JsonElement jsonElement, Type type,
                    JsonDeserializationContext jsonDeserializationContext)
                    throws JsonParseException {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                return jsonObject;
            }
        });

        gson = builder.disableHtmlEscaping().create();
    }

    /**
     * 对象JSON转换
     * 
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
    }


    /**
     * 将字符串转换为目标类型
     * 
     * @param content
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String content, Class<T> clazz) {
        if (StringUtils.isEmpty(content) || clazz == null) {
            return null;
        }
        try {
            return gson.fromJson(content, clazz);
        } catch (JsonSyntaxException e) {
        	logger.error("json parse failed");
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * 将字符串转换为目标类型
     * 
     * <pre>
     * 支持集合类泛型
     * </pre>
     * 
     * @param content
     * @param token
     * @return
     */
    public static <T> T fromJson(String content, TypeToken<T> token) {
        if (StringUtils.isEmpty(content) || token == null) {
            return null;
        }
        try {
            return gson.fromJson(content, token.getType());
        } catch (JsonSyntaxException e) {
            logger.info("json parse failed");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将对象转换为map
     * 
     * @param obj
     * @return
     */
    public static Map<String, Object> toMap(Object obj) {
        JsonElement element = gson.toJsonTree(obj);
        return gson.fromJson(element, Map.class);
    }

    /**
     * 将对象重新转换为目标类型
     * 
     * @param obj
     * @param clazz
     * @return
     */
    public static <T> T fromObject(Object obj, Class<T> clazz) {
        JsonElement element = gson.toJsonTree(obj);
        return gson.fromJson(element, clazz);
    }

    /**
     * 将对象重新转换为目标类型
     * 
     * <pre>
     * 支持集合类的泛型
     * </pre>
     * 
     * @param obj
     * @param token
     * @return
     */
    public static <T> T fromObject(Object obj, TypeToken<T> token) {
        JsonElement element = gson.toJsonTree(obj);
        return gson.fromJson(element, token.getType());
    }

    /**
     * 从map对象获得子map
     * 
     * @param map
     * @param key
     * @return
     */
    public static Map<String, Object> getMap(Map<String, Object> map, String key) {
        if (map == null || key == null) {
            return null;
        }
        Object value = map.get(key);
        if (value instanceof Map) {
            return (Map) value;
        }
        return null;
    }

    /**
     * 从map对象中获得一个长整数
     * 
     * @param map
     * @param key
     * @return
     */
    public static Long getLong(Map<String, Object> map, String key) {
        if (map == null || key == null) {
            return null;
        }
        Object value = map.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        try {
            return Long.parseLong(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 从map对象获得一个长整数列表
     * 
     * @param map
     * @param key
     * @return
     */
    public static List<Long> getLongList(Map<String, Object> map, String key) {
        if (map == null || key == null) {
            return Collections.emptyList();
        }
        Object value = map.get(key);
        if (value == null) {
            return Collections.emptyList();
        }
        if (value instanceof List) {
            List<Object> list = (List) value;
            List<Long> longValues = new ArrayList<Long>();
            for (Object i : list) {
                if (i instanceof Number) {
                    longValues.add(((Number) i).longValue());
                }
            }
            return longValues;
        }
        return Collections.emptyList();
    }
    
	/**
	 * map转换为post请求参数
	 * 
	 * @return
	 */
	public static String mapToString(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		Iterator<?> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<?, ?> e = (Entry<?, ?>) it.next();
			sb.append(e.getKey());
			sb.append("=");
			sb.append(String.valueOf(e.getValue()));
			sb.append("&");
		}
		String content = sb.substring(0, sb.length() - 1);
		return content;
	}
    
}
