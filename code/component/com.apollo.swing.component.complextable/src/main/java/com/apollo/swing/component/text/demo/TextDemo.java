/*
 * �˴��봴���� 2009-9-8 ����08:59:22��
 */
package com.apollo.swing.component.text.demo;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.apollo.base.util.BaseUtilities;
import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.complextable.ComplexTable;
import com.apollo.swing.component.complextable.ComplexTableCell;
import com.apollo.swing.component.complextable.ComplexTableModel;
import com.apollo.swing.component.editor.FormattedFieldCellEditor;
import com.apollo.swing.component.text.DecimalField;
import com.apollo.swing.component.text.IntegerField;
import com.apollo.swing.component.text.StringField;
import com.apollo.swing.laf.TLookAndFeelManager;

/**
 * <p>�ļ����ƣ�TextDemo.java</p>
 * <p>�����������ı��������ʾ�ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2009-9-8</p>
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
public class TextDemo extends JPanel implements IBaseConstants {

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
     * ��������
     * @param args ��������
     */
    public static void main(String[] args) {
        TLookAndFeelManager.loadAlloy(false);

        JFrame f = new JFrame("Text Demo");
        f.setContentPane(new TextDemo());
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
    protected ComplexTable m_complexTable = null;

    /**
     * �����������
     */
    protected DecimalField m_decimalField = null;

    /**
     * �����������
     */
    protected IntegerField m_integerField = null;

    /**
     * �ַ��������
     */
    protected StringField m_stringField = null;

    /**
     * ��Ϣ��
     */
    protected JTextArea m_msg = new JTextArea(10, 20);

    /**
     * ����------��ȡ��������
     */
    protected Action m_getDecimal = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "��ȡֵ");
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_msg.setText("");

            try {
                m_decimalField.commitEdit();
                BigDecimal decimal = m_decimalField.getDecimal();
                m_msg.append(decimal == null ? "null" : decimal.toString());

            } catch (ParseException ex) {
                m_msg.append(ex.getMessage());
            }
        }

    };

    /**
     * ����------��ȡ��������
     */
    protected Action m_getInteger = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "��ȡֵ");
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_msg.setText("");
            try {
                m_integerField.commitEdit();
                BigInteger integer = m_integerField.getInteger();
                m_msg.append(integer == null ? "null" : integer.toString());

            } catch (ParseException ex) {
                m_msg.append(ex.getMessage());
            }
        }

    };

    /**
     * ����------��ȡ�ַ�����
     */
    protected Action m_getString = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "��ȡֵ");
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_msg.setText("");
            try {
                m_stringField.commitEdit();
                String string = m_stringField.getString();
                m_msg.append(string == null ? "null" : string);

            } catch (ParseException ex) {
                m_msg.append(ex.getMessage());
            }
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
    public TextDemo() {
        super(new GridBagLayout());

        ComplexTableCell decimalCell = new ComplexTableCell(BigDecimal.valueOf(3));
        decimalCell.setEditable(true);
        decimalCell.setEditor(new FormattedFieldCellEditor(new DecimalField(5, -271.00, 574.66, 2, 3.5)));

        ComplexTableCell integerCell = new ComplexTableCell(BigInteger.valueOf(57));
        integerCell.setEditable(true);
        integerCell.setEditor(new FormattedFieldCellEditor(new IntegerField(5, 50, 897)));

        ComplexTableCell stringCell = new ComplexTableCell("testString");
        stringCell.setEditable(true);
        stringCell.setEditor(new FormattedFieldCellEditor(new StringField(2, 10)));

        ComplexTableModel dm = new ComplexTableModel(new Object[][] { { "�����������", decimalCell },
                                                                     { "�����������", integerCell },
                                                                     { "�ַ��������", stringCell } }, new Object[] { "����", "�����" });
        m_complexTable = new ComplexTable(dm, false, false, null);
        JScrollPane complexTableView = new JScrollPane(m_complexTable);
        complexTableView.setPreferredSize(new Dimension(100, 100));

        m_decimalField = new DecimalField(5, -2000.00, -1500.00, 2, -1750);
        m_integerField = new IntegerField(5, -2000, -1500, -1750);
        m_integerField.setFocusLostBehavior(JFormattedTextField.COMMIT);
        m_stringField = new StringField(2, 10, "testString");
        m_stringField.setFocusLostBehavior(JFormattedTextField.COMMIT);

        add(complexTableView, new GridBagConstraints(0,
                                                     0,
                                                     3,
                                                     1,
                                                     1.0,
                                                     0.0,
                                                     GridBagConstraints.WEST,
                                                     GridBagConstraints.HORIZONTAL,
                                                     INSETS_DEFAULT,
                                                     IPADX_DEFAULT,
                                                     IPADY_DEFAULT));

        add(new JLabel("�����������"), new GridBagConstraints(0,
                                                         1,
                                                         1,
                                                         1,
                                                         0.0,
                                                         0.0,
                                                         GridBagConstraints.WEST,
                                                         GridBagConstraints.NONE,
                                                         INSETS_DEFAULT,
                                                         IPADX_DEFAULT,
                                                         IPADY_DEFAULT));
        add(m_decimalField, new GridBagConstraints(1,
                                                   1,
                                                   1,
                                                   1,
                                                   1.0,
                                                   0.0,
                                                   GridBagConstraints.WEST,
                                                   GridBagConstraints.HORIZONTAL,
                                                   INSETS_DEFAULT,
                                                   IPADX_DEFAULT,
                                                   IPADY_DEFAULT));
        add(new JButton(m_getDecimal), new GridBagConstraints(2,
                                                              1,
                                                              1,
                                                              1,
                                                              0.0,
                                                              0.0,
                                                              GridBagConstraints.WEST,
                                                              GridBagConstraints.NONE,
                                                              INSETS_DEFAULT,
                                                              IPADX_DEFAULT,
                                                              IPADY_DEFAULT));

        add(new JLabel("�����������"), new GridBagConstraints(0,
                                                         2,
                                                         1,
                                                         1,
                                                         0.0,
                                                         0.0,
                                                         GridBagConstraints.WEST,
                                                         GridBagConstraints.NONE,
                                                         INSETS_DEFAULT,
                                                         IPADX_DEFAULT,
                                                         IPADY_DEFAULT));
        add(m_integerField, new GridBagConstraints(1,
                                                   2,
                                                   1,
                                                   1,
                                                   1.0,
                                                   0.0,
                                                   GridBagConstraints.WEST,
                                                   GridBagConstraints.HORIZONTAL,
                                                   INSETS_DEFAULT,
                                                   IPADX_DEFAULT,
                                                   IPADY_DEFAULT));
        add(new JButton(m_getInteger), new GridBagConstraints(2,
                                                              2,
                                                              1,
                                                              1,
                                                              0.0,
                                                              0.0,
                                                              GridBagConstraints.WEST,
                                                              GridBagConstraints.NONE,
                                                              INSETS_DEFAULT,
                                                              IPADX_DEFAULT,
                                                              IPADY_DEFAULT));

        add(new JLabel("�ַ��������"), new GridBagConstraints(0,
                                                         3,
                                                         1,
                                                         1,
                                                         0.0,
                                                         0.0,
                                                         GridBagConstraints.WEST,
                                                         GridBagConstraints.NONE,
                                                         INSETS_DEFAULT,
                                                         IPADX_DEFAULT,
                                                         IPADY_DEFAULT));
        add(m_stringField, new GridBagConstraints(1,
                                                  3,
                                                  1,
                                                  1,
                                                  1.0,
                                                  0.0,
                                                  GridBagConstraints.WEST,
                                                  GridBagConstraints.HORIZONTAL,
                                                  INSETS_DEFAULT,
                                                  IPADX_DEFAULT,
                                                  IPADY_DEFAULT));
        add(new JButton(m_getString), new GridBagConstraints(2,
                                                             3,
                                                             1,
                                                             1,
                                                             0.0,
                                                             0.0,
                                                             GridBagConstraints.WEST,
                                                             GridBagConstraints.NONE,
                                                             INSETS_DEFAULT,
                                                             IPADX_DEFAULT,
                                                             IPADY_DEFAULT));

        add(new JScrollPane(m_msg), new GridBagConstraints(0,
                                                           4,
                                                           3,
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

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
