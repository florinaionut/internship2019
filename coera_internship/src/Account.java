import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Account {
    String name;
    double rate1, rate2, availableAmount, maxAmount, D;
    String expDate;
    double valLess500, valGreater500;
    private NumberFormat fmt = NumberFormat.getCurrencyInstance();

    public Account(String name, double rate1, double rate2, String expDate, double availableAmount, double maxAmount) {
        this.name = name;
        this.rate1 = rate1;
        this.rate2 = rate2;
        this.expDate = expDate;
        this.availableAmount = availableAmount;
        this.maxAmount = maxAmount;
    }

    Account() {

    }

    public double calculate(double rate1, double rate2, double availableAmount) {
        if (availableAmount != 0) {
            if (availableAmount >= 0 && availableAmount <= 500) {
                valLess500 = availableAmount;
                valGreater500 = 0;
            } else if (availableAmount > 500 && availableAmount <= 5000) {
                valLess500 = 500;
                valGreater500 = availableAmount - 500;
            } else
                System.out.println("valoare in afara intervalului ");
            D = rate1 * valLess500 + rate2 * valGreater500;
            System.out.println(D);
        }
        return D;
    }

    public int timeDays() {
        //fara an bisect
        Calendar calendar = new GregorianCalendar();
        int CURRENT_DAY = 19;
        int CURRENT_MONTH = 3;
        int MONTH_FORWARD = 39;
        int HOUR = 11;
        int MIN = 30;
        int totalDays = 0;
        calendar.set(Calendar.DAY_OF_MONTH, CURRENT_MONTH, CURRENT_DAY, HOUR, MIN);
        for (int i = 0; i < MONTH_FORWARD; i++) {
            totalDays += calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.add(Calendar.MONTH, +1);
        }
        return totalDays;
    }


    public double[] compare(double rate_S, double rate_G, double rate_P) {
        double max, min;
        if (rate_S > rate_G) {
            max = rate_S;
            min = rate_G;
        } else {
            max = rate_G;
            min = rate_S;
        }
        if (rate_P > max) {
            max = rate_P;
        } else if (rate_P < min)
            min = rate_P;
        return new double[]{min, max};
    }


    public double withdraw(double amount) {
        if (amount < 0)
        {
            System.out.println();
            System.out.println("Error: Withdraw amount is invalid.");
            System.out.println("Account: " + name);
            System.out.println("Requested: " + fmt.format(amount));
        } else if (amount > availableAmount)
        {
            System.out.println();
            System.out.println("Error: Insufficient funds.");
            System.out.println("Account: " + name);
            System.out.println("Requested: " + fmt.format(amount));
            System.out.println("Available: " + fmt.format(availableAmount));
        } else
            availableAmount = availableAmount - amount;
        return availableAmount;
    }


    public double deposit(double amount) {
        if (amount < 0)
        {
            System.out.println();
            System.out.println("Error: Deposit amount is invalid.");
            System.out.println(name + " " + fmt.format(amount));
        } else
            availableAmount = availableAmount + amount;
        return availableAmount;
    }


    public static void transfer(Account from, Account to, double amount) {
        from.withdraw(amount);
        to.deposit(amount);
    }
}
