package com.wut.resources.payments;

import com.wut.provider.creditcard.PaymentProvider;
import com.wut.resources.common.WutOperation;
import com.wut.support.settings.SettingsManager;

public class PaymentOperationHelper {

	protected PaymentProvider getPaymentProvider(String customer) {
		String paymentProviderStr = SettingsManager.getCustomerSettings(customer, "payment-processor");
		PaymentManager paymentManager = new PaymentManager();
		PaymentProvider paymentProvider = paymentManager.getPaymentProcessor(paymentProviderStr, customer);
		return paymentProvider;
	}
	
}

