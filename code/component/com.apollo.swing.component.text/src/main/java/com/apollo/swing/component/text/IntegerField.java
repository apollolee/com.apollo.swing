/*
 * �˴��봴���� 2008-3-2 ����03:36:52��
 */
package com.apollo.swing.component.text;

import java.math.BigInteger;

/**
 * <p>�ļ����ƣ�IntegerField.java</p>
 * <p>���������������������ע�⣺���������JFormattedTextField�����Ӧ����Ӧ�������˽�JFormattedTextField������÷���</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-3-2</p>
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
public class IntegerField extends BaseField {

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
     * @since T3 V1.1
     */
    public IntegerField() {
        this(0, true, true, null, null, null, null, null);
    }

    /**
     * ���췽����
     * @param columns ���������������JText���Ǹ�һ������˼��
     * @param minimum �����������Сֵ��
     * @param maximum ������������ֵ��
     * @since T3 V1.1
     */
    public IntegerField(int columns, long minimum, long maximum) {
        this(columns, true, true, BigInteger.valueOf(minimum), BigInteger.valueOf(maximum), null, null, null);
    }

    /**
     * ���췽����
     * @param columns ���������������JText���Ǹ�һ������˼��
     * @param minimum �����������Сֵ��
     * @param maximum ������������ֵ��
     * @param value ��ʼ���õ�������ֵ��
     * @since T3 V1.1
     */
    public IntegerField(int columns, long minimum, long maximum, long value) {
        this(columns, true, true, BigInteger.valueOf(minimum), BigInteger.valueOf(maximum), null, null, BigInteger.valueOf(value));
    }

    /**
     * ���췽����
     * @param columns ���������������JText���Ǹ�һ������˼��
     * @param minimum �����������Сֵ��
     * @param maximum ������������ֵ��
     * @param step ���������Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ��������������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param value ��ʼ���õ�������ֵ��
     * @since T3 V1.1
     */
    public IntegerField(int columns, long minimum, long maximum, long step, long value) {
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
     * @param columns ���������������JText���Ǹ�һ������˼��
     * @param minimum �����������Сֵ��
     * @param maximum ������������ֵ��
     * @param step ���������Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ��������������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param stepBase ������ֵ�����Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ��������������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param value ��ʼ���õ�������ֵ��
     * @since T3 V1.1
     */
    public IntegerField(int columns, long minimum, long maximum, long step, long stepBase, long value) {
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
     * @param columns ���������������JText���Ǹ�һ������˼��
     * @param isNeedInput ������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip �����벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param minimum �����������Сֵ��
     * @param maximum ������������ֵ��
     * @param step ���������Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ��������������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param stepBase ������ֵ�����Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ��������������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param value ��ʼ���õ�������ֵ��
     * @since T3 V1.1
     */
    public IntegerField(int columns,
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
     * @param columns ���������������JText���Ǹ�һ������˼��
     * @param isNeedInput ������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip �����벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param minimum �����������Сֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip���ø������ʾ��
     * @param maximum ������������ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param step ���������Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param stepBase ������ֵ�����Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param value ��ʼ���õ�������ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#TIntegerFormatter(boolean, boolean, BigInteger, BigInteger, BigInteger)
     * @since T3 V1.1
     */
    public IntegerField(int columns,
                        boolean isNeedInput,
                        boolean isEnabledPopupErrorTip,
                        BigInteger minimum,
                        BigInteger maximum,
                        BigInteger step,
                        BigInteger stepBase,
                        BigInteger value) {
        super(new IntegerFormatter(isNeedInput, isEnabledPopupErrorTip, minimum, maximum, step, stepBase));

        setColumns(columns);
        setInteger(value);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ������ע�⣺�˴���ȡ��ֵ�������ǽ����ϵ�ֵ��ֻ��commitEdit�Ժ󣬽����ϵ�ֵ�Ż����ģ�ͣ�����JFormattedTextField��ȫһ����
     * @return ���������Ϊnull��ʾδ���������Ϊ�ա�
     * @since T3 V1.1
     */
    public BigInteger getInteger() {
        return (BigInteger) getValue();
    }

    /**
     * ����������ע�⣺Ӧ�ñ��뱣֤���õ������ǺϷ��ġ�
     * @param integer ������Ϊnull��ʾ����Ϊ�ա�
     * @since T3 V1.1
     */
    public void setInteger(BigInteger integer) {
        setValue(integer);
    }

    /**
     * ����������ע�⣺Ӧ�ñ��뱣֤���õ������ǺϷ��ġ�
     * @param integer ������
     * @since T3 V1.1
     */
    public void setInteger(long integer) {
        setInteger(BigInteger.valueOf(integer));
    }

    /**
     * ��ȡ������ʽ����
     * @return ������ʽ����
     * @since T3 V1.1
     */
    public IntegerFormatter getIntegerFormatter() {
        return (IntegerFormatter) getFormatter();
    }

    /**
     * ��ȡ�ֶ� isNeedInput ��ֵ��������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @return �ֶ� isNeedInput ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#isNeedInput()
     * @since T3 V1.1
     */
    public boolean isNeedInput() {
        return getIntegerFormatter().isNeedInput();
    }

    /**
     * �����ֶ� isNeedInput ��ֵ��������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isNeedInput �ֶ� isNeedInput ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#setNeedInput(boolean)
     * @since T3 V1.1
     */
    public void setNeedInput(boolean isNeedInput) {
        getIntegerFormatter().setNeedInput(isNeedInput);
    }

    /**
     * ��ȡ�ֶ� isEnabledPopupErrorTip ��ֵ�������벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @return �ֶ� isEnabledPopupErrorTip ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#isEnabledPopupErrorTip()
     * @since T3 V1.1
     */
    public boolean isEnabledPopupErrorTip() {
        return getIntegerFormatter().isEnabledPopupErrorTip();
    }

    /**
     * �����ֶ� isEnabledPopupErrorTip ��ֵ�������벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param isEnabledPopupErrorTip �ֶ� isEnabledPopupErrorTip ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#setEnabledPopupErrorTip(boolean)
     * @since T3 V1.1
     */
    public void setEnabledPopupErrorTip(boolean isEnabledPopupErrorTip) {
        getIntegerFormatter().setEnabledPopupErrorTip(isEnabledPopupErrorTip);
    }

    /**
     * ��ȡ�ֶ� minimum ��ֵ�������������Сֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip���ø������ʾ��
     * @return �ֶ� minimum ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#getMinimum()
     * @since T3 V1.1
     */
    public BigInteger getMinimum() {
        return getIntegerFormatter().getMinimum();
    }

    /**
     * �����ֶ� minimum ��ֵ�������������Сֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip���ø������ʾ��
     * @param minimum �ֶ� minimum ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#setMinimum(BigInteger)
     * @since T3 V1.1
     */
    public void setMinimum(BigInteger minimum) {
        getIntegerFormatter().setMinimum(minimum);
    }

    /**
     * �����ֶ� minimum ��ֵ�������������Сֵ��
     * @param minimum �ֶ� minimum ��ֵ��
     * @since T3 V1.1
     */
    public void setMinimum(long minimum) {
        setMinimum(BigInteger.valueOf(minimum));
    }

    /**
     * �����ֶ� maximum ��ֵ��������������ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @return �ֶ� maximum ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#getMaximum()
     * @since T3 V1.1
     */
    public BigInteger getMaximum() {
        return getIntegerFormatter().getMaximum();
    }

    /**
     * �����ֶ� maximum ��ֵ��������������ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param maximum �ֶ� maximum ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#setMaximum(BigInteger)
     * @since T3 V1.1
     */
    public void setMaximum(BigInteger maximum) {
        getIntegerFormatter().setMaximum(maximum);
    }

    /**
     * �����ֶ� maximum ��ֵ��������������ֵ��
     * @param maximum �ֶ� maximum ��ֵ��
     * @since T3 V1.1
     */
    public void setMaximum(long maximum) {
        setMaximum(BigInteger.valueOf(maximum));
    }

    /**
     * ��ȡ�ֶ� step ��ֵ�����������Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @return �ֶ� step ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#getStep()
     * @since T3 V1.1
     */
    public BigInteger getStep() {
        return getIntegerFormatter().getStep();
    }

    /**
     * �����ֶ� step ��ֵ�����������Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param step �ֶ� step ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#setStep(BigInteger)
     * @since T3 V1.1
     */
    public void setStep(BigInteger step) {
        getIntegerFormatter().setStep(step);
    }

    /**
     * �����ֶ� step ��ֵ�����������Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ��������������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param step �ֶ� step ��ֵ��
     * @since T3 V1.1
     */
    public void setStep(long step) {
        setStep(BigInteger.valueOf(step));
    }

    /**
     * ��ȡ�ֶ� stepBase ��ֵ��������ֵ�����Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @return �ֶ� stepBase ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#getStepBase()
     * @since T3 V1.1
     */
    public BigInteger getStepBase() {
        return getIntegerFormatter().getStepBase();
    }

    /**
     * �����ֶ� stepBase ��ֵ������ֵ�����Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param stepBase �ֶ� stepBase ��ֵ��
     * @see com.apollo.swing.component.text.IntegerFormatter#setStepBase(BigInteger)
     * @since T3 V1.1
     */
    public void setStepBase(BigInteger stepBase) {
        getIntegerFormatter().setStepBase(stepBase);
    }

    /**
     * �����ֶ� stepBase ��ֵ������ֵ�����Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ��������������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param stepBase �ֶ� stepBase ��ֵ��
     * @since T3 V1.1
     */
    public void setStepBase(long stepBase) {
        setStepBase(BigInteger.valueOf(stepBase));
    }

    /*------------------------------------- JFormattedTextField Public Method -------------------------------------*/

    /**
     * @deprecated ����ֱ��ʹ��getInteger������
     * @see javax.swing.JFormattedTextField#getValue()
     * @see com.apollo.swing.component.text.IntegerField#getInteger()
     */
    public Object getValue() {
        return super.getValue();
    }

    /**
     * @deprecated ����ֱ��ʹ��setInteger������
     * @see javax.swing.JFormattedTextField#setValue(java.lang.Object)
     * @see com.apollo.swing.component.text.IntegerField#setInteger(BigInteger)
     */
    public void setValue(Object value) {
        super.setValue(value);
    }

    /**
     * @see javax.swing.JFormattedTextField#setFormatterFactory(javax.swing.JFormattedTextField.AbstractFormatterFactory)
     */
    public void setFormatterFactory(AbstractFormatterFactory ff) {
        if (ff != null && ff.getFormatter(this) instanceof IntegerFormatter) {
            super.setFormatterFactory(ff);

        } else {
            throw new IllegalArgumentException("TIntegerField can not set non TIntegerFormatter !");
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- JFormattedTextField Protected Method -------------------------------------*/

    /**
     * @see javax.swing.JFormattedTextField#setFormatter(javax.swing.JFormattedTextField.AbstractFormatter)
     */
    protected void setFormatter(AbstractFormatter af) {
        if (af instanceof IntegerFormatter) {
            super.setFormatter(af);

        } else {
            throw new IllegalArgumentException("TIntegerField can not set non TIntegerFormatter !");
        }
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
