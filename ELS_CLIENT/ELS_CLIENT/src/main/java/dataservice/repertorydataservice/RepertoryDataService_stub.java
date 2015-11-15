package dataservice.repertorydataservice;

import dataservice.repertorydataservice.RepertoryDataService;
import po.GoodsPO;
import po.RepertoryPO;

import java.rmi.RemoteException;

public class RepertoryDataService_stub implements RepertoryDataService{
	
	public int modifyRepertory(RepertoryPO repertorypo) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Modify repertory in file succeed!");
        return 0;
    }
 
    public RepertoryPO findRepertory(String repertoryID) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Find repertory in file succeed!");
        return null;
    }
    
    public int addGoods(String repertoryID, GoodsPO goodspo) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Add goods to repertory in file succeed!");
        return 0;
    }
    
    public int deleteGoods(String repertoryID, GoodsPO goodspo) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Delete goods from repertory in file succeed!");
        return 0;
    }
    
    public int modifyGoods(String repertoryID, GoodsPO goodspo) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Modify goods in repertory in file succeed!");
        return 0;
    }
    
    public GoodsPO findGoodsbyID(String repertoryID, String JJD_ID) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Find goods by ID in repertory in file succeed!");
        return null;
    }
    
   public GoodsPO findGoodsbyDate(String repertoryID, String beginDate, String endDate) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Find goods by date in repertory in file succeed!");
        return null;
    }
    
    public GoodsPO findGoodsbyTime(String repertoryID, String time) throws RemoteException {
        // TODO 自动生成的方法存根
        System.out.println("Find goods by time in repertory in file succeed!");
        return null;
    }
    
}
