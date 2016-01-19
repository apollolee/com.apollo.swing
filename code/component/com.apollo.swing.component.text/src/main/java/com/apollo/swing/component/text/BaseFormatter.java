/*
 * �˴��봴���� 2008-2-28 ����09:10:08��
 */
package com.apollo.swing.component.text;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JToolTip;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.ToolTipManager;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.apollo.base.util.BaseUtilities;
import com.apollo.base.util.IBaseConstants;

/**
 * <p>�ļ����ƣ�BaseFormatter.java</p>
 * <p>��������������ĸ�ʽ������Ҫ���߼�����FormatterΪ����������ġ�ע�⣺���������JFormattedTextField�����Ӧ����Ӧ�������˽�JFormattedTextField������÷���</p>
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
public abstract class BaseFormatter extends AbstractFormatter implements IBaseConstants {

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

    /**
     * ���ʻ���Ϣ------��Χ��
     */
    protected static final String I18N_INFO_RANGE = getI18nMessage("text.range");

    /**
     * ���ʻ���Ϣ------���ȷ�Χ��
     */
    protected static final String I18N_INFO_LENGTHRANGE = getI18nMessage("text.lengthRange");

    /**
     * ���ʻ���Ϣ------���ȡ�
     */
    protected static final String I18N_INFO_PRECISION = getI18nMessage("text.precision");

    /**
     * ���ʻ���Ϣ------������
     */
    protected static final String I18N_INFO_STEP = getI18nMessage("text.step");

    /**
     * ���ʻ���Ϣ------������ֵ��
     */
    protected static final String I18N_INFO_STEPBASE = getI18nMessage("text.stepBase");

    /**
     * ���ʻ���Ϣ------�Ϸ��ַ���
     */
    protected static final String I18N_INFO_LEGALCHARACTER = getI18nMessage("text.legalCharacter");

    /**
     * ���ʻ���Ϣ------�Ƿ��ַ���
     */
    protected static final String I18N_INFO_ILLEGALCHARACTER = getI18nMessage("text.illegalCharacter");

    /**
     * ���ʻ���Ϣ------������ʽ��
     */
    protected static final String I18N_INFO_REGEX = getI18nMessage("text.regex");

    /**
     * ���ʻ���Ϣ------������ַ��ǲ��Ϸ��ġ�
     */
    protected static final String I18N_INFO_CHARACTERISILLEGAL = getI18nMessage("text.characterIsIllegal");

    /**
     * ���ʻ���Ϣ------������ַ�����Ч�ġ�
     */
    protected static final String I18N_INFO_CHARACTERISINVALID = getI18nMessage("text.characterIsInvalid");

    /**
     * ���ʻ���Ϣ------�����ʽ����
     */
    protected static final String I18N_INFO_FORMATISERROR = getI18nMessage("text.formatIsError");

    /**
     * ���ʻ���Ϣ------�����ַ�������̫�̡�
     */
    protected static final String I18N_INFO_LENGTHISTO0SHORT = getI18nMessage("text.lengthIsTooShort");

    /**
     * ���ʻ���Ϣ------�����ַ�������̫����
     */
    protected static final String I18N_INFO_LENGTHISTO0LONG = getI18nMessage("text.lengthIsTooLong");

    /**
     * ���ʻ���Ϣ------����ֵ̫С��
     */
    protected static final String I18N_INFO_VALUEISTOOSMALL = getI18nMessage("text.valueIsTooSmall");

    /**
     * ���ʻ���Ϣ------����ֵ̫��
     */
    protected static final String I18N_INFO_VALUEISTOOBIG = getI18nMessage("text.valueIsTooBig");

    /**
     * ���ʻ���Ϣ------���벻���Ͼ���Ҫ��
     */
    protected static final String I18N_INFO_FALLSHORTOFPRECISION = getI18nMessage("text.fallShortOfPrecision");

    /**
     * ���ʻ���Ϣ------���벻���ϲ���Ҫ��
     */
    protected static final String I18N_INFO_FALLSHORTOFSTEP = getI18nMessage("text.fallShortOfStep");

    /*------------------------------------- Friendly Static Field -------------------------------------*/

    /* Add Friendly Static Field */

    /*------------------------------------- Private Static Field -------------------------------------*/

    /* Add Private Static Field */

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Public Static Method -------------------------------------*/

    /* Add Public Static Method */

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /**
     * ��ȡ���ʻ���Ϣ�����ʻ��ļ���com.zte.ican.gui.component.text.resource.Text��Ŀ¼�¡�
     * @param i18nKey ���ʻ���Ϣ�ؼ��֡�
     * @return ���ʻ���Ϣ��
     */
    protected static final String getI18nMessage(String i18nKey) {
        return BaseUtilities.getI18nMessage("com.apollo.swing.component.text.resource.Text", i18nKey);
    }

    /**
     * �ж�b�Ƿ���a�ظ���
     * @param a ����a��
     * @param b ����b��
     * @return Ϊtrue��ʾa��b�ظ���
     */
    protected static final boolean isRepeated(Object a, Object b) {
        if (a == null) {
            return b == null ? true : false;

        } else {
            return a.equals(b) ? true : false;
        }
    }

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /**
     * ��ʶ�Ƿ�������롣
     */
    protected volatile boolean m_isNeedInput;

    /**
     * ��ʶ�Ƿ�������������ʾ��
     */
    protected volatile boolean m_isEnabledPopupErrorTip;

    /**
     * ��ʾ�ı������ڱ����������ʾ��Ϣ��
     */
    protected String m_tipText = null;

    /**
     * ������ʾ�����
     */
    protected JToolTip m_errorTip = new JToolTip();

    /**
     * ������ʾ�������ߡ�
     */
    protected Popup m_errorTipPopup = null;

    /**
     * �����������Ϊ�˹رտ��ܸ���������Ĵ�����ʾ����
     */
    protected ComponentListener m_componentListener = new ComponentAdapter() {

        /**
         * @see java.awt.event.ComponentAdapter#componentHidden(java.awt.event.ComponentEvent)
         */
        public void componentHidden(ComponentEvent event) {
            hideErrorTip();
        }

        /**
         * @see java.awt.event.ComponentAdapter#componentMoved(java.awt.event.ComponentEvent)
         */
        public void componentMoved(ComponentEvent event) {
            hideErrorTip();
        }

        /**
         * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.ComponentEvent)
         */
        public void componentResized(ComponentEvent event) {
            hideErrorTip();
        }

    };

    /**
     * �����������Ϊ�˹رտ��ܸ���������Ĵ�����ʾ����
     */
    protected FocusListener m_focusListener = new FocusListener() {

        /**
         * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
         */
        public void focusGained(FocusEvent event) {
            hideErrorTip();
        }

        /**
         * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
         */
        public void focusLost(FocusEvent event) {
            hideErrorTip();
        }

    };

    /**
     * ����������������Ϊ�˹رտ��ܸ���������Ĵ�����ʾ����
     */
    protected AncestorListener m_ancestorListener = new AncestorListener() {

        /**
         * @see javax.swing.event.AncestorListener#ancestorAdded(javax.swing.event.AncestorEvent)
         */
        public void ancestorAdded(AncestorEvent event) {
            hideErrorTip();
        }

        /**
         * @see javax.swing.event.AncestorListener#ancestorMoved(javax.swing.event.AncestorEvent)
         */
        public void ancestorMoved(AncestorEvent event) {
            hideErrorTip();
        }

        /**
         * @see javax.swing.event.AncestorListener#ancestorRemoved(javax.swing.event.AncestorEvent)
         */
        public void ancestorRemoved(AncestorEvent event) {
            hideErrorTip();
        }

    };

    /**
     * �ĵ�����������֤�߼���ִ�м����ڴˡ�
     */
    protected DocumentFilter m_documentFilter = new DocumentFilter() {

        /**
         * @see javax.swing.text.DocumentFilter#insertString(javax.swing.text.DocumentFilter.FilterBypass, int, java.lang.String, javax.swing.text.AttributeSet)
         */
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            /* ���ش�����ʾ */
            hideErrorTip();

            /* ��֤�ı� */
            try {
                verify(text, false);

            } catch (ParseException ex) {
                setErrorTipText(ex.getMessage());
                showErrorTip(offset);
                return;
            }

            /* ί�и����ദ�� */
            super.insertString(fb, offset, text, attr);
        }

        /**
         * @see javax.swing.text.DocumentFilter#remove(javax.swing.text.DocumentFilter.FilterBypass, int, int)
         */
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            /* ���ش�����ʾ */
            hideErrorTip();

            /* ƴ�Ӵ���֤�ı� */
            String oldText = getFormattedTextField().getText();
            StringBuffer sb = new StringBuffer();
            sb.append(oldText.substring(0, offset));
            sb.append(oldText.substring(offset + length, oldText.length()));

            /* ��֤�ı� */
            try {
                verify(sb.toString(), false);

            } catch (ParseException ex) {
                setErrorTipText(ex.getMessage());
                showErrorTip(offset);
                return;
            }

            /* ί�и����ദ�� */
            super.remove(fb, offset, length);
        }

        /**
         * @see javax.swing.text.DocumentFilter#replace(javax.swing.text.DocumentFilter.FilterBypass, int, int, java.lang.String, javax.swing.text.AttributeSet)
         */
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            /* ���ش�����ʾ */
            hideErrorTip();

            /* ƴ�Ӵ���֤�ı� */
            String oldText = getFormattedTextField().getText();
            StringBuffer sb = new StringBuffer();
            sb.append(oldText.substring(0, offset));
            sb.append(text);
            sb.append(oldText.substring(offset + length, oldText.length()));

            /* ��֤�ı� */
            try {
                verify(sb.toString(), false);

            } catch (ParseException ex) {
                setErrorTipText(ex.getMessage());
                showErrorTip(offset);
                return;
            }

            /* ί�и����ദ�� */
            super.replace(fb, offset, length, text, attrs);
        }

    };

    /**
     * ���ж�����Ŀǰ֧�����ӡ����٣���
     */
    protected Action[] m_actions = new Action[] { new AbstractAction() {

        {
            putValue(NAME, "increment");
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            mark(increment(evt));
        }

    }, new AbstractAction() {

        {
            putValue(NAME, "decrement");
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            mark(decrement(evt));
        }

    } };

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param isNeedInput ������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isEnabledPopupErrorTip �����벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @since T3 V1.1
     */
    public BaseFormatter(boolean isNeedInput, boolean isEnabledPopupErrorTip) {
        m_isNeedInput = isNeedInput;
        m_isEnabledPopupErrorTip = isEnabledPopupErrorTip;
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ�ֶ� isNeedInput ��ֵ��������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @return �ֶ� isNeedInput ��ֵ��
     * @since T3 V1.1
     */
    public boolean isNeedInput() {
        return m_isNeedInput;
    }

    /**
     * �����ֶ� isNeedInput ��ֵ��������������Ƿ����������Ϣ��������Ǳ����������ʾ������Է���nullֵ��Ϊtrue��ʾ�������룬Ϊfalse��ʾ���Ǳ������롣
     * @param isNeedInput �ֶ� isNeedInput ��ֵ��
     * @since T3 V1.1
     */
    public void setNeedInput(boolean isNeedInput) {
        m_isNeedInput = isNeedInput;
    }

    /**
     * ��ȡ�ֶ� isEnabledPopupErrorTip ��ֵ�������벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @return �ֶ� isEnabledPopupErrorTip ��ֵ��
     * @since T3 V1.1
     */
    public boolean isEnabledPopupErrorTip() {
        return m_isEnabledPopupErrorTip;
    }

    /**
     * �����ֶ� isEnabledPopupErrorTip ��ֵ�������벻��������ʱ����������Ƿ��������λ�õ���tooltip��ʾ�û�����������ˣ�Ϊtrue��ʾ������������ʾ��Ϊfalse��ʾ��������������ʾ��
     * @param isEnabledPopupErrorTip �ֶ� isEnabledPopupErrorTip ��ֵ��
     * @since T3 V1.1
     */
    public void setEnabledPopupErrorTip(boolean isEnabledPopupErrorTip) {
        m_isEnabledPopupErrorTip = isEnabledPopupErrorTip;
    }

    /*------------------------------------- AbstractFormatter Public Method -------------------------------------*/

    /**
     * @see javax.swing.JFormattedTextField.AbstractFormatter#install(javax.swing.JFormattedTextField)
     */
    public void install(JFormattedTextField ftf) {
        /* ί�и����ദ�� */
        super.install(ftf);

        /* ��װ������ʾ�ͼ����� */
        if (ftf != null) {
            ftf.setToolTipText(m_tipText);
            ftf.addComponentListener(m_componentListener);
            ftf.addFocusListener(m_focusListener);
            ftf.addAncestorListener(m_ancestorListener);
        }
    }

    /**
     * @see javax.swing.JFormattedTextField.AbstractFormatter#uninstall()
     */
    public void uninstall() {
        JFormattedTextField ftf = getFormattedTextField();

        /* ж�ؼ������͹�����ʾ */
        if (ftf != null) {
            ftf.removeAncestorListener(m_ancestorListener);
            ftf.removeFocusListener(m_focusListener);
            ftf.removeComponentListener(m_componentListener);
            ftf.setToolTipText(null);
        }

        /* ί�и����ദ�� */
        super.uninstall();
    }

    /*------------------------------------- Abstract Protected Method -------------------------------------*/

    /**
     * ��֤�ı���
     * @param text ����֤���ı���
     * @param isFull ���Ϊtrue��ʾ����֤���ı��������ģ����Ϊfalse��ʾ����֤���ı��ǲ������ġ�
     * @throws ParseException �����֤��ͨ�����׳����쳣��TODO �˴���ParseException��ȫ��Ϊ��@see�����������������ڴ���
     * @see javax.swing.JFormattedTextField.AbstractFormatter#stringToValue(java.lang.String)
     * @see javax.swing.JFormattedTextField.AbstractFormatter#valueToString(java.lang.Object)
     */
    protected abstract void verify(String text, boolean isFull) throws ParseException;

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * ���Ӷ����������Ӷ����޴���Ŀɲ���д�˷�����
     * @param evt �����¼���
     * @return [0]��ʾ���λ�ã�[1]��ʾ�����ʼλ�ã�[2]��ʾ��ǽ���λ�ã��������Ҫ��ǿɷ���null��
     */
    protected int[] increment(ActionEvent evt) {
        return getPositionsForMarkAll();
    }

    /**
     * ���ٶ������Լ��ٶ����޴���Ŀɲ���д�˷�����
     * @param evt �����¼���
     * @return [0]��ʾ���λ�ã�[1]��ʾ�����ʼλ�ã�[2]��ʾ��ǽ���λ�ã��������Ҫ��ǿɷ���null��
     */
    protected int[] decrement(ActionEvent evt) {
        return getPositionsForMarkAll();
    }

    /**
     * �����������඼��д�˷��������ڸ�д�ķ���ĩβ���ø����ʵ�֡�
     * ������ʾ�ı���
     */
    protected void updateTipText() {
        m_errorTip.setTipText(m_tipText);

        JFormattedTextField ftf = getFormattedTextField();
        if (ftf != null) {
            ftf.setToolTipText(m_tipText);
        }
    }

    /**
     * ��ȡ��������ı���λ�����顣
     * @return ��������ı���λ�����顣
     */
    protected int[] getPositionsForMarkAll() {
        JFormattedTextField ftf = getFormattedTextField();

        if (ftf == null) {
            return null;
        }

        int length = ftf.getText().length();

        if (length == 0) {
            return null;
        }

        return new int[] { length, 0, length };
    }

    /**
     * ����ı���
     * @param positions ����ı���λ�����顣
     */
    protected void mark(int[] positions) {
        if (positions == null || positions.length != 3) {
            return;
        }

        mark(positions[0], positions[1], positions[2]);
    }

    /**
     * ����ı���
     * @param caretPosition ���λ�á�
     * @param startPosition �����ʼλ�á�
     * @param endPosition ��ǽ���λ�á�
     */
    protected void mark(int caretPosition, int startPosition, int endPosition) {
        JFormattedTextField ftf = getFormattedTextField();

        if (ftf == null) {
            return;
        }

        ftf.setCaretPosition(caretPosition);
        ftf.setSelectionStart(startPosition);
        ftf.setSelectionEnd(endPosition);
    }

    /**
     * �׽����쳣��
     * @param exMsg �쳣��Ϣ��
     * @throws ParseException �׳��Ľ����쳣��
     */
    protected void throwParseException(String exMsg) throws ParseException {
        throwParseException(exMsg, 0);
    }

    /**
     * �׽����쳣��
     * @param exMsg �쳣��Ϣ��
     * @param errorOffset ������쳣λ�á�
     * @throws ParseException �׳��Ľ����쳣��
     */
    protected void throwParseException(String exMsg, int errorOffset) throws ParseException {
        throw new ParseException(exMsg, 0);
    }

    /*------------------------------------- AbstractFormatter Protected Method -------------------------------------*/

    /**
     * @see javax.swing.JFormattedTextField.AbstractFormatter#getDocumentFilter()
     */
    protected DocumentFilter getDocumentFilter() {
        return m_documentFilter;
    }

    /**
     * @see javax.swing.JFormattedTextField.AbstractFormatter#getActions()
     */
    protected Action[] getActions() {
        return m_actions;
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /**
     * ��ʾ������ʾ��
     * @param offset ������ʾ���ڵ��ı���ƫ��λ�á�
     */
    private void showErrorTip(int offset) {
        JFormattedTextField ftf = getFormattedTextField();

        if (ftf != null && ftf.isShowing()) {
            showErrorTip(getErrorTipLocation(offset));
        }
    }

    /**
     * ��ʾ������ʾ��
     * @param location ������ʾ���ڵ������λ�á�
     */
    private void showErrorTip(Point location) {
        /* �ж��Ƿ���Ҫ��ʾ������ʾ */
        if (m_isEnabledPopupErrorTip && m_errorTip.getTipText() != null) {
            /* ���ش�����ʾ */
            hideErrorTip();

            /* ��ȡ������ʾ�������� */
            m_errorTipPopup = PopupFactory.getSharedInstance().getPopup(getFormattedTextField(), m_errorTip, location.x, location.y);
            ToolTipManager.sharedInstance().setEnabled(false);

            /* ��ʾ������ʾ */
            m_errorTipPopup.show();
        }
    }

    /**
     * ���ش�����ʾ��
     */
    private void hideErrorTip() {
        /* �ж��Ƿ���Ҫ���ش�����ʾ */
        if (m_errorTipPopup != null) {
            /* ���ش�����ʾ */
            m_errorTipPopup.hide();

            /* ��մ�����ʾ�������� */
            m_errorTipPopup = null;
            ToolTipManager.sharedInstance().setEnabled(true);
        }
    }

    /**
     * ���ݴ�����ʾ���ڵ��ı���ƫ��λ�û�ȡ������ʾ���ڵ������λ�á�
     * @param offset ������ʾ���ڵ��ı���ƫ��λ�á�
     * @return ������ʾ���ڵ������λ�á�
     */
    private Point getErrorTipLocation(int offset) {
        JFormattedTextField ftf = getFormattedTextField();

        /* ��ȡ�������Ļ�ϵ�λ�� */
        Point location = ftf.getLocationOnScreen();
        location.x -= ftf.getScrollOffset();

        /* ��ȡ�����õ���ز��� */
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ttps = m_errorTip.getPreferredSize();
        FontMetrics fm = ftf.getFontMetrics(ftf.getFont());

        /* ��ȡƫ���ı� */
        String offsetText = null;
        try {
            offsetText = ftf.getText(0, offset + 1);

        } catch (BadLocationException ex) {
            offsetText = ftf.getText();
        }

        /* ��ȡ�ı�����ƫ���� */
        int offsetX = fm.stringWidth(offsetText);
        int offsetY = fm.getHeight();

        /* ����������ʾ��x������ */
        if (location.x + offsetX + ttps.width > ss.width) {
            location.x = location.x + offsetX - ttps.width;

        } else {
            location.x += offsetX;
        }

        /* ����������ʾ��y������ */
        if (location.y + offsetY + ttps.height > ss.height) {
            location.y -= ttps.height;

        } else {
            location.y += offsetY;
        }

        /* ����������ʾ��λ�� */
        return location;
    }

    /**
     * ���ô�����ʾ�ı���
     * @param errorTipText ������ʾ�ı���
     */
    private void setErrorTipText(String errorTipText) {
        /* �����ַ������� */
        StringBuffer sb = new StringBuffer();

        /* �������ı� */
        sb.append("<html><font color=\"#ff0000\">");
        sb.append(errorTipText);
        sb.append("</font>");

        /* �����ʾ�ı� */
        if (m_tipText != null && !m_tipText.equals("")) {
            sb.append("<br>").append(m_tipText);
        }

        /* ���µ���ʾ��Ϣ */
        m_errorTip.setTipText(sb.toString());
    }

}
