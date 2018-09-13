package com.example.max.texttospeech;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{


    Button btnTalk;
    TextToSpeech tts;
    EditText talkText;
    Spinner languageChoice;
    float pitch = -0.7f;
    float rate = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTalk = findViewById(R.id.btnTalk);
        talkText = findViewById(R.id.talkText);
        languageChoice = findViewById(R.id.languageChoice);

        addListenerOnSpinner();
        addListenerOnBtnTalk();
    }

    private void addListenerOnBtnTalk() {
        btnTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String content = talkText.getText().toString();
                speechOut(content);

            }
        });

    }

    private void addListenerOnSpinner() { //Static Spinner
        languageChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapterView.getContext(),"Selected Language:"
                        + adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();

                String Text = adapterView.getSelectedItem().toString();
                if(Text.equals("English")) {
                    int result = tts.setLanguage(Locale.UK);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language is not supported");
                    } else {
                        Log.e("TTS","Initialization Failed");
                    }
                }
                else if(Text.equals("Cantonese")) {
                    int result = tts.setLanguage(new Locale("zh", "HK"));
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language is not supported");
                    } else {
                        Log.e("TTS","Initialization Failed");
                    }
                }
                else if(Text.equals("Chinese")) {
                    int result = tts.setLanguage(Locale.TRADITIONAL_CHINESE);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language is not supported");
                    } else {
                        Log.e("TTS","Initialization Failed");
                    }
                }
                else if(Text.equals("Japanese")) {
                    int result = tts.setLanguage(Locale.JAPANESE);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language is not supported");
                    } else {
                        Log.e("TTS","Initialization Failed");
                    }
                }
                else if(Text.equals("German")) {
                    int result = tts.setLanguage(Locale.GERMAN);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language is not supported");
                    } else {
                        Log.e("TTS","Initialization Failed");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @Override
    public void onInit(int i) {
        if(i == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.UK);
            tts.setPitch(pitch);
            tts.setSpeechRate(rate);

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            } else {
                Log.e("TTS","Initialization Failed");
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        tts = new TextToSpeech(this,this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        tts.shutdown();
    }

    private void speechOut(String msg){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null,null);
        else
            tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
    }
}
