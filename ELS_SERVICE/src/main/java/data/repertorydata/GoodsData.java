package data.repertorydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.GoodsPO;
import file.JXCFile;
import dataservice.repertorydataservice.GoodsDataService;

public class GoodsData extends UnicastRemoteObject implements GoodsDataService{

	//我也不知道下面这句话有什么用，只是因为继承了UnicastRemoteObject所以要声明这样一个字段
	private static final long serialVersionUID = 131250149L;
		
	JXCFile goodsFile;
	
	public GoodsData() throws RemoteException{
		goodsFile = new JXCFile("src/goods.ser");
	}
	
	public int addGoods(GoodsPO goodspo) throws RemoteException{
    	if(findGoods(goodspo.getOrder_ID())==null){
    		goodsFile.write(goodspo);
    		return 0;
    	}
    	else {
    		System.out.println("已经存在相同ID的货物");
    		return 1;
    		}
    }
    
    public int deleteGoods(String JJD_ID) throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	
		if(objectList==null)	
			return 1;  	  
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.getOrder_ID().equals(JJD_ID)){
				objectList.remove(i);
				break;
			}
		}
		
		//goodsFile.clear();
		goodsFile.writeM(objectList);
		return 0;
    }
    
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
				tempGoodsPO.setLeaveRepertoryID(goodspo.getLatestLeaveTime());
				break;
			}
		}
		
		goodsFile.writeM(objectList);
		return 0;
    }
    
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
    
    public GoodsPO findGoods(String JJD_ID) throws RemoteException{
    	ArrayList<Object> objectList = goodsFile.read();
    	
		if(objectList==null)	
			return null;  	  
		
		for(int i=0; i<objectList.size(); i++){
			GoodsPO tempGoodsPO = (GoodsPO)(objectList.get(i));
			if(tempGoodsPO.getOrder_ID().equals(JJD_ID)){
				return tempGoodsPO;
			}
		}
		
		return null;
    }
    
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
    
    
    /*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*-------------------------------------- Part 1: Test logic whether is right -----------------------------------*/
	
     public static void main(String[] args){
		GoodsData goodsData;
		try{
			goodsData = new GoodsData();
			try{
				goodsData.addGoods(new GoodsPO("20151001-00001", 12, "南京", "上海"));
				goodsData.addGoods(new GoodsPO("20151002-00003", 15, "北京", "上海"));
				goodsData.addGoods(new GoodsPO("20151101-00012", 120, "洛杉矶", "北京"));
				goodsData.addGoods(new GoodsPO("20151024-00007", 250, "南京", "北极"));
				goodsData.addGoods(new GoodsPO("20151011-00001", 15, "南京","广州"));
				goodsData.addGoods(new GoodsPO("20151021-00001", 18, "北京","上海"));
				goodsData.addGoods(new GoodsPO("20151101-00001", 21, "北京","广州"));
				goodsData.addGoods(new GoodsPO("20151111-00001", 24, "南京","广州"));
				
				System.out.println("添加后:");
				ArrayList<GoodsPO> goodspoList0 = goodsData.showAllGoods();
				if(goodspoList0 != null){
	    			for(int i=0;i<goodspoList0.size();i++){
	    				GoodsPO tempGoodspo = goodspoList0.get(i);
	    				System.out.println(tempGoodspo.getOrder_ID()+" "+tempGoodspo.getFee()+" "+tempGoodspo.getDeparturePlace()+" "+tempGoodspo.getDestination()+" "
	    				+tempGoodspo.getLatestEnterTime()+" "+tempGoodspo.getLatestLeaveTime());
	    			}
				}
				
				GoodsPO goodspo = goodsData.findGoods("20151001-00001");
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
					System.out.println("Cannot find the goods");
				
			}catch(RemoteException exception){
				exception.printStackTrace();
			}
		}catch(RemoteException exception){
			exception.printStackTrace();
		}
	}
	
    
}
