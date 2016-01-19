/*
 * 此代码创建于 2008-5-4 下午01:59:15。
 */
package com.apollo.swing.component.table;

import java.awt.Component;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * <p>文件名称：ColumnView.java</p>
 * <p>类型描述：列视图类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-5-4</p>
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
public class ColumnView extends DefaultTableColumnModel implements SwingConstants, ITableConstants, TableColumnModelListener {

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

    /**
     * 判断指定列是否为行号列。
     * @param column 指定列。
     * @return 为true表示指定列是行号列，为false表示指定列不是行号列。
     */
    public static boolean isRowIdColumn(TableColumn column) {
        return I18N_INFO_ROWID.equals(column.getIdentifier());
    }

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
     * 表格。
     */
    protected JTable m_table;

    /**
     * 视图源。
     */
    protected TableColumnModel m_source;

    /**
     * 标识是否为行头列。
     */
    protected volatile boolean m_isRowHeader;

    /**
     * 标识是否显示行号列。
     */
    protected volatile boolean m_isRowIdVisible;

    /**
     * 行号列渲染器。
     */
    protected TableCellRenderer m_rowIdRenderer;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param source 视图源。
     */
    public ColumnView(TableColumnModel source) {
        this(source, false, false, null);
    }

    /**
     * 构造方法。
     * @param source 视图源。
     * @param isRowIdVisible 标识是否显示行号列。
     * @param rowIdRenderer 行号列渲染器。
     */
    public ColumnView(TableColumnModel source, boolean isRowIdVisible, TableCellRenderer rowIdRenderer) {
        this(source, true, isRowIdVisible, rowIdRenderer);
    }

    /**
     * 构造方法。
     * @param source 视图源。
     * @param isRowHeader 标识是否为行头列。
     * @param isRowIdVisible 标识是否显示行号列。
     * @param rowIdRenderer 行号列渲染器。
     */
    public ColumnView(TableColumnModel source, boolean isRowHeader, boolean isRowIdVisible, TableCellRenderer rowIdRenderer) {
        m_isRowHeader = isRowHeader;
        m_isRowIdVisible = isRowIdVisible;
        m_rowIdRenderer = rowIdRenderer == null ? new RowIdRenderer() : rowIdRenderer;

        setSource(source);

        if (!m_isRowHeader) {
            for (int i = 0, size = m_source.getColumnCount(); i < size; i++) {
                addColumn(m_source.getColumn(i));
            }
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
     * 把视图索引转化为视图源视图索引。
     * @param viewColumnIndex 视图索引。
     * @return 视图源视图索引。
     */
    public int convertIndexToSource(int viewColumnIndex) {
        return m_source.getColumnIndex(getColumn(viewColumnIndex).getIdentifier());
    }

    /**
     * 把视图源视图索引转化为视图索引。
     * @param sourceColumnIndex 视图源视图索引。
     * @return 视图索引。
     */
    public int convertIndexToView(int sourceColumnIndex) {
        return getColumnIndex(m_source.getColumn(sourceColumnIndex).getIdentifier());
    }

    /**
     * 判断指定列是否为行号列。
     * @param column 指定列索引。
     * @return 为true表示指定列是行号列，为false表示指定列不是行号列。
     */
    public boolean isRowIdColumn(int column) {
        return isRowIdColumn(getColumn(column));
    }

    /*------------------------------------- TableColumnModelListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TableColumnModelListener#columnAdded(javax.swing.event.TableColumnModelEvent)
     */
    public void columnAdded(TableColumnModelEvent evt) {
        if (!m_isRowHeader) {
            TableColumn column = m_source.getColumn(evt.getToIndex());
            addColumn(column);

            if (m_table != null) {
                List customs = TableUtilities.getTableCustoms(m_table);

                if (customs != null) {
                    customs.add(new TableCustom(column, true));
                }
            }
        }
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnRemoved(javax.swing.event.TableColumnModelEvent)
     */
    public void columnRemoved(TableColumnModelEvent evt) {
        for (int i = 0, size = getColumnCount(); i < size; i++) {
            TableColumn column = getColumn(i);

            if (!isRowIdColumn(column) && !isInSource(column)) {
                if (m_table != null) {
                    List customs = TableUtilities.getTableCustoms(m_table);

                    if (customs != null) {
                        for (int j = 0, sizes = customs.size(); j < sizes; j++) {
                            TableCustom custom = (TableCustom) customs.get(j);

                            if (custom.m_column == column) {
                                customs.remove(j);
                                break;
                            }
                        }
                    }
                }

                removeColumn(column);
                fireColumnRemoved(new TableColumnModelEvent(this, i, 0));
                return;
            }
        }
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnMoved(javax.swing.event.TableColumnModelEvent)
     */
    public void columnMoved(TableColumnModelEvent evt) {
        /* 暂不必实现 */
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnMarginChanged(javax.swing.event.ChangeEvent)
     */
    public void columnMarginChanged(ChangeEvent evt) {
        setColumnMargin(m_source.getColumnMargin());
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnSelectionChanged(javax.swing.event.ListSelectionEvent)
     */
    public void columnSelectionChanged(ListSelectionEvent evt) {
        /* 暂不必实现 */
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * 设置视图源。
     * @param source 视图源。
     */
    protected void setSource(TableColumnModel source) {
        if (m_source != null) {
            m_source.removeColumnModelListener(this);
        }

        m_source = source;
        m_source.addColumnModelListener(this);

        List identifiers = TableUtilities.getColumnIdentifiers(this);
        TableUtilities.clear(this);

        if (m_isRowIdVisible) {
            identifiers.remove(I18N_INFO_ROWID);

            TableColumn rowIdColumn = new TableColumn(0, DEFAULT_ROWIDWIDTH, m_rowIdRenderer, null);
            rowIdColumn.setHeaderValue(I18N_INFO_ROWID);
            rowIdColumn.setMinWidth(15);
            rowIdColumn.setMaxWidth(999);
            rowIdColumn.setPreferredWidth(DEFAULT_ROWIDWIDTH);
            rowIdColumn.setHeaderRenderer(new TableCellRenderer() {

                /**
                 * 渲染器组件。
                 */
                protected JLabel m_rc = new JLabel();

                {
                    m_rc.setHorizontalAlignment(CENTER);
                    m_rc.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
                }

                /**
                 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
                 */
                public Component getTableCellRendererComponent(JTable table,
                                                               Object value,
                                                               boolean isSelected,
                                                               boolean hasFocus,
                                                               int row,
                                                               int column) {
                    m_rc.setText(value.toString());

                    if (table != null) {
                        JTableHeader header = table.getTableHeader();

                        if (header != null) {
                            m_rc.setForeground(header.getForeground());
                            m_rc.setBackground(header.getBackground());
                            m_rc.setFont(header.getFont());
                        }
                    }

                    if (m_table != null) {
                        m_rc.setEnabled(m_table.isEnabled());
                    }

                    return m_rc;
                }

            });

            addColumn(rowIdColumn);
        }

        for (int i = 0, size = identifiers.size(); i < size; i++) {
            addColumn(m_source.getColumn(m_source.getColumnIndex(identifiers.get(i))));
        }
    }

    /**
     * 获取视图源同步列。
     * @param modelIndex 模型索引。
     * @return 视图源同步列。
     */
    protected TableColumn getSyncColumn(int modelIndex) {
        for (int i = m_isRowIdVisible ? 1 : 0, size = getColumnCount(); i < size; i++) {
            TableColumn column = getColumn(i);
            if (column.getModelIndex() == modelIndex) {
                return column;
            }
        }

        TableColumn column = TableUtilities.getColumn(m_source, modelIndex);
        addColumn(column);

        return column;
    }

    /**
     * 判断列是否还在视图源中。
     * @param column 列。
     * @return 为true表示列还在视图源中，为false表示列不在视图源中。
     */
    protected boolean isInSource(TableColumn column) {
        return TableUtilities.contains(m_source, column);
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
