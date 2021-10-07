package com.mobile.imei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.imei.service.ImeiService;

@RestController
public class ImeiController
{
	@Autowired
	private ImeiService imeiService;

	@RequestMapping(value = "/checkById/{num}", method = RequestMethod.GET)
	public String isValidIMEI(@PathVariable("num") String num)
	{
		int computedCheckDigit = imeiService.getDigitForValidIMEI(num.substring(0, (num.length() - 1)));
		int checkDigit = Integer.valueOf(num.substring((num.length() - 1)));

		if (computedCheckDigit == checkDigit)
		{
			return num + "   is a valid IMEI number!";
		} else
		{
			return num + "     is NOT a valid IMEI number and digit to be replaced is " + computedCheckDigit;
		}
	}
}
