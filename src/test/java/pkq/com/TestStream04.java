package pkq.com;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.exec.Trader;
import pkq.exec.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class TestStream04 {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
                                new Transaction(raoul, 2012, 1000),
                                new Transaction(raoul, 2011, 400),
                                new Transaction(mario, 2012, 710),
                                new Transaction(mario, 2012, 700),
                                new Transaction(alan, 2012, 950));

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));
        //1,找出2011年发生的所有交易,并按交易额排序(从低到高)
        transactions.stream()
                .filter((s) -> s.getYear() == 2011)
                .sorted((x, y) -> Integer.compare(x.getValue(), y.getValue()))
                .forEach(System.out::println);
        System.out.println("----------------1---------------------");
        // 2.交易员都在哪些不同的城市工作过?
        transactions.stream()
                .map((x) -> x.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
        System.out.println("----------------2---------------------");

        // 3,查找所有来自剑桥的交易员,并按姓名排序 ,
        transactions.stream()
                .filter((x) -> x.getTrader().getCity().equals("Cambridge"))
                .map((x) -> x.getTrader().getName())
                .sorted((x, y) -> x.compareTo(y))
                .distinct()
                .forEach(System.out::println);

        System.out.println("-----------3-------------------");


        // 4,返回所有交易员的姓名字符串,按字母顺序排序
        String collect = transactions.stream()
                .map((x) -> x.getTrader().getName())
                .sorted((x, y) -> x.compareTo(y))
                .collect(Collectors.joining());
        System.out.println(collect);

        String reduce = transactions.stream()
                .map((x) -> x.getTrader().getName())
                .sorted()
                .reduce("", String::concat);


        System.out.println("-----------4-------------------");

        // 5．有没有交易员是在米兰工作的？
        transactions.stream()
                .filter((x) -> x.getTrader().getCity().equals("Milan"))
                .distinct()
                .forEach(System.out::println);

        boolean milan = transactions.stream()
                .anyMatch((x) -> x.getTrader().getCity().equals("Milan"));
        System.out.println(milan);
        transactions.stream()
                .map((x) -> x.getTrader().getName())
                .flatMap(TestStream04::getStengeee)
                .sorted((x, y) -> x.compareToIgnoreCase(y))
                .forEach(System.out::print);
        System.out.println("-----------5-------------------");

// 6.打印生活在剑桥的交易员的所有交易额
        Optional<Integer> cambridge = transactions.stream()
                .filter((x) -> x.getTrader().getCity().equals("Cambridge"))
                .map((x) -> x.getValue())
                .reduce(Integer::sum);
        System.out.println(cambridge.get());
        Integer cambridge1 = transactions.stream()
                .filter((x) -> x.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.summingInt((x) -> x.getValue()));
        System.out.println(cambridge1);
        IntSummaryStatistics cambridge2 = transactions.stream()
                .filter((x) -> x.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.summarizingInt(Transaction::getValue));

        System.out.println(cambridge2.getSum());
        System.out.println("-----------6-------------------");

        // 7,所有交易中,最高的交易額是多少
        Optional<Transaction> max = transactions.stream()
                .max((x, y) -> Integer.compare(x.getValue(), y.getValue()));

        System.out.println( max.get().getValue()+"---------------------");
        Optional<Transaction> collect1 = transactions.stream()
                .collect(Collectors.maxBy((x, y) -> Integer.compare(x.getValue(), y.getValue())));
        IntSummaryStatistics collect2 = transactions.stream()
                .collect(Collectors.summarizingInt((Transaction::getValue)));
        System.out.println(collect2.getMax());
        // 8,找到交易额最小的交易
    }
    public static Stream<String> getStengeee(String us) {
        List<String> ss = new ArrayList<>();
        for (Character c : us.toCharArray()) {
            ss.add(c.toString());
        }
        return ss.stream();
    }
}


