package com.example.administrator.neteasenews.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.neteasenews.R;
import com.example.administrator.neteasenews.biz.Xhttp;
import com.example.administrator.neteasenews.db.DatebaseHelper;
import com.example.administrator.neteasenews.fragment.NewsContentFragment;

public class BrowerActivity extends AppCompatActivity {

    public static final String KEY_DOCID ="docid" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brower);
        NewsContentFragment newsContentFragment = NewsContentFragment.newInstance(getIntent().getStringExtra(KEY_DOCID));

        replace(newsContentFragment,false);

    }
    public static void start(Context context,String docId) {
        Intent starter = new Intent(context, BrowerActivity.class);
        starter.putExtra(KEY_DOCID,docId);
        context.startActivity(starter);
    }
    public void replace(Fragment f,boolean isAddToBackStack){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.activity_brower, f, f.getClass().getSimpleName());

        if (isAddToBackStack){
          ft.addToBackStack("");
        }
        ft.commit();
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_favor, menu);
            return true;
        }
        public boolean onOptionsItemSelected(MenuItem item) {
            int id=item.getItemId();
            switch (id) {
                case R.id.action_share:
                    //增加
                    Toast.makeText(this, "你好", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_favor:
                    //查找
                    AlertDialog.Builder b=new AlertDialog.Builder(this);
                    b.setTitle("温馨提示：");
                    b.setMessage("确定收藏该文章吗？");
                    b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Xhttp.getNewsContentList(getIntent().getStringExtra(KEY_DOCID), new Xhttp.NewsContentListener() {
                                @Override
                                public void onFinish(String str) {
                                    DatebaseHelper dbHelper=new DatebaseHelper(BrowerActivity.this, "test_db");
                                    SQLiteDatabase db=dbHelper.getReadableDatabase();
                                    ContentValues values=new ContentValues();
                                    values.put("KEY",str);
                                    db.insert("mytable","2",values);
                                    db.execSQL("sqlite> create table mytable(id integer primary key, value text)");
//                                            sqlite> create table mytable(id integer primary key, value text);

                                }
                            });
                            Toast.makeText(BrowerActivity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("取消",null);
                    b.show();
                    return true;

            }
            return super.onOptionsItemSelected(item);
        }
}
