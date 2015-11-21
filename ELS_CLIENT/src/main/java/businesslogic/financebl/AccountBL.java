package businesslogic.financebl;

import java.util.ArrayList;

import dataservice.financedataservice.AccountDataService;
import businesslogicservice.financeblservice.AccountBLService;
import po.AccountPO;
import vo.AccountVO;

public class AccountBL implements AccountBLService{

	private AccountDataService accountData;
	private AccountPO po;
	
	/**
	 * 单个po转化为单个vo
	 * */
	public AccountVO poToVO(AccountPO po){
		String name=po.getName();
		double money=po.getMoney();
		AccountVO vo=new AccountVO(name, money);
		return vo;
	}
	
	/**
	 * po集合转化为vo集合
	 * */
	public ArrayList<AccountVO> posToVOs(ArrayList<AccountPO> pos){
		ArrayList<AccountVO> vos=new ArrayList<AccountVO>();
		for(AccountPO po: pos){
			AccountVO vo=poToVO(po);
			vos.add(vo);
		}
		return null;
	}
	
	/** 
	 * 添加账户
	 * */
	public int addAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		po=new AccountPO(vo.getName(), vo.getMoney());
		accountData.addAccount(po);
		return 0;
	}

	/**
	 * 删除账户：发生错误时返回1
	 * */
	public int deleteAccount(String name) {
		// TODO Auto-generated method stub
		po=accountData.findbyName(name);
		if(po == null){
			System.out.println("账户删除错误！");
			return 1;
		}
		else{
			accountData.deleteAccount(po);
			return 0;
		}
	}

	/**
	 * 修改账户
	 * */
	public int modifyAccount(AccountVO vo,String name) {
		// TODO Auto-generated method stub
		po=accountData.findbyName(vo.getName());
		if(po==null){
			System.out.println("修改账户失败！");
			return 1;
		}
		else{
			po=new AccountPO(vo.getName(), vo.getMoney());
			return 0;
		}		
	}

	/**
	 * 按名称查找并返回VO
	 * */
	public AccountVO findbyName(String name) {
		// TODO Auto-generated method stub
		po=accountData.findbyName(name);
		if(po==null){
			System.out.println("查询账户失败！");
			return null;
		}
		else{
			return poToVO(po);
		}
	}

	/**
	 * 按关键字查找
	 * */
	public ArrayList<AccountVO> findByKeyword(String s) {
		// TODO Auto-generated method stub
		ArrayList<AccountPO> pos=accountData.findByKeyword(s);
		if(pos==null){
			System.out.println("关键字查找失败！");
		}
		ArrayList<AccountVO> vos=posToVOs(pos);
		return vos;
	}

	
	/**
	 * 显示所有的账户列表
	 * */
	public ArrayList<AccountVO> showAll() {
		// TODO Auto-generated method stub
		return posToVOs(accountData.showAll());
	}
	
	public AccountDataService getAccountData(){
		return accountData;
	}


	
	

}
