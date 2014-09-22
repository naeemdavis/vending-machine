package com.smart421.vendingmachine.exception;

import com.smart421.vendingmachine.type.ResultCodeType;

public class VendingMachineException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	private ResultCodeType resultCode;

	/**
     * Creates a new VendingMachineException with a given result code and message.
     *
     * @param resultCode
     * @param message
     */
    public VendingMachineException(ResultCodeType resultCode) {
        super(resultCode.getDescription());
        this.resultCode = resultCode;
    }

    public ResultCodeType getResultCode() {
		return resultCode;
	}
}
