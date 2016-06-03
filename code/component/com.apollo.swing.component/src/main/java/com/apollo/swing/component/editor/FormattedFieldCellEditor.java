/*
 * 此代码创建于 2009-1-15 上午11:07:04。
 */
package com.apollo.swing.component.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.LineBorder;

import com.apollo.base.util.IBaseConstants;

/**
 * <p>文件名称：TFormattedFieldCellEditor.java</p>
 * <p>类型描述：格式化输入框单元格编辑器类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2009-1-15</p>
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
public class FormattedFieldCellEditor extends DefaultCellEditor implements FocusListener, IBaseConstants {

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

    /* Add Public Static Method */

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
     * 用于对话框的标题。
     */
    protected String m_title;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param field 格式化输入框。
     */
    public FormattedFieldCellEditor(JFormattedTextField field) {
        super(field);

        delegate = new EditorDelegate() {

            /**
             * @see javax.swing.DefaultCellEditor$EditorDelegate#setValue(java.lang.Object)
             */
            public void setValue(Object value) {
                getFormattedField().setValue(value);
            }

            /**
             * @see javax.swing.DefaultCellEditor$EditorDelegate#getCellEditorValue()
             */
            public Object getCellEditorValue() {
                JFormattedTextField field = getFormattedField();

                try {
                    field.commitEdit();

                } catch (ParseException ex) {
                    field.setBorder(new LineBorder(Color.RED));
                    JOptionPane.showMessageDialog(field, ex.getMessage(), m_title, JOptionPane.ERROR_MESSAGE);
                }

                return field.getValue();
            }

        };

        editorComponent.addFocusListener(this);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- DefaultCellEditor Public Method -------------------------------------*/

    /**
     * @see javax.swing.DefaultCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        getFormattedField().setBorder(new LineBorder(Color.BLACK));
        m_title = table.getColumnName(column);
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    /**
     * @see javax.swing.DefaultCellEditor#getTreeCellEditorComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int)
     */
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        JFormattedTextField field = getFormattedField();
        field.setPreferredSize(null);
        field.setBorder(new LineBorder(Color.BLACK));
        m_title = I18N_INFO_ERROR;
        Component component = super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
        Dimension ps = field.getPreferredSize();
        ps.height = 18;
        field.setPreferredSize(ps);
        return component;
    }

    /*------------------------------------- FocusListener Public Method -------------------------------------*/

    /**
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    public void focusGained(FocusEvent evt) {
        /* 暂不必处理 */
    }

    /**
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    public void focusLost(FocusEvent evt) {
        if (!evt.isTemporary()) {
            stopCellEditing();
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * 获取格式化输入框。
     * @return 格式化输入框。
     */
    protected JFormattedTextField getFormattedField() {
        return (JFormattedTextField) editorComponent;
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
