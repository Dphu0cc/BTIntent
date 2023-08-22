package com.example.btintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> arrayName;

    private ImageView imgGoc, imgNhan;
    String tenHinhGoc;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String intHinh = intent.getStringExtra("tenhinhchon");
                        int idHinhNhan = getResources().getIdentifier(intHinh, "drawable", getPackageName());
                        imgNhan.setImageResource(idHinhNhan);
                        // ss theo ten hinh
                        if (tenHinhGoc .equals(intHinh)) {
                            Toast.makeText(MainActivity.this, "Chính xác", Toast.LENGTH_SHORT).show();

                            // neu dung doi hinh goc
                            new CountDownTimer(2000, 100) {
                                @Override
                                public void onTick(long l) {

                                }

                                @Override
                                public void onFinish() {
                                    // trộn mảng
                                    Collections.shuffle(arrayName);
                                    tenHinhGoc = arrayName.get(5);
                                    int idHinh = getResources().getIdentifier(arrayName.get(5), "drawable", getPackageName());

                                    imgGoc.setImageResource(idHinh);

                                }
                            }.start();
                        } else {
                            Toast.makeText(MainActivity.this, "Sai rồi", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgGoc = (ImageView) findViewById(R.id.imageViewGoc);
        imgNhan = (ImageView) findViewById(R.id.imageViewNhan);

        String[] mangTen = getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(mangTen));

        // trộn mảng
        Collections.shuffle(arrayName);
        tenHinhGoc = arrayName.get(5);
        int idHinh = getResources().getIdentifier(arrayName.get(5), "drawable", getPackageName());

        imgGoc.setImageResource(idHinh);

        imgNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
//                startActivity(intent);
                intentActivityResultLauncher.launch(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuReload) {
            Collections.shuffle(arrayName);
            tenHinhGoc = arrayName.get(5);
            int idHinh = getResources().getIdentifier(arrayName.get(5), "drawable", getPackageName());
            imgGoc.setImageResource(idHinh);
        }
        return super.onOptionsItemSelected(item);
    }
}