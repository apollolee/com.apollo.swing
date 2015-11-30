/*
 * �˴��봴���� 2009-9-14 ����02:37:36��
 */
package com.apollo.swing.component.treetable;

import java.awt.Color;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * <p>�ļ����ƣ�TreeTableCell.java</p>
 * <p>��������������Ԫ���࣬��װ�������еĵ�Ԫ�����ݣ�ע�⣺����Ԫ����⡣</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2009-9-14</p>
 * <p>�޸ļ�¼��</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * @version 1.0
 * @author ����
 * @since T3 V1.1
 */
public class TreeTableCell {

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

    /* Add Protected Field */

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /**
     * ֵ��
     */
    private Object m_value;

    /**
     * ��ʶ�Ƿ�ɱ༭��
     */
    private volatile boolean m_editable;

    /**
     * ǰ��ɫ��
     */
    private Color m_foreground = null;

    /**
     * ����ɫ��
     */
    private Color m_background = null;

    /**
     * ��Ⱦ����
     */
    private TableCellRenderer m_renderer;

    /**
     * �༭����
     */
    private TableCellEditor m_editor;

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param value ֵ����
     * @since T3 V1.1
     */
    public TreeTableCell(Object value) {
        this(value, false, null, null);
    }

    /**
     * ���췽����
     * @param value ֵ����
     * @param editable ��ʶ��Ԫ���Ƿ�����༭��
     * @param renderer ��Ԫ����Ⱦ����
     * @param editor ��Ԫ��༭����
     * @since T3 V1.1
     */
    public TreeTableCell(Object value, boolean editable, TableCellRenderer renderer, TableCellEditor editor) {
        m_value = value;
        m_editable = editable;
        m_renderer = renderer;
        m_editor = editor;
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ�ֶ� value ��ֵ��ֵ����
     * @return �ֶ� value ��ֵ��
     * @since T3 V1.1
     */
    public Object getValue() {
        return m_value;
    }

    /**
     * �����ֶ� value ��ֵ��ֵ����
     * @param value �ֶ� value ��ֵ��
     * @since T3 V1.1
     */
    public void setValue(Object value) {
        m_value = value;
    }

    /**
     * ��ȡ�ֶ� editable ��ֵ����ʶ��Ԫ���Ƿ�����༭��
     * @return �ֶ� editable ��ֵ��
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_editable;
    }

    /**
     * �����ֶ� editable ��ֵ����ʶ��Ԫ���Ƿ�����༭��
     * @param editable �ֶ� editable ��ֵ��
     * @since T3 V1.1
     */
    public void setEditable(boolean editable) {
        m_editable = editable;
    }

    /**
     * ��ȡ�ֶ� foreground ��ֵ����Ԫ���ǰ��ɫ��
     * @return �ֶ� foreground ��ֵ��
     * @since T3 V1.1
     */
    public Color getForeground() {
        return m_foreground;
    }

    /**
     * �����ֶ� foreground ��ֵ����Ԫ���ǰ����ɫ��
     * @param foreground �ֶ� foreground ��ֵ��
     * @since T3 V1.1
     */
    public void setForeground(Color foreground) {
        m_foreground = foreground;
    }

    /**
     * ��ȡ�ֶ� background ��ֵ����Ԫ��ı���ɫ��ע�⣺�������ɸ��ݵ�Ԫ���Ƿ�ɱ༭���ı䵥Ԫ����ʾ�ı���ɫ�����ɱ༭��Ԫ��ı���ɫΪǳ��ɫ���������Ԫ�����Լ�����Ⱦ���򱳾�ɫ�����Ե�Ԫ�����õ���ȾɫΪ׼��
     * @return �ֶ� background ��ֵ��
     * @since T3 V1.1
     */
    public Color getBackground() {
        return m_background;
    }

    /**
     * �����ֶ� background ��ֵ����Ԫ��ı���ɫ��ע�⣺�������ɸ��ݵ�Ԫ���Ƿ�ɱ༭���ı䵥Ԫ����ʾ�ı���ɫ�����ɱ༭��Ԫ��ı���ɫΪǳ��ɫ���������Ԫ�����Լ�����Ⱦ���򱳾�ɫ�����Ե�Ԫ�����õ���ȾɫΪ׼��
     * @param background �ֶ� background ��ֵ��
     * @since T3 V1.1
     */
    public void setBackground(Color background) {
        m_background = background;
    }

    /**
     * ��ȡ�ֶ� renderer ��ֵ����Ԫ�����Ⱦ����
     * @return �ֶ� renderer ��ֵ��
     * @since T3 V1.1
     */
    public TableCellRenderer getRenderer() {
        return m_renderer;
    }

    /**
     * �����ֶ� renderer ��ֵ����Ԫ�����Ⱦ����
     * @param renderer �ֶ� renderer ��ֵ��
     * @since T3 V1.1
     */
    public void setRenderer(TableCellRenderer renderer) {
        m_renderer = renderer;
    }

    /**
     * ��ȡ�ֶ� editor ��ֵ����Ԫ��ı༭����
     * @return �ֶ� editor ��ֵ��
     * @since T3 V1.1
     */
    public TableCellEditor getEditor() {
        return m_editor;
    }

    /**
     * �����ֶ� editor ��ֵ����Ԫ��ı༭����
     * @param editor �ֶ� editor ��ֵ��
     * @since T3 V1.1
     */
    public void setEditor(TableCellEditor editor) {
        m_editor = editor;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
