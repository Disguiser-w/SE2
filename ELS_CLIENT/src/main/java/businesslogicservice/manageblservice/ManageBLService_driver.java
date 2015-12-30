package businesslogicservice.manageblservice;

import java.rmi.RemoteException;

import businesslogic.managebl.BasicSalaryBL;
import businesslogic.managebl.CityDistanceBL;
import businesslogic.managebl.CostBL;
import businesslogic.managebl.OrganizationBL;
import businesslogic.managebl.PerWageBL;
import businesslogic.managebl.ReviewReceiptBL;
import type.ExpressType;
import type.OrganizationType;
import type.ProfessionType;
import vo.BasicSalaryVO;
import vo.CityDistanceVO;
import vo.CostVO;
import vo.OrganizationVO;
import vo.PerWageVO;

public class ManageBLService_driver {

	public void driveOrganization(OrganizationBLService organizationBLService) throws RemoteException{
		organizationBLService.deleteOrganization("021000");
		organizationBLService.addOrganization(new OrganizationVO(OrganizationType.businessHall, "021000", "北京市朝阳营业厅", null));
        organizationBLService.findOrganization("025000");
        organizationBLService.showAllOrganizations();
        organizationBLService.chooseDepartment("KD-00001", "025000");
	}
	
	public void drivePerWage(PerWageBLService perWageBLService) throws RemoteException{
		perWageBLService.modifyPerWage(new PerWageVO(ProfessionType.courier, 0.5));
		perWageBLService.findPerWage(ProfessionType.courier);
		perWageBLService.showAllPerWages();
	}
	
	public void driveBasicSalary(BasicSalaryBLService basicSalaryBLService) throws RemoteException{
		basicSalaryBLService.modifyBasicSalary(new BasicSalaryVO(ProfessionType.courier, 2000));
		basicSalaryBLService.findBasicSalary(ProfessionType.courier);
		basicSalaryBLService.showAllBasicSalarys();
	}
	
	public void driveCityDistance(CityDistanceBLService cityDistanceBLService) throws RemoteException{
		cityDistanceBLService.deleteCityDistance(new CityDistanceVO("南京", "上海", 266));
		cityDistanceBLService.addCityDistance(new CityDistanceVO("南京", "上海", 266));
        cityDistanceBLService.modifyCityDistance(new CityDistanceVO("南京", "上海", 266));
        cityDistanceBLService.findCityDistanceBySingle( "上海");
        cityDistanceBLService.findCityDistanceByBoth("南京", "上海");
        cityDistanceBLService.showAllCityDistances();
	}
	
	public void driveCost(CostBLService costBLService) throws RemoteException{
        costBLService.modifyCost(new CostVO(ExpressType.STANDARD, 23));
        costBLService.findCost(ExpressType.STANDARD);
        costBLService.showAllCosts();
	}
	

	public void driveReceipt(ReviewReceiptBLService rrs) throws RemoteException{
		rrs.getAllReceiptList();
	}
	
	
	public static void main(String[] args) throws RemoteException{
		OrganizationBLService organizationBLService = new OrganizationBL();
		ReviewReceiptBLService reviewReceiptBLService = new ReviewReceiptBL();
		PerWageBLService perWageBLService = new PerWageBL();
		BasicSalaryBLService basicSalaryBLService = new BasicSalaryBL();
		CityDistanceBLService cityDistanceBLService = new CityDistanceBL();
		CostBLService costBLService = new CostBL();
		
		ManageBLService_driver driver = new ManageBLService_driver();
       
		driver.driveOrganization(organizationBLService);
		driver.drivePerWage(perWageBLService);
        driver.driveBasicSalary(basicSalaryBLService);
        driver.driveCityDistance(cityDistanceBLService);
        driver.driveCost(costBLService);
        driver.driveReceipt(reviewReceiptBLService);
    }
	
}
