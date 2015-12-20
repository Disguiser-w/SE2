package businesslogic.intermediatebl.driver;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import type.OrganizationType;
import vo.OrganizationVO;
import vo.TrainVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.TrainManagerBL;
import dataservice.intermediatedataservice.IntermediateDataService;

public class TrainManager_driver {
	private static IntermediateDataService intermediateData;

	public static void main(String[] args) {
		OrganizationVO intermediateCentre = new OrganizationVO(
				OrganizationType.intermediateCenter, "025001", "南京中转中心");
		TrainVO traine1 = new TrainVO(intermediateCentre.organizationID
				+ "-001", "天国");
		TrainVO traine2 = new TrainVO(intermediateCentre.organizationID
				+ "-002", "天国");
		TrainVO traine3 = new TrainVO(intermediateCentre.organizationID
				+ "-003", "天国");

		ArrayList<TrainVO> traineList = new ArrayList<TrainVO>();
		traineList.add(traine1);
		traineList.add(traine2);
		traineList.add(traine3);

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

		TrainManagerBL pmbl = new TrainManagerBL(traineList,
				intermediateCentre, intermediateData);

		System.out.println(intermediateCentre.name + "此时拥有 "
				+ pmbl.showTrainList().size() + " 架火车");
		try {
			pmbl.addTrain(intermediateCentre.organizationID + "-004", "地狱");
			pmbl.deleteTrain(intermediateCentre.organizationID + "-003");
			pmbl.modifyTrain(new TrainVO(intermediateCentre.organizationID
					+ "-002", "仙林大道"));
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out
				.println("现在增加一架火车……ID为004，并删除ID为003的火车……并将ID为002的火车目的地改为仙林大道……");
		System.out.println("我们来依次看看这些火车");
		try {
			System.out.println(pmbl.showTrain(intermediateCentre.organizationID
					+ "-002").destination);
			System.out.println(pmbl.showTrain(intermediateCentre.organizationID
					+ "-004").destination);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		System.out.println("保存中……");
		try {
			pmbl.saveTrainList();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			System.out.println("读取保存的火车列表，其中第一架火车的ID和目的地： "
					+ intermediateData
							.getTrainList(intermediateCentre.organizationID)
							.get(0).getID()
					+ " "
					+ intermediateData
							.getTrainList(intermediateCentre.organizationID)
							.get(0).getDestination());
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
