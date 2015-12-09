package po;

import java.io.Serializable;

public class VehiclePO  implements Serializable{
	// 车辆代号、发动机号、车辆号、底盘号、购买时间、服役时间
	private String ID;
	private String engineNumber;
	private String licensePlateNumber;
	private String lowNumberPlate;
	private String buyTime;
	private String serviceTime;
	private OrganizationPO destination;
	private String destinationCity;
	private OrganizationPO local;
	private DriverPO driver;

	public VehiclePO(String ID, String engineNumber, String licensePlateNumber, String lowNumberPlate, String buyTime,
			String serviceTime, OrganizationPO destination, String destinationCity, OrganizationPO local,
			DriverPO driver) {
		super();
		this.ID = ID;
		this.engineNumber = engineNumber;
		this.licensePlateNumber = licensePlateNumber;
		this.lowNumberPlate = lowNumberPlate;
		this.buyTime = buyTime;
		this.serviceTime = serviceTime;
		this.destination = destination;
		this.local = local;
		this.driver = driver;
		this.destinationCity = destinationCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public DriverPO getDriver() {
		return driver;
	}

	public void setDriver(DriverPO driver) {
		this.driver = driver;
	}

	public OrganizationPO getDestination() {
		return destination;
	}

	public void setDestination(OrganizationPO destination) {
		this.destination = destination;
	}

	public OrganizationPO getLocal() {
		return local;
	}

	public void setLocal(OrganizationPO local) {
		this.local = local;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public String getLowNumberPlate() {
		return lowNumberPlate;
	}

	public void setLowNumberPlate(String lowNumberPlate) {
		this.lowNumberPlate = lowNumberPlate;
	}

	public String getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
}
