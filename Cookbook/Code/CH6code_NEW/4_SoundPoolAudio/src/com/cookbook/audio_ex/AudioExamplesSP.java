package com.cookbook.audio_ex;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AudioExamplesSP extends Activity {
    static float rate = 0.5f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main); 
        Button playDrumButton = (Button) findViewById(R.id.play_pause);

        final SoundPool mySP = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        final int soundId = mySP.load(this, R.raw.drum_beat, 1);
        
        playDrumButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {                
                rate = 1/rate;
                mySP.play(soundId, 1f, 1f, 1, 7, rate);
            }
        });
    }
}
