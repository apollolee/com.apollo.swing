/*
 * �˴��봴���� 2007-9-12 ����05:36:18
 */
package com.apollo.swing.component.complextree;

import javax.swing.tree.TreePath;

/**
 * <p>�ļ����ƣ�ComplexTreeChoiceNode.java</p>
 * <p>�ļ��������ۺ���ѡ��ڵ��࣬��װ��������̬ѡ��Ľڵ�Ĳ��������ݡ�</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004</p>
 * <p>������˾��������</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2007-9-12</p>
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
public class ComplexTreeChoiceNode extends ComplexTreeNode {

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
     * ѡ��״̬------��ѡ��
     */
    private static final byte STATE_SELECTED = 0;

    /**
     * ѡ��״̬------δѡ��
     */
    private static final byte STATE_DESELECTED = 1;

    /**
     * ѡ��״̬------��ȷ������ѡ����
     */
    private static final byte STATE_INDETERMINATE = 2;

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
     * ѡ��״̬��
     */
    private volatile byte m_state = STATE_DESELECTED;

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param userObject �û�����
     * @since T3 V1.1
     */
    public ComplexTreeChoiceNode(Object userObject) {
        this(userObject, true);
    }

    /**
     * ���췽����
     * @param userObject �û�����
     * @param allowsChildren ��ʶ�Ƿ�����ýڵ�ӵ���ӽڵ㡣
     * @since T3 V1.1
     */
    public ComplexTreeChoiceNode(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡѡ���õ�·�������Ӵ˽ڵ㵽���ڵ��·����ע�⣺·���������ÿ��ֵ���ǽڵ���󣬶��ǽڵ����������userObject����
     * @return ѡ���õ�·����
     * @since T3 V1.1
     */
    public TreePath getChosenPath() {
        return new TreePath(getUserObjectPath());
    }

    /**
     * �ж��Ƿ�Ϊ��ѡ��״̬���������ϴ˽ڵ�ǰ���ѡ����Ƿ��ڹ�ѡ״̬��
     * @return ��ʶ�Ƿ�Ϊ��ѡ��״̬��
     * @since T3 V1.1
     */
    public boolean isSelected() {
        return m_state == STATE_SELECTED;
    }

    /**
     * ����Ϊ��ѡ��״̬���������ϴ˽ڵ�ǰ���ѡ����Ƿ��ڹ�ѡ״̬��
     * @since T3 V1.1
     */
    public void setSelected() {
        m_state = STATE_SELECTED;
    }

    /**
     * �ж��Ƿ�Ϊδѡ��״̬���������ϴ˽ڵ�ǰ���ѡ����Ƿ���δ��ѡ״̬��
     * @return ��ʶ�Ƿ�Ϊδѡ��״̬��
     * @since T3 V1.1
     */
    public boolean isDeselected() {
        return m_state == STATE_DESELECTED;
    }

    /**
     * ����Ϊδѡ��״̬���������ϴ˽ڵ�ǰ���ѡ����Ƿ���δ��ѡ״̬��
     * @since T3 V1.1
     */
    public void setDeselected() {
        m_state = STATE_DESELECTED;
    }

    /**
     * �ж��Ƿ�Ϊ��ȷ��״̬���������ϴ˽ڵ�ǰ���ѡ����Ƿ��ڰ빴ѡ״̬��
     * @return ��ʶ�Ƿ�Ϊ��ȷ��״̬��
     * @since T3 V1.1
     */
    public boolean isIndetermined() {
        return m_state == STATE_INDETERMINATE;
    }

    /**
     * ����Ϊ��ȷ��״̬���������ϴ˽ڵ�ǰ���ѡ����Ƿ��ڰ빴ѡ״̬��
     * @since T3 V1.1
     */
    public void setIndetermined() {
        m_state = STATE_INDETERMINATE;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
