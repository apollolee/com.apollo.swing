/*
 * 此代码创建于 2009-3-31 上午09:34:28。
 */
package com.apollo.swing.component.text;

import java.math.BigInteger;
import java.text.ParseException;

/**
 * <p>文件名称：StringFormatter.java</p>
 * <p>类型描述：字符串格式器，注意：此组件基于JFormattedTextField，因此应用者应该首先了解JFormattedTextField组件的用法。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2009-3-31</p>
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
public class StringFormatter extends BaseFormatter {

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
     * 最小长度。
     */
    protected BigInteger m_minLength;

    /**
     * 最大长度。
     */
    protected BigInteger m_maxLength;

    /**
     * 字符集。
     */
    protected String m_characters;

    /**
     * 标识字符集是否合法。
     */
    protected boolean m_legal;

    /**
     * 正则表达式。
     */
    protected String m_regex;

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
    public StringFormatter() {
        this(true, true, null, null, null, false, null);
    }

    /**
     * 构造方法。
     * @param isNeedInput 定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param minLength 允许输入字符串的最小长度，用包装对象的，允许为null，null和0一样。
     * @param maxLength 允许输入字符串的最大长度，用包装对象的，允许为null，表示字符个数没有上限，在提示的tooltip中用正无穷表示。
     * @param characters 字符集，是一个字符限制条件，根据legal参数决定是合法字符集还是非法字符集，注意：如果设置了regex则此参数无效，以regex为准。
     * @param legal 标识字符集是否合法，注意：如果设置了regex则此参数无效，以regex为准。
     * @param regex 正则表达式，用于限制输入的字符串，缺省为null。
     * @since T3 V1.1
     */
    public StringFormatter(boolean isNeedInput,
                           boolean isEnabledPopupErrorTip,
                           BigInteger minLength,
                           BigInteger maxLength,
                           String characters,
                           boolean legal,
                           String regex) {
        super(isNeedInput, isEnabledPopupErrorTip);

        setMinLength(minLength);
        setMaxLength(maxLength);
        setCharacters(characters);
        setLegal(legal);
        setRegex(regex);

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
     * 获取字段 minLength 的值，允许输入字符串的最小长度，用包装对象的，允许为null，null和0一样。
     * @return 字段 minLength 的值。
     * @since T3 V1.1
     */
    public BigInteger getMinLength() {
        return m_minLength;
    }

    /**
     * 设置字段 minLength 的值，允许输入字符串的最小长度，用包装对象的，允许为null，null和0一样。
     * @param minLength 字段 minLength 的值。
     * @since T3 V1.1
     */
    public void setMinLength(BigInteger minLength) {
        /* 过滤重复设置 */
        if (isRepeated(m_minLength, minLength)) {
            return;
        }

        BigInteger oldMinLength = m_minLength;
        m_minLength = minLength;

        if (!verifyRule()) {
            m_minLength = oldMinLength;
            throw new IllegalArgumentException("Set min length is failed ! [MinLength = " + minLength + "]");
        }

        updateTipText();
    }

    /**
     * 设置字段 maxLength 的值，允许输入字符串的最大长度，用包装对象的，允许为null，表示字符个数没有上限，在提示的tooltip中用正无穷表示。
     * @return 字段 maxLength 的值。
     * @since T3 V1.1
     */
    public BigInteger getMaxLength() {
        return m_maxLength;
    }

    /**
     * 设置字段 maxLength 的值，允许输入字符串的最大长度，用包装对象的，允许为null，表示字符个数没有上限，在提示的tooltip中用正无穷表示。
     * @param maxLength 字段 maxLength 的值。
     * @since T3 V1.1
     */
    public void setMaxLength(BigInteger maxLength) {
        /* 过滤重复设置 */
        if (isRepeated(m_maxLength, maxLength)) {
            return;
        }

        BigInteger oldMaxLength = m_maxLength;
        m_maxLength = maxLength;

        if (!verifyRule()) {
            m_maxLength = oldMaxLength;
            throw new IllegalArgumentException("Set max length is failed ! [MaxLength = " + maxLength + "]");
        }

        updateTipText();
    }

    /**
     * 获取字段 characters 的值，字符集，是一个字符限制条件，根据legal参数决定是合法字符集还是非法字符集，注意：如果设置了regex则此参数无效，以regex为准。
     * @return 字段 characters 的值。
     * @since T3 V1.1
     */
    public String getCharacters() {
        return m_characters;
    }

    /**
     * 设置字段 characters 的值，字符集，是一个字符限制条件，根据legal参数决定是合法字符集还是非法字符集，注意：如果设置了regex则此参数无效，以regex为准。
     * @param characters 字段 characters 的值。
     * @since T3 V1.1
     */
    public void setCharacters(String characters) {
        m_characters = characters;
        updateTipText();
    }

    /**
     * 获取字段 legal 的值，标识字符集是否合法，注意：如果设置了regex则此参数无效，以regex为准。
     * @return 字段 legal 的值。
     * @since T3 V1.1
     */
    public boolean isLegal() {
        return m_legal;
    }

    /**
     * 设置字段 legal 的值，标识字符集是否合法，注意：如果设置了regex则此参数无效，以regex为准。
     * @param legal 字段 legal 的值。
     * @since T3 V1.1
     */
    public void setLegal(boolean legal) {
        m_legal = legal;
        updateTipText();
    }

    /**
     * 获取字段 regex 的值，正则表达式，用于限制输入的字符串，缺省为null。
     * @return 字段 regex 的值。
     * @since T3 V1.1
     */
    public String getRegex() {
        return m_regex;
    }

    /**
     * 设置字段 regex 的值，正则表达式，用于限制输入的字符串，缺省为null。
     * @param regex 字段 regex 的值。
     * @since T3 V1.1
     */
    public void setRegex(String regex) {
        m_regex = regex;
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
        if ("".equals(text) && !m_isNeedInput) {
            return null;
        }

        return text;
    }

    /**
     * @see javax.swing.JFormattedTextField.AbstractFormatter#valueToString(java.lang.Object)
     * @throws ParseException 注意：如果抛出此异常，那么text文本内容则会置为空。
     */
    public String valueToString(Object value) throws ParseException {
        /* 判断Value值是否合法 */
        if (value == null || !(value instanceof String)) {
            return "";

        } else {
            String text = (String) value;
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
        /* 最小长度不能小于0 */
        if (m_minLength != null && m_minLength.compareTo(BigInteger.ZERO) < 0) {
            return false;
        }

        /* 最小值和最大值只要有一个不设置，那么对应所有规则都是合法的 */
        if (m_minLength == null || m_maxLength == null) {
            return true;
        }

        /* 最小值大于最大值（或者最大值小于最小值）是不合法的 */
        if (m_minLength.compareTo(m_maxLength) > 0) {
            return false;
        }

        return true;
    }

    /*------------------------------------- TAbstractFormatter Protected Method -------------------------------------*/

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#verify(java.lang.String, boolean)
     */
    protected void verify(String text, boolean isFull) throws ParseException {
        int length = text.length();

        /* 验证最小长度 */
        if (isFull && m_minLength != null && length < m_minLength.intValue()) {
            throwParseException(I18N_INFO_LENGTHISTO0SHORT);
        }

        /* 验证最大长度 */
        if (m_maxLength != null && length > m_maxLength.intValue()) {
            throwParseException(I18N_INFO_LENGTHISTO0LONG);
        }

        /* 验证字符集 */
        if (m_characters != null) {
            char[] characters = text.toCharArray();
            for (int i = 0; i < characters.length; i++) {
                if (m_legal) {
                    if (m_characters.indexOf(characters[i]) == -1) {
                        throwParseException(I18N_INFO_CHARACTERISILLEGAL);
                    }

                } else {
                    if (m_characters.indexOf(characters[i]) != -1) {
                        throwParseException(I18N_INFO_CHARACTERISILLEGAL);
                    }
                }
            }
        }

        /* 验证正则表达式 */
        if (isFull && m_regex != null && !text.matches(m_regex)) {
            throwParseException(I18N_INFO_FORMATISERROR);
        }
    }

    /**
     * @see com.apollo.swing.component.text.BaseFormatter#updateTipText()
     */
    protected void updateTipText() {
        StringBuffer sb = new StringBuffer();

        sb.append(I18N_INFO_LENGTHRANGE).append(": [");
        sb.append(m_minLength == null ? "0" : m_minLength.toString());
        sb.append(" , ").append(m_maxLength == null ? "\u221e" : m_maxLength.toString()).append("]");

        if (m_characters != null) {
            sb.append(" , ").append(m_legal ? I18N_INFO_LEGALCHARACTER : I18N_INFO_ILLEGALCHARACTER).append(": ").append(m_characters);
        }

        if (m_regex != null) {
            sb.append(" , ").append(I18N_INFO_REGEX).append(": ").append(m_regex);
        }

        m_tipText = sb.toString();

        super.updateTipText();
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
