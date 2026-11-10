package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

// 명령을 입력받아 알맞은 컨트롤러로 넘기기만 한다.
public class App {
    private final Scanner scanner = AppContext.scanner;
    private final SystemController systemController = AppContext.systemController;
    private final WiseSayingController wiseSayingController = AppContext.wiseSayingController;

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            Rq rq = new Rq(scanner.nextLine().trim());

            switch (rq.getActionName()) {
                case "등록" -> wiseSayingController.actionWrite();
                case "목록" -> wiseSayingController.actionList();
                case "수정" -> wiseSayingController.actionModify(rq);
                case "삭제" -> wiseSayingController.actionDelete(rq);
                case "종료" -> {
                    systemController.actionExit();
                    return;
                }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
