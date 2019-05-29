package com.springboot.test.SpringBootTest.designModel.factory;

public class ProductTest {

    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        Product product = factory.createProduct(ConcreteProduct1.class);
        product.method();
    }
}
