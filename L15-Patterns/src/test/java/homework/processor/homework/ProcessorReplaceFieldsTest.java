package homework.processor.homework;

import homework.model.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProcessorReplaceFieldsTest {

    @Test
    @DisplayName("Тестируем вызов процессора смены местом 11 и 12 полей")
    void replaceFieldsProcessorsTest() {
        var message = new Message.Builder(1L).field11("11").field12("12").build();

        var processor = new ProcessorReplaceFields();
        var result = processor.process(message);

        assertThat(result.getField12()).isEqualTo("11");
        assertThat(result.getField11()).isEqualTo("12");
    }

}