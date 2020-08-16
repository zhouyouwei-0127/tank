package com.awei.dp.command;

public class InsertCommand extends Command {

    Content c;
    String insertStr = "hello insert";

    public InsertCommand(Content c) {
        this.c = c;
    }

    @Override
    void doIt() {
        c.msg += insertStr;
    }

    @Override
    void undo() {
        c.msg = c.msg.substring(0,c.msg.length() - insertStr.length());
    }
}
