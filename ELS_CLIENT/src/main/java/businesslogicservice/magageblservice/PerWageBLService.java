package businesslogicservice.magageblservice;

import vo.PerWageVO;
import type.ProfessionType;

public interface PerWageBLService {
	public int addPerWage(PerWageVO perwagevo);
	public int deletePerWage(PerWageVO perwagevo);
	public int modifyPerWage(PerWageVO perwagevo);
	public double findPerWage(ProfessionType profession);
}
