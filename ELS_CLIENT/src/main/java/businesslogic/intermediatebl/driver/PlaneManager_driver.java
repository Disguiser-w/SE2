package businesslogic.intermediatebl.driver;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.intermediatedataservice.IntermediateDataService;
import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.PlaneManagerBL;
import type.OrganizationType;
import vo.OrganizationVO;
import vo.PlaneVO;

public class PlaneManager_driver {
	private IntermediateDataService intermediateData;

	public void main(String[] args) {
		OrganizationVO intermediateCentre = new OrganizationVO(
				OrganizationType.intermediateCenter, "025001", "南京中转中心");
		PlaneVO plane1 = new PlaneVO(
				intermediateCentre.organizationID + "-001", "天国");
		PlaneVO plane2 = new PlaneVO(
				intermediateCentre.organizationID + "-002", "天国");
		PlaneVO plane3 = new PlaneVO(
				intermediateCentre.organizationID + "-003", "天国");

		ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
		planeList.add(plane1);
		planeList.add(plane2);
		planeList.add(plane3);

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

		PlaneManagerBL pmbl = new PlaneManagerBL(planeList, intermediateCentre,
				intermediateData);

		System.out.println(intermediateCentre.name + "此时拥有 "
				+ pmbl.showPlaneList().size() + " 架飞机");
		try {
			pmbl.addPlane(intermediateCentre.organizationID + "-004", "地狱");
			pmbl.deletePlane(new PlaneVO(intermediateCentre.organizationID
					+ "-003", null));
			pmbl.modifyPlane(new PlaneVO(intermediateCentre.organizationID + "-002", "仙林大道"));
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("现在增加一架飞机……ID为004，并删除ID为003的飞机……并将ID为002的飞机目的地改为仙林大道……");
		System.out.println("我们来依次看看这些飞机");
		System.out.println(pmbl.showPlane(intermediateCentre.organizationID + "-002"))
	}
}
