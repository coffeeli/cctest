package utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
	// 注意exec是单线程  
    static final ExecutorService exec = Executors.newFixedThreadPool(1); 
    
    public static void main(String[] args) throws Exception {  
        // 模拟中间人，存放任务结果  
        StringBuilder sb = new StringBuilder();  
  
        TaskOne taskOne = new TaskOne(sb);  
        TaskTwo taskTwo = new TaskTwo(sb);  
  
        long begin = System.currentTimeMillis();  
        // 先提交 taskOne， taskOne要等待taskTwo的运行结果  
        Future<String> one = exec.submit(taskOne);  
        // 再把taskTwo放入线程池运行  
        Future<String> two = exec.submit(taskTwo); 
        // 获取taskOne结果，但如果exec是个单线程的线程池  
        // taskOne等待taskTwo的结果，可taskTwo却要等到taskOne运行结束  
        // 才可以运行，造成了死锁，taskTwo永远不能获得cpu的运行,成饥饿状态  
        String s1 = one.get();  
        String s2 = two.get();  
  
        System.out.println(s2);  
        System.out.println(s1);  
        long end = System.currentTimeMillis();  
        System.out.println(end - begin);  
  
        // exec.shutdownNow();  
    }  
}
