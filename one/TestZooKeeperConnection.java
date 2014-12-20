package com.version.one;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.zookeeper.ZooKeeper;

public class TestZooKeeperConnection {

	static ZooKeeper zk;


	static ConnectZooKeeper zkc;
	static List<String> znodeList = new ArrayList<String>();
	static List<String> znodeDBList = new ArrayList<String>();
	static String userDB;
	static String strUserData;
	static  String driver = "com.mysql.jdbc.Driver";
	
	public static void main(String args[]) throws Exception
	{
		Scanner in = new Scanner(System.in);
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
			
		String continuemenu="no";
		
		do{
			System.out.println(zk);
		System.out.print("Please enter username :");	
		String s = in.nextLine();
		System.out.println("String is:"+s);
		String stringDB="";
		
		
		
		znodeList = zk.getChildren("/dataservice", true);
		System.out.println("users are"+znodeList);
		//find user
		if(znodeList.size()==0)
		{
			System.out.println("No Data Exists");
		}
		else
		{
		for(String str :znodeList)
		{
			byte[] bytearray = zk.getData("/dataservice/"+str,true, zk.exists("/dataservice/"+str,true));
			int counter=0;
			char[] tempchar=new char[bytearray.length];	
			for(byte b :bytearray)
				{
					//System.out.print((char)b);
				tempchar[counter]=(char)b;
					counter++;
				}
			String znodeData = String.valueOf(tempchar);
			System.out.println(znodeData);
			String[] newznodeData = znodeData.split(",");
			if(newznodeData[0].equalsIgnoreCase(s))
			{
				userDB = str;
				strUserData = znodeData;
				break;
			}
		}
		
		System.out.println("User is on DB:"+userDB);
		String[] userData = strUserData.split(",");
		String currentUser = userData[0];
		String jdbcURL = userData[1];
		String jdbcUsername = userData[2];
		String jdbcPwd = userData[3];
		 String myDriver = "com.mysql.jdbc.Driver";
		 Class.forName(myDriver).newInstance();
		 
		 System.out.println(jdbcURL);
		 System.out.println(jdbcUsername);
		 System.out.println(jdbcPwd);
		 //String eshan = "jdbc:mysql://54.174.194.67:3306/test";
		 Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPwd);
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery("SELECT * FROM userinfo");
		 while (rs.next()) {
			  String lastName = rs.getString("name");
			  System.out.println("name:"+lastName + "\n");
			}
		/**
		byte[] bytearray = zk.getData("/dataservice/"+s,true, zk.exists("/dataservice/"+s,true));
		char[] tempchar=new char[bytearray.length];	
		int counter=0;
		for(byte b :bytearray)
			{
				//System.out.print((char)b);
				tempchar[counter]=(char)b;
				counter++;
			}
		String znodeData = tempchar.toString();
		System.out.println(znodeData);
		/**
		//zk = zkc.connect("ec2-54-174-194-76.compute-1.amazonaws.com");
		zk.getState();
		znodeList = zk.getChildren("/dataservice", true);
		//System.out.println("zookeeper data:"+zk.getData("/shubham",true, zk.exists("/shubham",true)));
		
		System.out.println();
		
		for(String znode : znodeList)
		{
			System.out.println("znode"+znode);
			String temp = "/dataservice/"+znode;
			System.out.println("zookeeper data:");
			byte[] bytearray = zk.getData(temp,true, zk.exists(temp,true));
			for(byte b :bytearray)
			{
				System.out.print((char)b);
			}
		}
		**/
		}
			System.out.println("Enter yes to continue anything else to terminate");
			Scanner in2 = new Scanner(System.in);
			continuemenu = in2.nextLine();
			if(!continuemenu.equalsIgnoreCase("yes"))
			{
				System.out.println("terminated");
				zk.close();
			}
		}while(continuemenu.equalsIgnoreCase("yes"));
	}
}
