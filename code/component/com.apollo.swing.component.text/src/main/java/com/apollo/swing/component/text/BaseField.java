/*
 * �˴��봴���� 2009-9-8 ����04:17:22��
 */
package com.apollo.swing.component.text;

import javax.swing.JFormattedTextField;

/**
 * <p>�ļ����ƣ�BaseField.java</p>
 * <p>������������������������Text������������չ������Ҫ���߼����Ƿ���Formatter���档ע�⣺���������JFormattedTextField�����Ӧ����Ӧ�������˽�JFormattedTextField������÷���</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2009-9-8</p>
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
public class BaseField extends JFormattedTextField {

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
     * ��ʶ�Ƿ���������ʾ��
     */
    protected volatile boolean m_isEnabledPopupTip = true;

    /**
     * �û���ʾ�ı���
     */
    protected String m_userTipText = null;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param formatter ��ʽ����
     * @since T3 V1.1
     */
    public BaseField(BaseFormatter formatter) {
        super(formatter);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ�ֶ� isEnabledPopupTip ��ֵ���ж��Ƿ�������������ʾ��Ϣ��
     * @return �ֶ� isEnabledPopupTip ��ֵ��
     * @since T3 V1.1
     */
    public boolean isEnabledPopupTip() {
        return m_isEnabledPopupTip;
    }

    /**
     * �����ֶ� isEnabledPopupTip ��ֵ�������Ƿ�������������ʾ��Ϣ��
     * @param isEnabledPopupTip �ֶ� isEnabledPopupTip ��ֵ��
     * @since T3 V1.1
     */
    public void setEnabledPopupTip(boolean isEnabledPopupTip) {
        m_isEnabledPopupTip = isEnabledPopupTip;

        if (isEnabledPopupTip) {
            refreshTipText();

        } else {
            super.setToolTipText(null);
        }
    }

    /**
     * ��ȡ�ֶ� userTipText ��ֵ����ȡ������������ʾ��Ϣ��
     * @return �ֶ� userTipText ��ֵ��
     * @since T3 V1.1
     */
    public String getUserTipText() {
        return m_userTipText;
    }

    /**
     * �����ֶ� userTipText ��ֵ�����õ�����������ʾ��Ϣ��
     * @param userTipText �ֶ� userTipText ��ֵ��
     * @since T3 V1.1
     */
    public void setUserTipText(String userTipText) {
        m_userTipText = userTipText;
        refreshTipText();
    }

    /*------------------------------------- JComponent Public Method -------------------------------------*/

    /**
     * @see javax.swing.JComponent#setToolTipText(java.lang.String)
     */
    public void setToolTipText(String text) {
        if (m_isEnabledPopupTip && m_userTipText == null) {
            super.setToolTipText(text);
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * ˢ����ʾ�ı���
     */
    protected void refreshTipText() {
        super.setToolTipText(m_userTipText);
        ((BaseFormatter) getFormatter()).updateTipText();
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
