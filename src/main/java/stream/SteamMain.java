package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SteamMain {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // 1 Find all transactions in the year 2011 and sort them by value (small to high).
        System.out.println(1);
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        // 2 What are all the unique cities where the traders work?
        System.out.println(2);
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        // 3 Find all traders from Cambridge and sort them by name.
        System.out.println(3);
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        // 4 Return a string of all traders’ names sorted alphabetically.
        System.out.println(4);
        String result = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted(String::compareTo)
                .reduce("", (a, b) -> a + b);
        System.out.println(result);

        // 5 Are any traders based in Milan?
        System.out.println(5);
        boolean any = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .anyMatch(t -> t.getCity().equals("Milan"));
        System.out.println(any);

        // 6 Print the values of all transactions from the traders living in Cambridge.
        System.out.println(6);
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 7 What’s the highest value of all the transactions?
        System.out.println(7);
        transactions.stream()
                .max(Comparator.comparing(Transaction::getValue))
                .map(Transaction::getValue)
                .ifPresent(System.out::println);

        // 8 Find the transaction with the smallest value.
        System.out.println(8);
        transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .ifPresent(System.out::println);

    }
}
