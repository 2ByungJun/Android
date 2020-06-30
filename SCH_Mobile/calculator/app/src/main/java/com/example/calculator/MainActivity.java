package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/*****************************************************************
 * 20154010 이병준 계산기 프로젝트
 * > 산술연산 : +, -, *, /, (, ) - stack, LinkedList
 * > 나머지 : %
 * > 비트연산 : &, |, ^, ~ - stack, LinkedList
 * > 입력받는 방식 : 버튼, 사용자 텍스트, 파일 입력
 * > 출력받는 방식 : 결과 Text, 파일 쓰기
 * > History : LinkedList 로 구현
 * > 50자리 계산 : x
 * > Game (x)
 *****************************************************************/
public class MainActivity extends AppCompatActivity {

    /***** First Check Variable *****/
    boolean isFirstInput = true; // 처음 오는 수, 연산인지 판별

    /***** Declaring Variable *****/
    Button btnAllClear, btnClear, btnBack;  // AC, C, BK

    /***** Text Variable*****/
    EditText tvProcessor;   // 입력받는 화면
    TextView tvResult;      // 결과 화면
    String processor;       // 수식 저장
    String resultProcessor; // 결과 수식 저장
    String lastProcessor;   // 수식 마지막 값

    /***** Number Variable ******/
    Button[] btn_num = new Button[10];  // 0 - 9

    /***** Operator Variable  *****/
    Button btnMultiply, btnMinus, btnPlus, btnDivide, btnPercentage; // *, -, +, /, %
    Button btnDecimal; // .

    /***** Bit Variable *****/
    Button btnBitAND, btnBitOR, btnBitXOR, btnBitNOT; // &, |, ^, ~

    /***** Small Bracket Variable *****/
    Button btnSmallBracketLeft, btnSmallBracketRight; // (, )
    int isSmallBracketLeftCheck = 0; // ( 괄호 수 체크

    /***** Equal Variable *****/
    Button btnEqual;   // =
    Calculation calculation = new Calculation(); // Calculation.class

    /***** History *****/
    Button btnHistory; // Navigator - history.xml
    Option option = new Option(); // Option.class
    Intent intent;  // 페이지 간 값 전달을 위한 변수
    LinkedList<Option> historyList = new LinkedList<>(); // History List

    /***** File Read*****/
    Button btnRead; // File Read

    /***** Date *****/
    long now = System.currentTimeMillis(); // 현재 날짜
    Date date = new Date(now);  // 날짜 형태 객체로 초기화
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd"); // 양식 포멧
    String formatDate = sdfNow.format(date); // 문자 결과 생성


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***** Assigning Variable *****/
        // Clear : 지우기
        btnAllClear = (Button) findViewById(R.id.btn_all_clear);
        btnClear = (Button) findViewById(R.id.btn_delete);
        btnBack = (Button) findViewById(R.id.btn_back);
        // Edit View : 입력 뷰
        tvProcessor = (EditText) findViewById(R.id.tv_process);
        // Text View : 결과 뷰
        tvResult = (TextView) findViewById(R.id.tv_result);
        // Number Button : 0 ~ 9
        for (int i = 0; i < btn_num.length; i++) {
            btn_num[i] = findViewById(R.id.btn_0 + i);
        }
        // Operator Button : +, -, *, /, %
        btnPlus = (Button) findViewById(R.id.btn_plus);
        btnMinus = (Button) findViewById(R.id.btn_minus);
        btnMultiply = (Button) findViewById(R.id.btn_multiply);
        btnDivide = (Button) findViewById(R.id.btn_divide);
        btnPercentage = (Button) findViewById(R.id.btn_percentage);
        // Decimal : .
        btnDecimal = (Button) findViewById(R.id.btn_dot);
        // SmallBracket : (, )
        btnSmallBracketLeft = (Button) findViewById(R.id.btn_small_bracket_left);
        btnSmallBracketRight = (Button) findViewById(R.id.btn_small_bracket_right);
        // Equal : =
        btnEqual = (Button) findViewById(R.id.btn_equal);
        // Bit Operator Button : $, |, ^, ~
        btnBitAND = (Button) findViewById(R.id.btn_bit_and);
        btnBitOR = (Button) findViewById(R.id.btn_bit_or);
        btnBitXOR = (Button) findViewById(R.id.btn_bit_xor);
        btnBitNOT = (Button) findViewById(R.id.btn_bit_not);
        // Navigator Button : history
        btnHistory = (Button) findViewById(R.id.btn_history);
        // File Read Button
        btnRead = (Button) findViewById(R.id.btn_read);


        /***** Initialization Variable *****/
        tvProcessor.setText(""); // 입력 EditText 초기화
        tvResult.setText("");    // 결과 TextView 초기화

        /*****************************************************************
         *          * History Buttons on-Click
         *****************************************************************/
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** History 페이지 전환 ******/
                intent = new Intent(getApplicationContext(), History.class);
                intent.putExtra("history", historyList);
                startActivity(intent);
            }
        });

        /*****************************************************************
         * File Read Buttons on-Click
         *****************************************************************/
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    /***** File Read ******/
                    /*************************
                     * Location : [res]-[raw]-[read.txt]
                     *************************/
                    InputStream inputS = getResources().openRawResource(R.raw.read);
                    byte[] txt = new byte[inputS.available()];
                    inputS.read(txt);
                    /***** EditText .txt 수식으로 초기화 ******/
                    tvProcessor.setText(new String(txt));
                    inputS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        /*****************************************************************
         * Clear Buttons on-Click
         *****************************************************************/
        /***** 전체 지우기 : AC ******/
        btnAllClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 기본 값으로 초기화 ******/
                tvProcessor.setText(""); // 입력받는 EditText
                tvResult.setText("");    // 결과 TextView
                isFirstInput = true;     // 처음 입력받는 판단 값
                isSmallBracketLeftCheck = 0; // 괄호 갯수
            }
        });
        /***** 지우기 : C ******/
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 기본 값으로 초기화 : 결과값 제외 ******/
                tvProcessor.setText(""); // 입력받는 EditText
                isFirstInput = true;     // 처음 입력받는 판단 값
                isSmallBracketLeftCheck = 0; // 괄호 갯수
            }
        });
        /***** 백스페이스 : BK ******/
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processor = tvProcessor.getText().toString();
                /***** 처음 누를 경우 제제 ******/
                if (tvProcessor.getText().toString() == "") {
                    Toast.makeText(getApplicationContext(), "수식이 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    /***** 수식 길이 조건으로 빈값일 때 제제 ******/
                    if (processor.length() > 0) {
                        /***** 마지막 수식 저장 ******/
                        lastProcessor = processor.substring(processor.length() - 1);
                        switch (lastProcessor) {
                            /***** 특수 경우 "(" : 괄호체크 수를 줄여야함 ******/
                            case "(":
                                isSmallBracketLeftCheck--;
                                processor = processor.substring(0, processor.length() - 1);
                                tvProcessor.setText(processor);
                                break;
                            /***** 특수 경우 ")" : 괄호체크 수를 증가시켜야함 ******/
                            case ")":
                                isSmallBracketLeftCheck++;
                                processor = processor.substring(0, processor.length() - 1);
                                tvProcessor.setText(processor);
                                break;
                            /***** 기본 : 문자 하나를 지움 ******/
                            default:
                                processor = processor.substring(0, processor.length() - 1);
                                tvProcessor.setText(processor);
                                break;
                        }
                    }
                }
            }
        });

        /*****************************************************************
         * Number Buttons on-Click
         *****************************************************************/
        /***** 숫자 : 0 ~ 9 ******/
        for (final Button button : btn_num) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    processor = tvProcessor.getText().toString();
                    tvProcessor.setText(processor + v.getTag().toString());
                    isFirstInput = false;
                }
            });
        }

        /*****************************************************************
         * Operator Buttons on-Click
         *****************************************************************/
        /***** + : 덧셈 ******/
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 맨 앞에 명시할 경우 제제 ******/
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    /***** 선행이 숫자인 경우 ******/
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " + ");
                    } else { /***** 연산 뒤에 연산이 없게 방지 ******/
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /***** - : 뺄셈 ******/
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 맨 앞에 명시할 경우 제제 ******/
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    /***** 선행이 숫자인 경우 ******/
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " - ");
                    } else { /***** 연산 뒤에 연산이 없게 방지 ******/
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /***** * : 곱셈 ******/
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 맨 앞에 명시할 경우 제제 ******/
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    /***** 선행이 숫자인 경우 ******/
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " * ");
                    } else { /***** 연산 뒤에 연산이 없게 방지 ******/
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /***** / : 나눗셈 ******/
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 맨 앞에 명시할 경우 제제 ******/
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    /***** 선행이 숫자인 경우 ******/
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " / ");
                    } else { /***** 연산 뒤에 연산이 없게 방지 ******/
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /***** % : 나머지 ******/
        btnPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 맨 앞에 명시할 경우 제제 ******/
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    /***** 선행이 숫자인 경우 ******/
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " % ");
                    } else { /***** 연산 뒤에 연산이 없게 방지 ******/
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*****************************************************************
         * Decimal Buttons on-Click
         *****************************************************************/
        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 맨 앞에 명시할 경우 ex) "0." ******/
                if (isFirstInput) {
                    processor = tvProcessor.getText().toString();
                    tvProcessor.setText("0.");
                    isFirstInput = false;
                } else {
                    /***** 선행이 숫자인 경우 ex) " 234." ******/
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + ".");
                    } else { /***** 선행이 연산인 경우 ex) "1 + 0." ******/
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + "0.");
                    }
                }
            }
        });

        /*****************************************************************
         * SmallBracket Buttons on-Click
         *****************************************************************/
        btnSmallBracketLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "( ");
                /***** 괄호 개수 증가 ******/
                isSmallBracketLeftCheck++;
                isFirstInput = false;
            }
        });
        btnSmallBracketRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 맨 앞에 명시할 경우 ******/
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "선행 괄호를 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    /***** 선행 괄호 파악 "(" ******/
                    if (isSmallBracketLeftCheck > 0) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " )");
                        isSmallBracketLeftCheck--;
                    } else {
                        Toast.makeText(getApplicationContext(), "선행 괄호를 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*****************************************************************
         * Bit Buttons on-Click
         *****************************************************************/
        btnBitAND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 맨 앞에 명시할 경우 ******/
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " & ");
                    } else {
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnBitOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 맨 앞에 명시할 경우 ******/
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " | ");
                    } else {
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnBitXOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 맨 앞에 명시할 경우 ******/
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " ^ ");
                    } else {
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnBitNOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 맨 앞에 명시할 경우 ******/
                if (isFirstInput) {
                    processor = tvProcessor.getText().toString();
                    tvProcessor.setText(processor + " ~ ");
                } else {
                    // 숫자일 경우
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "연산이 없습니다.", Toast.LENGTH_SHORT).show();
                    } else { // 연산일 경우
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " ~ ");
                    }
                }
            }
        });

        /*****************************************************************
         * Equal Buttons on-Click
         *****************************************************************/
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***** 괄호 개수 체크 (, )는 1:1 비례 ******/
                if (isSmallBracketLeftCheck == 0) {
                    resultProcessor = calculation.calPostfix(tvProcessor.getText().toString());
                    tvResult.setText(resultProcessor);
                    /***** File Writing ******/
                    /*************************
                     * Location : [View]-[Device File Explorer]-[data]-[data]-[com...carculator]-[files]
                     *************************/
                    try {
                        FileOutputStream outFs = openFileOutput("file.txt",
                                Context.MODE_PRIVATE);
                        String str = "수식 : " + tvProcessor.getText().toString() +
                                "\n" + "값 : " + resultProcessor +
                                "\n" + "날짜 : " + formatDate;
                        outFs.write(str.getBytes());
                        outFs.close();
                        Toast.makeText(getApplicationContext(), "파일이 생성되었습니다.", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                    }
                } else { // 괄호 에러
                    Toast.makeText(getApplicationContext(), "error : (, )", Toast.LENGTH_SHORT).show();
                }
                /***** History LinkedList Setting ******/
                option = new Option(
                        formatDate,
                        tvProcessor.getText().toString(),
                        tvResult.getText().toString()
                );
                historyList.add(option);
            }
        });
    }

    /*****************************************************************
     * 마지막이 "(공백)"인 경우는 연산 false, 숫자인 경우 true
     *****************************************************************/
    private boolean lastProcessorNumCheck(String str) {
        switch (str.substring(str.length() - 1)) {
            case " ":
                return false;
            default:
                return true;
        }
    }
}
