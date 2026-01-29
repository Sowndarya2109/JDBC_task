package com.wipro.bank.service;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.dao.BankDAO;
import com.wipro.bank.util.InsufficientFundsException;

public class BankService {
	public String checkBalance(String accountNumber) {
		 BankDAO bank = new BankDAO();
         if(bank.validateAccount(accountNumber)) {
			float balance = bank.findBalance(accountNumber);
			return "BALANCE IS: "+ balance;
		 }
			return "ACCOUNT NUMBER INVALID";
		
	}
	public String transfer(TransferBean transferBean) throws InsufficientFundsException{
		BankDAO bank = new BankDAO();
	    try {
		if(transferBean == null)
			return "INVALID";
		String fromAcc = transferBean.getFromAccountNumber();
		String toAcc = transferBean.getToAccountNumber();
		if(bank.validateAccount(fromAcc) == true && bank.validateAccount(toAcc) == true) {
			if(bank.findBalance(fromAcc) >= transferBean.getAmount()) {
				float fromAc = bank.findBalance(fromAcc) - transferBean.getAmount();
				float toAc = bank.findBalance(toAcc) + transferBean.getAmount();
				bank.transferMoney(transferBean);
				bank.updateBalance(fromAcc, fromAc);
				bank.updateBalance(toAcc, toAc);
				return "SUCCESS";
			}
			throw new InsufficientFundsException();
		}
		return "INVALID ACCOUNT";

	}catch(InsufficientFundsException e) {
		return e.toString();
	}
		
	}

}
