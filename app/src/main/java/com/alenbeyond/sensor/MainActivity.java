package com.alenbeyond.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventCallback;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.alenbeyond.sensor.helper.SensorHelper;
import com.alenbeyond.sensor.helper.SensorValue;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sm;
    private Sensor linearSensor;
    private TextView tv_x;
    private TextView tv_y;
    private TextView tv_z;
    private SensorHelper helper;

    private boolean isStart;
    private boolean isView; //查看历史记录
    private MySensorListener sensorListener;
    private Button btnStart;
    private Button btnView;
    private View llCheck;
    private View llHistory;
    private ListView lvHistory;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        linearSensor = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION); // 获取传感器类型
        sensorListener = new MySensorListener();
        helper = new SensorHelper(this);
    }

    private void initView() {
        tv_x = (TextView) findViewById(R.id.x_value);
        tv_y = (TextView) findViewById(R.id.y_value);
        tv_z = (TextView) findViewById(R.id.z_value);
        lvHistory = (ListView) findViewById(R.id.lv_history);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnView = (Button) findViewById(R.id.btn_view);
        llCheck = findViewById(R.id.ll_check);
        llHistory = findViewById(R.id.ll_history);
        llCheck.setVisibility(View.VISIBLE);
    }

    public void view(View view) {
        llCheck.setVisibility(isView ? View.VISIBLE : View.INVISIBLE);
        llHistory.setVisibility(isView ? View.INVISIBLE : View.VISIBLE);
        if (!isView) {
            List<SensorValue> data = helper.findAll();
            if (adapter == null) {
                adapter = new MyAdapter(this, data);
                lvHistory.setAdapter(adapter);
            } else {
                adapter.setData(data);
                adapter.notifyDataSetChanged();
            }
        }
        isView = !isView;
        btnView.setText(isView ? "记录" : "查看");
    }

    public void click(View view) {
        if (!isStart) { // 正在检测
            sm.registerListener(sensorListener, linearSensor, SensorManager.SENSOR_DELAY_NORMAL);// 注册传感器,参数三为延迟
        } else {
            sm.unregisterListener(sensorListener, linearSensor);
        }
        isStart = !isStart;
        btnStart.setText(isStart ? "停止" : "检测");
    }

    public void wipe(View view) {
        helper.deleteAll();
    }

    class MySensorListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x_value = event.values[0]; //x轴加速度
            float y_value = event.values[1]; //y轴加速度
            float z_value = event.values[2]; //z轴加速度

            tv_x.setText("x:" + x_value);
            tv_y.setText("y:" + y_value);
            tv_z.setText("z:" + z_value);

            helper.save(new SensorValue()
                    .setX_value(x_value + "")
                    .setY_value(y_value + "")
                    .setZ_value(z_value + ""));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

}
