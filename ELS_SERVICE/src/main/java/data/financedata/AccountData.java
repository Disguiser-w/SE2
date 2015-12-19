package data.financedata;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.AccountPO;
import dataservice.financedataservice.AccountDataService;
import file.JXCFile;

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
	JXCFile file;
	public AccountData() throws RemoteException{
		super();
		file=new JXCFile("info/accountInfo/account.ser");
	}
	
	
	/**
	 * 添加账户：用户名不重复时可添加
	 * */
	public int addAccount(AccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(findbyName(po.getName())==null){
			file.write(po);
			return 0;
		}
		else
		return 1;
	}

	/**
	 * 删除账户,取出整个po，删除后再写入
	 * */
	public int deleteAccount(AccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("info/accountInfo/account.ser");
//		ArrayList<AccountPO> accountPOs=new ArrayList<AccountPO>();
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取account.ser失败");
			return 1;
		}
		for(Object o:os){
			AccountPO accountpo=(AccountPO) o;
			if(accountpo.getName().equals(po.getName())){
				os.remove(accountpo);
				break;
			}
		}
		
		file.writeM(os);
		return 0;
	}

	/**
	 * 修改账户
	 * */
	public int modifyAccount(AccountPO po, String name) throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("info/accountInfo/account.ser");
		if(findbyName(po.getName())==null){
			System.out.println("需要修改的账户不存在");
			return 1;
		}
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取文件account.ser失败");
			return 1;
		}
		for(int i=0;i<os.size();i++){
			AccountPO accountpo=(AccountPO) os.get(i);
			if(accountpo.getName().equals(po.getName())){
				accountpo.setName(name);
				break;
			}
		}
		file.writeM(os);
		return 0;
	}

	public AccountPO findbyName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("info/accountInfo/account.ser");
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取文件account.ser失败");
			return null;
		}
		for(Object o:os){
			AccountPO po=(AccountPO) o;
			if(po.getName().equals(name)){
				return po;
			}
		}
		//不存在该用户：返回null
		return null;
	}

	public ArrayList<AccountPO> findByKeyword(String s) throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("info/accountInfo/account.ser");
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取文件account.ser失败");
			return null;
		}
		ArrayList<AccountPO> right=new ArrayList<AccountPO>();
		for(Object o:os){
			AccountPO po=(AccountPO) o;
			if(po.getName().contains(s)){
				right.add(po);
			}
		}
		return right; 
	}

	public ArrayList<AccountPO> showAll() throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("info/accountInfo/account.ser");
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取文件account.ser失败");
			return null;
		}
		ArrayList<AccountPO> accountpos=new ArrayList<AccountPO>();
		for(Object o:os){
			AccountPO accountpo=(AccountPO) o;
			accountpos.add(accountpo);
		}
		return accountpos;
	}

	public int addMoney(String name, double m) throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("info/accountInfo/account.ser");
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取文件account.ser失败");
			return 1;
		}
		for(int i=0;i<os.size();i++){
			AccountPO accountpo=(AccountPO) os.get(i);
			if(accountpo.getName().equals(name)){
				accountpo.addMoney(m);
				break;
			}
		}
		file.writeM(os);
		return 0;
	}

	public int delMoney(String name, double m) throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("info/accountInfo/account.ser");
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取文件account.ser失败");
			return 1;
		}
		for(int i=0;i<os.size();i++){
			AccountPO accountpo=(AccountPO) os.get(i);
			if(accountpo.getName().equals(name)){
				accountpo.delMoney(m);
				break;
			}
		}
		file.writeM(os);
		return 0;
	}
	
	public static void main(String[] args) throws RemoteException{
		AccountData data=new AccountData();
		
////		AccountPO po=data.findbyName("刘钦");
////		System.out.println("Data中的"+po.getMoney());
		AccountPO po=new AccountPO("本宝宝", 200);
//		data.modifyAccount(po, "其他宝宝");
		data.addAccount(po);
		ArrayList<AccountPO> pos=data.showAll();
		for(AccountPO p:pos){
			System.out.println("Name: "+p.getName()+" "+p.getMoney());
		}
//		
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
	
		
//		AccountData data;
//		----------------------------------------------------------
//		try {
//			data = new AccountData();
//        --------------------------------------------------------------
			
			AccountPO po1=new AccountPO("鼓楼", 100);
			AccountPO po2=new AccountPO("总账",10000);
			AccountPO po3=new AccountPO("玄武",100);
			AccountPO po4=new AccountPO("钟楼",1000);
			data.addAccount(po1);
			data.addAccount(po2);
			data.addAccount(po3);
			data.deleteAccount(po1);
			data.addAccount(po4);
			
//			----------------------------------------------------------------------------
//			ArrayList<AccountPO> pos=data.showAll();
//			for(AccountPO p:pos){
//				System.out.println("Name: "+p.getName());
//			}
//			System.out.println("---------------------------------------------------");
//			ArrayList<AccountPO> pos_key=data.findByKeyword("楼");
//			for(AccountPO p:pos_key){
//				System.out.println("Name:  "+p.getName());
//			}
//			------------------------------------------------------------------------------
			
 //			AccountPO po=data.findbyName("鼓楼");
//			System.out.println("Name: "+po.getName()+"Money: "+po.getMoney());
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
