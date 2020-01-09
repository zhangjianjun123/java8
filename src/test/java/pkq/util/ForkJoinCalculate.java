package pkq.util;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * 这个就是测是Fork Join框架在大数据量的时候速度
 * RecursiveTask   和RecursiveAction 区别就是一个带返回值  一个不带
 */

public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;
    private static final long Origin = 10000;

    public ForkJoinCalculate(long start, long end) {
        this.start=start;
        this.end=end;
    }

    @Override
    protected Long compute() {
        long lengs = end - start;

        if (lengs<= Origin) {
            System.out.println("9999-");
            long sum = 0;
            //这段代码的意思就是当数值比较小的时候就没有必要用fork Join
                          for (long i = start; i <= end; i++) {
                    sum += i;

            }
            return sum;
        }else {
            //这段的意思就是用fork join
            long mido  = (start+end) /2;
            ForkJoinCalculate sa = new ForkJoinCalculate(start,mido);
            sa.fork();
            System.out.println("------------------11111111111-----------");
            ForkJoinCalculate ea= new ForkJoinCalculate(mido +1,end);
            ea.fork();

            return sa.join() + ea.join();
        }

    }
/*

    public class ForkJoinCalculate extends RecursiveTask<Long>{

        /**
         *
         *//*

    private static final long serialVersionUID = 13475679780L;

    private long start;
    private long end;

    private static final long THRESHOLD = 10000L; //临界值

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override



    protected Long compute() {
        long length = end - start;

        if(length <= THRESHOLD){
            long sum = 0;

            for (long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        }else{
            long middle = (start + end) / 2;

            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork(); //拆分，并将该子任务压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle+1, end);
            right.fork();

            return left.join() + right.join();
            //这段的意思就是用fork join






        }

    }
*/

}
