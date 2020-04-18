
import com.alibaba.fastjson.JSONObject;

public class TypeConversion {

    /**
     * @Description: 转换JavaBean对象
     * @Param: [object, clazz]
     * @return: T
     * @Author: YJiang
     * @DateTimeFormat: 2020/3/8
     */
    public static <T> T stringToJavaBean(Object object, Class<T> clazz) {

        String string = JSONObject.toJSONString(object);
        JSONObject json = JSONObject.parseObject(string);

        return JSONObject.toJavaObject(json, clazz);
    }
}