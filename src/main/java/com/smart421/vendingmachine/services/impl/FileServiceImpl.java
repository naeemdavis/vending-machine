	package com.smart421.vendingmachine.services.impl;

	import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeSet;

import com.smart421.vendingmachine.type.Coin;

	public class FileServiceImpl {
		
		
		
		public FileServiceImpl(){
			initFile();
		}
		

		public void updatePropertiesFiles(ArrayList<Coin> coinsList) {
			Properties prop = new Properties();
		
			FileInputStream input = null;
		
			
		try {

			input = new FileInputStream("coin-inventory.properties");
			prop.load(input);
			
			if(coinsList!=null){
				for(Coin coin: coinsList){
					Integer currentAvailableCoins = null;
					String currentDenomination = coin.getDenomination();
					Integer availbleCoins = Integer.valueOf(prop.getProperty(currentDenomination));
					System.out.println(availbleCoins);
					if(availbleCoins>0){
			     		currentAvailableCoins = availbleCoins-1;
						updateProperty(coin, currentAvailableCoins);
					}else{
						//availbleCoins = getDenomation(currentDenomination);
						Collection<Coin> changeFor = new VendingMachineServiceImpl().getOptimalChangeFor(Integer.valueOf(currentDenomination));
						System.out.println(changeFor.size());
					}
					
				}
			}
			
			
			
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
		}
		
		
		private void updateProperty(Coin coin, Integer currentAvailableCoins) {
			// TODO Auto-generated method stub
			
		}


		private String getDenomation(String currentDenomination) {
			// TODO Auto-generated method stub
			return null;
		}


		public void initFile() {
			Properties prop = new Properties();
		
			OutputStream output = null;
		
			
		try {

			output = new FileOutputStream("coin-inventory.properties");
			prop.setProperty(Coin.ONE_POUND.getDenomination().toString(), "11");
			prop.setProperty(Coin.FIFTY_PENCE.getDenomination().toString(), "24");
			prop.setProperty(Coin.TWENTY_PENCE.getDenomination().toString(), "0");
			prop.setProperty(Coin.TEN_PENCE.getDenomination().toString(), "99");
			prop.setProperty(Coin.FIVE_PENCE.getDenomination().toString(), "200");
			prop.setProperty(Coin.TWO_PENCE.getDenomination().toString(), "11");
			prop.setProperty(Coin.ONE_PENCE.getDenomination().toString(), "23");
			prop.store(output, null);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
		}
		
}