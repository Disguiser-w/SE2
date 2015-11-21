package businesslogicservice.financeblservice;

import java.util.ArrayList;

import vo.AccountVO;
/**
 *对账户的增删改查
 * */
public interface AccountBLService {
	//增加账户
	public int addAccount(AccountVO vo);
	//删除账户
	public int deleteAccount(String time);
	//修改账户
	public int modifyAccount(AccountVO vo,String name);
	//通过账户名查找
	public AccountVO findbyName(String name);
	//通过关键字查找
	public ArrayList<AccountVO> findByKeyword(String s);
	//输出所有账户
	public ArrayList<AccountVO> showAll();

}
