package vo;

public class MockFareVO extends FareVO{
	String time;
	double money;
	
	public MockFareVO(String t,double m){
		time=t;
		money=m;
	}
	
	public String getTime(){
		return time;
	}
	
	public double getMoney(){
		return money;
	}
}
