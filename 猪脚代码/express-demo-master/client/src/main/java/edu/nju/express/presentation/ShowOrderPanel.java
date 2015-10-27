package edu.nju.express.presentation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.nju.express.businesslogic.OrderBL;
import edu.nju.express.init.RMIHelper;
import edu.nju.express.vo.OrderReadVO;

public class ShowOrderPanel extends JPanel {
    private static final String[] TABLE_HEADER = new String[]{
            "Order ID", "Sender", "Receiver", "Commodity", "Type", "Pakcaging Fee", "Price"};

    private DefaultTableModel defaultTableModel;

    private OrderBL orderBL;

    public ShowOrderPanel() {
        orderBL = RMIHelper.getOrderBL();

        Object[][] orders = getOrders();
        defaultTableModel = new DefaultTableModel(orders, TABLE_HEADER);
        JTable orderListTable = new JTable(defaultTableModel);
        JButton addButton = new JButton("add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddOrderDialog(ShowOrderPanel.this).setVisible(true);
            }
        });

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(orderListTable), BorderLayout.CENTER);
        this.add(addButton, BorderLayout.SOUTH);
    }

    public void refresh() {
        Object[][] orders = getOrders();
        defaultTableModel.setDataVector(orders, TABLE_HEADER);
    }

    private Object[][] getOrders() {
        List<OrderReadVO> expressOrders = orderBL.getExpressOrders();

        Object[][] orders = new Object[expressOrders.size()][];
        for (int i = 0; i < expressOrders.size(); i++) {
            Object[] order = changeToAnObject(expressOrders.get(i));
            orders[i] = order;
        }
        return orders;
    }

    private Object[] changeToAnObject(OrderReadVO orderReadVO) {
        Object[] order = new Object[TABLE_HEADER.length];
        order[0] = orderReadVO.barcode;
        order[1] = orderReadVO.senderName;
        order[2] = orderReadVO.receiverName;
        order[3] = orderReadVO.commodityName;
        order[4] = orderReadVO.type;
        order[5] = orderReadVO.packagingFee;
        order[6] = orderReadVO.totalPrice;
        return order;
    }
}
