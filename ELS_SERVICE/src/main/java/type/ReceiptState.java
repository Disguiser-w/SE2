package type;

import java.io.Serializable;
	public enum ReceiptState implements Serializable{
		//草稿，提交，审批后
		DRAFT,SUBMIT,APPROVE,DISAPPROVE;
	}
