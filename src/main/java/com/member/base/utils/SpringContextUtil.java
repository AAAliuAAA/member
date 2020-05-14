package com.member.base.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private static Log logger = LogFactory.getLog(SpringContextUtil.class);

    public SpringContextUtil() {
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据bean id 获取组件
     * @param id
     * @return
     */
    public static Object getBeanById(String id) {
        if (applicationContext == null) {
            logger.error("未加载ApplicationContext");
            return null;
        } else {
            return applicationContext.getBean(id);
        }
    }

    /**
     * 根据 class 获取唯一的组件
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getBeanByClass(Class<T> c) {
        if (applicationContext == null) {
            logger.error("未加载ApplicationContext");
            return null;
        } else {
            return applicationContext.getBean(c);
        }
    }

    /**
     * 根据 class 获取 bean Map 适用于 一个class多个bean实例的情况
     * @param c
     * @return
     */
    public static Map getBeansByClass(Class c) {
        if (applicationContext == null) {
            logger.error("未加载ApplicationContext");
            return null;
        } else {
            return applicationContext.getBeansOfType(c);
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
