package com.mobisystems.menudemo;

public class TextViewModel {


    enum IOperation {
        COPY, CUT, DELETE, PASTE
    }

    private String _text = "";
    private String _clipboard = "";

    public String getText() {
        return _text;
    }

    public void setText(CharSequence subSequence) {
        _text = subSequence.toString();
    }

    public void applyOperation(CharSequence text, IOperation operation) {
        switch (operation) {
            case CUT:
                cut(text);
                break;
            case COPY:
                copy(text);
                break;
            case PASTE:
                paste(text);
                break;
            case DELETE:
                delete(text);
                break;
        }
    }

    private void cut(CharSequence selection) {
        _clipboard = selection.toString();
        _text = "";
    }

    private void copy(CharSequence selection) {
        _clipboard = selection.toString();
    }

    private void paste(CharSequence selection) {
        _text = _clipboard;

    }

    private void delete(CharSequence selection) {
        _text = "";
    }
}
