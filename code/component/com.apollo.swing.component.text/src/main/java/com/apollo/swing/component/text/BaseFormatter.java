/*
 * 此代码创建于 2008-2-28 上午09:10:08。
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
 * <p>文件名称：BaseFormatter.java</p>
 * <p>类型描述：抽象的格式器，主要的逻辑是以Formatter为中心来处理的。注意：此组件基于JFormattedTextField，因此应用者应该首先了解JFormattedTextField组件的用法。</p>
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
     * 国际化信息------范围。
     */
    protected static final String I18N_INFO_RANGE = getI18nMessage("text.range");

    /**
     * 国际化信息------长度范围。
     */
    protected static final String I18N_INFO_LENGTHRANGE = getI18nMessage("text.lengthRange");

    /**
     * 国际化信息------精度。
     */
    protected static final String I18N_INFO_PRECISION = getI18nMessage("text.precision");

    /**
     * 国际化信息------步长。
     */
    protected static final String I18N_INFO_STEP = getI18nMessage("text.step");

    /**
     * 国际化信息------步长基值。
     */
    protected static final String I18N_INFO_STEPBASE = getI18nMessage("text.stepBase");

    /**
     * 国际化信息------合法字符。
     */
    protected static final String I18N_INFO_LEGALCHARACTER = getI18nMessage("text.legalCharacter");

    /**
     * 国际化信息------非法字符。
     */
    protected static final String I18N_INFO_ILLEGALCHARACTER = getI18nMessage("text.illegalCharacter");

    /**
     * 国际化信息------正则表达式。
     */
    protected static final String I18N_INFO_REGEX = getI18nMessage("text.regex");

    /**
     * 国际化信息------输入的字符是不合法的。
     */
    protected static final String I18N_INFO_CHARACTERISILLEGAL = getI18nMessage("text.characterIsIllegal");

    /**
     * 国际化信息------输入的字符是无效的。
     */
    protected static final String I18N_INFO_CHARACTERISINVALID = getI18nMessage("text.characterIsInvalid");

    /**
     * 国际化信息------输入格式错误。
     */
    protected static final String I18N_INFO_FORMATISERROR = getI18nMessage("text.formatIsError");

    /**
     * 国际化信息------输入字符串长度太短。
     */
    protected static final String I18N_INFO_LENGTHISTO0SHORT = getI18nMessage("text.lengthIsTooShort");

    /**
     * 国际化信息------输入字符串长度太长。
     */
    protected static final String I18N_INFO_LENGTHISTO0LONG = getI18nMessage("text.lengthIsTooLong");

    /**
     * 国际化信息------输入值太小。
     */
    protected static final String I18N_INFO_VALUEISTOOSMALL = getI18nMessage("text.valueIsTooSmall");

    /**
     * 国际化信息------输入值太大。
     */
    protected static final String I18N_INFO_VALUEISTOOBIG = getI18nMessage("text.valueIsTooBig");

    /**
     * 国际化信息------输入不符合精度要求。
     */
    protected static final String I18N_INFO_FALLSHORTOFPRECISION = getI18nMessage("text.fallShortOfPrecision");

    /**
     * 国际化信息------输入不符合步长要求。
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
     * 获取国际化信息，国际化文件在com.zte.ican.gui.component.text.resource.Text包目录下。
     * @param i18nKey 国际化信息关键字。
     * @return 国际化信息。
     */
    protected static final String getI18nMessage(String i18nKey) {
        return BaseUtilities.getI18nMessage("com.apollo.swing.component.text.resource.Text", i18nKey);
    }

    /**
     * 判断b是否与a重复。
     * @param a 对象a。
     * @param b 对象b。
     * @return 为true表示a与b重复。
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
     * 标识是否必须输入。
     */
    protected volatile boolean m_isNeedInput;

    /**
     * 标识是否允许弹出错误提示。
     */
    protected volatile boolean m_isEnabledPopupErrorTip;

    /**
     * 提示文本，用于保存整体的提示信息。
     */
    protected String m_tipText = null;

    /**
     * 错误提示组件。
     */
    protected JToolTip m_errorTip = new JToolTip();

    /**
     * 错误提示弹出工具。
     */
    protected Popup m_errorTipPopup = null;

    /**
     * 组件监听器（为了关闭可能浮动在外面的错误提示）。
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
     * 焦点监听器（为了关闭可能浮动在外面的错误提示）。
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
     * 祖先容器监听器（为了关闭可能浮动在外面的错误提示）。
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
     * 文档过滤器，验证逻辑的执行集中于此。
     */
    protected DocumentFilter m_documentFilter = new DocumentFilter() {

        /**
         * @see javax.swing.text.DocumentFilter#insertString(javax.swing.text.DocumentFilter.FilterBypass, int, java.lang.String, javax.swing.text.AttributeSet)
         */
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            /* 隐藏错误提示 */
            hideErrorTip();

            /* 验证文本 */
            try {
                verify(text, false);

            } catch (ParseException ex) {
                setErrorTipText(ex.getMessage());
                showErrorTip(offset);
                return;
            }

            /* 委托给父类处理 */
            super.insertString(fb, offset, text, attr);
        }

        /**
         * @see javax.swing.text.DocumentFilter#remove(javax.swing.text.DocumentFilter.FilterBypass, int, int)
         */
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            /* 隐藏错误提示 */
            hideErrorTip();

            /* 拼接待验证文本 */
            String oldText = getFormattedTextField().getText();
            StringBuffer sb = new StringBuffer();
            sb.append(oldText.substring(0, offset));
            sb.append(oldText.substring(offset + length, oldText.length()));

            /* 验证文本 */
            try {
                verify(sb.toString(), false);

            } catch (ParseException ex) {
                setErrorTipText(ex.getMessage());
                showErrorTip(offset);
                return;
            }

            /* 委托给父类处理 */
            super.remove(fb, offset, length);
        }

        /**
         * @see javax.swing.text.DocumentFilter#replace(javax.swing.text.DocumentFilter.FilterBypass, int, int, java.lang.String, javax.swing.text.AttributeSet)
         */
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            /* 隐藏错误提示 */
            hideErrorTip();

            /* 拼接待验证文本 */
            String oldText = getFormattedTextField().getText();
            StringBuffer sb = new StringBuffer();
            sb.append(oldText.substring(0, offset));
            sb.append(text);
            sb.append(oldText.substring(offset + length, oldText.length()));

            /* 验证文本 */
            try {
                verify(sb.toString(), false);

            } catch (ParseException ex) {
                setErrorTipText(ex.getMessage());
                showErrorTip(offset);
                return;
            }

            /* 委托给父类处理 */
            super.replace(fb, offset, length, text, attrs);
        }

    };

    /**
     * 所有动作（目前支持增加、减少）。
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
     * 构造方法。
     * @param isNeedInput 定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
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
     * 获取字段 isNeedInput 的值，定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @return 字段 isNeedInput 的值。
     * @since T3 V1.1
     */
    public boolean isNeedInput() {
        return m_isNeedInput;
    }

    /**
     * 设置字段 isNeedInput 的值，定义输入组件是否必须输入信息，如果不是必须输入则表示组件可以返回null值，为true表示必须输入，为false表示不是必须输入。
     * @param isNeedInput 字段 isNeedInput 的值。
     * @since T3 V1.1
     */
    public void setNeedInput(boolean isNeedInput) {
        m_isNeedInput = isNeedInput;
    }

    /**
     * 获取字段 isEnabledPopupErrorTip 的值，当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @return 字段 isEnabledPopupErrorTip 的值。
     * @since T3 V1.1
     */
    public boolean isEnabledPopupErrorTip() {
        return m_isEnabledPopupErrorTip;
    }

    /**
     * 设置字段 isEnabledPopupErrorTip 的值，当输入不符合条件时，定义组件是否在输入的位置弹出tooltip提示用户哪里输入错了，为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param isEnabledPopupErrorTip 字段 isEnabledPopupErrorTip 的值。
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
        /* 委托给父类处理 */
        super.install(ftf);

        /* 安装工具提示和监听器 */
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

        /* 卸载监听器和工具提示 */
        if (ftf != null) {
            ftf.removeAncestorListener(m_ancestorListener);
            ftf.removeFocusListener(m_focusListener);
            ftf.removeComponentListener(m_componentListener);
            ftf.setToolTipText(null);
        }

        /* 委托给父类处理 */
        super.uninstall();
    }

    /*------------------------------------- Abstract Protected Method -------------------------------------*/

    /**
     * 验证文本。
     * @param text 待验证的文本。
     * @param isFull 如果为true表示待验证的文本是完整的，如果为false表示待验证的文本是不完整的。
     * @throws ParseException 如果验证不通过则抛出此异常，TODO 此处用ParseException完全是为了@see描述的两个方法便于处理。
     * @see javax.swing.JFormattedTextField.AbstractFormatter#stringToValue(java.lang.String)
     * @see javax.swing.JFormattedTextField.AbstractFormatter#valueToString(java.lang.Object)
     */
    protected abstract void verify(String text, boolean isFull) throws ParseException;

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * 增加动作，对增加动作无处理的可不覆写此方法。
     * @param evt 动作事件。
     * @return [0]表示光标位置，[1]表示标记起始位置，[2]表示标记结束位置；如果不需要标记可返回null。
     */
    protected int[] increment(ActionEvent evt) {
        return getPositionsForMarkAll();
    }

    /**
     * 减少动作，对减少动作无处理的可不覆写此方法。
     * @param evt 动作事件。
     * @return [0]表示光标位置，[1]表示标记起始位置，[2]表示标记结束位置；如果不需要标记可返回null。
     */
    protected int[] decrement(ActionEvent evt) {
        return getPositionsForMarkAll();
    }

    /**
     * 建议所有子类都覆写此方法，并在覆写的方法末尾调用父类的实现。
     * 更新提示文本。
     */
    protected void updateTipText() {
        m_errorTip.setTipText(m_tipText);

        JFormattedTextField ftf = getFormattedTextField();
        if (ftf != null) {
            ftf.setToolTipText(m_tipText);
        }
    }

    /**
     * 获取标记所有文本的位置数组。
     * @return 标记所有文本的位置数组。
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
     * 标记文本。
     * @param positions 标记文本的位置数组。
     */
    protected void mark(int[] positions) {
        if (positions == null || positions.length != 3) {
            return;
        }

        mark(positions[0], positions[1], positions[2]);
    }

    /**
     * 标记文本。
     * @param caretPosition 光标位置。
     * @param startPosition 标记起始位置。
     * @param endPosition 标记结束位置。
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
     * 抛解析异常。
     * @param exMsg 异常信息。
     * @throws ParseException 抛出的解析异常。
     */
    protected void throwParseException(String exMsg) throws ParseException {
        throwParseException(exMsg, 0);
    }

    /**
     * 抛解析异常。
     * @param exMsg 异常信息。
     * @param errorOffset 输入框异常位置。
     * @throws ParseException 抛出的解析异常。
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
     * 显示错误提示。
     * @param offset 错误提示所在的文本行偏移位置。
     */
    private void showErrorTip(int offset) {
        JFormattedTextField ftf = getFormattedTextField();

        if (ftf != null && ftf.isShowing()) {
            showErrorTip(getErrorTipLocation(offset));
        }
    }

    /**
     * 显示错误提示。
     * @param location 错误提示所在的坐标点位置。
     */
    private void showErrorTip(Point location) {
        /* 判断是否需要显示错误提示 */
        if (m_isEnabledPopupErrorTip && m_errorTip.getTipText() != null) {
            /* 隐藏错误提示 */
            hideErrorTip();

            /* 获取错误提示弹出工具 */
            m_errorTipPopup = PopupFactory.getSharedInstance().getPopup(getFormattedTextField(), m_errorTip, location.x, location.y);
            ToolTipManager.sharedInstance().setEnabled(false);

            /* 显示错误提示 */
            m_errorTipPopup.show();
        }
    }

    /**
     * 隐藏错误提示。
     */
    private void hideErrorTip() {
        /* 判断是否需要隐藏错误提示 */
        if (m_errorTipPopup != null) {
            /* 隐藏错误提示 */
            m_errorTipPopup.hide();

            /* 清空错误提示弹出工具 */
            m_errorTipPopup = null;
            ToolTipManager.sharedInstance().setEnabled(true);
        }
    }

    /**
     * 根据错误提示所在的文本行偏移位置获取错误提示所在的坐标点位置。
     * @param offset 错误提示所在的文本行偏移位置。
     * @return 错误提示所在的坐标点位置。
     */
    private Point getErrorTipLocation(int offset) {
        JFormattedTextField ftf = getFormattedTextField();

        /* 获取组件在屏幕上的位置 */
        Point location = ftf.getLocationOnScreen();
        location.x -= ftf.getScrollOffset();

        /* 获取计算用的相关参数 */
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ttps = m_errorTip.getPreferredSize();
        FontMetrics fm = ftf.getFontMetrics(ftf.getFont());

        /* 获取偏移文本 */
        String offsetText = null;
        try {
            offsetText = ftf.getText(0, offset + 1);

        } catch (BadLocationException ex) {
            offsetText = ftf.getText();
        }

        /* 获取文本字体偏移量 */
        int offsetX = fm.stringWidth(offsetText);
        int offsetY = fm.getHeight();

        /* 计算输入提示的x轴坐标 */
        if (location.x + offsetX + ttps.width > ss.width) {
            location.x = location.x + offsetX - ttps.width;

        } else {
            location.x += offsetX;
        }

        /* 计算输入提示的y轴坐标 */
        if (location.y + offsetY + ttps.height > ss.height) {
            location.y -= ttps.height;

        } else {
            location.y += offsetY;
        }

        /* 返回输入提示的位置 */
        return location;
    }

    /**
     * 设置错误提示文本。
     * @param errorTipText 错误提示文本。
     */
    private void setErrorTipText(String errorTipText) {
        /* 创建字符串缓存 */
        StringBuffer sb = new StringBuffer();

        /* 填充错误文本 */
        sb.append("<html><font color=\"#ff0000\">");
        sb.append(errorTipText);
        sb.append("</font>");

        /* 填充提示文本 */
        if (m_tipText != null && !m_tipText.equals("")) {
            sb.append("<br>").append(m_tipText);
        }

        /* 更新到提示信息 */
        m_errorTip.setTipText(sb.toString());
    }

}
