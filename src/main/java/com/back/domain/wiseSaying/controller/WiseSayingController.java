package com.back.domain.wiseSaying.controller;

import com.back.Rq;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

// 입력(Scanner)과 출력(System.out)은 컨트롤러에서만 처리한다.
public class WiseSayingController {
    private final Scanner scanner;
    private final WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner scanner, WiseSayingService wiseSayingService) {
        this.scanner = scanner;
        this.wiseSayingService = wiseSayingService;
    }

    public void actionWrite() {
        String line = prompt("명언: ");
        String auth = prompt("작가: ");

        WiseSaying wiseSaying = wiseSayingService.write(line, auth);
        System.out.println(wiseSaying.num() + "번 명언이 등록되었습니다.");
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("--------------------------");

        List<WiseSaying> wiseSayings = wiseSayingService.findAll();

        if (wiseSayings.isEmpty()) {
            System.out.println("비어 있음");
        }

        wiseSayings.forEach(wiseSaying ->
                System.out.printf("%d / %s / %s\n",
                        wiseSaying.num(), wiseSaying.auth(), wiseSaying.line())
        );
    }

    public void actionModify(Rq rq) {
        if (wiseSayingService.isEmpty()) {
            System.out.println("수정할 명언이 없습니다.");
            return;
        }

        int id = rq.getParamAsInt("id", -1);
        if (id < 0) {
            System.out.println("수정할 명언의 번호를 정확히 입력하세요.");
            return;
        }

        Optional<WiseSaying> opWiseSaying = wiseSayingService.findById(id);
        if (opWiseSaying.isEmpty()) {
            System.out.printf("%d번 명언이 존재하지 않습니다.\n", id);
            return;
        }

        WiseSaying wiseSaying = opWiseSaying.get();

        System.out.println("기존 명언: " + wiseSaying.line());
        String line = prompt("새 명언: ");
        System.out.println("기존 작가: " + wiseSaying.auth());
        String auth = prompt("새 작가: ");

        wiseSayingService.modify(wiseSaying, line, auth);
        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }

    public void actionDelete(Rq rq) {
        if (wiseSayingService.isEmpty()) {
            System.out.println("삭제할 명언이 없습니다.");
            return;
        }

        int id = rq.getParamAsInt("id", -1);
        if (id < 0) {
            System.out.println("삭제할 명언의 번호를 정확히 입력하세요.");
            return;
        }

        if (wiseSayingService.delete(id)) {
            System.out.printf("%d번 명언을 삭제했습니다.\n", id);
        } else {
            System.out.printf("%d번 명언이 존재하지 않습니다.\n", id);
        }
    }

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
