package com.wipro.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.util.DBUtil;

public class BankDAO {
	public int generateSequenceNumber() {
		Connection con = DBUtil.getDBConnection();
		String query = " SELECT transactionId_seq1.NEXTVAL from dual";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int sequenceNo = rs.getInt(1);
			return sequenceNo;
		  }catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public boolean validateAccount(String accountNumber) {
		Connection con = DBUtil.getDBConnection();
		String query = "SELECT * FROM ACCOUNT_TBL WHERE ACCOUNT_NUMBER = ?" ;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public float findBalance(String accountNumber) {
		 if(validateAccount(accountNumber)) {
			 Connection con = DBUtil.getDBConnection();
			 String query = "SELECT Balance From ACCOUNT_TBL WHERE ACCOUNT_NUMBER = ?";
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, accountNumber);
				ResultSet rs = ps.executeQuery();
				rs.next();
				float balance = rs.getFloat(1);
				return balance;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		 }
		 return -1;
	}
	public boolean updateBalance(String accountNumber, float newBalance) {
		Connection con = DBUtil.getDBConnection();
		String query = "UPDATE ACCOUNT_TBL SET Balance = ? WHERE ACCOUNT_NUMBER = ?";
		try {
			int s = 0;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(2, accountNumber);
			ps.setFloat(1, newBalance);
			s = ps.executeUpdate();
			if(s > 0)
			   return true;
			else
			   return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean transferMoney(TransferBean transferBean) {
		transferBean.setTransactionID(generateSequenceNumber());
		Connection con = DBUtil.getDBConnection();
		String query = "INSERT INTO Transfer_TBL VALUES(?,?,?,?,?)";
		try {
			 PreparedStatement ps = con.prepareStatement(query);
			 ps.setInt(1, transferBean.getTransactionID());
			 ps.setString(2, transferBean.getFromAccountNumber());
			 ps.setString(3, transferBean.getToAccountNumber());
			 Date d = new Date(transferBean.getDateOfTransaction().getTime());
			 ps.setDate(4, d);
			 ps.setFloat(5, transferBean.getAmount());
			 int row = ps.executeUpdate();
			 if(row > 0) {
				 return true;
			 }
			 return false;
		}catch(SQLException e){
			return false;
		}
	   
             
	}
	

}
