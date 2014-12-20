package com.version.webservice;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ConnectZooKeeper {
	
	ZooKeeper zk;
	CountDownLatch conSignal = new CountDownLatch(1);
	
	public ZooKeeper connect(String Host) throws Exception 
	{
		zk = new ZooKeeper(Host, 9000, new Watcher(){
											public void process(WatchedEvent event) {
												if(event.getState() == KeeperState.SyncConnected){
														conSignal.countDown();
												}
											}
		});
				conSignal.await();
				return zk;
		
	}
	
	public void closeConnection() throws Exception{
		zk.close();
	}

}
