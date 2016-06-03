/*
 * 此代码创建于 2009-1-13 上午10:06:09。
 */
package com.apollo.swing.component.text;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * <p>文件名称：DecimalField.java</p>
 * <p>类型描述：小数输入框，注意：此组件基于JFormattedTextField，因此应用者应该首先了解JFormattedTextField组件的用法。</p>
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
     * 构造方法。
     * @since T3 V1.1
     */
    public DecimalField() {
        this(0, true, true, null, null, null, null);
    }

    /**
     * 构造方法。
     * @param columns 列数，这个参数跟JText的那个一样的意思。
     * @param minimum 允许输入的最小值。
     * @param maximum 允许输入的最大值。
     * @param scale 精度，即小数点后几位。
     * @param value 初始化用的小数值。
     * @since T3 V1.1
     */
    public DecimalField(int columns, double minimum, double maximum, int scale, double value) {
        this(columns, true, true, minimum, maximum, scale, value);
    }

    /**
     * 构造方法。
     * @param columns 列数，这个参数跟JText的那个一样的意思。
     * @param isNeedInput 定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param minimum 允许输入的最小值。
     * @param maximum 允许输入的最大值。
     * @param scale 精度，即小数点后几位。
     * @param value 初始化用的小数值。
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
     * 构造方法。
     * @param columns 列数，这个参数跟JText的那个一样的意思。
     * @param isNeedInput 定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param minimum 允许输入的最小值，用包装对象的，允许为null，表示输入没有下限，在提示的tooltip中用负无穷表示。
     * @param maximum 允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @param scale 精度，即小数点后几位，用包装对象的，允许为null，表示输入没有精度，可以无限精度，但必须用包装对象来处理一切运算。
     * @param value 初始化用的小数值，用包装对象的，允许为null，表示输入没有初始值，注意：如果isNeedInput为true，那此处必须给定一个初始值。
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
     * 获取小数对象，注意：此处获取的值并不就是界面上的值，只有commitEdit以后，界面上的值才会存入模型，这点和JFormattedTextField完全一样。
     * @return 小数，如果为null表示未输入或输入为空。
     * @since T3 V1.1
     */
    public BigDecimal getDecimal() {
        return (BigDecimal) getValue();
    }

    /**
     * 设置小数，注意：应用必须保证设置的小数是合法的。
     * @param decimal 小数，为null表示输入为空。
     * @since T3 V1.1
     */
    public void setDecimal(BigDecimal decimal) {
        setValue(decimal);
    }

    /**
     * 设置小数，注意：应用必须保证设置的小数是合法的。
     * @param decimal 小数。
     * @since T3 V1.1
     */
    public void setDecimal(double decimal) {
        setDecimal(new BigDecimal(String.valueOf(decimal)));
    }

    /**
     * 获取小数格式器。
     * @return 小数格式器。
     * @since T3 V1.1
     */
    public DecimalFormatter getDecimalFormatter() {
        return (DecimalFormatter) getFormatter();
    }

    /**
     * 获取字段 isNeedInput 的值，定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @return 字段 isNeedInput 的值。
     * @see com.apollo.swing.component.text.DecimalFormatter#isNeedInput()
     * @since T3 V1.1
     */
    public boolean isNeedInput() {
        return getDecimalFormatter().isNeedInput();
    }

    /**
     * 设置字段 isNeedInput 的值，定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isNeedInput 字段 isNeedInput 的值。
     * @see com.apollo.swing.component.text.DecimalFormatter#setNeedInput(boolean)
     * @since T3 V1.1
     */
    public void setNeedInput(boolean isNeedInput) {
        getDecimalFormatter().setNeedInput(isNeedInput);
    }

    /**
     * 获取字段 isEnabledPopupErrorTip 的值，当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @return 字段 isEnabledPopupErrorTip 的值。
     * @see com.apollo.swing.component.text.DecimalFormatter#isEnabledPopupErrorTip()
     * @since T3 V1.1
     */
    public boolean isEnabledPopupErrorTip() {
        return getDecimalFormatter().isEnabledPopupErrorTip();
    }

    /**
     * 设置字段 isEnabledPopupErrorTip 的值，当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param isEnabledPopupErrorTip 字段 isEnabledPopupErrorTip 的值。
     * @see com.apollo.swing.component.text.DecimalFormatter#setEnabledPopupErrorTip(boolean)
     * @since T3 V1.1
     */
    public void setEnabledPopupErrorTip(boolean isEnabledPopupErrorTip) {
        getDecimalFormatter().setEnabledPopupErrorTip(isEnabledPopupErrorTip);
    }

    /**
     * 获取字段 minimum 的值，允许输入的最小值，用包装对象的，允许为null，表示输入没有下限，在提示的tooltip中用负无穷表示。
     * @return 字段 minimum 的值。
     * @see com.apollo.swing.component.text.DecimalFormatter#getMinimum()
     * @since T3 V1.1
     */
    public BigDecimal getMinimum() {
        return getDecimalFormatter().getMinimum();
    }

    /**
     * 设置字段 minimum 的值，允许输入的最小值，用包装对象的，允许为null，表示输入没有下限，在提示的tooltip中用负无穷表示。
     * @param minimum 字段 minimum 的值。
     * @see com.apollo.swing.component.text.DecimalFormatter#setMinimum(BigDecimal)
     * @since T3 V1.1
     */
    public void setMinimum(BigDecimal minimum) {
        getDecimalFormatter().setMinimum(minimum);
    }

    /**
     * 设置字段 minimum 的值，允许输入的最小值。
     * @param minimum 字段 minimum 的值。
     * @since T3 V1.1
     */
    public void setMinimum(long minimum) {
        setMinimum(BigDecimal.valueOf(minimum));
    }

    /**
     * 设置字段 maximum 的值，允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @return 字段 maximum 的值。
     * @see com.apollo.swing.component.text.DecimalFormatter#getMaximum()
     * @since T3 V1.1
     */
    public BigDecimal getMaximum() {
        return getDecimalFormatter().getMaximum();
    }

    /**
     * 设置字段 maximum 的值，允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @param maximum 字段 maximum 的值。
     * @see com.apollo.swing.component.text.DecimalFormatter#setMaximum(BigDecimal)
     * @since T3 V1.1
     */
    public void setMaximum(BigDecimal maximum) {
        getDecimalFormatter().setMaximum(maximum);
    }

    /**
     * 设置字段 maximum 的值，允许输入的最大值。
     * @param maximum 字段 maximum 的值。
     * @since T3 V1.1
     */
    public void setMaximum(long maximum) {
        setMaximum(BigDecimal.valueOf(maximum));
    }

    /**
     * 获取字段 scale 的值，精度，即小数点后几位，用包装对象的，允许为null，表示输入没有精度，可以无限精度，但必须用包装对象来处理一切运算。
     * @return 字段 scale 的值。
     * @see com.apollo.swing.component.text.DecimalFormatter#getScale()
     * @since T3 V1.1
     */
    public BigInteger getScale() {
        return getDecimalFormatter().getScale();
    }

    /**
     * 设置字段 scale 的值，精度，即小数点后几位，用包装对象的，允许为null，表示输入没有精度，可以无限精度，但必须用包装对象来处理一切运算。
     * @param scale 字段 scale 的值。
     * @see com.apollo.swing.component.text.DecimalFormatter#setScale(BigInteger)
     * @since T3 V1.1
     */
    public void setScale(BigInteger scale) {
        getDecimalFormatter().setScale(scale);
    }

    /**
     * 设置字段 scale 的值，精度，即小数点后几位。
     * @param scale 字段 scale 的值。
     * @since T3 V1.1
     */
    public void setScale(int scale) {
        setScale(BigInteger.valueOf(scale));
    }

    /*------------------------------------- JFormattedTextField Public Method -------------------------------------*/

    /**
     * @deprecated 建议直接使用getDecimal方法。
     * @see javax.swing.JFormattedTextField#getValue()
     * @see com.apollo.swing.component.text.DecimalField#getDecimal()
     */
    public Object getValue() {
        return super.getValue();
    }

    /**
     * @deprecated 建议直接使用setDecimal方法。
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
