package com.adapterj.test.commandline;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.adapterj.annotation.GetMethod;
import com.adapterj.example.pojo.Source;
import com.adapterj.widget.SimpleListAdapter;

public class Main1 {
	
	public static void main(String args[]) {
		boolean async = true;
		String type = "text/javascript";
		String uri = "http://static.google.com/js/abc.js";
		String defer = "defer";
		
		final StringBuilder s = new StringBuilder();
		s.append("<script ").append(async ? "async " : "").append("type=\"").append(type).append("\" src=\"").append(uri).append("\" defer=\"").append(defer).append("\"></script>").append('\n');
		System.out.println(s.toString());
	}
	
	static final String FORM_OPTION_VALUE    = "<![CDATA[= %s.getSelectOptions(\"%s\").selected(String.valueOf(%s.%s()), %%s) ]]>";
	public static void main5(String args[]) {
		final String formatted1 = String.format(FORM_OPTION_VALUE, "adapter1", "type", "data", "getType");
		System.out.println("formatted1: " + formatted1);
		
		final String formatted2 = String.format(formatted1, true);
		System.out.println("formatted2: " + formatted2);
	}
	
	public static void main4(String[] args) {
		java.text.DateFormat _formatter = new SimpleDateFormat("G yyyy-MM-dd HH:mm");

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date time0 = cal.getTime();

		System.out.println(time0.getTime());
		System.out.println(_formatter.format(time0));

		Date time1 = new Date(-62135798400000L);
		System.out.println(_formatter.format(time1));

		Date time2 = new Date();
		System.out.println(_formatter.format(time2));
	}

	public static void main3(String[] args) {
		Date begin = new Date();

		String path = Main1.class.getClassLoader().getResource("").getPath();
		System.out.println(path);

		/*
		AdapterJClassLoader classLoader = AdapterJClassLoader.getInstance();
		try {
			classLoader.findClass(Source.class.getName());
			classLoader.loadClass(Source.class.getName());
			classLoader.loadClass(Source.class.getName());
			classLoader.findClass(Source.class.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Error e) {
			e.printStackTrace();
		}*/

		Date end = new Date();
		final long cost = end.getTime() - begin.getTime();
		System.out.println(cost);
	}

	public static void main2(String[] args) {
		SimpleListAdapter<Source> adapter = new SimpleListAdapter<Source>();
		Class<?> clazz = null;

		// 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
		Type genType = adapter.getClass().getGenericSuperclass();
		System.out.println(genType);
		if (!(genType instanceof ParameterizedType)) {
			System.out.println("generic superclass not a ParameterizedType instance");
			clazz = Object.class;
		} else {
			// 返回表示此类型实际类型参数的 Type 对象的数组。
			Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
			for (int i = 0; i < params.length; i++) {
				System.out.println("Type is " + params[i]);
			}

			int index = 0;
			if (index >= params.length || index < 0) {
				clazz = Object.class;
			} else if (!(params[index] instanceof Class)) {
				clazz = Object.class;
			} else {
				clazz = (Class<?>) params[index];
			}
		}
		System.out.println(clazz);
	}

	public static void main1(String[] args) {
		final Method[] methods = Source.class.getMethods();
		for (final Method method : methods) {
			if (method.isAnnotationPresent(GetMethod.class)) {
				System.out.println(method.getName() + "()");
				System.out.println(method.toGenericString());
				System.out.println(method.toString());
				System.out.println();
			}
		}
	}
}
