/*
 * 此代码创建于 2007-2-26 下午03:31:54
 */
package com.apollo.swing.component.common;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * <p>文件名称：TComponentUtilities.java</p>
 * <p>文件描述：组件工具类。</p>
 * <p>版权所有：版权所有(C)2001-2004</p>
 * <p>公　　司：菠萝梨</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2007-6-15</p>
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
public final class ComponentUtilities {

    /*------------------------------------- Public Static Field -------------------------------------*/

    /**
     * 组件监听器------限制尺寸。
     */
    public static final ComponentListener CPL_LIMITSIZE = new ComponentAdapter() {

        /**
         * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.ComponentEvent)
         */
        public void componentResized(ComponentEvent evt) {
            /* 获取目标组件 */
            Component component = evt.getComponent();

            /* 获取目标组件设置的各种尺寸 */
            Dimension newSize = component.getSize();
            Dimension minSize = component.getMinimumSize();
            Dimension maxSize = component.getMaximumSize();

            /* 创建目标组件设置的最终尺寸 */
            Dimension finalSize = new Dimension();

            /* 确定目标组件的最终宽度 */
            if (newSize.width < minSize.width) {
                finalSize.width = minSize.width;

            } else if (newSize.width > maxSize.width) {
                finalSize.width = maxSize.width;

            } else {
                finalSize.width = newSize.width;
            }

            /* 确定目标组件的最终高度 */
            if (newSize.height < minSize.height) {
                finalSize.height = minSize.height;

            } else if (newSize.height > maxSize.height) {
                finalSize.height = maxSize.height;

            } else {
                finalSize.height = newSize.height;
            }

            /* 设置目标组件设置的最终尺寸 */
            component.setSize(finalSize);
        }

    };

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /* Add Public Constructor */

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /**
     * 构造方法。
     */
    private ComponentUtilities() {
        /* 禁止从外部实例化此类 */
    }

    /*------------------------------------- Public Static Method -------------------------------------*/

    /**
     * 如果当前线程本来就是awt事件线程就直接执行code.run()，否则会调用SwingUtilities.invokeLater()把code放入事件队列执行。
     * @param code 需要运行的代码。
     */
    public static final void safelyInvokeLater(Runnable code) {
        if (SwingUtilities.isEventDispatchThread()) {
            code.run();

        } else {
            SwingUtilities.invokeLater(code);
        }
    }

    /**
     * 判断指定容器是否允许填充垂直区域以拉伸容器的显示区域。
     * @param container 指定的容器。
     * @return 为true表示允许填充垂直区域，为false表示不允许填充垂直区域。
     */
    public static final boolean isFillVertical(Container container) {
        /* 过滤参数为null的情形 */
        if (container == null) {
            return false;
        }

        /* 获取容器的布局管理器 */
        LayoutManager layout = container.getLayout();

        /* 过滤容器的布局管理器不是GridBagLayout的情形 */
        if (!(layout instanceof GridBagLayout)) {
            return true;
        }

        /* 获取GridBagLayout和容器的全部组件 */
        GridBagLayout gridBagLayout = (GridBagLayout) layout;
        Component[] components = container.getComponents();

        /* 判断指定容器是否允许填充垂直区域以拉伸容器的显示区域 */
        for (int i = 0; i < components.length; i++) {
            /* 获取GridBagConstraints */
            GridBagConstraints gbc = gridBagLayout.getConstraints(components[i]);

            /* 判断组件是否允许填充垂直区域以拉伸容器的显示区域 */
            if (gbc.fill == GridBagConstraints.VERTICAL || gbc.fill == GridBagConstraints.BOTH) {
                return true;
            }
        }

        /* 不允许填充垂直区域 */
        return false;
    }

    /**
     * 调整JTable的相关首选宽度，包括其中的各列，以及滚动窗格中的首选宽度（非线程安全）。
     * 注意：1.如果你的targetTable所有模型（包括列模型）及其渲染器（包括表头渲染器）没有准备好，此方法有可能抛出不可遇见的异常。
     * 　　　2.如果你的targetTable选择的AutoResizeMode是非AUTO_RESIZE_OFF，那么此设置不会在界面行产生你想要的效果。
     * @param targetTable 目标表格。
     */
    public static final void adjustJTablePreferredWidths(JTable targetTable) {
        if (targetTable == null) {
            return; //目标表格为空不做以下处理
        }

        TableColumnModel cm = targetTable.getColumnModel(); //获取列模型
        JTableHeader th = targetTable.getTableHeader(); //获取表头

        final int columnMargin = cm.getColumnMargin(); //表格的列间距
        int targetTablePreferredWidths = 0; //目标表的首选宽度收集变量

        for (int i = 0, columnCount = cm.getColumnCount(); i < columnCount; i++) { //遍历列
            TableColumn column = cm.getColumn(i);

            TableCellRenderer headerRenderer = column.getHeaderRenderer();
            if (headerRenderer == null) { //用户没设置表头渲染器就使用默认的
                headerRenderer = th.getDefaultRenderer();
            }

            Object headerValue = column.getHeaderValue();

            /* 获取表头的首选宽度 */
            Component headerComponent = headerRenderer.getTableCellRendererComponent(targetTable, headerValue, false, false, 0, i);
            int columnMaxWidth = headerComponent.getPreferredSize().width; //当前列的最大宽度，用于以下的替换比较

            for (int j = 0, rowCount = targetTable.getRowCount(); j < rowCount; j++) { //遍历行
                TableCellRenderer cellRenderer = targetTable.getCellRenderer(j, i);
                Object cellValue = targetTable.getValueAt(j, i);

                /* 获取当列的当前行的首选宽度 */
                Component cellComponent = cellRenderer.getTableCellRendererComponent(targetTable, cellValue, false, false, j, i);
                columnMaxWidth = Math.max(columnMaxWidth, cellComponent.getPreferredSize().width); //比较替换
            }

            columnMaxWidth += columnMargin; //最大列宽需要考虑到列间距
            column.setPreferredWidth(columnMaxWidth); //设置列的首选宽度
            targetTablePreferredWidths += column.getPreferredWidth(); //收集各个列的首选宽度
        }

        /* 设置滚动窗格视口的最大尺寸（JTable自身的首选宽度会根据各个列计算出来，所以不需要特别设定） */
        Dimension psvs = targetTable.getPreferredScrollableViewportSize();
        psvs.width = targetTablePreferredWidths;
        /* 注意：这里不能直接设置为JTable的首选大小，这样会破坏用户已设定的滚动窗格的首选"高度"的 */
        targetTable.setPreferredScrollableViewportSize(psvs);
    }

    /**
     * 停止指定表格的单元格编辑动作（如果有表格有正在编辑的的单元格的话）。
     * @param targetTable 目标表格。
     */
    public static final void stopJTableCellEditing(JTable targetTable) {
        if (targetTable == null) {
            return; //目标表格为空不做以下处理
        }

        if (targetTable.isEditing()) {
            int row = targetTable.getEditingRow();
            int column = targetTable.getEditingColumn();
            TableCellEditor editor = targetTable.getCellEditor(row, column);
            editor.stopCellEditing();
        }
    }

    /**
     * 获取指定组件的的顶层框架。
     * @param component 指定的组件。
     * @return 指定组件的的顶层框架。
     */
    public static final JFrame getFrame(Component component) {
        /* 获取指定组件的的顶层窗口 */
        Window window = getWindow(component);

        /* 返回指定组件的的顶层框架 */
        if (window instanceof JFrame) { //指定组件的的顶层窗口是框架
            return (JFrame) window;

        } else { //指定组件的的顶层窗口不是框架
            return null;
        }
    }

    /**
     * 获取指定组件的的顶层对话框。
     * @param component 指定的组件。
     * @return 指定组件的的顶层对话框。
     */
    public static final JDialog getDialog(Component component) {
        /* 获取指定组件的的顶层窗口 */
        Window window = getWindow(component);

        /* 返回指定组件的的顶层对话框 */
        if (window instanceof JDialog) { //指定组件的的顶层窗口是对话框
            return (JDialog) window;

        } else { //指定组件的的顶层窗口不是对话框
            return null;
        }
    }

    /**
     * 获取指定组件的的顶层窗口。
     * @param component 指定的组件。
     * @return 指定组件的的顶层窗口。
     */
    public static final Window getWindow(Component component) {
        /* 返回指定组件的的顶层窗口 */
        if (component != null) { //指定的组件不为空
            if (component instanceof JDialog || component instanceof JFrame) { //指定的组件是框架或对话框
                return (Window) component;

            } else { //指定的组件是不是框架和对话框
                /* 获取指定组件的的顶层窗口 */
                Window window = SwingUtilities.windowForComponent(component);

                /* 获取的指定组件的的顶层窗口为null过滤 */
                if (window == null) {
                    window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
                }

                /* 返回指定组件的的顶层窗口 */
                return window;
            }

        } else { //指定的组件为空
            return null;
        }
    }

    /**
     * 获取指定窗口放在屏幕中央时候的左上角坐标点。
     * @param window 指定的窗口。
     * @return 指定窗口放在屏幕中央时候的左上角坐标点。
     */
    public static final Point getLocationOnCenterInScreen(Window window) {
        /* 创建指定窗口放在屏幕中央时候的左上角坐标点 */
        Point location = new Point(0, 0);

        /* 获取屏幕的尺寸和指定窗口的尺寸 */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getSize();

        /* 计算指定窗口放在屏幕中央时候的左上角坐标点 */
        location.x = screenSize.width / 2 - windowSize.width / 2;
        location.y = screenSize.height / 2 - windowSize.height / 2;

        /* 返回指定窗口放在屏幕中央时候的左上角坐标点 */
        return location;
    }

    /**
     * 获取一个组件在屏幕内的首选显示位置。
     * @param targetComponent 目标组件。
     * @param expectantLocation 目标组件期待出现的位置（组件左上角的坐标点）。
     * @return 如果目标组件或目标组件期待出现的位置为空则返回null。
     */
    public static final Point getPreferredLocationInScreen(JComponent targetComponent, Point expectantLocation) {
        if (targetComponent == null || expectantLocation == null) {
            return null;
        }

        Point preferredLocation = new Point(); //首选显示位置

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension targetComponentPreferredSize = targetComponent.getPreferredSize();

        /* 计算x轴的坐标值 */
        if (expectantLocation.x < 0) { //期待的x坐标在屏幕外面（左侧）
            preferredLocation.x = expectantLocation.x + targetComponentPreferredSize.width;

        } else if (expectantLocation.x + targetComponentPreferredSize.width > screenSize.width) { //期待的x坐标靠右不能显示完整的组件，其中包含了期待的x坐标在屏幕外面（右侧）的情况
            preferredLocation.x = expectantLocation.x - targetComponentPreferredSize.width;

        } else { //正常情况
            preferredLocation.x = expectantLocation.x;
        }

        /* 计算y轴的坐标值 */
        if (expectantLocation.y < 0) { //期待的y坐标在屏幕外面（上侧）
            preferredLocation.y = expectantLocation.y + targetComponentPreferredSize.height;

        } else if (expectantLocation.y + targetComponentPreferredSize.height > screenSize.height) { //期待的y坐标靠下不能显示完整的组件，其中包含了期待的y坐标在屏幕外面（下侧）的情况
            preferredLocation.y = expectantLocation.y - targetComponentPreferredSize.height;

        } else { //正常情况
            preferredLocation.y = expectantLocation.y;
        }

        /* 处理期待的坐标小于屏幕很多时的情形 */
        if (preferredLocation.x < 0) { //期待的x坐标是负数，而且过于小以至于加上组件宽度后仍然是负数，在屏幕外面
            preferredLocation.x = 0;
        }

        if (preferredLocation.y < 0) { //期待的y坐标是负数，而且过于小以至于加上组件高度后仍然是负数，在屏幕外面
            preferredLocation.y = 0;
        }

        return preferredLocation;
    }

    /**
     * 把鼠标事件的中描述的点转化为屏幕上的坐标点。
     * @param evt 鼠标事件。
     * @return 如果鼠标事件为空则返回为null。
     */
    public static final Point convertLocationToScreen(MouseEvent evt) {
        if (evt == null) {
            return null;
        }

        return convertLocationToScreen(evt.getComponent(), evt.getPoint());
    }

    /**
     * 将一个组件的相对坐标点转化为屏幕上的绝对坐标点。
     * @param source 源组件。
     * @param relativeLocation
     * @return 如果组件源为空则返回null。
     */
    public static final Point convertLocationToScreen(Component source, Point relativeLocation) {
        if (source == null) {
            return null;
        }

        Point sourceLocation = source.getLocationOnScreen();

        Point absoluteLocation = new Point();
        absoluteLocation.x = sourceLocation.x + relativeLocation.x;
        absoluteLocation.y = sourceLocation.y + relativeLocation.y;

        return absoluteLocation;
    }

    /**
     * 根据ResourceBundle机制获取i18n信息，如果指定的资源找不到将直接返回i18nKey。
     * @param i18nBaseName 资源包的基本名称，是一个完全限定类名。
     * @param i18nKey 国际化资源的国际化关键字。
     * @return 国际化信息。
     */
    public static final String getI18nMessage(String i18nBaseName, String i18nKey) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle(i18nBaseName);
            return rb.getString(i18nKey);

        } catch (MissingResourceException ex) { //找不到指定的资源
            return i18nKey;
        }
    }

    /**
     * 获取指定精度的浮点数的字符串描述形式。
     * @param floatValue 浮点值。
     * @param precision 精度。
     * @return 字符串描述。
     */
    public static final String getFloatDescriptionByPrecision(double floatValue, int precision) {
        BigDecimal floatValueBd = new BigDecimal(floatValue);

        BigDecimal result = floatValueBd.setScale(precision, BigDecimal.ROUND_HALF_UP);

        return result.toString();
    }

    /**
     * 获取指定类的class文件所在的位置。
     * @param targetClass 目标类。
     * @param isClasspathParent 为true返回的是class文件所在类路径的父目录；为false将直接返回类路径（注意：如果class文件在jar或zip中将返回的是jar文件或zip文件的路径）。
     * @param isRealSpace 为true将使用真实的空格来替换URL里面的转义空格，为false将直接使用URL里面规定的转义空格（注意：如果URL用于构建File，此参数需要设置为true）。
     * @return 标识位置，为null表示targetClass为null或构造URL失败了。
     */
    public static final URL getClassCodeLocation(Class targetClass, boolean isClasspathParent, boolean isRealSpace) {
        /* 目标类判空 */
        if (targetClass == null) {
            return null;
        }

        /*
         * 注意：这里可以直接调用Class.getProtectionDomain().getCodeSource().getLocation()方法来得到我们想要的Location，但
         * 　　　是这个方法有2个限制不太方便：
         * 　　　1.此方法需要相应的安全权限，如果没有，会抛Java安全异常的。
         * 　　　2.此方法的getCodeSource()在当Class为启动类加载器加载时会返回null，虽然很少会用到，但这还是我们不希望的。
         * 　　　基于以上两个缺点，我们不使用此方法来获取Location。
         */

        /* 获取目标类class文件的URL，并得到相关信息 */
        String clsName = targetClass.getName();
        String clsRes = "/" + clsName.replace('.', '/') + ".class";
        URL url = targetClass.getResource(clsRes);
        String protocol = url.getProtocol();
        String spec = url.toString();

        /* TODO 替换空格，URL中描述空格用"%20"，用这样的URL来构建File类将无法找到文件，必须替换到，但可能会存在平台差异性。 */
        if (isRealSpace) {
            spec = spec.replaceAll("%20", " ");
        }

        /* 获取类路径 */
        if (protocol.equals("jar") || protocol.equals("zip")) { //class文件是放在jar或zip中的
            spec = spec.substring(spec.indexOf(":") + 1, spec.lastIndexOf("!"));

        } else { //是普通的目录
            spec = spec.substring(0, spec.lastIndexOf(clsRes));
        }

        /* 获取类路径的父目录 */
        if (isClasspathParent) {
            spec = spec.substring(0, spec.lastIndexOf("/"));
        }

        /* 构造URL并返回 */
        try {
            return new URL(spec);

        } catch (MalformedURLException ex) {
            return null;
        }
    }

    /**
     * 根据指定树节点获取树的路径信息。
     * @param node 目标节点。
     * @return 路径信息，如果目标节点为空将返回null。
     */
    public static final TreePath getTreePath(TreeNode node) {
        /* 参数判空 */
        if (node == null) {
            return null;
        }

        /* 获取目标节点到根节点的列表 */
        List nodeList = new ArrayList();
        nodeList.add(node);
        TreeNode parent = node.getParent();
        while (parent != null) {
            nodeList.add(parent);
            parent = parent.getParent();
        }

        /* 获取根节点到目标节点的数组 */
        Object[] path = new Object[nodeList.size()];
        for (int i = 0; i < path.length; i++) {
            path[i] = nodeList.get(path.length - 1 - i);
        }

        /* 构造并返回路径信息 */
        return new TreePath(path);
    }

    /**
     * 判断树上的一个节点是否在树的可视区域上。
     * @param tree 目标树。
     * @param node 目标节点。
     * @param isContainFull 为true表示要包涵目标节点的全部像素才判定为在可视区域内，为false表示只要包涵目标节点的一个像素就判定为在可视区域中。
     * @return 在可视区域里返回true，不在显示区域里或参数错误则返回false。
     */
    public static final boolean isInTreeVisibleRect(JTree tree, TreeNode node, boolean isContainFull) {
        /* 参数判空 */
        if (tree == null || node == null) {
            return false;
        }

        /* 计算树的可视区域和节点的显示区域 */
        Rectangle vr = tree.getVisibleRect();
        Rectangle pb = tree.getPathBounds(getTreePath(node));

        /* 把树的可视区域扩大1个像素以此把节点的边框计算在内 */
        vr.x--;
        vr.y--;
        vr.width += 2;
        vr.height += 2;

        /* 计算结果判空 */
        if (vr == null || pb == null) {
            return false;
        }

        /* 可视区域计算 */
        if (isContainFull) { //完整包涵
            return vr.contains(pb);

        } else { //部分包涵
            if (vr.contains(pb.x, pb.y)) { //左上角
                return true;

            } else if (vr.contains(pb.x, pb.y + pb.height)) { //左下角
                return true;

            } else if (vr.contains(pb.x + pb.width, pb.y)) { //右上角
                return true;

            } else if (vr.contains(pb.x + pb.width, pb.y + pb.height)) { //右下角
                return true;

            } else { //节点一个像素都不在树的可视区域内
                return false;
            }
        }
    }

    /**
     * 判断指定的对象数组是否包含了指定对象，判断标准是对象相同（不是相等）。
     * @param objectArray 目标对象数组。
     * @param object 目标对象。
     * @return 如果包含则返回true；如果不包含、目标对象数组为空或目标对象为空则返回false。
     */
    public static final boolean containsObject(Object[] objectArray, Object object) {
        /* 对象判空 */
        if (objectArray == null || objectArray.length == 0 || object == null) {
            return false;
        }

        /* 迭代比较 */
        for (int i = 0; i < objectArray.length; i++) {
            if (objectArray[i] == object) { //当前对象和目标对象相同
                return true;
            }
        }

        /* 没找到相同的对象，返回false */
        return false;
    }

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

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
