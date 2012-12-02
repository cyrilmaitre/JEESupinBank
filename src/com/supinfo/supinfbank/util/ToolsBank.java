package com.supinfo.supinfbank.util;

public class ToolsBank 
{
	private static final String establishmentCode = "92807";
	private static final String branchCode = "00000";

	public static String computeBBAN(int accountId)
	{
		if(accountId <= 0)
			return null;
		
		String accountNumber = String.valueOf(accountId);
		while(accountNumber.length() < 11)
			accountNumber = "0" + accountNumber;
		
		String key = String.valueOf((long)(97 - (Double.parseDouble(establishmentCode + branchCode + accountNumber) * 100 % 97)));
		
		return establishmentCode + branchCode + accountNumber + key;
	}
}
