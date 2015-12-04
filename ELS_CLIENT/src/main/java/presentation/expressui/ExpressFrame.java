package presentation.expressui;

import presentation.commonui.UserFrame;
import vo.ExpressVO;

public class ExpressFrame extends UserFrame {

	public ExpressFrame(ExpressVO vo) {
		super();
		// addFuncLabel(new QueryPanel());
		// addFuncLabel(new ChargeMessageCollectionPanel());
		// addFuncLabel(new AddOrderPanel());
		// addFuncLabel(new FinishedOrderPanel());
		// 设置用户名和ID

		setMessage(vo.name, vo.ID);

	}

	// public static void initGlobalFontSetting(Font fnt){
	// FontUIResource fontRes = new FontUIResource(fnt);
	// for(Enumeration keys = UIManager.getDefaults().keys();
	// keys.hasMoreElements();)
	//
	//
	//
	// if(value instanceof FontUIResource)
	// UIManager.put(key,fontRes);
	// }
	// }
	

	// test
}
