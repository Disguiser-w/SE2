package businesslogicservice.magageblservice;

import vo.CostVO;
import type.ExpressType;

public interface CostBLService {
	public int addCost(CostVO costvo);
	public int deleteCost(CostVO costvo);
	public int modifyCost(CostVO costvo);
	public double findCost(ExpressType category);
}
