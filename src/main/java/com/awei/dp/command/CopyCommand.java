package com.awei.dp.command;

public class CopyCommand extends Command {
    Content c;

    public CopyCommand(Content c) {
        this.c = c;
    }

    @Override
    void doIt() {
        c.msg += c.msg;
    }

    @Override
    void undo() {
        c.msg = c.msg.substring(0,c.msg.length()/2);
    }
}
