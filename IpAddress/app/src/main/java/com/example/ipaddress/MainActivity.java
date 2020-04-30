package com.example.ipaddress;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ipaddress.model.ip;
import com.example.ipaddress.remote.ipService;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ipService mService;
    TextView txtIp;
    Button btnGetIp;
//    AlertDialog dialog = new SpotsDialog(MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = common.getIpService();

        txtIp =(TextView) findViewById(R.id.IpAdd);
        btnGetIp = (Button) findViewById(R.id.btnGetIp);

        btnGetIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIpAddress();
            }
        });
    }

    private void getIpAddress() {
        mService.getIp().enqueue(new Callback<ip>() {
            @Override
            public void onResponse(Call<ip> call, Response<ip> response) {
                txtIp.setText(response.body().getIp());
            }

            @Override
            public void onFailure(Call<ip> call, Throwable t) {
                Log.e("error",t.getMessage());

            }
        });
    }
}