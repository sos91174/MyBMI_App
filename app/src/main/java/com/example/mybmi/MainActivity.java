package com.example.mybmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取得控制項物件
        initViews();
        //設定監聽事件
        setListensers();
    }

    private Button button_calc;
    private EditText num_height;
    private EditText num_weight;
    private TextView show_result;
    private TextView show_suggest;

    //取得控制項物件
    private void initViews()
    {
        button_calc = (Button)findViewById(R.id.button);
        num_height = (EditText)findViewById(R.id.height);
        num_weight = (EditText)findViewById(R.id.weight);
        show_result = (TextView)findViewById(R.id.result);
        show_suggest = (TextView)findViewById(R.id.suggest);
    }

    //設定監聽事件
    private void setListensers()
    {
        button_calc.setOnClickListener(calcBMI);
    }

    private View.OnClickListener calcBMI = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try
            {
                DecimalFormat nf = new DecimalFormat("0.00");
                //身高
                double height = Double.parseDouble(num_height.getText().toString())/100;
                //體重
                double weight = Double.parseDouble(num_weight.getText().toString());
                //計算出BMI值
                double BMI = weight / (height*height);

                //結果
                show_result.setText(getText(R.string.bmi_result) + nf.format(BMI));

                //建議
                if(BMI > 25) //太重了
                    show_suggest.setText(R.string.advice_heavy);
                else if(BMI < 20) //太輕了
                    show_suggest.setText(R.string.advice_light);
                else //剛剛好
                    show_suggest.setText(R.string.advice_average);
            }
            catch(Exception obj)
            {
                Toast.makeText(MainActivity.this, "要先輸入身高體重喔!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.action_settings:
                openOptionsDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private void openOptionsDialog()
    {
        Toast popup = Toast.makeText(this, "BMI計算機", Toast.LENGTH_SHORT);
        popup.show();
    }



}