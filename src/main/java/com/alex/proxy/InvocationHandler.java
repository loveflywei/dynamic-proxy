package com.alex.proxy;

import java.lang.reflect.Method;

/**
 * @Title: InvocationHandler
 * @ProjectName proxy-pattern
 * @Description: TODO
 * @Author jiangwei121
 * @Date 2019/4/2516:15
 */
public interface InvocationHandler {
    void invoke(Object proxy, Method method,Object[] args);
}
