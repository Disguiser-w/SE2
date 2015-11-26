package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.managebl.OrganizationBL;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import po.DriverPO;
import vo.DriverVO;

public class DriverManager {

	private BusinessDataService businessData;

	public DriverManager() {
		businessData = new BusinessDataService_stub();
	}

	public ArrayList<DriverVO> getDriverInfo() {
		ArrayList<DriverVO> vos = new ArrayList<DriverVO>();
		ArrayList<DriverPO> pos = null;
		try {
			pos = businessData.getDriverInfos();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (DriverPO i : pos)
			vos.add(poToVO(i));
		return vos;
	}

	public boolean addDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.addDriver(voToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.deleteDriver(voToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean modifyDriver(DriverVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.modifyDriver(voToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static DriverVO poToVO(DriverPO po) {

		return new DriverVO(po.getID(), po.getName(), po.getDateOfBirth(), po.getIdCardNumber(), po.getPhoneNumber(),
				OrganizationBL.organizationPOToVO(po.getVehicleOrganization()), po.getSexuality(),
				po.getRegistrationDeadline());
	}

	public static DriverPO voToPO(DriverVO vo) {
		return new DriverPO(vo.ID, vo.name, vo.DateOfBirth, vo.IdCardNumber, vo.phoneNumber,
				OrganizationBL.organizationVOToPO(vo.vehicleOrganization), vo.sexuality, vo.registrationDeadline);
	}

}
