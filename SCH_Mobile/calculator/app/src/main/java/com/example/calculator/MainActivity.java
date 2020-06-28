package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/*****************************************************************
 * 20154010 이병준 계산기 프로젝트
 *****************************************************************/
public class MainActivity extends AppCompatActivity {

    /***** First Check *****/
    boolean isFirstInput = true;

    /***** Declaring Variable *****/
    Button btnAllClear, btnClear, btnBack;  // AC, C, BK

    /***** Text Variable*****/
    EditText tvProcessor;
    TextView tvResult;
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
    Calculation calculation = new Calculation();

    /***** History *****/
    Button btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***** Assigning Variable *****/
        btnAllClear = (Button) findViewById(R.id.btn_all_clear);
        btnClear = (Button) findViewById(R.id.btn_delete);
        btnBack = (Button) findViewById(R.id.btn_back);
        tvProcessor = (EditText) findViewById(R.id.tv_process);
        tvResult = (TextView) findViewById(R.id.tv_result);
        for (int i = 0; i < btn_num.length; i++) {
            btn_num[i] = findViewById(R.id.btn_0 + i);
        }
        btnMultiply = (Button) findViewById(R.id.btn_multiply);
        btnMinus = (Button) findViewById(R.id.btn_minus);
        btnPlus = (Button) findViewById(R.id.btn_plus);
        btnDivide = (Button) findViewById(R.id.btn_divide);
        btnPercentage = (Button) findViewById(R.id.btn_percentage);
        btnDecimal = (Button) findViewById(R.id.btn_dot);
        btnSmallBracketLeft = (Button) findViewById(R.id.btn_small_bracket_left);
        btnSmallBracketRight = (Button) findViewById(R.id.btn_small_bracket_right);
        btnEqual = (Button) findViewById(R.id.btn_equal);
        btnBitAND = (Button) findViewById(R.id.btn_bit_and);
        btnBitOR = (Button) findViewById(R.id.btn_bit_or);
        btnBitXOR = (Button) findViewById(R.id.btn_bit_xor);
        btnBitNOT = (Button) findViewById(R.id.btn_bit_not);
        btnHistory = (Button) findViewById(R.id.btn_history);


        /***** Initialization Variable *****/
        tvProcessor.setText("");
        tvResult.setText("");

        /*****************************************************************
        * History Buttons on-Click
        *****************************************************************/
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), History.class);
                startActivity(intent);
            }
        });

        /*****************************************************************
         * Clear Buttons on-Click
         *****************************************************************/
        btnAllClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvProcessor.setText("");
                tvResult.setText("");
                isFirstInput = true;
                isSmallBracketLeftCheck = 0;
                lastProcessor = "";
                resultProcessor = "";
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvProcessor.setText("");
                isFirstInput = true;
                isSmallBracketLeftCheck = 0;
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processor = tvProcessor.getText().toString();
                if (tvProcessor.getText().toString() == "") {
                    Toast.makeText(getApplicationContext(), "수식이 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    if (processor.length() > 0) {
                        lastProcessor = processor.substring(processor.length() - 1);
                        switch (lastProcessor) {
                            case "(":
                                isSmallBracketLeftCheck--;
                                processor = processor.substring(0, processor.length() - 1);
                                tvProcessor.setText(processor);
                                break;
                            case ")":
                                isSmallBracketLeftCheck++;
                                processor = processor.substring(0, processor.length() - 1);
                                tvProcessor.setText(processor);
                                break;
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
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " + ");
                    } else {
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " - ");
                    } else {
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " * ");
                    } else {
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " / ");
                    } else {
                        Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "연산할 값을 앞에 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + " % ");
                    } else {
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
                if (isFirstInput) {
                    processor = tvProcessor.getText().toString();
                    tvProcessor.setText("0.");
                    isFirstInput = false;
                } else {
                    if (lastProcessorNumCheck(tvProcessor.getText().toString())) {
                        processor = tvProcessor.getText().toString();
                        tvProcessor.setText(processor + ".");
                    } else {
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
                isSmallBracketLeftCheck++;
                isFirstInput = false;
            }
        });
        btnSmallBracketRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstInput) {
                    Toast.makeText(getApplicationContext(), "선행 괄호를 명시하세요", Toast.LENGTH_SHORT).show();
                } else {
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
                // 괄호 개수 일치 판단
                if (isSmallBracketLeftCheck == 0) {
                    resultProcessor = calculation.calPostfix(tvProcessor.getText().toString());
                    tvResult.setText(resultProcessor);
                } else { // 괄호 에러
                    Toast.makeText(getApplicationContext(), "error : (, )", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean lastProcessorNumCheck(String str) {
        switch (str.substring(str.length() - 1)) {
            case " ":
                return false;
            default:
                return true;
        }
    }
}
