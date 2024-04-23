
# Домашнее задание для курса по Java 2023
## Домашнее №15, занятие 31 - Executors
### Задача
    Домашнее задание
    Последовательность чисел
    
    Цель:
    Освоить базовые механизмы синхронизации.

    Описание/Пошаговая инструкция выполнения домашнего задания:
    Два потока печатают числа от 1 до 10, потом от 10 до 1.
    Надо сделать так, чтобы числа чередовались, т.е. получился такой вывод:
    Поток 1:1 2 3 4 5 6 7 8 9 10 9 8 7 6 5 4 3 2 1 2 3 4....
    Поток 2: 1 2 3 4 5 6 7 8 9 10 9 8 7 6 5 4 3 2 1 2 3....
    Всегда должен начинать Поток 1.


### Выполнение
Запустить класс Main

### Результаты работы программы

    > Task :L31-Executors:Main.main()
    14:49:57.785 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 1
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 1
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 2
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 2
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 3
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 3
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 4
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 4
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 5
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 5
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 6
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 6
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 7
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 7
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 8
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 8
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 9
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 9
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 10
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 10
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 9
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 9
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 8
    14:49:57.787 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 8
    14:49:57.787 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 7
    14:49:57.788 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 7
    14:49:57.788 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 6
    14:49:57.788 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 6
    14:49:57.788 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 5
    14:49:57.788 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 5
    14:49:57.788 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 4
    14:49:57.788 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 4
    14:49:57.788 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 3
    14:49:57.788 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 3
    14:49:57.788 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 2
    14:49:57.788 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 2
    14:49:57.788 [Thread-0] INFO ru.otus.Main -- Thread Thread1: 1
    14:49:57.788 [Thread-1] INFO ru.otus.Main -- Thread Thread2: 1

