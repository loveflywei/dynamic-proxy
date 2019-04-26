package com.alex.proxy;

/**
 * @Title: Bird3
 * @ProjectName proxy-pattern
 * @Description: TODO
 * @Author jiangwei121
 * @Date 2019/4/2415:07
 */
public class Bird3 implements Flyable {

    private Bird bird;

    public Bird3(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void fly() {
        long start = System.currentTimeMillis();

        bird.fly();

        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));

    }

    public static void main(String[] args) {
        Bird bird = new Bird();
        Bird3 bird3 = new Bird3(bird);
        bird3.fly();
    }
}
