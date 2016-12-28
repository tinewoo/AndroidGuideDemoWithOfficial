package com.official.android.androidofficialguidedemo.multimedia.manageaudioplayback;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.official.android.androidofficialguidedemo.R;

public class ManageAudioActivity extends AppCompatActivity {

    private Button bt_identify_which_audio_tream, bt_control_audio_volume_by_hardware_volumekeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_audio);
        bt_identify_which_audio_tream = (Button)findViewById(R.id.bt_identify_which_audio_tream);
        bt_control_audio_volume_by_hardware_volumekeys = (Button)findViewById(R.id.bt_control_audio_volume_by_hardware_volumekeys);
        registerMediaButtonEventReceiver();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_audio, menu);
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

    class BTClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_identify_which_audio_tream:
                    identifyWhichAudioStream();
                    break;
                case R.id.bt_control_audio_volume_by_hardware_volumekeys:

                    break;
                default:

                    break;
            }
        }
    }

    private void identifyWhichAudioStream(){
//        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        String audioStreamType;
        switch (getVolumeControlStream()){
            case AudioManager.STREAM_VOICE_CALL:
                audioStreamType = "STREAM_VOICE_CALL";
                break;
            case AudioManager.STREAM_SYSTEM:
                audioStreamType = "STREAM_SYSTEM";
                break;
            default:
                audioStreamType = "OtherAudioStream";
                break;
        }
        Toast.makeText(this, audioStreamType, Toast.LENGTH_LONG).show();
    }

    AudioManager am;
    ComponentName mbCN;
    private void registerMediaButtonEventReceiver(){
        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

//        ...
        mbCN = new ComponentName(getPackageName(),RemoteControlReceiver.class.getName());

//  Start listening for button presses
        am.registerMediaButtonEventReceiver(mbCN);
//        ...

        //AUDIOFOCUS_GAIN:永久的焦点锁定,如播放音乐AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:短暂的焦点锁定，如短信提示音，或地图导航指示音
//  Request audio focus for playback
        int result = am.requestAudioFocus(afChangeListener,
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            am.registerMediaButtonEventReceiver(mbCN);
            // Start playback.
        }

    }

    private void unregisterMediaButtonEventReceiver(){
        if(am != null && mbCN != null){
            // Stop listening for button presses
            am.unregisterMediaButtonEventReceiver(mbCN);
        }

    }

    //处理失去音频焦点
    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
//          不使用使用duck：播放器在暂时失去音频焦点时暂停播放，并在重新获得音频焦点之后恢复播放
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                // Pause playback
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Resume playback
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {//失去音频焦点，表示结束了播放
                am.unregisterMediaButtonEventReceiver(mbCN);
                am.abandonAudioFocus(afChangeListener);
                // Stop playback
            }
//          使用duck：播放器在暂时失去音频焦点时降低音量，并在重新获得音频焦点之后恢复原来音量
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Lower the volume
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Raise it back to normal
            }
        }
    };

//    private void checkWhatHardwareUsing(){
//        if (isBluetoothA2dpOn()) {
//            // Adjust output for Bluetooth.
//        } else if (isSpeakerphoneOn()) {
//            // Adjust output for Speakerphone.
//        } else if (isWiredHeadsetOn()) {
//            // Adjust output for headsets
//        } else {
//            // If audio plays and noone can hear it, is it still playing?
//        }
//    }

    private IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
    NoisyAudioStreamReceiver myNoisyAudioStreamReceiver = new NoisyAudioStreamReceiver();

    private void startPlayback() {
        registerReceiver(myNoisyAudioStreamReceiver, intentFilter);
    }

    private void stopPlayback() {
        unregisterReceiver(myNoisyAudioStreamReceiver);
    }

}
