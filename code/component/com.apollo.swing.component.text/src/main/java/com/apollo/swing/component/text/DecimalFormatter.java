/*
 * 此代码创建于 2009-1-13 上午10:04:53。
 */
package com.apollo.swing.component.text;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * <p>文件名称：DecimalFormatter.java</p>
 * <p>类型描述：小数格式器，注意：此组件基于JFormattedTextField，因此应用者应该首先了解JFormattedTextField组件的用法。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2009-1-13</p>
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
     * 最小值。
     */
    protected BigDecimal m_minimum;

    /**
     * 最大值。
     */
    protected BigDecimal m_maximum;

    /**
     * 精度。
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
     * 构造方法。
     * @since T3 V1.1
     */
    public DecimalFormatter() {
        this(true, true, null, null, null);
    }

    /**
     * 构造方法。
     * @param isNeedInput 定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param minimum 允许输入的最小值，用包装对象的，允许为null，表示输入没有下限，在提示的tooltip中用负无穷表示。
     * @param maximum 允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @param scale 精度，即小数点后几位，用包装对象的，允许为null，表示输入没有精度，可以无限精度，但必须用包装对象来处理一切运算。
     * @since T3 V1.1
     */
    public DecimalFormatter(boolean isNeedInput, boolean isEnabledPopupErrorTip, BigDecimal minimum, BigDecimal maximum, BigInteger scale) {
        super(isNeedInput, isEnabledPopupErrorTip);

        setMinimum(minimum);
        setMaximum(maximum);
        setScale(scale);

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
    public BigDecimal getMinimum() {
        return m_minimum;
    }

    /**
     * 设置字段 minimum 的值，允许输入的最小值，用包装对象的，允许为null，表示输入没有下限，在提示的tooltip中用负无穷表示。
     * @param minimum 字段 minimum 的值。
     * @since T3 V1.1
     */
    public void setMinimum(BigDecimal minimum) {
        /* 过滤重复设置 */
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
     * 设置字段 maximum 的值，允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @return 字段 maximum 的值。
     * @since T3 V1.1
     */
    public BigDecimal getMaximum() {
        return m_maximum;
    }

    /**
     * 设置字段 maximum 的值，允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @param maximum 字段 maximum 的值。
     * @since T3 V1.1
     */
    public void setMaximum(BigDecimal maximum) {
        /* 过滤重复设置 */
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
     * 获取字段 scale 的值，精度，即小数点后几位，用包装对象的，允许为null，表示输入没有精度，可以无限精度，但必须用包装对象来处理一切运算。
     * @return 字段 scale 的值。
     * @since T3 V1.1
     */
    public BigInteger getScale() {
        return m_scale;
    }

    /**
     * 设置字段 scale 的值，精度，即小数点后几位，用包装对象的，允许为null，表示输入没有精度，可以无限精度，但必须用包装对象来处理一切运算。
     * @param scale 字段 scale 的值。
     * @since T3 V1.1
     */
    public void setScale(BigInteger scale) {
        /* 过滤重复设置 */
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
     * @throws ParseException 注意：如果抛出此异常，那么value值不会改变，即最近一次的值。
     */
    public Object stringToValue(String text) throws ParseException {
        verify(text, true);

        /* 判断文本是否为空，注意：如果为空同时也表示m_isNeedInput是false，否则早就会在验证的时候抛出异常了 */
        if ("".equals(text)) {
            return null;
        }

        return new BigDecimal(text);
    }

    /**
     * @see javax.swing.JFormattedTextField.AbstractFormatter#valueToString(java.lang.Object)
     * @throws ParseException 注意：如果抛出此异常，那么text文本内容则会置为空。
     */
    public String valueToString(Object value) throws ParseException {
        /* 判断Value值是否合法 */
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
     * 验证制定的规则是否合法。
     * @return 为true表示制定的规则合法，为false表示制定的规则不合法。
     */
    protected boolean verifyRule() {
        /* 验证精度 */
        if (m_scale != null) {
            if (m_minimum != null && m_minimum.scale() > m_scale.intValue()) {
                return false;
            }

            if (m_maximum != null && m_maximum.scale() > m_scale.intValue()) {
                return false;
            }
        }

        /* 最小值和最大值只要有一个不设置，那么对应所有规则都是合法的 */
        if (m_minimum == null || m_maximum == null) {
            return true;
        }

        /* 最小值大于最大值（或者最大值小于最小值）是不合法的 */
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
        /* 验证字符合法性、格式以及有效性 */
        if (isFull) { //待验证文本是完整的
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

        } else { //待验证文本不是完整的
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

        /* 获取计算值 */
        BigDecimal value = new BigDecimal(text);
        BigDecimal nextMinValue = new BigDecimal(text + (text.startsWith("-") ? "9" : "0"));
        BigDecimal nextMaxValue = new BigDecimal(text + (text.startsWith("-") ? "0" : "9"));
        BigDecimal nextNextMinValue = new BigDecimal(text + (text.startsWith("-") ? "99" : "00"));
        BigDecimal nextNextMaxValue = new BigDecimal(text + (text.startsWith("-") ? "00" : "99"));

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

        /* 验证精度 */
        if (m_scale != null && m_scale.intValue() < value.scale()) {
            throwParseException(I18N_INFO_FALLSHORTOFPRECISION);
        }
    }

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#increment(java.awt.event.ActionEvent)
     */
    protected int[] increment(ActionEvent evt) {
        /* TODO 暂不支持*/
        return null;
    }

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#decrement(java.awt.event.ActionEvent)
     */
    protected int[] decrement(ActionEvent evt) {
        /* TODO 暂不支持*/
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
     * 获取精度显示信息。
     * @return 精度显示信息。
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
