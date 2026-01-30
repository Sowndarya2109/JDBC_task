BANK TRANSACTION MANAGEMENT SYSTEM

OVERVIEW:

A simple Java JDBC + Oracle based banking application that supports account validation, balance check, and money transfer between accounts using a layered architecture (Bean, DAO, Service, Util).

MODULES:

DBUtil – Database connection

TransferBean – Transaction data model

BankDAO – Database operations

BankService – Business logic

InsufficientFundsException – Custom exception

DATABASE OBJECTS:

ACCOUNT_TBL – Stores account details and balance

TRANSFER_TBL – Stores transaction details

SEQUENCE – Generates unique transaction IDs

FEATURES:

Validate account number

Check account balance

Transfer money between accounts

Auto-generate transaction ID

Handle insufficient balance scenario

HOW TO RUN:

Create tables and sequence in Oracle DB

Update DB credentials in DBUtil.java

Run service methods to test balance and transfer
