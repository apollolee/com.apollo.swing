/*
 * 此代码创建于 2008-4-15 下午01:53:25。
 */
package com.apollo.swing.component.table.demo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.table.TableCustomDialog;
import com.apollo.swing.component.table.TableUtilities;
import com.apollo.swing.laf.TLookAndFeelManager;

/**
 * <p>文件名称：TableUtilitiesDemo.java</p>
 * <p>类型描述：表格工具演示类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-4-15</p>
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
public class TableCustomDemo implements IBaseConstants {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /* Add Public (Static) Inner Class */

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    protected static class TMainTableModel extends AbstractTableModel {

        protected static final int COLUMN_COUNT = 10;

        protected static final int ROW_COUNT = 10;

        protected List m_columns = new ArrayList();

        protected List m_rows = new ArrayList();

        public TMainTableModel() {
            for (int i = 0; i < COLUMN_COUNT; i++) {
                m_columns.add("C" + i);
            }

            for (int i = 0; i < ROW_COUNT; i++) {
                List row = new ArrayList();

                for (int j = 0; j < COLUMN_COUNT; j++) {
                    if (j % 3 == 0) {
                        row.add(Boolean.TRUE);

                    } else {
                        row.add("Data[" + i + " , " + j + "]");
                    }
                }

                m_rows.add(row);
            }
        }

        /**
         * @see javax.swing.table.TableModel#getColumnCount()
         */
        public int getColumnCount() {
            return COLUMN_COUNT;
        }

        /**
         * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
         */
        public Class getColumnClass(int columnIndex) {
            return columnIndex % 3 == 0 ? Boolean.class : super.getColumnClass(columnIndex);
        }

        /**
         * @see javax.swing.table.AbstractTableModel#getColumnName(int)
         */
        public String getColumnName(int columnIndex) {
            return (String) m_columns.get(columnIndex);
        }

        /**
         * @see javax.swing.table.TableModel#getRowCount()
         */
        public int getRowCount() {
            return ROW_COUNT;
        }

        /**
         * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
         */
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex % 3 == 0 ? true : false;
        }

        /**
         * @see javax.swing.table.TableModel#getValueAt(int, int)
         */
        public Object getValueAt(int rowIndex, int columnIndex) {
            List row = (List) m_rows.get(rowIndex);
            return row.get(columnIndex);
        }

        /**
         * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
         */
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            List row = (List) m_rows.get(rowIndex);
            row.remove(columnIndex);
            row.add(columnIndex, value);
        }

    }

    protected static class TMainTable extends JTable {

        public TMainTable() {
            super(new TMainTableModel());

            setAutoResizeMode(AUTO_RESIZE_OFF);
        }

    }

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
     * 入口方法。
     * @param args 入口参数。
     */
    //	public static void main(String[] args) {
    //		MainFrame.loadDefaultLookAndFeel();
    //
    //		TMainTable mt = new TMainTable();
    //		
    //		TTableBody body = new TTableBody(mt);
    //		TTableRowHeader rh = new TTableRowHeader(mt, body);
    //		TTableUtilities.autoManageCustom("Table Custom Demo", rh);
    //		JScrollPane sp = new JScrollPane(body);
    //		sp.setRowHeaderView(rh);
    //		sp.setCorner(JScrollPane.UPPER_LEFT_CORNER, rh.getTableHeader());
    //
    //		JFrame f = new JFrame("列表定制演示");
    //		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //
    //		mt.setCellSelectionEnabled(true);
    //		f.setContentPane(sp);
    //		f.pack();
    //		f.setVisible(true);
    //	}
    public static void main(String[] args) {
        TLookAndFeelManager.loadAlloy(false);

        //		try {
        //			LineNumberReader rr = new LineNumberReader(new InputStreamReader(new FileInputStream("d:/aa.txt")));
        //			System.out.println(rr.getLineNumber());
        //			String aa = rr.readLine();
        //			List dd = new ArrayList();
        //			while (aa != null) {
        //				aa = aa.substring(aa.indexOf("\\T3\\code\\client\\"), aa.indexOf("\\src\\"));
        //				if (!dd.contains(aa)) {
        //					dd.add(aa);
        //					System.out.println(aa);
        //				}
        //				aa = rr.readLine();
        //			}
        //			
        //		} catch (FileNotFoundException ex) {
        //			/* TODO 请添加异常处理代码 */
        //			ex.printStackTrace();
        //		} catch (IOException ex) {
        //			/* TODO 请添加异常处理代码 */
        //			ex.printStackTrace();
        //		}

        TMainTable mt = new TMainTable();

        TableUtilities.loadCustom("ddd", mt);
        JScrollPane sp = new JScrollPane(mt);
        TableCustomDialog cd = new TableCustomDialog(mt, null, "ddd");

        JFrame f = new JFrame("列表定制演示");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mt.setCellSelectionEnabled(true);
        f.setContentPane(sp);
        f.pack();
        f.setVisible(true);
        cd.setVisible(true);
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
