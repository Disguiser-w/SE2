package businesslogicservice.financeblservice;

import java.util.ArrayList;

import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;
/**
 * 创建入款单
 * */
public interface CollectionReceiptBLService {
	//创建入款单
	public int creatCollection(CollectionReceiptVO vo);
	//获取特定时间入款单
	public CollectionReceiptVO getCollection(String s);
	//获取所有入款单
	public ArrayList<CollectionReceiptVO> getAllCollection();
	
	//依据时间获取收款单
	public ArrayList<GatheringReceiptVO> getGatheringByTime(String Time);
	
	//依据营业厅编号获取收款单
	public ArrayList<GatheringReceiptVO> getGatheringByHall(String hallID);
	
	//依据时间和营业厅编号获取收款单
	public ArrayList<GatheringReceiptVO> getGatheingByBoth(String time,String hallID);

	//获取合计数值
	public double getTotalMoney(ArrayList<GatheringReceiptVO> vo);
	//自动生成入款单编号
	public String getCollectionListID();
	//获取未审批的合计收款单(审批单据需要)
	public ArrayList<CollectionReceiptVO> getUnapprovedCollectionReceipt();
	//保存审批后的结果
	public int saveSubmittedCollectionReceiptInfo(CollectionReceiptVO vo);
	//账户金额的增减
	public int excute(CollectionReceiptVO vo);
	
//	//获取营业厅编号
//	public String getHallID();
//	//获取日期
//	public String getDate();
	
	

}
