package en.ubbcluj.info.util;

import java.util.List;
import java.util.Set;

public class ThreadUtil {
    public static void printAllRunningThreads(){
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for(Thread t: threadSet){
            String name = t.getName();
            Thread.State state = t.getState();
            int priority = t.getPriority();
            String type = t.isDaemon() ? "Daemon" : "Normal";
            System.out.printf("%-20s \t %s \t %d \t %s\n", name, state, priority, type);
        }
        System.out.println("\n\n\n");
    }

    public static void startThreads(List<Thread> threadList){
        threadList.forEach(Thread::start);
    }

    public static void stopThreads(List<Thread> threadList){
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
