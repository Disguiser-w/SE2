package po;

import type.OrganizationType;
import po.RepertoryPO;

public class OrganizationPO {
	private OrganizationType category;
	private String organizationID;
	private String name;
	private RepertoryPO repertory;
	
	public OrganizationPO(OrganizationType category, String organizationID, String name, RepertoryPO repertory){
//                                         类别                               编号                                  名称
//	                                                         025000				  鼓楼营业厅
//	                                                         025-0   			  南京中转中心
		this.category = category;
		this.organizationID = organizationID;
		this.name = name;
		
		//如果新建机构是中转中心的话，同时新建一个对应的中转中心仓库
		if(category.equals(OrganizationType.intermediateCenter)){
			this.repertory = new RepertoryPO(organizationID+"-CK", "");
		}
		else{
			this.repertory = null;
		}
		
	}
	
	public OrganizationType getCategory(){
		return this.category;
	}
	
	public void setCategory(OrganizationType category){
		this.category = category;
	}
	
	public String getOrganizationID(){
		return this.organizationID;
	}
	
	public void setOrganizationID(String organizationID){
		this.organizationID = organizationID;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public RepertoryPO getRepertory(){
		return this.repertory;
	}
	
	public void setRepertory(RepertoryPO repertory){
		this.repertory = repertory;
	}
	
}
