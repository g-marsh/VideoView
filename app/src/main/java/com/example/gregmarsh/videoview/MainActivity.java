package com.example.gregmarsh.videoview;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.TextView;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class MainActivity extends ActionBarActivity {

//    private int msec;
//    private int msec2;
    private float msec;
    private float msec2;
    private float bpm;
    private float msecAdd;
    private TextView textView1;
    private TextView textViewD;
    private EditText editText1;
    private EditText editText2;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private boolean frame1Advanced;
    private boolean frame2Advanced;
    private boolean loaded1;
    private boolean loaded2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final VideoView videoView = (VideoView) findViewById(R.id.video_view);
        final VideoView videoView2 = (VideoView) findViewById(R.id.video_view_2);

        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView1 = (TextView) findViewById(R.id.textView1);
        textViewD = (TextView) findViewById(R.id.textViewD);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        textViewD.setText("Videos path: " + Environment.getExternalStorageDirectory().getPath() + "/DCIM/");
        bpm = 90f;
        msecAdd = (60*1000)/bpm;


        // editText preset to date and hour
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd_HH");
        Calendar cal = Calendar.getInstance();
        editText1.setText(sdf.format(cal.getTime())+"mm");
        editText2.setText(sdf.format(cal.getTime()) + "mm");

        final Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play.getText() == "Pause") {
                    videoView.pause();
                    videoView2.pause();
                    msec = videoView.getCurrentPosition();
                    msec2 = videoView2.getCurrentPosition();
                    Log.i("tagt", "Video1 length ms: " + videoView.getDuration());
                    Log.i("tagt", "Video1 length ms: " + videoView.getDrawingTime());
                    Log.i("tagt", "Video2 length ms: " + videoView2.getDuration());
                    play.setText("Play");
                    //textView1.setText(getMilliToString(videoView.getCurrentPosition()) + "+" + (int)(videoView.getCurrentPosition() - msec));
                    textView1.setText("Tgt-"+msec+"VPlayer-"+getMilliToString(videoView.getCurrentPosition()));
                    textView2.setText(getMilliToString(videoView2.getCurrentPosition()) + "+" + (int)(videoView2.getCurrentPosition() - msec2));
                } else {
                    if (!checkBox1.isChecked()&&loaded1) videoView.start();
                    if (!checkBox2.isChecked()&&loaded2) videoView2.start();
                    play.setText("Pause");
                }
            }
        });


        final Button step = (Button) findViewById(R.id.step);
        step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox1.isChecked()&&loaded1) {
                    if (frame1Advanced) msec = videoView.getCurrentPosition();
                    msec += msecAdd;
                    videoView.requestFocus();
                    videoView.start();
                    while (videoView.getCurrentPosition() < (msec-20)) {
                        //textView1.setText(getMilliToString(videoView.getCurrentPosition())+ "+" + (int)(videoView.getCurrentPosition() - msec));
                        textView1.setText("Tgt-"+msec+"VPlayer-"+getMilliToString(videoView.getCurrentPosition()));
                    }
                    videoView.pause();
                }

                if (!checkBox2.isChecked()&&loaded2) {
                    if (frame2Advanced) msec2 = videoView2.getCurrentPosition();
                    msec2 += msecAdd;
                    videoView2.requestFocus();
                    videoView2.start();
                    while (videoView2.getCurrentPosition() < (msec2-20)) {
                        textView2.setText(getMilliToString(videoView2.getCurrentPosition())+ "+" + (int)(videoView2.getCurrentPosition() - msec2));

                    }
                    videoView2.pause();
                }
                //textView1.setText(getMilliToString(videoView.getCurrentPosition()) + "+" + (int)(videoView.getCurrentPosition() - msec));
                textView1.setText("Tgt-"+msec+"VPlayer-"+getMilliToString(videoView.getCurrentPosition()));

                textView2.setText(getMilliToString(videoView2.getCurrentPosition()) + "+" + (int)(videoView2.getCurrentPosition() - msec2));
            }
        });

        final Button stepH = (Button) findViewById(R.id.stepH);
        stepH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox1.isChecked()&&loaded1) {
                    msec += msecAdd/2;
                    videoView.requestFocus();
                    videoView.start();
                    while (videoView.getCurrentPosition() < msec) {
                        textView1.setText(getMilliToString(videoView.getCurrentPosition())+ "+" + (int)(videoView.getCurrentPosition() - msec));
                    }
                    videoView.pause();
                }

                if (!checkBox2.isChecked()&&loaded2) {
                    msec2 += msecAdd/2;
                    videoView2.requestFocus();
                    videoView2.start();
                    while (videoView2.getCurrentPosition() < msec2) {
                        textView2.setText(getMilliToString(videoView2.getCurrentPosition())+ "+" + (int)(videoView2.getCurrentPosition() - msec2));
                    }
                    videoView2.pause();
                }
                textView1.setText(getMilliToString(videoView.getCurrentPosition())+ "+" + (int)(videoView.getCurrentPosition() - msec));
                textView2.setText(getMilliToString(videoView2.getCurrentPosition())+ "+" + (int)(videoView2.getCurrentPosition() - msec2));
            }
        });


        final Button frame = (Button) findViewById(R.id.frame);
        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox1.isChecked()&&loaded1) {
                    msec+=66.7334;
                    videoView.requestFocus();
                    videoView.start();
                    while (videoView.getCurrentPosition() < (msec)) {
                        textView1.setText(getMilliToString(videoView.getCurrentPosition())+ "+" + (int)(videoView.getCurrentPosition() - msec));
                    }
                    videoView.pause();
                    frame1Advanced = true;
                }
                if (!checkBox2.isChecked()&&loaded2) {
                    msec2 += 33.3667;
                    videoView2.requestFocus();
                    videoView2.start();
                    while (videoView2.getCurrentPosition() < (msec2)) {
                        textView2.setText(getMilliToString(videoView2.getCurrentPosition())+ "+" + (int)(videoView2.getCurrentPosition() - msec2));
                    }
                    videoView2.pause();
                    frame2Advanced = true;
                }

                textView1.setText(getMilliToString(videoView.getCurrentPosition())+ "+" + (int)(msec-videoView.getCurrentPosition()));
                textView2.setText(getMilliToString(videoView2.getCurrentPosition())+ "+" + (int)(msec2-videoView2.getCurrentPosition()));

            }
        });


        final Button frameB = (Button) findViewById(R.id.frameB);
        frameB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox1.isChecked()&&loaded1) {
                    msec-=66.7334;
                    Log.i("tagt", "Desired ms: " + msec);
                    Log.i("tagt", "Rounded ms: " + 1000*(int)TimeUnit.MILLISECONDS.toSeconds((int) msec));
                    videoView.requestFocus();
                    videoView.seekTo(1000*(int)TimeUnit.MILLISECONDS.toSeconds((int) msec));

                    Log.i("tagt", "Seek ms: " + videoView.getCurrentPosition());

                    videoView.start();
                    while (videoView.getCurrentPosition() < (msec)) {
                        textView1.setText(getMilliToString(videoView.getCurrentPosition())+ "+" + (int)(videoView.getCurrentPosition() - msec));
                    }
                    videoView.pause();
                    frame1Advanced = true;
                    Log.i("tagt", "Play ms: " + videoView.getCurrentPosition());


                }
                if (!checkBox2.isChecked()&&loaded2) {
                    videoView2.requestFocus();
                    videoView2.seekTo(4500);
                    msec2 = videoView2.getCurrentPosition();

                }

                textView1.setText(getMilliToString(videoView.getCurrentPosition())+ "+" + (int)(videoView.getCurrentPosition() - msec));
                textView2.setText(getMilliToString(videoView2.getCurrentPosition())+ "+" + (int)(videoView2.getCurrentPosition() - msec2));
            }
        });



        final Button load = (Button) findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaded1 = true; // error listener will set to false if not loaded
                loaded2 = true; // error listener will set to false if not loaded

                String videoName1 = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + editText1.getText() + ".mp4";
                videoView.setVideoURI(Uri.parse(videoName1));
                videoView.requestFocus();
                videoView.seekTo(1);


                String videoName2 = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + editText2.getText() + ".mp4";
                videoView2.setVideoURI(Uri.parse(videoName2));
                videoView2.requestFocus();
                videoView2.seekTo(1);


                msec = videoView.getCurrentPosition();
                msec2 = videoView2.getCurrentPosition();

            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e("er1", "Error playing video");
                loaded1 = false;
                textView1.setText("V1NotLoaded");
                return true;
            }
        });


        videoView2.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e("er2", "Error playing video");
                loaded2 = false;
                textView2.setText("V2NotLoaded");
                return true;
            }
        });

//        //onSeekCompletionListener declaration
//        videoView.setOnPreparedListener((MediaPlayer.OnPreparedListener) this);
//        MediaPlayer mp;
//        mp.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
//
//            //show current frame after changing the playback position
//            @Override
//            public void onSeekComplete(MediaPlayer mp) {
//                if (mp.isPlaying()) {
//                    playMedia(null);
//                    //playMedia(play);
//                } else {
//                    //playMedia(null);
//                    //playMedia(play);
//                    //playMedia(null);
//                }
//                //mediaTimeElapsed.setText(countTime(vv.getCurrentPosition()));
//            }
//        });

    }
    /**
     * Convert a millisecond duration to a string stopwatch format
     *
     * @param millis A duration to convert to a string form
     * @return A string of the form M:ss.SS
     */
//    public static String getMilliToString(long millis)
    public String getMilliToString(int millis)

    {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        long hundreds = (Math.round(millis/10.0))%100;

        return(minutes+":"+String.format("%02d",seconds)+"."+String.format("%02d", hundreds));
    }

}
