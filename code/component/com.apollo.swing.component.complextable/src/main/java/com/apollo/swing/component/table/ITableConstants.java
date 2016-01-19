/*
 * �˴��봴���� 2008-4-25 ����09:24:14��
 */
package com.apollo.swing.component.table;

import javax.swing.Icon;

/**
 * <p>�ļ����ƣ�ITableConstants.java</p>
 * <p>����������������ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-4-25</p>
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
public interface ITableConstants {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /**
     * ȱʡ���к��п�ȡ�
     */
    public static final int DEFAULT_ROWIDWIDTH = 50;

    /**
     * ȱʡ�Ķ����ļ�����ע�⣺���ļ�������TTableUtilities.class���ڵ���·����Jar����һ����Ŀ¼�С�
     */
    public static final String DEFAULT_CUSTOMFILENAME = "default-tablecustom.xml";

    /**
     * �ͻ�������------�б��ơ�
     */
    public static final String CP_TABLECUSTOM = "TableCustom";

    /**
     * �ͻ�������------ȱʡ�б��ơ�
     */
    public static final String CP_TableDefaultCustom = "TableDefaultCustom";

    /**
     * ���ʻ���Ϣ------�б��ơ�
     */
    public static final String I18N_INFO_TABLECUSTOM = TableUtilities.getI18nMessage("tableCustom");

    /**
     * ���ʻ���Ϣ------�кš�
     */
    public static final String I18N_INFO_ROWID = TableUtilities.getI18nMessage("rowId");

    /**
     * ���ʻ���Ϣ------��ͷ���̶��м��ϣ���
     */
    public static final String I18N_INFO_ROWHEADER = TableUtilities.getI18nMessage("rowHeader");

    /**
     * ���ʻ���Ϣ------���壨���϶��м��ϣ���
     */
    public static final String I18N_INFO_BODY = TableUtilities.getI18nMessage("body");

    /**
     * ���ʻ���Ϣ------������
     */
    public static final String I18N_INFO_COLUMNNAME = TableUtilities.getI18nMessage("columnName");

    /**
     * ���ʻ���Ϣ------��ʾ��
     */
    public static final String I18N_INFO_VISIBLE = TableUtilities.getI18nMessage("visible");

    /**
     * ���ʻ���Ϣ------�п�
     */
    public static final String I18N_INFO_WIDTH = TableUtilities.getI18nMessage("width");

    /**
     * ���ʻ���Ϣ------ȫ���п�
     */
    public static final String I18N_INFO_ALLWIDTH = TableUtilities.getI18nMessage("allWidth");

    /**
     * ���ʻ���Ϣ------��ʾ�С�
     */
    public static final String I18N_INFO_SHOW = TableUtilities.getI18nMessage("show");

    /**
     * ���ʻ���Ϣ------�����С�
     */
    public static final String I18N_INFO_HIDE = TableUtilities.getI18nMessage("hide");

    /**
     * ͼ��------�б��ơ�
     */
    public static final Icon ICON_TABLECUSTOM = TableUtilities.getIcon("table-custom.gif");

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /* Add Public Abstract Method */

}
