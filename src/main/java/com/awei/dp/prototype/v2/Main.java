package com.awei.dp.prototype.v2;

/**
 * 深克隆-克隆出来的引用类型不会对被克隆对象有影响
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person();
        Person p2 = (Person)p1.clone();
        System.out.println(p1 == p2);
        System.out.println(p2.age + "\t" + p2.score);

        //在p1的location改掉时，p2的location也会改，这就说明复制的时候引用类型复制的是内存地址的引用
        p1.location.street = "马甸桥";
        System.out.println(p2.location.street);
    }
}

/**
 * 一个雷想要被克隆，就必须实现Cloneable接口，并且重写clone方法
 */
class Person implements Cloneable {
    int age = 8;
    int score = 80;
    Location location = new Location("知春路",3);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person p = (Person)super.clone();
        p.location = (Location) p.location.clone();
        return p;
    }
}

class Location implements Cloneable {
    String street;
    int roomNo;

    public Location(String street, int roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", roomNo=" + roomNo +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
