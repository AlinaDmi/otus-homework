
# Домашнее задание для курса по Java 2023
## Домашнее №10, занятие 21 - Кэширование
### Задача



    Закончите реализацию MyCache из вебинара.
    Используйте WeakHashMap для хранения значений.
    Добавьте кэширование в DBService из задания про Hibernate ORM или "Самодельный ORM".
    Для простоты скопируйте нужные классы в это ДЗ.
    Убедитесь, что ваш кэш действительно работает быстрее СУБД и сбрасывается при недостатке памяти.
    
    


### Выполнение
Кеширование было добавлено в DataTemplateJdbc. Программа стала работать быстрее и реже обращаться в бд. При недостатке памяти gc отрабатывает и кеш очищается.

Для работы необходимо запустить докер и выполнить команду ./runDb.src из директории docker предыдущего домашнего.
После этого можно запускать:
 - HomeWork (L21-Cache/src/main/java/dbservice/HomeWork.java).
 - HWCacheDemo (L21-Cache/src/main/java/cachehw/HWCacheDemo.java)

### Результаты работы программы

HWCacheDemo

    16:05:23.994 [main] INFO cachehw.HWCacheDemo -- key:1, value:1, action: put
    16:05:23.996 [main] INFO cachehw.HWCacheDemo -- getValue:1
    16:05:23.996 [main] INFO cachehw.HWCacheDemo -- key:1, value:1, action: remove
    
    Process finished with exit code 0

HomeWork

    16:45:02.448 [DemoHiPool connection adder] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - Added connection org.postgresql.jdbc.PgConnection@4892c17d
    16:45:02.465 [DemoHiPool connection adder] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - Added connection org.postgresql.jdbc.PgConnection@d90560b
    16:45:02.466 [main] INFO dbservice.HomeWork -- db migration finished.
    16:45:02.466 [main] INFO dbservice.HomeWork -- ***
    16:45:02.477 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- created client: Client{id=47, name='dbServiceFirst'}
    16:45:02.482 [DemoHiPool connection adder] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - Added connection org.postgresql.jdbc.PgConnection@1abdbf76
    16:45:02.483 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- created client: Client{id=48, name='dbServiceSecond'}
    16:45:02.486 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- created client: Client{id=49, name='dbServiceSecond1'}
    16:45:02.494 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- clientList:[Client{id=1, name='dbServiceFirst'}, Client{id=2, name='dbServiceSecond'}, Client{id=3, name='dbServiceFirst'}, Client{id=4, name='dbServiceSecond'}, Client{id=5, name='dbServiceSecond1'}, Client{id=6, name='dbServiceSecond2'}, Client{id=7, name='dbServiceFirst'}, Client{id=8, name='dbServiceSecond'}, Client{id=9, name='dbServiceSecond1'}, Client{id=10, name='dbServiceSecond2'}, Client{id=11, name='dbServiceFirst'}, Client{id=12, name='dbServiceSecond'}, Client{id=13, name='dbServiceSecond1'}, Client{id=14, name='dbServiceSecond2'}, Client{id=15, name='dbServiceFirst'}, Client{id=16, name='dbServiceSecond'}, Client{id=17, name='dbServiceSecond1'}, Client{id=18, name='dbServiceSecond2'}, Client{id=19, name='dbServiceFirst'}, Client{id=20, name='dbServiceSecond'}, Client{id=21, name='dbServiceSecond1'}, Client{id=22, name='dbServiceSecond2'}, Client{id=23, name='dbServiceFirst'}, Client{id=24, name='dbServiceSecond'}, Client{id=25, name='dbServiceSecond1'}, Client{id=26, name='dbServiceSecond2'}, Client{id=27, name='dbServiceFirst'}, Client{id=28, name='dbServiceSecond'}, Client{id=29, name='dbServiceSecond1'}, Client{id=30, name='dbServiceSecond2'}, Client{id=31, name='dbServiceFirst'}, Client{id=32, name='dbServiceSecond'}, Client{id=33, name='dbServiceSecond1'}, Client{id=34, name='dbServiceSecond2'}, Client{id=35, name='dbServiceFirst'}, Client{id=36, name='dbServiceSecond'}, Client{id=37, name='dbServiceSecond1'}, Client{id=38, name='dbServiceSecond2'}, Client{id=39, name='dbServiceFirst'}, Client{id=40, name='dbServiceSecond'}, Client{id=42, name='dbServiceSecond2'}, Client{id=41, name='new Name'}, Client{id=43, name='dbServiceFirst'}, Client{id=44, name='dbServiceSecond'}, Client{id=46, name='dbServiceSecond2'}, Client{id=45, name='new Name'}, Client{id=47, name='dbServiceFirst'}, Client{id=48, name='dbServiceSecond'}, Client{id=49, name='dbServiceSecond1'}]
    16:45:02.496 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- updated client: Client{id=49, name='new Name'}
    16:45:02.498 [DemoHiPool connection adder] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - Added connection org.postgresql.jdbc.PgConnection@3c573ccd
    16:45:02.501 [main] INFO dbservice.DataTemplateJdbc -- taking data from cache with id 49
    16:45:02.501 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- client: Optional[Client{id=49, name='new Name'}]
    16:45:02.502 [main] INFO dbservice.DataTemplateJdbc -- taking data from cache with id 48
    16:45:02.502 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- client: Optional[Client{id=48, name='dbServiceSecond'}]
    16:45:02.502 [main] INFO dbservice.HomeWork -- clientSecondSelected:Client{id=48, name='dbServiceSecond'}
    16:45:02.504 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- created client: Client{id=50, name='dbServiceSecond2'}
    16:45:02.508 [main] INFO dbservice.DataTemplateJdbc -- taking data from db with id 50
    16:45:02.508 [DemoHiPool connection adder] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - After adding stats (total=6, active=1, idle=5, waiting=0)
    16:45:02.509 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- client: Optional[Client{id=50, name='dbServiceSecond2'}]
    
    Process finished with exit code 0

