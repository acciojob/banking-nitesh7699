package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        int s = 0;
        int temp = digits;
        while(temp>0){
            int ld = temp%10;
            temp = temp/10;
            s = s + ld;
        }
        
        try{
            if(s != sum){
                throw new CustomException("Account Number can not be generated");
            }
        }
        catch(CustomException e){
            return e.getMessage();
        }

        return Integer.toString(digits);
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance = this.balance + amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        try{
            this.balance = this.balance - amount;
            if(this.balance < this.minBalance){
                throw new CustomException("Insufficient Balance");
            }
        }
        catch(CustomException e){
            System.out.println(e.getMessage());
        }

    }

    public double getAmount(){
        return balance;
    }

}