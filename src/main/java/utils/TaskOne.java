package utils;

import java.util.concurrent.Callable;

public class TaskOne implements Callable<String>{
	
	// 模拟中间人，存放任务结果  
    private StringBuilder sb;  
  
    public TaskOne(StringBuilder sb) {  
        this.sb = sb;  
    } 

	@Override
	public String call() throws Exception {
		synchronized (sb) {  
            // 这个任务要等另一个任务完成，中间人没有获取到另一个任务的结果的时候  
            // 此任务要一直等待  
            while (sb.length() == 0) {  
                sb.wait();  
            }  
        }  
        sb.append("哈哈，task one终于等到task two运行完成了~~ task one结束");  
        return sb.toString();  
	}

}
