import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    public void test1() {
        //Range (最後が含まれない)
        assertArrayEquals(IntStream.range(1, 5).toArray(), new int[]{1, 2, 3, 4});
    }

    @Test
    public void test2() {
        //Range (最後が含まれる)
        assertArrayEquals(IntStream.rangeClosed(1, 5).toArray(), new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void test3() {
        // Streamの生成（無限Streamになるのでlimit指定）
        assertArrayEquals(IntStream.generate(() -> 1).limit(5).toArray(), new int[]{1, 1, 1, 1, 1});
    }

    @Test
    public void test4() {
        int allSum = Stream.generate(() -> IntStream.rangeClosed(1, 5))
                .limit(5)
                .mapToInt(x -> x.sum())
                .sum();
        assertEquals(allSum, 75);
    }

    @Test
    public void test5() {
        int[] ilist = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Streamへの変換
        IntStream ist = Arrays.stream(ilist);
        assertArrayEquals(ist.filter(x -> x % 2 == 0).toArray(), new int[]{2, 4, 6, 8, 10});
    }

    @Test
    public void test6() {
        // List -> Stream
        List<String> wrapList = Arrays.asList("a", "b", "c");
        assertArrayEquals(wrapList.stream().map(x -> "[" + x + "]").toArray(), new String[]{"[a]", "[b]", "[c]"});
    }

    @Test
    public void test7() {
        // すべての要素が条件を満たすか
        List<Integer> allMatchList = Arrays.asList(2, 4, 6);
        assertTrue(allMatchList.stream().allMatch(x -> x % 2 == 0));
        assertFalse(allMatchList.stream().allMatch(x -> x == 2));
    }

    @Test
    public void test8() {
        // 畳み込み
        Optional<Integer> sum = Stream.generate(() -> 1)
                .limit(5)
                .reduce((elem, acc) -> elem + acc);
        assertTrue(sum.isPresent());
        assertEquals(sum.get(), Integer.valueOf(5));
    }
}
