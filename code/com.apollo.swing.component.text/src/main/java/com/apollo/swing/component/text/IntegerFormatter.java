/*
 * �˴��봴���� 2008-2-28 ����09:37:36��
 */
package com.apollo.swing.component.text;

import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.text.ParseException;

import javax.swing.JFormattedTextField;

/**
 * <p>�ļ����ƣ�IntegerFormatter.java</p>
 * <p>����������������ʽ����ע�⣺���������JFormattedTextField�����Ӧ����Ӧ�������˽�JFormattedTextField������÷���</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-2-28</p>
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
public class IntegerFormatter extends BaseFormatter {

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
     * ��Сֵ��
     */
    protected BigInteger m_minimum;

    /**
     * ���ֵ��
     */
    protected BigInteger m_maximum;

    /**
     * ������
     */
    protected BigInteger m_step;

    /**
     * ������ֵ��
     */
    protected BigInteger m_stepBase;

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
    public IntegerFormatter() {
        this(true, true, null, null, null, null);
    }

    /**
     * ���췽����
     * @param isNeedInput ������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip �����벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param minimum �����������Сֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip���ø������ʾ��
     * @param maximum ������������ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param step ���������Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param stepBase ������ֵ�����Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @since T3 V1.1
     */
    public IntegerFormatter(boolean isNeedInput,
                            boolean isEnabledPopupErrorTip,
                            BigInteger minimum,
                            BigInteger maximum,
                            BigInteger step,
                            BigInteger stepBase) {
        super(isNeedInput, isEnabledPopupErrorTip);

        setMinimum(minimum);
        setMaximum(maximum);
        setStep(step);
        setStepBase(stepBase);

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
    public BigInteger getMinimum() {
        return m_minimum;
    }

    /**
     * �����ֶ� minimum ��ֵ�������������Сֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip���ø������ʾ��
     * @param minimum �ֶ� minimum ��ֵ��
     * @since T3 V1.1
     */
    public void setMinimum(BigInteger minimum) {
        /* �����ظ����� */
        if (isRepeated(m_minimum, minimum)) {
            return;
        }

        BigInteger oldMinimum = m_minimum;
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
    public BigInteger getMaximum() {
        return m_maximum;
    }

    /**
     * �����ֶ� maximum ��ֵ��������������ֵ���ð�װ����ģ�����Ϊnull����ʾ����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param maximum �ֶ� maximum ��ֵ��
     * @since T3 V1.1
     */
    public void setMaximum(BigInteger maximum) {
        /* �����ظ����� */
        if (isRepeated(m_maximum, maximum)) {
            return;
        }

        BigInteger oldMaximum = m_maximum;
        m_maximum = maximum;

        if (!verifyRule()) {
            m_maximum = oldMaximum;
            throw new IllegalArgumentException("Set maximum is failed ! [Maximum = " + maximum + "]");
        }

        updateTipText();
    }

    /**
     * ��ȡ�ֶ� step ��ֵ�����������Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @return �ֶ� step ��ֵ��
     * @since T3 V1.1
     */
    public BigInteger getStep() {
        if (m_step != null) {
            return m_step;

        } else {
            return BigInteger.valueOf(1);
        }
    }

    /**
     * �����ֶ� step ��ֵ�����������Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param step �ֶ� step ��ֵ��
     * @since T3 V1.1
     */
    public void setStep(BigInteger step) {
        /* �����ظ����� */
        if (isRepeated(m_step, step)) {
            return;
        }

        BigInteger oldStep = m_step;
        m_step = step;

        if (!verifyRule()) {
            m_step = oldStep;
            throw new IllegalArgumentException("Set step is failed ! [Step = " + step + "]");
        }

        updateTipText();
    }

    /**
     * ��ȡ�ֶ� stepBase ��ֵ��������ֵ�����Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @return �ֶ� stepBase ��ֵ��
     * @since T3 V1.1
     */
    public BigInteger getStepBase() {
        if (m_stepBase != null) {
            return m_stepBase;

        } else if (m_minimum != null) {
            return m_minimum;

        } else {
            return BigInteger.ZERO;
        }
    }

    /**
     * �����ֶ� stepBase ��ֵ��������ֵ�����Ӳ�����ֵ��ʼ���㣬���������Ҫ�ǲ���ֵ�����������ð�װ����ģ�����Ϊnull����ʾʹ��ȱʡֵ������ȱʡֵΪ1��������ֵȱʡΪ0��
     * @param stepBase �ֶ� stepBase ��ֵ��
     * @since T3 V1.1
     */
    public void setStepBase(BigInteger stepBase) {
        /* �����ظ����� */
        if (isRepeated(m_stepBase, stepBase)) {
            return;
        }

        BigInteger oldStepBase = m_stepBase;
        m_stepBase = stepBase;

        if (!verifyRule()) {
            m_stepBase = oldStepBase;
            throw new IllegalArgumentException("Set step base is failed ! [StepBase = " + stepBase + "]");
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

        return new BigInteger(text);
    }

    /**
     * @see javax.swing.JFormattedTextField.AbstractFormatter#valueToString(java.lang.Object)
     * @throws ParseException ע�⣺����׳����쳣����ôtext�ı����������Ϊ�ա�
     */
    public String valueToString(Object value) throws ParseException {
        /* �ж�Valueֵ�Ƿ�Ϸ� */
        if (value == null || !(value instanceof BigInteger)) {
            return "";

        } else {
            String text = value.toString();
            verify(text, true);
            return text;
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * ��֤�ƶ��Ĺ����Ƿ�Ϸ���
     * @return Ϊtrue��ʾ�ƶ��Ĺ���Ϸ���Ϊfalse��ʾ�ƶ��Ĺ��򲻺Ϸ���
     */
    protected boolean verifyRule() {
        /* ��Сֵ�����ֵֻҪ��һ�������ã���ô��Ӧ���й����ǺϷ��� */
        if (m_minimum == null || m_maximum == null) {
            return true;
        }

        /* ��Сֵ�������ֵ���������ֵС����Сֵ���ǲ��Ϸ��� */
        if (m_minimum.compareTo(m_maximum) > 0) {
            return false;
        }

        /* ��Сֵ�������϶����������ֵ�ǲ��Ϸ��ģ�û���κ�һ������ֵ���ںϷ������ڣ� */
        if (getFlushInteger(m_minimum, true).compareTo(m_maximum) > 0) {
            return false;
        }

        return true;
    }

    /**
     * �ж��Ƿ�Ϊ��С�������������Ƿ�Ϊ1��
     * @return Ϊtrue��ʾ����С������Ϊfalse��ʾ������С������
     */
    protected boolean isMinimumStep() {
        return getStep().equals(BigInteger.valueOf(1));
    }

    /**
     * �ж�ָ���������Ƿ���ϲ�����
     * @param value ָ����������
     * @return Ϊtrue��ʾָ�����������ϲ�����Ϊfalse��ʾָ�������������ϲ�����
     */
    protected boolean isTrueOfStep(BigInteger value) {
        return getMod(value).equals(BigInteger.ZERO);
    }

    /**
     * ��ȡָ�����������ϲ�������һ��ֵ��
     * @param value ָ����������
     * @return ָ�����������ϲ�������һ��ֵ��
     */
    protected BigInteger getNextInteger(BigInteger value) {
        return getStep().subtract(getMod(value)).add(value);
    }

    /**
     * ��ȡָ�����������ϲ�������һ��ֵ��
     * @param value ָ����������
     * @return ָ�����������ϲ�������һ��ֵ��
     */
    protected BigInteger getPreviousInteger(BigInteger value) {
        return value.subtract(isTrueOfStep(value) ? getStep() : getMod(value));
    }

    /**
     * ��ȡָ������������ֵ��
     * @param value ָ����������
     * @param isUpFlush Ϊtrue��ʾ���϶��룬Ϊfalse��ʾ���¶��롣
     * @return ָ������������ֵ��
     */
    protected BigInteger getFlushInteger(BigInteger value, boolean isUpFlush) {
        if (isTrueOfStep(value)) { //ָ�������Ѿ��Ƿ��ϲ�����
            return value;

        } else {
            value = value.subtract(getMod(value));
            if (isUpFlush) {
                value = value.add(getStep());
            }

            return value;
        }
    }

    /**
     * ��ȡָ�������벽����ģֵ��
     * @param value ָ��������
     * @return ָ�������벽����ģֵ��
     */
    protected BigInteger getMod(BigInteger value) {
        return value.subtract(getStepBase()).mod(getStep());
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
                if (!text.equals("0") && !text.matches("-?[1-9]\\d*")) {
                    throwParseException(I18N_INFO_FORMATISERROR);
                }
            }

        } else { //����֤�ı�����������
            if ("".equals(text)) {
                return;

            } else if ("-".equals(text)) {
                if (m_minimum == null || m_minimum.compareTo(BigInteger.ZERO) < 0) {
                    return;

                } else {
                    throwParseException(I18N_INFO_CHARACTERISILLEGAL);
                }

            } else if ("0".equals(text)) {
                if ((m_minimum == null || m_minimum.compareTo(BigInteger.ZERO) <= 0) && (m_maximum == null || m_maximum.compareTo(BigInteger.ZERO) >= 0)) {
                    return;

                } else {
                    throwParseException(I18N_INFO_CHARACTERISINVALID);
                }

            } else {
                if (text.matches("-?0.*")) {
                    throwParseException(I18N_INFO_CHARACTERISINVALID);
                }

                if (!text.matches("-?\\d*")) {
                    throwParseException(I18N_INFO_CHARACTERISILLEGAL);
                }

                if (text.startsWith("-") && m_minimum != null && m_minimum.compareTo(BigInteger.ZERO) >= 0) {
                    throwParseException(I18N_INFO_CHARACTERISILLEGAL);
                }

                if (!text.startsWith("-") && m_maximum != null && m_maximum.compareTo(BigInteger.ZERO) < 0) {
                    throwParseException(I18N_INFO_CHARACTERISILLEGAL);
                }
            }
        }

        /* ��ȡ����ֵ */
        BigInteger value = new BigInteger(text);
        BigInteger nextMinValue = new BigInteger(text + (text.startsWith("-") ? "9" : "0"));
        BigInteger nextMaxValue = new BigInteger(text + (text.startsWith("-") ? "0" : "9"));
        BigInteger nextNextMinValue = new BigInteger(text + (text.startsWith("-") ? "99" : "00"));
        BigInteger nextNextMaxValue = new BigInteger(text + (text.startsWith("-") ? "00" : "99"));

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
        if (isFull) {
            if (!isMinimumStep() && !isTrueOfStep(value)) {
                throwParseException(I18N_INFO_FALLSHORTOFSTEP);
            }

        } else {
            if (m_maximum != null && !isMinimumStep() && (text.startsWith("-") ? (nextMaxValue.compareTo(m_minimum) < 0) : (nextMinValue.compareTo(m_maximum) > 0)) && !isTrueOfStep(value)) {
                throwParseException(I18N_INFO_FALLSHORTOFSTEP);
            }
        }
    }

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#increment(java.awt.event.ActionEvent)
     */
    protected int[] increment(ActionEvent evt) {
        JFormattedTextField ftf = getFormattedTextField();

        if (ftf == null) {
            return null;
        }

        String text = ftf.getText();
        BigInteger value;

        if ("".equals(text) || "-".equals(text)) {
            if (m_maximum != null) {
                value = getFlushInteger(m_maximum, false);

            } else {
                value = (BigInteger) ftf.getValue();
                if (value == null) {
                    if (m_minimum == null) {
                        value = getStepBase();

                    } else {
                        value = getFlushInteger(m_minimum, true);
                    }
                }
            }

        } else {
            value = new BigInteger(text);

            if (m_minimum != null && value.compareTo(m_minimum) < 0) {
                value = getFlushInteger(m_minimum, true);

            } else {
                value = getNextInteger(value);
            }
        }

        if (m_maximum == null || value.compareTo(m_maximum) <= 0) {
            ftf.setText(value.toString());
        }

        return super.increment(evt);
    }

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#decrement(java.awt.event.ActionEvent)
     */
    protected int[] decrement(ActionEvent evt) {
        JFormattedTextField ftf = getFormattedTextField();

        if (ftf == null) {
            return null;
        }

        String text = ftf.getText();
        BigInteger value;

        if ("".equals(text) || "-".equals(text)) {
            if (m_minimum != null) {
                value = getFlushInteger(m_minimum, true);

            } else {
                value = (BigInteger) ftf.getValue();
                if (value == null) {
                    if (m_maximum == null) {
                        value = getStepBase();

                    } else {
                        value = getFlushInteger(m_maximum, false);
                    }
                }
            }

        } else {
            value = new BigInteger(text);

            if (m_minimum != null && value.compareTo(m_minimum) < 0) {
                value = getFlushInteger(m_minimum, true);

            } else {
                value = getPreviousInteger(value);
            }
        }

        if (m_minimum == null || value.compareTo(m_minimum) >= 0) {
            ftf.setText(value.toString());
        }

        return super.decrement(evt);
    }

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#updateTipText()
     */
    protected void updateTipText() {
        StringBuffer sb = new StringBuffer();

        sb.append(I18N_INFO_RANGE).append(": [");
        sb.append(m_minimum == null ? "-\u221e" : m_minimum.toString());
        sb.append(" , ").append(m_maximum == null ? "+\u221e" : m_maximum.toString()).append("]");

        BigInteger step = getStep();
        if (!step.equals(BigInteger.valueOf(1))) {
            sb.append(" , ").append(I18N_INFO_STEP).append(": ").append(step.toString());
        }

        BigInteger stepBase = getStepBase();
        if (!stepBase.equals(m_minimum) && !stepBase.equals(BigInteger.ZERO)) {
            sb.append(" , ").append(I18N_INFO_STEPBASE).append(": ").append(stepBase.toString());
        }

        m_tipText = sb.toString();

        super.updateTipText();
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
