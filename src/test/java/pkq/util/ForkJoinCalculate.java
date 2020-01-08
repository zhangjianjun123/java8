package pkq.util;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * 这个就是测是Fork Join框架在大数据量的时候速度
 * RecursiveTask   和RecursiveAction 区别就是一个带返回值  一个不带
 */
public class ForkJoinCalculate extends RecursiveTask<Integer> {

    private int start;
    private int end;
    private static final int Origin = 10000;

    public ForkJoinCalculate(int start, int end) {
        this.start=start;
        this.end=end;
    }

    @Override
    protected Integer compute() {
        int lengs = end - start;
        int sum = 0;
        if (lengs< Origin) {
            //这段代码的意思就是当数值比较小的时候就没有必要用fork Join
            for (int i = start; i < end; i++) {
                sum += i;

            }
            return sum;
        }else {
            //这段的意思就是用fork join
            int summ  = start+end;
            ForkJoinCalculate sa = new ForkJoinCalculate(start,summ);
            sa.fork();
            ForkJoinCalculate ea= new ForkJoinCalculate(summ+1,end);
            ea.fork();
            return sa.join()+ea.join();
        }

    }

}
