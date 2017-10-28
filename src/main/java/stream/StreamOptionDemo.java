package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by cody81314 on 2017/10/28.
 */
public class StreamOptionDemo {

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

        demo1(transactions);
        demo2(transactions);
        demo3(transactions);
        demo4(transactions);
        demo5(transactions);
        demo6(transactions);
        demo7(transactions);
        demo8(transactions);
    }

    private static void demo1(List<Transaction> transactions) {
        System.out.println("=======================demo1=========================");
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);
        System.out.println("=====================================================");
    }

    private static void demo2(List<Transaction> transactions) {
        System.out.println("=======================demo2=========================");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
        System.out.println("=====================================================");
    }

    private static void demo3(List<Transaction> transactions) {
        System.out.println("=======================demo3=========================");
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
        System.out.println("=====================================================");
    }

    private static void demo4(List<Transaction> transactions) {
        System.out.println("=======================demo4=========================");
        String namesStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(namesStr);
        System.out.println("=====================================================");
    }

    private static void demo5(List<Transaction> transactions) {
        System.out.println("=======================demo5=========================");
        boolean hasMilan = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .anyMatch(trader -> "Milan".equals(trader.getCity()));
        System.out.println(hasMilan);
        System.out.println("=====================================================");
    }

    private static void demo6(List<Transaction> transactions) {
        System.out.println("=======================demo6=========================");
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        System.out.println("=====================================================");
    }

    private static void demo7(List<Transaction> transactions) {
        System.out.println("=======================demo7=========================");
        Optional<Transaction> maxTrans = transactions.stream()
                .max(Comparator.comparing(Transaction::getValue));
        System.out.println(maxTrans.get());
        System.out.println("=====================================================");
    }

    private static void demo8(List<Transaction> transactions) {
        System.out.println("=======================demo8=========================");
        Optional<Transaction> minTrans = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println(minTrans.get());
        System.out.println("=====================================================");
    }

    private static class Trader {
        private final String name;
        private final String city;

        public Trader(String name, String city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        @Override
        public String toString() {
            return "Trader{" +
                    "name='" + name + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }

    private static class Transaction {
        private final Trader trader;
        private final int year;
        private final int value;

        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader() {
            return trader;
        }

        public int getYear() {
            return year;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "trader=" + trader +
                    ", year=" + year +
                    ", value=" + value +
                    '}';
        }
    }
}
