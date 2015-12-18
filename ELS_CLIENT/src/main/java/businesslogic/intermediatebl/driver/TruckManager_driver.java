package businesslogic.intermediatebl.driver;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import type.OrganizationType;
import vo.OrganizationVO;
import vo.TruckVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.TruckManagerBL;
import dataservice.intermediatedataservice.IntermediateDataService;

public class TruckManager_driver {
	private static IntermediateDataService intermediateData;

	public static void main(String[] args) {
		OrganizationVO intermediateCentre = new OrganizationVO(
				OrganizationType.intermediateCenter, "025001", "南京中转中心");
		TruckVO trucke1 = new TruckVO(
				intermediateCentre.organizationID + "-001", "天国");
		TruckVO trucke2 = new TruckVO(
				intermediateCentre.organizationID + "-002", "天国");
		TruckVO trucke3 = new TruckVO(
				intermediateCentre.organizationID + "-003", "天国");

		ArrayList<TruckVO> truckeList = new ArrayList<TruckVO>();
		truckeList.add(trucke1);
		truckeList.add(trucke2);
		truckeList.add(trucke3);

		try {
			intermediateData = DataFactory.getIntermediateData();
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		TruckManagerBL pmbl = new TruckManagerBL(truckeList, intermediateCentre,
				intermediateData);

		System.out.println(intermediateCentre.name + "此时拥有 "
				+ pmbl.showTruckList().size() + " 架汽车");
		try {
			pmbl.addTruck(intermediateCentre.organizationID + "-004", "地狱");
			pmbl.deleteTruck(new TruckVO(intermediateCentre.organizationID
					+ "-003", null));
			pmbl.modifyTruck(new TruckVO(intermediateCentre.organizationID
					+ "-002", "仙林大道"));
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out
				.println("现在增加一架汽车……ID为004，并删除ID为003的汽车……并将ID为002的汽车目的地改为仙林大道……");
		System.out.println("我们来依次看看这些汽车");
		try {
			System.out.println(pmbl.showTruck(intermediateCentre.organizationID
					+ "-002").destination);
			System.out.println(pmbl.showTruck(intermediateCentre.organizationID
					+ "-004").destination);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		System.out.println("保存中……");
		try {
			pmbl.saveTruckList();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			System.out.println("读取保存的汽车列表，其中第一架汽车的ID和目的地： "
					+ intermediateData
							.getTruckList(intermediateCentre.organizationID)
							.get(0).getID()
					+ " "
					+ intermediateData
							.getTruckList(intermediateCentre.organizationID)
							.get(0).getDestination());
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
