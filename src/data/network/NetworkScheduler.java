package data.network;

import java.util.concurrent.*;

public final class NetworkScheduler {

    private static final int POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 4;
    private static final int TIMEOUT = 10;

    private final ThreadPoolExecutor executor =
            new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT, TimeUnit.SECONDS, new ArrayBlockingQueue<>(POOL_SIZE));

    public NetworkScheduler() {
    }

    public <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    }

    public void run(Runnable runnable) {
        executor.execute(runnable);
    }

    public Executor getExecutor() {
        return executor;
    }

}
