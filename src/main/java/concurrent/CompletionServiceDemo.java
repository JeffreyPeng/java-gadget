package concurrent;

import java.util.concurrent.*;

public class CompletionServiceDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<Object> future = executorService.submit(new Callable<Object>() {
            public Object call() throws Exception {
                return null;
            }
        });
        // more in foreach
        future.get();
        // compare
        CompletionService completionService = new ExecutorCompletionService(executorService);
        completionService.submit(new Callable() {
            public Object call() throws Exception {
                return null;
            }
        });
        // more in foreach
        completionService.take().get();
    }
}
