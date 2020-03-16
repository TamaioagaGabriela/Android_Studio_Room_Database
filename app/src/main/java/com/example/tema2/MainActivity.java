package com.example.tema2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExampleItem> exampleList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();

    private EditText edt_name;
    private EditText edt_mark;
    private Button btn_add;
    private Button btn_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        edt_name = findViewById(R.id.edt_name);
        edt_mark = findViewById(R.id.edt_mark);
        btn_add = findViewById(R.id.btn1);
        btn_remove = findViewById(R.id.btn2);

        userList.addAll(new ApplicationController().getAppDatabase().userDao().getAll());
        for (int i = 0; i < userList.size(); i++) {
            exampleList.add(new ExampleItem(userList.get(i).getFullName(), userList.get(i).getMark()));
            mAdapter.notifyDataSetChanged();
        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                   //exampleList.add( new ExampleItem(edt_name.getText().toString(), edt_mark.getText().toString()));
                   //mAdapter.notifyDataSetChanged();
                    //db.userDao().insertAll(user);

                    final User user = new User(edt_name.getText().toString(), edt_mark.getText().toString());
                    new UserRepository(getApplicationContext()).insertTask(user, new OnUserRepositoryActionListener() {
                        @Override
                        public void actionSuccess() {
                            Toast.makeText(getApplicationContext(), "A fost adaugat!", Toast.LENGTH_SHORT).show();
                            //userList.clear();
                            //userList.addAll(new ApplicationController().getAppDatabase().userDao().getAll());
                            //exampleListInt
                            exampleList.add(new ExampleItem(edt_name.getText().toString(), edt_mark.getText().toString()));
                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void actionFaailed() {

                        }
                    });

                }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*for (int i=0; i< exampleList.size(); i++ )
                    if (exampleList.get(i).getmText1().equals(edt_name.getText().toString()))
                        exampleList.remove(i);
                    mAdapter.notifyDataSetChanged();*/
                final User user = new UserRepository(getApplicationContext()).getUserByName(edt_name.getText().toString());
                if (user != null){
                  new UserRepository(getApplicationContext()).deleteTask(user, new OnUserRepositoryActionListener() {
                      @Override
                      public void actionSuccess() {
                          Toast.makeText(MainActivity.this, "Sters!", Toast.LENGTH_SHORT).show();
                          for (int i=0; i< exampleList.size(); i++ )
                              if (exampleList.get(i).getmText1().equals(edt_name.getText().toString()))
                                  exampleList.remove(i);
                          mAdapter.notifyDataSetChanged();
                      }

                      @Override
                      public void actionFaailed() {

                      }
                  });
                }
            }
        });

    }
}
