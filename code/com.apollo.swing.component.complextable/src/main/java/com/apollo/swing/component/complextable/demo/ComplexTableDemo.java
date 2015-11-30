/*
 * �˴��봴���� 2008-4-23 ����04:01:02��
 */
package com.apollo.swing.component.complextable.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;

import com.apollo.base.util.BaseUtilities;
import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.complextable.ComplexTable;
import com.apollo.swing.component.complextable.ComplexTableCell;
import com.apollo.swing.component.complextable.ComplexTableColumn;
import com.apollo.swing.component.complextable.ComplexTableModel;
import com.apollo.swing.component.complextable.ComplexTableRow;
import com.apollo.swing.component.complextable.IComplexTableManager;
import com.apollo.swing.component.editor.FormattedFieldCellEditor;
import com.apollo.swing.component.editor.IntegerFieldCellEditor;
import com.apollo.swing.component.table.TableCustom;
import com.apollo.swing.component.text.IntegerField;
import com.apollo.swing.laf.TLookAndFeelManager;

/**
 * <p>�ļ����ƣ�ComplexTableDemo.java</p>
 * <p>�����������ۺϱ���ʾ�ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-4-23</p>
 * <p>�޸ļ�¼��</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * @version 1.0
 * @author ����
 */
@SuppressWarnings("all")
public class ComplexTableDemo extends JPanel implements IBaseConstants, IComplexTableManager, TableModelListener {

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

    /* Add Private Static Field */

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Public Static Method -------------------------------------*/

    /**
     * ������ڡ�
     * @param args ��ڲ�������
     */
    public static void main(String[] args) {
        TLookAndFeelManager.loadAlloy(false);

        JFrame f = new JFrame("Complex Table Demo");
        final ComplexTableDemo ctd = new ComplexTableDemo();

        f.setContentPane(ctd);
        f.addWindowListener(new WindowAdapter() {

            /**
             * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
             */
            public void windowClosing(WindowEvent evt) {
                ctd.m_ct.close(); //���ʵ���ʱ��ر��ۺϱ��Դ洢�б�����Ϣ
            }

        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
     * �ۺϱ�
     */
    protected ComplexTable m_ct;

    /**
     * ��Ϣ��塣
     */
    protected JTextArea m_msgTa = new JTextArea();

    /**
     * �����Ϣ��
     */
    protected Action m_clearMsg = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "�����Ϣ", 'C');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_msgTa.setText("");
        }

    };

    /**
     * ��ӡ��Ϣ��
     */
    protected Action m_print = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "��ӡ��Ϣ", 'P');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_ct.save(); //��ӡǰ�ȱ���һ��

            m_msgTa.append("---------------------------------------------------------\n");

            ComplexTableRow[] rows = m_ct.getComplexTableModel().getRows();
            ComplexTableModel ctm = m_ct.getComplexTableModel();

            for (int i = 0; i < rows.length; i++) {
                m_msgTa.append("������" + ctm.getCell(i, 0).getValue() + " , ");
                m_msgTa.append("�Ա�" + ctm.getCell(i, 1).getValue() + " , ");
                m_msgTa.append("���䣺" + ctm.getCell(i, 2).getValue() + " , ");
                m_msgTa.append("��ַ��" + ctm.getCell(i, 3).getValue() + " , ");
                m_msgTa.append("���" + ctm.getCell(i, 4).getValue() + " , ");
                m_msgTa.append("нˮ��" + ctm.getCell(i, 5).getValue() + " , ");
                m_msgTa.append("��Ʒ��" + ctm.getCell(i, 6).getValue() + "\n");
            }

            m_msgTa.append("---------------------------------------------------------\n");
        }

    };

    /**
     * ȫѡ���
     */
    protected Action m_selectAllMarry = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "ȫѡ", 'A');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            for (int i = 0, size = m_ct.getRowCount(); i < size; i++) {
                m_ct.getComplexTableModel().setValueAt(Boolean.TRUE, i, 4);
            }
        }

    };

    /**
     * ȫȥѡ���
     */
    protected Action m_deselectAllMarry = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "ȫȥѡ", 'M');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            for (int i = 0, size = m_ct.getRowCount(); i < size; i++) {
                m_ct.getComplexTableModel().setValueAt(Boolean.FALSE, i, 4);
            }
        }

    };

    /**
     * ���
     */
    protected Action m_marry = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "���", 'J');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            JOptionPane.showMessageDialog(ComplexTableDemo.this, "���ǻ���У�");
        }

    };

    /**
     * ��н��
     */
    protected Action m_raises = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "��н", 'J');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            ComplexTableModel model = m_ct.getComplexTableModel();
            int rowIndex = m_ct.getSelectedRow();
            ComplexTableRow row = model.getRow(rowIndex);
            ComplexTableCell pay = row.getCell(5);
            pay.setValue(((BigInteger) pay.getValue()).add(BigInteger.valueOf(500))); //��ֵ
            model.fireTableCellUpdated(rowIndex, 5); //��֪ͨ
            JOptionPane.showMessageDialog(ComplexTableDemo.this, "��н500��");
        }

    };

    /**
     * �����С�
     */
    protected Action m_odd = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "������", 'J');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            JOptionPane.showMessageDialog(ComplexTableDemo.this, "���ǣ�ģ�Ͳ㣩�����У�");
        }

    };

    /**
     * ż���С�
     */
    protected Action m_even = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "ż����", 'O');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            JOptionPane.showMessageDialog(ComplexTableDemo.this, "���ǣ�ģ�Ͳ㣩ż���У�");
        }

    };

    /**
     * �С�
     */
    protected Action m_row = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "��", 'J');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            JOptionPane.showMessageDialog(ComplexTableDemo.this, "�����У�");
        }

    };

    /**
     * �Ƿ���������
     */
    protected JCheckBoxMenuItem m_sortable = new JCheckBoxMenuItem(new AbstractAction() {

        {
            BaseUtilities.setAction(this, "��������", 'O');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_ct.setSortable(m_sortable.isSelected());
        }

    });

    /**
     * ����С�
     */
    protected Action m_addColumn = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "�����", 'L');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            final int columnCount = m_ct.getColumnCount();

            ComplexTableRow[] rows = m_ct.getComplexTableModel().getRows();
            for (int i = 0; i < rows.length; i++) {
                rows[i].add(new ComplexTableCell("����ֵ" + columnCount));
            }

            ComplexTableColumn column = ComplexTableColumn.create("������" + columnCount);
            column.addAction(new AbstractAction() {

                {
                    BaseUtilities.setAction(this, "ɾ������", 'E');
                }

                /**
                 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
                 */
                public void actionPerformed(ActionEvent evt) {
                    ComplexTableRow[] rows = m_ct.getComplexTableModel().getRows();
                    for (int i = 0; i < rows.length; i++) {
                        rows[i].remove(columnCount);
                    }

                    m_ct.getComplexTableModel().removeColumn(columnCount);
                }

            });
            m_ct.getComplexTableModel().addColumn(column);
        }

    };

    /**
     * �ı����ݡ�
     */
    protected Action m_changeData = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "�ı�ṹ", 'S');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_ct.getComplexTableModel().setData(new String[][] { { "1", "2", "3" }, { "4", "5", "6" } }, new String[] { "a", "b", "c" });
        }

    };

    /**
     * ʹ�ܡ�
     */
    protected Action m_isEnabled = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "ʹ��", 'S');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_ct.setEnabled(!m_ct.isEnabled());
        }

    };

    /**
     * չ�ֱ�ǡ�
     */
    protected JCheckBoxMenuItem m_isEnabledShowFlag = new JCheckBoxMenuItem(new AbstractAction() {

        {
            BaseUtilities.setAction(this, "չ�ֱ��", 'F');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_ct.setEnabledShowFlag(m_isEnabledShowFlag.isSelected());
        };

    });

    /**
     * ��������Ⱦ����
     */
    protected TableCellRenderer m_pbRenderer = new TableCellRenderer() {

        private final Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

        /**
         * ��������
         */
        protected JProgressBar m_pb = new JProgressBar();

        {
            m_pb.setStringPainted(true);
            //m_pb.setForeground(Color.GREEN);
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
            m_pb.setEnabled(table.isEnabled());

            return m_pb;
        }

    };

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     */
    public ComplexTableDemo() {
        super(new GridBagLayout());

        ComplexTableColumn[] columns = createColumns();
        List<TableCustom> bodyDefaultCustoms = new ArrayList<TableCustom>();
        List<TableCustom> rowHeaderDefaultCustoms = new ArrayList<TableCustom>();
        for (int i = 0; i < columns.length; i++) {
            if (i % 2 == 0) {
                bodyDefaultCustoms.add(new TableCustom(columns[i], true));

            } else {
                rowHeaderDefaultCustoms.add(new TableCustom(columns[i], true));
            }
        }

        m_ct = new ComplexTable(new ComplexTableModel(createRows(), columns),
                                false,
                                false,
                                this,
                                "ComplexTableDemo",
                                null,
                                false,
                                true,
                                bodyDefaultCustoms,
                                rowHeaderDefaultCustoms);
        m_ct.getModel().addTableModelListener(this); //����ע��ģ�ͼ�������Ϊ�����ĳЩԼ����
        //m_ct.setRowHeight(25); //JComboBox̫�ߣ�ȱʡ�и߲���
        //m_ct.setReadMode(true);
        //m_ct.setEnabledShowFlag(false);

        m_sortable.setSelected(true);

        m_isEnabledShowFlag.setSelected(m_ct.isEnabledShowFlag());
        m_ct.addMenuItem(m_isEnabledShowFlag);

        /* ��ӱ����Ҽ��˵����� */
        JMenu menu = new JMenu("���Թ���");

        menu.add(m_sortable);
        menu.add(m_addColumn);
        menu.add(m_changeData);

        m_ct.addMenuItem(menu);

        m_ct.addAction(m_print);
        m_ct.addAction(m_clearMsg);
        m_ct.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        constrain();

        m_msgTa.setEditable(false);

        JScrollPane ctSp = new JScrollPane(m_ct);
        ctSp.setBorder(BorderFactory.createTitledBorder("�ۺϱ�"));

        JScrollPane msgTaSp = new JScrollPane(m_msgTa);
        msgTaSp.setBorder(BorderFactory.createTitledBorder("��Ϣ��"));
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctSp, msgTaSp);
        sp.setDividerLocation(300);

        add(new JCheckBox(m_isEnabled), new GridBagConstraints(0,
                                                               0,
                                                               1,
                                                               1,
                                                               1.0,
                                                               0.0,
                                                               GridBagConstraints.WEST,
                                                               GridBagConstraints.HORIZONTAL,
                                                               INSETS_DEFAULT,
                                                               IPADX_DEFAULT,
                                                               IPADY_DEFAULT));
        add(sp, new GridBagConstraints(0,
                                       1,
                                       1,
                                       1,
                                       1.0,
                                       1.0,
                                       GridBagConstraints.WEST,
                                       GridBagConstraints.BOTH,
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

    /*------------------------------------- IComplexTableManager Public Method -------------------------------------*/

    /**
     * �����������ʱ��ȱʡ���ݡ�
     * @see com.apollo.swing.component.complextable.IComplexTableManager#createRow(com.apollo.swing.component.complextable.ComplexTable, int)
     */
    public ComplexTableRow createRow(ComplexTable table, int index) {
        ComplexTableRow row = new ComplexTableRow();

        row.add(new ComplexTableCell("Superman"));

        ComplexTableCell sex = new ComplexTableCell("��");
        sex.setEditable(true);
        row.add(sex);

        ComplexTableCell age = new ComplexTableCell(BigInteger.valueOf(100));
        age.setEditable(true);
        row.add(age);

        ComplexTableCell addr = new ComplexTableCell("����");
        addr.setEditable(true);
        row.add(addr);

        ComplexTableCell marry = new ComplexTableCell(Boolean.FALSE);
        marry.setEditable(true);
        row.add(marry);

        ComplexTableCell pay = new ComplexTableCell(BigInteger.valueOf(1000));
        pay.setEditable(true);
        row.add(pay);

        ComplexTableCell rp = new ComplexTableCell(BigInteger.valueOf(100));
        row.add(rp);

        return row;
    }

    /*------------------------------------- TableModelListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
     */
    public void tableChanged(TableModelEvent evt) {
        constrain();
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * �����м���
     * @return �м���
     */
    protected ComplexTableColumn[] createColumns() {
        ComplexTableColumn[] columns = new ComplexTableColumn[7];

        columns[0] = new ComplexTableColumn("����");
        columns[0].setCellEditor(new DefaultCellEditor(new JTextField()));
        columns[0].setMinWidth(20);
        columns[0].setMaxWidth(200);
        columns[0].setPreferredWidth(100); //�������ó�ʼ���

        columns[1] = new ComplexTableColumn("�Ա�");
        columns[1].setCellEditor(new DefaultCellEditor(new JComboBox(new String[] { "��", "Ů" })));
        columns[1].setMinWidth(20);
        columns[1].setMaxWidth(100);
        columns[1].setPreferredWidth(50); //�������ó�ʼ���

        columns[2] = new ComplexTableColumn("����");
        columns[2].setCellEditor(new IntegerFieldCellEditor(2, 1, 150));
        columns[2].setMinWidth(100);
        columns[2].setMaxWidth(200);
        columns[2].setPreferredWidth(150); //�������ó�ʼ���

        columns[3] = new ComplexTableColumn("��ַ");
        columns[3].setEditable(false); //�����Ͻ�ֹ�༭�� ��Ԫ�������õĶ���Ч�ˡ�
        columns[3].setMinWidth(50);
        columns[3].setMaxWidth(500);
        columns[3].setPreferredWidth(50); //�������ó�ʼ���

        columns[4] = new ComplexTableColumn("���");
        columns[4].setType(Boolean.class); //���ö�������
        columns[4].addHeaderAction(m_selectAllMarry); //����м����Ҽ��˵�����������ͷ�ϳ��֣�
        columns[4].addCellAction(m_deselectAllMarry); //����м����Ҽ��˵����������еĵ�Ԫ���ϳ��֣�
        columns[4].addAction(m_marry); //����м����Ҽ��˵�����������ͷ���еĵ�Ԫ���϶�����֣�
        columns[4].setMinWidth(20);
        columns[4].setMaxWidth(50);
        columns[4].setPreferredWidth(50); //�������ó�ʼ���

        columns[5] = new ComplexTableColumn("нˮ");
        columns[5].setCellEditor(new FormattedFieldCellEditor(new IntegerField(4, 1000, 11000)));
        columns[5].setMinWidth(50);
        columns[5].setMaxWidth(150);
        columns[5].setPreferredWidth(100); //�������ó�ʼ���

        columns[6] = new ComplexTableColumn("��Ʒ");
        columns[6].setCellRenderer(m_pbRenderer);
        columns[6].setMinWidth(100);
        columns[6].setMaxWidth(300);
        columns[6].setPreferredWidth(150); //�������ó�ʼ���

        return columns;
    }

    /**
     * �����м���
     * @return �м���
     */
    protected ComplexTableRow[] createRows() {
        ComplexTableRow[] rows = new ComplexTableRow[5];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new ComplexTableRow(i);

            ComplexTableCell name = new ComplexTableCell("Tester_" + (i + 1));
            name.setEditable(true);

            rows[i].add(name);

            ComplexTableCell sex = new ComplexTableCell("��");
            sex.setEditable(true);
            rows[i].add(sex);

            ComplexTableCell age = new ComplexTableCell(BigInteger.valueOf((long) (Math.random() * 100)));
            age.setEditable(true);
            rows[i].add(age);

            ComplexTableCell addr = new ComplexTableCell("����");
            addr.setEditable(true); //������Ϊ��������ס�ˣ�������������trueҲ��Ч���������ʾһ�¡�
            rows[i].add(addr);
            addr.setBackground(Color.ORANGE); //���õ�Ԫ��ı���ɫ��������ȱʡ��򵥵���Ⱦ����

            ComplexTableCell marry = new ComplexTableCell(Boolean.FALSE);
            marry.setEditable(true);
            rows[i].add(marry);

            ComplexTableCell pay = new ComplexTableCell(BigInteger.valueOf((long) (Math.random() * 10000) + 1000));
            pay.setEditable(true);
            pay.setForeground(Color.BLUE); //���õ�Ԫ���ǰ��ɫ��������ȱʡ��򵥵���Ⱦ����
            rows[i].add(pay);

            ComplexTableCell rp = new ComplexTableCell(BigInteger.valueOf((long) (Math.random() * 100)));
            rows[i].add(rp);

            /* ����м����Ҽ��˵����� */
            rows[i].addAction(m_row);
            if ((i + 1) % 2 == 0) {
                rows[i].addHeaderAction(m_even);

            } else {
                rows[i].addCellAction(m_odd);
            }
        }

        return rows;
    }

    /**
     * Լ������С�
     * @param row �С�
     */
    protected void constrainMarry(ComplexTableRow row) {
        try {
            String sex = (String) row.getCell(1).getValue();
            int age = ((BigInteger) row.getCell(2).getValue()).intValue();
            ComplexTableCell marry = row.getCell(4);

            if (age >= ("��".equals(sex) ? 22 : 20)) { //��22��Ů20���ܽ��
                if (!marry.isEditable()) {
                    marry.setEditable(true);
                    m_ct.repaintAll();
                }

            } else {
                if (marry.isEditable()) {
                    marry.setEditable(false);
                    m_ct.repaintAll();
                }

                if (marry.getValue().equals(Boolean.TRUE)) {
                    marry.setValue(Boolean.FALSE);
                    ComplexTableModel model = m_ct.getComplexTableModel();
                    model.fireTableCellUpdated(model.getRowIndex(row), 4); //��֪ͨ
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Լ��нˮ�С�
     * @param row �С�
     */
    protected void constrainPay(ComplexTableRow row) {
        try {
            ComplexTableCell pay = row.getCell(5);
            ComplexTableCell rp = row.getCell(6);

            if (((BigInteger) pay.getValue()).intValue() < 5000 && ((BigInteger) rp.getValue()).intValue() > 50) { //нˮ5000һ�£���Ʒ50���ϲ��ܼ�н
                if (!pay.containsAction(m_raises)) {
                    pay.addAction(m_raises);
                }

            } else {
                pay.removeAction(m_raises);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Լ����
     */
    protected void constrain() {
        for (int i = 0, size = m_ct.getRowCount(); i < size; i++) {
            ComplexTableRow row = m_ct.getRow(i);
            constrainMarry(row);
            constrainPay(row);
        }
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
