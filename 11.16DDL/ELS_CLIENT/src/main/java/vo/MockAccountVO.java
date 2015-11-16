package vo;

public class MockAccountVO extends AccountVO{
	double money;
	String name;
	
	public MockAccountVO(String n,double m){
		name=n;
		money=m;
	}
	
	public String getName(){
		return name;
	}
	
	public double getMoney(){
		return money;
	}

}
