package com.awei.dp.observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverDemo {

}

//事件原对象
class Child {

    List<Observer> observers = new ArrayList<>();

    {
        observers.add(new Father());
        observers.add(new Mom());
    }

    public void wakeUp() {
        WakeUpEvent wakeUpEvent = new WakeUpEvent(100l,"bed",this);
        for (Observer observer : observers) {
            observer.actionOnWakeUp(wakeUpEvent);
        }
    }
}

//监听类
interface Observer {
    void actionOnWakeUp(WakeUpEvent event);
}

abstract class Event<T> {
    abstract T getSource();
}

//具体事件类
class WakeUpEvent extends Event {
    long time;
    String location;
    Child source;

    public WakeUpEvent(long time, String location, Child source) {
        this.time = time;
        this.location = location;
        this.source = source;
    }

    @Override
    Child getSource() {
        return source;
    }
}

class Father implements Observer {

    @Override
    public void actionOnWakeUp(WakeUpEvent event) {
        feed();
    }

    public void feed() {
        System.out.println("feeding.......");
    }
}

class Mom implements Observer {

    @Override
    public void actionOnWakeUp(WakeUpEvent event) {
        //可以用事件中的条件判断写具体逻辑
        hug();
    }

    public void hug() {
        System.out.println("huging.......");
    }
}
