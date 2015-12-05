package common;

import java.rmi.Naming;

import dataservice.expressdataservice.ExpressDataService;
import po.ExpressPO;

public class TestClient {
	public static void main(String[] args) {

		try {

			// BusinessDataService businessData = new BusinessData();
			// LocateRegistry.createRegistry(7777);
			// Naming.rebind("rmi://172.25.133.95:7777/BusinessDataService",
			// businessData);

			// System.setProperty("java.rmi.server.hostname", "172.25.133.95");
			String[] list = Naming.list("rmi://localhost:8888");
			
			ExpressDataService expressData = (ExpressDataService) Naming
					.lookup(list[0]);
			ExpressPO vo = (ExpressPO)expressData.getExpressInfo(null,"kdy-00001");
			System.out.println(vo.getName());

			
//			AccountDataService accountData =(AccountDataService) Naming.lookup(list[2]);
			System.out.println("successful");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
