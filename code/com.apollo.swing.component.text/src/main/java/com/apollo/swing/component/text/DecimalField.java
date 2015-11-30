/*
 * �˴��봴���� 2009-1-13 ����10:06:09��
 */
package com.apollo.swing.component.text;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * <p>�ļ����ƣ�DecimalField.java</p>
 * <p>����������С�������ע�⣺���������JFormattedTextField�����Ӧ����Ӧ�������˽�JFormattedTextField������÷���</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2009-1-13</p>
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
public class DecimalField extends BaseField {

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
    public DecimalField() {
        this(0, true, true, null, null, null, null);
    }

    /**
     * ���췽����
     * @param columns ���������������JText���Ǹ�һ������˼��
     * @param minimum �����������Сֵ��
     * @param maximum ������������ֵ��
     * @param scale ���ȣ���С�����λ��
     * @param value ��ʼ���õ�С��ֵ��
     * @since T3 V1.1
     */
    public DecimalField(int columns, double minimum, double maximum, int scale, double value) {
        this(columns, true, true, minimum, maximum, scale, value);
    }

    /**
     * ���췽����
     * @param columns ���������������JText���Ǹ�һ������˼��
     * @param isNeedInput ������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip �����벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param minimum �����������Сֵ��
     * @param maximum ������������ֵ��
     * @param scale ���ȣ���С�����λ��
     * @param value ��ʼ���õ�С��ֵ��
     * @since T3 V1.1
     */
    public DecimalField(int columns,
                        boolean isNeedInput,
                        boolean isEnabledPopupErrorTip,
                        double minimum,
                        double maximum,
                        int scale,
                        double value) {
        this(columns,
             isNeedInput,
             isEnabledPopupErrorTip,
             new BigDecimal(String.valueOf(minimum)),
             new BigDecimal(String.valueOf(maximum)),
             BigInteger.valueOf(scale),
             new BigDecimal(String.valueOf(value)));
    }

    /**
     * ���췽����
     * @param columns ���������������JText���Ǹ�һ������˼��
     * @param isNeedInput ������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip �����벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param minimum �����������Сֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip���ø������ʾ��
     * @param maximum ������������ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param scale ���ȣ���С�����λ���ð�װ����ģ�����Ϊnull����ʾ����û�о��ȣ��������޾��ȣ��������ð�װ����������һ�����㡣
     * @param value ��ʼ���õ�С��ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�г�ʼֵ��ע�⣺���isNeedInputΪtrue���Ǵ˴��������һ����ʼֵ��
     * @see com.apollo.swing.component.text.DecimalFormatter#TDecimalFormatter(boolean, boolean, BigDecimal, BigDecimal, BigInteger)
     * @since T3 V1.1
     */
    public DecimalField(int columns,
                        boolean isNeedInput,
                        boolean isEnabledPopupErrorTip,
                        BigDecimal minimum,
                        BigDecimal maximum,
                        BigInteger scale,
                        BigDecimal value) {
        super(new DecimalFormatter(isNeedInput, isEnabledPopupErrorTip, minimum, maximum, scale));

        setColumns(columns);
        setDecimal(value);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡС������ע�⣺�˴���ȡ��ֵ�������ǽ����ϵ�ֵ��ֻ��commitEdit�Ժ󣬽����ϵ�ֵ�Ż����ģ�ͣ�����JFormattedTextField��ȫһ����
     * @return С�������Ϊnull��ʾδ���������Ϊ�ա�
     * @since T3 V1.1
     */
    public BigDecimal getDecimal() {
        return (BigDecimal) getValue();
    }

    /**
     * ����С����ע�⣺Ӧ�ñ��뱣֤���õ�С���ǺϷ��ġ�
     * @param decimal С����Ϊnull��ʾ����Ϊ�ա�
     * @since T3 V1.1
     */
    public void setDecimal(BigDecimal decimal) {
        setValue(decimal);
    }

    /**
     * ����С����ע�⣺Ӧ�ñ��뱣֤���õ�С���ǺϷ��ġ�
     * @param decimal С����
     * @since T3 V1.1
     */
    public void setDecimal(double decimal) {
        setDecimal(new BigDecimal(String.valueOf(decimal)));
    }

    /**
     * ��ȡС����ʽ����
     * @return С����ʽ����
     * @since T3 V1.1
     */
    public DecimalFormatter getDecimalFormatter() {
        return (DecimalFormatter) getFormatter();
    }

    /**
     * ��ȡ�ֶ� isNeedInput ��ֵ��������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @return �ֶ� isNeedInput ��ֵ��
     * @see com.apollo.swing.component.text.DecimalFormatter#isNeedInput()
     * @since T3 V1.1
     */
    public boolean isNeedInput() {
        return getDecimalFormatter().isNeedInput();
    }

    /**
     * �����ֶ� isNeedInput ��ֵ��������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isNeedInput �ֶ� isNeedInput ��ֵ��
     * @see com.apollo.swing.component.text.DecimalFormatter#setNeedInput(boolean)
     * @since T3 V1.1
     */
    public void setNeedInput(boolean isNeedInput) {
        getDecimalFormatter().setNeedInput(isNeedInput);
    }

    /**
     * ��ȡ�ֶ� isEnabledPopupErrorTip ��ֵ�������벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @return �ֶ� isEnabledPopupErrorTip ��ֵ��
     * @see com.apollo.swing.component.text.DecimalFormatter#isEnabledPopupErrorTip()
     * @since T3 V1.1
     */
    public boolean isEnabledPopupErrorTip() {
        return getDecimalFormatter().isEnabledPopupErrorTip();
    }

    /**
     * �����ֶ� isEnabledPopupErrorTip ��ֵ�������벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param isEnabledPopupErrorTip �ֶ� isEnabledPopupErrorTip ��ֵ��
     * @see com.apollo.swing.component.text.DecimalFormatter#setEnabledPopupErrorTip(boolean)
     * @since T3 V1.1
     */
    public void setEnabledPopupErrorTip(boolean isEnabledPopupErrorTip) {
        getDecimalFormatter().setEnabledPopupErrorTip(isEnabledPopupErrorTip);
    }

    /**
     * ��ȡ�ֶ� minimum ��ֵ�������������Сֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip���ø������ʾ��
     * @return �ֶ� minimum ��ֵ��
     * @see com.apollo.swing.component.text.DecimalFormatter#getMinimum()
     * @since T3 V1.1
     */
    public BigDecimal getMinimum() {
        return getDecimalFormatter().getMinimum();
    }

    /**
     * �����ֶ� minimum ��ֵ�������������Сֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip���ø������ʾ��
     * @param minimum �ֶ� minimum ��ֵ��
     * @see com.apollo.swing.component.text.DecimalFormatter#setMinimum(BigDecimal)
     * @since T3 V1.1
     */
    public void setMinimum(BigDecimal minimum) {
        getDecimalFormatter().setMinimum(minimum);
    }

    /**
     * �����ֶ� minimum ��ֵ�������������Сֵ��
     * @param minimum �ֶ� minimum ��ֵ��
     * @since T3 V1.1
     */
    public void setMinimum(long minimum) {
        setMinimum(BigDecimal.valueOf(minimum));
    }

    /**
     * �����ֶ� maximum ��ֵ��������������ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @return �ֶ� maximum ��ֵ��
     * @see com.apollo.swing.component.text.DecimalFormatter#getMaximum()
     * @since T3 V1.1
     */
    public BigDecimal getMaximum() {
        return getDecimalFormatter().getMaximum();
    }

    /**
     * �����ֶ� maximum ��ֵ��������������ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param maximum �ֶ� maximum ��ֵ��
     * @see com.apollo.swing.component.text.DecimalFormatter#setMaximum(BigDecimal)
     * @since T3 V1.1
     */
    public void setMaximum(BigDecimal maximum) {
        getDecimalFormatter().setMaximum(maximum);
    }

    /**
     * �����ֶ� maximum ��ֵ��������������ֵ��
     * @param maximum �ֶ� maximum ��ֵ��
     * @since T3 V1.1
     */
    public void setMaximum(long maximum) {
        setMaximum(BigDecimal.valueOf(maximum));
    }

    /**
     * ��ȡ�ֶ� scale ��ֵ�����ȣ���С�����λ���ð�װ����ģ�����Ϊnull����ʾ����û�о��ȣ��������޾��ȣ��������ð�װ����������һ�����㡣
     * @return �ֶ� scale ��ֵ��
     * @see com.apollo.swing.component.text.DecimalFormatter#getScale()
     * @since T3 V1.1
     */
    public BigInteger getScale() {
        return getDecimalFormatter().getScale();
    }

    /**
     * �����ֶ� scale ��ֵ�����ȣ���С�����λ���ð�װ����ģ�����Ϊnull����ʾ����û�о��ȣ��������޾��ȣ��������ð�װ����������һ�����㡣
     * @param scale �ֶ� scale ��ֵ��
     * @see com.apollo.swing.component.text.DecimalFormatter#setScale(BigInteger)
     * @since T3 V1.1
     */
    public void setScale(BigInteger scale) {
        getDecimalFormatter().setScale(scale);
    }

    /**
     * �����ֶ� scale ��ֵ�����ȣ���С�����λ��
     * @param scale �ֶ� scale ��ֵ��
     * @since T3 V1.1
     */
    public void setScale(int scale) {
        setScale(BigInteger.valueOf(scale));
    }

    /*------------------------------------- JFormattedTextField Public Method -------------------------------------*/

    /**
     * @deprecated ����ֱ��ʹ��getDecimal������
     * @see javax.swing.JFormattedTextField#getValue()
     * @see com.apollo.swing.component.text.DecimalField#getDecimal()
     */
    public Object getValue() {
        return super.getValue();
    }

    /**
     * @deprecated ����ֱ��ʹ��setDecimal������
     * @see javax.swing.JFormattedTextField#setValue(java.lang.Object)
     * @see com.apollo.swing.component.text.DecimalField#setDecimal(BigDecimal)
     */
    public void setValue(Object value) {
        super.setValue(value);
    }

    /**
     * @see javax.swing.JFormattedTextField#setFormatterFactory(javax.swing.JFormattedTextField.AbstractFormatterFactory)
     */
    public void setFormatterFactory(AbstractFormatterFactory ff) {
        if (ff != null && ff.getFormatter(this) instanceof DecimalFormatter) {
            super.setFormatterFactory(ff);

        } else {
            throw new IllegalArgumentException("TDecimalField can not set non TDecimalFormatter !");
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- JFormattedTextField Protected Method -------------------------------------*/

    /**
     * @see javax.swing.JFormattedTextField#setFormatter(javax.swing.JFormattedTextField.AbstractFormatter)
     */
    protected void setFormatter(AbstractFormatter af) {
        if (af instanceof DecimalFormatter) {
            super.setFormatter(af);

        } else {
            throw new IllegalArgumentException("TDecimalField can not set non TDecimalFormatter !");
        }
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
