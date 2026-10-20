package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int idx = 1;
        Scanner scan = new Scanner(System.in);
        List<WiseSaying> list = new ArrayList<>();

        System.out.println("== 명언 앱 ==");
        while(true){
            System.out.print("명령) ");
            String answer = scan.nextLine();

            if(answer.equals("등록")){
                System.out.print("명언: ");
                String line = scan.nextLine();
                System.out.print("작가: ");
                String auth = scan.nextLine();

                WiseSaying wise = new WiseSaying(idx, line, auth);
                list.add(wise);
                System.out.println(idx + "번 명언이 등록되었습니다.");
                idx++;
            }else if(answer.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("--------------------------");

                if(list.isEmpty()){
                    System.out.println("비어 있음");
                }

                for(int i = list.size(); i > 0; i--){
                    WiseSaying wise = list.get(i - 1);
                    System.out.println(wise.getNum() + " / " + wise.getAuth() + " / " + wise.getLine());
                }
            }else if(answer.startsWith("삭제?id=")){
                if(list.isEmpty()){
                    System.out.println("삭제할 명언이 없습니다.");
                }else{
                    try {
                        String answerSplit = answer.split("=")[1];
                        int num = Integer.parseInt(answerSplit);
                        boolean isFound = false;

                        for (WiseSaying wise : list) {
                            if (wise.getNum() == num) {
                                list.remove(wise);
                                System.out.println(num + "번 명언이 삭제되었습니다.");
                                isFound = true;
                                break;
                            }
                        }

                        if (!isFound) {
                            System.out.println(num + "번 명언은 존재하지 않습니다.");
                        }
                    } catch(NumberFormatException e){
                        System.out.println("번호만 입력하세요.");
                    }
                }
            }else if(answer.startsWith("수정?id=")){
                if(list.isEmpty()) {
                    System.out.println("수정할 명언이 없습니다.");
                }else{
                    try{
                        String answerSplit = answer.split("=")[1];
                        int num = Integer.parseInt(answerSplit);
                        boolean isFound = false;

                        for(int i = 0; i < list.size(); i++){
                            WiseSaying wise = list.get(i);

                            if(wise.getNum() == num){
                                System.out.println("기존 명언: " + wise.getLine());
                                System.out.print("새 명언: ");
                                wise.setLine(scan.nextLine());

                                System.out.println("기존 작가: " + wise.getAuth());
                                System.out.print("새 작가: ");
                                wise.setAuth(scan.nextLine());

                                System.out.println(num + "번 명언이 수정되었습니다.");
                                isFound = true;
                                break;
                            }
                        }

                        if (!isFound) {
                            System.out.println(num + "번 명언은 존재하지 않습니다.");
                        }
                    }catch (NumberFormatException e){
                        System.out.println("번호만 입력하세요.");
                    }
                }
            }else if(answer.equals("종료")){
                System.out.println("시스템을 종료합니다.");
                break;
            }else{
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}

class WiseSaying{
    private int num;
    private String line;
    private String auth;

    WiseSaying(){}
    WiseSaying(int num, String line, String auth){
        this.num = num;
        this.line = line;
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

class Rq{
    private String action;
    private String param;

    Rq() {}
    Rq(String answer){
        this.param = 
        this.action = answer.substring(0,1);
    }

    String getAction(){
        return action;
    }

    int getParam(){
        return param;
    }



}