package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.datafactory.DataFactory;
import businesslogic.financebl.controller.FinanceMainController;
import dataservice.financedataservice.AccountDataService;
import po.AccountPO;
import vo.AccountVO;

public class AccountBL {

	
	private AccountDataService accountData;
	
	public AccountBL()  {
				try {
					accountData=DataFactory.getAccountData();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	}

	/** 
	 * 添加账户
	 * 成功添加返回0
	 * */
	public int addAccount(AccountVO vo) {
		AccountPO po=FinanceMainController.avoToPO(vo);
		try {
			return accountData.addAccount(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("添加账户异常");
			return 2;
		}
	}

	/**
	 * 删除账户：发生错误时返回1
	 * */
	public int deleteAccount(String name) {
		// TODO Auto-generated method stub
		try {
			AccountPO po=accountData.findbyName(name);
			if(po == null){
				System.out.println("账户删除错误！");
				return 1;
			}
			else{
				try {
					accountData.deleteAccount(po);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 2;
		}
		
	}

	/**
	 * 修改账户
	 * */
	public int modifyAccount(AccountVO vo,String name) {
		// TODO Auto-generated method stub
		try {
			AccountPO po=accountData.findbyName(vo.name);
			if(po==null){
				System.out.println("修改账户失败！");
				return 1;
			}
			else{
				accountData.modifyAccount(po, name);
				return 0;
			}		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 2;
		}
	
	}

	/**
	 * 按名称查找并返回VO
	 * */
	public AccountVO findbyName(String name) {
		// TODO Auto-generated method stub
		try {
			AccountPO po=accountData.findbyName(name);
			if(po==null){
				System.out.println("查询账户失败！");
				return null;
			}
			else{
				return FinanceMainController.apoToVO(po);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("查找账户失败");
			return null;
		}
	
	}

	/**
	 * 按关键字查找
	 * */
	public ArrayList<AccountVO> findByKeyword(String s) {
		// TODO Auto-generated method stub
		ArrayList<AccountPO> pos;
		try {
			pos = accountData.findByKeyword(s);
			if(pos==null){
				System.out.println("关键字查找失败！");
			}
			ArrayList<AccountVO> vos=FinanceMainController.aposToVOs(pos);
			return vos;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	
	}

	
	/**
	 * 显示所有的账户列表
	 * */
	public ArrayList<AccountVO> showAll() {
		// TODO Auto-generated method stub
		try {
			if(accountData.showAll()!=null){
			return FinanceMainController.aposToVOs(accountData.showAll());
			}
			else{
				return null;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public AccountDataService getAccountData(){
		return accountData;
	}
	
	public int addMoney(String name,double money){
		try {
			return accountData.addMoney(name, money);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	}
	
	public int delMoney(String name,double money){
		try {
			return accountData.delMoney(name, money);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	}
	

	

	
/*	public static void main(String[] args){
		try {
			AccountDataService accountData=(AccountDataService)Naming.lookup("rmi://172.26.209.182:8888/AccountDataService");
//			ArrayList<AccountPO> pos=accountData.showAll();
//			for(AccountPO p:pos){
//				System.out.println("Name: "+p.getName());
//			}
//			AccountPO po=new AccountPO("hh", 200);
			ArrayList<AccountPO> pos=accountData.showAll();
			for(AccountPO p:pos){
				System.out.println("Name："+p.getName());
			}
			System.out.println("------------------------------------------------------------------------------");
			ArrayList<AccountPO> poss=accountData.findByKeyword("楼");
			for(AccountPO p:poss){
				System.out.println("Name："+p.getName());
			}
//			int n=accountData.addAccount(po);
//			System.out.println(n);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	
//	/**
//	 * 单个po转化为单个vo
//	 * */
//	public AccountVO poToVO(AccountPO po){
//		String name=po.getName();
//		double money=po.getMoney();
//		AccountVO vo=new AccountVO(name, money);
//		return vo;
//	}
//	
//	/**
//	 * po集合转化为vo集合
//	 * */
//	public ArrayList<AccountVO> posToVOs(ArrayList<AccountPO> pos){
//		ArrayList<AccountVO> vos=new ArrayList<AccountVO>();
//		for(AccountPO po: pos){
//			AccountVO vo=poToVO(po);
//			vos.add(vo);
//		}
//		return null;
//	}
//	/**
//	 * 单个vo转化为po
//	 * */
//	public AccountPO voToPO(AccountVO vo){
//		String name=vo.getName();
//		double money=vo.getMoney();
//		AccountPO po=new AccountPO(name, money);
//		return po;
//	}
//	
	
	

}
