package live.cnaidai.com.ccyb.util;

import java.lang.reflect.ParameterizedType;

/**
 * @author JokerX
 * @version V1.0
 * @Package com.aidai.aidaimvp.utils
 * @Description: 通过泛型获取当前的P和M
 * @date 2016/5/18 14:54
 */
public class TUtil {

	public static <T> T getT(Object o, int i) {
		try {
			return ((Class<T>) ((ParameterizedType) (o.getClass()
				.getGenericSuperclass())).getActualTypeArguments()[i])
				.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Class<?> forName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
