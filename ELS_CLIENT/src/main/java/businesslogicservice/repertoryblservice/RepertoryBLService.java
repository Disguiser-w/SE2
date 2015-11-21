package businesslogicservice.repertoryblservice;

import vo.GoodsVO;
import vo.InventoryVO;
import po.GoodsPO;
import po.InventoryPO;

import java.util.ArrayList;

public interface RepertoryBLService {
	public int inventoryInitialization(String repertoryID, int maxRow, int maxShelf, int maxDigit, int warningRatio);
//  										   仓库编号                    最多多少排     最多多少架          最多多少位         警戒比例
	public int enterRepertory(String repertoryID, String JJD_ID, int blockNum, String date);
//								 	 仓库编号                          寄件单号          区号                      排号               架号                    位号           入库日期
//                                   0 飞机区
//                                   1 火车区
//                                   2 汽车区
//                                   3 机动区;

	public int leaveRepertory(String repertoryID, String JJD_ID, int transType, String date);
//                               仓库编号                          寄件单号         转运方式                    出库日期
//                                                               0 飞机转运
//                                                               1 火车转运
//                                                               2 汽车转运
	public String inventoryWarning(String repertoryID);
	public ArrayList<InventoryVO> inventoryCheck(String repertoryID, String beginDate, String endDate);
	public ArrayList<InventoryVO> inventoryStockTaking(String repertoryID);
}
