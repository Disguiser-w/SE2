package data.userdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.BusinessPO;
import po.ExpressPO;
import po.IntermediatePO;
import po.OrganizationPO;
import po.PlanePO;
import po.TruckPO;
import po.UserPO;
import type.AuthorityType;
import type.OperationState;
import type.ProfessionType;
import type.SalaryPlanType;
import common.FileGetter;
import data.managedata.OrganizationData;
import data.repertorydata.RepertoryData;
//import type.SalaryPlanType;
import dataservice.userdataservice.UserDataService;

/**
 * 用户数据的处理，包括新增用户，删除用户，修改用户,查询用户等基本操作
 * */
@SuppressWarnings("serial")

public class UserData extends UnicastRemoteObject implements UserDataService {

	private static final long serialVersionUID = 131250147L;


	public UserData() throws RemoteException {
		super();
	}

	public ArrayList<UserPO> getUserList() throws RemoteException{
		String path = "userInfo/user.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<UserPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<UserPO> userList = (ArrayList<UserPO>) in.readObject();
			in.close();
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int saveUserList(ArrayList<UserPO> userList) throws RemoteException {
		String path = "userInfo/user.ser";
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
			out.writeObject(userList);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	 * 新增用户
	 * 
	 * @param UserPO userpo
	 * @return int : 0(add succeed), 1(user with the ID has already existed)
	 * 
	 * */
	public int addUser(UserPO userpo) throws RemoteException {
		ArrayList<UserPO> userList = getUserList();
		
		for(int i=0; i<userList.size(); i++){
			UserPO tempUserPO = userList.get(i);
			if (tempUserPO.getUserID().equals(userpo.getUserID())) {
				return 1;
			}
		}
		
		userList.add(userpo);
		saveUserList(userList);
		return 0;
	}

	/**
	 * 删除用户
	 * 
	 * @param String userID
	 * @return int : 0(delete succeed), 1(delete failed)
	 * 
	 * */
	public int deleteUser(String userID) throws RemoteException {
		ArrayList<UserPO> userList = getUserList();

		boolean hasExist = false;
		
		for(int i=0; i<userList.size(); i++){
			UserPO tempUserPO = userList.get(i);
			if (tempUserPO.getUserID().equals(userID)){
				hasExist = true;
				userList.remove(i);
				break;
			}
		}
		
		saveUserList(userList);
		
		if(hasExist)
			return 0;
		else
			return 1;
		
		/*for (int i = 0; i < userList.size(); i++) {
			UserPO tempUserPO = (UserPO) (userList.get(i));

			if (tempUserPO.getUserID().equals(userID)) {

				if (!tempUserPO.getOrganization().equals("")) {
					String newOrganization = tempUserPO.getOrganization();
					switch (tempUserPO.getProfession()) {
					// 快递员
					case courier:
						File file = FileGetter.getFile("expressInfo/"
								+ newOrganization + "-express.dat");
						try {
							ArrayList<ExpressPO> expressPOs = null;
							if (file.exists()) {
								ObjectInputStream in = new ObjectInputStream(
										new FileInputStream(file));
								expressPOs = (ArrayList<ExpressPO>) in
										.readObject();
								in.close();

								int size = expressPOs.size();
								for (int j = 0; j < size; j++) {
									if (expressPOs.get(j).getID()
											.equals(tempUserPO.getUserID())) {
										expressPOs.remove(j);
										break;
									}
								}

								ObjectOutputStream out = new ObjectOutputStream(
										new FileOutputStream(file));
								out.writeObject(expressPOs);
								out.close();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

						break;
					case businessHallCounterman:

						OrganizationPO organizationPO1 = (new OrganizationData())
								.findOrganizationByID(newOrganization);
						BusinessPO po1 = new BusinessPO(tempUserPO.getName(),
								tempUserPO.getUserID(), "0", organizationPO1);

						File file1 = FileGetter.getFile("businessInfo/"
								+ newOrganization + "-business.dat");

						try {
							ArrayList<BusinessPO> businessPOs = null;
							if (file1.exists()) {
								ObjectInputStream in = new ObjectInputStream(
										new FileInputStream(file1));
								businessPOs = (ArrayList<BusinessPO>) in
										.readObject();
								in.close();

								int size = businessPOs.size();
								for (int j = 0; j < size; j++) {
									if (businessPOs.get(j).getID()
											.equals(tempUserPO.getUserID())) {
										businessPOs.remove(j);
										break;
									}
								}

								ObjectOutputStream out = new ObjectOutputStream(
										new FileOutputStream(file1));
								out.writeObject(businessPOs);
								out.close();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

						break;
					case intermediateCenterCounterman:

						OrganizationPO organizationPO2 = (new OrganizationData())
								.findOrganizationByID(newOrganization);
						IntermediatePO po2 = new IntermediatePO(
								organizationPO2, tempUserPO.getName(),
								tempUserPO.getUserID());

						File file2 = FileGetter
								.getFile("IntermediateCentreInfo/intermediate/"
										+ tempUserPO.getUserID()
										+ "-intermediate.dat");

						try {
							ArrayList<IntermediatePO> intermediatePOs = null;
							if (file2.exists()) {
								ObjectInputStream in = new ObjectInputStream(
										new FileInputStream(file2));
								intermediatePOs = (ArrayList<IntermediatePO>) in
										.readObject();
								in.close();

								int size = intermediatePOs.size();
								for (int j = 0; j < size; j++) {
									if (intermediatePOs.get(j).getID()
											.equals(tempUserPO.getUserID())) {
										intermediatePOs.remove(j);
										break;
									}
								}

								ObjectOutputStream out = new ObjectOutputStream(
										new FileOutputStream(file2));
								out.writeObject(intermediatePOs);
								out.close();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

						break;

					}
				}

				userList.remove(i);
				break;

			}
		}*/

	}

	/**
	 * 修改用户密码
	 * 
	 * @param String userID, String newPassword
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyUserPassword(String userID, String newPassword) throws RemoteException {
		ArrayList<UserPO> userList = getUserList();

		boolean hasExist = false;
		
		for(int i=0; i<userList.size(); i++){
			UserPO tempUserPO = userList.get(i);
			if (tempUserPO.getUserID().equals(userID)){
				hasExist = true;
				tempUserPO.setPassWord(newPassword);
				break;
			}
		}
		
		saveUserList(userList);
		if(hasExist)
			return 0;
		else
			return 1;
	}

	/**
	 * 修改用户权限
	 * 
	 * @param String userID, AuthorityType authority
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyUserAuthority(String userID, AuthorityType authority) throws RemoteException {
		ArrayList<UserPO> userList = getUserList();

		boolean hasExist = false;
		
		for(int i=0; i<userList.size(); i++){
			UserPO tempUserPO = userList.get(i);
			if (tempUserPO.getUserID().equals(userID)){
				hasExist = true;
				tempUserPO.setAuthority(authority);
				break;
			}
		}
		
		saveUserList(userList);
		if(hasExist)
			return 0;
		else
			return 1;
	}

	/**
	 * 修改用户机构
	 * 
	 * @param String userID, String newOrganizationID
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyUserOrganization(String userID, String newOrganizationID)throws RemoteException {
		ArrayList<UserPO> userList = getUserList();

		boolean hasExist = false;
		
		for(int i=0; i<userList.size(); i++){
			UserPO tempUserPO = userList.get(i);
			if (tempUserPO.getUserID().equals(userID)){
				hasExist = true;
				tempUserPO.setOrganization(newOrganizationID);
				break;
			}
		}
		
		saveUserList(userList);
		if(hasExist)
			return 0;
		else
			return 1;
		
			// 增加对应的人员信息
			/*if (tempUserPO.getUserID().equals(userID)) {

				switch (tempUserPO.getProfession()) {
				// 快递员
				case courier:
					OrganizationPO organizationPO = (new OrganizationData())
							.findOrganizationByID(newOrganizationID);
					ExpressPO po = new ExpressPO(tempUserPO.getName(),
							tempUserPO.getUserID(), "0",
							new ArrayList<String>(), organizationPO,
							new ArrayList<String>(), new ArrayList<String>(),
							new ArrayList<String>());
					File file = FileGetter.getFile("expressInfo/"
							+ organizationPO.getOrganizationID()
							+ "-express.dat");

					try {
						ArrayList<ExpressPO> expressPOs = null;
						if (!file.exists()) {
							file.getParentFile().mkdirs();
							file.createNewFile();
							expressPOs = new ArrayList<ExpressPO>();
						} else {
							ObjectInputStream in = new ObjectInputStream(
									new FileInputStream(file));
							expressPOs = (ArrayList<ExpressPO>) in.readObject();
							in.close();

						}

						expressPOs.add(po);

						ObjectOutputStream out = new ObjectOutputStream(
								new FileOutputStream(file));
						out.writeObject(expressPOs);
						out.close();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;
				// 营业厅业务员
				case businessHallCounterman:

					OrganizationPO organizationPO1 = (new OrganizationData())
							.findOrganizationByID(newOrganizationID);
					BusinessPO po1 = new BusinessPO(tempUserPO.getName(),
							tempUserPO.getUserID(), "0", organizationPO1);

					File file1 = FileGetter.getFile("businessInfo/"
							+ organizationPO1.getOrganizationID()
							+ "-business.dat");

					try {
						ArrayList<BusinessPO> businessPOs = null;
						if (!file1.exists()) {
							file1.getParentFile().mkdirs();
							file1.createNewFile();
							businessPOs = new ArrayList<BusinessPO>();
						} else {
							ObjectInputStream in = new ObjectInputStream(
									new FileInputStream(file1));
							businessPOs = (ArrayList<BusinessPO>) in
									.readObject();
							in.close();

						}

						businessPOs.add(po1);

						ObjectOutputStream out = new ObjectOutputStream(
								new FileOutputStream(file1));
						out.writeObject(businessPOs);
						out.close();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;
				// 中转中心业务员
				case intermediateCenterCounterman:

					OrganizationPO organizationPO2 = (new OrganizationData())
							.findOrganizationByID(newOrganizationID);
					IntermediatePO po2 = new IntermediatePO(organizationPO2,
							tempUserPO.getName(), tempUserPO.getUserID());

					File file2 = FileGetter
							.getFile("intermediateCentreInfo/intermediate/"
									+ tempUserPO.getUserID()
									+ "-intermediate.dat");

					try {
						ArrayList<IntermediatePO> intermediatePOs = null;
						if (!file2.exists()) {
							file2.getParentFile().mkdirs();
							file2.createNewFile();
							intermediatePOs = new ArrayList<IntermediatePO>();
						} else {
							ObjectInputStream in = new ObjectInputStream(
									new FileInputStream(file2));
							intermediatePOs = (ArrayList<IntermediatePO>) in
									.readObject();
							in.close();
						}

						intermediatePOs.add(po2);

						ObjectOutputStream out = new ObjectOutputStream(
								new FileOutputStream(file2));
						out.writeObject(intermediatePOs);
						out.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				// 仓库管理员
				case stockman:
					try {
						RepertoryData repertoryData = new RepertoryData();
						newOrganizationID = newOrganizationID + "-CK";
						repertoryData.modifyRepertoryOwner(newOrganizationID,
								userID);
					} catch (RemoteException ex) {
						ex.printStackTrace();
					}
					break;

				}

				tempUserPO.setOrganization(newOrganizationID);
				//System.out.println(tempUserPO.getOrganization());
				break;
			}*/
}
	
	/**
	 * 修改用户绩点
	 * 
	 * @param String userID, int newGrade
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyUserGrades(String userID, int newGrade)throws RemoteException {
		ArrayList<UserPO> userList = getUserList();

		boolean hasExist = false;
		
		for(int i=0; i<userList.size(); i++){
			UserPO tempUserPO = userList.get(i);
			if (tempUserPO.getUserID().equals(userID)){
				hasExist = true;
				tempUserPO.setGrades(newGrade);
				break;
			}
		}
		
		saveUserList(userList);
		if(hasExist)
			return 0;
		else
			return 1;
	}

	/**
	 * 根据编号查找用户（精确搜索）
	 * 
	 * @param String userID
	 * @return UserPO
	 * 
	 * */
	public UserPO findUserByID(String userID) throws RemoteException {
		ArrayList<UserPO> userList = getUserList();
		
		for(int i=0; i<userList.size(); i++){
			UserPO tempUserPO = (userList.get(i));
			if (tempUserPO.getUserID().equals(userID)) {
				return tempUserPO;
			}
		}
		return null;
	}

	/**
	 * 根据关键字查找用户（模糊搜索）
	 * 
	 * @param String keyword
	 * @return ArrayList<UserPO>
	 * 
	 * */
	public ArrayList<UserPO> findUserByKeyword(String keyword)throws RemoteException {
		ArrayList<UserPO> userList = getUserList();
		ArrayList<UserPO> userpoList = new ArrayList<UserPO>();

		if (userList == null)
			return null;

		else {
			for (int i = 0; i < userList.size(); i++) {
				UserPO tempUserPO = (userList.get(i));
				if (tempUserPO.getUserID().contains(keyword) || tempUserPO.getName().contains(keyword)) {
					userpoList.add(tempUserPO);
				}
			}
			return userpoList;
		}
	}

	/**
	 * 显示所有用户信息
	 * 
	 * @return ArrayList<UserPO>
	 * 
	 * */
	public ArrayList<UserPO> showAllUsers() throws RemoteException {
		ArrayList<UserPO> userList = getUserList();
		return userList;
	}

	/**
	 * 获得某职业用户编号后缀
	 * 
	 * @param ProfessionType profession
	 * @return String
	 * 
	 * */
	public String getUserIDPost(ProfessionType profession) throws RemoteException {
		ArrayList<UserPO> userList = getUserList();

		if (userList.size() == 0)
			return "00001";

		int professionMaxPost = 0; // 记录该职业的用户编号目前拥有的最大后缀数
		for (int i = 0; i < userList.size(); i++) {
			UserPO tempUserPO = userList.get(i);
			if (tempUserPO.getProfession().equals(profession)) {
				int tempPost = Integer.parseInt(tempUserPO.getUserID().split("-")[1]);
				if(tempPost > professionMaxPost)
				professionMaxPost = tempPost;
			}
		}

		boolean exist[] = new boolean[professionMaxPost + 1];
		for (int i = 0; i <= professionMaxPost; i++) {
			exist[i] = false;
		}

		int currentCount = 0;
		for (int i = 0; i < userList.size(); i++) {
			UserPO tempUserPO = userList.get(i);
			if (tempUserPO.getProfession().equals(profession)) {
				String[] parts = tempUserPO.getUserID().split("-");
				currentCount = Integer.parseInt(parts[1]); // 该用户目前的编号后缀
					exist[currentCount] = true;
			}
		}

		for (int i = 1; i <= professionMaxPost; i++) {
			if (exist[i] == false) {
				return formatPostString(i);
			}
		}

		return formatPostString(professionMaxPost + 1);
	}

	public String formatPostString(int post) {
		if (post > 0 && post <= 9)
			return "0000" + post;
		else if (post >= 10 && post <= 99)
			return "000" + post;
		else
			return "00" + post;
	}

	/*--------------------------------------------------Test Part---------------------------------------------------*/

	/*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/

	public static void main(String[] args) {
		UserData userData;
		try {
			userData = new UserData();
			try {

				userData.addUser(new UserPO("刘钦", "JL-00001", "123456",
						ProfessionType.manager, "总部",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.highest, 0));
				userData.addUser(new UserPO("丁二玉", "JL-00002", "123456",
						ProfessionType.manager, "总部",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.highest, 0));
				userData.addUser(new UserPO("王丽莉", "CW-00001", "123456",
						ProfessionType.financialStaff, "总部",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.highest, 0));
				userData.addUser(new UserPO("王腻腻", "CW-00002", "123456",
						ProfessionType.financialStaff, "总部",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.commonFianacialStaff, 0));
				userData.addUser(new UserPO("魏彦淑", "admin", "admin",
						ProfessionType.administrator, "总部",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.administrator, 0));

				/*userData.addUser(new UserPO("张家盛", "ZZZX-00001", "123456",
						ProfessionType.intermediateCenterCounterman, "025-0",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("张方浩", "ZZZX-00002", "123456",
						ProfessionType.intermediateCenterCounterman, "021-0",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("张海涛", "ZZZX-00003", "123456",
						ProfessionType.intermediateCenterCounterman, "010-0",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("张晨剑", "ZZZX-00004", "123456",
						ProfessionType.intermediateCenterCounterman, "020-0",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));

				userData.addUser(new UserPO("万云天", "CK-00001", "123456",
						ProfessionType.stockman, "025-0-CK",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("汪盼", "CK-00002", "123456",
						ProfessionType.stockman, "021-0-CK",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("王成垚", "CK-00003", "123456",
						ProfessionType.stockman, "010-0-CK",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("王栋", "CK-00004", "123456",
						ProfessionType.stockman, "020-0-CK",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));

				userData.addUser(new UserPO("杨关", "YYT-00001", "123456",
						ProfessionType.businessHallCounterman, "025000",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("杨华安", "YYT-00002", "123456",
						ProfessionType.businessHallCounterman, "025001",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("杨三洋", "YYT-00003", "123456",
						ProfessionType.businessHallCounterman, "021000",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("杨雁飞", "YYT-00004", "123456",
						ProfessionType.businessHallCounterman, "021001",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("曾攀", "YYT-00005", "123456",
						ProfessionType.businessHallCounterman, "010000",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("朱文沛", "YYT-00006", "123456",
						ProfessionType.businessHallCounterman, "010001",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("朱宇翔", "YYT-00007", "123456",
						ProfessionType.businessHallCounterman, "020000",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("邹瀚真", "YYT-00008", "123456",
						ProfessionType.businessHallCounterman, "020001",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));

				userData.addUser(new UserPO("张词校", "KD-00001", "123456",
						ProfessionType.courier, "025000",
						SalaryPlanType.courierSalaryPlan, AuthorityType.lowest,
						50));
				userData.addUser(new UserPO("徐朱峰", "KD-00002", "123456",
						ProfessionType.courier, "025001",
						SalaryPlanType.courierSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("徐江河", "KD-00003", "123456",
						ProfessionType.courier, "021000",
						SalaryPlanType.courierSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("徐亚帆", "KD-00004", "123456",
						ProfessionType.courier, "021001",
						SalaryPlanType.courierSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("徐文杰", "KD-00005", "123456",
						ProfessionType.courier, "010000",
						SalaryPlanType.courierSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("徐家逸", "KD-00006", "123456",
						ProfessionType.courier, "010001",
						SalaryPlanType.courierSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("孙浩", "KD-00007", "123456",
						ProfessionType.courier, "020000",
						SalaryPlanType.courierSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("孙旭", "KD-00008", "123456",
						ProfessionType.courier, "020001",
						SalaryPlanType.courierSalaryPlan, AuthorityType.lowest,
						0));

				userData.addUser(new UserPO("孙超", "SJ-00001", "123456",
						ProfessionType.driver, "025000",
						SalaryPlanType.driverSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("宋益明", "SJ-00002", "123456",
						ProfessionType.driver, "025001",
						SalaryPlanType.driverSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("宋子微", "SJ-00003", "123456",
						ProfessionType.driver, "021000",
						SalaryPlanType.driverSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("苏琰梓", "SJ-00004", "123456",
						ProfessionType.driver, "021001",
						SalaryPlanType.driverSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("孙政", "SJ-00005", "123456",
						ProfessionType.driver, "010000",
						SalaryPlanType.driverSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("王杰", "SJ-00006", "123456",
						ProfessionType.driver, "010001",
						SalaryPlanType.driverSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("王家玮", "SJ-00007", "123456",
						ProfessionType.driver, "020000",
						SalaryPlanType.driverSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("王嘉琛", "SJ-00008", "123456",
						ProfessionType.driver, "020001",
						SalaryPlanType.driverSalaryPlan, AuthorityType.lowest,
						0));*/

				System.out.println("添加后:");
				ArrayList<UserPO> userpoList0 = userData.showAllUsers();
				if (userpoList0 != null) {
					for (int i = 0; i < userpoList0.size(); i++) {
						UserPO tempUserpo = userpoList0.get(i);
						System.out.println(tempUserpo.getName() + "  "
								+ tempUserpo.getUserID() + "  "
								+ tempUserpo.getOrganization() + "  "
								+ tempUserpo.getProfession() + " "
								+ tempUserpo.getGrades()+"");
					}
				}

				/*String postNow = userData.getUserIDPost(ProfessionType.courier);
				System.out.println(postNow);

				UserPO userpo1 = userData.findUserByID("KD-00001");
				if (userpo1 != null)
					System.out.println("Find the user: " + userpo1.getName()
							+ " " + userpo1.getUserID() + " "
							+ userpo1.getOrganization() + " "
							+ userpo1.getProfession());
				else
					System.out.println("Cannot find the user");

				userData.modifyUserOrganization("张Doge", "025000");
				System.out.println("修改机构后:");
				ArrayList<UserPO> userpoList3 = userData.showAllUsers();
				if (userpoList3 != null) {
					for (int i = 0; i < userpoList3.size(); i++) {
						UserPO tempUserpo = userpoList3.get(i);
						System.out.println(tempUserpo.getName() + "  "
								+ tempUserpo.getUserID() + "  "
								+ tempUserpo.getOrganization() + "  "
								+ tempUserpo.getProfession());
					}
				} else
					System.out.println("Cannot find the user");
				
				userData.modifyUserGrades("KD-00001", 50);
				System.out.println("修改绩点后:");

				System.out.println("没有删除前:");
				ArrayList<UserPO> userpoList1 = userData.showAllUsers();
				if (userpoList1 != null) {
					for (int i = 0; i < userpoList1.size(); i++) {
						UserPO tempUserpo = userpoList1.get(i);
						System.out.println(tempUserpo.getName() + "  "
								+ tempUserpo.getUserID() + "  "
								+ tempUserpo.getOrganization() + "  "
								+ tempUserpo.getProfession());
					}
				} else
					System.out.println("Cannot find the user");

				// userData.deleteUser("CW-00001"); System.out.println("删除后:");
				ArrayList<UserPO> userpoList2 = userData.showAllUsers();
				if (userpoList2 != null) {
					for (int i = 0; i < userpoList2.size(); i++) {
						UserPO tempUserpo = userpoList2.get(i);
						System.out.println(tempUserpo.getName() + "  "
								+ tempUserpo.getUserID() + "  "
								+ tempUserpo.getOrganization() + "  "
								+ tempUserpo.getProfession());
					}
				} else
					System.out.println("Cannot find the user");*/

			} catch (RemoteException exception) {
				exception.printStackTrace();
			}
		} catch (RemoteException exception) {
			exception.printStackTrace();
		}
	}

	/*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/

	/*
	 * public static void main(String[] args) { try {
	 * System.setProperty("java.rmi.server.hostname", "172.25.132.40");
	 * UserDataService userData = new UserData();
	 * LocateRegistry.createRegistry(6000);
	 * 
	 * // 绑定RMI名称进行发布 Naming.rebind("rmi://172.25.132.40:6000/UserDataService",
	 * userData); System.out.println("User Service start!");
	 * 
	 * ArrayList<UserPO> userList0 = userData.showAllUsers(); for (UserPO user :
	 * userList0) System.out.println("ID: " + user.getUserID() + ", Name: " +
	 * user.getName());
	 * 
	 * UserPO user = userData.findUser("JL-01"); System.out.println("ID: " +
	 * user.getUserID() + ", Name: " + user.getName());
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

}
