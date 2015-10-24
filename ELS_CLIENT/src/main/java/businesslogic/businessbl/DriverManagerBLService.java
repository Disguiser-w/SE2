package businesslogic.businessbl;

import java.util.ArrayList;

/**
 * 营业厅业务员-车辆信息管理
 */
public interface DriverManagerBLService {
	/**
	 * 系统返回所有的车辆信息
	 */
	public ArrayList<VehicleVO> getVehicleInfo(String ID);

	/**
	 * 系统根据车辆信息更新车辆信息列表，并返回更新结果
	 */
	public boolean addVehicle(VehicleVO vo);

	/**
	 * 系统删除对应的车辆信息，并返回删除结果
	 */
	public boolean deleteVehicle(VehicleVO vo);

	/**
	 * 系统修改对应的车辆信息，并返回修改结果
	 */
	public boolean modifyVehicle(VehicleVO vo);
}
