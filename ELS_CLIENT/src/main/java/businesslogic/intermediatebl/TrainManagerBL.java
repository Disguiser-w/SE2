package businesslogic.intermediatebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogic.logdiarybl.LogDiaryBL;
import businesslogicservice.intermediateblservice.envehicleblservice.TrainManagerBLService;
import dataservice.intermediatedataservice.IntermediateDataService;
import po.TrainPO;
import type.OperationState;
import vo.IntermediateVO;
import vo.LogDiaryVO;
import vo.OrganizationVO;
import vo.TrainVO;

public class TrainManagerBL implements TrainManagerBLService {
	private IntermediateDataService intermediateData;

	private ArrayList<TrainVO> trainList = new ArrayList<TrainVO>();
	private ArrayList<TrainPO> trainList_temp = new ArrayList<TrainPO>();

	private OrganizationVO intermediateCenter;
	private IntermediateVO intermediate;

	private LogDiaryBL logDiary;

	public TrainManagerBL(ArrayList<TrainVO> trainList,
			OrganizationVO intermediateCentre,
			IntermediateDataService intermediateData,
			IntermediateVO intermediate) {
		// TODO 自动生成的方法存根
		this.trainList = trainList;
		this.intermediateCenter = intermediateCentre;
		this.intermediateData = intermediateData;
		this.intermediate = intermediate;

		logDiary = new LogDiaryBL();
	}

	public ArrayList<TrainVO> showTrainList() {
		// TODO 自动生成的方法存根
		return trainList;
	}

	public OperationState addTrain(String ID, String destination)
			throws RemoteException {
		// TODO 自动生成的方法存根
		TrainVO train_add = new TrainVO(ID, destination);
		trainList.add(train_add);
		saveTrainList();
		logDiary.addLogDiary(new LogDiaryVO(getDate.getdate(), intermediate,
				"在本中转中心火车列表中新增了一架火车"), getDate.getdate());
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState deleteTrain(String s) throws Exception {
		// TODO 自动生成的方法存根
		for (TrainVO train : trainList) {
			if (train.ID.equals(s)) {
				trainList.remove(train);
				saveTrainList();
				logDiary.addLogDiary(new LogDiaryVO(getDate.getdate(),
						intermediate, "在本中转中心火车列表中删除了一架火车"), getDate.getdate());
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
				saveTrainList();
				logDiary.addLogDiary(new LogDiaryVO(getDate.getdate(),
						intermediate, "在本中转中心中修改了一家火车信息"), getDate.getdate());
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
		System.out.println(intermediateCenter.organizationID);
		intermediateData.saveTrainList(intermediateCenter.organizationID,
				trainList_temp);
		return OperationState.SUCCEED_OPERATION;
	}
}
