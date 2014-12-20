package com.version.webservice;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			  Stat temp = zk.exists("/webservice", true);
			  if(temp.toString()!="")
			  {
				  byte[] b = "".getBytes();
				  //zk.create("/webservice", b, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				  byte[] db1data = "shubham,jdbc:mysql://54.174.194.67:3306/test,root,root".getBytes();
				  
				  zk.create("/webservice/DB1EphemralNode", db1data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
				  //zk.setData("/webservice/DB1", db1data, 6);
				  System.out.println("Data created for DB1");
				  System.out.println("Do you want to terminate connection, as it will destory the ephemral znode just created. y / n");
				  Scanner in = new Scanner(System.in);
				  String s = in.nextLine();
				  if(s.equalsIgnoreCase("y"))
				  {
					  zk.close();
					  DataserviceClient2.callnext();
				  }
				  else
				  {
					//do nothing  
				  }
				  
			  }
			
	}
	
	public static void callfirst() throws Exception{
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
			  Stat temp = zk.exists("/webservice", true);
			  if(temp.toString()!="")
			  {
				  byte[] b = "".getBytes();
				  //zk.create("/webservice", b, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				  byte[] db1data = "shubham,jdbc:mysql://54.174.194.67:3306/test,root,root".getBytes();
				  
				  zk.create("/webservice/DB1EphemralNode", db1data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
				  //zk.setData("/webservice/DB1", db1data, 6);
				  System.out.println("Data created for DB1");
				  System.out.println("Do you want to terminate connection, as it will destory the ephemral znode just created. y / n");
				  Scanner in = new Scanner(System.in);
				  String s = in.nextLine();
				  if(s.equalsIgnoreCase("y"))
				  {
					  zk.close();
					  DataserviceClient2.callnext();
				  }
				  else
				  {
					//do nothing  
				  }
				  
			  }
			
	}
}
