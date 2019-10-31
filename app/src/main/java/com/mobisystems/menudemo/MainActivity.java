package com.mobisystems.menudemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {

    private PopupWindow _popupWindow;
    private ViewGroup _container;
    private AppCompatTextView _textView;
    private AppCompatEditText _editText;
    private TextViewModel _textViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
         _textViewModel = new TextViewModel();
        _container = findViewById(R.id.activity_container);
        _textView = findViewById(R.id.textView);
        _editText = findViewById(R.id.editText);
        RecyclerView rv = new RecyclerView(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(llm);
        rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            int padding = (int) getResources().getDimension(R.dimen.decorator_padding);

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                       @NonNull RecyclerView parent,
                                       @NonNull RecyclerView.State state) {
                outRect.set(padding, padding, padding, padding);
            }
        });
        rv.requestDisallowInterceptTouchEvent(true);
        PopupAdapter adapter = getAdapter();
        rv.setAdapter(adapter);
        CardView cardView = new CardView(this);
        cardView.addView(rv);
        _popupWindow = new PopupWindow(cardView, WRAP_CONTENT, WRAP_CONTENT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP)
            showPopupWindow(_container, (int) event.getX(), (int) event.getY());
        return super.onTouchEvent(event);
    }

    private PopupAdapter getAdapter() {
        ArrayList<PopupItem> items = new ArrayList<>();
        items.add(new PopupItem(getString(R.string.cut), R.drawable.ic_tb_cut,
                TextViewModel.IOperation.CUT));
        items.add(new PopupItem(getString(R.string.copy), R.drawable.ic_tb_copy,
                TextViewModel.IOperation.COPY));
        items.add(new PopupItem(getString(R.string.paste), R.drawable.ic_tb_paste,
                TextViewModel.IOperation.PASTE));
        items.add(new PopupItem(getString(R.string.delete), R.drawable.ic_tb_delete_delete,
                TextViewModel.IOperation.DELETE));
        return new PopupAdapter(this, items, (adapter, position) -> {
            PopupItem item = adapter.getItem(position);
            applyOperation(item.getOperation());
        });
    }

    private void applyOperation(TextViewModel.IOperation operation) {
        int selectionStart = _editText.getSelectionStart();
        int selectionEnd = _editText.getSelectionEnd();
        _textViewModel.applyOperation(_editText.getText().subSequence(selectionStart, selectionEnd), operation);
        _textView.setText(_textViewModel.getText());
    }

    private void showPopupWindow(View parent, int x, int y) {
        if (_popupWindow.isShowing())
            _popupWindow.dismiss();
        else
            _popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, x, y);
    }
}
