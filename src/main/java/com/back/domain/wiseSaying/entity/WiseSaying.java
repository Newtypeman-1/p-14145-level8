package com.back.domain.wiseSaying.entity;

public record WiseSaying(int num, String line, String auth) {
    public boolean isNew() {
        return num == 0;
    }

    public WiseSaying withNum(int num) {
        return new WiseSaying(num, line, auth);
    }
}
