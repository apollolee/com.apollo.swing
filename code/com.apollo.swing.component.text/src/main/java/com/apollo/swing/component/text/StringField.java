/*
 * �˴��봴���� 2008-3-2 ����03:36:52��
 */
package com.apollo.swing.component.text;

import java.math.BigInteger;

/**
 * <p>�ļ����ƣ�StringField.java</p>
 * <p>�����������ַ��������ע�⣺���������JFormattedTextField�����Ӧ����Ӧ�������˽�JFormattedTextField������÷���</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-3-2</p>
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
     * ���췽����
     * @since T3 V1.1
     */
    public StringField() {
        this(0, true, true, null, null, null, true, null, null);
    }

    /**
     * ���췽����
     * @param minLength ���������ַ�������С���ȡ�
     * @param maxLength ���������ַ�������󳤶ȡ�
     * @since T3 V1.1
     */
    public StringField(long minLength, long maxLength) {
        this(minLength, maxLength, null);
    }

    /**
     * ���췽����
     * @param minLength ���������ַ�������С���ȡ�
     * @param maxLength ���������ַ�������󳤶ȡ�
     * @param string ��ʼ���õ�������ֵ��
     * @since T3 V1.1
     */
    public StringField(long minLength, long maxLength, String string) {
        this(0, true, true, minLength, maxLength, null, true, null, string);
    }

    /**
     * ���췽����
     * @param columns ���������������JText���Ǹ�һ������˼��
     * @param isNeedInput ������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip �����벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param minLength ���������ַ�������С���ȡ�
     * @param maxLength ���������ַ�������󳤶ȡ�
     * @param characters �ַ�������һ���ַ���������������legal���������ǺϷ��ַ������ǷǷ��ַ�����ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @param legal ��ʶ�ַ����Ƿ�Ϸ���ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @param regex ������ʽ����������������ַ�����ȱʡΪnull��
     * @param string ��ʼ���õ�������ֵ��
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
     * ���췽����
     * @param columns ���������������JText���Ǹ�һ������˼��
     * @param isNeedInput ������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip �����벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param minLength ���������ַ�������С���ȣ��ð�װ����ģ�����Ϊnull��null��0һ����
     * @param maxLength ���������ַ�������󳤶ȣ��ð�װ����ģ�����Ϊnull����ʾ�ַ�����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param characters �ַ�������һ���ַ���������������legal���������ǺϷ��ַ������ǷǷ��ַ�����ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @param legal ��ʶ�ַ����Ƿ�Ϸ���ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @param regex ������ʽ����������������ַ�����ȱʡΪnull��
     * @param string ��ʼ���õ�������ֵ��
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
     * ��ȡ�ַ�����ע�⣺�˴���ȡ��ֵ�������ǽ����ϵ�ֵ��ֻ��commitEdit�Ժ󣬽����ϵ�ֵ�Ż����ģ�ͣ�����JFormattedTextField��ȫһ����
     * @return �ַ��������Ϊnull��ʾδ���������Ϊ�ա�
     * @since T3 V1.1
     */
    public String getString() {
        return (String) getValue();
    }

    /**
     * �����ַ�����ע�⣺Ӧ�ñ��뱣֤���õ��ַ����ǺϷ��ġ�
     * @param string �ַ�����Ϊnull��ʾ����Ϊ�ա�
     * @since T3 V1.1
     */
    public void setString(String string) {
        setValue(string);
    }

    /**
     * ��ȡ�ַ�����ʽ����
     * @return �ַ�����ʽ����
     * @since T3 V1.1
     */
    public StringFormatter getStringFormatter() {
        return (StringFormatter) getFormatter();
    }

    /**
     * ��ȡ�ֶ� isNeedInput ��ֵ��������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @return �ֶ� isNeedInput ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#isNeedInput()
     * @since T3 V1.1
     */
    public boolean isNeedInput() {
        return getStringFormatter().isNeedInput();
    }

    /**
     * �����ֶ� isNeedInput ��ֵ��������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isNeedInput �ֶ� isNeedInput ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#setNeedInput(boolean)
     * @since T3 V1.1
     */
    public void setNeedInput(boolean isNeedInput) {
        getStringFormatter().setNeedInput(isNeedInput);
    }

    /**
     * ��ȡ�ֶ� isEnabledPopupErrorTip ��ֵ�������벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @return �ֶ� isEnabledPopupErrorTip ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#isEnabledPopupErrorTip()
     * @since T3 V1.1
     */
    public boolean isEnabledPopupErrorTip() {
        return getStringFormatter().isEnabledPopupErrorTip();
    }

    /**
     * �����ֶ� isEnabledPopupErrorTip ��ֵ�������벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param isEnabledPopupErrorTip �ֶ� isEnabledPopupErrorTip ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#setEnabledPopupErrorTip(boolean)
     * @since T3 V1.1
     */
    public void setEnabledPopupErrorTip(boolean isEnabledPopupErrorTip) {
        getStringFormatter().setEnabledPopupErrorTip(isEnabledPopupErrorTip);
    }

    /**
     * ��ȡ�ֶ� minLength ��ֵ�����������ַ�������С���ȣ��ð�װ����ģ�����Ϊnull��null��0һ����
     * @return �ֶ� minLength ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#getMinLength()
     * @since T3 V1.1
     */
    public BigInteger getMinLength() {
        return getStringFormatter().getMinLength();
    }

    /**
     * �����ֶ� minLength ��ֵ�����������ַ�������С���ȣ��ð�װ����ģ�����Ϊnull��null��0һ����
     * @param minLength �ֶ� minLength ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#setMinLength(BigInteger)
     * @since T3 V1.1
     */
    public void setMinLength(BigInteger minLength) {
        getStringFormatter().setMinLength(minLength);
    }

    /**
     * �����ֶ� minLength ��ֵ�����������ַ�������С���ȡ�
     * @param minLength �ֶ� minLength ��ֵ��
     * @since T3 V1.1
     */
    public void setMinLength(long minLength) {
        setMinLength(BigInteger.valueOf(minLength));
    }

    /**
     * �����ֶ� maxLength ��ֵ�����������ַ�������󳤶ȣ��ð�װ����ģ�����Ϊnull����ʾ�ַ�����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @return �ֶ� maxLength ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#getMaxLength()
     * @since T3 V1.1
     */
    public BigInteger getMaxLength() {
        return getStringFormatter().getMaxLength();
    }

    /**
     * �����ֶ� maxLength ��ֵ�����������ַ�������󳤶ȣ��ð�װ����ģ�����Ϊnull����ʾ�ַ�����û�����ޣ�����ʾ��tooltip�����������ʾ��
     * @param maxLength �ֶ� maxLength ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#setMaxLength(BigInteger)
     * @since T3 V1.1
     */
    public void setMaxLength(BigInteger maxLength) {
        getStringFormatter().setMaxLength(maxLength);
    }

    /**
     * �����ֶ� maxLength ��ֵ�����������ַ�������󳤶ȡ�
     * @param maxLength �ֶ� maxLength ��ֵ��
     * @since T3 V1.1
     */
    public void setMaxLength(long maxLength) {
        setMaxLength(BigInteger.valueOf(maxLength));
    }

    /**
     * ��ȡ�ֶ� characters ��ֵ���ַ�������һ���ַ���������������legal���������ǺϷ��ַ������ǷǷ��ַ�����ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @return �ֶ� characters ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#getCharacters()
     * @since T3 V1.1
     */
    public String getCharacters() {
        return getStringFormatter().getCharacters();
    }

    /**
     * �����ֶ� characters ��ֵ���ַ�������һ���ַ���������������legal���������ǺϷ��ַ������ǷǷ��ַ�����ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @param characters �ֶ� characters ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#setCharacters(String)
     * @since T3 V1.1
     */
    public void setCharacters(String characters) {
        getStringFormatter().setCharacters(characters);
    }

    /**
     * ��ȡ�ֶ� legal ��ֵ����ʶ�ַ����Ƿ�Ϸ���ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @return �ֶ� legal ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#isLegal()
     * @since T3 V1.1
     */
    public boolean isLegal() {
        return getStringFormatter().isLegal();
    }

    /**
     * �����ֶ� legal ��ֵ����ʶ�ַ����Ƿ�Ϸ���ע�⣺���������regex��˲�����Ч����regexΪ׼��
     * @param legal �ֶ� legal ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#setLegal(boolean)
     * @since T3 V1.1
     */
    public void setLegal(boolean legal) {
        getStringFormatter().setLegal(legal);
    }

    /**
     * ��ȡ�ֶ� regex ��ֵ��������ʽ����������������ַ�����ȱʡΪnull��
     * @return �ֶ� regex ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#getRegex()
     * @since T3 V1.1
     */
    public String getRegex() {
        return getStringFormatter().getRegex();
    }

    /**
     * �����ֶ� regex ��ֵ��������ʽ����������������ַ�����ȱʡΪnull��
     * @param regex �ֶ� regex ��ֵ��
     * @see com.apollo.swing.component.text.StringFormatter#setRegex(String)
     * @since T3 V1.1
     */
    public void setRegex(String regex) {
        getStringFormatter().setRegex(regex);
    }

    /*------------------------------------- JFormattedTextField Public Method -------------------------------------*/

    /**
     * @deprecated ����ֱ��ʹ��getString������
     * @see javax.swing.JFormattedTextField#getValue()
     * @see StringField#getString()
     */
    public Object getValue() {
        return super.getValue();
    }

    /**
     * @deprecated ����ֱ��ʹ��setString������
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
