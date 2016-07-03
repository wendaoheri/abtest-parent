package com.liuxiang.abtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Test {
	public static void main(String args[]) throws Exception {
//		File file = new File("D:\\ec_workspace\\abtest-parent\\abtest-test\\target\\abtest-test-1.0.jar");
//		BufferedReader in = new BufferedReader(new FileReader(file));
//		String s = new String();
//		while((s = in.readLine()) != null) {
			URL url = new URL("file:D:\\ec_workspace\\abtest-parent\\abtest-test\\target\\abtest-test-1.0.jar");  
//			s = null;
			URLClassLoader myClassLoader = new URLClassLoader(new URL[] { url }, Thread.currentThread()  
                    .getContextClassLoader());
			Class<?> myClass = (Class<?>) myClassLoader.loadClass("com.liuxiang.abtest.test.TestAction");
			Method method = myClass.getMethod("action");
			Object instance = myClass.newInstance();
			method.invoke(instance);
			
//		}
			
			Class<?> clazz = Class.forName("com.liuxiang.abtest.test.TestAction");
			clazz.newInstance();
	}
}
