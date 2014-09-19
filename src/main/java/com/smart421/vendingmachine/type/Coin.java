
package com.smart421.vendingmachine.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Coin {

    ONE_POUND(100, "100p"),
    FIFTY_PENCE(50, "50p"),
    TWENTY_PENCE(20, "20p"),
    TEN_PENCE(10, "10p"),
    FIVE_PENCE(5, "5p"),
    TWO_PENCE(2, "2p"),
    ONE_PENCE(1, "1p"),
    ;

    private Integer denomination;
    private String description;


    public static final Map<Integer, Coin> lookupByResultCode = new HashMap<Integer, Coin>();
    static {
        for (Coin s : EnumSet.allOf(Coin.class)) {
            lookupByResultCode.put(s.getDenomination(), s);
        }
    }


    private Coin(Integer denomination, String description){
     this.denomination = denomination;
     this.description = description;
    }




    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the denomination
     */
    public Integer getDenomination() {
        return denomination;
    }


}
