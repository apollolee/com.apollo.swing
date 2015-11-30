/*
 * �˴��봴���� 2007-9-12 ����05:50:27
 */
package com.apollo.swing.component.complextree;

/**
 * <p>�ļ����ƣ�IComplexTreeDataSource.java</p>
 * <p>�����������ۺ�������Դ�ӿڣ���Ҫ������ڵ��ȡ���ݵĹ��ܡ�</p>
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
public interface IComplexTreeDataSource {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /* Add Public Static Final Field */

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /**
     * ����ָ��������ڵ�������ӽڵ㣬ע�⣺�˻ص��������ڷ��¼��ɷ��߳��е��ã��Է���Ӧ��ֱ��������������·����
     * @param parent ָ���ĸ�����ڵ㣬Ҳ������Ҫ�����ӽڵ�Ľڵ㡣
     * @return ָ��������ڵ�������ӽڵ㡣
     * @throws ComplexTreeException �������������з����������׳����쳣��
     * @since T3 V1.1
     */
    public abstract ComplexTreeNode[] load(ComplexTreeCacheNode parent) throws ComplexTreeException;

}
