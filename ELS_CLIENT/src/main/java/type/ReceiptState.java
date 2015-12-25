package type;

import java.io.Serializable;
	public enum ReceiptState implements Serializable{
		//草稿，提交，审批通过， 审批未通过
		DRAFT,SUBMIT,APPROVE,DISAPPROVE;
	}


