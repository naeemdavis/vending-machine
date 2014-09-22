package com.smart421.vendingmachine.utils;

import com.smart421.vendingmachine.type.Coin;

public class VendingMachineUtils {


    public static int[] getChange(Coin[] coins2, int value) {

        int[] result = new int[coins2.length];
        int remaining = value;
        for (int i = 0; i < coins2.length && remaining > 0; i++) {
            Integer denomination = Integer.valueOf(coins2[i].getDenomination());
            result[i] = remaining / denomination;
            remaining %= denomination;
        }
        return result;
    }

}
