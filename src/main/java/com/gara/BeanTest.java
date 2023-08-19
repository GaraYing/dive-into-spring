package com.gara;

import com.gara.bean.TestBean;
import com.gara.bean.TestDao;
import com.gara.config.BeanConfig;
import com.gara.config.DataSourceConfig;
import com.gara.service.UserService;
import com.gara.service.impl.UserAccountServiceImpl;
import com.gara.service.impl.UserServiceImpl;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class BeanTest {

    public static void main(String[] args) {

        // 基于xml文件实现
        ApplicationContext context = new ClassPathXmlApplicationContext("app.xml");

        TestBean testBean = context.getBean("testBean", TestBean.class);
        BeanConfig beanConfig = context.getBean("beanConfig", BeanConfig.class);

        System.out.println("ClassPathXmlApplicationContext: " +  beanConfig);
        System.out.println("ClassPathXmlApplicationContext: " +  testBean);
        System.out.println("ClassPathXmlApplicationContext: " +  testBean.getName());
        System.out.println("ClassPathXmlApplicationContext: " + testBean.getTestDao());

        System.out.println("======================");

        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();

        new XmlBeanDefinitionReader(genericApplicationContext).loadBeanDefinitions("app.xml");


        genericApplicationContext.refresh();

        System.out.println("GenericApplicationContext: " + testBean);
        System.out.println("GenericApplicationContext: " + testBean.getName());
        // System.out.println("GenericApplicationContext: " + testBean.getTestDao());

        TestDao testDao2 = genericApplicationContext.getBean("testDao2", TestDao.class);

        System.out.println("testDao: " +  testBean.getTestDao());
        System.out.println("testDao2: " +  testDao2);

        System.out.println("testBean.getTestDao() == testDao2: " + (testBean.getTestDao() == testDao2));

//        BeanConfig beanConfig = genericApplicationContext.getBeanFactory().createBean(BeanConfig.class);
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.scan("com.gara");
        annotationConfigApplicationContext.register(BeanConfig.class);


        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(annotationConfigApplicationContext);
        annotatedBeanDefinitionReader.register(DataSourceConfig.class);
        System.out.println("annotationConfigApplicationContext.getBean(DataSourceConfig.class) = " + annotationConfigApplicationContext.getBean(DataSourceConfig.class));

        annotationConfigApplicationContext.refresh();

        System.out.println("annotationConfigApplicationContext: " + annotationConfigApplicationContext.getBean("beanConfig"));

//        Object userService = annotationConfigApplicationContext.getBean("userService");
        UserService userService = annotationConfigApplicationContext.getBean("userServiceImpl", UserServiceImpl.class);
        UserService userAccountService = annotationConfigApplicationContext.getBean("userService", UserAccountServiceImpl.class);
        TestDao test = annotationConfigApplicationContext.getBean("test", TestDao.class);
        System.out.println("test: " +  test);

        userService.queryUser(1L);
        userAccountService.queryAccount(2L);

        genericApplicationContext.close();
        annotationConfigApplicationContext.close();
    }
}
