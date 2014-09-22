package com.smart421.vendingmachine.services;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.smart421.vendingmachine.type.Coin;

public interface CoinInventoryService {
	void updateProperties(ArrayList<Coin> coinsList);

	Coin[] getArrayOfAvailableCoins(BigDecimal pences);
}
