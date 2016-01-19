/*
 * 此代码创建于 2008-3-2 下午03:36:52。
 */
package com.apollo.swing.component.text;

import java.math.BigInteger;

/**
 * <p>文件名称：IntegerField.java</p>
 * <p>类型描述：整型数输入框，注意：此组件基于JFormattedTextField，因此应用者应该首先了解JFormattedTextField组件的用法。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-3-2</p>
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
     * 构造方法。
     * @since T3 V1.1
     */
    public IntegerField() {
        this(0, true, true, null, null, null, null, null);
    }

    /**
     * 构造方法。
     * @param columns 列数，这个参数跟JText的那个一样的意思。
     * @param minimum 允许输入的最小值。
     * @param maximum 允许输入的最大值。
     * @since T3 V1.1
     */
    public IntegerField(int columns, long minimum, long maximum) {
        this(columns, true, true, BigInteger.valueOf(minimum), BigInteger.valueOf(maximum), null, null, null);
    }

    /**
     * 构造方法。
     * @param columns 列数，这个参数跟JText的那个一样的意思。
     * @param minimum 允许输入的最小值。
     * @param maximum 允许输入的最大值。
     * @param value 初始化用的整型数值。
     * @since T3 V1.1
     */
    public IntegerField(int columns, long minimum, long maximum, long value) {
        this(columns, true, true, BigInteger.valueOf(minimum), BigInteger.valueOf(maximum), null, null, BigInteger.valueOf(value));
    }

    /**
     * 构造方法。
     * @param columns 列数，这个参数跟JText的那个一样的意思。
     * @param minimum 允许输入的最小值。
     * @param maximum 允许输入的最大值。
     * @param step 步长，即从步长基值开始计算，输入的数都要是步长值的整数倍，步长缺省值为1，步长基值缺省为0。
     * @param value 初始化用的整型数值。
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
     * 构造方法。
     * @param columns 列数，这个参数跟JText的那个一样的意思。
     * @param minimum 允许输入的最小值。
     * @param maximum 允许输入的最大值。
     * @param step 步长，即从步长基值开始计算，输入的数都要是步长值的整数倍，步长缺省值为1，步长基值缺省为0。
     * @param stepBase 步长基值，即从步长基值开始计算，输入的数都要是步长值的整数倍，步长缺省值为1，步长基值缺省为0。
     * @param value 初始化用的整型数值。
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
     * 构造方法。
     * @param columns 列数，这个参数跟JText的那个一样的意思。
     * @param isNeedInput 定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param minimum 允许输入的最小值。
     * @param maximum 允许输入的最大值。
     * @param step 步长，即从步长基值开始计算，输入的数都要是步长值的整数倍，步长缺省值为1，步长基值缺省为0。
     * @param stepBase 步长基值，即从步长基值开始计算，输入的数都要是步长值的整数倍，步长缺省值为1，步长基值缺省为0。
     * @param value 初始化用的整型数值。
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
     * 构造方法。
     * @param columns 列数，这个参数跟JText的那个一样的意思。
     * @param isNeedInput 定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param minimum 允许输入的最小值，用包装对象的，允许为null，表示输入没有下限，在提示的tooltip中用负无穷表示。
     * @param maximum 允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @param step 步长，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
     * @param stepBase 步长基值，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
     * @param value 初始化用的整型数值。
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
     * 获取整数，注意：此处获取的值并不就是界面上的值，只有commitEdit以后，界面上的值才会存入模型，这点和JFormattedTextField完全一样。
     * @return 整数，如果为null表示未输入或输入为空。
     * @since T3 V1.1
     */
    public BigInteger getInteger() {
        return (BigInteger) getValue();
    }

    /**
     * 设置整数，注意：应用必须保证设置的整数是合法的。
     * @param integer 整数，为null表示输入为空。
     * @since T3 V1.1
     */
    public void setInteger(BigInteger integer) {
        setValue(integer);
    }

    /**
     * 设置整数，注意：应用必须保证设置的整数是合法的。
     * @param integer 整数。
     * @since T3 V1.1
     */
    public void setInteger(long integer) {
        setInteger(BigInteger.valueOf(integer));
    }

    /**
     * 获取整数格式器。
     * @return 整数格式器。
     * @since T3 V1.1
     */
    public IntegerFormatter getIntegerFormatter() {
        return (IntegerFormatter) getFormatter();
    }

    /**
     * 获取字段 isNeedInput 的值，定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @return 字段 isNeedInput 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#isNeedInput()
     * @since T3 V1.1
     */
    public boolean isNeedInput() {
        return getIntegerFormatter().isNeedInput();
    }

    /**
     * 设置字段 isNeedInput 的值，定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isNeedInput 字段 isNeedInput 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#setNeedInput(boolean)
     * @since T3 V1.1
     */
    public void setNeedInput(boolean isNeedInput) {
        getIntegerFormatter().setNeedInput(isNeedInput);
    }

    /**
     * 获取字段 isEnabledPopupErrorTip 的值，当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @return 字段 isEnabledPopupErrorTip 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#isEnabledPopupErrorTip()
     * @since T3 V1.1
     */
    public boolean isEnabledPopupErrorTip() {
        return getIntegerFormatter().isEnabledPopupErrorTip();
    }

    /**
     * 设置字段 isEnabledPopupErrorTip 的值，当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param isEnabledPopupErrorTip 字段 isEnabledPopupErrorTip 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#setEnabledPopupErrorTip(boolean)
     * @since T3 V1.1
     */
    public void setEnabledPopupErrorTip(boolean isEnabledPopupErrorTip) {
        getIntegerFormatter().setEnabledPopupErrorTip(isEnabledPopupErrorTip);
    }

    /**
     * 获取字段 minimum 的值，允许输入的最小值，用包装对象的，允许为null，表示输入没有下限，在提示的tooltip中用负无穷表示。
     * @return 字段 minimum 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#getMinimum()
     * @since T3 V1.1
     */
    public BigInteger getMinimum() {
        return getIntegerFormatter().getMinimum();
    }

    /**
     * 设置字段 minimum 的值，允许输入的最小值，用包装对象的，允许为null，表示输入没有下限，在提示的tooltip中用负无穷表示。
     * @param minimum 字段 minimum 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#setMinimum(BigInteger)
     * @since T3 V1.1
     */
    public void setMinimum(BigInteger minimum) {
        getIntegerFormatter().setMinimum(minimum);
    }

    /**
     * 设置字段 minimum 的值，允许输入的最小值。
     * @param minimum 字段 minimum 的值。
     * @since T3 V1.1
     */
    public void setMinimum(long minimum) {
        setMinimum(BigInteger.valueOf(minimum));
    }

    /**
     * 设置字段 maximum 的值，允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @return 字段 maximum 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#getMaximum()
     * @since T3 V1.1
     */
    public BigInteger getMaximum() {
        return getIntegerFormatter().getMaximum();
    }

    /**
     * 设置字段 maximum 的值，允许输入的最大值，用包装对象的，允许为null，表示输入没有上限，在提示的tooltip中用正无穷表示。
     * @param maximum 字段 maximum 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#setMaximum(BigInteger)
     * @since T3 V1.1
     */
    public void setMaximum(BigInteger maximum) {
        getIntegerFormatter().setMaximum(maximum);
    }

    /**
     * 设置字段 maximum 的值，允许输入的最大值。
     * @param maximum 字段 maximum 的值。
     * @since T3 V1.1
     */
    public void setMaximum(long maximum) {
        setMaximum(BigInteger.valueOf(maximum));
    }

    /**
     * 获取字段 step 的值，步长，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
     * @return 字段 step 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#getStep()
     * @since T3 V1.1
     */
    public BigInteger getStep() {
        return getIntegerFormatter().getStep();
    }

    /**
     * 设置字段 step 的值，步长，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
     * @param step 字段 step 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#setStep(BigInteger)
     * @since T3 V1.1
     */
    public void setStep(BigInteger step) {
        getIntegerFormatter().setStep(step);
    }

    /**
     * 设置字段 step 的值，步长，即从步长基值开始计算，输入的数都要是步长值的整数倍，步长缺省值为1，步长基值缺省为0。
     * @param step 字段 step 的值。
     * @since T3 V1.1
     */
    public void setStep(long step) {
        setStep(BigInteger.valueOf(step));
    }

    /**
     * 获取字段 stepBase 的值，步长基值，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
     * @return 字段 stepBase 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#getStepBase()
     * @since T3 V1.1
     */
    public BigInteger getStepBase() {
        return getIntegerFormatter().getStepBase();
    }

    /**
     * 设置字段 stepBase 的值步长基值，即从步长基值开始计算，输入的数都要是步长值的整数倍，用包装对象的，允许为null，表示使用缺省值，步长缺省值为1，步长基值缺省为0。
     * @param stepBase 字段 stepBase 的值。
     * @see com.apollo.swing.component.text.IntegerFormatter#setStepBase(BigInteger)
     * @since T3 V1.1
     */
    public void setStepBase(BigInteger stepBase) {
        getIntegerFormatter().setStepBase(stepBase);
    }

    /**
     * 设置字段 stepBase 的值步长基值，即从步长基值开始计算，输入的数都要是步长值的整数倍，步长缺省值为1，步长基值缺省为0。
     * @param stepBase 字段 stepBase 的值。
     * @since T3 V1.1
     */
    public void setStepBase(long stepBase) {
        setStepBase(BigInteger.valueOf(stepBase));
    }

    /*------------------------------------- JFormattedTextField Public Method -------------------------------------*/

    /**
     * @deprecated 建议直接使用getInteger方法。
     * @see javax.swing.JFormattedTextField#getValue()
     * @see com.apollo.swing.component.text.IntegerField#getInteger()
     */
    public Object getValue() {
        return super.getValue();
    }

    /**
     * @deprecated 建议直接使用setInteger方法。
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
