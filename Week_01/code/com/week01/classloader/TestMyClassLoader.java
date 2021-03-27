package com.week01.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMyClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Object log1 = classLoader1();
        Object log2 = classLoader2();
        System.out.println(log1.equals(log2));
    }

    private static Object classLoader1() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String logFilePath = "E:\\Java-Code\\JAVA-000\\Week_01\\code\\com\\week01\\classloader\\Log.class";
        MyClassLoader classLoader = new MyClassLoader(logFilePath);
        Class<?> Log = classLoader.findClass("com.week01.classloader.Log");

        Method print = Log.getDeclaredMethod("print", String.class);
        Object log = Log.newInstance();
        print.invoke(log, "实例化成功");
        return log;
    }

    private static Object classLoader2() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String logFilePath = "E:\\Java-Code\\JAVA-000\\Week_01\\code\\com\\week01\\classloader\\Log.class";
        MyClassLoader2 classLoader = new MyClassLoader2(logFilePath);
        Class<?> Log = classLoader.findClass("com.week01.classloader.Log");

        Method print = Log.getDeclaredMethod("print", String.class);
        Object log = Log.newInstance();
        print.invoke(log, "实例化成功");
        return log;
    }
}
