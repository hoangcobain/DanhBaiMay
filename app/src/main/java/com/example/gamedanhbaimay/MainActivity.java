package com.example.gamedanhbaimay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int manghinhbai[] = {
            R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10,
            R.drawable.cj, R.drawable.cq, R.drawable.ck,
            R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5,
            R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
            R.drawable.dj, R.drawable.dq, R.drawable.dk,
            R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
            R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10,
            R.drawable.hj, R.drawable.hq, R.drawable.hk,
            R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
            R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
            R.drawable.sj, R.drawable.sq, R.drawable.sk};
    int[] value_bot1;
    int[] value_bot2;
    ImageView iv1,iv2,iv3,ivb1,ivb2,ivb3;
    CountDownTimer timer;
    TextView tv_KetQua,tv_KetQuaBot;
    RadioButton rd1,rd2,rd3;
    int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        iv1 = findViewById(R.id.imageView1);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);

        ivb1 = findViewById(R.id.imageViewBot1);
        ivb2 = findViewById(R.id.imageViewBot2);
        ivb3 = findViewById(R.id.imageViewBot3);

        rd1 = findViewById(R.id.radiobutton1);
        rd2 = findViewById(R.id.radiobutton2);
        rd3 = findViewById(R.id.radiobutton3);

        Button btnStop = findViewById(R.id.btnStop);
        Button btnContinue = findViewById(R.id.btnContinue);
        time = 6000;

        tv_KetQua = findViewById(R.id.textView_KetQua);
        tv_KetQuaBot = findViewById(R.id.textView_KetQuaBot);

            timer = new CountDownTimer(16069000, time) {
                @Override
                public void onTick(long l) {
                    value_bot1 = layBaSoNgauNhien(0, 51);
                    value_bot2 = layBaSoNgauNhien(0, 51);
                    layBaiNgauNhien(value_bot1,value_bot2);


                    tv_KetQua.setText(tinhKetQua(value_bot1));
                    tv_KetQuaBot.setText(tinhKetQua(value_bot2));

                    phanThangThua(value_bot1, value_bot2);
                }

                @Override
                public void onFinish() {

                }
            }.start();

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();
            }
        });
    }

    //----------------------------------------------------
    private void layBaiNgauNhien(int[] value,int[] value2){
        iv1.setImageResource(manghinhbai[value[0]]);
        iv2.setImageResource(manghinhbai[value[1]]);
        iv3.setImageResource(manghinhbai[value[2]]);

        ivb1.setImageResource(manghinhbai[value2[0]]);
        ivb2.setImageResource(manghinhbai[value2[1]]);
        ivb3.setImageResource(manghinhbai[value2[2]]);


    }
    //----------------------------------------------------
    private void phanThangThua(int[] value1 , int[] value2){
        if(tinhDiemNguoiThang(value1) > tinhDiemNguoiThang(value2))
            Toast.makeText(MainActivity.this, "Máy 1 thắng", Toast.LENGTH_SHORT).show();
        else if(tinhDiemNguoiThang(value1) < tinhDiemNguoiThang(value2))
            Toast.makeText(MainActivity.this, "Máy 2 thắng", Toast.LENGTH_SHORT).show();
        else if(tinhDiemNguoiThang(value1) == tinhDiemNguoiThang(value2))
            Toast.makeText(MainActivity.this, "Hòa", Toast.LENGTH_SHORT).show();
    }

    //----------------------------------------------------
    private String tinhKetQua(int[] baso) {
        String ketqua = "";
        if (tinhSoTay(baso) == 3)
            ketqua = "Kết quả : 3 tây";
        else {
            int tong = 0;
            for (int i = 0; i < baso.length; i++)
                if (baso[i] % 13 < 10)
                    tong += baso[i] % 13 + 1;
            if (tong % 10 == 0)
                ketqua = "Kết quả bù, trong đó có " + tinhSoTay(baso) + " tây.";
            else
                ketqua = "Kết quả là " + (tong % 10) + " nút, trong đó có " + tinhSoTay(baso) + " tây.";
        }
        return ketqua;
    }
    //----------------------------------------------------
    private int tinhDiemNguoiThang(int[] diem){
        if(tinhSoTay(diem) == 3)
            return 30;
        else{
            int tong = 0;
            for (int i = 0; i < diem.length; i++)
                if (diem[i] % 13 < 10)
                    tong += diem[i] % 13 + 1;
            if (tong % 10 == 0)
                return 0;
            else
                return  tong % 10;
        }
    }

    //----------------------------------------------------
    private int tinhSoTay(int[] a) {
        int k = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] % 13 >= 10 && a[i] % 13 < 13)
                k++;
        return k;
    }

    //----------------------------------------------------
    private int[] layBaSoNgauNhien(int min, int max) {
        int[] baso = new int[3];
        int i = 0;
        baso[i++] = random(min, max);
        do {
            int k = random(min, max);
            if (!kiemTraTrung(k, baso))
                baso[i++] = k;
        } while (i < 3);
        return baso;
    }

    //---------------------------------------------------
    private boolean kiemTraTrung(int k, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == k)
                return true;
        return false;
    }

    // Lay gia tri ngau nhien
    private int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}