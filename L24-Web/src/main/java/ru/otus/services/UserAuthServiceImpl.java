package ru.otus.services;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

    private static final String REALM_PROPERTIES_PATH = "/realm.properties";

    @Override
    public boolean authenticate(String login, String password) {
        Properties properties = new Properties();

        try (InputStream input = getClass().getResourceAsStream(REALM_PROPERTIES_PATH)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + REALM_PROPERTIES_PATH);
                return false;
            }
            properties.load(input);

            String storedUsername = properties.getProperty("login");
            String storedPassword = properties.getProperty("password");

            return login.equals(storedUsername) && password.equals(storedPassword);

        } catch (IOException e) {
            log.error("Error getting auth params ", e);
            return false;
        }
    }
}
