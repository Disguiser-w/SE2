package data.userdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.BusinessPO;
import po.ExpressPO;
import po.IntermediatePO;
import po.OrganizationPO;
import po.UserPO;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import common.FileGetter;
import data.managedata.OrganizationData;
import data.repertorydata.RepertoryData;
//import type.SalaryPlanType;
import dataservice.userdataservice.UserDataService;
import file.JXCFile;

/**
 * 用户数据的处理，包括新增用户，删除用户，修改用户,查询用户等基本操作
 * */

public class UserData extends UnicastRemoteObject implements UserDataService { 

	private static final long serialVersionUID = 131250147L;

	JXCFile userFile;

	public UserData() throws RemoteException {
		userFile = new JXCFile("src/user.ser");
	}

	/**
	 * 新增用户
	 * @param UserPO userpo
	 * @return int : 0(add succeed), 1(user with the ID has already existed)
	 * 
	 * */
	public int addUser(UserPO userpo) throws RemoteException {
		ArrayList<Object> objectList = userFile.read();

		if (objectList == null) {
			objectList = new ArrayList<Object>();
			objectList.add(userpo);
			userFile.writeM(objectList);
			return 0;
		}

		else {
			for(int i =0; i<objectList.size(); i++) {
				UserPO tempUserPO = (UserPO) (objectList.get(i));
				if (tempUserPO.getName().equals(userpo.getName())) {
					return 1;
				}
			}
			objectList.add(userpo);
			userFile.writeM(objectList);
			return 0;
		}

	}

	
	/**
	 * 删除用户
	 * @param String userID
	 * @return int : 0(delete succeed), 1(delete failed)
	 * 
	 * */
	public int deleteUser(String userID) throws RemoteException {
		ArrayList<Object> objectList = userFile.read();

		if (objectList == null)
			return 1;

		for (int i = 0; i < objectList.size(); i++) {
			UserPO tempUserPO = (UserPO) (objectList.get(i));

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
								.findOrganization(newOrganization);
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

					}
				}

				objectList.remove(i);
				break;

			}
		}

		// userFile.clear();
		userFile.writeM(objectList);
		return 0;
	}

	
	/**
	 * 修改用户密码
	 * @param String userID, String newPassword
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyUserPassword(String userID, String newPassword)
			throws RemoteException {
		ArrayList<Object> objectList = userFile.read();

		if (objectList == null)
			return 1;

		for (int i = 0; i < objectList.size(); i++) {
			UserPO tempUserPO = (UserPO) (objectList.get(i));
			if (tempUserPO.getUserID().equals(userID)) {
				tempUserPO.setPassWord(newPassword);
				break;
			}
		}

		userFile.writeM(objectList);
		return 0;
	}

	
	/**
	 * 修改用户权限
	 * @param String userID, AuthorityType authority
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyUserAuthority(String userID, AuthorityType authority)
			throws RemoteException {
		ArrayList<Object> objectList = userFile.read();

		if (objectList == null)
			return 1;

		for (int i = 0; i < objectList.size(); i++) {
			UserPO tempUserPO = (UserPO) (objectList.get(i));
			if (tempUserPO.getUserID().equals(userID)) {
				tempUserPO.setAuthority(authority);
				break;
			}
		}

		userFile.writeM(objectList);
		return 0;
	}

	
	/**
	 * 修改用户机构
	 * @param String userID, String newOrganizationID 
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyUserOrganization(String userID, String newOrganizationID) throws RemoteException {
		ArrayList<Object> objectList = userFile.read();

		if (objectList == null)
			return 1;

		for (int i = 0; i < objectList.size(); i++) {
			UserPO tempUserPO = (UserPO) (objectList.get(i));
			// 增加对应的人员信息
			if (tempUserPO.getUserID().equals(userID)) {

				switch (tempUserPO.getProfession()) {
				// 快递员
				case courier:
					OrganizationPO organizationPO = (new OrganizationData()).findOrganization(newOrganizationID);
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
							.findOrganization(newOrganizationID);
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
							.findOrganization(newOrganizationID);
					IntermediatePO po2 = new IntermediatePO(organizationPO2,
							tempUserPO.getName(), tempUserPO.getUserID());

					File file2 = FileGetter.getFile("intermediateInfo/"
							+ organizationPO2.getOrganizationID()
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
					try{
						RepertoryData repertoryData = new RepertoryData();
						newOrganizationID = newOrganizationID+"-CK";
						repertoryData.modifyRepertoryOwner(newOrganizationID, userID);
					}catch(RemoteException ex){
						ex.printStackTrace();
					}
					break;
					
				}

					tempUserPO.setOrganization(newOrganizationID);
					System.out.println(tempUserPO.getOrganization());
					break;
			}
		}

		userFile.writeM(objectList);
		return 0;
	}

	
	/**
	 * 修改用户绩点
	 * @param String userID, int newGrade
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyUserGrades(String userID, int newGrade)
			throws RemoteException {
		ArrayList<Object> objectList = userFile.read();

		if (objectList == null)
			return 1;

		for (int i = 0; i < objectList.size(); i++) {
			UserPO tempUserPO = (UserPO) (objectList.get(i));
			if (tempUserPO.getUserID().equals(userID)) {
				tempUserPO.setGrades(newGrade);
				break;
			}
		}

		userFile.writeM(objectList);
		return 0;
	}

	
	/**
	 * 根据编号查找用户（精确搜索）
	 * @param String userID
	 * @return UserPO
	 * 
	 * */
	public UserPO findUserByID(String userID) throws RemoteException {
		ArrayList<Object> objectList = userFile.read();

		if (objectList == null)
			return null;

		else {
			for (int i = 0; i < objectList.size(); i++) {
				UserPO tempUserPO = (UserPO) (objectList.get(i));
				if (tempUserPO.getUserID().equals(userID)) {
					return tempUserPO;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 根据关键字查找用户（模糊搜索）
	 * @param String keyword
	 * @return ArrayList<UserPO>
	 * 
	 * */
	public ArrayList<UserPO> findUserByKeyword(String keyword) throws RemoteException {
		ArrayList<Object> objectList = userFile.read();
		ArrayList<UserPO> userpoList = new ArrayList<UserPO>();
		
		if (objectList == null)
			return null;

		else {
			for(int i = 0; i < objectList.size(); i++){
				UserPO tempUserPO = (UserPO) (objectList.get(i));
				if (tempUserPO.getUserID().contains(keyword) || tempUserPO.getName().contains(keyword)) {
					userpoList.add(tempUserPO);
				}
			}
			return userpoList;
		}
	}

	
	/**
	 * 显示所有用户
	 * @return ArrayList<UserPO>
	 * 
	 * */
	public ArrayList<UserPO> showAllUsers() throws RemoteException {
		ArrayList<Object> objectList = userFile.read();

		if (objectList == null)
			return null;

		ArrayList<UserPO> userList = new ArrayList<UserPO>();

		for (int i = 0; i < objectList.size(); i++) {
			UserPO tempUserPO = (UserPO) (objectList.get(i));
			userList.add(tempUserPO);
		}

		return userList;
	}

	
	/**
	 * 获得某职业用户编号后缀
	 * @return String 
	 * 
	 * */
	public String getUserIDPost(ProfessionType profession)
			throws RemoteException {
		ArrayList<Object> objectList = userFile.read();

		if (objectList == null)
			return "00001";

		int professionCount = 0; 	// 记录该职业的用户有多少人
		
		for (int i = 0; i < objectList.size(); i++) {
			UserPO tempUserPO = (UserPO) (objectList.get(i));
			if (tempUserPO.getProfession().equals(profession)) {
				String[] parts = tempUserPO.getUserID().split("-");
				int professionMaxTemp = Integer.parseInt(parts[1]);	//该用户目前的编号后缀
				professionCount++; //已有该职业用户的个数
				if(professionCount != professionMaxTemp){	//如果该用户目前的编号不等于已有该职业用户的个数，证明中间某编号是空余的
					if (professionCount <= 9) {
						return "0000" + professionCount;
					} else if (professionCount >= 10 && professionCount <= 100) {
						return "000" + professionCount;
					} else {
						return "00" + professionCount;
					}
				}
				
			}
		}
		
		if(professionCount == 0)
			return "00001";	//如果遍历完所有的,没有找到对应职业的用户，就返回00001
		else{	//如果遍历完所有的,用户个数和编号都一一对应，用户个数加一，返回
			professionCount++;
			if (professionCount <= 9) {
				return "0000" + professionCount;
			} else if (professionCount >= 10 && professionCount <= 100) {
				return "000" + professionCount;
			} else {
				return "00" + professionCount;
			}
		}
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
				userData.addUser(new UserPO("王丽莉", "CW-00001", "123456",
						ProfessionType.financialStaff, "总部",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.highest, 0));
				userData.addUser(new UserPO("魏彦淑", "admin", "admin",
						ProfessionType.administrator, "总部",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.administrator, 0));
				userData.addUser(new UserPO("张家盛", "YYT-00001", "123456",
						ProfessionType.businessHallCounterman, "025-0",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("张词校", "KD-00001", "123456",
						ProfessionType.courier, "025000",
						SalaryPlanType.courierSalaryPlan, AuthorityType.lowest,
						0));
				userData.addUser(new UserPO("王卉", "CK-00001", "123456",
						ProfessionType.stockman, "025-0-CK",
						SalaryPlanType.basicStaffSalaryPlan,
						AuthorityType.lowest, 0));
				userData.addUser(new UserPO("吴秦月", "SJ-00001", "123456",
						ProfessionType.driver, "030000",
						SalaryPlanType.driverSalaryPlan, AuthorityType.lowest,
						0));

				System.out.println("添加后:");
				ArrayList<UserPO> userpoList0 = userData.showAllUsers();
				if (userpoList0 != null) {
					for (int i = 0; i < userpoList0.size(); i++) {
						UserPO tempUserpo = userpoList0.get(i);
						System.out.println(tempUserpo.getName() + "  "
								+ tempUserpo.getUserID() + "  "
								+ tempUserpo.getOrganization() + "  "
								+ tempUserpo.getProfession());
					}
				}

				UserPO userpo1 = userData.findUserByID("KD-00001");
				if (userpo1 != null)
					System.out.println("Find the user: " + userpo1.getName()
							+ " " + userpo1.getUserID() + " "
							+ userpo1.getOrganization() + " "
							+ userpo1.getProfession());
				else
					System.out.println("Cannot find the user");

				userData.modifyUserOrganization("张Doge", "025001");
				System.out.println("修改后:");
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
					System.out.println("Cannot find the user");

			} catch (RemoteException exception) {
				exception.printStackTrace();
			}
		} catch (RemoteException exception) {
			exception.printStackTrace();
		}
	}

	/*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/

	/* public static void main(String[] args) {
	 try {
	 System.setProperty("java.rmi.server.hostname", "172.25.132.40");
	 UserDataService userData = new UserData();
	 LocateRegistry.createRegistry(6000);
	
	 // 绑定RMI名称进行发布
	 Naming.rebind("rmi://172.25.132.40:6000/UserDataService", userData);
	 System.out.println("User Service start!");
	
	 ArrayList<UserPO> userList0 = userData.showAllUsers();
	 for (UserPO user : userList0)
	 System.out.println("ID: " + user.getUserID() + ", Name: " +
	 user.getName());
	
	 UserPO user = userData.findUser("JL-01");
	 System.out.println("ID: " + user.getUserID() + ", Name: " +
	 user.getName());
	
	 } catch (Exception e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	 }*/

}
