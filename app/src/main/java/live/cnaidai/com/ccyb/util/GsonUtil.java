package live.cnaidai.com.ccyb.util;

import com.google.gson.Gson;

/**
 * Created by ChristLu on 2016/1/18.
 */
public class GsonUtil {
    public static <T> T convertJsonToObj(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return t;
    }

    public static String convertObjToJson(Object value)
    {
        Gson gson = new Gson();
        String str = gson.toJson(value);
        return str;
    }


}
