package businesslogicservice.manageblservice;

import vo.BasicSalaryVO;
import type.ProfessionType;

public interface BasicSalaryBLService {
	public int addBasicSalary(BasicSalaryVO basicsalaryvo);
	public int deleteBasicSalary(BasicSalaryVO basicsalaryvo);
	public int modifyBasicSalary(BasicSalaryVO basicsalaryvo);
	public double findBasicSalary(ProfessionType profession);
}
