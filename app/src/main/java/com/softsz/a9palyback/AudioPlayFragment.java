package com.softsz.a9palyback;

import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class AudioPlayFragment extends DialogFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "AudioPlayFragment";

    Context context;
    SeekBar mSeekBar;
    TextView totalTV;
    TextView progressTV;
    TextView descriptionTv;
    Button contralBt;
    String data;
    int duration;
    String displayName;

    String timerFormat;
    private static final int TIME_BASE = 60;

    private static final int PLAY_STATE_STOP = 0;
    private static final int PLAY_STATE_PLAYING = 1;
    private static final int PLAY_STATE_PAUSE = 2;
    private static int play_state = PLAY_STATE_STOP;

    MediaPlayer mediaPlayer;

    private Handler mHandler = new Handler();

    private Runnable mUpdateTimerBar = new Runnable() {
        @Override
        public void run() {
            int ms = 0;
            if (mediaPlayer != null)
                ms = mediaPlayer.getCurrentPosition();
            mSeekBar.setProgress(ms);
            setTimerTextView(ms / 1000, progressTV);
            mHandler.postDelayed(mUpdateTimerBar, 100);
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().requestWindowFeature(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View view = inflater.inflate(R.layout.audio_play_fragment, container);
        initViewRes(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initViewRes(View view) {
        mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
        totalTV = (TextView) view.findViewById(R.id.now_progress);
        progressTV = (TextView) view.findViewById(R.id.total_progress);
        descriptionTv = (TextView) view.findViewById(R.id.media_description);
        contralBt = (Button) view.findViewById(R.id.audio_play_contral);
        contralBt.setOnClickListener(this);

        Bundle bundle = getArguments();
        data = bundle.getString(MediaFileListFragment.MEDIA_DATA_KEY);
        displayName = bundle.getString(MediaFileListFragment.MEDIA_DISPLAYNAME_KEY);
        duration = bundle.getInt(MediaFileListFragment.MEDIA_DURATION_KEY);

        timerFormat = context.getResources().getString(R.string.timer_format);

        mSeekBar.setOnSeekBarChangeListener(this);

        mediaPlayer = new MediaPlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        descriptionTv.setText(displayName);
        setTimerTextView(duration, totalTV);

        if (play_state == PLAY_STATE_STOP) {
            try {
                mediaPlayer.setDataSource(data);
                mediaPlayer.prepare();
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
                mHandler.post(mUpdateTimerBar);
                play_state = PLAY_STATE_PLAYING;
                mSeekBar.setMax(mediaPlayer.getDuration());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        AudioPlayFragment.this.dismiss();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer = null;
        play_state = PLAY_STATE_STOP;
        mHandler.removeCallbacks(mUpdateTimerBar);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.audio_play_contral) {
            if (play_state == PLAY_STATE_PAUSE || play_state == PLAY_STATE_STOP) {
                mediaPlayer.start();
                play_state = PLAY_STATE_PLAYING;
                contralBt.setBackground(context.getDrawable(R.drawable.audio_pause));
            } else {
                mediaPlayer.pause();
                play_state = PLAY_STATE_PAUSE;
                contralBt.setBackground(context.getDrawable(R.drawable.audio_play));
            }
        }
    }

    private void setTimerTextView(int time, TextView tv) {
        int h = time / (TIME_BASE * TIME_BASE);
        int m = (time / TIME_BASE) - (h * TIME_BASE);
        String timerString = String.format(timerFormat, h, m, time % TIME_BASE);
        tv.setText(timerString);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaPlayer.seekTo(seekBar.getProgress());
    }
}
