/*
 * 此代码创建于 2009-9-8 上午08:59:22。
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
 * <p>文件名称：TextDemo.java</p>
 * <p>类型描述：文本输入框演示类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2009-9-8</p>
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
     * 主方法。
     * @param args 参数集。
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
     * 综合表。
     */
    protected ComplexTable m_complexTable = null;

    /**
     * 浮点数输入框。
     */
    protected DecimalField m_decimalField = null;

    /**
     * 整型数输入框。
     */
    protected IntegerField m_integerField = null;

    /**
     * 字符串输入框。
     */
    protected StringField m_stringField = null;

    /**
     * 消息框。
     */
    protected JTextArea m_msg = new JTextArea(10, 20);

    /**
     * 动作------获取浮点数。
     */
    protected Action m_getDecimal = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "获取值");
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
     * 动作------获取整型数。
     */
    protected Action m_getInteger = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "获取值");
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
     * 动作------获取字符串。
     */
    protected Action m_getString = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "获取值");
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
     * 构造方法。
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

        ComplexTableModel dm = new ComplexTableModel(new Object[][] { { "浮点数输入框", decimalCell },
                                                                     { "整型数输入框", integerCell },
                                                                     { "字符串输入框", stringCell } }, new Object[] { "类型", "输入框" });
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

        add(new JLabel("浮点数输入框"), new GridBagConstraints(0,
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

        add(new JLabel("整型数输入框"), new GridBagConstraints(0,
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

        add(new JLabel("字符串输入框"), new GridBagConstraints(0,
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
