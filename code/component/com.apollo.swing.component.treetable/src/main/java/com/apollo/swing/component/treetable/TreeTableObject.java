/*
 * �˴��봴���� 2009-9-16 ����10:14:36��
 */
package com.apollo.swing.component.treetable;

import java.awt.Color;
import java.util.List;

/**
 * <p>�ļ����ƣ�TreeTableObject.java</p>
 * <p>������������������࣬�������Ƿ�װ���ۺ����ڵ��userObject���������жԵ�Ԫ���б�ķ�װ��</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2009-9-16</p>
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
@SuppressWarnings("all")
public class TreeTableObject {

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
     * �ڵ�ǰ��ɫ��
     */
    private Color m_NodeForeground = null;

    /**
     * �ڵ㱳��ɫ��
     */
    private Color m_NodeBackground = null;

    /**
     * ��Ԫ�񼯡�
     */
    private List m_cells;

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param value ֵ�������ֵ���������ۺ����ڵ����ʾ���൱���ۺ����ڵ��userObject��ֻ��������װ�ø����ˡ�
     * @since T3 V1.1
     */
    public TreeTableObject(Object value) {
        this(value, null);
    }

    /**
     * ���췽����
     * @param value ֵ�������ֵ���������ۺ����ڵ����ʾ���൱���ۺ����ڵ��userObject��ֻ��������װ�ø����ˡ�
     * @param cells ��Ԫ�񼯣�����ĵ�Ԫ�񼯵ĳ��Ȳ�һ��Ҫ����һ����������Ӧ����û�е�Ԫ�����ʱ����Ӧ�ĵ�Ԫ��ֵ�ǿ��ַ��������ɱ༭������ʹ��ȱʡ��Ⱦ��
     * @since T3 V1.1
     */
    public TreeTableObject(Object value, List cells) {
        this(value, true, cells);
    }

    /**
     * ���췽����
     * @param value ֵ�������ֵ���������ۺ����ڵ����ʾ���൱���ۺ����ڵ��userObject��ֻ��������װ�ø����ˡ�
     * @param editable ��ʶ����ڵ������ĵ�Ԫ���Ƿ�����༭��ע�⣺���ﲻ�Ǳ�ʶ���ڵ��Ƿ�����༭�ģ������еĽڵ��ǲ�����༭�ġ�
     * @param cells ��Ԫ�񼯣�����ĵ�Ԫ�񼯵ĳ��Ȳ�һ��Ҫ����һ����������Ӧ����û�е�Ԫ�����ʱ����Ӧ�ĵ�Ԫ��ֵ�ǿ��ַ��������ɱ༭������ʹ��ȱʡ��Ⱦ��
     * @since T3 V1.1
     */
    public TreeTableObject(Object value, boolean editable, List cells) {
        m_value = value;
        m_editable = editable;
        m_cells = cells;
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ�ֶ� value ��ֵ��ֵ�������ֵ���������ۺ����ڵ����ʾ���൱���ۺ����ڵ��userObject��ֻ��������װ�ø����ˡ�
     * @return �ֶ� value ��ֵ��
     * @since T3 V1.1
     */
    public Object getValue() {
        return m_value;
    }

    /**
     * �����ֶ� value ��ֵ��ֵ�������ֵ���������ۺ����ڵ����ʾ���൱���ۺ����ڵ��userObject��ֻ��������װ�ø����ˡ�
     * @param value �ֶ� value ��ֵ��
     * @since T3 V1.1
     */
    public void setValue(Object value) {
        m_value = value;
    }

    /**
     * ��ȡ�ֶ� editable ��ֵ����ʶ����ڵ������ĵ�Ԫ���Ƿ�����༭��ע�⣺���ﲻ�Ǳ�ʶ���ڵ��Ƿ�����༭�ģ������еĽڵ��ǲ�����༭�ġ�
     * @return �ֶ� editable ��ֵ��
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_editable;
    }

    /**
     * �����ֶ� editable ��ֵ����ʶ����ڵ������ĵ�Ԫ���Ƿ�����༭��ע�⣺���ﲻ�Ǳ�ʶ���ڵ��Ƿ�����༭�ģ������еĽڵ��ǲ�����༭�ġ�
     * @param editable �ֶ� editable ��ֵ��
     * @since T3 V1.1
     */
    public void setEditable(boolean editable) {
        m_editable = editable;
    }

    /**
     * ��ȡ�ֶ� foreground ��ֵ������ڵ������ĵ�Ԫ���ǰ��ɫ��ע�⣺���ﲻ��ָ���ڵ��ǰ��ɫ��
     * @return �ֶ� foreground ��ֵ��
     * @since T3 V1.1
     */
    public Color getForeground() {
        return m_foreground;
    }

    /**
     * �����ֶ� foreground ��ֵ������ڵ������ĵ�Ԫ���ǰ��ɫ��ע�⣺���ﲻ��ָ���ڵ��ǰ��ɫ��
     * @param foreground �ֶ� foreground ��ֵ��
     * @since T3 V1.1
     */
    public void setForeground(Color foreground) {
        m_foreground = foreground;
    }

    /**
     * ��ȡ�ֶ� background ��ֵ������ڵ������ĵ�Ԫ��ı���ɫ��ע�⣺���ﲻ��ָ���ڵ�ı���ɫ��
     * @return �ֶ� background ��ֵ��
     * @since T3 V1.1
     */
    public Color getBackground() {
        return m_background;
    }

    /**
     * �����ֶ� background ��ֵ������ڵ������ĵ�Ԫ��ı���ɫ��ע�⣺���ﲻ��ָ���ڵ�ı���ɫ��
     * @param background �ֶ� background ��ֵ��
     * @since T3 V1.1
     */
    public void setBackground(Color background) {
        m_background = background;
    }

    /**
     * ��ȡ�ֶ� nodeForeground ��ֵ�����ڵ�ǰ��ɫ��
     * @return �ֶ� nodeForeground ��ֵ��
     * @since T3 V1.1
     */
    public Color getNodeForeground() {
        return m_NodeForeground;
    }

    /**
     * �����ֶ� nodeForeground ��ֵ�����ڵ�ǰ��ɫ��
     * @param nodeForeground �ֶ� nodeForeground ��ֵ��
     * @since T3 V1.1
     */
    public void setNodeForeground(Color nodeForeground) {
        m_NodeForeground = nodeForeground;
    }

    /**
     * ��ȡ�ֶ� nodeBackground ��ֵ�����ڵ㱳��ɫ��
     * @return �ֶ� nodeBackground ��ֵ��
     * @since T3 V1.1
     */
    public Color getNodeBackground() {
        return m_NodeBackground;
    }

    /**
     * �����ֶ� nodeBackground ��ֵ�����ڵ㱳��ɫ��
     * @param nodeBackground �ֶ� nodeBackground ��ֵ��
     * @since T3 V1.1
     */
    public void setNodeBackground(Color nodeBackground) {
        m_NodeBackground = nodeBackground;
    }

    /**
     * ��ȡ�ֶ� cells ��ֵ����Ԫ���б�ע�⣺������ģ���нڵ��б�����ã�Ӧ��ģ��������ά������б������һ���Ժ������ԡ�
     * @return �ֶ� cells ��ֵ��
     * @since T3 V1.1
     */
    public List getCells() {
        return m_cells;
    }

    /**
     * �����ֶ� cells ��ֵ����Ԫ���б�ע�⣺������ģ���нڵ��б�����ã�Ӧ��ģ��������ά������б������һ���Ժ������ԡ�
     * @param cells �ֶ� cells ��ֵ��
     * @since T3 V1.1
     */
    public void setCells(List cells) {
        m_cells = cells;
    }

    /*------------------------------------- Object Public Method -------------------------------------*/

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getValue().toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        return getValue().equals(((TreeTableObject) obj).getValue());
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
