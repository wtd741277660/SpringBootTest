package com.springboot.test.SpringBootTest.designModel.strategy;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();
//        client.normal();
        client.enumMethod();
    }

    public void normal(){
        IStrategy strategy = new ConcreteStrategy1();
        Context context = new Context(strategy);
        context.doAnyThing();
    }

    public void enumMethod(){
        int num = StarategyEnum.Add.exec(2,3);
        System.out.println(num);
    }
}
