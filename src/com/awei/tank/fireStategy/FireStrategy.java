package com.awei.tank.fireStategy;

import com.awei.tank.Tank;

import java.io.Serializable;

/**
 * 开火策略接口
 */
public interface FireStrategy extends Serializable {
    void fire(Tank tank);
}
