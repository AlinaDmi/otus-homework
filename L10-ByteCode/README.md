
# Домашнее задание для курса по Java 2023
## Домашнее №5, занятие 10 - Байт код
### Задача

    Разработайте такой функционал:
    метод класса можно пометить самодельной аннотацией @Log, например, так:
    class TestLogging implements TestLoggingInterface {
    @Log
    public void calculation(int param) {};
    }
    При вызове этого метода "автомагически" в консоль должны логироваться значения параметров.
    Например так.
    class Demo {
    public void action() {
    new TestLogging().calculation(6);
    }
    }
    В консоле дожно быть:
    executed method: calculation, param: 6
    Обратите внимание: явного вызова логирования быть не должно.
    Учтите, что аннотацию можно поставить, например, на такие методы:
    public void calculation(int param1)
    public void calculation(int param1, int param2)
    public void calculation(int param1, int param2, String param3)

### Выполнение

Выполнение в классе App. Пример вывода

    17:29:24.856 [main] INFO homework.Ioc -- executing method: calculation, params: 1
    calculating 1
    17:29:24.864 [main] INFO homework.Ioc -- executing method: calculation, params: 1, 2
    calculating 12
    calculating 123
    17:29:24.874 [main] INFO homework.Ioc -- executing method: writing, params: sssss
    writing sssss