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
	
	//获取收款单
	public ArrayList<GatheringReceiptVO> getGathering(String HallID,String Time);
	//获取金额
	public double[]  getMoney(GatheringReceiptVO vo);
	//获取合计数值
	public double getTotalMoney(int[] money);
	//自动生成入款单编号
	public String getCollectionListID();
	
	
	
//	//获取营业厅编号
//	public String getHallID();
//	//获取日期
//	public String getDate();
	
	

}
