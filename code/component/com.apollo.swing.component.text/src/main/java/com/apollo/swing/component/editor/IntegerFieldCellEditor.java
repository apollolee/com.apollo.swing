/*
 * 此代码创建于 2008-5-6 下午05:18:17。
 */
package com.apollo.swing.component.editor;

import java.math.BigInteger;

import com.apollo.swing.component.text.IntegerField;

/**
 * <p>文件名称：IntegerFieldCellEditor.java</p>
 * <p>类型描述：整型数输入框单元格编辑器类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-5-6</p>
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
public class IntegerFieldCellEditor extends FormattedFieldCellEditor {

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

    /* Add Protected Field */

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
    public IntegerFieldCellEditor() {
        this(0, true, true, null, null, null, null, null);
    }

    /**
     * 构造方法。
     * @param columns 列数。
     * @param minimum 最小值。
     * @param maximum 最大值。
     */
    public IntegerFieldCellEditor(int columns, long minimum, long maximum) {
        this(columns, true, true, BigInteger.valueOf(minimum), BigInteger.valueOf(maximum), null, null, null);
    }

    /**
     * 构造方法。
     * @param columns 列数。
     * @param minimum 最小值。
     * @param maximum 最大值。
     * @param value 初始化用的整型数值。
     */
    public IntegerFieldCellEditor(int columns, long minimum, long maximum, long value) {
        this(columns, true, true, BigInteger.valueOf(minimum), BigInteger.valueOf(maximum), null, null, BigInteger.valueOf(value));
    }

    /**
     * 构造方法。
     * @param columns 列数。
     * @param minimum 最小值。
     * @param maximum 最大值。
     * @param step 步长。
     * @param value 初始化用的整型数值。
     */
    public IntegerFieldCellEditor(int columns, long minimum, long maximum, long step, long value) {
        this(columns,
             true,
             true,
             BigInteger.valueOf(minimum),
             BigInteger.valueOf(maximum),
             BigInteger.valueOf(step),
             null,
             BigInteger.valueOf(value));
    }

    /**
     * 构造方法。
     * @param columns 列数。
     * @param minimum 最小值。
     * @param maximum 最大值。
     * @param step 步长。
     * @param stepBase 步长基值。
     * @param value 初始化用的整型数值。
     */
    public IntegerFieldCellEditor(int columns, long minimum, long maximum, long step, long stepBase, long value) {
        this(columns,
             true,
             true,
             BigInteger.valueOf(minimum),
             BigInteger.valueOf(maximum),
             BigInteger.valueOf(step),
             BigInteger.valueOf(stepBase),
             BigInteger.valueOf(value));
    }

    /**
     * 构造方法。
     * @param columns 列数。
     * @param isNeedInput 为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param minimum 最小值。
     * @param maximum 最大值。
     * @param step 步长。
     * @param stepBase 步长基值。
     * @param value 初始化用的整型数值。
     */
    public IntegerFieldCellEditor(int columns,
                                  boolean isNeedInput,
                                  boolean isEnabledPopupErrorTip,
                                  long minimum,
                                  long maximum,
                                  long step,
                                  long stepBase,
                                  long value) {
        this(columns,
             isNeedInput,
             isEnabledPopupErrorTip,
             BigInteger.valueOf(minimum),
             BigInteger.valueOf(maximum),
             BigInteger.valueOf(step),
             BigInteger.valueOf(stepBase),
             BigInteger.valueOf(value));
    }

    /**
     * 构造方法。
     * @param columns 列数。
     * @param isNeedInput 为true表示必须输入，为false表示不是必须输入。
     * @param isEnabledPopupErrorTip 为true表示允许弹出错误提示，为false表示不允许弹出错误提示。
     * @param minimum 最小值。
     * @param maximum 最大值。
     * @param step 步长。
     * @param stepBase 步长基值。
     * @param value 初始化用的整型数值。
     */
    public IntegerFieldCellEditor(int columns,
                                  boolean isNeedInput,
                                  boolean isEnabledPopupErrorTip,
                                  BigInteger minimum,
                                  BigInteger maximum,
                                  BigInteger step,
                                  BigInteger stepBase,
                                  BigInteger value) {
        this(new IntegerField(columns, isNeedInput, isEnabledPopupErrorTip, minimum, maximum, step, stepBase, value));
    }

    /**
     * 构造方法。
     * @param field 整型数输入框。
     */
    public IntegerFieldCellEditor(IntegerField field) {
        super(field);
    }

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
