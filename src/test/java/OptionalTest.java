import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalTest {

    @Test(expected = NullPointerException.class)
    public void test1() {
        // ofにNull渡すとExceptionが発生する
        Optional.of(null);
    }

    @Test
    public void test2() {
        // ofNullableだとExceptionは発生しない
        Optional<?> opt = Optional.ofNullable(null);
        assertFalse(opt.isPresent());

        //emptyになる
        assertEquals(opt, Optional.empty());
    }

    @Test
    public void test3() {
        Optional<?> opt = Optional.ofNullable(1);

        // getで値が取得
        assertEquals(opt.get(), 1);
    }

    @Test(expected = NoSuchElementException.class)
    public void test4() {
        //Emptyから値を取り出そうとするとException発生
        Optional.empty().get();
    }

    @Test
    public void test5() {
        //mapも使える
        Optional<?> opt = Optional.ofNullable(1);
        Optional<String> opt2 = opt.map(x -> x.toString());

        assertEquals(opt2.get(), "1");
    }

    @Test
    public void test6() {
        //filterも使える
        Optional<Integer> opt = Optional.ofNullable(10);

        assertTrue(opt.filter(x -> x % 2 == 0).isPresent());
        assertFalse(opt.filter(x -> x % 2 != 0).isPresent());
    }

    @Test
    public void test7() {
        //flatten
        Optional<Optional<Integer>> opt = Optional.ofNullable(Optional.ofNullable(10));
        Optional<Integer> opt2 = opt.flatMap(x -> x);

        assertEquals(opt2.get(), Integer.valueOf(10));
    }
}
