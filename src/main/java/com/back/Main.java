package com.back;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int idx = 1;
        Scanner scan = new Scanner(System.in);
        List<WiseSaying> list = new ArrayList<>();

        System.out.println("== 명언 앱 ==");
        while(true){
            System.out.print("명령) ");
            String answer = scan.nextLine().trim();
            Rq rq = new Rq(answer);

            switch(rq.getActionName()){
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

                    for(int i = list.size(); i > 0; i--){
                        WiseSaying wise = list.get(i - 1);
                        System.out.println(wise.getNum() + " / " + wise.getAuth() + " / " + wise.getLine());
                    }
                }
                case "삭제" -> {
                    if(list.isEmpty()){
                        System.out.println("삭제할 명언이 없습니다.");
                    }else{
                        if(rq.getParam("id", "").isEmpty()){

                        }

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
                }
                case "수정" -> {
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
                                    wise.setLine(scan.nextLine().trim());

                                    System.out.println("기존 작가: " + wise.getAuth());
                                    System.out.print("새 작가: ");
                                    wise.setAuth(scan.nextLine().trim());

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
                }
                case "종료" ->
                        System.out.println("시스템을 종료합니다.");
                default ->
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
    //명령의 종류: 등록 목록 수정 삭제
    private final String actionName;
    //해시맵 선언: 우선 인터페이스 객체(Map)로 선언한 뒤 초기화하는 것이 관례
    private final Map<String, String> paramMap;

    //생성자
    Rq(String answer){
        //해시맵으로 초기화
        paramMap = new HashMap<>();

        //split()에 사용할 정규표현식
        String expression = "\\?";

        //입력을 명령 + 패러미터로 분할
        //최대 2칸 배열(limit:2)로 반환되나, ?가 없는 명령(등록, 목록, 혹은 잘못된 입력)이면 1칸 배열로 반환됨
        String[] urlDivides = answer.split(expression, 2);

        //명령을 저장
        actionName = urlDivides[0];

        //배열이 2칸일 경우(수정 혹은 삭제 명령일 경우)
        if(urlDivides.length > 1){
            //key=value 형태로 된 패러미터를 =를 기준으로 분할
            String[] params = urlDivides[1].split("=",2);

            //분할된 양 값을 key/value 쌍으로 저장
            //value가 없을 경우 params[1]의 값은 ""
            paramMap.put(params[0], params[1]);
        }
    }

    String getActionName(){
        return actionName;
    }

    String getParam(String key, String defaultValue){
        return paramMap.getOrDefault(key, defaultValue);
    }

    int getParamAsInt(String key, int defaultValue){
        //해결됨: 존재하지 않는 키를 조회할 경우 널 포인트 에러 발생 가능성
        //=> 애초에 컨트롤러의 조건문에서 걸러짐

        //value가 없는 경우(등록, 목록)
        if(getParam(key, "").isEmpty()){
            return defaultValue;
        }

        try {
            return Integer.parseInt(paramMap.get(key));

            //value가 정수가 아닌 경우
        }catch (NumberFormatException e){
            return defaultValue;
        }
    }

}