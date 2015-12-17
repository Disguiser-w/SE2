package businesslogic.intermediatebl.driver;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.TransferingBL;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import dataservice.intermediatedataservice.IntermediateDataService;
import type.ExpressType;
import type.OrderState;
import type.OrganizationType;
import type.PackType;
import type.ReceiptState;
import vo.OrderVO;
import vo.OrganizationVO;
import vo.TransferingReceiptVO;

public class Transfering_driver {
	static IntermediateDataService intermediateData;

	public static void main(String[] args) {
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();

		OrderVO order1 = new OrderVO("02500100001", null, null, null, null,
				null, null, null, null, null, null, 0, null, null, null,
				ExpressType.ECONOMIC, PackType.CARTONS, 0, 0, null, null, null,
				null, OrderState.TRANSFERING, null);
		OrderVO order2 = new OrderVO("02500100002", null, null, null, null,
				null, null, null, null, null, null, 0, null, null, null,
				ExpressType.FAST, PackType.CARTONS, 0, 0, null, null, null,
				null, OrderState.TRANSFERING, null);
		OrderVO order3 = new OrderVO("02500100003", null, null, null, null,
				null, null, null, null, null, null, 0, null, null, null,
				ExpressType.ECONOMIC, PackType.CARTONS, 0, 0, null, null, null,
				null, OrderState.TRANSFERING, null);
		OrderVO order4 = new OrderVO("02500100004", null, null, null, null,
				null, null, null, null, null, null, 0, null, null, null,
				ExpressType.ECONOMIC, PackType.CARTONS, 0, 0, null, null, null,
				null, OrderState.TRANSFERING, null);

		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		orderList.add(order4);

		TransferingReceiptVO transferingReceipt = new TransferingReceiptVO(
				new OrganizationVO(OrganizationType.intermediateCenter,
						"025001", "南京中转中心"), orderList, "025001-2015-12-18",
				null, ReceiptState.DRAFT);

		try {
			intermediateData = DataFactory.getIntermediateData();
		} catch (MalformedURLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

		TransferingBL tbl = new TransferingBL(transferingReceipt,
				intermediateData);
		System.out.println("中转中心到达单ID： " + tbl.showTransferingReceipt().ID);
		System.out.println("当前到达单中订单个数： "
				+ tbl.showTransferingReceipt().orderList.size());

		try {
			tbl.deleteOrder("02500100003");
			System.out.println("删除一个订单后：");
			System.out.println("当前到达单中订单个数： "
					+ tbl.showTransferingReceipt().orderList.size());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		try {
			System.out.println("修改前： "
					+ transferingReceipt.orderList.get(1).expressType
							.toString());
			tbl.modifyOrder(new OrderVO("02500100002", null, null, null, null,
					null, null, null, null, null, null, 0, null, null, null,
					ExpressType.STANDARD, PackType.CARTONS, 0, 0, null, null,
					null, null, OrderState.TRANSFERING, null));
			System.out.println("修改一个ID为00002的订单后：");
			System.out.println("修改后： "
					+ transferingReceipt.orderList.get(1).expressType
							.toString());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		try {
			System.out.println("保存中……");
			tbl.saveTransferingReceipt();
			System.out
					.println("读取保存的到达单，读取的到达单中订单的个数： "
							+ IntermediateMainController
									.poToVO(intermediateData
											.getTransferingReceiptInfo(
													"025001", "2015-12-18")).orderList
									.size());
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
