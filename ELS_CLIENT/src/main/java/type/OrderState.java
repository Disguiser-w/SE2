package type;

public enum OrderState {
	//刚刚添加时的状态
	WAITING_ENVEHICLE,
	//被营业厅接收后的状态
	WAITING_DISTRIBUTE,
	//正在派送
	DiSTRIBUEING,
	//完成时的状态
	FINISHED
}
