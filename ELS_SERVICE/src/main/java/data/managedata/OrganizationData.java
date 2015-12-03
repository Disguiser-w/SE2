package data.managedata;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataservice.managedataservice.OrganizationDataService;
import file.JXCFile;
import po.OrganizationPO;
import type.OrganizationType;

public class OrganizationData extends UnicastRemoteObject implements OrganizationDataService {

	// 我也不知道下面这句话有什么用，只是因为继承了UnicastRemoteObject所以要声明这样一个字段
	private static final long serialVersionUID = 131250154L;

	JXCFile organizationFile;

	public OrganizationData() throws RemoteException {
		organizationFile = new JXCFile("src/organization.ser");
	}

	public int addOrganization(OrganizationPO organizationpo) throws RemoteException {
		if (findOrganization(organizationpo.getOrganizationID()) == null) {
			organizationFile.write(organizationpo);
			return 0;
		} else
			return 1;
	}

	public int deleteOrganization(String organizationID) throws RemoteException {
		ArrayList<Object> objectList = organizationFile.read();

		if (objectList == null)
			return 1;

		for (int i = 0; i < objectList.size(); i++) {
			OrganizationPO tempOrganizationPO = (OrganizationPO) (objectList.get(i));
			if (tempOrganizationPO.getOrganizationID().equals(organizationID)) {
				objectList.remove(i);
				break;
			}
		}

		// organizationFile.clear();
		organizationFile.writeM(objectList);
		return 0;
	}

	public int modifyOrganization(OrganizationPO organizationpo) throws RemoteException {
		ArrayList<Object> objectList = organizationFile.read();

		if (objectList == null)
			return 1;

		for (int i = 0; i < objectList.size(); i++) {
			OrganizationPO tempOrganizationPO = (OrganizationPO) (objectList.get(i));
			if (tempOrganizationPO.getOrganizationID().equals(organizationpo.getOrganizationID())) {
				objectList.add(organizationpo);
				objectList.remove(i);
				break;
			}
		}

		organizationFile.writeM(objectList);
		return 0;
	}

	public OrganizationPO findOrganization(String organizationID) throws RemoteException {
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
	
	public ArrayList<String> getBelongingPlaces (String city) throws RemoteException {
		ArrayList<Object> objectList = organizationFile.read();
		ArrayList<String> belongingPlaces = new ArrayList<String>();
		
		if (objectList == null)
			return null;

		for (int i = 0; i < objectList.size(); i++) {
			OrganizationPO tempOrganizationPO = (OrganizationPO) (objectList.get(i));
			if(tempOrganizationPO.getName().startsWith(city) && 
				tempOrganizationPO.getCategory().equals(OrganizationType.businessHall)){
				String[] transition1 = tempOrganizationPO.getName().split("市");
				String[] transition2 = transition1[1].split("营业厅");
				belongingPlaces.add(transition2[0]);
			}
		}
		
		return belongingPlaces;
	}
	
	

	/*--------------------------------------------------Test Part---------------------------------------------------*/

	/*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/

	/*
	 * public static void main(String[] args){ OrganizationData
	 * organizationData; try{ organizationData = new OrganizationData(); try{
	 * organizationData.addOrganization(new
	 * OrganizationPO(OrganizationType.businessHall, "025000","鼓楼营业厅",new
	 * RepertoryPO("hehe","haha"))); organizationData.addOrganization(new
	 * OrganizationPO(OrganizationType.intermediateCenter, "025-0","南京中转中心",new
	 * RepertoryPO("025-CK","CK-01"))); organizationData.addOrganization(new
	 * OrganizationPO(OrganizationType.businessHall, "025001","仙林营业厅",new
	 * RepertoryPO("hehe","haha"))); organizationData.addOrganization(new
	 * OrganizationPO(OrganizationType.intermediateCenter, "030-0","上海中转中心",new
	 * RepertoryPO("030-CK","CK-02"))); organizationData.addOrganization(new
	 * OrganizationPO(OrganizationType.intermediateCenter, "035-0","北京中转中心",new
	 * RepertoryPO("035-CK","CK-03"))); organizationData.addOrganization(new
	 * OrganizationPO(OrganizationType.intermediateCenter,
	 * "040-0","坦桑尼亚中转中心",new RepertoryPO("040-CK","CK-04")));
	 * organizationData.addOrganization(new
	 * OrganizationPO(OrganizationType.intermediateCenter, "045-0","洛杉矶中转中心",new
	 * RepertoryPO("045-CK","CK-05")));
	 * 
	 * System.out.println("添加后:"); ArrayList<OrganizationPO> organizationpoList0
	 * = organizationData.showAllOrganizations(); if(organizationpoList0 !=
	 * null){ for(int i=0;i<organizationpoList0.size();i++){ OrganizationPO
	 * tempOrganizationpo = organizationpoList0.get(i);
	 * if(tempOrganizationpo.getCategory().equals(OrganizationType.
	 * intermediateCenter))
	 * System.out.println(tempOrganizationpo.getOrganizationID()+"  "
	 * +tempOrganizationpo.getName()+" 对应仓库："
	 * +tempOrganizationpo.getRepertory().getRepertoryID()); else
	 * System.out.println(tempOrganizationpo.getOrganizationID()+"  "
	 * +tempOrganizationpo.getName()); } } else System.out.println(
	 * "Cannot find the organization");
	 * 
	 * OrganizationPO organizationpo =
	 * organizationData.findOrganization("025-0"); if(organizationpo != null){
	 * if(organizationpo.getCategory().equals(OrganizationType.
	 * intermediateCenter)) System.out.println("Find the organization: "
	 * +organizationpo.getCategory()+" "+organizationpo.getOrganizationID()+" "
	 * +organizationpo.getName()+" 对应仓库："
	 * +organizationpo.getRepertory().getRepertoryID()); else
	 * System.out.println("Find the organization: "
	 * +organizationpo.getCategory()+" "+organizationpo.getOrganizationID()+" "
	 * +organizationpo.getName()); } else System.out.println(
	 * "Cannot find the organization");
	 * 
	 * organizationData.modifyOrganization(new
	 * OrganizationPO(OrganizationType.intermediateCenter,
	 * "025-0","南京呵呵呵中转中心",new RepertoryPO("025-呵呵呵-CK","CK-01")));
	 * System.out.println("修改后:"); ArrayList<OrganizationPO> organizationpoList3
	 * = organizationData.showAllOrganizations(); if(organizationpoList3 !=
	 * null){ for(int i=0;i<organizationpoList3.size();i++){ OrganizationPO
	 * tempOrganizationpo = organizationpoList3.get(i);
	 * if(tempOrganizationpo.getCategory().equals(OrganizationType.
	 * intermediateCenter))
	 * System.out.println(tempOrganizationpo.getOrganizationID()+"  "
	 * +tempOrganizationpo.getName()+" 对应仓库："
	 * +tempOrganizationpo.getRepertory().getRepertoryID()); else
	 * System.out.println(tempOrganizationpo.getOrganizationID()+"  "
	 * +tempOrganizationpo.getName()); } } else System.out.println(
	 * "Cannot find the organization");
	 * 
	 * System.out.println("没有删除前:"); ArrayList<OrganizationPO>
	 * organizationpoList1 = organizationData.showAllOrganizations();
	 * if(organizationpoList1 != null){ for(int
	 * i=0;i<organizationpoList1.size();i++){ OrganizationPO tempOrganizationpo
	 * = organizationpoList1.get(i);
	 * if(tempOrganizationpo.getCategory().equals(OrganizationType.
	 * intermediateCenter))
	 * System.out.println(tempOrganizationpo.getOrganizationID()+"  "
	 * +tempOrganizationpo.getName()+" 对应仓库："
	 * +tempOrganizationpo.getRepertory().getRepertoryID()); else
	 * System.out.println(tempOrganizationpo.getOrganizationID()+"  "
	 * +tempOrganizationpo.getName()); } } else System.out.println(
	 * "Cannot find the organization");
	 * 
	 * organizationData.deleteOrganization("025-0"); System.out.println("删除后:");
	 * ArrayList<OrganizationPO> organizationpoList2 =
	 * organizationData.showAllOrganizations(); if(organizationpoList2 != null){
	 * for(int i=0;i<organizationpoList2.size();i++){ OrganizationPO
	 * tempOrganizationpo = organizationpoList2.get(i);
	 * if(tempOrganizationpo.getCategory().equals(OrganizationType.
	 * intermediateCenter))
	 * System.out.println(tempOrganizationpo.getOrganizationID()+"  "
	 * +tempOrganizationpo.getName()+" 对应仓库："
	 * +tempOrganizationpo.getRepertory().getRepertoryID()); else
	 * System.out.println(tempOrganizationpo.getOrganizationID()+"  "
	 * +tempOrganizationpo.getName()); } } else System.out.println(
	 * "Cannot find the organization");
	 * 
	 * }catch(RemoteException exception){ exception.printStackTrace(); }
	 * }catch(RemoteException exception){ exception.printStackTrace(); } }
	 */

	/*------------------------------------- Part 2: Test server whether can normally work -----------------------------------*/

	public static void main(String[] args) {
		try {
			System.setProperty("java.rmi.server.hostname", "172.25.132.40");
			OrganizationDataService organizationData = new OrganizationData();
			LocateRegistry.createRegistry(6006);

			// 绑定RMI名称进行发布
			Naming.rebind("rmi://172.25.132.40:6006/OrganizationDataService", organizationData);
			System.out.println("Organization Service start!");

			ArrayList<OrganizationPO> organizationList0 = organizationData.showAllOrganizations();
			for (OrganizationPO organization : organizationList0)
				System.out.println("ID: " + organization.getOrganizationID() + ", Name: " + organization.getName());

			OrganizationPO organizationpo = organizationData.findOrganization("025000");
			System.out.println("ID: " + organizationpo.getOrganizationID() + ", Name: " + organizationpo.getName());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
