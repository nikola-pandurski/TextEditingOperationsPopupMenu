package com.mobisystems.menudemo;

import androidx.annotation.DrawableRes;

public class PopupItem {

    private String _title;
    private int _icon;
    private TextViewModel.IOperation _operation;

    public PopupItem(String title, @DrawableRes int icon, TextViewModel.IOperation operation) {
        _title = title;
        _icon = icon;
        _operation = operation;
    }

    public String getTitle() {
        return _title;
    }

    public int getIcon() {
        return _icon;
    }

    public TextViewModel.IOperation getOperation(){
        return _operation;
    }
}
