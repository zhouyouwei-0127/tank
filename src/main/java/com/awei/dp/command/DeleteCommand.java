package com.awei.dp.command;

public class DeleteCommand extends Command {

    Content c;
    String deleteStr;

    public DeleteCommand(Content c) {
        this.c = c;
    }

    @Override
    void doIt() {
        deleteStr = c.msg.substring(0,3);
        c.msg = c.msg.substring(3,c.msg.length());
    }

    @Override
    void undo() {
        c.msg = deleteStr + c.msg;
    }
}
