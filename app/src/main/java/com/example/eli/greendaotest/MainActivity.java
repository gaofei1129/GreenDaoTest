package com.example.eli.greendaotest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eli.greendaotest.db.GreenDaoManager;
import com.example.eli.greendaotest.gen.Car;
import com.example.eli.greendaotest.gen.CarDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private EditText mNameET;
    private Button mAddBtn;
    private ListView mUserLV;

    private UserAdapter mUserAdapter;
    private List<Car> mUserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mNameET = (EditText) findViewById(R.id.et_name);
        mAddBtn = (Button) findViewById(R.id.btn_add);
        mUserLV = (ListView) findViewById(R.id.lv_user);

        mAddBtn.setOnClickListener(this);
    }

    private void initData() {
        mUserList = GreenDaoManager.getInstance().getSession().getCarDao().queryBuilder().build().list();
        mUserAdapter = new UserAdapter(this, mUserList);
        mUserLV.setAdapter(mUserAdapter);
        mUserLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //deleteUser(mUserList.get(position).getName());
                deleteUser(mUserList.get(position).getId());
            }
        });
    }

    /**
     * 根据名字更新某条数据的名字
     *
     * @param prevName 原名字
     * @param newName  新名字
     */
    private void updateUser(String prevName, String newName) {
        Car findUser = GreenDaoManager.getInstance().getSession().getCarDao().queryBuilder()
                .where(CarDao.Properties.Name.eq(prevName)).build().unique();
        if (findUser != null) {
            findUser.setName(newName);
            GreenDaoManager.getInstance().getSession().getCarDao().update(findUser);
            Toast.makeText(MyApplication.getContext(), "修改成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MyApplication.getContext(), "用户不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 根据名字删除某用户
     *
     * @param name
     */
    private void deleteUser(String name) {
        CarDao userDao = GreenDaoManager.getInstance().getSession().getCarDao();
        Car findUser = userDao.queryBuilder().where(CarDao.Properties.Name.eq(name)).build().unique();
        if (findUser != null) {
            userDao.deleteByKey(findUser.getId());

            mUserList.clear();
            mUserList.addAll(userDao.queryBuilder().build().list());
            mUserAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 根据id删除某用户
     *
     * @param id
     */
    private void deleteUser(Long id) {
        CarDao userDao = GreenDaoManager.getInstance().getSession().getCarDao();
        Car findUser = userDao.queryBuilder().where(CarDao.Properties.Id.eq(id)).build().unique();
        if (findUser != null) {
            userDao.deleteByKey(id);

            mUserList.clear();
            mUserList.addAll(userDao.queryBuilder().build().list());
            mUserAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 本地数据里添加一个User
     *
     * @param id   id
     * @param name 名字
     */
    private void insertUser(Long id, String name) {
        if (name == null) {
            return;
        }
        CarDao userDao = GreenDaoManager.getInstance().getSession().getCarDao();
        Car user = new Car(id, name);
        userDao.insert(user);
        mNameET.setText("");

        mUserList.clear();
        mUserList.addAll(userDao.queryBuilder().build().list());
        mUserAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.btn_add:
                insertUser(null, mNameET.getText().toString());
                break;
            default:
                break;
        }
    }
}  