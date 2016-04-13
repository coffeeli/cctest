package utils;

import java.util.concurrent.Callable;

public class TaskTwo implements Callable<String>{

	 // 模拟中间人，任务结果放在这个里面  
    private StringBuilder sb;  
    public TaskTwo(StringBuilder sb) {  
        this.sb = sb;  
    }  
    
	@Override
	public String call() throws Exception {
		 synchronized (sb) {  
	            // 任务运行结果给中间人  
	            sb.append("task two result");  
	            sb.notify();  
	        }  
	        return sb.toString(); 
	}

}
