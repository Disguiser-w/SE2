package vo;

public class VehicleVO {
	// 车辆代号、发动机号、车辆号、底盘号、购买时间、服役时间
	public final String ID;
	public final String engineNumber;
	public final String licensePlateNumber;
	public final String lowNumberPlate;
	public final String buyTime;
	public final String serviceTime;
	public final String location;

	public VehicleVO(String ID, String engineNumber, String licensePlateNumber, String lowNumberPlate, String buyTime,
			String serviceTime,String location) {
		super();
		this.ID = ID;
		this.engineNumber = engineNumber;
		this.licensePlateNumber = licensePlateNumber;
		this.lowNumberPlate = lowNumberPlate;
		this.buyTime = buyTime;
		this.serviceTime = serviceTime;
		this.location = location;
	}

}
