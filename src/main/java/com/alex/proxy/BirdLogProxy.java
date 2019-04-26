package com.alex.proxy;

/**
 * @Title: BirdLogProxy
 * @ProjectName proxy-pattern
 * @Description: TODO
 * @Author jiangwei121
 * @Date 2019/4/2415:48
 */
public class BirdLogProxy implements Flyable {
    private Flyable flyable;

    public BirdLogProxy(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        System.out.println("Bird fly start...");

        flyable.fly();

        System.out.println("Bird fly end...");
    }

    public static void main(String[] args) {
        Bird bird = new Bird();
        BirdLogProxy p1 = new BirdLogProxy(bird);
        BirdTimeProxy p2 = new BirdTimeProxy(p1);
        p2.fly();
    }
}

