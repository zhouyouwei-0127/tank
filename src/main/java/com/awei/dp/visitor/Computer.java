package com.awei.dp.visitor;

/**
 * 假设电脑的组件不再发生改变
 * 需求：对不同的客户组装电脑使用不同的优惠策略
 */
public class Computer {
    ComputerPart cpu = new Cpu();
    ComputerPart board = new Board();
    ComputerPart memory = new Memory();

    public void accept(Visitor visitor) {
        cpu.accept(visitor);
        board.accept(visitor);
        memory.accept(visitor);
    }
}

abstract class ComputerPart {
    abstract void accept(Visitor visitor);
    abstract double getPrice();
}

class Cpu extends ComputerPart {

    @Override
    void accept(Visitor visitor) {
        visitor.visitCpu(this);
    }

    @Override
    double getPrice() {
        return 0;
    }

}

class Memory extends ComputerPart {

    @Override
    void accept(Visitor visitor) {
        visitor.visitMemory(this);
    }

    @Override
    double getPrice() {
        return 300;
    }
}

class Board extends ComputerPart {

    @Override
    void accept(Visitor visitor) {
        visitor.visitBoard(this);
    }

    @Override
    double getPrice() {
        return 200;
    }
}

interface Visitor {
    void visitCpu(Cpu cpu);
    void visitMemory(Memory memory);
    void visitBoard(Board board);
}

class PersonelVisitor implements Visitor {

    private double totalPrice = 0.0;

    @Override
    public void visitCpu(Cpu cpu) {
        totalPrice += cpu.getPrice() * 0.9;
    }

    @Override
    public void visitMemory(Memory memory) {
        totalPrice += memory.getPrice() * 0.9;
    }

    @Override
    public void visitBoard(Board board) {
        totalPrice += board.getPrice() * 0.9;
    }
}

class corpVisitor implements Visitor {

    private double totalPrice = 0.0;

    @Override
    public void visitCpu(Cpu cpu) {
        totalPrice += cpu.getPrice() * 0.7;
    }

    @Override
    public void visitMemory(Memory memory) {
        totalPrice += memory.getPrice() * 0.7;
    }

    @Override
    public void visitBoard(Board board) {
        totalPrice += board.getPrice() * 0.7;
    }
}