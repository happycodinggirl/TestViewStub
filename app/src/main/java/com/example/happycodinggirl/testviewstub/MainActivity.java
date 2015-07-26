package com.example.happycodinggirl.testviewstub;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ItemEntity> items=new ArrayList<>();


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        long time=System.currentTimeMillis();

        for (int i=0;i<50;i++){

            ItemEntity itemEntity=new ItemEntity();
            itemEntity.setName("item " + i);
            itemEntity.setCreateTime(time);
            items.add(itemEntity);

        }
        long time2=System.currentTimeMillis()+100;

        for (int i=0;i<50;i++){

            ItemEntity itemEntity=new ItemEntity();
            itemEntity.setName("item " + i);
            itemEntity.setCreateTime(time2);

            items.add(itemEntity);
        }

        MyAdapter adapter=new MyAdapter(this,items);
        listView.setAdapter(adapter);







    }

   class MyAdapter extends BaseAdapter{
    LayoutInflater inflater;
       Context context;
       ArrayList<ItemEntity> items;

       public MyAdapter(Context context, ArrayList<ItemEntity> items) {
           this.context = context;
           this.items = items;
           inflater=LayoutInflater.from(context);
       }

       @Override
      public int getCount() {
          return items.size();
      }

      @Override
      public Object getItem(int position) {
          return items.get(position);
      }

      @Override
      public long getItemId(int position) {
          return position;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {

        ItemEntity itemEntity=items.get(position);
          ViewHolder viewHolder;
          if (convertView==null){

              convertView=inflater.inflate(R.layout.item,parent, false);
              viewHolder=new ViewHolder();
              viewHolder.textView= (TextView) convertView.findViewById(R.id.itemTv);
              viewHolder.button= (Button) convertView.findViewById(R.id.itemBtn);
                viewHolder.viewStub= (ViewStub) convertView.findViewById(R.id.mViewStub);
              convertView.setTag(viewHolder);

          }else{
              viewHolder= (ViewHolder) convertView.getTag();

          }
          presentTime(viewHolder, itemEntity, position);
          presentName(viewHolder, position);
          return convertView;
      }

       private void presentName(ViewHolder viewHolder, int position) {
           Log.v("TAG","-------VIEWhOLDER NOT NULL");
           ItemEntity itemEntity= (ItemEntity) getItem(position);
           viewHolder.textView.setText(itemEntity.getName());
       }

       class ViewHolder{
           private TextView textView;
           private Button button;
           private ViewStub  viewStub;
           public TextView timeLable;
       }

       private void presentTime(MyAdapter.ViewHolder viewHolder, ItemEntity itemEntity, int position) {
           ItemEntity item = ((ItemEntity) getItem(position));
           ItemEntity lastitem = null;
           if (position > 0) {
               lastitem = ((ItemEntity) getItem(position - 1));
           }
           if (lastitem == null || item.getCreateTime() != lastitem.getCreateTime()) {
               inflateTime(viewHolder);
           } else {
               viewHolder.viewStub.setVisibility(View.GONE);
           }

           if (viewHolder.timeLable != null) {
               viewHolder.timeLable.setText(String.valueOf(itemEntity.getCreateTime()));
           }

       }

       private void inflateTime(ViewHolder viewHolder) {
           if (viewHolder.timeLable == null) {
               viewHolder.timeLable = (TextView) viewHolder.viewStub.inflate();
           } else {
               viewHolder.timeLable.setVisibility(View.VISIBLE);
           }
       }
   }




}
