package businesslogicservice.userblservice;

import vo.UserVO;

import java.util.ArrayList;

public interface UserBLService {
	public int login(String userID, String password);
	public int addUser(UserVO uservo);
	public int deleteUser(UserVO uservo);
	public int modifyUserPassword(UserVO uservo);
	public int modifyUserAuthority(UserVO uservo);
	public UserVO findUser(String userID);
	public ArrayList<UserVO> showAllUsers();
}
