package com.wtd.designmodel.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

    /**
     * 通过Cglib来实现动态代理
     */
    public class CglibProxyTest {

        public static void main(String[] args) {
            CEO ceo = new CEO();
            CEO ceoProxy = (CEO) getCglibProxy(ceo);
            ceoProxy.sign();
        }

        private static Object getCglibProxy(Object target){
            //创建增强器对象
            Enhancer enhancer = new Enhancer();
            //为增强器设置类加载器
            enhancer.setClassLoader(target.getClass().getClassLoader());
            //为增强器设置父类
            enhancer.setSuperclass(target.getClass());
            //设置回调函数
            enhancer.setCallback(new InvocationHandler() {
                @Override
                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    System.out.println("方法调用前");
                    Object result = method.invoke(target,objects);
                    System.out.println("方法调用后");
                    return result;
                }
            });
            return enhancer.create();
        }
    }
