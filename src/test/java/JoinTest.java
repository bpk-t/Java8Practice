import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.Arrays;
import java.util.StringJoiner;

public class JoinTest {
    @Test
    public void test1()
    {
        String s = String.join("*", "AA", "BB", "CC");
        assertEquals(s, "AA*BB*CC");

        // 文字列リストを渡す
        String [] sarray = {"aa", "bb", "cc"};
        assertEquals(String.join(",", sarray), "aa,bb,cc");
    }

    @Test
    public void test2()
    {
        // String Joiner
        String [] langs = {"cpp", "csharp", "golang"};
        StringJoiner joiner = new StringJoiner(",", "(", ")");
        joiner.setEmptyValue("");
        Arrays.stream(langs).forEach(joiner::add);

        assertEquals(joiner.toString(), "(cpp,csharp,golang)");
    }
}
