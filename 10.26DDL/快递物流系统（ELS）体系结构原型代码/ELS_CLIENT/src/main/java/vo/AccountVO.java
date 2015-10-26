package vo;

public class AccountVO {
	String name;
	double money;
	
	public AccountVO(){
		this(null,0);
	}

	public AccountVO(String name, double money) {
		// TODO Auto-generated constructor stub
	}
	
	public String getName(){
		return name;
	}
	
	public double getMoney(){
		return money;
	}

}
