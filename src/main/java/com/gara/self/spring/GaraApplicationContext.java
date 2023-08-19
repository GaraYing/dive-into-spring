package com.gara.self.spring;

import com.gara.self.service.GaraBeanDefinition;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GARA
 * @description TODO
 * @date 2023/8/6 15:45
 */
public class GaraApplicationContext {

    private final Class configClass;

    private Map<String, GaraBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new LinkedList<>();

    public GaraApplicationContext(Class configClass) {
        this.configClass = configClass;

        scan(configClass);

        beanDefinitionMap.forEach((beanName, beanDefinition)->{
            if ("singleton".equals(beanDefinition.getScope())) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName,bean);
            }
        });

    }

    private Object createBean(String beanName, GaraBeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();
        Object instance = null;
        try {
            instance = clazz.getConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    field.set(instance, getBean(field.getName()));
                }
            }
            // 初始化处理
            if (instance instanceof InitializingBean) {
                ((InitializingBean)instance).afterPropertiesSet();
            }
            // AOP底层基于BeanPostProcessor实现
            // postProcessBeforeInitialization
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }
            // postProcessAfterInitialization
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }


    public Object getBean(String beanName){

        if (!beanDefinitionMap.containsKey(beanName)){
            throw new NullPointerException("class not found");
        }
        GaraBeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        String scope = beanDefinition.getScope();
        if ("singleton".equals(scope)){
            Object singletonBean = singletonObjects.get(beanName);
            if(singletonBean == null){
                singletonBean = createBean(beanName,beanDefinition);
                singletonObjects.put(beanName, singletonBean);
            }
            return singletonBean;
        }else {
            // protoType
            Object bean = this.createBean(beanName, beanDefinition);
            return bean;

        }
    }

    private void scan(Class configClass) {
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = componentScanAnnotation.value();
            System.out.println(path);

            path = path.replace(".", "/");
            ClassLoader classLoader = GaraApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());

            if (file.isDirectory()){
                for (File f : file.listFiles()) {
                    String absolutePath = f.getAbsolutePath();
                    System.out.println(absolutePath);
                    if (!f.isFile()){
                        continue;
                    }
                    String classPath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"))
                            .replace("\\", ".");
                    try {
                        Class<?> aClass = classLoader.loadClass(classPath);

                        if (aClass.isAnnotationPresent(Component.class)){

                            if (BeanPostProcessor.class.isAssignableFrom(aClass)){
                                BeanPostProcessor  beanPostProcessor =   (BeanPostProcessor) aClass.getConstructor().newInstance();
                                beanPostProcessorList.add(beanPostProcessor);
                            }

                            Component component = aClass.getAnnotation(Component.class);
                            String beanName = component.value();
                            if ("".equals(beanName)){
                                beanName = Introspector.decapitalize(aClass.getSimpleName());
                            }
                            GaraBeanDefinition beanDefinition = new GaraBeanDefinition();
                            beanDefinition.setType(aClass);
                            if (aClass.isAnnotationPresent(Scope.class)) {
                                Scope scope = aClass.getAnnotation(Scope.class);
                                String value = scope.value();
                                beanDefinition.setScope(value);
                            }else {
                                // 单例
                                beanDefinition.setScope("singleton");
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
