package com.smart421.vendingmachine.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import com.smart421.vendingmachine.services.VendingMachineService;
import com.smart421.vendingmachine.type.Coin;
import com.smart421.vendingmachine.utils.VendingMachineUtils;

public class VendingMachineServiceImpl implements VendingMachineService {

    public static Coin coins[] = new Coin[] { Coin.ONE_POUND, Coin.FIFTY_PENCE, Coin.TWENTY_PENCE, Coin.TEN_PENCE,
                    Coin.FIVE_PENCE, Coin.TWO_PENCE, Coin.ONE_PENCE };

    @Override
    public Collection<Coin> getOptimalChangeFor(int pence) {
        int[] change = VendingMachineUtils.getChange(coins, pence);
        return getCoinsList(change, coins);
    }

    private Collection<Coin> getCoinsList(int[] change, Coin coins[]) {
        ArrayList<Coin> coinList = new ArrayList<Coin>();

        for (int i = 0; i < change.length; i++) {
            int coinsCount = change[i];
            for (int c = 0; c < coinsCount; c++) {
                coinList.add(coins[i]);
            }
        }

        return coinList;
    }


    @Override
    public Collection<Coin> getChangeFor(int pence) {
          CoinInventoryServiceImpl coinInventoryServiceImpl = new CoinInventoryServiceImpl();
          Coin coins[] = coinInventoryServiceImpl.getArrayOfAvailableCoins(new BigDecimal(pence));
          int[] change = VendingMachineUtils.getChange(coins, pence);
          ArrayList<Coin> coinsList = (ArrayList<Coin>) getCoinsList(change, coins);

          coinInventoryServiceImpl.updateProperties(coinsList);

    	return coinsList;
    }

}
