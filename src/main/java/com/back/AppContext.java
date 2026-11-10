package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

// 앱 전체에서 공유하는 객체를 한 번만 생성해 보관한다.
public class AppContext {
    public static final Scanner scanner = new Scanner(System.in);

    public static final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();
    public static final WiseSayingService wiseSayingService = new WiseSayingService(wiseSayingRepository);

    public static final SystemController systemController = new SystemController();
    public static final WiseSayingController wiseSayingController =
            new WiseSayingController(scanner, wiseSayingService);

    private AppContext() {
    }
}
