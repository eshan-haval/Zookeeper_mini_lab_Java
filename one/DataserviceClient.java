package com.version.one;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class DataserviceClient {

	static ZooKeeper zk;
	static ConnectZooKeeper zkc;
	static List<String> znodeList = new ArrayList<String>();
	
	public static void main(String args[]) throws Exception{
		zkc = new ConnectZooKeeper();
		List<String> zookeeperMachines = new ArrayList<String>();
		
		zookeeperMachines.add("ec2-54-164-197-186.compute-1.amazonaws.com");
		zookeeperMachines.add("ec2-54-174-194-67.compute-1.amazonaws.com");
		zookeeperMachines.add("ec2-54-174-194-81.compute-1.amazonaws.com");
		zookeeperMachines.add("ec2-54-174-194-48.compute-1.amazonaws.com");
		
		zookeeperMachines.add("ec2-54-174-194-76.compute-1.amazonaws.com");
		String workingZookeeper="";
			for(int i=0;i<zookeeperMachines.size();i++)
			{
				zk = zkc.connect(zookeeperMachines.get(i).toString());
				if(zk.getState().toString().equalsIgnoreCase("CONNECTED"))
				{
					workingZookeeper = zookeeperMachines.get(i).toString();
					System.out.println(workingZookeeper+":I am alive");
					break;
				}
				
			}
			//zk.create(path, data, acl, createMode)
			  Stat temp = zk.exists("/dataservice", true);
			  if(temp.toString()!="")
			  {
				  byte[] b = "".getBytes();
				  //zk.create("/dataservice", b, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				  byte[] db1data = "shubham,jdbc:mysql://54.174.194.67:3306/test,root,root".getBytes();
				  
				  //zk.create("/dataservice/DB1", db1data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				  zk.setData("/dataservice/DB1", db1data, 6);
				  System.out.println("Data created for DB1");
				  byte[] db2data = "avi,jdbc:mysql://54.174.194.81:3306/test,root,root".getBytes();
				  zk.setData("/dataservice/DB2", db2data, 0);
				  System.out.println("Data created for DB2");
				  //zk.create("/dataservice/DB2", db2data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				  byte[] db3data = "sagar,jdbc:mysql://54.174.194.48:3306/test,root,root".getBytes();
				  zk.setData("/dataservice/DB3", db3data, 0);
				  System.out.println("Data created for DB3");
				  //zk.create("/dataservice/DB3", db3data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			  }
			
	}
}
