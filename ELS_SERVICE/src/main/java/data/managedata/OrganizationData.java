package data.managedata;

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
import data.userdata.UserData;
import dataservice.managedataservice.OrganizationDataService;
import po.OrganizationPO;
import po.RepertoryPO;
import po.UserPO;
import type.OrganizationType;

/**
 * 机构数据的处理，包括新增机构，删除机构，修改机构,查询机构等基本操作
 * */


public class OrganizationData extends UnicastRemoteObject implements OrganizationDataService {

	private static final long serialVersionUID = 131250154L;

	UserData userData; 
	
	public OrganizationData() throws RemoteException {
		super();
		userData = new UserData();
	}

	
	/**
	 * 读文件（增删改查统一调用它）
	 * 
	 * */
	public ArrayList<OrganizationPO> getOrganizationList() throws RemoteException{
		String path = "organizationInfo/organization.ser";
		File file = FileGetter.getFile(path);
		if (!file.exists()) {
			return new ArrayList<OrganizationPO>();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<OrganizationPO> organizationList = (ArrayList<OrganizationPO>) in.readObject();
			in.close();
			return organizationList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 写文件（增删改查统一调用它）
	 * 
	 * */
	public int saveOrganizationList(ArrayList<OrganizationPO> organizationList) throws RemoteException {
		String path = "organizationInfo/organization.ser";
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
			out.writeObject(organizationList);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	
	/**
	 * 新增机构
	 * @param OrganizationPO organizationpo
	 * @return int : 0(add succeed), 1(user with the ID has already existed)
	 * 
	 * */
	public int addOrganization(OrganizationPO organizationpo) throws RemoteException {
		ArrayList<OrganizationPO> organizationList = getOrganizationList();
		
		for(int i=0; i<organizationList.size(); i++){
			OrganizationPO tempOrganizationPO = organizationList.get(i);
			if (tempOrganizationPO.getOrganizationID().equals(organizationpo.getOrganizationID())) {
				return 1;
			}
		}
		
		organizationList.add(organizationpo);
		saveOrganizationList(organizationList);
		return 0;
	}

	
	/**
	 * 删除机构(删除一个机构的同时，把它拥有的员工也删除)
	 * @param String organizationID
	 * @return int : 0(delete succeed), 1(delete failed)
	 * 
	 * */
	public int deleteOrganization(String organizationID) throws RemoteException {
		ArrayList<OrganizationPO> organizationList = getOrganizationList();

		boolean hasExist = false;
		
		for(int i=0; i<organizationList.size(); i++){
			OrganizationPO tempOrganizationPO = organizationList.get(i);
			if (tempOrganizationPO.getOrganizationID().equals(organizationID)){
				hasExist = true;
				organizationList.remove(i);
				break;
			}
		}
		
		saveOrganizationList(organizationList);
		
		String keyword = organizationID;
		ArrayList<UserPO> userList = userData.findUserByKeyword(keyword);
		
		for(int i=0, size = userList.size(); i<size; i++){
			userData.deleteUser(userList.get(i).getUserID());
		}		
		
		if(hasExist)
			return 0;
		else
			return 1;
	}

	
	/**
	 * 修改机构信息
	 * @param OrganizationPO organizationpo
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyOrganization(OrganizationPO organizationpo) throws RemoteException {
		ArrayList<OrganizationPO> organizationList = getOrganizationList();

		boolean hasExist = false;
		
		for(int i=0; i<organizationList.size(); i++){
			OrganizationPO tempOrganizationPO = organizationList.get(i);
			if (tempOrganizationPO.getOrganizationID().equals(organizationpo.getOrganizationID())){
				hasExist = true;
				tempOrganizationPO.setPlaneList(organizationpo.getPlaneList());
				tempOrganizationPO.setTrainList(organizationpo.getTrainList());
				tempOrganizationPO.setTruckList(organizationpo.getTruckList());
				tempOrganizationPO.setRepertory(organizationpo.getRepertory());
				break;
			}
		}
		
		saveOrganizationList(organizationList);
		if(hasExist)
			return 0;
		else
			return 1;
	}

	
	/**
	 * 根据编号查找机构（精确搜索）
	 * @param String organizationID
	 * @return OrganizationPO
	 * 
	 * */
	public OrganizationPO findOrganizationByID(String organizationID) throws RemoteException {
		ArrayList<OrganizationPO> organizationList = getOrganizationList();
		
		for(int i=0; i<organizationList.size(); i++){
			OrganizationPO tempOrganizationPO = organizationList.get(i);
			if (tempOrganizationPO.getOrganizationID().equals(organizationID)) {
				return tempOrganizationPO;
			}
		}
		return null;
	}
	
	
	/**
	 * 根据关键字查找机构（模糊搜索）
	 * @param String keyword
	 * @return ArrayList<OrganizationPO>
	 * 
	 * */
	public ArrayList<OrganizationPO> findOrganizationByKeyword(String keyword) throws RemoteException {
		ArrayList<OrganizationPO> organizationList = getOrganizationList();
		ArrayList<OrganizationPO> organizationpoList = new ArrayList<OrganizationPO>();

		if (organizationList == null)
			return null;

		else {
			for (int i = 0; i < organizationList.size(); i++) {
				OrganizationPO tempOrganizationPO = (organizationList.get(i));
				if (tempOrganizationPO.getOrganizationID().contains(keyword) || tempOrganizationPO.getName().contains(keyword)) {
					organizationpoList.add(tempOrganizationPO);
				}
			}
			return organizationpoList;
		}
	}

	
	/**
	 * 显示所有机构信息
	 * @return ArrayList<OrganizationPO>
	 * 
	 * */
	public ArrayList<OrganizationPO> showAllOrganizations() throws RemoteException {
		ArrayList<OrganizationPO> organizationList = getOrganizationList();
		return organizationList;
	}

	
	/**
	 * 查找下级已经设立营业厅的城市（例如南京市，它有仙林营业厅和鼓楼营业厅，那么就返回一个ArrayList<String>,拥有值“仙林”、“鼓楼”）
	 * @param String city
	 * @return ArrayList<String>
	 * 
	 * 一点限制就是，总经理新建机构的时候，要输入全名，比如“南京市仙林营业厅”
	 * */
	public ArrayList<String> getBelongingPlaces(String city) throws RemoteException {
		ArrayList<OrganizationPO> organizationList = getOrganizationList();
		ArrayList<String> belongingPlaces = new ArrayList<String>();

		if (organizationList == null)
			return null;

		for (int i = 0; i < organizationList.size(); i++) {
			OrganizationPO tempOrganizationPO = organizationList.get(i);
			if (tempOrganizationPO.getName().startsWith(city) && tempOrganizationPO.getCategory().equals(OrganizationType.businessHall)) {
				String[] transition1 = tempOrganizationPO.getName().split("市");
				String[] transition2 = transition1[1].split("营业厅");
				belongingPlaces.add(transition2[0]);
			}
		}

		return belongingPlaces;
	}

	/*--------------------------------------------------Test Part---------------------------------------------------*/

	/*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/

	public static void main(String[] args) {
		OrganizationData organizationData;
		try {
			organizationData = new OrganizationData();
			try {
				organizationData.addOrganization(new OrganizationPO(OrganizationType.intermediateCenter, "025-0",
						"南京中转中心", new RepertoryPO("025-0-CK", "CK-00001")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.intermediateCenter, "021-0",
						"上海中转中心", new RepertoryPO("021-0-CK", "CK-00002")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.intermediateCenter, "010-0",
						"北京中转中心", new RepertoryPO("010-0-CK", "CK-00003")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.intermediateCenter, "020-0",
						"广州中转中心", new RepertoryPO("020-0-CK", "CK-00004")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "025000", "南京市鼓楼营业厅",
						new RepertoryPO("", "")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "025001", "南京市仙林营业厅",
						new RepertoryPO("", "")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "021000", "上海市静安营业厅",
						new RepertoryPO("", "")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "021001", "上海市浦东营业厅",
						new RepertoryPO("", "")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "010000", "北京市朝阳营业厅",
						new RepertoryPO("", "")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "010001", "北京市海淀营业厅",
						new RepertoryPO("", "")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "020000", "广州市白云营业厅",
						new RepertoryPO("", "")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "020001", "广州市天河营业厅",
						new RepertoryPO("", "")));

				System.out.println("添加后:");
				ArrayList<OrganizationPO> organizationpoList0 = organizationData.showAllOrganizations();
				if (organizationpoList0 != null) {
					for (int i = 0; i < organizationpoList0.size(); i++) {
						OrganizationPO tempOrganizationpo = organizationpoList0.get(i);
						if (tempOrganizationpo.getCategory().equals(OrganizationType.intermediateCenter))
							System.out.println(
									tempOrganizationpo.getOrganizationID() + "  " + tempOrganizationpo.getName()
											+ " 对应仓库：" + tempOrganizationpo.getRepertory().getRepertoryID());
						else
							System.out.println(
									tempOrganizationpo.getOrganizationID() + "  " + tempOrganizationpo.getName());
					}
				} else
					System.out.println("Cannot find the organization");

				OrganizationPO organizationpo = organizationData.findOrganizationByID("025-0");
				if (organizationpo != null) {
					if (organizationpo.getCategory().equals(OrganizationType.intermediateCenter))
						System.out.println("Find the organization: " + organizationpo.getCategory() + " "
								+ organizationpo.getOrganizationID() + " " + organizationpo.getName() + " 对应仓库："
								+ organizationpo.getRepertory().getRepertoryID());
					else
						System.out.println("Find the organization: " + organizationpo.getCategory() + " "
								+ organizationpo.getOrganizationID() + " " + organizationpo.getName());
				} else
					System.out.println("Cannot find the organization");

				System.out.println("修改后:");
				ArrayList<OrganizationPO> organizationpoList3 = organizationData.showAllOrganizations();
				if (organizationpoList3 != null) {
					for (int i = 0; i < organizationpoList3.size(); i++) {
						OrganizationPO tempOrganizationpo = organizationpoList3.get(i);
						if (tempOrganizationpo.getCategory().equals(OrganizationType.intermediateCenter))
							System.out.println(
									tempOrganizationpo.getOrganizationID() + "  " + tempOrganizationpo.getName()
											+ " 对应仓库：" + tempOrganizationpo.getRepertory().getRepertoryID());
						else
							System.out.println(
									tempOrganizationpo.getOrganizationID() + "  " + tempOrganizationpo.getName());
					}
				} else
					System.out.println("Cannot find the organization");

				System.out.println("没有删除前:");
				ArrayList<OrganizationPO> organizationpoList1 = organizationData.showAllOrganizations();
				if (organizationpoList1 != null) {
					for (int i = 0; i < organizationpoList1.size(); i++) {
						OrganizationPO tempOrganizationpo = organizationpoList1.get(i);
						if (tempOrganizationpo.getCategory().equals(OrganizationType.intermediateCenter))
							System.out.println(
									tempOrganizationpo.getOrganizationID() + "  " + tempOrganizationpo.getName()
											+ " 对应仓库：" + tempOrganizationpo.getRepertory().getRepertoryID());
						else
							System.out.println(
									tempOrganizationpo.getOrganizationID() + "  " + tempOrganizationpo.getName());
					}
				} else
					System.out.println("Cannot find the organization");

				// organizationData.deleteOrganization("025-0");
				System.out.println("删除后:");
				ArrayList<OrganizationPO> organizationpoList2 = organizationData.showAllOrganizations();
				if (organizationpoList2 != null) {
					for (int i = 0; i < organizationpoList2.size(); i++) {
						OrganizationPO tempOrganizationpo = organizationpoList2.get(i);
						if (tempOrganizationpo.getCategory().equals(OrganizationType.intermediateCenter))
							System.out.println(
									tempOrganizationpo.getOrganizationID() + "  " + tempOrganizationpo.getName()
											+ " 对应仓库：" + tempOrganizationpo.getRepertory().getRepertoryID());
						else
							System.out.println(
									tempOrganizationpo.getOrganizationID() + "  " + tempOrganizationpo.getName());
					}
				} else
					System.out.println("Cannot find the organization");

			} catch (RemoteException exception) {
				exception.printStackTrace();
			}
		} catch (RemoteException exception) {
			exception.printStackTrace();
		}
	}

	/*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/

	 /*public static void main(String[] args) {
		 try {
			 System.setProperty("java.rmi.server.hostname", "172.25.132.40");
			 OrganizationDataService organizationData = new OrganizationData();
			 LocateRegistry.createRegistry(6006);
			
			 // 绑定RMI名称进行发布
			 Naming.rebind("rmi://172.25.132.40:6006/OrganizationDataService",organizationData);
			 System.out.println("Organization Service start!");
			
			 ArrayList<OrganizationPO> organizationList0 = organizationData.showAllOrganizations();
			 for (OrganizationPO organization : organizationList0)
				 System.out.println("ID: " + organization.getOrganizationID() + ", Name: "+ organization.getName());
			
			 OrganizationPO organizationpo = organizationData.findOrganization("025000");
			 System.out.println("ID: " + organizationpo.getOrganizationID() + ", Name:" + organizationpo.getName());
			
		 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 	}
	 }*/

}
