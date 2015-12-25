package type;

import java.io.Serializable;

	//单据类型的枚举类
		public enum ReceiptType implements Serializable{
			//快递员单据
			//营业厅单据
			//库存单据
			//财务单据
			COLLECTIONRECEIPT,PAYMENTRECEIPT,COSTINCOMERECEPTION, ENTERREPERTORYRECEIPT, LEAVEREPERTORYRECEIPT;
		}


