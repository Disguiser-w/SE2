package dataservice.financedataservice;

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
