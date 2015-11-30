/*
 * 此代码创建于 2008-2-28 上午09:37:36。
 */
package com.apollo.swing.component.text;

import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.text.ParseException;

import javax.swing.JFormattedTextField;

/**
 * <p>文件名称：IntegerFormatter.java</p>
 * <p>类型描述：整数格式器，注意：此组件基于JFormattedTextField，因此应用者应该首先了解JFormattedTextField组件的用法。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-2-28</p>
 * <p>修改记录：</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * @version 1.0
 * @author 李镇
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
     * 最小值。
     */
    protected BigInteger m_minimum;

    /**
     * 最大值。
     */
    protected BigInteger m_maximum;

    /**
     * 步长。
     */
    protected BigInteger m_step;

    /**
     * 步长基值。
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
     * 构造方法。
     * @since T3 V1.1
     */
    public IntegerFormatter() {
        this(true, true, null, null, null, null);
    }

    /**
     * 构造方法。
     * @param isNeedInput 定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param minimum 允许输入的最小值，用包装对象的，允许为null，表示输入没有下限，在提示的tooltip中用负无穷表示。
     * @param maximum 允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @param step 步长，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
     * @param stepBase 步长基值，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
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

        if (m_tipText == null) { //上面的set方法都未执行到updateTipText()就返回了
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
     * 获取字段 minimum 的值，允许输入的最小值，用包装对象的，允许为null，表示输入没有下限，在提示的tooltip中用负无穷表示。
     * @return 字段 minimum 的值。
     * @since T3 V1.1
     */
    public BigInteger getMinimum() {
        return m_minimum;
    }

    /**
     * 设置字段 minimum 的值，允许输入的最小值，用包装对象的，允许为null，表示输入没有下限，在提示的tooltip中用负无穷表示。
     * @param minimum 字段 minimum 的值。
     * @since T3 V1.1
     */
    public void setMinimum(BigInteger minimum) {
        /* 过滤重复设置 */
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
     * 设置字段 maximum 的值，允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @return 字段 maximum 的值。
     * @since T3 V1.1
     */
    public BigInteger getMaximum() {
        return m_maximum;
    }

    /**
     * 设置字段 maximum 的值，允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @param maximum 字段 maximum 的值。
     * @since T3 V1.1
     */
    public void setMaximum(BigInteger maximum) {
        /* 过滤重复设置 */
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
     * 获取字段 step 的值，步长，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
     * @return 字段 step 的值。
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
     * 设置字段 step 的值，步长，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
     * @param step 字段 step 的值。
     * @since T3 V1.1
     */
    public void setStep(BigInteger step) {
        /* 过滤重复设置 */
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
     * 获取字段 stepBase 的值，步长基值，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
     * @return 字段 stepBase 的值。
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
     * 设置字段 stepBase 的值，步长基值，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
     * @param stepBase 字段 stepBase 的值。
     * @since T3 V1.1
     */
    public void setStepBase(BigInteger stepBase) {
        /* 过滤重复设置 */
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
     * @throws ParseException 注意：如果抛出此异常，那么value值不会改变，即最近一次的值。
     */
    public Object stringToValue(String text) throws ParseException {
        verify(text, true);

        /* 判断文本是否为空，注意：如果为空同时也表示m_isNeedInput是false，否则早就会在验证的时候抛出异常了 */
        if ("".equals(text)) {
            return null;
        }

        return new BigInteger(text);
    }

    /**
     * @see javax.swing.JFormattedTextField.AbstractFormatter#valueToString(java.lang.Object)
     * @throws ParseException 注意：如果抛出此异常，那么text文本内容则会置为空。
     */
    public String valueToString(Object value) throws ParseException {
        /* 判断Value值是否合法 */
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
     * 验证制定的规则是否合法。
     * @return 为true表示制定的规则合法，为false表示制定的规则不合法。
     */
    protected boolean verifyRule() {
        /* 最小值和最大值只要有一个不设置，那么对应所有规则都是合法的 */
        if (m_minimum == null || m_maximum == null) {
            return true;
        }

        /* 最小值大于最大值（或者最大值小于最小值）是不合法的 */
        if (m_minimum.compareTo(m_maximum) > 0) {
            return false;
        }

        /* 最小值经过向上对齐后大于最大值是不合法的（没有任何一个整数值落在合法区间内） */
        if (getFlushInteger(m_minimum, true).compareTo(m_maximum) > 0) {
            return false;
        }

        return true;
    }

    /**
     * 判断是否为最小步长，即步长是否为1。
     * @return 为true表示是最小步长，为false表示不是最小步长。
     */
    protected boolean isMinimumStep() {
        return getStep().equals(BigInteger.valueOf(1));
    }

    /**
     * 判断指定的整数是否符合步长。
     * @param value 指定的整数。
     * @return 为true表示指定的整数符合步长，为false表示指定的整数不符合步长。
     */
    protected boolean isTrueOfStep(BigInteger value) {
        return getMod(value).equals(BigInteger.ZERO);
    }

    /**
     * 获取指定整数并符合步长的下一个值。
     * @param value 指定的整数。
     * @return 指定整数并符合步长的下一个值。
     */
    protected BigInteger getNextInteger(BigInteger value) {
        return getStep().subtract(getMod(value)).add(value);
    }

    /**
     * 获取指定整数并符合步长的上一个值。
     * @param value 指定的整数。
     * @return 指定整数并符合步长的上一个值。
     */
    protected BigInteger getPreviousInteger(BigInteger value) {
        return value.subtract(isTrueOfStep(value) ? getStep() : getMod(value));
    }

    /**
     * 获取指定整数对齐后的值。
     * @param value 指定的整数。
     * @param isUpFlush 为true表示向上对齐，为false表示向下对齐。
     * @return 指定整数对齐后的值。
     */
    protected BigInteger getFlushInteger(BigInteger value, boolean isUpFlush) {
        if (isTrueOfStep(value)) { //指定整数已经是符合步长的
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
     * 获取指定整数与步长的模值。
     * @param value 指定整数。
     * @return 指定整数与步长的模值。
     */
    protected BigInteger getMod(BigInteger value) {
        return value.subtract(getStepBase()).mod(getStep());
    }

    /*------------------------------------- TAbstractFormatter Protected Method -------------------------------------*/

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#verify(java.lang.String, boolean)
     */
    protected void verify(String text, boolean isFull) throws ParseException {
        /* 验证字符合法性、格式以及有效性 */
        if (isFull) { //待验证文本是完整的
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

        } else { //待验证文本不是完整的
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

        /* 获取计算值 */
        BigInteger value = new BigInteger(text);
        BigInteger nextMinValue = new BigInteger(text + (text.startsWith("-") ? "9" : "0"));
        BigInteger nextMaxValue = new BigInteger(text + (text.startsWith("-") ? "0" : "9"));
        BigInteger nextNextMinValue = new BigInteger(text + (text.startsWith("-") ? "99" : "00"));
        BigInteger nextNextMaxValue = new BigInteger(text + (text.startsWith("-") ? "00" : "99"));

        /* 验证最小值 */
        if (isFull || text.startsWith("-")) {
            if (m_minimum != null && value.compareTo(m_minimum) < 0) {
                throwParseException(I18N_INFO_VALUEISTOOSMALL);
            }

        } else {
            if (m_minimum != null && m_maximum != null && ((value.compareTo(m_minimum) < 0 && nextMinValue.compareTo(m_maximum) > 0) || (nextMaxValue.compareTo(m_minimum) < 0 && nextNextMinValue.compareTo(m_maximum) > 0))) {
                throwParseException(I18N_INFO_VALUEISTOOSMALL);
            }
        }

        /* 验证最大值 */
        if (isFull || !text.startsWith("-")) {
            if (m_maximum != null && value.compareTo(m_maximum) > 0) {
                throwParseException(I18N_INFO_VALUEISTOOBIG);
            }

        } else if (text.startsWith("-")) {
            if (m_minimum != null && m_maximum != null && ((value.compareTo(m_maximum) > 0 && nextMaxValue.compareTo(m_minimum) < 0) || (nextMinValue.compareTo(m_maximum) > 0 && nextNextMaxValue.compareTo(m_minimum) < 0))) {
                throwParseException(I18N_INFO_VALUEISTOOBIG);
            }
        }

        /* 验证步长 */
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
