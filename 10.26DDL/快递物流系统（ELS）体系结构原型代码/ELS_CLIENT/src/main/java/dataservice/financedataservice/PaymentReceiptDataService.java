package dataservice.financedataservice;

import java.util.ArrayList;

import po.PaymentReceiptPO;

public interface PaymentReceiptDataService {
		public int creatPaymentReceipt(PaymentReceiptPO vo);
		public ArrayList<PaymentReceiptPO> getAllPaymentReceipt();
		
		//当天存储的持久化对象个数
		public int getNum();
		//根据ID查找持久化对象
		public PaymentReceiptPO findByID(String ID);
		//修改持久化对象
		public  PaymentReceiptPO modify(PaymentReceiptPO po);
		

}
