package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.OrganizationType;

public class OrganizationPO implements Serializable {

	private static final long serialVersionUID = 141250151L;

	private OrganizationType category;
	private String organizationID;
	private String name;
	private RepertoryPO repertory;

	private ArrayList<PlanePO> planeList = new ArrayList<PlanePO>();
	private ArrayList<TrainPO> trainList = new ArrayList<TrainPO>();
	private ArrayList<TruckPO> truckList = new ArrayList<TruckPO>();

	public OrganizationPO(OrganizationType category, String organizationID,
			String name, RepertoryPO repertory) {
		// 类别 编号 名称
		// 025000 鼓楼营业厅
		// 025-0 南京中转中心
		this.category = category;
		this.organizationID = organizationID;
		this.name = name;

		// 如果新建机构是中转中心的话，同时新建一个对应的中转中心仓库
		if (category.equals(OrganizationType.intermediateCenter)) {
			this.repertory = repertory;
		} else {
			this.repertory = null;
		}

	}

	public OrganizationPO(OrganizationType category, String organizationID,
			String name) {

		this.category = category;
		this.organizationID = organizationID;
		this.name = name;
	}

	public OrganizationType getCategory() {
		return this.category;
	}

	public void setCategory(OrganizationType category) {
		this.category = category;
	}

	public String getOrganizationID() {
		return this.organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RepertoryPO getRepertory() {
		return this.repertory;
	}

	public void setRepertory(RepertoryPO repertory) {
		this.repertory = repertory;
	}

	public ArrayList<PlanePO> getPlaneList() {
		return planeList;
	}

	public void setPlaneList(ArrayList<PlanePO> planeList) {
		this.planeList = planeList;
	}

	public ArrayList<TrainPO> getTrainList() {
		return trainList;
	}

	public void setTrainList(ArrayList<TrainPO> trainList) {
		this.trainList = trainList;
	}

	public ArrayList<TruckPO> getTruckList() {
		return truckList;
	}

	public void setTruckList(ArrayList<TruckPO> truckList) {
		this.truckList = truckList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
