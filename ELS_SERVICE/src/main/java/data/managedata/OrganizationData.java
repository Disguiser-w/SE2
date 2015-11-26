package data.managedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.OrganizationPO;

public class OrganizationData {

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
		
		organizationFile.clear();
		organizationFile.write(objectList);
		return 0;
    }
    
    public int modifyOrganization(OrganizationPO organizationpo) throws RemoteException{
    	String organizationID = organizationpo.getOrganizationID();
    	if(deleteOrganization(organizationID)==0){
    		addOrganization(organizationpo);
    		return 0;
    	}
    	return 1;
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
    
}
