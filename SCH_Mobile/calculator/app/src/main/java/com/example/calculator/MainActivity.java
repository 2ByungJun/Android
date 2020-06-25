package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true; // 입력 값이 처음 입력되는가 체크

    ScrollView scrollView;
    TextView resultOperatorTextView;
    TextView resultTextView;

    ImageButton[] operatorButton = new ImageButton[8];
    ImageButton allClearButton, clearEntryButton , backSpaceButton;

    Button[] numberButton = new Button[10];
    Button numberButtonSpot;

    Calculator calculator = new Calculator(new DecimalFormat("###,###.##########"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("20154010 이병준 계산기");

        scrollView = findViewById(R.id.scroll_view);
        resultOperatorTextView = findViewById(R.id.result_operator_text_view);
        resultTextView = findViewById(R.id.result_text_view);

        allClearButton = findViewById(R.id.all_clear_button);
        clearEntryButton = findViewById(R.id.clear_entry_button);
        backSpaceButton = findViewById(R.id.back_space_button);

        for(int i=0; i<numberButton.length; i++){
            numberButton[i] = findViewById(R.id.number_button_0 + i);
        }

        for(int i=0; i<operatorButton.length; i++){
            operatorButton[i] = findViewById(R.id.operator_button_0 + i);
        }

        numberButtonSpot = findViewById(R.id.number_button_spot);

        for(Button button : numberButton){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numberButtonClick(v);
                }
            });
        }

        for(ImageButton imageButton : operatorButton){
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operatorButtonClick(v);
                }
            });
        }

        allClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allClearButtonClick(v);
            }
        });

        clearEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEntryButtonClick(v);
            }
        });

        backSpaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backSpaceButtonClick(v);
            }
        });

        numberButtonSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberButtonSpotClick(v);
            }
        });
    }

    // 소수점(.)
    private void numberButtonSpotClick(View v) {
        if(isFirstInput){
            // 처음 눌릴 경우
            resultTextView.setTextColor(0xFF000000);
            resultTextView.setText("0.");
            isFirstInput = false;
        }else {
            // . 이 존재할 경우
            if(resultTextView.getText().toString().contains(".")){
                Toast.makeText(getApplicationContext(), "이미 소숫점이 존재합니다.", Toast.LENGTH_SHORT).show();
            } else {
                resultTextView.append(".");
            }
        }
    }

    // 백스페이스
    private void backSpaceButtonClick(View v) {
        // 초기에 백스페이스를 누를 경우의 예외 처리
        if (isFirstInput && !calculator.getOperatorString().equals("")) {
            Toast.makeText(getApplicationContext(), "결과값은 지울 수 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            if(resultTextView.getText().toString().length() > 1){
                String getResultString = resultTextView.getText().toString().replace(",","");
                String subString = getResultString.substring(0, getResultString.length()-1); // 총 길이에서 한 글자를 뺌
                String decimalString = calculator.getDecimalString(subString);
                resultTextView.setText(decimalString);
            }else {
                clearText();
            }
        }
    }

    // 부분 초기화
    private void clearEntryButtonClick(View v) {
        clearText();
    }

    // 전체 초기화
    private void allClearButtonClick(View v) {
        calculator.setAllClear();
        resultOperatorTextView.setText(calculator.getOperatorString());
        clearText();
    }

    // 연산 버튼
    private void operatorButtonClick(View v) {
        String getResultString = resultTextView.getText().toString();
        String operator = v.getTag().toString();
        String getResult = calculator.getResult(isFirstInput, getResultString, operator);
        resultTextView.setText(getResult);
        resultOperatorTextView.setText(calculator.getOperatorString());
        isFirstInput = true; // 다음 수를 입력받아야하기 때문에.
    }

    // 숫자 버튼
    private void numberButtonClick(View v) {
        if(isFirstInput){
            resultTextView.setTextColor(0xFF000000);
            resultTextView.setText(v.getTag().toString());
            isFirstInput = false;
        } else {
            String getResultText = resultTextView.getText().toString().replace(",","");
            getResultText = getResultText + v.getTag().toString(); // 12,0005 => 1,200,005
            String getDecimalString = calculator.getDecimalString(getResultText);
            resultTextView.setText(getDecimalString);
        }

    }

    // 텍스트 초기화
    private void clearText() {
        isFirstInput = true;
        resultTextView.setTextColor(0xFF666666);
        resultTextView.setText(calculator.getclearInputText()); // "0"
    }


}
