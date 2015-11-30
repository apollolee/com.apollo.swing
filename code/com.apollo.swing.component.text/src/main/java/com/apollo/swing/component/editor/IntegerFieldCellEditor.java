/*
 * �˴��봴���� 2008-5-6 ����05:18:17��
 */
package com.apollo.swing.component.editor;

import java.math.BigInteger;

import com.apollo.swing.component.text.IntegerField;

/**
 * <p>�ļ����ƣ�IntegerFieldCellEditor.java</p>
 * <p>���������������������Ԫ��༭���ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-5-6</p>
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
@SuppressWarnings("all")
public class IntegerFieldCellEditor extends FormattedFieldCellEditor {

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

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     */
    public IntegerFieldCellEditor() {
        this(0, true, true, null, null, null, null, null);
    }

    /**
     * ���췽����
     * @param columns ������
     * @param minimum ��Сֵ��
     * @param maximum ���ֵ��
     */
    public IntegerFieldCellEditor(int columns, long minimum, long maximum) {
        this(columns, true, true, BigInteger.valueOf(minimum), BigInteger.valueOf(maximum), null, null, null);
    }

    /**
     * ���췽����
     * @param columns ������
     * @param minimum ��Сֵ��
     * @param maximum ���ֵ��
     * @param value ��ʼ���õ�������ֵ��
     */
    public IntegerFieldCellEditor(int columns, long minimum, long maximum, long value) {
        this(columns, true, true, BigInteger.valueOf(minimum), BigInteger.valueOf(maximum), null, null, BigInteger.valueOf(value));
    }

    /**
     * ���췽����
     * @param columns ������
     * @param minimum ��Сֵ��
     * @param maximum ���ֵ��
     * @param step ������
     * @param value ��ʼ���õ�������ֵ��
     */
    public IntegerFieldCellEditor(int columns, long minimum, long maximum, long step, long value) {
        this(columns,
             true,
             true,
             BigInteger.valueOf(minimum),
             BigInteger.valueOf(maximum),
             BigInteger.valueOf(step),
             null,
             BigInteger.valueOf(value));
    }

    /**
     * ���췽����
     * @param columns ������
     * @param minimum ��Сֵ��
     * @param maximum ���ֵ��
     * @param step ������
     * @param stepBase ������ֵ��
     * @param value ��ʼ���õ�������ֵ��
     */
    public IntegerFieldCellEditor(int columns, long minimum, long maximum, long step, long stepBase, long value) {
        this(columns,
             true,
             true,
             BigInteger.valueOf(minimum),
             BigInteger.valueOf(maximum),
             BigInteger.valueOf(step),
             BigInteger.valueOf(stepBase),
             BigInteger.valueOf(value));
    }

    /**
     * ���췽����
     * @param columns ������
     * @param isNeedInput Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param minimum ��Сֵ��
     * @param maximum ���ֵ��
     * @param step ������
     * @param stepBase ������ֵ��
     * @param value ��ʼ���õ�������ֵ��
     */
    public IntegerFieldCellEditor(int columns,
                                  boolean isNeedInput,
                                  boolean isEnabledPopupErrorTip,
                                  long minimum,
                                  long maximum,
                                  long step,
                                  long stepBase,
                                  long value) {
        this(columns,
             isNeedInput,
             isEnabledPopupErrorTip,
             BigInteger.valueOf(minimum),
             BigInteger.valueOf(maximum),
             BigInteger.valueOf(step),
             BigInteger.valueOf(stepBase),
             BigInteger.valueOf(value));
    }

    /**
     * ���췽����
     * @param columns ������
     * @param isNeedInput Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param minimum ��Сֵ��
     * @param maximum ���ֵ��
     * @param step ������
     * @param stepBase ������ֵ��
     * @param value ��ʼ���õ�������ֵ��
     */
    public IntegerFieldCellEditor(int columns,
                                  boolean isNeedInput,
                                  boolean isEnabledPopupErrorTip,
                                  BigInteger minimum,
                                  BigInteger maximum,
                                  BigInteger step,
                                  BigInteger stepBase,
                                  BigInteger value) {
        this(new IntegerField(columns, isNeedInput, isEnabledPopupErrorTip, minimum, maximum, step, stepBase, value));
    }

    /**
     * ���췽����
     * @param field �����������
     */
    public IntegerFieldCellEditor(IntegerField field) {
        super(field);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
