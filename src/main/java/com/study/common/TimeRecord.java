package com.study.common;

public class TimeRecord {

    private long recordStart;

    public TimeRecord() {
        this.recordStart = System.currentTimeMillis();
    }

    public void recordStop(String name) {
        System.out.println(name + ":" + (System.currentTimeMillis() - recordStart) + "ms");
    }

    public void setRecordStart(long recordStart) {
        this.recordStart = recordStart;
    }

}
