/*
 * �˴��봴���� 2007-6-5 ����05:34:46
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
 * <p>�ļ����ƣ�TComponentByEditorTableCellRenderer.java</p>
 * <p>�ļ��������ͱ༭����������ı����Ⱦ������Ҫ�༭�����ص���һ��JComponent�����</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004</p>
 * <p>������˾��������</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2007-6-5</p>
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
public final class ComponentByEditorTableCellRenderer implements TableCellRenderer {

    /*------------------------------------- Public Static Field -------------------------------------*/

    /**
     * �ؼ��ǽ���״̬�ı߿�Ϊһ�����ܶ���1�����ؿ�ȵĿձ߿�
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
        if (editor == null) { //û���ṩ�༭��
            return NO_EDITOR_LABEL;
        }

        JComponent component = (JComponent) editor.getTableCellEditorComponent(table, value, isSelected, row, column);
        if (component == null) { //�༭��û���ṩ���
            return NO_COMPONENT_LABEL;
        }

        /* ����͸���� */
        if (!component.isOpaque()) {
            component.setOpaque(true);
        }

        /* ���ñ߿���Щ�ؼ�ȱʡ��֧�ֱ߿���ƣ� */
        if (!isBorderPainted(component)) {
            setBorderPainted(component, true);
        }

        /* ���õ�Ԫ��ѡ�����ѡ��״̬ʱ��ؼ��ı���ɫ */
        if (isSelected) {
            component.setBackground(table.getSelectionBackground());

        } else {
            component.setBackground(null);
        }

        /* ���õ�Ԫ���ȡ������ǽ���״̬ʱ��ؼ��ı߿� */
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
     * �ж�����ı߿��Ƿ�ɻ��ơ�
     * @param component �����
     * @return Ϊtrue��ʾ����ı߿�ɻ��ƣ�Ϊfalse��ʾ����ı߿򲻿ɻ��ơ�
     */
    private boolean isBorderPainted(JComponent component) {
        /* ����5����������ǿ������ñ߿��Ƿ�ɱ༭�ģ���Щ��ͨ����ѯJDK�õ��� */
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

        } else { //Ĭ�����пؼ����ɻ��Ʊ߿�
            return true;
        }
    }

    /**
     * ��������ı߿��Ƿ�ɻ��ơ�
     * @param component �����
     * @param isBorderPainted Ϊtrue��ʾ����ı߿�ɻ��ƣ�Ϊfalse��ʾ����ı߿򲻿ɻ��ơ�
     */
    private void setBorderPainted(JComponent component, boolean isBorderPainted) {
        /* ����5����������ǿ������ñ߿��Ƿ�ɱ༭�ģ���Щ��ͨ����ѯJDK�õ��� */
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
     * û�б༭��ʱ��ʾ�ı�ǩ�����
     */
    private static final JLabel NO_EDITOR_LABEL = new JLabel("No Support Editor");

    /**
     * �༭��������Ч���ʱ��ʾ�ı�ǩ�����
     */
    private static final JLabel NO_COMPONENT_LABEL = new JLabel("No Support Component");

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

}
