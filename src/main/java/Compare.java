import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;

public class Compare {

    private static BaseResult baseResult;

    /**
     * @Description: 更新结果，错误数量计数
     * @Param: [parameter]
     * @Author: YJiang
     * @DateTimeFormat: 2020/3/8
     */
    private static void updateBaseResult(ResultDetail resultDetail) {
        // 错误数量统计
        baseResult.setWrongNumber(baseResult.getWrongNumber() + 1);

        // 填充 RetValue 错误内容
        if (baseResult.getResultDetail() == null) {
            ArrayList<ResultDetail> resultDetailArrayList = new ArrayList<ResultDetail>();
            resultDetailArrayList.add(resultDetail);
            baseResult.setResultDetail(resultDetailArrayList);
        } else {
            baseResult.getResultDetail().add(resultDetail);
        }

    }

    /**
     * @Description: 字符串比较
     * @Param: [parameter]
     * @return: void
     * @Author: YJiang
     * @DateTimeFormat: 2020/3/8
     */
    private static void compareJson(String actualString, String expectString, String key, String prefix , String ignore) {
        boolean status = false;
        if (StringUtils.isNotBlank(ignore)) {
            List<String> listIgnore = Arrays.asList(ignore.split(","));
            status = listIgnore.contains(key);
        }
        if (!status) {
            if (!actualString.equals(expectString)) {
                ResultDetail resultDetail = new ResultDetail(prefix, actualString, expectString, "Value Not Equal");
                updateBaseResult(resultDetail);
                System.out.println(
                        "\033[1;31m" + "【fail】Key:" + prefix + "\n     actualJson:" + actualString + "\n     expectJson:" + expectString + "\033[m");

            } else {
                System.out.println(
                        "\033[1;94m" + "【success】Key:" + prefix + "\n     actualJson:" + actualString + "\n     expectJson:" + expectString + "\033[m");
            }
        }

    }

    /**
     * @Description: JSONObject 比较
     * @Param: [parameter]
     * @return: void
     * @Author: YJiang
     * @DateTimeFormat: 2020/3/8
     */
    private static void compareJson(JSONObject actualJson, JSONObject expectJson, String key,String prefix, String ignore) {
        if (StringUtils.isBlank(prefix)){
            prefix = "";
        }else {
            prefix = prefix + ".";
        }

        for (String s : expectJson.keySet()) {
            key = s;
            compareJson(actualJson.get(key), expectJson.get(key), key, prefix + key, ignore);
        }
    }

    /**
     * @Description: JSONArray 比较
     * @Param: [parameter]
     * @return: void
     * @Author: YJiang
     * @DateTimeFormat: 2020/3/8
     */
    private static void compareJson(JSONArray actualJsonArray, JSONArray expectJsonArray, String key,String prefix, String ignore) {
        if (actualJsonArray != null && expectJsonArray != null) {
            if (actualJsonArray.size() == expectJsonArray.size()) {
                Iterator iteratorActualJsonArray = actualJsonArray.iterator();
                if (StringUtils.isBlank(prefix)){
                    prefix = "";
                }
                int num = 0;
                for (Object o : expectJsonArray) {
                    compareJson(iteratorActualJsonArray.next(), o, key,prefix+"["+num+"]", ignore);
                    num ++;

                }
            } else {
                ResultDetail resultDetail = new ResultDetail(prefix, actualJsonArray, expectJsonArray, "Length Not Equal");
                updateBaseResult(resultDetail);
            }
        } else {
            if (actualJsonArray == null && expectJsonArray == null) {
                ResultDetail resultDetail = new ResultDetail(prefix, "在 actualJsonArray 中不存在",
                    "在 expectJsonArray 中不存在",
                    "Both Not Exist");
                updateBaseResult(resultDetail);
            } else if (actualJsonArray == null) {
                ResultDetail resultDetail = new ResultDetail(prefix, "在 actualJsonArray 中不存在", expectJsonArray,
                    "Other Exist");
                updateBaseResult(resultDetail);
            } else {
                ResultDetail resultDetail = new ResultDetail(prefix, actualJsonArray, "在 expectJsonArray 中不存在",
                        "Other Exist");
                updateBaseResult(resultDetail);
            }
        }

    }
    /**
     * @Description: Object 比较
     * @Param: [parameter]
     * @return: void
     * @Author: YJiang
     * @DateTimeFormat: 2020/3/8
     */
    private static void compareJson(Object actualJson, Object expectJson, String key,String prefix, String ignore) {
        if (actualJson != null && expectJson != null) {
            if (actualJson instanceof JSONObject) {
                compareJson((JSONObject)actualJson, (JSONObject)expectJson, key,prefix, ignore);
            } else if (actualJson instanceof JSONArray) {
                compareJson((JSONArray)actualJson, (JSONArray)expectJson, key,prefix, ignore);
            } else if (actualJson instanceof String) {
                try {
                    String actualJsonToStr = actualJson.toString();
                    String expectJsonToStr = expectJson.toString();
                    compareJson(actualJsonToStr, expectJsonToStr, key,prefix, ignore);

                } catch (Exception e) {
                    ResultDetail resultDetail = new ResultDetail(prefix, actualJson, expectJson, "String 转换发生异常 Key");
                    updateBaseResult(resultDetail);
                    e.printStackTrace();
                }

            } else {
                compareJson(actualJson.toString(), expectJson.toString(), key,prefix, ignore);
            }
        } else {
            if (actualJson == null && expectJson == null) {
                ResultDetail resultDetail = new ResultDetail(prefix, "在actualJson中不存在", "在expectJson中不存在",
                    "Both Not Exist");
                updateBaseResult(resultDetail);
            } else if (actualJson == null) {
                ResultDetail resultDetail = new ResultDetail(prefix, "在actualJson中不存在", expectJson, "Other Exist");
                updateBaseResult(resultDetail);
            } else{
                ResultDetail resultDetail = new ResultDetail(prefix, actualJson, "在expectJson中不存在", "Not Exist");
                updateBaseResult(resultDetail);
            }

        }

    }

    /** 非线性安全
     * @Description: CompareFactory
     * @Param: [parameter]
     * @return: void
     * @Author: YJiang
     * @DateTimeFormat: 2020/3/9
     */
    public static <T> BaseResult CompareFactory(T actual, T expect, String ignore) {

        Compare.baseResult = new BaseResult();
        Compare.compareJson(actual, expect, null,null, ignore);

        return Compare.baseResult;
    }

}
