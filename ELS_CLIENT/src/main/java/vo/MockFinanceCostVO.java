package vo;

public class MockFinanceCostVO extends FinanceCostVO{
	String clause;
	String time;
	//费用金额
	double cost;
	double basicsalary;
	double onessalary;
	//次数
	int times;
	FareVO mfvo;
	
	public MockFinanceCostVO(double money){
		cost=money;
	}
	//租金+营业员月薪
	public MockFinanceCostVO(String cla,double money,String t){
		cost=money;
		clause=cla;
		time=t;
	}
	//运费
	public MockFinanceCostVO(String cla,FareVO vo,String t ){
		clause=cla;
		mfvo=vo;
		cost=vo.getMoney();
		time=t;
		
	}
	//司机月薪------这里的次数都要从其他地方来，，，具体功能时再写
	public MockFinanceCostVO(String cla,int cishu,double yicisalary,String t){
		clause=cla;
		times=cishu;
		onessalary=yicisalary;
		time=t;
		cost=cishu*yicisalary;
	}
	//快递员月薪
	public MockFinanceCostVO(String cla,int cishu,double basic,double ticheng,String t){
		clause=cla;
		times=cishu;
		basicsalary=basic;
		onessalary=ticheng;
		time=t;
		cost=ticheng*cishu+basic;
	}
	
	public double getCost(){
		return cost;
	}
	
	public String getClause(){
		return clause;
	}
	
	public String getTime(){
		return time;
	}
	
	public double getBasicsalary(){
		return basicsalary;
		}
	
	public double getOneSalary(){
		return onessalary;
	}
	
	public int getTimes(){
		return times;
	}
	
	public FareVO getFareVO(){
		return mfvo;
		
	}
}
