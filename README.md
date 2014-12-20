Zookeeper_mini_lab_Java
=======================
Setup of Cluster :
1) We created 5 server machines on EC2 and connected them using ssh
connection and installed Zookeeper on each one of them.
Snapshot of zookeeper extraction and directory creation.Snapshot ODF Installation Steps
Snapshot of configuration file.
We created zoo.cfg that consist of configuration of all the other servers
connected to the the zookeeper and port configuration on which they were
listening.Snapshot of the directories created :- Data, Logs, Conf
Snaphot of Myid file .Snapshot of Starting the server and checking the status of the node.
Snapshot of follower machine in zookeeper.Snapshot of Leader machine in zookeeper.Snapshots of failed server scenario.
New Server elected as leader after the leader fails.We stopped the Leader manually to check if zookeeper elect the new leader
automatically. So based on algorithm like PAXOS it elects for new leader.
Client connected to zookeeper to get configuration details
Our understanding of Zookeeper-:
Apache ZooKeeper - It is a tool to manage, discover and register different
services working on different machines on the distributed enviorment. It is
one of the most widely used member in technology stack when we have to
deal with distributed system.
Its architecture supports HA through services that are redundant. The clients
can thus ask another master or elected leader if the first fails to answer.
Nodes store their data in a hierarchical name space, much like a tree data
structure. Clients can read and write from/to the nodes and in this way have a
shared configuration service.PART 2
1) Data Service:
(1) Data service DB1 (MySQL)
(2) Data service DB2 (MySQL)(3) Data service DB3 (MySQL)
(a) Data service client(b) Data service output
(c) Data service output on command line2) Service Discovery and dynamic configuration:
(1) Ephemeral creation
(2) Ephemeral Appeared and then disappeared (both shown in pic)(a) Read Client
(b) Write Client(c) Output
Useful Notes:
Zookeeper Nodes
ec2-54-164-197-186.compute-1.amazonaws.com
ec2-54-174-194-67.compute-1.amazonaws.com
ec2-54-174-194-81.compute-1.amazonaws.com
ec2-54-174-194-48.compute-1.amazonaws.com
ec2-54-174-194-76.compute-1.amazonaws.com
DB/MySql Nodes
54.174.194.67 DB=test
54.174.194.81 DB=test
54.174.194.48 DB=test
Lessons Learnt:
 Zookeeper works on the idea of PAXOS algorithm for leader election
and load distribution. The consensus algorithm it uses is a process
where all the interconnected nodes in zookeeper agree on one result
among the group of participants. Zookeeper allows distributed processes to co-ordinate with each other
through a shared hierarchal name space of data registers.
 Learned about zookeeper architecture and its API’s. Learned how to
handle data service and application service layer using zookeeper
API’s .
