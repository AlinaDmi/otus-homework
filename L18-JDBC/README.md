
# Домашнее задание для курса по Java 2023
## Домашнее №9, занятие 18 - JDBC
### Задача

     Работа должна использовать базу данных в docker-контейнере .
     В модуле homework реализуйте классы:

    EntityClassMetaData
    EntitySQLMetaData
    DataTemplateJdbc
    Метод main в классе HomeWork должен работать без ошибок.



### Выполнение

Для работы необходимо запустить докер и выполнить команду ./runDb.src из директории docker.
После этого можно запускать HomeWork (L18-JDBC/src/main/java/ru/otus/homework/src/main/java/ru/HomeWork.java)

### Результаты работы программы

    12:44:01.464 [main] INFO com.zaxxer.hikari.HikariDataSource -- DemoHiPool - Starting...
    12:44:01.484 [main] DEBUG com.zaxxer.hikari.util.DriverDataSource -- Loaded driver with class name org.postgresql.Driver for jdbcUrl=jdbc:postgresql://localhost:5430/demoDB
    12:44:01.616 [main] INFO com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - Added connection org.postgresql.jdbc.PgConnection@5e955596
    12:44:01.720 [main] INFO com.zaxxer.hikari.HikariDataSource -- DemoHiPool - Start completed.
    12:44:01.720 [main] INFO ru.otus.homework.src.main.java.ru.otus.HomeWork -- db migration started...
    Jan 23, 2024 12:44:01 PM org.flywaydb.core.internal.license.VersionPrinter printVersionOnly
    INFO: Flyway Community Edition 9.16.3 by Redgate
    Jan 23, 2024 12:44:01 PM org.flywaydb.core.internal.license.VersionPrinter printVersion
    INFO: See release notes here: https://rd.gt/416ObMi
    Jan 23, 2024 12:44:01 PM org.flywaydb.core.internal.license.VersionPrinter printVersion
    INFO:
    Jan 23, 2024 12:44:01 PM org.flywaydb.core.internal.database.base.BaseDatabaseType createDatabase
    INFO: Database: jdbc:postgresql://localhost:5430/demoDB (PostgreSQL 13.13)
    12:44:01.820 [DemoHiPool housekeeper] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - Before cleanup stats (total=1, active=1, idle=0, waiting=0)
    12:44:01.821 [DemoHiPool housekeeper] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - After cleanup  stats (total=1, active=1, idle=0, waiting=0)
    12:44:01.827 [DemoHiPool connection adder] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - Added connection org.postgresql.jdbc.PgConnection@6d822cc9
    12:44:01.843 [DemoHiPool connection adder] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - Added connection org.postgresql.jdbc.PgConnection@677a47b9
    Jan 23, 2024 12:44:01 PM org.flywaydb.core.internal.command.DbValidate validate
    INFO: Successfully validated 1 migration (execution time 00:00.020s)
    Jan 23, 2024 12:44:01 PM org.flywaydb.core.internal.command.DbMigrate migrateGroup
    INFO: Current version of schema "public": 1
    Jan 23, 2024 12:44:01 PM org.flywaydb.core.internal.command.DbMigrate logSummary
    INFO: Schema "public" is up to date. No migration necessary.
    12:44:01.858 [DemoHiPool connection adder] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - Added connection org.postgresql.jdbc.PgConnection@2278d532
    12:44:01.862 [main] INFO ru.otus.homework.src.main.java.ru.otus.HomeWork -- db migration finished.
    12:44:01.862 [main] INFO ru.otus.homework.src.main.java.ru.otus.HomeWork -- ***
    12:44:01.868 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- created client: Client{id=23, name='dbServiceFirst'}
    12:44:01.871 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- created client: Client{id=24, name='dbServiceSecond'}
    12:44:01.872 [DemoHiPool connection adder] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - Added connection org.postgresql.jdbc.PgConnection@5ee041d0
    12:44:01.874 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl -- client: Optional[Client{id=24, name='dbServiceSecond'}]
    12:44:01.875 [main] INFO ru.otus.homework.src.main.java.ru.otus.HomeWork -- clientSecondSelected:Client{id=24, name='dbServiceSecond'}
    12:44:01.876 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceManagerImpl -- created manager: Manager{no=9, label='ManagerFirst'}
    12:44:01.878 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceManagerImpl -- created manager: Manager{no=10, label='ManagerSecond'}
    12:44:01.881 [main] INFO ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceManagerImpl -- manager: Optional[Manager{no=10, label='ManagerSecond'}]
    12:44:01.881 [main] INFO ru.otus.homework.src.main.java.ru.otus.HomeWork -- managerSecondSelected:Manager{no=10, label='ManagerSecond'}
    12:44:01.882 [DemoHiPool connection adder] DEBUG com.zaxxer.hikari.pool.HikariPool -- DemoHiPool - After adding stats (total=5, active=0, idle=5, waiting=0)