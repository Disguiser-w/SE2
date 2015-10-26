package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.GoodsPO;
import po.RepertoryPO;

public interface RepertoryDataService extends Remote{
	
	public int modifyRepertory(RepertoryPO repertorypo) throws RemoteException;  //对仓库信息做修改，比如库存信息初始化工作
	public RepertoryPO findRepertory(String repertoryID) throws RemoteException;
	public int addGoods(String repertoryID, GoodsPO goodspo) throws RemoteException;
	public int deleteGoods(String repertoryID, GoodsPO goodspo) throws RemoteException;
	public int modifyGoods(String repertoryID, GoodsPO goodspo) throws RemoteException;
	public GoodsPO findGoodsbyID(String repertoryID, String JJD_ID) throws RemoteException;
	public GoodsPO findGoodsbyDate(String repertoryID, String beginDate, String endDate) throws RemoteException;
	public GoodsPO findGoodsbyTime(String repertoryID, String time) throws RemoteException;
	
}
