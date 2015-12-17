package businesslogic.intermediatebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.intermediatedataservice.IntermediateDataService;
import po.TrainPO;
import type.OperationState;
import vo.OrganizationVO;
import vo.TrainVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogicservice.intermediateblservice.envehicleblservice.TrainManagerBLService;

public class TrainManagerBL implements TrainManagerBLService {
	private IntermediateDataService intermediateData;

	private ArrayList<TrainVO> trainList = new ArrayList<TrainVO>();
	private ArrayList<TrainPO> trainList_temp = new ArrayList<TrainPO>();

	private OrganizationVO intermediateCentre;

	public TrainManagerBL(ArrayList<TrainVO> trainList,
			OrganizationVO intermediateCentreIntermediate,
			IntermediateDataService intermediateData) {
		this.trainList = trainList;
		this.intermediateCentre = intermediateCentreIntermediate;
		this.intermediateData = intermediateData;
	}

	public ArrayList<TrainVO> showTrainList() {
		// TODO 自动生成的方法存根
		return trainList;
	}

	public OperationState addTrain(String ID, String destination) {
		// TODO 自动生成的方法存根
		TrainVO train_add = new TrainVO(ID, destination);
		trainList.add(train_add);
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState deleteTrain(TrainVO train_delete) throws Exception {
		// TODO 自动生成的方法存根
		for (TrainVO train : trainList) {
			if (train.ID.equals(train_delete.ID)) {
				trainList.remove(train);
				return OperationState.SUCCEED_OPERATION;
			}
		}
		throw new Exception("未找到该ID的火车！");
	}

	public OperationState modifyTrain(TrainVO train_modify) throws Exception {
		// TODO 自动生成的方法存根
		for (TrainVO train : trainList) {
			if (train.ID.equals(train_modify.ID)) {
				trainList.set(trainList.indexOf(train), train_modify);
				return OperationState.SUCCEED_OPERATION;
			}
		}
		throw new Exception("未找到该ID的火车！");
	}

	public TrainVO showTrain(String train_ID) throws Exception {
		// TODO 自动生成的方法存根
		for (TrainVO train : trainList) {
			if (train.ID.equals(train_ID))
				return train;
		}
		throw new Exception("未找到该ID的火车！");
	}

	public OperationState saveTrainList() throws RemoteException {
		// TODO 自动生成的方法存根
		for (TrainVO train : trainList)
			trainList_temp.add(IntermediateMainController.voToPO(train));
		intermediateData.saveTrainList(intermediateCentre.organizationID,
				trainList_temp);
		return OperationState.SUCCEED_OPERATION;
	}
}
