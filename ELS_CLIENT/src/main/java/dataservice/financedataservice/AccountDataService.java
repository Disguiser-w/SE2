package dataservice.financedataservice;
//
//import java.rmi.Remote;
//import java.util.ArrayList;
//
//import po.AccountPO;
//
//public interface AccountDataService extends Remote{
//	   //增加账户
//		public int addAccount(AccountPO po);
//		//删除账户
//		public int deleteAccount(AccountPO po);
//		//修改账户
//		public int modifyAccount(AccountPO po,String name);
//		//通过账户名查找
//		public AccountPO findbyName(String name);
//		//通过关键字查找
//		public ArrayList<AccountPO> findByKeyword(String s);
//		//输出所有账户
//		public ArrayList<AccountPO> showAll();
//		//金额++（入款单）
//		public int  addMoney(String name,double money);
//		//金额--（付款单）
//		public int delMoney(String name,double money);
//		public String test();
//
//}
//*/


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.AccountPO;

public interface AccountDataService extends Remote{
	//增加一条AccountPO
	public int addAccount(AccountPO po) throws RemoteException;
	//删除一条AccountPO
	public int deleteAccount(AccountPO po)throws RemoteException;
	//修改某个AccountPO
	public int modifyAccount(AccountPO po,String name)throws RemoteException;
	//通过账户名查找某个AccountPO
	public AccountPO findbyName(String name)throws RemoteException;
	//通过关键字查找某个AccountPO
	public ArrayList<AccountPO> findByKeyword(String s)throws RemoteException;
	//输出所有的AccountPO记录
	public ArrayList<AccountPO> showAll()throws RemoteException;
	//金额++
	  public int addMoney(String name,double m) throws RemoteException; 
	  //金额--
	    public int delMoney(String name,double m) throws RemoteException;

}

