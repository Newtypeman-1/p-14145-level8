package com.back;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq{
    private final String actionName;
    private final Map<String, String> paramMap;

    public Rq(String cmd){
        paramMap = new HashMap<>();

        String[] cmdBits = cmd.split("\\?", 2);
        actionName = cmdBits[0];

        if(cmdBits.length > 1 && !cmdBits[1].isEmpty()){
            String[] paramBits = cmdBits[1].split("&");

            Map<String, String> parsedItems =
                    Arrays.stream(paramBits)
                        .map(bit -> bit.split("=", 2))
                        .filter(params -> params.length == 2 && !params[1].isEmpty())
                        .collect(Collectors.toMap(
                                params -> params[0],
                                 params-> params[1],
                                (oldValue, newValue) -> newValue
                        ));
            paramMap.putAll(parsedItems);
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
