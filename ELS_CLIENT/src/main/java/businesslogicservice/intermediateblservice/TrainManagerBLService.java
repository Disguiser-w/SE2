package businesslogicservice.intermediateblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import type.OperationState;
import vo.TrainVO;

public interface TrainManagerBLService {
	public ArrayList<TrainVO> showTrainList();

	public OperationState addTrain(String ID, String destination) throws RemoteException;

	public OperationState deleteTrain(String train_ID) throws Exception;

	public OperationState modifyTrain(TrainVO train_modify) throws Exception;

	public TrainVO showTrain(String train_ID) throws Exception;

	public OperationState saveTrainList() throws RemoteException;
}
