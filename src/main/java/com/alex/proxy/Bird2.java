package com.alex.proxy;

/**
 * @Title: Bird2
 * @ProjectName proxy-pattern
 * @Description: TODO
 * @Author jiangwei121
 * @Date 2019/4/2415:10
 */
public class Bird2 extends Bird {

    @Override
    public void fly(){
        long start = System.currentTimeMillis();

        super.fly();

        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));

    }

    public static void main(String[] args) {
        Bird2 bird2 = new Bird2();
        bird2.fly();
    }
}
