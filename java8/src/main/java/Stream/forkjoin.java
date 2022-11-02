package Stream;

import java.util.concurrent.RecursiveTask;

public class forkjoin extends RecursiveTask<Long> {
    private long start;
    private long end;
    private static final long THRESHOLD=100000L;

    public forkjoin(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = start-end;
        if(length<=THRESHOLD){
            long sum=0;
            for (long i = start; i <=end; i++) {
                sum+=i;
            }
            return sum;
        }
        else {
            long middle=(start+end)/2;
            forkjoin left = new forkjoin(start, middle);
            left.fork();//拆分子任务，同时压入线程队列
            forkjoin right = new forkjoin(middle + 1, end);
            right.fork();
            return left.join()+right.join();
        }
    }
}
