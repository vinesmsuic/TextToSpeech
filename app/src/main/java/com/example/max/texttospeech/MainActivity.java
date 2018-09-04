package com.example.max.texttospeech;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{


    Button btnSubmit;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onInit(int i) {
        if(i == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.TRADITIONAL_CHINESE);
            tts.setPitch(-0.7f);
            tts.setSpeechRate(1);

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
