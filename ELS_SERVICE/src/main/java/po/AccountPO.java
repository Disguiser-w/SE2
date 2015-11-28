package po;

import java.io.Serializable;

public class AccountPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	double money;
	
	public AccountPO(){
		this(null,0);
	}
	
	public AccountPO(String name,double money){
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
	
    public void addMoney(double m){
  	  money+=m;
    }
    
    public void delMoney(double m){
  	  money-=m;
    }


}
