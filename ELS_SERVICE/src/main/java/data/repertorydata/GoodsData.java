package data.repertorydata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.GoodsPO;
import file.JXCFile;
import dataservice.repertorydataservice.GoodsDataService;

public class GoodsData implements GoodsDataService{

	JXCFile goodsFile;
	
	public GoodsData() throws RemoteException{
		goodsFile = new JXCFile("src/main/java/goods.ser");
	}
	
	public int addGoods(GoodsPO goodspo) throws RemoteException{
    	if(findGoods(goodspo.getOrder_ID())==null){
    		goodsFile.write(goodspo);
    		return 0;
    	}
    	else 
    		return 1;
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
				objectList.add(goodspo);
				objectList.remove(i);
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
    
    
    
     /*public static void main(String[] args){
		GoodsData goodsData;
		try{
			goodsData = new GoodsData();
			try{
				goodsData.addGoods(new GoodsPO("20151001-00001", 12, "南京", "上海"));
				goodsData.addGoods(new GoodsPO("20151002-00003", 15, "北京", "上海"));
				goodsData.addGoods(new GoodsPO("20151101-00012", 120, "洛杉矶", "北京"));
				goodsData.addGoods(new GoodsPO("20151024-00007", 250, "南京", "北极"));
				
				System.out.println("添加后:");
				ArrayList<GoodsPO> goodspoList0 = goodsData.showAllGoods();
				if(goodspoList0 != null){
	    			for(int i=0;i<goodspoList0.size();i++){
	    				GoodsPO tempGoodspo = goodspoList0.get(i);
	    				System.out.println(tempGoodspo.getOrder_ID()+" "+tempGoodspo.getFee()+" "+tempGoodspo.getDeparturePlace()+" "+tempGoodspo.getDestination()+" "
	    				+tempGoodspo.getLatestEnterTime()+" "+tempGoodspo.getLatestLeaveTime());
	    			}
				}
				
				GoodsPO goodspo = goodsData.findGoods("20151101-00012");
				if(goodspo != null)
					System.out.println("Find the goods: "+goodspo.getOrder_ID()+" "+goodspo.getFee()+" "+goodspo.getDeparturePlace()+" "+goodspo.getDestination()+" "
						+goodspo.getLatestEnterTime()+" "+goodspo.getLatestLeaveTime());
				else
					System.out.println("Cannot find the goods");
				
				goodsData.modifyGoods(new GoodsPO("20151024-00007", 250, "南京", "南极"));
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
						+tempGoodspo.getLatestEnterTime()+" "+tempGoodspo.getLatestLeaveTime());
	    			}
				}
				else 
					System.out.println("Cannot find the goods");
				
				goodsData.deleteGoods("20151101-00012");
				System.out.println("删除后:");
				ArrayList<GoodsPO> goodspoList2 = goodsData.showAllGoods();
				if(goodspoList2 != null){
	    			for(int i=0;i<goodspoList2.size();i++){
	    				GoodsPO tempGoodspo = goodspoList2.get(i);
	    				System.out.println(tempGoodspo.getOrder_ID()+" "+tempGoodspo.getFee()+" "+tempGoodspo.getDeparturePlace()+" "+tempGoodspo.getDestination()+" "
						+tempGoodspo.getLatestEnterTime()+" "+tempGoodspo.getLatestLeaveTime());
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
	}*/
	
}
