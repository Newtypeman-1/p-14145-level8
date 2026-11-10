package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;
import java.util.Optional;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    public WiseSaying write(String line, String auth) {
        return wiseSayingRepository.save(new WiseSaying(0, line, auth));
    }

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }

    public Optional<WiseSaying> findById(int num) {
        return wiseSayingRepository.findById(num);
    }

    public WiseSaying modify(WiseSaying wiseSaying, String line, String auth) {
        return wiseSayingRepository.save(new WiseSaying(wiseSaying.num(), line, auth));
    }

    public boolean delete(int num) {
        return wiseSayingRepository.deleteById(num);
    }

    public boolean isEmpty() {
        return wiseSayingRepository.isEmpty();
    }
}
