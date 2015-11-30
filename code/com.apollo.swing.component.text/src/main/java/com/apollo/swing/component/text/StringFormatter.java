/*
 * �˴��봴���� 2009-3-31 ����09:34:28��
 */
package com.apollo.swing.component.text;

import java.math.BigInteger;
import java.text.ParseException;

/**
 * <p>�ļ����ƣ�StringFormatter.java</p>
 * <p>�����������ַ�����ʽ����ע�⣺���������JFormattedTextField�����Ӧ����Ӧ�������˽�JFormattedTextField������÷���</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2009-3-31</p>
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
     * ��С���ȡ�
     */
    protected BigInteger m_minLength;

    /**
     * ��󳤶ȡ�
     */
    protected BigInteger m_maxLength;

    /**
     * �ַ�����
     */
    protected String m_characters;

    /**
     * ��ʶ�ַ����Ƿ�Ϸ���
     */
    protected boolean m_legal;

    /**
     * ������ʽ��
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
     * ���췽����
     * @since T3 V1.1
     */
    public StringFormatter() {
        this(true, true, null, null, null, false, null);
    }

    /**
     * ���췽����
     * @param isNeedInput ������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip �����벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param minLength ���������ַ�������С���ȣ��ð�װ����ģ�����Ϊnull��null��0һ����
     * @param maxLength ���������ַ�������󳤶ȣ��ð�װ����ģ�����Ϊnull����ʾ�ַ�����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param characters �ַ�������һ���ַ���������������legal���������ǺϷ��ַ������ǷǷ��ַ�����ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @param legal ��ʶ�ַ����Ƿ�Ϸ���ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @param regex ������ʽ����������������ַ�����ȱʡΪnull��
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
     * ��ȡ�ֶ� minLength ��ֵ�����������ַ�������С���ȣ��ð�װ����ģ�����Ϊnull��null��0һ����
     * @return �ֶ� minLength ��ֵ��
     * @since T3 V1.1
     */
    public BigInteger getMinLength() {
        return m_minLength;
    }

    /**
     * �����ֶ� minLength ��ֵ�����������ַ�������С���ȣ��ð�װ����ģ�����Ϊnull��null��0һ����
     * @param minLength �ֶ� minLength ��ֵ��
     * @since T3 V1.1
     */
    public void setMinLength(BigInteger minLength) {
        /* �����ظ����� */
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
     * �����ֶ� maxLength ��ֵ�����������ַ�������󳤶ȣ��ð�װ����ģ�����Ϊnull����ʾ�ַ�����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @return �ֶ� maxLength ��ֵ��
     * @since T3 V1.1
     */
    public BigInteger getMaxLength() {
        return m_maxLength;
    }

    /**
     * �����ֶ� maxLength ��ֵ�����������ַ�������󳤶ȣ��ð�װ����ģ�����Ϊnull����ʾ�ַ�����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param maxLength �ֶ� maxLength ��ֵ��
     * @since T3 V1.1
     */
    public void setMaxLength(BigInteger maxLength) {
        /* �����ظ����� */
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
     * ��ȡ�ֶ� characters ��ֵ���ַ�������һ���ַ���������������legal���������ǺϷ��ַ������ǷǷ��ַ�����ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @return �ֶ� characters ��ֵ��
     * @since T3 V1.1
     */
    public String getCharacters() {
        return m_characters;
    }

    /**
     * �����ֶ� characters ��ֵ���ַ�������һ���ַ���������������legal���������ǺϷ��ַ������ǷǷ��ַ�����ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @param characters �ֶ� characters ��ֵ��
     * @since T3 V1.1
     */
    public void setCharacters(String characters) {
        m_characters = characters;
        updateTipText();
    }

    /**
     * ��ȡ�ֶ� legal ��ֵ����ʶ�ַ����Ƿ�Ϸ���ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @return �ֶ� legal ��ֵ��
     * @since T3 V1.1
     */
    public boolean isLegal() {
        return m_legal;
    }

    /**
     * �����ֶ� legal ��ֵ����ʶ�ַ����Ƿ�Ϸ���ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @param legal �ֶ� legal ��ֵ��
     * @since T3 V1.1
     */
    public void setLegal(boolean legal) {
        m_legal = legal;
        updateTipText();
    }

    /**
     * ��ȡ�ֶ� regex ��ֵ��������ʽ����������������ַ�����ȱʡΪnull��
     * @return �ֶ� regex ��ֵ��
     * @since T3 V1.1
     */
    public String getRegex() {
        return m_regex;
    }

    /**
     * �����ֶ� regex ��ֵ��������ʽ����������������ַ�����ȱʡΪnull��
     * @param regex �ֶ� regex ��ֵ��
     * @since T3 V1.1
     */
    public void setRegex(String regex) {
        m_regex = regex;
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
        if ("".equals(text) && !m_isNeedInput) {
            return null;
        }

        return text;
    }

    /**
     * @see javax.swing.JFormattedTextField.AbstractFormatter#valueToString(java.lang.Object)
     * @throws ParseException ע�⣺����׳����쳣����ôtext�ı����������Ϊ�ա�
     */
    public String valueToString(Object value) throws ParseException {
        /* �ж�Valueֵ�Ƿ�Ϸ� */
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
     * ��֤�ƶ��Ĺ����Ƿ�Ϸ���
     * @return Ϊtrue��ʾ�ƶ��Ĺ���Ϸ���Ϊfalse��ʾ�ƶ��Ĺ��򲻺Ϸ���
     */
    protected boolean verifyRule() {
        /* ��С���Ȳ���С��0 */
        if (m_minLength != null && m_minLength.compareTo(BigInteger.ZERO) < 0) {
            return false;
        }

        /* ��Сֵ�����ֵֻҪ��һ�������ã���ô��Ӧ���й����ǺϷ��� */
        if (m_minLength == null || m_maxLength == null) {
            return true;
        }

        /* ��Сֵ�������ֵ���������ֵС����Сֵ���ǲ��Ϸ��� */
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

        /* ��֤��С���� */
        if (isFull && m_minLength != null && length < m_minLength.intValue()) {
            throwParseException(I18N_INFO_LENGTHISTO0SHORT);
        }

        /* ��֤��󳤶� */
        if (m_maxLength != null && length > m_maxLength.intValue()) {
            throwParseException(I18N_INFO_LENGTHISTO0LONG);
        }

        /* ��֤�ַ��� */
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

        /* ��֤������ʽ */
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
