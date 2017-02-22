package qc.com.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import qc.com.util.buff.ReflectCached;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;


public class ProtoUtils {
	public static Object getProtoValueByMethodName(Message message,String methodName){
		try {
			return invokeObjectMethod(message, methodName, new Class[]{}, new Object[]{});
		} catch (Exception e) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public static Object invokeObjectMethod(Object obj, String method,
			Class[] pClass, Object[] params) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {
		Class runClass = obj.getClass();
		Method ms=ReflectCached.getMethodFromClass(runClass, method, pClass);
		ms.setAccessible(true);
		Object reObj = ms.invoke(obj, params);
		ms.setAccessible(false);
		return reObj;
	}

	public static Class getFieldListParameterizedType(Class messageClass,
			String fieldName) throws Exception {
		if(Message.class.isAssignableFrom(messageClass)||com.google.protobuf.GeneratedMessage.Builder.class.isAssignableFrom(messageClass)){//List<Meeage>情况下
			String fristChar = String.valueOf(fieldName.charAt(0));
			fieldName = fieldName.replaceFirst(fristChar, fristChar.toLowerCase())
					+ "_";
		}
		Field f =ReflectCached.getDeclaredFieldFromClass(messageClass, fieldName);
		Type p = f.getGenericType();
		if (p instanceof ParameterizedTypeImpl) {// 如果是泛型
			ParameterizedTypeImpl pti = (ParameterizedTypeImpl) p;
			Type[] paramClasses = pti.getActualTypeArguments();
			if (paramClasses.length > 0) {
				Class pmc = (Class) paramClasses[0];
				return pmc;
			}
		}
		return null;
	}
	public static Class getFieldType(Class messageClass, String fieldName)
			throws Exception {
		if(GeneratedMessage.class.isAssignableFrom(messageClass)||com.google.protobuf.GeneratedMessage.Builder.class.isAssignableFrom(messageClass)){//List<Meeage>情况下
			String fristChar = String.valueOf(fieldName.charAt(0));
			fieldName = fieldName.replaceFirst(fristChar, fristChar.toLowerCase())
					+ "_";
		}
		Field f =ReflectCached.getDeclaredFieldFromClass(messageClass, fieldName);
		return f.getType();
		// 找不到
	}
	public static String getMapListKey(Map<String,Object> map) {
		for(String key:map.keySet()){
			if(map.get(key) instanceof List){
				return key;
			}
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public static Object invokeStaticMethod(String className, String method,
			Class[] pClass, Object[] params) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {
		Method ms=ReflectCached.getMethod(className, method, pClass);
		ms.setAccessible(true);
		Object reObj = ms.invoke(null, params);
		ms.setAccessible(false);
		return reObj;
	}
}
