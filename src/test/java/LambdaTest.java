import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

public class LambdaTest {

    private boolean called = false;

    @Before
    public void setUp() {
        called = false;
    }

    @After
    public void tearDown() {
        called = false;
    }

    @Test
    public void test1() {
        Runnable r = () -> {
            called = true;
        };

        // 呼び出し
        r.run();

        assertTrue(called);
    }

    @Test
    public void test2() {
        assertFalse(called);

        // メソッドを1つだけもつinterfaceでlambda式が利用できる
        MyFunction mf = () -> {called = true;};
        mf.callTest();

        assertTrue(called);
    }
    private interface MyFunction {
        void callTest();
    }

    @Test
    public void test3() {
        // java.util.Function
        Function<Integer, String> f = (i) -> {
            return "param = " + i;
        };

        assertEquals(f.apply(10), "param = 10");
    }

    @Test
    public void test4() {
        assertEquals(calc(100, 50, LambdaTest::add), 150);
    }

    private int calc(int a, int b, TwoParam p) {
        return p.func(a, b);
    }

    private interface TwoParam
    {
        int func(int a, int b);
    }

    private static int add(int a, int b)
    {
        return a + b;
    }
}
