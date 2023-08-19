package com.gara.self.spring;

import java.lang.annotation.*;

/**
 * @author GARA
 * @description TODO
 * @date 2023/8/6 15:49
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ComponentScan {

    String value() default "";

}
