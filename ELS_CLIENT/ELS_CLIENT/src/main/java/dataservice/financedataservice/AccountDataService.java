package dataservice.financedataservice;

import java.util.ArrayList;

import po.AccountPO;

public interface AccountDataService {
	   //增加账户
		public int addAccount(AccountPO po);
		//删除账户
		public int deleteAccount(AccountPO po);
		//修改账户
		public int modifyAccount(AccountPO po,String name);
		//通过账户名查找
		public AccountPO findbyName(String name);
		//通过关键字查找
		public ArrayList<AccountPO> findByKeyword(String s);
		//输出所有账户
		public ArrayList<AccountPO> showAll();

}
