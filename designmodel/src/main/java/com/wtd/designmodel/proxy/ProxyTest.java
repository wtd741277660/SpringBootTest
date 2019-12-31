package com.wtd.designmodel.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试
 */
public class ProxyTest {

    public static void main(String[] args) {
        //委托类
        CEO ceo = new CEO();
        try {
            //根据委托类的类信息，动态生成代理类
            Leader leader = (Leader) getProxy1(ceo);
            //调用代理类的方法
            leader.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object getProxy(Object target) throws Exception {
        /**
         * 第一个参数：类加载器，随便一个类加载器
         * 第二个参数：委托类所实现的接口数组，让代理类也实现相同的接口
         */
        Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(),
                                                target.getClass().getInterfaces());
        //获取代理类的构造器
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);

        //通过构造器创建具体的实例对象
        Object o = constructor.newInstance(new InvocationHandler() {
            //通过该方式获取的实例对象，不管调用哪个接口方法，都会先访问invoke，然后通过反射调用实际的方法
            //该方法相当于静态代理中代理类的方法体，可在这里实现对委托类的代码增强等功能
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("方法执行前");
                //通过反射调用方法的对象
                //调用的是委托类的方法，所以这里必须用target，而不是proxy
                Object result = method.invoke(target, args);
                System.out.println("方法执行后");
                return null;
            }
        });
        return o;
    }

    private static Object getProxy1(Object target) throws Exception {
        //在实际开发的过程，一般使用Proxy.newProxyInstance来获取代理对象，省略的上面的具体创建过程
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("方法执行前");
                    Object result = method.invoke(target, args);
                    System.out.println("方法执行后");
                    return result;
                });
        return o;
    }
}
