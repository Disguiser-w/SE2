package vo;

import type.Sexuality;

public class DriverVO {

	// 司机编号、姓名、出生日期、身份证号、手机、车辆单位、性别、行驶证期限
	public final String ID;
	public final String name;
	public final String DateOfBirth;
	public final String IdCardNumber;
	public final String phoneNumber;
	public final OrganizationVO vehicleOrganization;
	public final Sexuality sexuality;
	public final String registrationDeadline;
	public final int time;
	public boolean isUsing;

	public DriverVO(String ID, String name, String dateOfBirth, String IdCardNumber, String phoneNumber,
			OrganizationVO vehicleOrganization, Sexuality sexuality, String registrationDeadline,int time) {
		super();
		this.ID = ID;
		this.name = name;
		this.DateOfBirth = dateOfBirth;
		this.IdCardNumber = IdCardNumber;
		this.phoneNumber = phoneNumber;
		this.vehicleOrganization = vehicleOrganization;
		this.sexuality = sexuality;
		this.registrationDeadline = registrationDeadline;
		this.time = time;
		this.isUsing = false;
	}
	
	public DriverVO(String ID, String name, String dateOfBirth, String IdCardNumber, String phoneNumber,
			OrganizationVO vehicleOrganization, Sexuality sexuality, String registrationDeadline,int time,boolean isUsing) {
		super();
		this.ID = ID;
		this.name = name;
		this.DateOfBirth = dateOfBirth;
		this.IdCardNumber = IdCardNumber;
		this.phoneNumber = phoneNumber;
		this.vehicleOrganization = vehicleOrganization;
		this.sexuality = sexuality;
		this.registrationDeadline = registrationDeadline;
		this.time = time;
		this.isUsing = isUsing;
	}
}