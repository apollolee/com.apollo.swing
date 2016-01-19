/*
 * �˴��봴���� 2009-1-13 ����10:04:53��
 */
package com.apollo.swing.component.text;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * <p>�ļ����ƣ�DecimalFormatter.java</p>
 * <p>����������С����ʽ����ע�⣺���������JFormattedTextField�����Ӧ����Ӧ�������˽�JFormattedTextField������÷���</p>
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
public class DecimalFormatter extends BaseFormatter {

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

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /**
     * ��Сֵ��
     */
    protected BigDecimal m_minimum;

    /**
     * ���ֵ��
     */
    protected BigDecimal m_maximum;

    /**
     * ���ȡ�
     */
    protected BigInteger m_scale;

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
    public DecimalFormatter() {
        this(true, true, null, null, null);
    }

    /**
     * ���췽����
     * @param isNeedInput ������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip �����벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param minimum �����������Сֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip���ø������ʾ��
     * @param maximum ������������ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param scale ���ȣ���С�����λ���ð�װ����ģ�����Ϊnull����ʾ����û�о��ȣ��������޾��ȣ��������ð�װ����������һ�����㡣
     * @since T3 V1.1
     */
    public DecimalFormatter(boolean isNeedInput, boolean isEnabledPopupErrorTip, BigDecimal minimum, BigDecimal maximum, BigInteger scale) {
        super(isNeedInput, isEnabledPopupErrorTip);

        setMinimum(minimum);
        setMaximum(maximum);
        setScale(scale);

        if (m_tipText == null) { //�����set������δִ�е�updateTipText()�ͷ�����
            updateTipText();
        }
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ�ֶ� minimum ��ֵ�������������Сֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip���ø������ʾ��
     * @return �ֶ� minimum ��ֵ��
     * @since T3 V1.1
     */
    public BigDecimal getMinimum() {
        return m_minimum;
    }

    /**
     * �����ֶ� minimum ��ֵ�������������Сֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip���ø������ʾ��
     * @param minimum �ֶ� minimum ��ֵ��
     * @since T3 V1.1
     */
    public void setMinimum(BigDecimal minimum) {
        /* �����ظ����� */
        if (isRepeated(m_minimum, minimum)) {
            return;
        }

        BigDecimal oldMinimum = m_minimum;
        m_minimum = minimum;

        if (!verifyRule()) {
            m_minimum = oldMinimum;
            throw new IllegalArgumentException("Set minimum is failed ! [Minimum = " + minimum + "]");
        }

        updateTipText();
    }

    /**
     * �����ֶ� maximum ��ֵ��������������ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @return �ֶ� maximum ��ֵ��
     * @since T3 V1.1
     */
    public BigDecimal getMaximum() {
        return m_maximum;
    }

    /**
     * �����ֶ� maximum ��ֵ��������������ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param maximum �ֶ� maximum ��ֵ��
     * @since T3 V1.1
     */
    public void setMaximum(BigDecimal maximum) {
        /* �����ظ����� */
        if (isRepeated(m_maximum, maximum)) {
            return;
        }

        BigDecimal oldMaximum = m_maximum;
        m_maximum = maximum;

        if (!verifyRule()) {
            m_maximum = oldMaximum;
            throw new IllegalArgumentException("Set maximum is failed ! [Maximum = " + maximum + "]");
        }

        updateTipText();
    }

    /**
     * ��ȡ�ֶ� scale ��ֵ�����ȣ���С�����λ���ð�װ����ģ�����Ϊnull����ʾ����û�о��ȣ��������޾��ȣ��������ð�װ����������һ�����㡣
     * @return �ֶ� scale ��ֵ��
     * @since T3 V1.1
     */
    public BigInteger getScale() {
        return m_scale;
    }

    /**
     * �����ֶ� scale ��ֵ�����ȣ���С�����λ���ð�װ����ģ�����Ϊnull����ʾ����û�о��ȣ��������޾��ȣ��������ð�װ����������һ�����㡣
     * @param scale �ֶ� scale ��ֵ��
     * @since T3 V1.1
     */
    public void setScale(BigInteger scale) {
        /* �����ظ����� */
        if (isRepeated(m_scale, scale)) {
            return;
        }

        BigInteger oldScale = m_scale;
        m_scale = scale;

        if (!verifyRule()) {
            m_scale = oldScale;
            throw new IllegalArgumentException("Set scale is failed ! [Scale = " + scale + "]");
        }

        updateTipText();
    }

    /*------------------------------------- AbstractFormatter Public Method -------------------------------------*/

    /**
     * @see javax.swing.JFormattedTextField.AbstractFormatter#stringToValue(java.lang.String)
     * @throws ParseException ע�⣺����׳����쳣����ôvalueֵ����ı䣬�����һ�ε�ֵ��
     */
    public Object stringToValue(String text) throws ParseException {
        verify(text, true);

        /* �ж��ı��Ƿ�Ϊ�գ�ע�⣺���Ϊ��ͬʱҲ��ʾm_isNeedInput��false��������ͻ�����֤��ʱ���׳��쳣�� */
        if ("".equals(text)) {
            return null;
        }

        return new BigDecimal(text);
    }

    /**
     * @see javax.swing.JFormattedTextField.AbstractFormatter#valueToString(java.lang.Object)
     * @throws ParseException ע�⣺����׳����쳣����ôtext�ı����������Ϊ�ա�
     */
    public String valueToString(Object value) throws ParseException {
        /* �ж�Valueֵ�Ƿ�Ϸ� */
        if (value == null || !(value instanceof BigDecimal)) {
            return "";

        } else {
            String text = value.toString();
            verify(text, true);

            if (m_scale != null) {
                StringBuffer pattern = new StringBuffer("0.");
                for (int i = 0, size = m_scale.intValue(); i < size; i++) {
                    pattern.append("0");
                }
                text = new DecimalFormat(pattern.toString()).format(((BigDecimal) value).doubleValue());
            }

            return text;
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * ��֤�ƶ��Ĺ����Ƿ�Ϸ���
     * @return Ϊtrue��ʾ�ƶ��Ĺ���Ϸ���Ϊfalse��ʾ�ƶ��Ĺ��򲻺Ϸ���
     */
    protected boolean verifyRule() {
        /* ��֤���� */
        if (m_scale != null) {
            if (m_minimum != null && m_minimum.scale() > m_scale.intValue()) {
                return false;
            }

            if (m_maximum != null && m_maximum.scale() > m_scale.intValue()) {
                return false;
            }
        }

        /* ��Сֵ�����ֵֻҪ��һ�������ã���ô��Ӧ���й����ǺϷ��� */
        if (m_minimum == null || m_maximum == null) {
            return true;
        }

        /* ��Сֵ�������ֵ���������ֵС����Сֵ���ǲ��Ϸ��� */
        if (m_minimum.compareTo(m_maximum) > 0) {
            return false;
        }

        return true;
    }

    /*------------------------------------- TAbstractFormatter Protected Method -------------------------------------*/

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#verify(java.lang.String, boolean)
     */
    protected void verify(String text, boolean isFull) throws ParseException {
        /* ��֤�ַ��Ϸ��ԡ���ʽ�Լ���Ч�� */
        if (isFull) { //����֤�ı���������
            if ("".equals(text)) {
                if (m_isNeedInput) {
                    throwParseException(I18N_INFO_CHARACTERISILLEGAL);

                } else {
                    return;
                }

            } else {
                try {
                    new BigDecimal(text);

                } catch (Exception ex) {
                    throwParseException(I18N_INFO_FORMATISERROR);
                }
            }

        } else { //����֤�ı�����������
            if ("".equals(text)) {
                return;

            } else if ("-".equals(text)) {
                if (m_minimum == null || m_minimum.compareTo(BigDecimal.valueOf(0)) < 0) {
                    return;

                } else {
                    throwParseException(I18N_INFO_CHARACTERISILLEGAL);
                }

            } else if ("0".equals(text)) {
                if ((m_scale != null && m_scale.intValue() > 0) || ((m_minimum == null || m_minimum.compareTo(BigDecimal.valueOf(0)) <= 0) && (m_maximum == null || m_maximum.compareTo(BigDecimal.valueOf(0)) >= 0))) {
                    return;

                } else {
                    throwParseException(I18N_INFO_CHARACTERISINVALID);
                }

            } else if (text.startsWith(".") || text.startsWith("-.")) {
                return;

            } else {
                if (text.matches("-?0\\d.*")) {
                    throwParseException(I18N_INFO_CHARACTERISINVALID);
                }

                if (!text.matches("-?\\d*\\.?\\d*")) {
                    throwParseException(I18N_INFO_CHARACTERISILLEGAL);
                }

                if (text.startsWith("-") && m_minimum != null && m_minimum.compareTo(BigDecimal.valueOf(0)) >= 0) {
                    throwParseException(I18N_INFO_CHARACTERISILLEGAL);
                }

                if (!text.startsWith("-") && m_maximum != null && m_maximum.compareTo(BigDecimal.valueOf(0)) < 0) {
                    throwParseException(I18N_INFO_CHARACTERISILLEGAL);
                }
            }
        }

        /* ��ȡ����ֵ */
        BigDecimal value = new BigDecimal(text);
        BigDecimal nextMinValue = new BigDecimal(text + (text.startsWith("-") ? "9" : "0"));
        BigDecimal nextMaxValue = new BigDecimal(text + (text.startsWith("-") ? "0" : "9"));
        BigDecimal nextNextMinValue = new BigDecimal(text + (text.startsWith("-") ? "99" : "00"));
        BigDecimal nextNextMaxValue = new BigDecimal(text + (text.startsWith("-") ? "00" : "99"));

        /* ��֤��Сֵ */
        if (isFull || text.startsWith("-")) {
            if (m_minimum != null && value.compareTo(m_minimum) < 0) {
                throwParseException(I18N_INFO_VALUEISTOOSMALL);
            }

        } else {
            if (m_minimum != null && m_maximum != null && ((value.compareTo(m_minimum) < 0 && nextMinValue.compareTo(m_maximum) > 0) || (nextMaxValue.compareTo(m_minimum) < 0 && nextNextMinValue.compareTo(m_maximum) > 0))) {
                throwParseException(I18N_INFO_VALUEISTOOSMALL);
            }
        }

        /* ��֤���ֵ */
        if (isFull || !text.startsWith("-")) {
            if (m_maximum != null && value.compareTo(m_maximum) > 0) {
                throwParseException(I18N_INFO_VALUEISTOOBIG);
            }

        } else if (text.startsWith("-")) {
            if (m_minimum != null && m_maximum != null && ((value.compareTo(m_maximum) > 0 && nextMaxValue.compareTo(m_minimum) < 0) || (nextMinValue.compareTo(m_maximum) > 0 && nextNextMaxValue.compareTo(m_minimum) < 0))) {
                throwParseException(I18N_INFO_VALUEISTOOBIG);
            }
        }

        /* ��֤���� */
        if (m_scale != null && m_scale.intValue() < value.scale()) {
            throwParseException(I18N_INFO_FALLSHORTOFPRECISION);
        }
    }

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#increment(java.awt.event.ActionEvent)
     */
    protected int[] increment(ActionEvent evt) {
        /* TODO �ݲ�֧��*/
        return null;
    }

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#decrement(java.awt.event.ActionEvent)
     */
    protected int[] decrement(ActionEvent evt) {
        /* TODO �ݲ�֧��*/
        return null;
    }

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#updateTipText()
     */
    protected void updateTipText() {
        StringBuffer sb = new StringBuffer();

        sb.append(I18N_INFO_RANGE).append(": [");
        sb.append(m_minimum == null ? "-\u221e" : m_minimum.toString());
        sb.append(" , ").append(m_maximum == null ? "+\u221e" : m_maximum.toString()).append("]");

        if (m_scale != null) {
            sb.append(" , ").append(I18N_INFO_PRECISION).append(": ").append(getScaleDisplay());
        }

        m_tipText = sb.toString();

        super.updateTipText();
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /**
     * ��ȡ������ʾ��Ϣ��
     * @return ������ʾ��Ϣ��
     */
    private String getScaleDisplay() {
        StringBuffer sb = new StringBuffer();

        sb.append("0.");
        for (int i = 1, size = m_scale.intValue(); i < size; i++) {
            sb.append("0");
        }
        sb.append("1");

        return sb.toString();
    }

}
