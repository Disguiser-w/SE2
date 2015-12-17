package vo;

import java.util.ArrayList;

import type.OrganizationType;

public class OrganizationVO {
	
	public OrganizationType category;
	public String organizationID;
	public String name;
	public RepertoryVO repertory;
	
	public ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
	public ArrayList<TrainVO> trainList = new ArrayList<TrainVO>();
	public ArrayList<TruckVO> truckList = new ArrayList<TruckVO>();

	public OrganizationVO(OrganizationType category, String organizationID,
			String name, RepertoryVO repertory) {

		// 类别 编号 名称
		// 025000 鼓楼营业厅
		// 025-0 南京中转中心
		this.category = category;
		this.organizationID = organizationID;
		this.name = name;

		// 如果新建机构是中转中心的话，同时新建一个对应的中转中心仓库
		if (category.equals(OrganizationType.intermediateCenter)) {
			this.repertory = new RepertoryVO(organizationID + "-CK", "");
		} else {
			this.repertory = null;
		}
	}
	
	public OrganizationVO(OrganizationType category, String organizationID, String name){

		this.category = category;
		this.organizationID = organizationID;
		this.name = name;
	}
	
}