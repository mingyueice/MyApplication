package com.example.mingyueice.myapplication;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.EditText;
import android.view.Gravity;
import android.view.View.OnClickListener;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private ListView listView;
    private SimpleAdapter adapter;
    private DBUtil dbUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn_all);
        btn2 = (Button) findViewById(R.id.btn_add);
        listView = (ListView) findViewById(R.id.listView);
        dbUtil = new DBUtil();

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideButton(true);
                setListView();
            }
        });

        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideButton(true);
                setAddDialog();
            }
        });

    }

    /**
     * 设置弹出添加对话框
     */
    private void setAddDialog() {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_add);
        dialog.setTitle("输入添加的货物的信息");
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);

        final EditText cNameEditText = (EditText) dialog.findViewById(R.id.editText1);
        final EditText cNumEditText = (EditText) dialog.findViewById(R.id.editText2);
        Button btnConfirm = (Button) dialog.findViewById(R.id.button1);
        Button btnCancel = (Button) dialog.findViewById(R.id.button2);

        btnConfirm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                dbUtil.insertCargoInfo(cNameEditText.getText().toString(), cNumEditText.getText().toString());
                dialog.dismiss();
                hideButton(false);
                Toast.makeText(MainActivity.this, "成功添加数据", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                hideButton(false);
            }
        });
        dialog.show();
    }
    /**
     * 设置listView
     */
    private void setListView() {

        listView.setVisibility(View.VISIBLE);

        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        list = dbUtil.getAllInfo();

        adapter = new SimpleAdapter(
                MainActivity.this,
                list,
                R.layout.adapter_item,
                new String[] { "name", "conid"},
                new int[] { R.id.txt_name, R.id.txt_conid});

        listView.setAdapter(adapter);

    }
    /**
     * 设置button的可见性
     */
    private void hideButton(boolean result) {
        if (result) {
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
        } else {
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
        }

    }
    /**
     * 返回按钮的重写
     */
    @Override
    public void onBackPressed()
    {
        if (listView.getVisibility() == View.VISIBLE) {
            listView.setVisibility(View.GONE);
            hideButton(false);
        }else {
            MainActivity.this.finish();
        }
    }


}
