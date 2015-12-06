package vo;

public class AccountVO {
	String name;
	double money;

	public AccountVO(String name, double money) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.money=money;
	}
	
	public String getName(){
		return name;
	}
	
	public double getMoney(){
		return money;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setMoney(double money){
		this.money=money;
	}
}
