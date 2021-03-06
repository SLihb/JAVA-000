import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description 作业2 加载xlass文件，执行hello方法
 * @Author lihb
 * @Date 2020/10/19 3:20 下午
 */
public class HelloClassLoader extends ClassLoader {

    private final String classPath;

    public HelloClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) {
        // 将文件读取成字节码
        byte[] classData = file2Byte();
        // 对每个字节进行255处理
        for (int i = 0; i < classData.length; i++) {
             classData[i] = (byte) (255 - classData[i]);
        }
        // 返回解析的类
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] file2Byte() {
        File file = new File(classPath);
        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int size = 0;
            // 从文件中读取
            while ((size = in.read(buffer)) != -1) {
                out.write(buffer, 0, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return out.toByteArray();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        HelloClassLoader classLoader = new HelloClassLoader("Week_01/work1/Hello.xlass");
        // 加载类
        Class<?> aClass = classLoader.loadClass("Hello");
        try {
            // 实例化类
            Object newInstance = aClass.newInstance();
            // 获取该类的hello方法
            Method hello = aClass.getMethod("hello");
            // 调用hello方法
            hello.invoke(newInstance);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
