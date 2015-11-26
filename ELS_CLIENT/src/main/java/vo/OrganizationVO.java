package vo;

import type.OrganizationType;
import vo.RepertoryVO;

public class OrganizationVO {
	public final OrganizationType category;
	public final String organizationID;
	public final String name;
	public final RepertoryVO repertory;

	public OrganizationVO(OrganizationType category, String organizationID, String name, RepertoryVO repertory) {
		// 类别 编号 名称
		// 025000 鼓楼营业厅
		// 025-0 南京中转中心
		this.category = category;
		this.organizationID = organizationID;
		this.name = name;

		// 如果新建机构是中转中心的话，同时新建一个对应的中转中心仓库
		if (category.equals(OrganizationType.intermediateCenter)) {
			this.repertory = new RepertoryVO(organizationID + "-CK", "");
		} else {
			this.repertory = null;
		}

	}
}