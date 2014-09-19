package com.smart421.vendingmachine.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.smart421.vendingmachine.services.VendingMachineService;

@Controller
@RequestMapping("/vendingmachine")
public class VendingMachineController {


	@Autowired
    private VendingMachineService vendingMachineService;

//    /**
//     * get rewards
//     */
//    @RequestMapping( value = {"/change"}, method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.OK)
//    public @ResponseBody BigDecimal getChange(@RequestBody List<Item> items) {
//        checkoutService.setItemList(items);
//        return checkoutService.total();
//    }

}
