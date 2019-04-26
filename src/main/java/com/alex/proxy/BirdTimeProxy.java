package com.alex.proxy;

/**
 * @Title: BirdTimeProxy
 * @ProjectName proxy-pattern
 * @Description: TODO
 * @Author jiangwei121
 * @Date 2019/4/2415:51
 */
public class BirdTimeProxy implements Flyable {
    private Flyable flyable;

    public BirdTimeProxy(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        long start = System.currentTimeMillis();

        flyable.fly();

        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));

    }

    public static void main(String[] args) {
        Bird bird = new Bird();
        BirdTimeProxy p2 = new BirdTimeProxy(bird);
        BirdLogProxy p1 = new BirdLogProxy(p2);
        p1.fly();
    }


}
