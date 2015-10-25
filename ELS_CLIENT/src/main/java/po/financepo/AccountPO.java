package po.financepo;

public class AccountPO {
	String name;
	double money;
	
	public AccountPO(){
		this(null,0);
	}
	
	public AccountPO(String name,double money){
	}
	
	public String getName(){
		return name;
	}
	
	public double getMoney(){
		return money;
	}

}
