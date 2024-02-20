package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.Method;
import java.util.*;

@SuppressWarnings("squid:S1068")
public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);

        // Создаем экземпляр конфигурационного класса
        Object configInstance;
        try {
            configInstance = configClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create config instance", e);
        }

        // Получаем все методы с аннотацией @AppComponent
        Method[] methods = configClass.getDeclaredMethods();
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AppComponent.class))
                .sorted(Comparator.comparingInt(method -> method.getAnnotation(AppComponent.class).order()))
                .forEach(method -> {
                    // Создаем компонент, передавая необходимые зависимости
                    Object component = createComponent(configInstance, method);
                    // Добавляем компонент в список
                    appComponents.add(component);
                    // Добавляем компонент в мапу по имени
                    String name = method.getAnnotation(AppComponent.class).name();
                    if (appComponentsByName.containsKey(name)) {
                        throw new IllegalArgumentException(String.format("Given name already exists %s", name));
                    }
                    appComponentsByName.put(name, component);
                });
    }

    private Object createComponent(Object configInstance, Method method) {
        try {
            // Получаем аргументы метода
            Class<?>[] parameterTypes = method.getParameterTypes();
            // Создаем массив для аргументов
            Object[] args = new Object[parameterTypes.length];
            // Заполняем массив аргументов, получая каждый аргумент из списка
            for (int i = 0; i < parameterTypes.length; i++) {
                args[i] = getAppComponent(parameterTypes[i]);
            }
            // Вызываем метод конфигурации для создания компонента
            return method.invoke(configInstance, args);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create component", e);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        // Ищем компонент нужного класса в списке компонентов
        List<C> components = appComponents.stream()
                .filter(componentClass::isInstance)
                .map(componentClass::cast)
                .toList();
        if (components.size() != 1) {
            throw new IllegalArgumentException(String.format("Given component doesn't exist or duplicating %s", componentClass.getName()));
        }
        return components.get(0);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        // Получаем компонент из мапы по имени
        C component = (C) appComponentsByName.get(componentName);

        if (component == null) {
            throw new IllegalArgumentException(String.format("Given component doesn't exist %s", componentName));
        }

        return component;
    }
}
