package ru.alibaevrinat.project2;

import android.app.Application;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import ru.alibaevrinat.project2.classes.Fraction;

public class MainActivity extends AppCompatActivity {

    Logger logger = Logger.getGlobal();
    private Fraction fraction1;
    private Fraction fraction2;
    TextView textView12;
    TextView textView13;
    TextView textView14;
    TextView textView16;
    TextView textView17;
    TextView textView18;
    EditText editText26;
    EditText editText27;
    EditText editText28;
    TextView textView36;
    TextView textView37;
    TextView textView38;
    TextView textView50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myscreen2);
        fraction1 = new Fraction();
        fraction2 = new Fraction();
        initializeTextViews();
        setTextViews();
        initializeEditTexts();
    }

    private void initializeTextViews () {
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        textView14 = findViewById(R.id.textView14);
        textView16 = findViewById(R.id.textView16);
        textView17 = findViewById(R.id.textView17);
        textView18 = findViewById(R.id.textView18);
        textView36 = findViewById(R.id.textView36);
        textView37 = findViewById(R.id.textView37);
        textView38 = findViewById(R.id.textView38);
        textView50 = findViewById(R.id.textView50);
    }

    private void initializeEditTexts() {
        editText26 = findViewById(R.id.editText26);
        editText27 = findViewById(R.id.editText27);
        editText28 = findViewById(R.id.editText28);

    }

    private void setTextViews() {
        textView12.setText(String.valueOf(fraction1.getZnamenatel()));
        textView16.setText(String.valueOf(fraction2.getZnamenatel()));
        textView13.setText(String.valueOf(fraction1.getChislitel()));
        textView17.setText(String.valueOf(fraction2.getChislitel()));
        textView14.setText(String.valueOf(fraction1.getCeloe()));
        textView18.setText(String.valueOf(fraction2.getCeloe()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculate(View view) {
        System.out.println("in calculate()");
        int znamenatel = calcNok();
        int chisl;
        int celoe = 0;
        chisl = znamenatel/fraction1.getZnamenatel()*fraction1.getChislitel() +
                znamenatel/fraction2.getZnamenatel()*fraction2.getChislitel();

        if (chisl == znamenatel) {
            chisl = 0;
            znamenatel = 0;
            celoe = fraction1.getCeloe() + fraction2.getCeloe() + 1;
        } else if (chisl > znamenatel) {
            celoe = fraction1.getCeloe() + fraction2.getCeloe() + 1;
            chisl = chisl - znamenatel;
        } else {
            celoe = fraction1.getCeloe() + fraction2.getCeloe();
        }

        setRightAnswerTextViewes(celoe, chisl, znamenatel);
        appendEditTexts();
        checkAnswerAndPrintDecision(celoe, chisl, znamenatel);
    }

    private void checkAnswerAndPrintDecision(int celoe, int chisl, int znamenatel) {
        if (Integer.parseInt(editText26.getText().toString()) == znamenatel &&
                Integer.parseInt(editText27.getText().toString()) == chisl &&
                Integer.parseInt(editText28.getText().toString()) == celoe) {
            textView50.setText("Ваш ответ ВЕРНЫЙ");
        } else {
            textView50.setText("Ваш ответ НЕВЕРНЫЙ");
        }
    }

    private void appendEditTexts() {
        if (editText26.getText().toString().equals("")) {
            editText26.getText().append("0");
        }
        if (editText27.getText().toString().equals("")) {
            editText27.getText().append("0");
        }
        if (editText28.getText().toString().equals("")) {
            editText28.getText().append("0");
        }
    }

    private void setRightAnswerTextViewes(int celoe, int chisl, int znamenatel) {
        textView36.setText(String.valueOf(znamenatel));
        textView37.setText(String.valueOf(chisl));
        textView38.setText(String.valueOf(celoe));
    }

    private int calcNok() {
        int nok = 1;
        double znam1 = fraction1.getZnamenatel();
        double znam2 = fraction2.getZnamenatel();
        double [] arr = {2,3,5,7};
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        for (int i =0; i <arr.length; i++) {
            int tempMultiplierCount = 0;
            list1.add(tempMultiplierCount);
            while(isHaveMultiplier(arr[i],znam1)) {
                tempMultiplierCount++;
                list1.set(i, tempMultiplierCount);
                znam1/=arr[i];
            }
        }
        for (int i =0; i <arr.length; i++) {
            int tempMultiplierCount = 0;
            list2.add(tempMultiplierCount);
            while(isHaveMultiplier(arr[i],znam2)) {
                tempMultiplierCount++;
                list2.set(i, tempMultiplierCount);
                znam2/=arr[i];
            }
        }
        ArrayList resultList = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            resultList.add(Math.max((int)list1.get(i),(int)list2.get(i)));
            nok = nok * (int) Math.pow((int)arr[i], (int)resultList.get(i));
        }
        return nok;
    }

    private boolean isHaveMultiplier (double multiplier, double tempZnam) {
        return (tempZnam/multiplier - Math.floor(tempZnam/multiplier) == 0 && tempZnam != 0);
    }

    public void refreshActivity(View view) {
        fraction1 = new Fraction();
        fraction2 = new Fraction();
        setTextViews();
        clearEditTexts();
        clearRightAnswerTextViews();
//        finish();
//        startActivity(getIntent());
    }

    private void clearEditTexts () {
        editText26.getText().clear();
        editText27.getText().clear();
        editText28.getText().clear();
    }

    private void clearRightAnswerTextViews() {
        textView36.setText("");
        textView37.setText("");
        textView38.setText("");
        textView50.setText("Ваш ответ:");
    }
}
