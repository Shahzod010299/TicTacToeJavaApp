package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // o'yinda belgilanishlar
    //    0 - X o'yinchi
    //    1 - O o'yinchi
    //    2 - Null
    // yutish yo'llari
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public static int counter = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;

        // index ni o'qib olish uchun.
        int tappedImage = Integer.parseInt(img.getTag().toString());

        // o'yinni yangittan boshlash
        if (!gameActive) {
            gameReset(view);
        }

        // agar bosilgan rasm bo'sh bo'lsa
        if (gameState[tappedImage] == 2) {
            // har bir bosishdan keyin hisoblashni oshiring
            counter++;

            // oxirgi rasm bosilganini tekshirish
            if (counter == 9) {
                // o'yinni qayta boshlash
                gameActive = false;
            }

            // rasmlar massiviga qaysi rasm bosilsa osha rasmni id sini yozish
            gameState[tappedImage] = activePlayer;

            // rasmga effekt berish
            img.setTranslationY(-2000f);

            // faol oyinchini ozgartirish
            if (activePlayer == 0) {
                // image ga x rasmini qoyish
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);

                // O -  o'yinchiga bosishni eslatuvchi text
                status.setText("O o'yinchi - o'ynash uchun bosing");
            } else {
                // image ga 0 rasmini qoyish
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);

                // O -  o'yinchiga bosishni eslatuvchi text
                status.setText("X o'yinchi - o'ynash uchun bosing");
            }
            img.animate().translationYBy(2000f).setDuration(500);
        }

        int flag = 0;
        // o'yinchi g'alaba qozonganligini tekshirish
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;

                // o'yinchi g'alaba qozonga text ga osha oyinchini chiqarish
                String winnerStr;

                // o'yinni qayta boshlash
                gameActive = false;

                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X yutdi";
                } else {
                    winnerStr = "O yutdi";
                }
                // yutgan oyinchini text ga chiqarish
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        // o'yin durang bo'lganda
        if (counter == 9 && flag == 0) {
            TextView status = findViewById(R.id.status);
            status.setText("Do'stlik g'alaba qozondi");
        }
    }
    // reset the game
    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;

        // o'yin yangidan boshlanganda hammasiga 2 ni joylab chiqadi
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        // yangi oyin boshlanganda hamma rasmlarni boshatadi
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X o'yinchi - o'ynash uchun bosing");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}