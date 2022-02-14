package listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class annotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        //annotation.setDataProvider("testRegistration");
        annotation.setRetryAnalyzer(retryFailedTests.class);
        //annotation.setDataProvider("testDta");
    }

    public void transform(IConfigurationAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
    }

    public void transform(IDataProviderAnnotation annotation, Method method) {
    }

    public void transform(IFactoryAnnotation annotation, Method method) {
    }

    public void transform(IListenersAnnotation annotation, Class<?> testClass) {
    }
}