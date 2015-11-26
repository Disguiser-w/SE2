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
	}
	
	public String getName(){
		return name;
	}
	
	public double getMoney(){
		return money;
	}
	
	public void setName(String s){
		this.name=s;
	}
	
	  public void addMoney(double m){
    	  money+=m;
      }
      
      public void delMoney(double m){
    	  money-=m;
      }


}
