package algo.other;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class SimpleExprParser {

    public static void main(String[] args) throws Exception {
        String json = "{\"aa\":\"1\",\"bb\":\"{\\\"cc\\\":\\\"2\\\"}\"}";
        Map map = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        System.out.println(parse(map, "${aa}"));
        System.out.println(parse(map, "${bb.cc}"));
        System.out.println(parse(map, "${aa}_t1"));
        System.out.println(parse(map, "tt_${bb.cc}"));
    }

    /**
     * demo from: "{\"aa\":\"1\",\"bb\":\"{\\\"cc\\\":\\\"2\\\"}\"}";
     * 支持2层表达式提取，如${aa}、${bb.cc}_xx
     * @param valueMap json to map
     * @param exprStr
     * @return
     * @throws Exception
     */
    private static String parse(Map valueMap, String exprStr) throws Exception {
        try {
            if (exprStr == null || exprStr.equals("")) {
                return null;
            }
            if (!exprStr.contains("$")) {
                return exprStr;
            }
            int start = exprStr.indexOf("${");
            int end = exprStr.indexOf("}");
            String prefix = exprStr.substring(0, start);
            String expr = exprStr.substring(start + 2, end);
            String suffix = exprStr.substring(end + 1);
            if (!expr.contains(".")) {
                return prefix + valueMap.get(expr) + suffix;
            }
            String[] split = expr.split("\\.");
            Object value = valueMap.get(split[0]);
            if (value instanceof Map) {
                return prefix + ((Map) value).get(split[1]) + suffix;
            } else {
                Map<String, String> map = new Gson().fromJson(value.toString(), new TypeToken<Map<String, String>>() {
                }.getType());
                valueMap.put(split[0], map);
                return prefix + map.get(split[1]) + suffix;
            }
        } catch (Exception e) {
            throw new Exception("parse error", e);
        }
    }
}
