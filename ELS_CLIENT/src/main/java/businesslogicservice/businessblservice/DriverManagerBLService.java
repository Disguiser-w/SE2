package businesslogicservice.businessblservice;

import java.util.ArrayList;

import vo.DriverVO;

/**
 * 营业厅业务员-车辆信息管理
 */
public interface DriverManagerBLService {
	/**
	 * 系统返回所有的车辆信息
	 */
	public ArrayList<DriverVO> getDriverInfo();

	/**
	 * 系统根据车辆信息更新车辆信息列表，并返回更新结果
	 */
	public boolean addDriver(DriverVO vo);

	/**
	 * 系统删除对应的车辆信息，并返回删除结果
	 */
	public boolean deleteDriver(DriverVO vo);

	/**
	 * 系统修改对应的车辆信息，并返回修改结果
	 */
	public boolean modifyDriver(DriverVO vo);

}
