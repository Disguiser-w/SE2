package data.repertorydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.GoodsPO;
import file.JXCFile;
import dataservice.repertorydataservice.GoodsDataService;

public class GoodsData extends UnicastRemoteObject implements GoodsDataService{

	private static final long serialVersionUID = 131250149L;
		
	JXCFile goodsFile;
	
	public GoodsData() throws RemoteException{
		goodsFile = new JXCFile("info/goodInfo/goods.ser");
	}
	
	
	/**
	 * 新增货物（每次AddOrder完成新增一个订单时调该方法）
	 * 
	 * @param GoodsPO goodspo
	 * @return 0(add succeed), 1(goods with the ID has already existed)
	 * 
	 * */
	public int addGoods(GoodsPO goodspo) throws RemoteException{
    	if(findGoodsByID(goodspo.getOrder_ID())==null){
    		goodsFile.write(goodspo);
    		return 0;
    	}
    	else {
    		System.out.println("已经存在相同ID的货物");
    		return 1;
    		}
    }
    
	
	/**
	 * 删除货物
	 * 
	 * @param String orderID
	 * @return 0(delete succeed), 1(delete failed)
	 * 
	 * */
    public int deleteGoods(String orderID) throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.getOrder_ID().equals(orderID)){
				objectList.remove(i);
				break;
			}
		}
		
		//goodsFile.clear();
		goodsFile.writeM(objectList);
		return 0;
    }
    
    
    /**
	 * 修改货物信息
	 * 
	 * @param GoodsPO goodspo
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
    public int modifyGoods(GoodsPO goodspo) throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.getOrder_ID().equals(goodspo.getOrder_ID())){
				tempGoodsPO.setEnterTime(goodspo.getLatestEnterTime());
				tempGoodsPO.setLeaveTime(goodspo.getLatestLeaveTime());
				tempGoodsPO.setEnterRepertoryID(goodspo.getLatestEnterRepertoryID());
				tempGoodsPO.setLeaveRepertoryID(goodspo.getLatestLeaveRepertoryID());
				break;
			}
		}
		
		goodsFile.writeM(objectList);
		return 0;
    }
    
    
    /**
	 * 修改货物入库时间（给该货物的入库时间记录中增加一条记录）
	 * 
	 * @param String goodsID, String enterTime
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
    public int modifyGoodsEnterTime(String goodsID, String enterTime) throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.getOrder_ID().equals(goodsID)){
				tempGoodsPO.setEnterTime(enterTime);
				break;
			}
		}
		
		goodsFile.writeM(objectList);
		return 0;
    }
    
    
    /**
   	 * 修改货物入库的仓库ID（给该货物的入库仓库ID记录中增加一条记录）
   	 * 
   	 * @param String goodsID, String enterRepertoryID
   	 * @return 0(modify succeed), 1(modify failed)
   	 * 
   	 * */
    public int modifyGoodsEnterRepertoryID(String goodsID, String enterRepertoryID) throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.getOrder_ID().equals(goodsID)){
				tempGoodsPO.setEnterRepertoryID(enterRepertoryID);
				break;
			}
		}
		
		goodsFile.writeM(objectList);
		return 0;
    }
    
    
    /**
	 * 修改货物出库时间（给该货物的出库时间记录中增加一条记录）
	 * 
	 * @param String goodsID, String leaveTime
	 * @return 0(modify succeed), 1(modify failed)
	 * 
	 * */
    public int modifyGoodsLeaveTime(String goodsID, String leaveTime) throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.getOrder_ID().equals(goodsID)){
				tempGoodsPO.setLeaveTime(leaveTime);
				break;
			}
		}
		
		goodsFile.writeM(objectList);
		return 0;
    }
    
    
    /**
   	 * 修改货物出库的仓库ID（给该货物的出库仓库ID记录中增加一条记录）
   	 * 
   	 * @param String goodsID, String leaveRepertoryID
   	 * @return 0(modify succeed), 1(modify failed)
   	 * 
   	 * */
    public int modifyGoodsLeaveRepertoryID(String goodsID, String leaveRepertoryID) throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.getOrder_ID().equals(goodsID)){
				tempGoodsPO.setLeaveRepertoryID(leaveRepertoryID);
				break;
			}
		}
		
		goodsFile.writeM(objectList);
		return 0;
    }
    
    
    /**
   	 * 修改货物目前的状态（true表示在仓库中， false是在路上运输）
   	 * 
   	 * @param String goodsID, boolean isInRepertory
   	 * @return 0(modify succeed), 1(modify failed)
   	 * 
   	 * */
    public int modifyGoodsState(String goodsID, boolean isInRepertory) throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.getOrder_ID().equals(goodsID)){
				tempGoodsPO.setInRepertory(isInRepertory);
				break;
			}
		}
		
		goodsFile.writeM(objectList);
		return 0;
    }
    
    
    /**
	 * 根据订单号查找货物（精确搜索）
	 * 
	 * @param String orderID
	 * @return GoodsPO
	 * 
	 * */
    public GoodsPO findGoodsByID(String orderID) throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	
		if(objectList==null)	
			return null;  	  
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.getOrder_ID().equals(orderID)){
				return tempGoodsPO;
			}
		}
		
		return null;
    }
    
    
    /**
	 * 根据关键字查找货物（模糊搜索）
	 * 
	 * @param String keyword
	 * @return ArrayList<GoodsPO>
	 * 
	 * */
    public ArrayList<GoodsPO> findGoodsByKeyword(String keyword) throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	ArrayList<GoodsPO> goodsList = new ArrayList<GoodsPO>();
    	
		if(objectList==null)	
			return null;  	  
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.getOrder_ID().contains(keyword) || tempGoodsPO.getDeparturePlace().contains(keyword) || tempGoodsPO.getDestination().contains(keyword)){
				goodsList.add(tempGoodsPO);
			}
		}
		return goodsList;
    }
    
    
    /**
	 * 显示所有货物信息
	 * 
	 * @return ArrayList<GoodsPO>
	 * 
	 * */
    public ArrayList<GoodsPO> showAllGoods() throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	
		if(objectList==null)
			return null;  	  
		
		ArrayList<GoodsPO> goodsList = new ArrayList<GoodsPO>();
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			goodsList.add(tempGoodsPO);
		}
		
		return goodsList;
    }
    
    
    /**
	 * 显示所有在路上运输的货物信息
	 * 
	 * @return ArrayList<GoodsPO>
	 * 
	 * */
    public ArrayList<GoodsPO> getAllFreeGoods() throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
		ArrayList<GoodsPO> freeGoodsList = new ArrayList<GoodsPO>();
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.isInRepertory() == false)
				freeGoodsList.add(tempGoodsPO);
		}
		
		return freeGoodsList;
	}
    
    
    /**
   	 * 根据关键字查找在路上运输的货物信息（模糊搜索）
   	 * 
   	 * @param String keyword
   	 * @return ArrayList<GoodsPO>
   	 * 
   	 * */
    public ArrayList<GoodsPO> findFreeGoodsByKeyword(String keyword) throws RemoteException{
    	ArrayList<GoodsPO> allFreeGoods = getAllFreeGoods();
		ArrayList<GoodsPO> keyFreeGoodsList = new ArrayList<GoodsPO>();
		
		for(int i=0; i<allFreeGoods.size(); i++){
			GoodsPO tmpGoodsPO = (GoodsPO)(allFreeGoods.get(i));
			if(tmpGoodsPO.getOrder_ID().contains(keyword) || tmpGoodsPO.getDeparturePlace().contains(keyword) || tmpGoodsPO.getDestination().contains(keyword))
				keyFreeGoodsList.add(tmpGoodsPO);
		}
		
		return keyFreeGoodsList;
	}
    
    /*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/
	
     public static void main(String[] args){
		GoodsData goodsData;
		try{
			goodsData = new GoodsData();
			try{
				
				//goodsData.addGoods(new GoodsPO("DD-20151224-1", 11.0, "南京鼓楼", "南京仙林"));
				//goodsData.addGoods(new GoodsPO("DD-20151224-2", 12.0, "南京仙林", "上海静安"));
				//goodsData.addGoods(new GoodsPO("DD-20151224-3", 13.0, "上海浦东", "上海静安"));
				//goodsData.addGoods(new GoodsPO("DD-20151224-4", 14.0, "北京朝阳", "广州白云"));
				//goodsData.addGoods(new GoodsPO("DD-20151224-5", 15.0, "上海静安", "广州白云"));
				//goodsData.addGoods(new GoodsPO("DD-20151224-6", 16.0, "南京仙林", "南京仙林"));
				
				System.out.println("所有在外的货物");
				ArrayList<GoodsPO> freeGoodList = goodsData.getAllFreeGoods();
				if(freeGoodList != null){
					for(int i=0;i<freeGoodList.size();i++){
						GoodsPO tmpGoodspo = freeGoodList.get(i);
						System.out.println(tmpGoodspo.getOrder_ID());
					}
				}
				
				System.out.println("添加后:");
				ArrayList<GoodsPO> goodspoList0 = goodsData.showAllGoods();
				if(goodspoList0 != null){
	    			for(int i=0;i<goodspoList0.size();i++){
	    				GoodsPO tempGoodspo = goodspoList0.get(i);
	    				System.out.println(tempGoodspo.getOrder_ID()+" "+tempGoodspo.getFee()+" "+tempGoodspo.getDeparturePlace()+" "+tempGoodspo.getDestination()+" "
	    				+tempGoodspo.getEnterTime()[0]+" "+tempGoodspo.getEnterTime()[1]+" "+tempGoodspo.getEnterTime()[2]+" "+tempGoodspo.getEnterTime()[3]+" "
	    				+tempGoodspo.getEnterRepertoryID()[0]+" "+tempGoodspo.getEnterRepertoryID()[1]+" "+tempGoodspo.getEnterRepertoryID()[2]+" "+tempGoodspo.getEnterRepertoryID()[3]+" "
	    				+tempGoodspo.getLeaveTime()[0]+" " +tempGoodspo.getLeaveTime()[1]+" "+tempGoodspo.getLeaveTime()[2]+" "+tempGoodspo.getLeaveTime()[3]+ " "
	    				+tempGoodspo.getLeaveRepertoryID()[0]+" " +tempGoodspo.getLeaveRepertoryID()[1]+" "+tempGoodspo.getLeaveRepertoryID()[2]+" "+tempGoodspo.getLeaveRepertoryID()[3]+" "
	    				+tempGoodspo.isInRepertory());
	    			}
				}
				
				/*String time = goodsData.findGoodsByID("DD-20151224-1").getThisRepertoryEnterTime("025-0-CK");
				System.out.println(time);*/
				
				/*GoodsPO goodspo = goodsData.findGoods("20151001-00001");
				if(goodspo != null)
					System.out.println("Find the goods: "+goodspo.getOrder_ID()+" "+goodspo.getFee()+" "+goodspo.getDeparturePlace()+" "+goodspo.getDestination()+" "
						+goodspo.getLatestEnterTime()+" "+goodspo.getLatestLeaveTime());
				else
					System.out.println("Cannot find the goods");
				
				goodsData.modifyGoodsEnterTime("20151024-00007", "2015-12-26 21:59:59");
				System.out.println("修改后:");
				ArrayList<GoodsPO> goodspoList3 = goodsData.showAllGoods();
				if(goodspoList3 != null){
	    			for(int i=0;i<goodspoList3.size();i++){
	    				GoodsPO tempGoodspo = goodspoList3.get(i);
	    				System.out.println(tempGoodspo.getOrder_ID()+" "+tempGoodspo.getFee()+" "+tempGoodspo.getDeparturePlace()+" "+tempGoodspo.getDestination()+" "
						+tempGoodspo.getLatestEnterTime()+" "+tempGoodspo.getLatestLeaveTime());
	    			}
				}
				else 
					System.out.println("Cannot find the goods");
				
				System.out.println("没有删除前:");
				ArrayList<GoodsPO> goodspoList1 = goodsData.showAllGoods();
				if(goodspoList1 != null){
	    			for(int i=0;i<goodspoList1.size();i++){
	    				GoodsPO tempGoodspo = goodspoList1.get(i);
	    				System.out.println(tempGoodspo.getOrder_ID()+" "+tempGoodspo.getFee()+" "+tempGoodspo.getDeparturePlace()+" "+tempGoodspo.getDestination()+" "
	    						+tempGoodspo.getLatestEnterTime()+" "+tempGoodspo.getLatestEnterRepertoryID()+" "
		    					+tempGoodspo.getLatestLeaveTime()+" "+tempGoodspo.getLatestLeaveRepertoryID());
	    			}
				}
				else 
					System.out.println("Cannot find the goods");
				
				//goodsData.deleteGoods("20151101-00012");
				System.out.println("删除后:");
				ArrayList<GoodsPO> goodspoList2 = goodsData.showAllGoods();
				if(goodspoList2 != null){
	    			for(int i=0;i<goodspoList2.size();i++){
	    				GoodsPO tempGoodspo = goodspoList2.get(i);
	    				System.out.println(tempGoodspo.getOrder_ID()+" "+tempGoodspo.getFee()+" "+tempGoodspo.getDeparturePlace()+" "+tempGoodspo.getDestination()+" "
						+tempGoodspo.getLatestEnterTime()+" "+tempGoodspo.getLatestEnterRepertoryID()+" "
	    					+tempGoodspo.getLatestLeaveTime()+" "+tempGoodspo.getLatestLeaveRepertoryID());
	    			}
				}
				else 
					System.out.println("Cannot find the goods");*/
				
			}catch(RemoteException exception){
				exception.printStackTrace();
			}
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
	}
	
    
}
