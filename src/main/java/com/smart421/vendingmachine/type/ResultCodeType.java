package com.smart421.vendingmachine.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ResultCodeType {

    INSUFFICIENT_COINS(1, "Insufficient coins");

    private static final Map<Integer, ResultCodeType> lookupByResultCode = new HashMap<Integer, ResultCodeType>();
    static {
        for (ResultCodeType s : EnumSet.allOf(ResultCodeType.class)) {
            lookupByResultCode.put(s.getResultCode(), s);
        }
    }


    private Integer resultCode;


    private String message;


    private ResultCodeType(Integer resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;

    }

    public Integer getResultCode() {
        return resultCode;
    }

    public String getDescription() {
        return message;
    }

    public static ResultCodeType getByResultCode(Integer id) {
        return lookupByResultCode.get(id);
    }

}
