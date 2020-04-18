import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

public class TestCompare {
    private Map mapTestData;

    @BeforeTest
    public void initBefore(){
        String datdFilePath = "src/test/yaml/CompareJson.yaml";
        this.mapTestData = LoadYaml.load(datdFilePath);
    }

    @Test
    public void compareJsonObjectTest(){
        // JSONObject 对象
        String jsonObjectA = mapTestData.get("JsonObjectA").toString();
        String jsonObjectB = mapTestData.get("JsonObjectB").toString();

        JSONObject jsonObject1 = JSONObject.parseObject(jsonObjectA);
        JSONObject jsonObject2 = JSONObject.parseObject(jsonObjectB);

        BaseResult baseResult = Compare.CompareFactory(jsonObject1, jsonObject2, "code,designation,hobby_id,desc");
        System.out.println("比对结果：" + JSONObject.toJSONString(baseResult));

    }

    @Test
    public void compareJsonArrayTest() {
        // JSONArray 对象
        String jsonArrayA = mapTestData.get("JsonArrayA").toString();
        String jsonArrayB = mapTestData.get("JsonArrayB").toString();

        JSONArray jsonArray1 = JSONArray.parseArray(jsonArrayA);
        JSONArray jsonArray2 = JSONArray.parseArray(jsonArrayB);

        BaseResult baseResult1 = Compare.CompareFactory(jsonArray1, jsonArray2, "name");
        System.out.println("比对结果：" + JSONObject.toJSONString(baseResult1));
    }

    @Test
    public void compareStringTest() {
        // String
        String stringA = "IntelliJ IDEA 2019.2.4";
        String stringB = "IntelliJ IDEA 2019.2.4";
        BaseResult baseResult2 = Compare.CompareFactory(stringA, stringB, null);
        System.out.println("比对结果：" + JSONObject.toJSONString(baseResult2));
    }

    @Test
    public void compareIntegerTest() {
        // Integer
        Integer integerA = 9527;
        Integer integerB = 9521;
        BaseResult baseResult3 = Compare.CompareFactory(integerA, integerB, null);

        System.out.println("比对结果：" + JSONObject.toJSONString(baseResult3));
    }

    @Test
    public void compareLongTest() {
        // Long
        Long LongA = 9527L;
        Long LongB = 9521L;
        BaseResult baseResult4 = Compare.CompareFactory(LongA, LongB, null);
        System.out.println("比对结果：" + JSONObject.toJSONString(baseResult4));
    }
}

