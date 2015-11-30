/*
 * 此代码创建于 2007-6-5 下午05:34:46
 */
package com.apollo.swing.component.common.jtable;

import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * <p>文件名称：TComponentByEditorTableCellRenderer.java</p>
 * <p>文件描述：和编辑器共用组件的表格渲染器，需要编辑器返回的是一个JComponent组件。</p>
 * <p>版权所有：版权所有(C)2001-2004</p>
 * <p>公　　司：菠萝梨</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2007-6-5</p>
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
public final class ComponentByEditorTableCellRenderer implements TableCellRenderer {

    /*------------------------------------- Public Static Field -------------------------------------*/

    /**
     * 控件非焦点状态的边框，为一个四周都是1个像素宽度的空边框。
     */
    public static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /* Add Public Constructor */

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Static Method -------------------------------------*/

    /* Add Public Static Method */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- TableCellRenderer Public Method -------------------------------------*/

    /**
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellEditor editor = table.getCellEditor(row, column);
        if (editor == null) { //没有提供编辑器
            return NO_EDITOR_LABEL;
        }

        JComponent component = (JComponent) editor.getTableCellEditorComponent(table, value, isSelected, row, column);
        if (component == null) { //编辑器没有提供组件
            return NO_COMPONENT_LABEL;
        }

        /* 设置透明性 */
        if (!component.isOpaque()) {
            component.setOpaque(true);
        }

        /* 设置边框（有些控件缺省不支持边框绘制） */
        if (!isBorderPainted(component)) {
            setBorderPainted(component, true);
        }

        /* 设置单元格选中与非选中状态时候控件的背景色 */
        if (isSelected) {
            component.setBackground(table.getSelectionBackground());

        } else {
            component.setBackground(null);
        }

        /* 设置单元格获取焦点与非焦点状态时候控件的边框 */
        if (hasFocus) {
            component.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));

        } else {
            component.setBorder(NO_FOCUS_BORDER);
        }

        return component;
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /* Add Protected Static Method */

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /**
     * 判断组件的边框是否可绘制。
     * @param component 组件。
     * @return 为true表示组件的边框可绘制，为false表示组件的边框不可绘制。
     */
    private boolean isBorderPainted(JComponent component) {
        /* 以下5类特殊组件是可以设置边框是否可编辑的，这些是通过查询JDK得到的 */
        if (component instanceof AbstractButton) {
            return ((AbstractButton) component).isBorderPainted();

        } else if (component instanceof JMenuBar) {
            return ((JMenuBar) component).isBorderPainted();

        } else if (component instanceof JPopupMenu) {
            return ((JPopupMenu) component).isBorderPainted();

        } else if (component instanceof JProgressBar) {
            return ((JProgressBar) component).isBorderPainted();

        } else if (component instanceof JToolBar) {
            return ((JToolBar) component).isBorderPainted();

        } else { //默认所有控件都可绘制边框
            return true;
        }
    }

    /**
     * 设置组件的边框是否可绘制。
     * @param component 组件。
     * @param isBorderPainted 为true表示组件的边框可绘制，为false表示组件的边框不可绘制。
     */
    private void setBorderPainted(JComponent component, boolean isBorderPainted) {
        /* 以下5类特殊组件是可以设置边框是否可编辑的，这些是通过查询JDK得到的 */
        if (component instanceof AbstractButton) {
            ((AbstractButton) component).setBorderPainted(isBorderPainted);

        } else if (component instanceof JMenuBar) {
            ((JMenuBar) component).setBorderPainted(isBorderPainted);

        } else if (component instanceof JPopupMenu) {
            ((JPopupMenu) component).setBorderPainted(isBorderPainted);

        } else if (component instanceof JProgressBar) {
            ((JProgressBar) component).setBorderPainted(isBorderPainted);

        } else if (component instanceof JToolBar) {
            ((JToolBar) component).setBorderPainted(isBorderPainted);
        }
    }

    /*------------------------------------- Public Static Inner Class -------------------------------------*/

    /* Add Public Static Inner Class */

    /*------------------------------------- Public Inner Class -------------------------------------*/

    /* Add Public Inner Class */

    /*------------------------------------- Protected Static Inner Class -------------------------------------*/

    /* Add Protected Static Inner Class */

    /*------------------------------------- Protected Inner Class -------------------------------------*/

    /* Add Protected Inner Class */

    /*------------------------------------- Friendly Static Inner Class -------------------------------------*/

    /* Add Friendly Static Inner Class */

    /*------------------------------------- Friendly Inner Class -------------------------------------*/

    /* Add Friendly Inner Class */

    /*------------------------------------- Private Static Inner Class -------------------------------------*/

    /* Add Private Static Inner Class */

    /*------------------------------------- Private Inner Class -------------------------------------*/

    /* Add Private Inner Class */

    /*------------------------------------- Protected Static Field -------------------------------------*/

    /* Add Protected Static Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /* Add Protected Field */

    /*------------------------------------- Friendly Static Field -------------------------------------*/

    /* Add Friendly Static Field */

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Static Field -------------------------------------*/

    /**
     * 没有编辑器时显示的标签组件。
     */
    private static final JLabel NO_EDITOR_LABEL = new JLabel("No Support Editor");

    /**
     * 编辑器返回无效组件时显示的标签组件。
     */
    private static final JLabel NO_COMPONENT_LABEL = new JLabel("No Support Component");

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

}
