package com.smart421.vendingmachine.services;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.smart421.vendingmachine.exception.VendingMachineException;
import com.smart421.vendingmachine.services.impl.VendingMachineServiceImpl;
import com.smart421.vendingmachine.type.Coin;

public class VendingMachineServiceTest {

    public VendingMachineService vendingMachineService;

    @Before
    public void initilizeBeforeClass() {
        vendingMachineService = new VendingMachineServiceImpl();
    }

    /****PART 1 Tests getOptimalChangeFor***/
    
    @Test
    public void testgetOptimalChangeFor_70Pence() {
        Collection<Coin> coinsList = vendingMachineService.getOptimalChangeFor(70);

        Assert.assertNotNull(coinsList);
        Assert.assertSame(coinsList.size(), 2);
    }

    @Test
    public void testgetOptimalChangeFor_80Pence() {
        Collection<Coin> coinsList = vendingMachineService.getOptimalChangeFor(80);

        Assert.assertNotNull(coinsList);
        Assert.assertSame(coinsList.size(), 3);
    }

    @Test
    public void testgetOptimalChangeFor_90Pence() {
        Collection<Coin> coinsList = vendingMachineService.getOptimalChangeFor(90);

        Assert.assertNotNull(coinsList);
        Assert.assertSame(coinsList.size(), 3);
    }

    @Test
    public void testgetOptimalChangeFor_89Pence() {
        Collection<Coin> coinsList = vendingMachineService.getOptimalChangeFor(89);
        Assert.assertNotNull(coinsList);
        Assert.assertSame(coinsList.size(), 6);
        Object[] coins = coinsList.toArray();
        Assert.assertEquals(coins[0], Coin.FIFTY_PENCE);
        Assert.assertEquals(coins[1], Coin.TWENTY_PENCE);
        Assert.assertEquals(coins[2], Coin.TEN_PENCE);
        Assert.assertEquals(coins[3], Coin.FIVE_PENCE);
        Assert.assertEquals(coins[4], Coin.TWO_PENCE);
        Assert.assertEquals(coins[5], Coin.TWO_PENCE);
    }

    @Test
    public void testgetOptimalChangeFor_99Pence() {
        Collection<Coin> coinsList = vendingMachineService.getOptimalChangeFor(99);
        Assert.assertNotNull(coinsList);
        Assert.assertSame(coinsList.size(), 6);

        Object[] coins = coinsList.toArray();
        Assert.assertEquals(coins[0], Coin.FIFTY_PENCE);
        Assert.assertEquals(coins[1], Coin.TWENTY_PENCE);
        Assert.assertEquals(coins[2], Coin.TWENTY_PENCE);
        Assert.assertEquals(coins[3], Coin.FIVE_PENCE);
        Assert.assertEquals(coins[4], Coin.TWO_PENCE);
        Assert.assertEquals(coins[5], Coin.TWO_PENCE);
    }
    
    
    /****PART 2 Tests getChangeFor***/
    

    
    @Test
    public void testgetChangeFor_89Pence() {
        Collection<Coin> coinsList = vendingMachineService.getChangeFor(89);
        Assert.assertNotNull(coinsList);
        Assert.assertSame(coinsList.size(), 7);
        Object[] coins = coinsList.toArray();
        Assert.assertEquals(coins[0], Coin.FIFTY_PENCE);
        Assert.assertEquals(coins[1], Coin.TEN_PENCE);
        Assert.assertEquals(coins[2], Coin.TEN_PENCE);
        Assert.assertEquals(coins[3], Coin.TEN_PENCE);
        Assert.assertEquals(coins[4], Coin.FIVE_PENCE);
        Assert.assertEquals(coins[5], Coin.TWO_PENCE);
        Assert.assertEquals(coins[6], Coin.TWO_PENCE);
    }

    
    
    @Test
    public void testgetChangeFor_99Pence() {
        Collection<Coin> coinsList = vendingMachineService.getChangeFor(99);
        Assert.assertNotNull(coinsList);
        Assert.assertSame(coinsList.size(), 8);
        
        Object[] coins = coinsList.toArray();
        Assert.assertEquals(coins[0], Coin.FIFTY_PENCE);
        Assert.assertEquals(coins[1], Coin.TEN_PENCE);
        Assert.assertEquals(coins[2], Coin.TEN_PENCE);
        Assert.assertEquals(coins[3], Coin.TEN_PENCE);
        Assert.assertEquals(coins[4], Coin.TEN_PENCE);
        Assert.assertEquals(coins[5], Coin.FIVE_PENCE);
        Assert.assertEquals(coins[6], Coin.TWO_PENCE);
        Assert.assertEquals(coins[7], Coin.TWO_PENCE);
    }
    
    
    @Test(expected = VendingMachineException.class)
    public void testgetChangeFor_4336Pence_insufficentCoins() {
        vendingMachineService.getChangeFor(4336);
        Assert.fail("Should not reach this point");
    }

}
