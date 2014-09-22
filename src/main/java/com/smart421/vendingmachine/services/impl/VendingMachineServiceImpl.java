package com.smart421.vendingmachine.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.smart421.vendingmachine.services.VendingMachineService;
import com.smart421.vendingmachine.type.Coin;

public class VendingMachineServiceImpl implements VendingMachineService {

    public static Coin coins[] = new Coin[] { Coin.ONE_POUND, Coin.FIFTY_PENCE, Coin.TWENTY_PENCE, Coin.TEN_PENCE,
                    Coin.FIVE_PENCE, Coin.TWO_PENCE, Coin.ONE_PENCE };

    @Override
    public Collection<Coin> getOptimalChangeFor(int pence) {
        ArrayList<Coin> coinList = new ArrayList<Coin>();
        int[] change = getChange(coins, pence);
        return getCoinsList(coinList, change);
    }

    private Collection<Coin> getCoinsList(ArrayList<Coin> coinList, int[] change) {
        Coin coin;
        for (int i = 0; i < change.length; i++) {
            int coinsCount = change[i];
            for (int c = 0; c < coinsCount; c++) {
                coinList.add(coins[i]);
            }
        }

        return coinList;
    }

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

    @Override
    public Collection<Coin> getChangeFor(int pence) {
    	
    	  ArrayList<Coin> coinList = new ArrayList<Coin>();
          int[] change = getChange(coins, pence);
          ArrayList<Coin> coinsList = (ArrayList<Coin>) getCoinsList(coinList, change);
        
          FileServiceImpl fileService = new FileServiceImpl();
          fileService.updatePropertiesFiles(coinsList);
    	
    	return null;
    }

}
