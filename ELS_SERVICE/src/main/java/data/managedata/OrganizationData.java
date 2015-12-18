package data.managedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataservice.managedataservice.OrganizationDataService;
import file.JXCFile;
import po.OrganizationPO;
import po.RepertoryPO;
import po.UserPO;
//import po.RepertoryPO;
import type.OrganizationType;

/**
 * 机构数据的处理，包括新增机构，删除机构，修改机构,查询机构等基本操作
 * */

public class OrganizationData extends UnicastRemoteObject implements OrganizationDataService {

	private static final long serialVersionUID = 131250154L;

	JXCFile organizationFile;
	JXCFile userFile;

	public OrganizationData() throws RemoteException {
		organizationFile = new JXCFile("src/organization.ser");
		userFile = new JXCFile("src/user.ser");
	}

	/**
	 * 新增机构
	 * @param OrganizationPO organizationpo
	 * @return int : 0(add succeed), 1(user with the ID has already existed)
	 * 
	 * */
	public int addOrganization(OrganizationPO organizationpo) throws RemoteException {
		if (findOrganizationByID(organizationpo.getOrganizationID()) == null) {
			organizationFile.write(organizationpo);
			return 0;
		} else
			return 1;
	}

	
	/**
	 * 删除机构(删除一个机构的同时，把它拥有的员工也删除)
	 * @param String organizationID
	 * @return int : 0(delete succeed), 1(delete failed)
	 * 
	 * */
	public int deleteOrganization(String organizationID) throws RemoteException {
		ArrayList<Object> organizationList = organizationFile.read();
		ArrayList<Object> userList = userFile.read();

		if (organizationList == null)
			return 1;

		else{
			for (int i = 0; i < organizationList.size(); i++) {
				OrganizationPO tempOrganizationPO = (OrganizationPO) (organizationList.get(i));
				if (tempOrganizationPO.getOrganizationID().equals(organizationID)) {
					organizationList.remove(i);
					break;
				}
			}
	
			for (int i = 0; i < userList.size(); i++) {
				UserPO tempUserPO = (UserPO)(userList.get(i));
				if(tempUserPO.getOrganization().contains(organizationID)) {
					userList.remove(i);
				}
			}
			
			organizationFile.writeM(organizationList);
			userFile.writeM(userList);
			return 0;
		}
	}

	
	/**
	 * 修改机构信息
	 * @param OrganizationPO organizationpo
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
	public int modifyOrganization(OrganizationPO organizationpo) throws RemoteException {
		ArrayList<Object> objectList = organizationFile.read();

		if (objectList == null)
			return 1;

		for (int i = 0; i < objectList.size(); i++) {
			OrganizationPO tempOrganizationPO = (OrganizationPO) (objectList.get(i));
			if (tempOrganizationPO.getOrganizationID().equals(organizationpo.getOrganizationID())) {
				tempOrganizationPO.setName(organizationpo.getName());
				tempOrganizationPO.setRepertory(organizationpo.getRepertory());
				tempOrganizationPO.setPlaneList(organizationpo.getPlaneList());
				tempOrganizationPO.setTrainList(organizationpo.getTrainList());
				tempOrganizationPO.setTruckList(organizationpo.getTruckList());
				break;
			}
		}

		organizationFile.writeM(objectList);
		return 0;
	}

	
	/**
	 * 根据编号查找机构（精确搜索）
	 * @param String organizationID
	 * @return OrganizationPO
	 * 
	 * */
	public OrganizationPO findOrganizationByID(String organizationID) throws RemoteException {
		ArrayList<Object> objectList = organizationFile.read();

		if (objectList == null)
			return null;

		for (int i = 0; i < objectList.size(); i++) {
			OrganizationPO tempOrganizationPO = (OrganizationPO) (objectList.get(i));
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
		ArrayList<Object> objectList = organizationFile.read();
		ArrayList<OrganizationPO> organizationpoList = new ArrayList<OrganizationPO>();

		if (objectList == null)
			return null;

		else {
			for (int i = 0; i < objectList.size(); i++) {
				OrganizationPO tempOrganizationPO = (OrganizationPO) (objectList.get(i));
				if (tempOrganizationPO.getOrganizationID().contains(keyword)  || tempOrganizationPO.getName().contains(keyword)) {
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
		ArrayList<Object> objectList = organizationFile.read();

		if (objectList == null)
			return null;

		ArrayList<OrganizationPO> organizationList = new ArrayList<OrganizationPO>();

		for (int i = 0; i < objectList.size(); i++) {
			OrganizationPO tempOrganizationPO = (OrganizationPO) (objectList.get(i));
			organizationList.add(tempOrganizationPO);
		}

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
		ArrayList<Object> objectList = organizationFile.read();
		ArrayList<String> belongingPlaces = new ArrayList<String>();

		if (objectList == null)
			return null;

		for (int i = 0; i < objectList.size(); i++) {
			OrganizationPO tempOrganizationPO = (OrganizationPO) (objectList.get(i));
			if (tempOrganizationPO.getName().startsWith(city)
					&& tempOrganizationPO.getCategory().equals(OrganizationType.businessHall)) {
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

				// organizationData.modifyOrganization(new
				// OrganizationPO(OrganizationType.intermediateCenter,"025-0","南京呵呵呵中转中心",new
				// RepertoryPO("025-呵呵呵-CK","CK-01")));
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
