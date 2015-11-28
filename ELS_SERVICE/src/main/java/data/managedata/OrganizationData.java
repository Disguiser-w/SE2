package data.managedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.OrganizationPO;
import po.RepertoryPO;
import type.OrganizationType;
import file.JXCFile;
import dataservice.managedataservice.OrganizationDataService;

public class OrganizationData implements OrganizationDataService{

	JXCFile organizationFile;
    
    public OrganizationData() throws RemoteException {
		organizationFile = new JXCFile("src/main/java/organization.ser");
	}
    
    public int addOrganization(OrganizationPO organizationpo) throws RemoteException{
    	if(findOrganization(organizationpo.getOrganizationID())==null){
    		organizationFile.write(organizationpo);
    		return 0;
    	}
    	else 
    		return 1;
    }
    
    public int deleteOrganization(String organizationID) throws RemoteException{
    	ArrayList<Object> objectList = organizationFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			OrganizationPO tempOrganizationPO = (OrganizationPO)(objectList.get(i));
			if(tempOrganizationPO.getOrganizationID().equals(organizationID)){
				objectList.remove(i);
				break;
			}
		}
		
		//organizationFile.clear();
		organizationFile.writeM(objectList);
		return 0;
    }
    
    public int modifyOrganization(OrganizationPO organizationpo) throws RemoteException{
    	ArrayList<Object> objectList = organizationFile.read();
    	
		if(objectList==null)
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			OrganizationPO tempOrganizationPO = (OrganizationPO)(objectList.get(i));
			if(tempOrganizationPO.getOrganizationID().equals(organizationpo.getOrganizationID())){
				objectList.add(organizationpo);
				objectList.remove(i);
				break;
			}
		}
		
		organizationFile.writeM(objectList);
		return 0;
    }
    
    public OrganizationPO findOrganization(String organizationID) throws RemoteException{
    	ArrayList<Object> objectList = organizationFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return null;  	  
		
		for(int i=0; i<objectList.size(); i++){
			OrganizationPO tempOrganizationPO = (OrganizationPO)(objectList.get(i));
			if(tempOrganizationPO.getOrganizationID().equals(organizationID)){
				return tempOrganizationPO;
			}
		}
		
		return null;
    }
    
    public ArrayList<OrganizationPO> showAllOrganizations() throws RemoteException{
    	ArrayList<Object> objectList = organizationFile.read();
    	
		if(objectList==null)	//不存在该机构	
			return null;  	  
		
		ArrayList<OrganizationPO> organizationList = new ArrayList<OrganizationPO>();
		
		for(int i=0; i<objectList.size(); i++){
			OrganizationPO tempOrganizationPO = (OrganizationPO)(objectList.get(i));
			organizationList.add(tempOrganizationPO);
		}
		
		return organizationList;
    }	
    
    /*public static void main(String[] args){
		OrganizationData organizationData;
		try{
			organizationData = new OrganizationData();
			try{
				organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "025000","鼓楼营业厅",new RepertoryPO("hehe","haha")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.intermediateCenter, "025-0","南京中转中心",new RepertoryPO("025-CK","CK-01")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "025001","仙林营业厅",new RepertoryPO("hehe","haha")));
				organizationData.addOrganization(new OrganizationPO(OrganizationType.intermediateCenter, "030-0","上海中转中心",new RepertoryPO("030-CK","CK-02")));
				
				OrganizationPO organizationpo = organizationData.findOrganization("025-0");
				if(organizationpo != null){
					if(organizationpo.getCategory().equals(OrganizationType.intermediateCenter))
						System.out.println(organizationpo.getOrganizationID()+" "+organizationpo.getName()+" 对应仓库："+organizationpo.getRepertory().getRepertoryID());
					else
						System.out.println(organizationpo.getOrganizationID()+" "+organizationpo.getName());
				}
				else
					System.out.println("Cannot find the organization");
				
				System.out.println("添加后:");
				ArrayList<OrganizationPO> organizationpoList0 = organizationData.showAllOrganizations();
				if(organizationpoList0 != null){
	    			for(int i=0;i<organizationpoList0.size();i++){
	    				OrganizationPO tempOrganizationpo = organizationpoList0.get(i);
	    				if(tempOrganizationpo.getCategory().equals(OrganizationType.intermediateCenter))
	    					System.out.println(tempOrganizationpo.getOrganizationID()+"  "+tempOrganizationpo.getName()+" 对应仓库："+organizationpo.getRepertory().getRepertoryID());
	    				else
	    					System.out.println(tempOrganizationpo.getOrganizationID()+"  "+tempOrganizationpo.getName());
	    			}
				}
				else 
					System.out.println("Cannot find the organization");
					
				System.out.println("修改后:");
				organizationData.modifyOrganization(new OrganizationPO(OrganizationType.intermediateCenter, "025-0","南京呵呵呵中转中心",new RepertoryPO("025-呵呵呵-CK","CK-01")));
				ArrayList<OrganizationPO> organizationpoList3 = organizationData.showAllOrganizations();
				if(organizationpoList3 != null){
	    			for(int i=0;i<organizationpoList3.size();i++){
	    				OrganizationPO tempOrganizationpo = organizationpoList3.get(i);
	    				if(tempOrganizationpo.getCategory().equals(OrganizationType.intermediateCenter))
	    					System.out.println(tempOrganizationpo.getOrganizationID()+"  "+tempOrganizationpo.getName()+" 对应仓库："+organizationpo.getRepertory().getRepertoryID());
	    				else
	    					System.out.println(tempOrganizationpo.getOrganizationID()+"  "+tempOrganizationpo.getName());
	    			}
				}
				else 
					System.out.println("Cannot find the organization");
				
				System.out.println("没有删除前:");
				ArrayList<OrganizationPO> organizationpoList1 = organizationData.showAllOrganizations();
				if(organizationpoList1 != null){
	    			for(int i=0;i<organizationpoList1.size();i++){
	    				OrganizationPO tempOrganizationpo = organizationpoList1.get(i);
	    				if(tempOrganizationpo.getCategory().equals(OrganizationType.intermediateCenter))
	    					System.out.println(tempOrganizationpo.getOrganizationID()+"  "+tempOrganizationpo.getName()+" 对应仓库："+organizationpo.getRepertory().getRepertoryID());
	    				else
	    					System.out.println(tempOrganizationpo.getOrganizationID()+"  "+tempOrganizationpo.getName());
	    			}
				}
				else 
					System.out.println("Cannot find the organization");
				
				System.out.println("删除后:");
				organizationData.deleteOrganization("025-0");
				ArrayList<OrganizationPO> organizationpoList2 = organizationData.showAllOrganizations();
				if(organizationpoList2 != null){
	    			for(int i=0;i<organizationpoList2.size();i++){
	    				OrganizationPO tempOrganizationpo = organizationpoList2.get(i);
	    				if(tempOrganizationpo.getCategory().equals(OrganizationType.intermediateCenter))
	    					System.out.println(tempOrganizationpo.getOrganizationID()+"  "+tempOrganizationpo.getName()+" 对应仓库："+organizationpo.getRepertory().getRepertoryID());
	    				else
	    					System.out.println(tempOrganizationpo.getOrganizationID()+"  "+tempOrganizationpo.getName());
	    			}
				}
				else 
					System.out.println("Cannot find the organization");
				
			}catch(RemoteException exception){
				exception.printStackTrace();
			}
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
	}*/
    
    
    
}
