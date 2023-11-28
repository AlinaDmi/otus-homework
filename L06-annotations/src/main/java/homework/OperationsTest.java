package homework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;
import org.junit.jupiter.api.Assertions;

class OperationsTest {
    private Operations operations;

    @Before
    public void setUp() {
        System.out.println("before tests");
        operations = new Operations();
    }

    @Test
    public void sumTest() {
        System.out.println("--test1");
        Assertions.assertEquals((3), operations.sum(1, 2));
    }

    @Test
    public void exceptionTest() {
        System.out.println("--test2");
        throw new RuntimeException("test exception");
    }

    @Test
    public void biggerTest() {
        System.out.println("--test3");
        Assertions.assertEquals((2), operations.getBigger(1, 2));
    }

    @After
    public void clean() {
        System.out.println("after tests");
        operations = null;

    }

}