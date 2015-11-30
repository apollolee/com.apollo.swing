/*
 * �˴��봴���� 2008-3-19 ����09:37:35��
 */
package com.apollo.swing.component.complextree;

import java.util.EventObject;
import java.util.List;

import javax.swing.tree.TreePath;

/**
 * <p>�ļ����ƣ�ComplexTreeModelEvent.java</p>
 * <p>�����������ۺ���ģ���¼��࣬��װ���ۺ����������¼���</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-3-20</p>
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
public class ComplexTreeModelEvent extends EventObject {

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
     * ·������
     */
    protected TreePath[] m_paths = null;

    /**
     * ��ʶ�Ƿ�ѡ��
     */
    protected volatile boolean m_isSelected = true;

    /**
     * �쳣��
     */
    protected ComplexTreeException m_exception = null;

    /**
     * ���漯��
     */
    protected List m_caches = null;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /* Add Public Constructor */

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param source �¼�Դ��
     */
    protected ComplexTreeModelEvent(Object source) {
        super(source);
    }

    /**
     * ���췽����
     * @param source �¼�Դ��
     * @param path ·����ע�⣺·���������ÿ��ֵ���ǽڵ���󣬶��ǽڵ����������userObject����
     * @param isSelected ��ʶ�Ƿ�ѡ��
     */
    protected ComplexTreeModelEvent(Object source, TreePath path, boolean isSelected) {
        this(source, new TreePath[] { path }, isSelected);
    }

    /**
     * ���췽����
     * @param source �¼�Դ��
     * @param paths ·������ע�⣺·���������ÿ��ֵ���ǽڵ���󣬶��ǽڵ����������userObject����
     * @param isSelected ��ʶ�Ƿ�ѡ��
     */
    protected ComplexTreeModelEvent(Object source, TreePath[] paths, boolean isSelected) {
        super(source);
        m_paths = paths;
        m_isSelected = isSelected;
    }

    /**
     * ���췽����
     * @param source �¼�Դ��
     * @param exception �쳣��
     */
    protected ComplexTreeModelEvent(Object source, ComplexTreeException exception) {
        super(source);
        m_exception = exception;
    }

    /**
     * ���췽����
     * @param source �¼�Դ��
     * @param caches ���漯������ǰ����������״̬�Ļ���ڵ���б�
     */
    protected ComplexTreeModelEvent(Object source, List caches) {
        super(source);
        m_caches = caches;
    }

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ·������ע�⣺·���������ÿ��ֵ���ǽڵ���󣬶��ǽڵ����������userObject����
     * @return ·������
     * @since T3 V1.1
     */
    public TreePath[] getPahts() {
        return m_paths;
    }

    /**
     * �ж��Ƿ�ѡ��
     * @return ��ʶ�Ƿ�ѡ��
     * @since T3 V1.1
     */
    public boolean isSelected() {
        return m_isSelected;
    }

    /**
     * ��ȡ�쳣��
     * @return �쳣��
     * @since T3 V1.1
     */
    public ComplexTreeException getException() {
        return m_exception;
    }

    /**
     * ��ȡ���漯������ǰ����������״̬�Ļ���ڵ���б�
     * @return ���漯��
     * @since T3 V1.1
     */
    public List getCaches() {
        return m_caches;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
