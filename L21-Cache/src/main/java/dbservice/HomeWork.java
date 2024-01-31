package dbservice;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.demo.src.main.java.ru.otus.core.repository.executor.DbExecutorImpl;
import ru.otus.demo.src.main.java.ru.otus.core.sessionmanager.TransactionRunnerJdbc;
import ru.otus.demo.src.main.java.ru.otus.crm.datasource.DriverManagerDataSource;
import ru.otus.demo.src.main.java.ru.otus.crm.model.Client;
import ru.otus.demo.src.main.java.ru.otus.crm.model.Manager;
import ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceClientImpl;
import ru.otus.demo.src.main.java.ru.otus.crm.service.DbServiceManagerImpl;
import ru.otus.homework.src.main.java.ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.homework.src.main.java.ru.otus.jdbc.mapper.EntitySQLMetaData;
import ru.otus.homework.src.main.java.ru.otus.jdbc.mapper.impl.EntityClassMetaDataImpl;
import ru.otus.homework.src.main.java.ru.otus.jdbc.mapper.impl.EntitySQLMetaDataImpl;

import javax.sql.DataSource;
import java.util.Optional;

@SuppressWarnings({"java:S125", "java:S1481"})
public class HomeWork {
    private static final String URL = "jdbc:postgresql://localhost:5430/demoDB";
    private static final String USER = "usr";
    private static final String PASSWORD = "pwd";

    private static final Logger log = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
        // Общая часть
        var dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        flywayMigrations(dataSource);
        var transactionRunner = new TransactionRunnerJdbc(dataSource);
        var dbExecutor = new DbExecutorImpl();

        // Работа с клиентом
        EntityClassMetaData<Client> entityClassMetaDataClient = new EntityClassMetaDataImpl<>(Client.class);
        EntitySQLMetaData<Client> entitySQLMetaDataClient = new EntitySQLMetaDataImpl<>(entityClassMetaDataClient);
        var dataTemplateClient = new DataTemplateJdbc<>(
                dbExecutor, entitySQLMetaDataClient, Client.class); // реализация DataTemplate, универсальная

        // Выполняем разные операции
        var dbServiceClient = new DbServiceClientImpl(transactionRunner, dataTemplateClient);
        dbServiceClient.saveClient(new Client("dbServiceFirst"));

        var clientSecond = dbServiceClient.saveClient(new Client("dbServiceSecond"));
        var client1 = dbServiceClient.saveClient(new Client("dbServiceSecond1"));

        dbServiceClient.findAll();

        client1.setName("new Name");
        dbServiceClient.saveClient(client1);
        Optional<Client> updatedClient1 = dbServiceClient.getClient(client1.getId());
        if ( updatedClient1.isEmpty() || !updatedClient1.get().getName().equals("new Name")){
            throw new RuntimeException("Client not updated");
        }

        var clientSecondSelected = dbServiceClient
                .getClient(clientSecond.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecond.getId()));
        log.info("clientSecondSelected:{}", clientSecondSelected);

        var client2 = dbServiceClient.saveClient(new Client("dbServiceSecond2"));
        dbServiceClient.getClient(client2.getId());

    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }
}
