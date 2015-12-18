package businesslogic.managebl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.datafactory.DataFactory;
import businesslogicservice.manageblservice.OrganizationBLService;
import common.FileGetter;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.userdataservice.UserDataService;
import po.OrganizationPO;
import po.RepertoryPO;
import po.UserPO;
import type.OrganizationType;
import vo.OrganizationVO;
import vo.RepertoryVO;

public class OrganizationBL implements OrganizationBLService {

	public OrganizationDataService odService;
	public UserDataService udService;

	public OrganizationBL() {
		try {
			odService = (OrganizationDataService) Naming.lookup("rmi://localhost:8888/OrganizationDataService");
			udService = (UserDataService) Naming.lookup("rmi://localhost:8888/UserDataService");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public int addOrganization(OrganizationVO organizationvo) {
		try {
			OrganizationPO neworganizationpo = organizationVOToPO(organizationvo);
			return (odService.addOrganization(neworganizationpo));
		} catch (RemoteException exception) {
			exception.printStackTrace();
			return 2;
		}
	}

	public int deleteOrganization(String organizationID) {
		try {
			return (odService.deleteOrganization(organizationID));
		} catch (RemoteException exception) {
			exception.printStackTrace();
			return 2;
		}
	}

	public int modifyOrganization(OrganizationVO organizationvo) {
		try {
			OrganizationPO organizationpo = organizationVOToPO(organizationvo);
			return (odService.modifyOrganization(organizationpo));
		} catch (RemoteException exception) {
			exception.printStackTrace();
			return 2;
		}
	}

	public OrganizationVO findOrganization(String organizationID) {
		try {
			OrganizationPO organizationpo = odService.findOrganizationByID(organizationID);
			return organizationPOToVO(organizationpo);
		} catch (RemoteException exception) {
			exception.printStackTrace();
			return null;
		}
	}

	public ArrayList<OrganizationVO> findOrganizationByKeyword(String keyword) {
		try {
			ArrayList<OrganizationPO> organizationpoList = odService.findOrganizationByKeyword(keyword);
			ArrayList<OrganizationVO> organizationvoList = new ArrayList<OrganizationVO>();

			for (OrganizationPO organization : organizationpoList)
				organizationvoList.add(organizationPOToVO(organization));
			return organizationvoList;
		} catch (RemoteException exception) {
			exception.printStackTrace();
			return null;
		}
	}
	
	public int chooseDepartment(String userID, String organizationID) {
		try {
			UserPO userpo = udService.findUserByID(userID);
			udService.modifyUserOrganization(userpo.getID(), organizationID);
			return 0;
		} catch (RemoteException exception) {
			exception.printStackTrace();
			return 1;
		}

	}

	public ArrayList<OrganizationVO> showAllOrganizations() {
		try {
			ArrayList<OrganizationPO> organizationPOList = odService.showAllOrganizations();
			ArrayList<OrganizationVO> organizationVOList = new ArrayList<OrganizationVO>();

			for (OrganizationPO organization : organizationPOList)
				organizationVOList.add(organizationPOToVO(organization));
			return organizationVOList;
		} catch (RemoteException exception) {
			exception.printStackTrace();
			return null;
		}
	}

	public static RepertoryVO repertoryPOToVO(RepertoryPO repertorypo) {
		if (repertorypo != null) {
			return new RepertoryVO(repertorypo.getRepertoryID(), repertorypo.getOwnerID(), repertorypo.getMaxRow(),
					repertorypo.getMaxShelf(), repertorypo.getMaxDigit(), repertorypo.getWarningRatio(),
					repertorypo.getStockNumArray());
		} else {
			return null;
		}
	}

	public static RepertoryPO repertoryVOToPO(RepertoryVO repertoryvo) {
		if (repertoryvo != null) {
			return new RepertoryPO(repertoryvo.repertoryID, repertoryvo.ownerID, repertoryvo.maxRow,
					repertoryvo.maxShelf, repertoryvo.maxDigit, repertoryvo.warningRatio,
					repertoryvo.stockNum);
		} else {
			return null;
		}
	}

	public static OrganizationVO organizationPOToVO(OrganizationPO organizationpo) {
		if (organizationpo != null) {
			if (organizationpo.getRepertory() != null)
				return new OrganizationVO(organizationpo.getCategory(), organizationpo.getOrganizationID(),
						organizationpo.getName(), repertoryPOToVO(organizationpo.getRepertory()));
			else
				return new OrganizationVO(organizationpo.getCategory(), organizationpo.getOrganizationID(),
						organizationpo.getName(), null);
		} else {
			return null;
		}
	}

	public static OrganizationPO organizationVOToPO(OrganizationVO organizationvo) {
		return new OrganizationPO(organizationvo.category, organizationvo.organizationID,
				organizationvo.name, repertoryVOToPO(organizationvo.repertory));
	}

	/*--------------------------------------------------Test Part---------------------------------------------------*/

	/*------------------------------------- Test server whether can normally work ----------------------------------*/

	public static void main(String[] args) {
		try {
			OrganizationDataService organizationData = DataFactory.getOrganizationData();

			ArrayList<OrganizationPO> organizationList0 = organizationData.showAllOrganizations();
			for (OrganizationPO organization : organizationList0)
				System.out.println(("ID: " + organization.getOrganizationID() + ", Name: " + organization.getName()));

			organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "012000", "南极营业厅"));

			ArrayList<OrganizationPO> organizationList1 = organizationData.showAllOrganizations();
			for (OrganizationPO organization : organizationList1)
				System.out.println(("ID: " + organization.getOrganizationID() + ", Name: " + organization.getName()));

			//OrganizationPO po1 = new OrganizationPO(OrganizationType.businessHall, "012000", "南极营业厅");

			File file = FileGetter.getFile("../src/organization.ser");
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}

			ArrayList<OrganizationPO> pos = new ArrayList<OrganizationPO>();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(pos);
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
