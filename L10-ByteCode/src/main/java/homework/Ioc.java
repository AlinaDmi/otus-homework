package homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    private Ioc() {
    }

    static CalculatorInterface createCalculator() {
        InvocationHandler handler = new CalculatorInvocationHandler(new Calculator());
        return (CalculatorInterface)
                Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{CalculatorInterface.class}, handler);
    }

    static class CalculatorInvocationHandler implements InvocationHandler {
        private final Calculator calculator;
        private final List<Method> loggingMethods;

        CalculatorInvocationHandler(Calculator calculator) {
            this.calculator = calculator;
            this.loggingMethods = new ArrayList<>(Arrays
                    .stream(calculator.getClass().getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(Log.class))
                    .toList());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (isMethodForLogging(method, method.getParameterTypes())) {
                logger.info("executing method: {}, params: {}", method.getName(), Arrays.stream(args).map(Object::toString)
                        .collect(Collectors.joining(", ")));
            }
            return method.invoke(calculator, args);
        }

        private boolean isMethodForLogging(Method method, Class<?>[] parameters) {
            for (Method logMethod : loggingMethods) {
                if (!method.getName().equals(logMethod.getName()) || !method.getReturnType().equals(logMethod.getReturnType())) {
                    continue;
                }
                if (equalParamTypes(logMethod.getParameterTypes(), parameters)){
                    return true;
                }

            }
            return false;
        }

        boolean equalParamTypes(Class<?>[] params1, Class<?>[] params2) {
            if (params1.length == params2.length) {
                for (int i = 0; i < params1.length; i++) {
                    if (params1[i] != params2[i])
                        return false;
                }
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" + "myClass=" + calculator + '}';
        }
    }

}
