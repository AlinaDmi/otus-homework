package homework.processor.homework;

import homework.model.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorExceptionForEvenSecondsTest {

    @Test
    @DisplayName("Тестируем вызовы эксепшены в чётную секунду")
    void processTest() {

        ProcessorExceptionForEvenSeconds processor = new ProcessorExceptionForEvenSeconds(
                () -> LocalDateTime.of(2024, Month.JANUARY, 5, 17, 0, 2));

        var message = new Message.Builder(1L).build();

        Throwable exception = assertThrows(IllegalStateException.class, () -> processor.process(message));
        assertEquals("Exception for even second: 2", exception.getMessage());
    }

    @Test
    @DisplayName("Тестируем вызовы эксепшены в нечётную секунду")
    void processOddTest() {

        ProcessorExceptionForEvenSeconds processor = new ProcessorExceptionForEvenSeconds(
                () -> LocalDateTime.of(2024, Month.JANUARY, 5, 17, 0, 1));

        var message = new Message.Builder(1L).build();

        assertDoesNotThrow(() -> processor.process(message));
    }
}