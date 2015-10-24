package businesslogic.businessbl;

import java.util.ArrayList;

/**
 * 营业厅业务员-车辆信息管理
 */
public interface VehicleManagerBLService {
	/**
	 * 系统根据车辆编号返回对于那个的车辆信息
	 */
	public ArrayList<VehicleVO> getVehicleInfo(String ID);

	/**
	 * 系统更新车辆列表，并返回更新结果
	 * */
	public boolean addVehicle(VehicleVO vo)；

	/**
	 * 系统删除车辆信息，并返回更新结果
	 */
	public boolean deleteVehicle(VehicleVO vo);

	/**
	 * 系统更新车辆信息，并返回结果
	 * */
	public boolean modifyVehicle(VehicleVO vo)
}
