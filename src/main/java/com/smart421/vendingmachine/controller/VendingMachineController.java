package com.smart421.vendingmachine.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.smart421.vendingmachine.services.VendingMachineService;
import com.smart421.vendingmachine.type.Coin;

@Controller
@RequestMapping("/vendingmachine")
public class VendingMachineController {

	@Autowired
	private VendingMachineService vendingMachineService;

	/**
	 * get rewards
	 */
	@RequestMapping(value = { "/optimalchange" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Collection<Coin> getOptimalChange(
			@RequestBody int pence) {
		return vendingMachineService.getOptimalChangeFor(pence);
	}

	@RequestMapping(value = { "/change" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Collection<Coin> getChange(@RequestBody int pence) {
		return vendingMachineService.getChangeFor(pence);
	}
}
