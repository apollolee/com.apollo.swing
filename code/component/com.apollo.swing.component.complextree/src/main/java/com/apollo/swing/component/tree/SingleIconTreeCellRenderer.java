/*
 * 此代码创建于 2008-8-18 上午10:12:20。
 */
package com.apollo.swing.component.tree;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * <p>文件名称：SingleIconTreeCellRenderer.java</p>
 * <p>类型描述：单图标树渲染器类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-8-18</p>
 * <p>修改记录：</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * @version 1.0
 * @author 李镇
 */
@SuppressWarnings("all")
public class SingleIconTreeCellRenderer extends DefaultTreeCellRenderer {

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
     * 单图标。
     */
    protected Icon m_singleIcon;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     */
    public SingleIconTreeCellRenderer() {
        this((Icon) null);
    }

    /**
     * 构造方法。
     * @param imageLocation 单图标图像的位置，先把此字符串先当成一个系统资源名字去查找，找不到就当成一个文件名去查找。
     */
    public SingleIconTreeCellRenderer(String imageLocation) {
        /* 对象判空 */
        if (imageLocation == null) {
            setSingleIcon(null);

        } else {
            /* 从imageLocation指定的位置获取系统资源的URL */
            URL imageUrl = ClassLoader.getSystemResource(imageLocation);

            /* 依据获取的系统资源的URL进行不同的处理 */
            if (imageUrl == null) { //获取不到系统资源
                setSingleIcon(new ImageIcon(imageLocation)); //把imageLocation当成一个filename去处理

            } else { //获取到了系统资源
                setSingleIcon(new ImageIcon(imageUrl));
            }
        }
    }

    /**
     * 构造方法。
     * @param imageUrl 单图标图像的的URL地址。
     */
    public SingleIconTreeCellRenderer(URL imageUrl) {
        this(new ImageIcon(imageUrl));
    }

    /**
     * 构造方法。
     * @param singleIcon 单图标。
     * @see SingleIconTreeCellRenderer#setSingleIcon(Icon)
     */
    public SingleIconTreeCellRenderer(Icon singleIcon) {
        setSingleIcon(singleIcon);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 获取单图标。
     * @return 单图标。
     */
    public Icon getSingleIcon() {
        return m_singleIcon;
    }

    /**
     * 设置单图标。
     * @param singleIcon 单图标。
     */
    public void setSingleIcon(Icon singleIcon) {
        m_singleIcon = singleIcon;
    }

    /*------------------------------------- DefaultTreeCellRenderer Public Method -------------------------------------*/

    /**
     * @see javax.swing.tree.DefaultTreeCellRenderer#getOpenIcon()
     */
    public Icon getOpenIcon() {
        return m_singleIcon;
    }

    /**
     * @see javax.swing.tree.DefaultTreeCellRenderer#getClosedIcon()
     */
    public Icon getClosedIcon() {
        return m_singleIcon;
    }

    /**
     * @see javax.swing.tree.DefaultTreeCellRenderer#getLeafIcon()
     */
    public Icon getLeafIcon() {
        return m_singleIcon;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
