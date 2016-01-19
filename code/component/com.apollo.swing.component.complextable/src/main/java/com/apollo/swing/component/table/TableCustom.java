/*
 * �˴��봴���� 2008-5-5 ����04:13:40��
 */
package com.apollo.swing.component.table;

import java.math.BigInteger;

import javax.swing.table.TableColumn;

/**
 * <p>�ļ����ƣ�TableCustom.java</p>
 * <p>�����������б�����Ϣ�ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-5-5</p>
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
public class TableCustom {

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
     * �ж���
     */
    protected TableColumn m_column;

    /**
     * ��ʶ���Ƿ�ɼ���
     */
    protected Boolean m_visible;

    /**
     * �п�
     */
    protected BigInteger m_width;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param column �ж���
     * @param visible ��ʶ���Ƿ�ɼ���
     */
    public TableCustom(TableColumn column, boolean visible) {
        this(column, new Boolean(visible));
    }

    /**
     * ���췽����
     * @param column �ж���
     * @param visible ��ʶ���Ƿ�ɼ���
     */
    public TableCustom(TableColumn column, Boolean visible) {
        m_column = column;
        m_visible = visible;

        syncWidthFromColumn();
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    protected TableCustom(TableCustom custom) {
        m_column = custom.m_column;
        m_visible = Boolean.valueOf(custom.m_visible.booleanValue());
        m_width = BigInteger.valueOf(custom.m_width.longValue());
    }

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ�ֶ� column ��ֵ��
     * @return �ֶ� column ��ֵ��
     */
    public TableColumn getColumn() {
        return m_column;
    }

    /**
     * ��ȡ�ֶ� visible ��ֵ��
     * @return �ֶ� visible ��ֵ��
     */
    public boolean isVisible() {
        return m_visible.booleanValue();
    }

    public void setVisible(Boolean visible) {
        m_visible = visible;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * ���ж���ͬ���п�
     */
    protected void syncWidthFromColumn() {
        m_width = BigInteger.valueOf(m_column.getWidth());
    }

    /**
     * ͬ���п��ж���
     */
    protected void syncWidthToColumn() {
        m_column.setPreferredWidth(m_width.intValue());
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
