package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
            );
    //ID, Customer Name, Charge, Charge Date
    public static void main(String[] args) {
        List<Customer> cleanData = new ArrayList<>();
        for (String[] data : customerData) {
            //System.out.println(data[1]);
            Optional<Customer> filteredStream = cleanData.stream()
                    .filter(customer -> customer.getId() ==Integer.parseInt(data[0]))
                    .findFirst();
            if (filteredStream.isPresent()){
                Customer found = filteredStream.get();
                AccountRecord accountCharge = new AccountRecord();
                accountCharge.setCharge(Integer.parseInt(data[2]));
                accountCharge.setChargeDate(data[3]);
                found.getCharges().add(accountCharge);
            }
            else{
                Customer notFound = new Customer();
                notFound.setId(Integer.parseInt(data[0]));
                notFound.setName(data[1]);
                AccountRecord accountCharge = new AccountRecord();
                accountCharge.setCharge(Integer.parseInt(data[2]));
                accountCharge.setChargeDate(data[3]);
                notFound.getCharges().add(accountCharge);
                cleanData.add(notFound);
            }

        }
        for (Customer customer : cleanData){
            System.out.println(customer.toString());
        }


        System.out.println("Positive accounts:");
        cleanData.stream().filter(customer -> customer.getBalance()>=0)
                .forEach(System.out::println);


        System.out.println("Negative accounts:");
        cleanData.stream().filter(customer -> customer.getBalance()<0)
                .forEach(System.out::println);


    }
}
