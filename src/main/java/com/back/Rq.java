package com.back;

import java.util.HashMap;
import java.util.Map;

public class Rq{
    private final String actionName;
    private final Map<String, String> paramMap;

    public Rq(String cmd){
        paramMap = new HashMap<>();

        String[] cmdBits = cmd.split("\\?", 2);
        actionName = cmdBits[0];

        if(cmdBits.length > 1 && !cmdBits[1].isEmpty()){
            String[] paramBits = cmdBits[1].split("&");
            for(String bit : paramBits){
                String[] params = bit.split("=", 2);
                if(params.length == 2 && !params[1].isEmpty()){
                    paramMap.put(params[0], params[1]);
                }
            }
        }
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String key, String defaultValue) {
        return paramMap.getOrDefault(key, defaultValue);
    }

    public int getParamAsInt(String key, int defaultValue) {
        String param = getParam(key, "");

        try{
            return Integer.parseInt(param);
        }catch (NumberFormatException e){
            return defaultValue;
        }
    }
}
