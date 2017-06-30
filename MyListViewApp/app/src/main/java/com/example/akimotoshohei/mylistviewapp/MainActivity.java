package com.example.akimotoshohei.mylistviewapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView) findViewById(R.id.myListView);

        // データを準備
        ArrayList<User> users = new ArrayList<>();

        int[] icons = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher
        };

        String[] names = {
                "Akimoto",
                "Hoge",
                "Hogehoge",
                "Hoge",
                "Hogehoge"
        };

        String[] locations = {
                "Japan",
                "US",
                "Mars",
                "US",
                "Mars"
        };

        for (int i = 0; i < icons.length; i++){
            User user = new User();
            user.setIcon(BitmapFactory.decodeResource(
                    getResources(),
                    icons[i]
            ));
            user.setName(names[i]);
            user.setLocation(locations[i]);

            users.add(user);
        }

        // Adapter - UserAdapter

        UserAdapter adapter = new UserAdapter(this, 0, users);

        // ListViewに表示

        myListView.setAdapter(adapter);

        // Event
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(
                    AdapterView<?> adapterView, View view, int i, long l) {

                TextView name = (TextView)view.findViewById(R.id.name);
                Toast.makeText(
                        MainActivity.this,
                        Integer.toString(i) + ":" + name.getText().toString(),
                        Toast.LENGTH_SHORT
                ).show();
                name.setText("Selected!");
            }
        });
    }

    public class UserAdapter extends ArrayAdapter<User> {

        // xmlをviewに変換
        private LayoutInflater layoutInflater;

        // コンストラクタ
        public UserAdapter(Context c, int id, ArrayList<User> users) {
            // 親クラスのコンストラクタを呼ぶ
            super(c, id, users);
            this.layoutInflater = (LayoutInflater) c.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {

                convertView = layoutInflater.inflate(
                        R.layout.list_item,
                        parent,
                        false
                );
                holder = new ViewHolder();
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.location = (TextView) convertView.findViewById(R.id.location);
                convertView.setTag(holder);
            } else {

                // 再利用できるViewがあった場合
                holder = (ViewHolder) convertView.getTag();
            }

            User user = (User) getItem(position);

            holder.icon.setImageBitmap(user.getIcon());
            holder.name.setText(user.getName());
            holder.location.setText(user.getLocation());

            return convertView;
        }
    }

    // findViewByIdを1回だけ行い高速化を目指す
    static class ViewHolder {

        ImageView icon;
        TextView name;
        TextView location;
    }

    // ユーザークラス　アイコン画像、名前、ロケーション
    public class User {
        private Bitmap icon;
        private String name;
        private String location;

        public Bitmap getIcon() {
            return icon;
        }

        public void setIcon(Bitmap icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

}
