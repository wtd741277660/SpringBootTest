package com.springboot.test.SpringBootTest;

/**
 * 静态代理的代理类，接受委托类的代理
 */
public class Assistant implements Leader{

    /**
     * 委托类，代理类中包含委托类的实例，可以直接操控委托类
     * 可接受任何实现了Leader接口的委托类
     */
    private Leader leader;

    public Assistant(Leader leader){
        this.leader = leader;
    }

    @Override
    public void sign() {
        System.out.println("收到文件，递给领导签字");
        leader.sign();
        System.out.println("领导签字完成，再将文件传回去");
    }
}
