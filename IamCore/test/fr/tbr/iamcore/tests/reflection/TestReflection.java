package fr.tbr.iamcore.tests.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.tbr.iamcore.datamodel.Identity;

public class TestReflection {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		String propertyToSetThroughReflection = "displayName";
		
		Identity identity = new Identity();
		
		Class<?> clazz = identity.getClass();
		//The following won't work directly because displayName is "private"
//		Field field = clazz.getField(propertyToSetThroughReflection);
//		field.set(identity, "toto");
//		
		
		Method method = clazz.getMethod("setDisplayName", String.class);
		method.invoke(identity, "toto");
		
		identity.setDisplayName("toto");
		
		System.out.println(identity);

	}

}
