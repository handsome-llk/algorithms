package com.study.common;

public class TimeRecord {

    private long recordStart;
    private long recordEnd;

    public TimeRecord() {
        this.recordStart = System.currentTimeMillis();
    }

    public void recordStop(String name) {
        recordEnd = System.currentTimeMillis();
        System.out.println(name + ":" + (recordEnd - recordStart) + "ms");
        setRecordStart(recordEnd);
    }

    public void recordStop() {
        recordEnd = System.currentTimeMillis();
    }

    public void timeCon(String name) {
        System.out.println(name + ":" + (recordEnd - recordStart) + "ms");
    }

    public void setRecordStart(long recordStart) {
        this.recordStart = recordStart;
    }

    public long timeCon() {
        return recordEnd - recordStart;
    }

}
