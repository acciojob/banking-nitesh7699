package com.driver;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        if(balance < 5000){
            throw new customException("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if (!isConsecutiveChar(tradeLicenseId)) {
            String newTradeId = rearrangeString(tradeLicenseId);
            if (!isConsecutiveChar(newTradeId) || newTradeId.length()==0) throw new Exception("Valid License can not be generated");
            else this.tradeLicenseId = newTradeId;
        }
    }

    private String rearrangeString(String id) {
        int len = id.length();
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : id.toCharArray()) {
            map.put(ch,map.getOrDefault(ch,0)+1);
            if(map.get(ch) > (len+1)/2) return "";
        }

        PriorityQueue<Character> pq = new PriorityQueue<>((a, b)->map.get(b)-map.get(a));
        for(char ch : map.keySet()) pq.offer(ch);

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            char ch = pq.poll();
            if(sb.length()==0 || sb.charAt(sb.length()-1)!=ch) {
                sb.append(ch);
                int f1 = map.get(ch);
                if(--f1 > 0) {
                    map.put(ch,f1);
                    pq.offer(ch);
                }
            } else {
                char ch2 = pq.poll();
                int f2 = map.get(ch2);
                sb.append(ch2);
                if(--f2 > 0) {
                    map.put(ch2, f2);
                    pq.offer(ch2);
                }
                pq.offer(ch);
            }
        }
        return sb.toString();
    }

    private boolean isConsecutiveChar(String id) {
        int n = id.length();
        for (int i = 0; i < n-1; i++) {
            if (id.charAt(i) == id.charAt(i+1)) return false;
        }
        return true;
    }
}
