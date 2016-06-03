/*
 * 此代码创建于 2009-9-16 下午02:36:26。
 */
package com.apollo.swing.component.treetable.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import com.apollo.base.util.BaseUtilities;
import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.common.jtree.SingleIconTreeCellRenderer;
import com.apollo.swing.component.complextree.ComplexTree;
import com.apollo.swing.component.complextree.ComplexTreeCacheNode;
import com.apollo.swing.component.complextree.ComplexTreeChoiceNode;
import com.apollo.swing.component.complextree.ComplexTreeException;
import com.apollo.swing.component.complextree.ComplexTreeModel;
import com.apollo.swing.component.complextree.ComplexTreeModelEvent;
import com.apollo.swing.component.complextree.ComplexTreeNode;
import com.apollo.swing.component.complextree.IComplexTreeDataSource;
import com.apollo.swing.component.complextree.IComplexTreeModelListener;
import com.apollo.swing.component.editor.FormattedFieldCellEditor;
import com.apollo.swing.component.text.IntegerField;
import com.apollo.swing.component.treetable.TreeTable;
import com.apollo.swing.component.treetable.TreeTableCell;
import com.apollo.swing.component.treetable.TreeTableModel;
import com.apollo.swing.component.treetable.TreeTableObject;
import com.apollo.swing.laf.TLookAndFeelManager;

/**
 * <p>文件名称：TreeTableDemo.java</p>
 * <p>类型描述：树表演示类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2009-9-19</p>
 * <p>修改记录：</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * @version 1.0
 * @author 李镇
 */
@SuppressWarnings("all")
public class TreeTableDemo extends JPanel
    implements
        IBaseConstants,
        IComplexTreeDataSource,
        ListSelectionListener,
        TableModelListener,
        TableColumnModelListener,
        TreeSelectionListener,
        TreeWillExpandListener,
        TreeExpansionListener,
        IComplexTreeModelListener,
        TreeModelListener {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /* Add Public (Static) Inner Class */

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    /* Add Protected (Static) Inner Class */

    /*------------------------------------- Friendly (Static) Inner Class -------------------------------------*/

    /* Add Friendly (Static) Inner Class */

    /*------------------------------------- Private (Static) Inner Class -------------------------------------*/

    /* Add Private (Static) Inner Class */

    /*------------------------------------- Public Static Field -------------------------------------*/

    /* Add Public Static Field */

    /*------------------------------------- Protected Static Field -------------------------------------*/

    /* Add Protected Static Field */

    /*------------------------------------- Friendly Static Field -------------------------------------*/

    /* Add Friendly Static Field */

    /*------------------------------------- Private Static Field -------------------------------------*/

    /**
     * 渲染器------全网资源。
     */
    private static final TreeCellRenderer RENDERER_ROOT = new SingleIconTreeCellRenderer(BaseUtilities.getIcon(TreeTableDemo.class,
                                                                                                               "com/apollo/swing/component/treetable/demo/",
                                                                                                               "root.gif"));

    /**
     * 渲染器------EMS。
     */
    private static final TreeCellRenderer RENDERER_EMS = new SingleIconTreeCellRenderer(BaseUtilities.getIcon(TreeTableDemo.class,
                                                                                                              "com/apollo/swing/component/treetable/demo/",
                                                                                                              "ems.png"));

    /**
     * 渲染器------网元。
     */
    private static final TreeCellRenderer RENDERER_NE = new SingleIconTreeCellRenderer(BaseUtilities.getIcon(TreeTableDemo.class,
                                                                                                             "com/apollo/swing/component/treetable/demo/",
                                                                                                             "ne.png"));

    /**
     * 渲染器------单板。
     */
    private static final TreeCellRenderer RENDERER_BOARD = new SingleIconTreeCellRenderer(BaseUtilities.getIcon(TreeTableDemo.class,
                                                                                                                "com/apollo/swing/component/treetable/demo/",
                                                                                                                "board.png"));

    /**
     * 渲染器------端口。
     */
    private static final TreeCellRenderer RENDERER_PTP = new SingleIconTreeCellRenderer(BaseUtilities.getIcon(TreeTableDemo.class,
                                                                                                              "com/apollo/swing/component/treetable/demo/",
                                                                                                              "ptp.png"));

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Public Static Method -------------------------------------*/

    /**
     * 主方法。
     * @param args 入口参数集。
     */
    public static void main(String[] args) {
        TLookAndFeelManager.loadAlloy(false);

        JFrame f = new JFrame("树表演示");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(new TreeTableDemo());
        f.pack();
        f.setVisible(true);
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /* Add Protected Static Method */

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /**
     * 进度条渲染器。
     */
    protected TableCellRenderer m_pbRenderer = new TableCellRenderer() {

        private final Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

        /**
         * 进度条。
         */
        protected JProgressBar m_pb = new JProgressBar();

        {
            m_pb.setStringPainted(true);
            m_pb.setForeground(Color.GREEN);
            //m_pb.setOpaque(false);
        }

        /**
         * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
         */
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                m_pb.setBackground(table.getSelectionBackground());

            } else {
                m_pb.setBackground(Color_Bg_Editable_False);
            }

            if (hasFocus) {
                m_pb.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));

            } else {
                m_pb.setBorder(noFocusBorder);
            }

            m_pb.setValue(((BigInteger) value).intValue());

            return m_pb;
        }

    };

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /**
     * 已选中的路径集。
     */
    private TreePath[] m_chosenPaths = null;

    /**
     * 树表模型。
     */
    private TreeTableModel m_model;

    /**
     * 树表。
     */
    private TreeTable m_table;

    /**
     * 消息区域。
     */
    private JTextArea m_msg = new JTextArea() {

        {
            setEditable(false);
        }

        /**
         * @see javax.swing.JTextArea#append(java.lang.String)
         */
        public void append(String str) {
            str = DateFormat.getDateTimeInstance().format(new Date()) + " " + str;
            super.append(str);
        }

    };

    /**
     * 动作------重置树表。
     */
    private Action m_resetTable = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "重置树表");
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_model.getTree().getComplexTreeModel().setRoot(createRoot());
        }

    };

    /**
     * 动作------添加节点。
     */
    private Action m_addNode = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "添加节点");
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            ComplexTreeChoiceNode temp = new ComplexTreeChoiceNode(new TreeTableObject("添加的节点", createCells()));

            int row = m_table.getSelectedRow();
            if (row == -1) {
                row = 0;
            }

            ComplexTreeNode node = m_model.getNode(row);
            node.insert(temp, 0);
            m_model.getTree().getComplexTreeModel().reload(node);
        }

    };

    /**
     * 动作------获取已选中路径。
     */
    private Action m_getChosenPaths = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "获取已选中路径");
            setEnabled(false);
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_msg.append("---------------------------------已选中路径（开始）---------------------------------\n");
            m_chosenPaths = m_model.getTree().getComplexTreeModel().getChosenPaths();
            for (int i = 0; i < m_chosenPaths.length; i++) {
                m_msg.append(m_chosenPaths[i].toString() + "\n");
            }
            m_msg.append("---------------------------------已选中路径（结束）---------------------------------\n");
        }

    };

    /**
     * 动作------设置已选中路径。
     */
    private Action m_setChosenPaths = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "设置已选中路径");
            setEnabled(false);
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            ComplexTree tree = m_model.getTree();
            tree.getComplexTreeModel().setChosenPaths(m_chosenPaths);
            m_table.repaint();
            //tree.expand();
        }

    };

    /**
     * 动作------清除消息。
     */
    private Action m_clearMsg = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "清空消息框");
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_msg.setText("");
        }

    };

    /**
     * 复选框------允许选中。
     */
    private JCheckBox m_enabledChoice = new JCheckBox(new AbstractAction() {

        {
            BaseUtilities.setAction(this, "允许选中");
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            boolean enabledChoice = m_enabledChoice.isSelected();
            m_getChosenPaths.setEnabled(enabledChoice);
            m_setChosenPaths.setEnabled(enabledChoice);
            m_model.getTree().setEnabledChoice(enabledChoice);
            m_table.repaint();
        }

    });

    /**
     * 复选框------允许ListSelectionListener。
     */
    private JCheckBox m_enabledListSelectionListener = new JCheckBox("ListSelectionListener");

    /**
     * 复选框------允许TableModelListener。
     */
    private JCheckBox m_enabledTableModelListener = new JCheckBox("TableModelListener");

    /**
     * 复选框------允许TableColumnModelListener。
     */
    private JCheckBox m_enabledTableColumnModelListener = new JCheckBox("TableColumnModelListener");

    /**
     * 复选框------允许TreeSelectionListener。
     */
    private JCheckBox m_enabledTreeSelectionListener = new JCheckBox("TreeSelectionListener");

    /**
     * 复选框------允许TreeWillExpandListener。
     */
    private JCheckBox m_enabledTreeWillExpandListener = new JCheckBox("TreeWillExpandListener");

    /**
     * 复选框------允许TreeExpansionListener。
     */
    private JCheckBox m_enabledTreeExpansionListener = new JCheckBox("TreeExpansionListener");

    /**
     * 复选框------允许IComplexTreeModelListener。
     */
    private JCheckBox m_enabledComplexTreeModelListener = new JCheckBox("IComplexTreeModelListener");

    /**
     * 复选框------允许TreeModelListener。
     */
    private JCheckBox m_enabledTreeModelListener = new JCheckBox("TreeModelListener");

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     */
    public TreeTableDemo() {
        super(new GridBagLayout());

        m_model = new TreeTableModel(new ComplexTreeModel(createRoot(), this, 5, false), new String[] { "资源",
                                                                                                       "生产时间",
                                                                                                       "设备制造商",
                                                                                                       "是否启用",
                                                                                                       "使用年限",
                                                                                                       "备注" });
        m_model.setColumnClass(3, Boolean.class);
        m_model.addTableModelListener(this);

        ComplexTree tree = m_model.getTree();
        tree.addTreeSelectionListener(this);
        tree.addTreeWillExpandListener(this);
        tree.addTreeExpansionListener(this);
        //tree.setRootVisible(false);
        m_model.refresh();

        ComplexTreeModel model = tree.getComplexTreeModel();
        model.setCacheSize(100);
        model.addComplexTreeModelListener(this);
        model.addTreeModelListener(this);

        m_table = new TreeTable(m_model);
        m_table.getTableHeader().setReorderingAllowed(false);
        //m_table.setReadMode(true);
        m_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        m_table.getColumnModel().addColumnModelListener(this);
        m_table.getSelectionModel().addListSelectionListener(this);
        m_table.getColumn("资源").setPreferredWidth(180);
        m_table.getColumn("生产时间").setPreferredWidth(120);
        m_table.getColumn("设备制造商").setPreferredWidth(70);
        m_table.getColumn("是否启用").setPreferredWidth(60);
        m_table.getColumn("使用年限").setPreferredWidth(60);
        m_table.getColumn("备注").setPreferredWidth(200);

        //m_table.setSelectionBackground(Color.BLUE);

        JScrollPane tableView = new JScrollPane(m_table);
        tableView.setBorder(BorderFactory.createTitledBorder("树表"));

        JScrollPane msgView = new JScrollPane(m_msg);
        msgView.setBorder(BorderFactory.createTitledBorder("消息区域"));

        JSplitPane tableAndMsgView = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableView, msgView);
        tableAndMsgView.setDividerLocation(300);
        tableAndMsgView.setPreferredSize(new Dimension(700, 500));

        JPanel opView = new JPanel(new GridBagLayout());
        opView.setBorder(BorderFactory.createTitledBorder("操作"));
        opView.add(new JButton(m_resetTable), new GridBagConstraints(0,
                                                                     0,
                                                                     1,
                                                                     1,
                                                                     0.0,
                                                                     0.0,
                                                                     GridBagConstraints.NORTHWEST,
                                                                     GridBagConstraints.HORIZONTAL,
                                                                     INSETS_DEFAULT,
                                                                     IPADX_DEFAULT,
                                                                     IPADY_DEFAULT));
        opView.add(new JButton(m_addNode), new GridBagConstraints(0,
                                                                  GridBagConstraints.RELATIVE,
                                                                  1,
                                                                  1,
                                                                  0.0,
                                                                  0.0,
                                                                  GridBagConstraints.NORTHWEST,
                                                                  GridBagConstraints.HORIZONTAL,
                                                                  INSETS_DEFAULT,
                                                                  IPADX_DEFAULT,
                                                                  IPADY_DEFAULT));
        opView.add(new JButton(m_getChosenPaths), new GridBagConstraints(0,
                                                                         GridBagConstraints.RELATIVE,
                                                                         1,
                                                                         1,
                                                                         0.0,
                                                                         0.0,
                                                                         GridBagConstraints.NORTHWEST,
                                                                         GridBagConstraints.HORIZONTAL,
                                                                         INSETS_DEFAULT,
                                                                         IPADX_DEFAULT,
                                                                         IPADY_DEFAULT));
        opView.add(new JButton(m_setChosenPaths), new GridBagConstraints(0,
                                                                         GridBagConstraints.RELATIVE,
                                                                         1,
                                                                         1,
                                                                         0.0,
                                                                         0.0,
                                                                         GridBagConstraints.NORTHWEST,
                                                                         GridBagConstraints.HORIZONTAL,
                                                                         INSETS_DEFAULT,
                                                                         IPADX_DEFAULT,
                                                                         IPADY_DEFAULT));
        opView.add(new JButton(m_clearMsg), new GridBagConstraints(0,
                                                                   GridBagConstraints.RELATIVE,
                                                                   1,
                                                                   1,
                                                                   0.0,
                                                                   0.0,
                                                                   GridBagConstraints.NORTHWEST,
                                                                   GridBagConstraints.HORIZONTAL,
                                                                   INSETS_DEFAULT,
                                                                   IPADX_DEFAULT,
                                                                   IPADY_DEFAULT));
        opView.add(m_enabledChoice, new GridBagConstraints(0,
                                                           GridBagConstraints.RELATIVE,
                                                           1,
                                                           1,
                                                           0.0,
                                                           0.0,
                                                           GridBagConstraints.NORTHWEST,
                                                           GridBagConstraints.HORIZONTAL,
                                                           INSETS_DEFAULT,
                                                           IPADX_DEFAULT,
                                                           IPADY_DEFAULT));
        opView.add(m_enabledListSelectionListener, new GridBagConstraints(0,
                                                                          GridBagConstraints.RELATIVE,
                                                                          1,
                                                                          1,
                                                                          0.0,
                                                                          0.0,
                                                                          GridBagConstraints.NORTHWEST,
                                                                          GridBagConstraints.HORIZONTAL,
                                                                          INSETS_DEFAULT,
                                                                          IPADX_DEFAULT,
                                                                          IPADY_DEFAULT));
        opView.add(m_enabledTableModelListener, new GridBagConstraints(0,
                                                                       GridBagConstraints.RELATIVE,
                                                                       1,
                                                                       1,
                                                                       0.0,
                                                                       0.0,
                                                                       GridBagConstraints.NORTHWEST,
                                                                       GridBagConstraints.HORIZONTAL,
                                                                       INSETS_DEFAULT,
                                                                       IPADX_DEFAULT,
                                                                       IPADY_DEFAULT));
        opView.add(m_enabledTableColumnModelListener, new GridBagConstraints(0,
                                                                             GridBagConstraints.RELATIVE,
                                                                             1,
                                                                             1,
                                                                             0.0,
                                                                             0.0,
                                                                             GridBagConstraints.NORTHWEST,
                                                                             GridBagConstraints.HORIZONTAL,
                                                                             INSETS_DEFAULT,
                                                                             IPADX_DEFAULT,
                                                                             IPADY_DEFAULT));
        opView.add(m_enabledTreeSelectionListener, new GridBagConstraints(0,
                                                                          GridBagConstraints.RELATIVE,
                                                                          1,
                                                                          1,
                                                                          0.0,
                                                                          0.0,
                                                                          GridBagConstraints.NORTHWEST,
                                                                          GridBagConstraints.HORIZONTAL,
                                                                          INSETS_DEFAULT,
                                                                          IPADX_DEFAULT,
                                                                          IPADY_DEFAULT));
        opView.add(m_enabledTreeWillExpandListener, new GridBagConstraints(0,
                                                                           GridBagConstraints.RELATIVE,
                                                                           1,
                                                                           1,
                                                                           0.0,
                                                                           0.0,
                                                                           GridBagConstraints.NORTHWEST,
                                                                           GridBagConstraints.HORIZONTAL,
                                                                           INSETS_DEFAULT,
                                                                           IPADX_DEFAULT,
                                                                           IPADY_DEFAULT));
        opView.add(m_enabledTreeExpansionListener, new GridBagConstraints(0,
                                                                          GridBagConstraints.RELATIVE,
                                                                          1,
                                                                          1,
                                                                          0.0,
                                                                          0.0,
                                                                          GridBagConstraints.NORTHWEST,
                                                                          GridBagConstraints.HORIZONTAL,
                                                                          INSETS_DEFAULT,
                                                                          IPADX_DEFAULT,
                                                                          IPADY_DEFAULT));
        opView.add(m_enabledComplexTreeModelListener, new GridBagConstraints(0,
                                                                             GridBagConstraints.RELATIVE,
                                                                             1,
                                                                             1,
                                                                             0.0,
                                                                             0.0,
                                                                             GridBagConstraints.NORTHWEST,
                                                                             GridBagConstraints.HORIZONTAL,
                                                                             INSETS_DEFAULT,
                                                                             IPADX_DEFAULT,
                                                                             IPADY_DEFAULT));
        opView.add(m_enabledTreeModelListener, new GridBagConstraints(0,
                                                                      GridBagConstraints.RELATIVE,
                                                                      1,
                                                                      1,
                                                                      0.0,
                                                                      1.0,
                                                                      GridBagConstraints.NORTHWEST,
                                                                      GridBagConstraints.HORIZONTAL,
                                                                      INSETS_DEFAULT,
                                                                      IPADX_DEFAULT,
                                                                      IPADY_DEFAULT));

        add(tableAndMsgView, new GridBagConstraints(0,
                                                    0,
                                                    1,
                                                    1,
                                                    1.0,
                                                    1.0,
                                                    GridBagConstraints.WEST,
                                                    GridBagConstraints.BOTH,
                                                    INSETS_DEFAULT,
                                                    IPADX_DEFAULT,
                                                    IPADY_DEFAULT));
        add(opView, new GridBagConstraints(GridBagConstraints.RELATIVE,
                                           0,
                                           1,
                                           1,
                                           0.0,
                                           1.0,
                                           GridBagConstraints.NORTHWEST,
                                           GridBagConstraints.VERTICAL,
                                           INSETS_DEFAULT,
                                           IPADX_DEFAULT,
                                           IPADY_DEFAULT));
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- IComplexTreeDataSource Public Method -------------------------------------*/

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeDataSource#load(com.apollo.swing.component.complextree.ComplexTreeCacheNode)
     */
    public ComplexTreeNode[] load(ComplexTreeCacheNode parent) throws ComplexTreeException {
        try {
            Thread.sleep(1000);

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        String desc = parent.getUserObject().toString();

        if (desc.startsWith("EMS")) {
            List nes = createNes();
            return (ComplexTreeNode[]) nes.toArray(new ComplexTreeNode[nes.size()]);

        } else if (desc.startsWith("NE")) {
            List boards = createBoards();
            return (ComplexTreeNode[]) boards.toArray(new ComplexTreeNode[boards.size()]);

        } else {
            List ptps = createPtps();
            return (ComplexTreeNode[]) ptps.toArray(new ComplexTreeNode[ptps.size()]);
        }
    }

    /*------------------------------------- ListSelectionListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    public void valueChanged(ListSelectionEvent evt) {
        if (m_enabledListSelectionListener.isSelected()) {
            m_msg.append("ListSelectionListener.valueChanged: " + evt + "\n");
        }
    }

    /*------------------------------------- TableModelListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
     */
    public void tableChanged(TableModelEvent evt) {
        if (m_enabledTableModelListener.isSelected()) {
            m_msg.append("TableModelListener.tableChanged: " + evt + "\n");
        }
    }

    /*------------------------------------- TableColumnModelListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TableColumnModelListener#columnAdded(javax.swing.event.TableColumnModelEvent)
     */
    public void columnAdded(TableColumnModelEvent evt) {
        if (m_enabledTableColumnModelListener.isSelected()) {
            m_msg.append("TableColumnModelListener.columnAdded: " + evt + "\n");
        }
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnMarginChanged(javax.swing.event.ChangeEvent)
     */
    public void columnMarginChanged(ChangeEvent evt) {
        if (m_enabledTableColumnModelListener.isSelected()) {
            m_msg.append("TableColumnModelListener.columnMarginChanged: " + evt + "\n");
        }
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnMoved(javax.swing.event.TableColumnModelEvent)
     */
    public void columnMoved(TableColumnModelEvent evt) {
        if (m_enabledTableColumnModelListener.isSelected()) {
            m_msg.append("TableColumnModelListener.columnMoved: " + evt + "\n");
        }
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnRemoved(javax.swing.event.TableColumnModelEvent)
     */
    public void columnRemoved(TableColumnModelEvent evt) {
        if (m_enabledTableColumnModelListener.isSelected()) {
            m_msg.append("TableColumnModelListener.columnRemoved: " + evt + "\n");
        }
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnSelectionChanged(javax.swing.event.ListSelectionEvent)
     */
    public void columnSelectionChanged(ListSelectionEvent evt) {
        if (m_enabledTableColumnModelListener.isSelected()) {
            m_msg.append("TableColumnModelListener.columnSelectionChanged: " + evt + "\n");
        }
    }

    /*------------------------------------- TreeSelectionListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
     */
    public void valueChanged(TreeSelectionEvent evt) {
        if (m_enabledTreeSelectionListener.isSelected()) {
            m_msg.append("TreeSelectionListener.valueChanged: " + evt + "\n");
        }
    }

    /*------------------------------------- TreeWillExpandListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TreeWillExpandListener#treeWillCollapse(javax.swing.event.TreeExpansionEvent)
     */
    public void treeWillCollapse(TreeExpansionEvent evt) throws ExpandVetoException {
        if (m_enabledTreeWillExpandListener.isSelected()) {
            m_msg.append("TreeWillExpandListener.treeWillCollapse: " + evt + "\n");
        }
    }

    /**
     * @see javax.swing.event.TreeWillExpandListener#treeWillExpand(javax.swing.event.TreeExpansionEvent)
     */
    public void treeWillExpand(TreeExpansionEvent evt) throws ExpandVetoException {
        if (m_enabledTreeWillExpandListener.isSelected()) {
            m_msg.append("TreeWillExpandListener.treeWillExpand: " + evt + "\n");
        }
    }

    /*------------------------------------- TreeExpansionListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TreeExpansionListener#treeCollapsed(javax.swing.event.TreeExpansionEvent)
     */
    public void treeCollapsed(TreeExpansionEvent evt) {
        if (m_enabledTreeExpansionListener.isSelected()) {
            m_msg.append("TreeExpansionListener.treeCollapsed: " + evt + "\n");
        }
    }

    /**
     * @see javax.swing.event.TreeExpansionListener#treeExpanded(javax.swing.event.TreeExpansionEvent)
     */
    public void treeExpanded(TreeExpansionEvent evt) {
        if (m_enabledTreeExpansionListener.isSelected()) {
            m_msg.append("TreeExpansionListener.treeExpanded: " + evt + "\n");
        }
    }

    /*------------------------------------- IComplexTreeModelListener Public Method -------------------------------------*/

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeChoiceChanged(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeChoiceChanged(ComplexTreeModelEvent evt) {
        if (m_enabledComplexTreeModelListener.isSelected()) {
            m_msg.append("IComplexTreeModelListener.nodeChoiceChanged: " + evt + "\n");
        }
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeLoadFailed(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeLoadFailed(ComplexTreeModelEvent evt) {
        if (m_enabledComplexTreeModelListener.isSelected()) {
            m_msg.append("IComplexTreeModelListener.nodeLoadFailed: " + evt + "\n");
        }
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeLoaded(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeLoaded(ComplexTreeModelEvent evt) {
        if (m_enabledComplexTreeModelListener.isSelected()) {
            m_msg.append("IComplexTreeModelListener.nodeLoaded: " + evt + "\n");
        }
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeWillLoad(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeWillLoad(ComplexTreeModelEvent evt) {
        if (m_enabledComplexTreeModelListener.isSelected()) {
            m_msg.append("IComplexTreeModelListener.nodeWillLoad: " + evt + "\n");
        }
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#releaseCache(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void releaseCache(ComplexTreeModelEvent evt) {
        if (m_enabledComplexTreeModelListener.isSelected()) {
            m_msg.append("IComplexTreeModelListener.releaseCache: " + evt + "\n");
        }
    }

    /*------------------------------------- TreeModelListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TreeModelListener#treeNodesChanged(javax.swing.event.TreeModelEvent)
     */
    public void treeNodesChanged(TreeModelEvent evt) {
        if (m_enabledTreeModelListener.isSelected()) {
            m_msg.append("TreeModelListener.treeNodesChanged: " + evt + "\n");
        }
    }

    /**
     * @see javax.swing.event.TreeModelListener#treeNodesInserted(javax.swing.event.TreeModelEvent)
     */
    public void treeNodesInserted(TreeModelEvent evt) {
        if (m_enabledTreeModelListener.isSelected()) {
            m_msg.append("TreeModelListener.treeNodesInserted: " + evt + "\n");
        }
    }

    /**
     * @see javax.swing.event.TreeModelListener#treeNodesRemoved(javax.swing.event.TreeModelEvent)
     */
    public void treeNodesRemoved(TreeModelEvent evt) {
        if (m_enabledTreeModelListener.isSelected()) {
            m_msg.append("TreeModelListener.treeNodesRemoved: " + evt + "\n");
        }
    }

    /**
     * @see javax.swing.event.TreeModelListener#treeStructureChanged(javax.swing.event.TreeModelEvent)
     */
    public void treeStructureChanged(TreeModelEvent evt) {
        if (m_enabledTreeModelListener.isSelected()) {
            m_msg.append("TreeModelListener.treeStructureChanged: " + evt + "\n");
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /**
     * 创建根节点。
     * @return 根节点。
     */
    private ComplexTreeNode createRoot() {
        List cells = createCells();
        for (int i = 0, size = cells.size(); i < size; i++) {
            ((TreeTableCell) cells.get(i)).setBackground((Color.ORANGE));
        }

        TreeTableObject obj = new TreeTableObject("全网资源", cells);
        obj.setNodeBackground(Color.CYAN);
        //obj.setBackground(Color.RED);

        ComplexTreeChoiceNode root = new ComplexTreeChoiceNode(obj);
        root.setRenderer(RENDERER_ROOT);

        for (int i = 0; i < 10; i++) {
            ComplexTreeCacheNode ems = new ComplexTreeCacheNode(new TreeTableObject("EMS" + i, createCells()));
            ems.setRenderer(RENDERER_EMS);
            root.add(ems);
        }

        return root;
    }

    /**
     * 创建网元节点集。
     * @return 网元节点集。
     */
    private List createNes() {
        List nes = new ArrayList();

        for (int i = 0; i < 10; i++) {
            TreeTableObject obj = new TreeTableObject("NE" + i, createCells());
            //obj.setForeground(Color.CYAN);
            //obj.setBackground(Color.PINK);

            ComplexTreeCacheNode ne = new ComplexTreeCacheNode(obj);
            ne.setRenderer(RENDERER_NE);
            nes.add(ne);
        }

        return nes;
    }

    /**
     * 创建单板节点集。
     * @return 单板节点集。
     */
    private List createBoards() {
        List boards = new ArrayList();

        for (int i = 0; i < 10; i++) {
            TreeTableObject obj = new TreeTableObject("BOARD" + i, createCells());
            obj.setBackground(Color.ORANGE);

            ComplexTreeCacheNode board = new ComplexTreeCacheNode(obj);
            board.setRenderer(RENDERER_BOARD);
            boards.add(board);
        }

        return boards;
    }

    /**
     * 创建端口节点集。
     * @return 端口节点集。
     */
    private List createPtps() {
        List Ptps = new ArrayList();

        for (int i = 0; i < 10; i++) {
            ComplexTreeChoiceNode ptp = new ComplexTreeChoiceNode(new TreeTableObject("PTP" + i, createCells()));
            ptp.setRenderer(RENDERER_PTP);
            Ptps.add(ptp);
        }

        return Ptps;
    }

    /**
     * 创建单元格集。
     * @return 单元格集。
     */
    private List createCells() {
        List cells = new ArrayList();

        TreeTableCell cell = new TreeTableCell(DateFormat.getDateTimeInstance().format(new Date()));
        cells.add(cell);

        cell = new TreeTableCell("ZTE");
        cell.setEditable(true);
        cell.setEditor(new DefaultCellEditor(new JComboBox(new Object[] { "ZTE", "HW" })));
        cells.add(cell);

        cell = new TreeTableCell(Boolean.TRUE);
        cell.setEditable(true);
        cells.add(cell);

        cell = new TreeTableCell(BigInteger.valueOf(25));
        cell.setEditable(true);
        cell.setEditor(new FormattedFieldCellEditor(new IntegerField(5, 1, 10, 2)));
        cell.setRenderer(m_pbRenderer);
        cells.add(cell);

        cell = new TreeTableCell("");
        cell.setEditable(true);
        cells.add(cell);

        return cells;
    }

}
