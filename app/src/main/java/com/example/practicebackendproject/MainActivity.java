package com.example.practicebackendproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mContent;
    private TextView mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContent=findViewById(R.id.content);
        mId=findViewById(R.id.id);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.2.6:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetroInterface retroInterface=retrofit.create(RetroInterface.class);

        Call<DemoItem> call = retroInterface.getDemoItem();
        call.enqueue(new Callback<DemoItem>() {
            @Override
            public void onResponse(Call<DemoItem> call, Response<DemoItem> response) {
               // mContent.setText("tester");
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                DemoItem demoItem=response.body();
               mContent.setText(demoItem.getContent());
               mId.setText(Integer.toString((int) demoItem.getId()));
            }

            @Override
            public void onFailure(Call<DemoItem> call, Throwable t) {
               // Toast.makeText(MainActivity.this,"Server is Offline",Toast.LENGTH_LONG).show();
            }
        });
    }
}