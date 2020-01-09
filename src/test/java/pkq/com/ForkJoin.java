package pkq.com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pkq.util.ForkJoinCalculate;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.DuplicateFormatFlagsException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@SpringBootTest
class ForkJoin {
  @Test
    public void test01(){
     /// Instant now = Instant.now();
      ForkJoinPool pj = new ForkJoinPool();
      ForkJoinTask<Long> ft = new ForkJoinCalculate(0, 100000000L);
      Long invoke = pj.invoke(ft);
      System.out.println(invoke);
      //Instant end = Instant.now();
     // System.out.println(Duration.between(now,end).toMillis());

  }

    public static void main(String[] args) {
        long sum = 0L;
        for (long i = 0; i <= 100000000L; i++) {
          sum += i;

        }
        System.out.println(sum);
           }
}


