package process;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class ExecutorTest {

    private Executor executor = new Executor();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldThrowErrorInvalidVariableName() throws IOException {
        // given
        String input = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("invalidVariable.txt"));

        // then
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Invalid name for variable");

        // when
        executor.execute(input);
    }

    @Test
    public void shouldThrowErrorUndeclaredVariable() throws IOException {
        // given
        String input = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("undeclaredVariable.txt"));

        // then
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("2  Use of undeclared variable name widthdd");

        // when
        executor.execute(input);
    }

    @Test
    public void shouldThrowErrorInvalidAssignment() throws IOException {
        // given
        String input = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("invalidAssignment.txt"));

        // then
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("2  Invalid element in expression");

        // when
        executor.execute(input);
    }

}