package presentation.managerui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;

public class BasicDataManagerPanel extends JPanel {

	// 每次工资
	private JLabel eventTimePayLabel;
	// 基础月薪
	private JLabel baseSalaryLabel;
	// 城市间距离
	private JLabel distancesLabel;
	// 运费系数
	private JLabel baseFreightLabel;

	private JTable eventTimePay;
	private JTable baseSalary;
	private JTable distances;
	private JTable baseFreight;

	// 当前table
	// private JTable currentTable;

	// 添加
	private JLabel addLabel;

	// 删除
	private JLabel delLabel;

	// 修改
	private JLabel modifyLabel;

	// 查询信息输入
	private JTextField queryField;

	// 查询按钮
	private JButton queryButton;

	// 上一面
	private JLabel previousPageLabel;

	// 下一面
	private JLabel nextPageLabel;

	// 位置设置
	// private LocationHelper helper;

	public BasicDataManagerPanel() {
		eventTimePayLabel = new JLabel("每次工资");
		baseSalaryLabel = new JLabel("基础月薪");
		distancesLabel = new JLabel("城市间距离");
		baseFreightLabel = new JLabel("运费系数");

		eventTimePay = new JTable();
		baseSalary = new JTable();
		distances = new JTable();
		baseFreight = new JTable();

		addLabel = new JLabel();
		delLabel = new JLabel();
		modifyLabel = new JLabel();

		queryField = new JTextField();
		queryButton = new JButton();
		previousPageLabel = new JLabel();
		nextPageLabel = new JLabel();

		add(eventTimePayLabel);
		add(baseSalaryLabel);
		add(distancesLabel);
		add(baseFreightLabel);
		add(eventTimePay);
		// add(baseSalary);
		// add(distances);
		// add(baseFreight);
		add(addLabel);
		add(delLabel);
		add(modifyLabel);
		add(queryField);
		add(queryButton);
		add(previousPageLabel);
		add(nextPageLabel);

		// currentTable = eventTimePay;
		setLayout(null);
		// helper = new LocationHelper(this);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		//
		eventTimePayLabel.setBounds((int) (width * 1.9526248399487836 / 25), (int) (height * 1.5625 / 20),
				(int) (width * 3.1690140845070425 / 25), (int) (height * 1.4285714285714286 / 20));
		baseSalaryLabel.setBounds((int) (width * 7.458386683738796 / 25), (int) (height * 1.5625 / 20),
				(int) (width * 3.1690140845070425 / 25), (int) (height * 1.4285714285714286 / 20));
		distancesLabel.setBounds((int) (width * 12.900128040973112 / 25), (int) (height * 1.5625 / 20),
				(int) (width * 3.1370038412291934 / 25), (int) (height * 1.4285714285714286 / 20));
		baseFreightLabel.setBounds((int) (width * 18.405889884763123 / 25), (int) (height * 1.5625 / 20),
				(int) (width * 3.1370038412291934 / 25), (int) (height * 1.4285714285714286 / 20));
		eventTimePay.setBounds((int) (width * 2.0166453265044813 / 25), (int) (height * 7.321428571428571 / 20),
				(int) (width * 21.862996158770805 / 25), (int) (height * 9.821428571428571 / 20));

		baseSalary.setBounds((int) (width * 2.0166453265044813 / 25), (int) (height * 7.321428571428571 / 20),
				(int) (width * 21.862996158770805 / 25), (int) (height * 9.821428571428571 / 20));
		distances.setBounds((int) (width * 2.0166453265044813 / 25), (int) (height * 7.321428571428571 / 20),
				(int) (width * 21.862996158770805 / 25), (int) (height * 9.821428571428571 / 20));
		baseFreight.setBounds((int) (width * 2.0166453265044813 / 25), (int) (height * 7.321428571428571 / 20),
				(int) (width * 21.862996158770805 / 25), (int) (height * 9.821428571428571 / 20));

		addLabel.setBounds((int) (width * 4.12932138284251 / 25), (int) (height * 4.419642857142857 / 20),
				(int) (width * 1.5364916773367479 / 25), (int) (height * 2.142857142857143 / 20));
		delLabel.setBounds((int) (width * 8.482714468629961 / 25), (int) (height * 4.419642857142857 / 20),
				(int) (width * 1.5364916773367479 / 25), (int) (height * 2.142857142857143 / 20));
		modifyLabel.setBounds((int) (width * 12.836107554417413 / 25), (int) (height * 4.419642857142857 / 20),
				(int) (width * 1.5364916773367479 / 25), (int) (height * 2.142857142857143 / 20));
		queryField.setBounds((int) (width * 16.005121638924457 / 25), (int) (height * 4.821428571428571 / 20),
				(int) (width * 4.3854033290653005 / 25), (int) (height * 1.3392857142857142 / 20));
		queryButton.setBounds((int) (width * 21.286811779769526 / 25), (int) (height * 4.821428571428571 / 20),
				(int) (width * 1.6005121638924455 / 25), (int) (height * 1.2946428571428572 / 20));
		previousPageLabel.setBounds((int) (width * 17.6696542893726 / 25), (int) (height * 17.857142857142858 / 20),
				(int) (width * 1.120358514724712 / 25), (int) (height * 1.5178571428571428 / 20));
		nextPageLabel.setBounds((int) (width * 20.774647887323944 / 25), (int) (height * 17.857142857142858 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.5178571428571428 / 20));

	}

}
