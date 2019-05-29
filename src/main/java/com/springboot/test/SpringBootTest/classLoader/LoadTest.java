package com.springboot.test.SpringBootTest.classLoader;

import com.springboot.test.SpringBootTest.designModel.factory.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller()
@Scope("singleton")
public class LoadTest {

    private Factory factory;

    public static void main(String[] args) {
        ClassLoader loader = LoadTest.class.getClassLoader();
        System.out.println(loader);
        ClassLoader parent1 = loader.getParent();
        System.out.println(parent1);

        ClassLoader parent2 = parent1.getParent();
        System.out.println(parent2);
    }
}
