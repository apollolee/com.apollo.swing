/*
 * 此代码创建于 2009-9-8 下午04:17:22。
 */
package com.apollo.swing.component.text;

import javax.swing.JFormattedTextField;

/**
 * <p>文件名称：BaseField.java</p>
 * <p>类型描述：抽象的输入框，其他Text组件由这个类扩展，但主要的逻辑还是放在Formatter里面。注意：此组件基于JFormattedTextField，因此应用者应该首先了解JFormattedTextField组件的用法。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2009-9-8</p>
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
     * 标识是否允许弹出提示。
     */
    protected volatile boolean m_isEnabledPopupTip = true;

    /**
     * 用户提示文本。
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
     * 构造方法。
     * @param formatter 格式器。
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
     * 获取字段 isEnabledPopupTip 的值，判断是否允许弹出输入提示信息。
     * @return 字段 isEnabledPopupTip 的值。
     * @since T3 V1.1
     */
    public boolean isEnabledPopupTip() {
        return m_isEnabledPopupTip;
    }

    /**
     * 设置字段 isEnabledPopupTip 的值，设置是否允许弹出输入提示信息。
     * @param isEnabledPopupTip 字段 isEnabledPopupTip 的值。
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
     * 获取字段 userTipText 的值，获取弹出的输入提示信息。
     * @return 字段 userTipText 的值。
     * @since T3 V1.1
     */
    public String getUserTipText() {
        return m_userTipText;
    }

    /**
     * 设置字段 userTipText 的值，设置弹出的输入提示信息。
     * @param userTipText 字段 userTipText 的值。
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
     * 刷新提示文本。
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
