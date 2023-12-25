package com.video.enums;

public enum VIPEnum {

    VIP1(1,1),
    VIP2(2,3),
    VIP3(3,6),
    VIP4(4,10);

    private int level;
    private int count;

    VIPEnum(int level, int count){
        this.level = level;
        this.count = count;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static int getLevelByCount(int count){
        VIPEnum[] values = VIPEnum.values();
        int level=0;
        for (VIPEnum enu : values) {
            if(enu.getCount() <= count){
                level = enu.getLevel();
                continue;
            }
        }
        return level;
    }
}
