package com.example.happycodinggirl.testviewstub;

/**
 * Created by happycodinggirl on 2015/7/25.
 */
public class ItemEntity {

    public String name;

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public long getCreateTime() {

        return createTime;
    }

    public long createTime;
}
