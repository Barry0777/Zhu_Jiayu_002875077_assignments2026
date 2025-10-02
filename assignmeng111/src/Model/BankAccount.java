/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author barryzhu
 */
public class BankAccount {
    
    private String name, accountType, accountNumber, debitAmount, creditAmount;

    public BankAccount() {}

    public BankAccount(String name, String accountType, String accountNumber, String debitAmount, String creditAmount) {
        this.name = name; this.accountType = accountType; this.accountNumber = accountNumber;
        this.debitAmount = debitAmount; this.creditAmount = creditAmount;
    }

    public String getName() { return name; }                public void setName(String v) { name = v; }
    public String getAccountType() { return accountType; }  public void setAccountType(String v) { accountType = v; }
    public String getAccountNumber() { return accountNumber; } public void setAccountNumber(String v) { accountNumber = v; }
    public String getDebitAmount() { return debitAmount; }  public void setDebitAmount(String v) { debitAmount = v; }
    public String getCreditAmount() { return creditAmount; }public void setCreditAmount(String v) { creditAmount = v; }
}

