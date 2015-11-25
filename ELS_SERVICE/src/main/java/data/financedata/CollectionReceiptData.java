package data.financedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.GatheringReceiptPO;
import dataservice.financedataservice.CollectionReceiptDataService;

public class CollectionReceiptData implements CollectionReceiptDataService{
	
	//继续意淫..........
	JXCFile file;
	//计次数
	int num;
	public CollectionReceiptData(){
		super();
		file=new JXCFile("collection.ser");
	}

	/**
	 * 创建收款单，即将一个po写入序列化文件
	 * */
	public int createCollection(CollectionReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("collection.ser");
		file.write(po);
		//每创建一个对象num+1
		num++;
		return 0;
	}

	/**
	 * 获取所有的收款单------也就是从文件中读取放到po中
	 * */
	public ArrayList<CollectionReceiptPO> getAllCollection()
			throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("collection.ser");
		ArrayList<Object> collection=file.read();
		if(collection==null){
			System.out.println("读文件collection.ser失败或者文件为空");
			return null;
		}
		else{
			ArrayList<CollectionReceiptPO> buffer=new ArrayList<CollectionReceiptPO>();
			for(Object o:collection){
				CollectionReceiptPO p=(CollectionReceiptPO) o;
				buffer.add(p);
			}
			return buffer;
		}
	}

	/**
	 * 按时间获取所有的营业厅收款单------从文件中放到po中
	 * 才看到钦gg的需求里竟然要按天按营业厅查询，我们愉快地忽略吧233333
	 * 暂取名==
	 * 但是我这里可以直接读营业厅的文件吗，，，并不清楚
	 * */
	public ArrayList<GatheringReceiptPO> getGathering(String Time)
			throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("gathering.ser");
		ArrayList<Object> gathering=file.read();
		if(gathering==null){
			System.out.println("读取文件gathering.ser失败或文件为空");
			return null;
		}
		else{
			ArrayList<GatheringReceiptPO> buffer=new ArrayList<GatheringReceiptPO>();
			for(Object o:gathering){
				GatheringReceiptPO p=(GatheringReceiptPO) o;
				buffer.add(p);
			}
			return buffer;
		}
	}

	/**
	 * 但是这个在BL层好像写过了
	 * */
	public double getTotalMoney(ArrayList<GatheringReceiptPO> po) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

//	public double getTotalMoney(ArrayList<Double> money) throws RemoteException {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	public int getNum() throws RemoteException {
		// TODO Auto-generated method stub
		return num;
	}

	public CollectionReceiptPO findByID(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		
		return null;
	}

	public CollectionReceiptPO modify(CollectionReceiptPO po)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
