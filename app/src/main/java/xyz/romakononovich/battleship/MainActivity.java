package xyz.romakononovich.battleship;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Random;

import xyz.romakononovich.battleship.model.Cell;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mContainer;
    private TestView mTestView;
    private Cell[][] mGameField;
    private int viewSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = (FrameLayout)findViewById(R.id.container);
        mTestView = new TestView(this);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);


        int width = size.x ;
        int height = size.y ;
        if(width<height) {
            viewSize = width;
        } else {
            viewSize = height;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(viewSize,viewSize);
        params.gravity = Gravity.CENTER;
        mContainer.addView(mTestView,params);
        mGameField = new Cell[10][10];
        generateGameField();
        drawGameField();
        mTestView.assignGameField(mGameField);
    }

    private void drawGameField() {

    }

    private void generateGameField() {
        int[] ships = new int[]{4,3,3,2,2,2,1,1,1,1};
        Random random = new Random();
        for (int i = 0; i < ships.length;i++) {
                generateShip(ships[i]);
            }
        }

    private void generateShip(int decks) {
        boolean isFree = false;
        Random random = new Random();
        int startX = 0;
        int startY = 0;
        boolean isVertical = false;
        while (!isFree) {
            isVertical = random.nextBoolean();
            if (isVertical) {
                startX = random.nextInt(10);
                startY = random.nextInt(10-decks);
            } else {
                startX = random.nextInt(10-decks);
                startY = random.nextInt(10);
            }
            isFree = checkStartCoordination(startX, startY, isVertical, decks);
        }
        if (isVertical) {
            for (int j = 0; j<decks; j++) {
                mGameField[startX][startY+j] = new Cell();
            }
        }else {
            startX = random.nextInt(10-decks);
            startY = random.nextInt(10);
            for (int j = 0; j<decks; j++) {
                mGameField[startX+j][startY] = new Cell();

            }}
    }

    private boolean checkStartCoordination(int startX, int startY, boolean isVertical,int decks) {
        if (isVertical) {
            for (int i = 0; i < decks; i++) {
                if (mGameField[startX][startY + i] != null) {
                    return false;
                }
                if ((startX - 1 != -1) && (startY - 1 != -1) &&
                        mGameField[startX - 1][startY + i - 1] != null) {
                    return false;
                }
                if ((startY - 1 != -1) && i == 0 &&
                        mGameField[startX][startY + i - 1] != null) {
                    return false;
                }
                if ((startX + 1 != 10) && (startY - 1 != -1) &&
                        mGameField[startX + 1][startY + i - 1] != null) {
                    return false;
                }
                if ((startX + 1 != 10) &&
                        mGameField[startX + 1][startY + i] != null) {
                    return false;
                }
                if ((startX + 1 != 10) && (startY + 1 != 10) &&
                        mGameField[startX + 1][startY + 1] != null) {
                    return false;
                }
                if ((startY + 1 != 10) &&
                        mGameField[startX][startY + i + 1] != null) {
                    return false;
                }
                if ((startX - 1 != -1) && (startY + 1 != 10) &&
                        mGameField[startX - 1][startY + i + 1] != null) {
                    return false;
                }
                if ((startX - 1 != -1) && mGameField[startX - 1][startY + i] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTestView.post(new Runnable() {
            @Override
            public void run() {
                mTestView.logSize();
            }
        });

    }
}
