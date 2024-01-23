package ru.otus.demo.src.main.java.ru.otus.core.sessionmanager;

public interface TransactionRunner {

    <T> T doInTransaction(TransactionAction<T> action);
}
