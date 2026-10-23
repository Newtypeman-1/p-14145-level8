package com.back;

import java.util.*;

public class Main {
    static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        List<WiseSaying> list = new ArrayList<>();
        int idx = 1;

        System.out.println("== 명언 앱 ==");
        while (true){
            System.out.print("명령) ");
            String cmd = scan.nextLine().trim();
            Rq rq = new Rq(cmd);
            switch (rq.getActionName()){
                case "등록" -> {
                    System.out.print("명언: ");
                    String line = scan.nextLine().trim();
                    System.out.print("작가: ");
                    String auth = scan.nextLine().trim();

                    WiseSaying wise = new WiseSaying(idx, line, auth);
                    list.add(wise);
                    System.out.println(idx + "번 명언이 등록되었습니다.");
                    idx++;
                }
                case "목록" -> {
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("--------------------------");

                    if(list.isEmpty()){
                        System.out.println("비어 있음");
                    }

                    list.forEach(wise ->
                            System.out.printf("%d / %s / %s\n"
                                    .formatted(wise.num(), wise.auth(), wise.line()))
                    );
                }
                case "수정" -> {
                    if(list.isEmpty()) System.out.println("수정할 명언이 없습니다.");
                    else{
                        int i = rq.getParamAsInt("id", -1);
                        if(i < 0){
                            System.out.println("수정할 명언의 번호를 정확히 입력하세요.");
                            break;
                        }

                        int listIndexOfWise = IntStream
                                .range(0, list.size())
                                .filter(item -> list.get(item).num() == i)
                                .findAny()
                                .orElse(-1);

                        if(listIndexOfWise < 0)
                            System.out.printf("%d번 명언이 존재하지 않습니다.\n"
                                    .formatted(i));
                        else{
                            WiseSaying wise = list.get(listIndexOfWise);

                            System.out.println("기존 명언: " + wise.line());
                            System.out.print("새 명언: ");
                            String line = scan.nextLine().trim();
                            System.out.println("기존 작가: " + wise.auth());
                            System.out.print("새 작가: ");
                            String auth = scan.nextLine().trim();

                            WiseSaying newWise = new WiseSaying(wise.num(), line, auth);
                            list.set(listIndexOfWise, newWise);
                            System.out.printf("%d번 명언이 수정되었습니다.\n"
                                    .formatted(i));
                        }
                    }
                }
                case "삭제" -> {
                    if(list.isEmpty()) System.out.println("삭제할 명언이 없습니다.");
                    else{
                        int i = rq.getParamAsInt("id", -1);
                        boolean isFound = list.removeIf(
                                wise -> wise.num() == i);
                        if(!isFound) System.out.printf("%d번 명언이 존재하지 않습니다.\n"
                                .formatted(i));
                        else System.out.printf("%d번 명언을 삭제했습니다.\n"
                                .formatted(i));
                    }
                }
                case "종료" -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default ->
                        System.out.println("잘못된 입력입니다.");
            }
        }
    }
}