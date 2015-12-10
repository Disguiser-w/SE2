package dataservice.repertorydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsPO;

public interface GoodsDataService extends Remote {
	
	public int addGoods(GoodsPO goodspo) throws RemoteException;
    public int deleteGoods(String JJD_ID) throws RemoteException;
    public int modifyGoods(GoodsPO goodspo) throws RemoteException;
    public GoodsPO findGoods(String JJD_ID) throws RemoteException;
    public ArrayList<GoodsPO> showAllGoods() throws RemoteException;
    
}
