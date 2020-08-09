package com.awei.dp.factory.simpleFactory;

/**
 * plan的工厂
 */
public class PlanFactory {

    /**
     * 此处做创建对象前后的逻辑处理
     * @return
     */
    public Moveable createPlan() {
        System.out.println("Plan created");
        return new Plan();
    }
}
