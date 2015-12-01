package po;

import java.io.Serializable;

import type.Sexuality;

public class DriverPO  implements Serializable{

	// 司机编号、姓名、出生日期、身份证号、手机、车辆单位、性别、行驶证期限
	private String ID;
	private String name;
	private String DateOfBirth;
	private String IdCardNumber;
	private String phoneNumber;
	private OrganizationPO vehicleOrganization;
	private Sexuality sexuality;
	private String registrationDeadline;


	public DriverPO() {
	}

	public DriverPO(String ID, String name, String dateOfBirth, String IdCardNumber, String phoneNumber,
			OrganizationPO vehicleOrganization, Sexuality sexuality, String registrationDeadline) {
		super();
		this.ID = ID;
		this.name = name;
		this.DateOfBirth = dateOfBirth;
		this.IdCardNumber = IdCardNumber;
		this.phoneNumber = phoneNumber;
		this.vehicleOrganization = vehicleOrganization;
		this.sexuality = sexuality;
		this.registrationDeadline = registrationDeadline;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getIdCardNumber() {
		return IdCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		IdCardNumber = idCardNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public OrganizationPO getVehicleOrganization() {
		return vehicleOrganization;
	}

	public void setVehicleOrganization(OrganizationPO vehicleOrganization) {
		this.vehicleOrganization = vehicleOrganization;
	}

	public Sexuality getSexuality() {
		return sexuality;
	}

	public void setSexuality(Sexuality sexuality) {
		this.sexuality = sexuality;
	}

	public String getRegistrationDeadline() {
		return registrationDeadline;
	}

	public void setRegistrationDeadline(String registrationDeadline) {
		this.registrationDeadline = registrationDeadline;
	}

}