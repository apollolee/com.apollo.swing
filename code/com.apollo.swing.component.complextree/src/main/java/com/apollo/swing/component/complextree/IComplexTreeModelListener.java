/*
 * �˴��봴���� 2008-3-19 ����09:35:49��
 */
package com.apollo.swing.component.complextree;

import java.util.EventListener;

/**
 * <p>�ļ����ƣ�IComplexTreeModelListener.java</p>
 * <p>�����������ۺ���ģ�ͼ������ӿڣ������ۺϱ�ģ���Ϸ����������¼���</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-3-19</p>
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
public interface IComplexTreeModelListener extends EventListener {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /* Add Public Static Final Field */

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /**
     * �ڵ�ѡ���Ѹı䣬�нڵ����̬ѡ��״̬���������������¼���
     * @param evt �ۺ���ģ���¼���
     * @since T3 V1.1
     */
    public abstract void nodeChoiceChanged(ComplexTreeModelEvent evt);

    /**
     * �ڵ㼴�����룬����ڵ��������ӽڵ��ǰһ�̲������¼���
     * @param evt �ۺ���ģ���¼���
     * @since T3 V1.1
     */
    public abstract void nodeWillLoad(ComplexTreeModelEvent evt);

    /**
     * �ڵ������룬����ڵ��������ӽڵ��������¼���
     * @param evt �ۺ���ģ���¼���
     * @since T3 V1.1
     */
    public abstract void nodeLoaded(ComplexTreeModelEvent evt);

    /**
     * �ڵ�������ʧ�ܣ�����ڵ��������ӽڵ�ʧ�ܺ�������¼���
     * @param evt �ۺ���ģ���¼���
     * @since T3 V1.1
     */
    public abstract void nodeLoadFailed(ComplexTreeModelEvent evt);

    /**
     * �ͷŻ��棬�����ͷŻ���Ķ�������ʱ�������¼���һ��Ӧ�ò���Ҫ��������¼��ķ�����
     * @param evt �ۺ���ģ���¼���
     * @since T3 V1.1
     */
    public abstract void releaseCache(ComplexTreeModelEvent evt);

}
