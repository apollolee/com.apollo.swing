/*
 * 此代码创建于 2008-3-2 下午03:36:52。
 */
package com.apollo.swing.component.text;

import java.math.BigInteger;

/**
 * <p>文件名称：StringField.java</p>
 * <p>类型描述：字符串输入框，注意：此组件基于JFormattedTextField，因此应用者应该首先了解JFormattedTextField组件的用法。</p>
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
public class StringField extends BaseField {

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
    public StringField() {
        this(0, true, true, null, null, null, true, null, null);
    }

    /**
     * 构造方法。
     * @param minLength 允许输入字符串的最小长度。
     * @param maxLength 允许输入字符串的最大长度。
     * @since T3 V1.1
     */
    public StringField(long minLength, long maxLength) {
        this(minLength, maxLength, null);
    }

    /**
     * 构造方法。
     * @param minLength 允许输入字符串的最小长度。
     * @param maxLength 允许输入字符串的最大长度。
     * @param string 初始化用的整型数值。
     * @since T3 V1.1
     */
    public StringField(long minLength, long maxLength, String string) {
        this(0, true, true, minLength, maxLength, null, true, null, string);
    }

    /**
     * 构造方法。
     * @param columns 列数，这个参数跟JText的那个一样的意思。
     * @param isNeedInput 定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param minLength 允许输入字符串的最小长度。
     * @param maxLength 允许输入字符串的最大长度。
     * @param characters 字符集，是一个字符限制条件，根据legal参数决定是合法字符集还是非法字符集，注意：如果设置了regex则此参数无效，以regex为准。
     * @param legal 标识字符集是否合法，注意：如果设置了regex则此参数无效，以regex为准。
     * @param regex 正则表达式，用于限制输入的字符串，缺省为null。
     * @param string 初始化用的整型数值。
     * @since T3 V1.1
     */
    public StringField(int columns,
                       boolean isNeedInput,
                       boolean isEnabledPopupErrorTip,
                       long minLength,
                       long maxLength,
                       String characters,
                       boolean legal,
                       String regex,
                       String string) {
        this(columns,
             isNeedInput,
             isEnabledPopupErrorTip,
             BigInteger.valueOf(minLength),
             BigInteger.valueOf(maxLength),
             characters,
             legal,
             regex,
             string);
    }

    /**
     * 构造方法。
     * @param columns 列数，这个参数跟JText的那个一样的意思。
     * @param isNeedInput 定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param minLength 允许输入字符串的最小长度，用包装对象的，允许为null，null和0一样。
     * @param maxLength 允许输入字符串的最大长度，用包装对象的，允许为null，表示字符个数没有上限，在提示的tooltip中用正无穷表示。
     * @param characters 字符集，是一个字符限制条件，根据legal参数决定是合法字符集还是非法字符集，注意：如果设置了regex则此参数无效，以regex为准。
     * @param legal 标识字符集是否合法，注意：如果设置了regex则此参数无效，以regex为准。
     * @param regex 正则表达式，用于限制输入的字符串，缺省为null。
     * @param string 初始化用的整型数值。
     * @see com.apollo.swing.component.text.IntegerFormatter#TIntegerFormatter(boolean, boolean, BigInteger, BigInteger, BigInteger)
     * @since T3 V1.1
     */
    public StringField(int columns,
                       boolean isNeedInput,
                       boolean isEnabledPopupErrorTip,
                       BigInteger minLength,
                       BigInteger maxLength,
                       String characters,
                       boolean legal,
                       String regex,
                       String string) {
        super(new StringFormatter(isNeedInput, isEnabledPopupErrorTip, minLength, maxLength, characters, legal, regex));

        setColumns(columns);
        setString(string);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 获取字符串，注意：此处获取的值并不就是界面上的值，只有commitEdit以后，界面上的值才会存入模型，这点和JFormattedTextField完全一样。
     * @return 字符串，如果为null表示未输入或输入为空。
     * @since T3 V1.1
     */
    public String getString() {
        return (String) getValue();
    }

    /**
     * 设置字符串，注意：应用必须保证设置的字符串是合法的。
     * @param string 字符串，为null表示输入为空。
     * @since T3 V1.1
     */
    public void setString(String string) {
        setValue(string);
    }

    /**
     * 获取字符串格式器。
     * @return 字符串格式器。
     * @since T3 V1.1
     */
    public StringFormatter getStringFormatter() {
        return (StringFormatter) getFormatter();
    }

    /**
     * 获取字段 isNeedInput 的值，定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @return 字段 isNeedInput 的值。
     * @see com.apollo.swing.component.text.StringFormatter#isNeedInput()
     * @since T3 V1.1
     */
    public boolean isNeedInput() {
        return getStringFormatter().isNeedInput();
    }

    /**
     * 设置字段 isNeedInput 的值，定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isNeedInput 字段 isNeedInput 的值。
     * @see com.apollo.swing.component.text.StringFormatter#setNeedInput(boolean)
     * @since T3 V1.1
     */
    public void setNeedInput(boolean isNeedInput) {
        getStringFormatter().setNeedInput(isNeedInput);
    }

    /**
     * 获取字段 isEnabledPopupErrorTip 的值，当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @return 字段 isEnabledPopupErrorTip 的值。
     * @see com.apollo.swing.component.text.StringFormatter#isEnabledPopupErrorTip()
     * @since T3 V1.1
     */
    public boolean isEnabledPopupErrorTip() {
        return getStringFormatter().isEnabledPopupErrorTip();
    }

    /**
     * 设置字段 isEnabledPopupErrorTip 的值，当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param isEnabledPopupErrorTip 字段 isEnabledPopupErrorTip 的值。
     * @see com.apollo.swing.component.text.StringFormatter#setEnabledPopupErrorTip(boolean)
     * @since T3 V1.1
     */
    public void setEnabledPopupErrorTip(boolean isEnabledPopupErrorTip) {
        getStringFormatter().setEnabledPopupErrorTip(isEnabledPopupErrorTip);
    }

    /**
     * 获取字段 minLength 的值，允许输入字符串的最小长度，用包装对象的，允许为null，null和0一样。
     * @return 字段 minLength 的值。
     * @see com.apollo.swing.component.text.StringFormatter#getMinLength()
     * @since T3 V1.1
     */
    public BigInteger getMinLength() {
        return getStringFormatter().getMinLength();
    }

    /**
     * 设置字段 minLength 的值，允许输入字符串的最小长度，用包装对象的，允许为null，null和0一样。
     * @param minLength 字段 minLength 的值。
     * @see com.apollo.swing.component.text.StringFormatter#setMinLength(BigInteger)
     * @since T3 V1.1
     */
    public void setMinLength(BigInteger minLength) {
        getStringFormatter().setMinLength(minLength);
    }

    /**
     * 设置字段 minLength 的值，允许输入字符串的最小长度。
     * @param minLength 字段 minLength 的值。
     * @since T3 V1.1
     */
    public void setMinLength(long minLength) {
        setMinLength(BigInteger.valueOf(minLength));
    }

    /**
     * 设置字段 maxLength 的值，允许输入字符串的最大长度，用包装对象的，允许为null，表示字符个数没有上限，在提示的tooltip中用正无穷表示。
     * @return 字段 maxLength 的值。
     * @see com.apollo.swing.component.text.StringFormatter#getMaxLength()
     * @since T3 V1.1
     */
    public BigInteger getMaxLength() {
        return getStringFormatter().getMaxLength();
    }

    /**
     * 设置字段 maxLength 的值，允许输入字符串的最大长度，用包装对象的，允许为null，表示字符个数没有上限，在提示的tooltip中用正无穷表示。
     * @param maxLength 字段 maxLength 的值。
     * @see com.apollo.swing.component.text.StringFormatter#setMaxLength(BigInteger)
     * @since T3 V1.1
     */
    public void setMaxLength(BigInteger maxLength) {
        getStringFormatter().setMaxLength(maxLength);
    }

    /**
     * 设置字段 maxLength 的值，允许输入字符串的最大长度。
     * @param maxLength 字段 maxLength 的值。
     * @since T3 V1.1
     */
    public void setMaxLength(long maxLength) {
        setMaxLength(BigInteger.valueOf(maxLength));
    }

    /**
     * 获取字段 characters 的值，字符集，是一个字符限制条件，根据legal参数决定是合法字符集还是非法字符集，注意：如果设置了regex则此参数无效，以regex为准。
     * @return 字段 characters 的值。
     * @see com.apollo.swing.component.text.StringFormatter#getCharacters()
     * @since T3 V1.1
     */
    public String getCharacters() {
        return getStringFormatter().getCharacters();
    }

    /**
     * 设置字段 characters 的值，字符集，是一个字符限制条件，根据legal参数决定是合法字符集还是非法字符集，注意：如果设置了regex则此参数无效，以regex为准。
     * @param characters 字段 characters 的值。
     * @see com.apollo.swing.component.text.StringFormatter#setCharacters(String)
     * @since T3 V1.1
     */
    public void setCharacters(String characters) {
        getStringFormatter().setCharacters(characters);
    }

    /**
     * 获取字段 legal 的值，标识字符集是否合法，注意：如果设置了regex则此参数无效，以regex为准。
     * @return 字段 legal 的值。
     * @see com.apollo.swing.component.text.StringFormatter#isLegal()
     * @since T3 V1.1
     */
    public boolean isLegal() {
        return getStringFormatter().isLegal();
    }

    /**
     * 设置字段 legal 的值，标识字符集是否合法，注意：如果设置了regex则此参数无效，以regex为准。
     * @param legal 字段 legal 的值。
     * @see com.apollo.swing.component.text.StringFormatter#setLegal(boolean)
     * @since T3 V1.1
     */
    public void setLegal(boolean legal) {
        getStringFormatter().setLegal(legal);
    }

    /**
     * 获取字段 regex 的值，正则表达式，用于限制输入的字符串，缺省为null。
     * @return 字段 regex 的值。
     * @see com.apollo.swing.component.text.StringFormatter#getRegex()
     * @since T3 V1.1
     */
    public String getRegex() {
        return getStringFormatter().getRegex();
    }

    /**
     * 设置字段 regex 的值，正则表达式，用于限制输入的字符串，缺省为null。
     * @param regex 字段 regex 的值。
     * @see com.apollo.swing.component.text.StringFormatter#setRegex(String)
     * @since T3 V1.1
     */
    public void setRegex(String regex) {
        getStringFormatter().setRegex(regex);
    }

    /*------------------------------------- JFormattedTextField Public Method -------------------------------------*/

    /**
     * @deprecated 建议直接使用getString方法。
     * @see javax.swing.JFormattedTextField#getValue()
     * @see StringField#getString()
     */
    public Object getValue() {
        return super.getValue();
    }

    /**
     * @deprecated 建议直接使用setString方法。
     * @see javax.swing.JFormattedTextField#setValue(java.lang.Object)
     * @see StringField#setString(String)
     */
    public void setValue(Object value) {
        super.setValue(value);
    }

    /**
     * @see javax.swing.JFormattedTextField#setFormatterFactory(javax.swing.JFormattedTextField.AbstractFormatterFactory)
     */
    public void setFormatterFactory(AbstractFormatterFactory ff) {
        if (ff != null && ff.getFormatter(this) instanceof StringFormatter) {
            super.setFormatterFactory(ff);

        } else {
            throw new IllegalArgumentException("TStringField can not set non TStringFormatter !");
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- JFormattedTextField Protected Method -------------------------------------*/

    /**
     * @see javax.swing.JFormattedTextField#setFormatter(javax.swing.JFormattedTextField.AbstractFormatter)
     */
    protected void setFormatter(AbstractFormatter af) {
        if (af instanceof StringFormatter) {
            super.setFormatter(af);

        } else {
            throw new IllegalArgumentException("TStringField can not set non TStringFormatter !");
        }
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
