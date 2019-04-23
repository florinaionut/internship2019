import java.util.List;

public class RedistributeMoney {
    double free, bit, bit1, dob1, dob2, dob3, min1, min2, max1, max2;
    int time;
    Account r = new Account();

    public List<Account> redistributeMoney(List<Account> accounts) {
        // TODO: add your code here

        Account acc1 = new Account("SILVER", 0.3 / 100, 0.2 / 100, "2020-05-23", 5000, 5000);
        Account acc2 = new Account("GOLD", 0.6 / 100, 0.4 / 100, "2020-07-05", 700, 5000);
        Account acc3 = new Account("PLATINUM", 0.9 / 100, 0.5 / 100, "2020-03-15", 300, 5000);
        accounts.add(acc1);
        accounts.add(acc2);
        accounts.add(acc3);

        min1 = r.compare(acc1.rate1, acc2.rate1, acc3.rate1)[0];
        min2 = r.compare(acc1.rate2, acc2.rate2, acc3.rate2)[0];
        max1 = r.compare(acc1.rate1, acc2.rate1, acc3.rate1)[1];
        max2 = r.compare(acc1.rate2, acc2.rate2, acc3.rate2)[1];

        transaction(acc1, acc2, acc3, min1, min2, max1, max2, acc1.rate1, acc1.rate2, acc2.rate1, acc2.rate2, acc1.availableAmount, acc2.availableAmount, acc3.availableAmount, acc2.maxAmount);
        transaction(acc1, acc3, acc2, min1, min2, max1, max2, acc1.rate1, acc1.rate2, acc3.rate1, acc3.rate2, acc1.availableAmount, acc3.availableAmount, acc2.availableAmount, acc3.maxAmount);
        transaction(acc2, acc1, acc3, min1, min2, max1, max2, acc2.rate1, acc2.rate2, acc1.rate1, acc1.rate2, acc2.availableAmount, acc1.availableAmount, acc3.availableAmount, acc1.maxAmount);
        transaction(acc2, acc3, acc2, min1, min2, max1, max2, acc2.rate1, acc2.rate2, acc3.rate1, acc3.rate2, acc2.availableAmount, acc3.availableAmount, acc1.availableAmount, acc3.maxAmount);
        transaction(acc3, acc1, acc2, min1, min2, max1, max2, acc3.rate1, acc3.rate2, acc1.rate1, acc1.rate2, acc3.availableAmount, acc1.availableAmount, acc2.availableAmount, acc1.maxAmount);
        transaction(acc3, acc2, acc1, min1, min2, max1, max2, acc3.rate1, acc3.rate2, acc2.rate1, acc2.rate2, acc3.availableAmount, acc2.availableAmount, acc1.availableAmount, acc2.maxAmount);


        System.out.print("Name account: " + acc1.name + ", available amount: " + acc1.availableAmount + ", Interest: ");
        dob1 = r.calculate(acc1.rate1, acc1.rate2, acc1.availableAmount);
        if (dob1 == 0)
            System.out.println("0");
        System.out.print("Name account: " + acc2.name + ", available amount: " + acc2.availableAmount + ", Interest: ");
        dob2 = r.calculate(acc2.rate1, acc2.rate2, acc2.availableAmount);
        if (dob2 == 0)
            System.out.println("0");
        System.out.print("Name account: " + acc3.name + ", available amount: " + acc3.availableAmount + ", Interest: ");
        dob3 = r.calculate(acc3.rate1, acc3.rate2, acc3.availableAmount);
        if (dob3 == 0)
            System.out.println("0");


        time = r.timeDays() / 365;
        double totalSum = (acc1.availableAmount + dob1 * time) + (acc2.availableAmount + dob2 * time) + (acc3.availableAmount + dob3 * time);
        System.out.println();
        System.out.println("Total amount available in all accounts after the money has been \n" +
                " redistributed and 39 months have passed= " + totalSum);


        return accounts;
    }


    public void transaction(Account a, Account b, Account c, double low1, double low2, double high1, double high2, double r1, double r2, double r3, double r4, double amount1, double amount2, double amount3, double maxAmount) {
        if (low1 == r1 && low2 == r2) {
            if (high1 == r3 && high2 == r4) {
                free = maxAmount - amount2;
                if (amount1 <= free) {
                    r.transfer(a, b, amount1);
                    free = free - amount1;
                    if (free > 0) {
                        if (amount3 <= free) {
                            r.transfer(c, b, amount3);
                        } else if (amount3 > free) {
                            bit1 = amount3 - free;
                            r.transfer(c, b, amount3 - bit1);
                        }
                    }
                } else if (amount1 > free) {
                    bit = amount1 - free;
                    r.transfer(a, c, bit);
                    r.transfer(a, b, amount1 - bit);
                }
            }
        }
    }


}
