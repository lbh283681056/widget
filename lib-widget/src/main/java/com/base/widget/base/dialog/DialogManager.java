package com.base.widget.base.dialog;

import android.app.Dialog;

import java.util.Stack;

/**
 * 应用程序Dialog管理类：用于Dialog管理和应用程序退出
 *
 * 作者 kymjs
 * 版本 1.0
 * @created 2013-11-24
 */
public class DialogManager {
    private static Stack<Dialog> dialogStack;
    private static DialogManager instance;

    private DialogManager() {
    }

    /**
     * 单实例 , UI无需考虑多线程同步问题
     */
    public static DialogManager getDialogsManager() {
        if (instance == null) {
            instance = new DialogManager();
        }
        return instance;
    }

    /**
     * 添加Dialog到栈
     */
    public void addDialog(Dialog Dialog) {
        if (dialogStack == null) {
            dialogStack = new Stack<Dialog>();
        }
        dialogStack.add(Dialog);
    }

    /**
     * 获取当前Dialog（栈顶Dialog）
     */
    public Dialog currentDialog() {
        if (dialogStack == null || dialogStack.isEmpty()) {
            return null;
        }
        Dialog Dialog = dialogStack.lastElement();
        return Dialog;
    }

    /**
     * 获取当前Dialog（栈顶Dialog） 没有找到则返回null
     */
    public Dialog findDialog(Class<?> cls) {
        Dialog Dialog = null;
        for (Dialog aty : dialogStack) {
            if (aty.getClass().equals(cls)) {
                Dialog = aty;
                break;
            }
        }
        return Dialog;
    }

    /**
     * 结束当前Dialog（栈顶Dialog）
     */
    public void removeDialog() {
        Dialog dialog = dialogStack.lastElement();
        removeDialog(dialog);
    }

    /**
     * 结束指定的Dialog(重载)
     */
    public void removeDialog(Dialog dialog) {
        if (dialog != null) {
            dialogStack.remove(dialog);
            dialog.dismiss();
            dialog = null;
        }
    }

    /**
     * 结束指定的Dialog(重载)
     */
    public void finishDialog(Class<?> cls) {
        for (Dialog dialog : dialogStack) {
            if (dialog.getClass().equals(cls)) {
                removeDialog(dialog);
            }
        }
    }

    public static Stack<Dialog> getDialogStack() {
        return dialogStack;
    }

    /**
     * 关闭除了指定Dialog以外的全部Dialog 如果cls不存在于栈中，则栈全部清空
     *
     * 属性 cls
     */
    public void finishOthersDialog(Class<?> cls) {
        for (Dialog dialog : dialogStack) {
            if (!(dialog.getClass().equals(cls))) {
                removeDialog(dialog);
            }
        }
    }

    /**
     * 结束所有Dialog
     */
    public void finishAllDialog() {
        for (int i = dialogStack.size() - 1; i >= 0; i--) {
            if (null != dialogStack.get(i)) {
                dialogStack.get(i).dismiss();
            }
        }
        dialogStack.clear();
    }


}