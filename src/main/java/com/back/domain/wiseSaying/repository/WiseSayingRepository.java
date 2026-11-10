package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastNum = 0;

    // 번호가 없으면(0) 새로 등록, 있으면 같은 번호의 명언을 교체
    public WiseSaying save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            WiseSaying saved = wiseSaying.withNum(++lastNum);
            wiseSayings.add(saved);
            return saved;
        }

        IntStream.range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).num() == wiseSaying.num())
                .findFirst()
                .ifPresent(i -> wiseSayings.set(i, wiseSaying));

        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return List.copyOf(wiseSayings);
    }

    public Optional<WiseSaying> findById(int num) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.num() == num)
                .findFirst();
    }

    public boolean deleteById(int num) {
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.num() == num);
    }

    public boolean isEmpty() {
        return wiseSayings.isEmpty();
    }
}
