package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsPO;

public interface GoodsDataService extends Remote {
	
	public int addGoods(GoodsPO goodspo) throws RemoteException;
    public int deleteGoods(String orderID) throws RemoteException;
    public int modifyGoods(GoodsPO goodspo) throws RemoteException;
    public int modifyGoodsEnterTime(String goodsID, String enterTime) throws RemoteException;
    public int modifyGoodsEnterRepertoryID(String goodsID, String enterRepertoryID) throws RemoteException;
    public int modifyGoodsLeaveTime(String goodsID, String leaveTime) throws RemoteException;
    public int modifyGoodsLeaveRepertoryID(String goodsID, String leaveRepertoryID) throws RemoteException;
    public int modifyGoodsState(String goodsID, boolean isInRepertory) throws RemoteException;
    public GoodsPO findGoodsByID(String orderID) throws RemoteException;
    public ArrayList<GoodsPO> findGoodsByKeyword(String keyword) throws RemoteException;
    public ArrayList<GoodsPO> showAllGoods() throws RemoteException;
    public ArrayList<GoodsPO> getAllFreeGoods() throws RemoteException;
    public ArrayList<GoodsPO> findFreeGoodsByKeyword(String keyword) throws RemoteException;
    
}
