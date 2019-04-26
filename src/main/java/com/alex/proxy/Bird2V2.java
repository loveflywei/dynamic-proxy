package com.alex.proxy;

/**
 * @Title: Bird2V2
 * @ProjectName proxy-pattern
 * @Description: TODO
 * @Author jiangwei121
 * @Date 2019/4/2415:31
 */
public class Bird2V2 extends Bird2{

    @Override
    public void fly(){
        System.out.println("Bird fly start...");
        super.fly();
        System.out.println("Bird fly end...");
    }

    public static void main(String[] args) {
        Bird2V2 bird2V2 = new Bird2V2();
        bird2V2.fly();
    }
}
