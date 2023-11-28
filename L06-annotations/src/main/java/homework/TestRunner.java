package homework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class TestRunner {
    public TestRunner(Class<?> clazz) {
        doTests(clazz);
    }

    private void doTests(Class<?> clazz) {
        List<Method> beforeMethods = parse(clazz, Before.class);
        List<Method> testMethods = parse(clazz, Test.class);
        List<Method> afterMethods = parse(clazz, After.class);

        int failed = run(testMethods, beforeMethods, afterMethods);
        writeResult(failed, testMethods.size());
    }

    private List<Method> parse(Class<?> clazz, Class annotationClass) {
        List<Method> result = new ArrayList<>();
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                result.add(method);
                continue;
            }
        }
        return result;
    }

    private int run(List<Method> methods, List<Method> before, List<Method> after) {
        int failed = 0;
        for (Method method : methods) {
            boolean isFailed = runTest(method, before, after);
            if (isFailed){
                failed++;
            }
        }
        return failed;
    }

    private boolean runTest(Method method, List<Method> before, List<Method> after) {
        boolean isFailed = false;
        OperationsTest currentInstance = new OperationsTest();

        try {
            runBeforeAfter(currentInstance, before);
            method.invoke(currentInstance);
        } catch (Exception e) {
            isFailed = true;
        }

        try {
            runBeforeAfter(currentInstance, after);
        } catch (Exception ignored) {
        }

        return isFailed;
    }

    private void runBeforeAfter(OperationsTest currentInstance, List<Method> methods) {
        for (Method method : methods) {
            try {
                method.invoke(currentInstance);
            } catch (Exception e) {
                System.out.println("Error while preparing tests");
            }
        }
    }

    private void writeResult(int failed, int all) {
        System.out.println("--------------------------");
        System.out.println("Total test: " + all);
        System.out.println("Successful test: " + (all - failed));
        System.out.println("Failed test: " + failed);
    }

}
