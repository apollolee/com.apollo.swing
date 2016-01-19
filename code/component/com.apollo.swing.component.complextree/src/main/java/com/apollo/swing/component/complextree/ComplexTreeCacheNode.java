/*
 * �˴��봴���� 2007-9-12 ����05:36:44
 */
package com.apollo.swing.component.complextree;

import javax.swing.Icon;

import com.apollo.swing.component.common.jtree.SingleIconTreeCellRenderer;

/**
 * <p>�ļ����ƣ�ComplexTreeCacheNode.java</p>
 * <p>�ļ��������ۺ�������ڵ��࣬��װ��������Ľڵ�Ĳ��������ݡ�</p>
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
public class ComplexTreeCacheNode extends ComplexTreeChoiceNode {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /* Add Public (Static) Inner Class */

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    /* Add Protected (Static) Inner Class */

    /*------------------------------------- Friendly (Static) Inner Class -------------------------------------*/

    /* Add Friendly (Static) Inner Class */

    /*------------------------------------- Private (Static) Inner Class -------------------------------------*/

    /* Add Private (Static) Inner Class */

    /*------------------------------------- Public Static Field -------------------------------------*/

    /**
     * ���ʻ���Ϣ------�����С�
     * @since T3 V1.1
     */
    public static final String I18N_INFO_LOADING = Utilities.getI18nMessage("loading");

    /**
     * ͼ��------�����С�
     * @since T3 V1.1
     */
    public static final Icon ICON_LOADING = Utilities.getIcon("loading.gif");

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
     * �����нڵ㡣
     */
    protected ComplexTreeNode m_loadingNode = new ComplexTreeNode(I18N_INFO_LOADING);

    /**
     * ״̬��
     */
    protected volatile int m_state = STATE_READY;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param userObject �û�����
     * @since T3 V1.1
     */
    public ComplexTreeCacheNode(Object userObject) {
        this(userObject, true);
    }

    /**
     * ���췽����
     * @param userObject �û�����
     * @param allowsChildren ��ʶ�Ƿ�����ýڵ�ӵ���ӽڵ㡣
     * @since T3 V1.1
     */
    public ComplexTreeCacheNode(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);

        m_loadingNode.setRenderer(new SingleIconTreeCellRenderer(ICON_LOADING));
        add(m_loadingNode);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * �жϽڵ��Ƿ���׼��״̬��׼��״̬������ڵ��»�û�������ӽڵ��״̬��
     * @return ��ʶ�ڵ��Ƿ���׼��״̬��
     * @since T3 V1.1
     */
    public synchronized boolean isReady() {
        return m_state == STATE_READY;
    }

    /**
     * �жϽڵ��Ƿ���������״̬��������״̬������ڵ����������ӽڵ��״̬��
     * @return ��ʶ�ڵ��Ƿ���������״̬��
     * @since T3 V1.1
     */
    public synchronized boolean isLoading() {
        return m_state == STATE_LOADING;
    }

    /**
     * �жϽڵ��Ƿ���������״̬��������״̬������ڵ��Ѿ��������ӽڵ��״̬��
     * @return ��ʶ�ڵ��Ƿ���������״̬��
     * @since T3 V1.1
     */
    public synchronized boolean isLoaded() {
        return m_state == STATE_LOADED;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * �ж��Ƿ��Ѿ�ӵ���������нڵ㡣
     * @return ��ʶ�Ƿ��Ѿ�ӵ���������нڵ㡣
     */
    protected boolean hasLoadingNode() {
        return getChildCount() == 1 ? (getChildAt(0) == m_loadingNode) : false;
    }

    /**
     * ���������нڵ㡣
     * @return ��ʶ�Ƿ�ɹ������������нڵ㡣
     */
    protected boolean setLoadingNode() {
        if (hasLoadingNode()) {
            return false;

        } else {
            removeAllChildren();
            add(m_loadingNode);
            return true;
        }
    }

    /**
     * ����׼��״̬��
     * @return ��ʶ�Ƿ�ɹ������������нڵ㡣
     */
    protected synchronized boolean ready() {
        m_state = STATE_READY;
        return setLoadingNode();
    }

    /**
     * ����������״̬��
     * @return ��ʶ�Ƿ�ɹ������������нڵ㡣
     */
    protected synchronized boolean loading() {
        m_state = STATE_LOADING;
        return setLoadingNode();
    }

    /**
     * ����������״̬��
     * @param loadedNodes ������Ľڵ㼯��
     */
    protected synchronized void loaded(ComplexTreeNode[] loadedNodes) {
        removeAllChildren();

        if (loadedNodes != null) {
            for (int i = 0; i < loadedNodes.length; i++) {
                add(loadedNodes[i]);
            }
        }

        m_state = STATE_LOADED;
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
