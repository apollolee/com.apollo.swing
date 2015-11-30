/*
 * �˴��봴���� 2012-11-9 ����10:49:32��
 */
package com.apollo.swing.laf.motif.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

import com.sun.java.swing.plaf.motif.MotifButtonUI;

/**
 * <p>�ļ����ƣ�TMotifButtonUI.java</p>
 * <p>����������TMotifButtonUI��</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2012-11-9</p>
 * <p>�޸ļ�¼��</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * @version 1.0
 * @author ����
 */
public class TMotifButtonUI extends MotifButtonUI {

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

    private static ComponentUI sharedInstance = new TMotifButtonUI();

    public static ComponentUI createUI(JComponent c) {
        return sharedInstance;
    }

    public Dimension getPreferredSize(JComponent c) {
        Dimension d = super.getPreferredSize(c);
        d.height = 23;
        d.width += 4;
        return d;
    }

    public Dimension getMaximumSize(JComponent c) {
        Dimension d = super.getMaximumSize(c);
        d.height = 23;
        d.width += 4;
        return d;
    }

    public Dimension getMinimumSize(JComponent c) {
        Dimension d = super.getMinimumSize(c);
        d.height = 23;
        d.width += 4;
        return d;
    }

    protected void fillContentArea(Graphics g, AbstractButton b, Color fillColor) {
        if (b.isContentAreaFilled()) {
            Dimension size = b.getSize();
            g.setColor(fillColor);
            g.fillRect(0, 0, size.width, size.height);
        }
    }

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

    /* Add Public Constructor */

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}