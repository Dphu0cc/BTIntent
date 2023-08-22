package com.example.btintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Collections;

public class ImageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        myTable = (TableLayout) findViewById(R.id.tableLayoutImage);

        int soDong = 5;
        int soCot = 3;

        Collections.shuffle(MainActivity.arrayName);
        // tao dong va cot
        for (int i = 1; i <= soDong; i++) {
            TableRow tableRow = new TableRow(this);

            // tao cột
            for (int j = 1; j <= soCot; j++) {
                ImageView imageView = new ImageView(ImageActivity.this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(330, 350);
                imageView.setLayoutParams(layoutParams);
                final int vitri = soCot * (i-1) + j-1;
                int idHinh = getResources().getIdentifier(MainActivity.arrayName.get(vitri), "drawable", getPackageName());
                imageView.setImageResource(idHinh);
                // add imageView vào tableRow
                tableRow.addView(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra("tenhinhchon", MainActivity.arrayName.get(vitri));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
            // add tableRow vaod table
            myTable.addView(tableRow);
        }
    }

    TableLayout myTable;
}