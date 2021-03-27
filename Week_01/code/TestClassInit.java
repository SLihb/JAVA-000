import java.util.ArrayList;
import java.util.List;

public class TestClassInit {

    public static void main(String[] args) throws ClassNotFoundException {
//        List<HelloWord> list = new ArrayList();
        TestClassInit testClassInit = new TestClassInit();
        Class.forName("Week_01/code/HelloWord.java", true, testClassInit.getClass().getClassLoader());
    }
}