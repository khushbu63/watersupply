package com.example.android.watersupply;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Order extends AppCompatActivity implements View.OnClickListener{

    Button btnDatePicker, btnTimePicker,btn;
    EditText bottleedit,txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_order );

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        btn=(Button)findViewById(R.id.btn);
        bottleedit=(EditText)findViewById(R.id.bottleedit);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        progressDialog=new ProgressDialog(this);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btn.setOnClickListener(this);
    }
    private void submitorder(){
        final String bottle =bottleedit.getText().toString().trim();
        final String date = txtDate.getText().toString().trim();
        final String time = txtTime.getText().toString().trim();

        progressDialog.setMessage("order done successfully.....");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest( Request.Method.POST, Constants.ORDER,
                new Response.Listener<String>() {
                   @Override
                    public void onResponse(String response) {
                        Log.e("res",response);
                     progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             progressDialog.hide();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
              Map<String,String> params=new HashMap<>();
              params.put("quantity",bottle);
              params.put("order_date",date);
              params.put("order_time",time);
              return params;
            }
        };
        Appcontroller.getInstance( this ).addToRequestQueue( stringRequest );
    }

    @Override
    public void onClick(View v) {
        if(v==btn){
              submitorder();
        }
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            //  txtDate.setText( year+"-"+(monthOfYear+1)+"-"+dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}
