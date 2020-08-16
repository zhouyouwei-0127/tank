package com.awei.dp.bridge;

/**
 * 需要送礼物（Gift），礼物分为两种-WarmGift、WildGift
 * GiftImpl为礼物的实现 也分为两种 Book、Flower
 * 在Gift中聚合GiftImpl，这样每次new Gift的时候就可以直接指定具体礼物
 */
public class GG {
    public static void main(String[] args) {
        Gift g = new WarmGift(new Flower());
    }
}
