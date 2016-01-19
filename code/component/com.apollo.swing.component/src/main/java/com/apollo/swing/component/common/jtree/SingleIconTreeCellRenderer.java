/*
 * 此代码创建于 2007-9-20 下午04:10:17
 */
package com.apollo.swing.component.common.jtree;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * <p>文件名称：TSingleIconTreeCellRenderer.java</p>
 * <p>文件描述：单图标树渲染器。</p>
 * <p>版权所有：版权所有(C)2001-2004</p>
 * <p>公　　司：菠萝梨</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2007-9-20</p>
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

    /*------------------------------------- Public Static Field -------------------------------------*/

    /* Add Public Static Field */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param imageLocation 指定图像的位置，先把此字符串先当成一个系统资源名字去查找，找不到就当成一个文件名去查找。
     * @exception IllegalArgumentException 指定图像的位置为空时抛出此异常。
     */
    public SingleIconTreeCellRenderer(String imageLocation) {
        /* 对象判空 */
        if (imageLocation == null) {
            throw new IllegalArgumentException("Image location is null !");
        }

        /* 从imageLocation指定的位置获取系统资源的URL */
        URL imageUrl = ClassLoader.getSystemResource(imageLocation);

        /* 依据获取的系统资源的URL进行不同的处理 */
        if (imageUrl == null) { //获取不到系统资源
            setSingleIcon(new ImageIcon(imageLocation)); //把imageLocation当成一个filename去处理

        } else { //获取到了系统资源
            setSingleIcon(new ImageIcon(imageUrl));
        }
    }

    /**
     * 构造方法。
     * @param imageUrl 指定图像的的URL地址。
     */
    public SingleIconTreeCellRenderer(URL imageUrl) {
        this(new ImageIcon(imageUrl));
    }

    /**
     * 构造方法。
     * @param singleIcon 指定图标。
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

    /*------------------------------------- Public Static Method -------------------------------------*/

    /* Add Public Static Method */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 设置单一图标。
     * @param singleIcon 指定图标。
     * @exception IllegalArgumentException 指定图标为空时抛出此异常。
     */
    public void setSingleIcon(Icon singleIcon) {
        /* 对象判空 */
        if (singleIcon == null) {
            throw new IllegalArgumentException("Single icon is null !");
        }

        /* 把缺省树渲染器的所有图标设置为指定的图标 */
        setOpenIcon(singleIcon);
        setClosedIcon(singleIcon);
        setLeafIcon(singleIcon);
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /* Add Protected Static Method */

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

    /*------------------------------------- Public Static Inner Class -------------------------------------*/

    /* Add Public Static Inner Class */

    /*------------------------------------- Public Inner Class -------------------------------------*/

    /* Add Public Inner Class */

    /*------------------------------------- Protected Static Inner Class -------------------------------------*/

    /* Add Protected Static Inner Class */

    /*------------------------------------- Protected Inner Class -------------------------------------*/

    /* Add Protected Inner Class */

    /*------------------------------------- Friendly Static Inner Class -------------------------------------*/

    /* Add Friendly Static Inner Class */

    /*------------------------------------- Friendly Inner Class -------------------------------------*/

    /* Add Friendly Inner Class */

    /*------------------------------------- Private Static Inner Class -------------------------------------*/

    /* Add Private Static Inner Class */

    /*------------------------------------- Private Inner Class -------------------------------------*/

    /* Add Private Inner Class */

    /*------------------------------------- Protected Static Field -------------------------------------*/

    /* Add Protected Static Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /* Add Protected Field */

    /*------------------------------------- Friendly Static Field -------------------------------------*/

    /* Add Friendly Static Field */

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Static Field -------------------------------------*/

    /* Add Private Static Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

}
