package presentation.logdiaryui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.logdiarybl.controller.LogDiaryBLController;
import presentation.commonui.DateChooser;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;
import vo.LogDiaryVO;

public class LogDiaryPanel extends OperationPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217858217226372209L;
	
	private JLabel startDateLabel;
	private JLabel dateOKLabel;

	private JLabel function;

	private MyTextField startDate_Input;

	private MyTable logDiaryPanel;
	
	public ArrayList<LogDiaryVO> logDiaryVOs;
	
	

	public LogDiaryBLController controller;


	public LogDiaryPanel(LogDiaryBLController controller) {
		this.controller=controller;
		
		startDateLabel = new JLabel("日期");
		dateOKLabel = new JLabel("确认");

		function = new JLabel("系统日志");

		startDate_Input = new MyTextField();

		startDateLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				startui();
			}
		});

		dateOKLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				dateOK();
			}
		});

		logDiaryVOs = controller.getAllLogDiaryVOs();
		setBaseInfo();
		setLayout(null);

		add(startDateLabel);
		add(dateOKLabel);
		add(function);
		add(startDate_Input);
		
		startDateLabel.setLayout(new BorderLayout());
		startDateLabel.add(new DateChooser(startDate_Input),BorderLayout.CENTER);
		
	
	}



	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);

		
        startDateLabel.setBounds((int)(width * 8.896683673469388/25),(int)(height * 2.5048923679060664/20),(int)(width *  0.9247448979591837 /25),(int)(height *  1.1741682974559686/20));
        dateOKLabel.setBounds((int)(width * 19.6109693877551/25),(int)(height * 2.5048923679060664/20),(int)(width *  2.1683673469387754 /25),(int)(height *  1.1350293542074363/20));
		function.setBounds((int)(width * 0.5420918367346939/25),(int)(height * 0.43052837573385516/20),(int)(width *  6.919642857142857 /25),(int)(height *  1.6046966731898238/20));
		startDate_Input.setBounds((int)(width * 5.0063775510204085/25),(int)(height * 2.544031311154599/20),(int)(width *  3.1568877551020407 /25),(int)(height *  1.1741682974559686/20));
		logDiaryPanel.setLocationAndSize((int)(width * 1.0987244897959184/25),(int)(height * 4.048727984344423/20),(int)(width *  23.0140306122449 /25),(int)(height *  13.424657534246576/20));		
	}
	private void setBaseInfo(){
		String[] head = new String[]{"日期","操作人","操作"};
		int[] widths = new int[]{100,100,400};
		
		logDiaryPanel = new MyTable(head, getInfos(logDiaryVOs), widths, false);
		add(logDiaryPanel);
	}
	
	private ArrayList<String[]> getInfos(ArrayList<LogDiaryVO> vos){
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		if(vos!=null){
		for(LogDiaryVO v : vos){
			lineInfo.add(new String[]{v.time,v.userVO.userID,v.info});
		}
		return lineInfo;
		}
		else{
			return new ArrayList<String[]>();
		}
	}
	

	public void startui() {
		
	}

	public void dateOK() {
		String beginTime=startDate_Input.getText();
		if(beginTime.equals("")){
			JOptionPane.showMessageDialog(null, "请输入需要查询的时间！", "提示",
					JOptionPane.WARNING_MESSAGE);
		}
		else{
			ArrayList<LogDiaryVO> vos = controller.getLogDiaryVO(beginTime);
			logDiaryPanel.setInfos(getInfos(vos));
		}
	}
	
	public static void main(String[] args){
		LogDiaryBLController controller = new LogDiaryBLController();
		new LogDiaryPanel(controller);
	}


}
