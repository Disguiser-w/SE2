package businesslogicservice.repertoryblservice;

import vo.GoodsVO;

import java.util.ArrayList;

public interface RepertoryBLService {
	public int inventoryInitialization(String repertoryID, int maxRow, int maxShelf, int Maxdigit, int warningRatio);
//  										   仓库编号                    最多多少排     最多多少架          最多多少位         警戒比例
	public int enterRepertory(String repertoryID, String JJD_ID, int blockNum, int rowNum, int shelfNum, int digitNum, String date);
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
	public boolean inventoryWarning();
	public ArrayList<GoodsVO> inventoryCheck(String repertoryID, String beginDate, String endDate);
	public ArrayList<GoodsVO> inventoryStockTaking(String repertoryID);
}
