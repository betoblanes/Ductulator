package com.example.beto.ductulatorman;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button,button_refresh;
    private Spinner spinner;
    String [] ductType = {"Rectangular duct","Circular duct"};

    private EditText flow_rate_input, max_vel_input, max_duct_height_input;
    private TextView rect_duct_height_output, rect_duct_width_output,circ_duct_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////////////////////////////////////////
        spinner = (Spinner)findViewById(R.id.spinner2);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ductType));
        //button
        button = (Button)findViewById(R.id.button_calculate);
        button_refresh = (Button)findViewById(R.id.button_refresh);
        //inputs
        flow_rate_input = (EditText)findViewById(R.id.editText_FlowRate);
        max_vel_input = (EditText)findViewById(R.id.editText_maxvel);
        max_duct_height_input = (EditText)findViewById(R.id.editText_maxDuct);
        //outputs
        rect_duct_height_output = (TextView)findViewById(R.id.textView8);
        rect_duct_width_output = (TextView)findViewById(R.id.textView10);
        circ_duct_output = (TextView)findViewById(R.id.textView12);

        //Onclicks
        //calculate
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("Ha pulsado");
                double flow_rate_input_value = Double.parseDouble(flow_rate_input.getText().toString());
                double max_vel_input_value = Double.parseDouble(max_vel_input.getText().toString());
                double max_duct_height_input_value = Double.parseDouble(max_duct_height_input.getText().toString());
                //create duct class
                Duct duct = new Duct(flow_rate_input_value,max_vel_input_value,max_duct_height_input_value);

                int outputValue1 = (int)duct.rectangular_ductHeight();
                int outputValue2 = (int)duct.rectangular_ductWidth();
                int outputValue3 = (int)duct.circular_duct();
                rect_duct_height_output.setText(String.valueOf(outputValue1));
                rect_duct_height_output.setTextColor(Color.BLUE);
                rect_duct_width_output.setText(String.valueOf(outputValue2));
                circ_duct_output.setText(String.valueOf(outputValue3));

            }
        });
     //refresh
        button_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flow_rate_input.setText("");
                max_vel_input.setText("");
                max_duct_height_input.setText("");
            }
        });

    }

}
