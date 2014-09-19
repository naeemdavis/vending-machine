package com.smart421.vendingmachine.services;

import java.util.Collection;

import com.smart421.vendingmachine.type.Coin;


public interface VendingMachineService {

    public Collection<Coin> getOptimalChangeFor(int pence);

    public Collection<Coin> getChangeFor(int pence);

}
