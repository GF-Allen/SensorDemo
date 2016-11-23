package com.alenbeyond.sensor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alenbeyond.sensor.helper.SensorValue;

import java.util.List;

/**
 * Created by alen on 16/11/23.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<SensorValue> data;

    public MyAdapter(Context context, List<SensorValue> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (data == null) {
            new Throwable("请设置数据");
        }

        Holder holder;
        if (convertView == null) {
            View view = View.inflate(context, R.layout.listview_item, null);
            holder = new Holder(view);
            view.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv_x_value.setText("X:" + data.get(position).getX_value());
        holder.tv_y_value.setText("Y:" + data.get(position).getY_value());
        holder.tv_z_value.setText("Z:" + data.get(position).getZ_value());

        return holder.rootView;
    }

    public void setData(List<SensorValue> data) {
        this.data = data;
    }

    static class Holder {
        View rootView;
        TextView tv_x_value;
        TextView tv_y_value;
        TextView tv_z_value;

        public Holder(View rootView) {
            this.rootView = rootView;
            tv_x_value = (TextView) rootView.findViewById(R.id.x_value);
            tv_y_value = (TextView) rootView.findViewById(R.id.y_value);
            tv_z_value = (TextView) rootView.findViewById(R.id.z_value);
        }
    }
}
