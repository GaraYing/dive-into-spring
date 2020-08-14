package com.gara.bean;

public class TestBean {

    public TestBean() {
    }

    public TestBean(TestDao testDao, String name) {
        this.testDao = testDao;
        this.name = name;
    }

    private TestDao testDao;

    private String name;

    public TestDao getTestDao() {
        return testDao;
    }

    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init(){
        System.out.println("init() called +++++++++");
        this.setName("1212");
    }
}
