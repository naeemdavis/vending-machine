package com.smart421.vendingmachine.services.impl;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.smart421.vendingmachine.exception.VendingMachineException;
import com.smart421.vendingmachine.type.Coin;
import com.smart421.vendingmachine.type.ResultCodeType;

public class CoinInventoryServiceImpl {

    private static final String COIN_INVENTORY_PROPERTIES_FILE = "coin-inventory.properties";

    public CoinInventoryServiceImpl() {
        initFile();
    }

    public void updateProperties(ArrayList<Coin> coinsList) {
        Properties prop = new Properties();
        HashMap<Coin, String> propertiesMap = new HashMap<Coin, String>();

        FileInputStream input = null;

        try {

            input = getProperties(prop);

            if (coinsList != null) {
                for (Coin coin : coinsList) {
                    Integer currentAvailableCoins = null;
                    String currentDenomination = coin.getDenomination();
                    Integer availbleCoins = Integer.valueOf(propertiesMap.get(coin) != null ? propertiesMap.get(coin) : prop
                                    .getProperty(currentDenomination));
                    System.out.println(availbleCoins);
                    if (availbleCoins > 0) {
                        currentAvailableCoins = availbleCoins - 1;
                        propertiesMap.put(coin, currentAvailableCoins.toString());
                    }
                }
            } else {

                throw new VendingMachineException(ResultCodeType.INSUFFICIENT_COINS);

            }

            updatePropertiesFile(propertiesMap, prop);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(input);
        }
    }

    private FileInputStream getProperties(Properties prop) throws FileNotFoundException, IOException {
        FileInputStream input;
        input = new FileInputStream(COIN_INVENTORY_PROPERTIES_FILE);
        prop.load(input);
        return input;
    }

    private void updatePropertiesFile(HashMap<Coin, String> propertiesMap, Properties filesProp) {

        Properties prop = new Properties();

        OutputStream output = null;

        try {

            output = new FileOutputStream(COIN_INVENTORY_PROPERTIES_FILE);

            for (Map.Entry<Coin, String> entry : propertiesMap.entrySet()) {
                String denomination = entry.getKey().getDenomination();
                prop.setProperty(denomination, entry.getValue());
            }

            if (filesProp != null) {
                for (Entry<Object, Object> e : filesProp.entrySet()) {
                    if (prop.get(e.getKey()) == null) {
                        String denomination = e.getKey().toString();
                        String availableCoins = e.getValue().toString();
                        prop.setProperty(denomination, availableCoins);
                    }
                }
            }

            prop.store(output, null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(output);

        }
    }

    private void closeStream(Closeable output) {
        if (output != null) {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initFile() {

        HashMap<Coin, String> defaultProperties = new HashMap<Coin, String>();
        defaultProperties.put(Coin.ONE_POUND, "11");
        defaultProperties.put(Coin.FIFTY_PENCE, "24");
        defaultProperties.put(Coin.TWENTY_PENCE, "0");
        defaultProperties.put(Coin.TEN_PENCE, "99");
        defaultProperties.put(Coin.FIVE_PENCE, "200");
        defaultProperties.put(Coin.TWO_PENCE, "11");
        defaultProperties.put(Coin.ONE_PENCE, "23");

        updatePropertiesFile(defaultProperties, null);

    }

    public Coin[] getArrayOfAvailableCoins(BigDecimal pences) {

        ArrayList<Coin> currentCoinsList = new ArrayList<Coin>();
        Coin[] coins = null;
        FileInputStream input = null;
        Properties prop = new Properties();
        BigDecimal totalAvailablePences = BigDecimal.ZERO;

        try {

            input = getProperties(prop);

            Integer availablePounds = Integer.valueOf(prop.getProperty(Coin.ONE_POUND.getDenomination()));
            if (availablePounds > 0) {
                totalAvailablePences = getAvailablePences(100, availablePounds, totalAvailablePences);
                currentCoinsList.add(Coin.ONE_POUND);
            }

            Integer availableFiftyPence = Integer.valueOf(prop.getProperty(Coin.FIFTY_PENCE.getDenomination()));
            if (availableFiftyPence > 0) {
                totalAvailablePences = getAvailablePences(50, availableFiftyPence, totalAvailablePences);

                currentCoinsList.add(Coin.FIFTY_PENCE);
            }

            Integer availableTwentyPence = Integer.valueOf(prop.getProperty(Coin.TWENTY_PENCE.getDenomination()));
            if (availableTwentyPence > 0) {
                totalAvailablePences = getAvailablePences(20, availableTwentyPence, totalAvailablePences);

                currentCoinsList.add(Coin.TWENTY_PENCE);
            }

            Integer availableTenPence = Integer.valueOf(prop.getProperty(Coin.TEN_PENCE.getDenomination()));
            if (availableTenPence > 0) {
                totalAvailablePences = getAvailablePences(10, availableTenPence, totalAvailablePences);

                currentCoinsList.add(Coin.TEN_PENCE);
            }

            Integer availableFivePence = Integer.valueOf(prop.getProperty(Coin.FIVE_PENCE.getDenomination()));
            if (availableFivePence > 0) {
                totalAvailablePences = getAvailablePences(5, availableFivePence, totalAvailablePences);

                currentCoinsList.add(Coin.FIVE_PENCE);
            }

            Integer availableTwoPence = Integer.valueOf(prop.getProperty(Coin.TWO_PENCE.getDenomination()));
            if (availableTwoPence > 0) {
                totalAvailablePences = getAvailablePences(2, availableTwoPence, totalAvailablePences);

                currentCoinsList.add(Coin.TWO_PENCE);
            }

            Integer availableOnePence = Integer.valueOf(prop.getProperty(Coin.ONE_PENCE.getDenomination()));
            if (availableOnePence > 0) {

                totalAvailablePences = getAvailablePences(1, availableOnePence, totalAvailablePences);

                currentCoinsList.add(Coin.ONE_PENCE);
            }

            coins = (Coin[]) currentCoinsList.toArray(new Coin[currentCoinsList.size()]);

            if (totalAvailablePences.compareTo(pences) == -1) {
                throw new VendingMachineException(ResultCodeType.INSUFFICIENT_COINS);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(input);
        }
        return coins;
    }

    private BigDecimal getAvailablePences(int domination, Integer availablePounds, BigDecimal availablePences) {
        return BigDecimal.valueOf(availablePounds).multiply(BigDecimal.valueOf(domination)).add(availablePences);
    }

}