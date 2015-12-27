package data.financedata;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.FileGetter;
import po.AccountPO;
import dataservice.financedataservice.AccountDataService;

public class AccountData extends UnicastRemoteObject implements AccountDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//本宝宝yy了一个读写文件的类
	/**
	 * JXCFile中包括读文件，写文件（修改后再写文件），写入单对象
	 * 那我参数为string的怎么办2333
	 * */
//	JXCFile file;
	public AccountData() throws RemoteException{
		super();
	}
	
	/**
	 * 获取文件中的account
	 * */
	public ArrayList<AccountPO> getAccountPOs(){
		String path = "accountInfo/account.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<AccountPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<AccountPO> accountPOs = (ArrayList<AccountPO>) in.readObject();
			in.close();
			return accountPOs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 将po存入文件
	 * */
	public int saveAccountPOs(ArrayList<AccountPO> pos){
		String path = "accountInfo/account.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(pos);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	/**
	 * 添加账户：用户名不重复时可添加
	 * */
	public int addAccount(AccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
        ArrayList<AccountPO> accountPOs = getAccountPOs();
		
		for(int i=0; i<accountPOs.size(); i++){
			AccountPO p = accountPOs.get(i);
			if (po.getName().equals(p.getName())) {
				System.out.println("账户已存在");
				return -1;
			}
		}
		accountPOs.add(po);
		saveAccountPOs(accountPOs);
		return 0;

	}

	/**
	 * 删除账户,取出整个po，删除后再写入
	 * */
	public int deleteAccount(AccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		int isExsit=-1;
	   ArrayList<AccountPO> accountPOs = getAccountPOs();
	  for(int i=0;i<accountPOs.size();i++){
		if(accountPOs.get(i).getName().equals(po.getName())){
			accountPOs.remove(i);
			isExsit = 0;
			break;
		 }
	   }
	  saveAccountPOs(accountPOs);
	  return isExsit;
	}
		
	/**
	 * 修改账户
	 * */
	public int modifyAccount(AccountPO po, String name) throws RemoteException {
		// TODO Auto-generated method stub
		int isExsit = -1 ;
		ArrayList<AccountPO> accountPOs = getAccountPOs();
		for(int i=0;i<accountPOs.size();i++){
			if(accountPOs.get(i).getName().equals(po.getName())){
				isExsit = 0;
				accountPOs.get(i).setName(name);
			}
		}
		saveAccountPOs(accountPOs);
		return isExsit;
	}

	public AccountPO findbyName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<AccountPO> accountPOs = getAccountPOs();
		for(int i=0;i<accountPOs.size();i++){
			if(accountPOs.get(i).getName().equals(name)){
			return accountPOs.get(i);
			}
		}
		return null;
	}

	public ArrayList<AccountPO> findByKeyword(String s) throws RemoteException {
	     ArrayList<AccountPO> accountPOs = getAccountPOs();
	     ArrayList<AccountPO> result = new ArrayList<AccountPO>();
		for(int i=0;i<accountPOs.size();i++){
			if(accountPOs.get(i).getName().contains(s)){
				result.add(accountPOs.get(i));
			}
		}
		return result;
		
	}

	public ArrayList<AccountPO> showAll() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<AccountPO> accountPOs = getAccountPOs();
		if(accountPOs!=null){
		return accountPOs;
		}
		else{
			return null;
		}
	}

	public int addMoney(String name, double m) throws RemoteException {
		// TODO Auto-generated method stub
		int isExsit=-1;
		 ArrayList<AccountPO> accountPOs = getAccountPOs();
		for(int i=0;i<accountPOs.size();i++){
			if(accountPOs.get(i).getName().equals(name)){
				accountPOs.get(i).addMoney(m);
				isExsit = 0;
			}
		}
		saveAccountPOs(accountPOs);
		return isExsit;

	}

	public int delMoney(String name, double m) throws RemoteException {
		// TODO Auto-generated method stub
		int isExsit=-1;
		 ArrayList<AccountPO> accountPOs = getAccountPOs();
		for(int i=0;i<accountPOs.size();i++){
			if(accountPOs.get(i).getName().equals(name)){
				accountPOs.get(i).delMoney(m);
				isExsit = 0;
			}
		}
		saveAccountPOs(accountPOs);
		return isExsit;
	}
	
	public static void main(String[] args) throws RemoteException{
		AccountData data=new AccountData();

		AccountPO po = data.findbyName("王丽莉");
		System.out.println(po.getMoney());
//		data.addMoney("王丽莉", 200);
		ArrayList<AccountPO> pos=data.showAll();
		ArrayList<AccountPO> hh=data.findByKeyword("营业厅");
		System.out.println(hh.get(0).getName());
		for(AccountPO p:pos){
			System.out.println("Name: "+p.getName()+" "+p.getMoney());
		}
	
//		AccountPO po=data.findbyName("刘钦");
//		data.deleteAccount(po);
//		System.out.println("----------------------------------------");
//		ArrayList<AccountPO> pos1=data.showAll();
//		for(AccountPO p:pos1){
//			System.out.println("Name: "+p.getName());
//		}
		/*try{
			System.setProperty("java.rmi.server.hostname", "172.26.209.182");
			AccountDataService data=new AccountData();
			LocateRegistry.createRegistry(8888);
//			//绑定RMI名称进行发布
			Naming.rebind("rmi://172.26.209.182:8888/AccountDataService", data);
			System.out.println("Service start!");
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		*/
	
	/*		ArrayList<AccountPO> pos=data.showAll();
			for(AccountPO p:pos){
				System.out.println("Name: "+p.getName());
			}
			System.out.println("---------------------------------------------------");
			ArrayList<AccountPO> pos_key=data.findByKeyword("楼");
			for(AccountPO p:pos_key){
				System.out.println("Name:  "+p.getName());
			}
			*/
	
		

		
	}

}
