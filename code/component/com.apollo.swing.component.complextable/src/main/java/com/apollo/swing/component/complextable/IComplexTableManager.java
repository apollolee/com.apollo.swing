/*
 * �˴��봴���� 2008-5-11 ����01:59:06��
 */
package com.apollo.swing.component.complextable;

/**
 * <p>�ļ����ƣ�IComplexTableManager.java</p>
 * <p>�����������ۺϱ�������ӿڣ���Ҫ����һЩ������ۺ���չ���ܣ������ɿ��ܽ�һ������ӿڡ�</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-5-11</p>
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
public interface IComplexTableManager {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /* Add Public Static Final Field */

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /**
     * <p>�ۺϱ�˵����������������У������е������ݳ�ʼ����ͨ����������ص����ɳ�����</p>
     * <p>ע�⣺�����ʵ�����������������������У���ȱʡ��ÿ�����ݶ��ǿ��ַ������ͣ��������п��ܷ���ģ�Ͳ�valueֵת���쳣�ġ�</p>
     * @param table ��Ҫ�����е��ۺϱ����
     * @param index �µ������ڵ�������
     * @return �����ݶ���
     * @since T3 V1.1
     */
    public abstract ComplexTableRow createRow(ComplexTable table, int index);

}
