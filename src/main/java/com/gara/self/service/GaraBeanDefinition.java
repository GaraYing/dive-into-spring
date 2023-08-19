package com.gara.self.service;

/**
 * @author GARA
 * @description TODO
 * @date 2023/8/6 16:19
 */
public class GaraBeanDefinition {

    private Class type;

    /**
     * 作用域： 单例/原型
     */
    private String scope;
    /**
     * 是否懒加载
     */
    private Boolean isLazy;

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Boolean getLazy() {
        return isLazy;
    }

    public void setLazy(Boolean lazy) {
        isLazy = lazy;
    }
}
