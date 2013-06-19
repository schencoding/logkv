package com.hp.hpl.logkv.utiltest;

import com.hp.hpl.logkv.util.WorkQueue;

public class WorkQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WorkQueue wq = new WorkQueue(5);
		for(int i = 0; i < 20; i++){
			MyThread thread = new MyThread(i);
			wq.execute(thread);
			if(i % 8 == 0){
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	public static class MyThread implements Runnable{
		
		private int threadId;
		
		public MyThread(int id){
			this.threadId = id;
		}

		@Override
		public void run() {
			System.out.println("["+ System.currentTimeMillis() + "]Running " + threadId);
			try {
				Thread.sleep(2*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
			System.out.println("["+ System.currentTimeMillis() + "]Finished " + threadId);
		}
		
	}

}
