package com.tmanswap.configupdater;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {
    // switch \\
    boolean mtn500mb1,mtn500mb2,mtn500mb3,mtn500mb4,mtn500mb5,mtn500mb6,mtn500mb7,mtn500mb8,mtn500mb9,mtn500mb10;

    // Expanding Layouts \\
    private ConstraintLayout networksViewExpanded;
    //
    private ConstraintLayout mtnViewExpanded,cellcViewExpanded,vodacomViewExpanded,telkomViewExpanded;
    //
    private ConstraintLayout instructionsViewExpanded;
    private ConstraintLayout socialMediaViewExpanded;

    // Buttons \\
    private Button networksArrowButton;
    //
    private Button mtnArrowButton,cellcArrowButton,vodacomArrowButton,telkomArrowButton;
    //
    private Button instructionsArrowButton;
    private Button socialMediaArrowButton;
    // CardViews \\
    private CardView networksCardView;
    //
    private CardView mtnCard,cellcCard,vCard,tCard;
    //
    private CardView instructionsCardView;
    private CardView socialMediaCardView;
    //
    TextView token;
    int tokenNumber;
    //
    private RewardedVideoAd mRewardedVideoAd;
    InterstitialAd mInterstitialAd;
    // real - AD_PUB
    // test - AD_PUB
    private static final String AD_UNIT_ID = "AD_PUB";
    private FrameLayout adContainerView;
    private AdView adView;
    //
    StorageReference storageReference;
    StorageReference ref;
    FirebaseAnalytics mFirebaseAnalytics;
    MediaPlayer mediaPlayer;
    String music_url = "https://bit.ly/configMusic";
    com.github.clans.fab.FloatingActionButton fab1,fab2,fab3;
    //

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //firebase
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //ADS
        MobileAds.initialize(this, "AD_PUB");
        // Video Ads
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();

        //Inter
        //real - ca-app-pub-8279863701405798/5219249501
        //test - ca-app-pub-3940256099942544/1033173712
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8279863701405798/5219249501");

        //Banner Ads
        adContainerView = findViewById(R.id.ad_view_container);

        // Since we're loading the banner based on the adContainerView size, we need to wait until this
        // view is laid out before we can get the width.
        adContainerView.post(new Runnable() {
            @Override
            public void run() {
                loadRewardedVideoAd();
            }
        });

        //
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        //
        fab2.setEnabled(false);
        fab1.setEnabled(false);
        //
        fab1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Playing...", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                fab1.setEnabled(false);
                fab2.setEnabled(true);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mediaPlayer.pause();
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Paused.", Snackbar.LENGTH_LONG);
                snackbar.show();
                fab2.setEnabled(false);
                fab1.setEnabled(true);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                fab3.setEnabled(false);
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Loading please wait...", Snackbar.LENGTH_LONG);
                snackbar.show();
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(3);
                try {
                    mediaPlayer.setDataSource(MainActivity.this.music_url);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Loading complete.", Snackbar.LENGTH_LONG);
                            snackbar.show();
                            fab1.setEnabled(true);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    fab3.setEnabled(true);
                }
            }
        });

        //
        networksViewExpanded = findViewById(R.id.networksViewExpanded);
        //
        mtnViewExpanded =  findViewById(R.id.mtnViewExpanded);
        cellcViewExpanded =  findViewById(R.id.cellcViewExpanded);
        vodacomViewExpanded =  findViewById(R.id.vodacomViewExpanded);
        telkomViewExpanded =  findViewById(R.id.telkomViewExpanded);
        //
        instructionsViewExpanded =  findViewById(R.id.instructionsViewExpanded);
        socialMediaViewExpanded =  findViewById(R.id.socialMediaViewExpanded);

        networksArrowButton =  findViewById(R.id.networksArrowButton);
        //
        mtnArrowButton =  findViewById(R.id.mtnArrowButton);
        cellcArrowButton =  findViewById(R.id.cellcArrowButton);
        vodacomArrowButton =  findViewById(R.id.vodacomArrowButton);
        telkomArrowButton =  findViewById(R.id.telkomArrowButton);
        //
        instructionsArrowButton =  findViewById(R.id.instructionsArrowButton);
        socialMediaArrowButton =  findViewById(R.id.socialMediaArrowButton);

        networksCardView =  findViewById(R.id.networksCardView);
        //
        mtnCard =  findViewById(R.id.mtnCard);
        cellcCard =  findViewById(R.id.cellcCard);
        tCard =  findViewById(R.id.tCard);
        vCard =  findViewById(R.id.vCard);
        //
        instructionsCardView =  findViewById(R.id.instructionsCardView);
        socialMediaCardView =  findViewById(R.id.socialMediaCardView);
        //

        token = findViewById(R.id.token);
        token.setText(getString(R.string.tokens) + tokenNumber);


        // Button Clicks
        networksArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (networksViewExpanded.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(networksCardView, new AutoTransition());
                    networksViewExpanded.setVisibility(View.VISIBLE);
                    networksArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(networksCardView, new AutoTransition());
                    networksViewExpanded.setVisibility(View.GONE);
                    networksArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });

        // Config View
        mtnArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mtnViewExpanded.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(mtnCard, new AutoTransition());
                    mtnViewExpanded.setVisibility(View.VISIBLE);
                    mtnArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(mtnCard, new AutoTransition());
                    mtnViewExpanded.setVisibility(View.GONE);
                    mtnArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });

        cellcArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cellcViewExpanded.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cellcCard, new AutoTransition());
                    cellcViewExpanded.setVisibility(View.VISIBLE);
                    cellcArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cellcCard, new AutoTransition());
                    cellcViewExpanded.setVisibility(View.GONE);
                    cellcArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });

        vodacomArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vodacomViewExpanded.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(vCard, new AutoTransition());
                    vodacomViewExpanded.setVisibility(View.VISIBLE);
                    vodacomArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(vCard, new AutoTransition());
                    vodacomViewExpanded.setVisibility(View.GONE);
                    vodacomArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });

        telkomArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (telkomViewExpanded.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(tCard, new AutoTransition());
                    telkomViewExpanded.setVisibility(View.VISIBLE);
                    telkomArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(tCard, new AutoTransition());
                    telkomViewExpanded.setVisibility(View.GONE);
                    telkomArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });

        // Other Views Button Clicks
        instructionsArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (instructionsViewExpanded.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(instructionsCardView, new AutoTransition());
                    instructionsViewExpanded.setVisibility(View.VISIBLE);
                    instructionsArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(instructionsCardView, new AutoTransition());
                    instructionsViewExpanded.setVisibility(View.GONE);
                    instructionsArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });

        socialMediaArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (socialMediaViewExpanded.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(socialMediaCardView, new AutoTransition());
                    socialMediaViewExpanded.setVisibility(View.VISIBLE);
                    socialMediaArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(socialMediaCardView, new AutoTransition());
                    socialMediaViewExpanded.setVisibility(View.GONE);
                    socialMediaArrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });


        //Switches
        mtn500mb1 = true;
        mtn500mb2 = true;
        mtn500mb3 = true;
        mtn500mb4 = true;
        mtn500mb5 = true;

        mtn500mb6 = true;
        mtn500mb7 = true;
        mtn500mb8 = true;
        mtn500mb9 = true;
        mtn500mb10 = true;


        //Buttons
        //
        Button videoButton = findViewById(R.id.videoButton);
        Button readButton = findViewById(R.id.readButton);

        //
        Button telegramButton = findViewById(R.id.telegramButton);
        Button whatsappButton = findViewById(R.id.whatsappButton);
        Button facebookButton = findViewById(R.id.facebookButton);

        Button dwnl_Mtn5_1 =  findViewById(R.id.mtn1_500_Button);
        Button dwnl_Mtn5_2 =  findViewById(R.id.mtn2_500_Button);
        Button dwnl_Mtn5_3 =  findViewById(R.id.mtn3_500_Button);
        Button dwnl_Mtn5_4 =  findViewById(R.id.mtn4_500_Button);
        Button dwnl_Mtn5_5 =  findViewById(R.id.mtn5_500_Button);

        Button dwnl_Mtn5_6 =  findViewById(R.id.mtn6_500_Button);
        Button dwnl_Mtn5_7 =  findViewById(R.id.mtn7_500_Button);
        Button dwnl_Mtn5_8 =  findViewById(R.id.mtn8_500_Button);
        Button dwnl_Mtn5_9 =  findViewById(R.id.mtn9_500_Button);
        Button dwnl_Mtn5_10 =  findViewById(R.id.mtn10_500_Button);


        //Download Buttons
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/hTXfBDrthO0")));

            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Instructions.class));

            }
        });

        telegramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telegram = new Intent(Intent.ACTION_VIEW,Uri.parse("http://bit.ly/freeNetCom"));
                startActivity(telegram);

            }
        });

        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whatsApp = new Intent(Intent.ACTION_VIEW,Uri.parse("http://bit.ly/whatsAppFreeNet"));
                startActivity(whatsApp);

            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebook = new Intent(Intent.ACTION_VIEW,Uri.parse("http://bit.ly/faceBookFreeNet"));
                startActivity(facebook);

            }
        });

        dwnl_Mtn5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn500mb1 = false;
                confirm();

            }
        });

        dwnl_Mtn5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn500mb2 = false;
                confirm();

            }
        });


        dwnl_Mtn5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn500mb3 = false;
                confirm();

            }
        });

        dwnl_Mtn5_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn500mb4 = false;
                confirm();

            }
        });

        dwnl_Mtn5_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn500mb5 = false;
                confirm();

            }
        });

        dwnl_Mtn5_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn500mb6 = false;
                confirm();

            }
        });

        dwnl_Mtn5_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn500mb7 = false;
                confirm();

            }
        });

        dwnl_Mtn5_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn500mb8 = false;
                confirm();

            }
        });

        dwnl_Mtn5_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn500mb9 = false;
                confirm();

            }
        });

        dwnl_Mtn5_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn500mb10 = false;
                confirm();

            }
        });
        loadBanner();

    }

    private void generateText() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(this));
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Out of Tokens");
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("In order to Download a Config you need 2 Tokens. Do you want to Generate a Token?");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                generateToken();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void generateToken() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(this));
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Confirm");
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("In order to Generate a Token you have to Watch a Video Ad first");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                } else {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Wait until you see READY or check internet connection", Snackbar.LENGTH_LONG);
                            snackbar.show();
                }



            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void confirm() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(this));
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Confirm");
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Are you sure you want to Download Config file?");
        alertDialogBuilder.setCancelable(true);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                if (tokenNumber > 1) {
                    tokenNumber = (tokenNumber - 2);
                    token.setText(getString(R.string.tokens) + tokenNumber);
                    downloadMethod();
                } else {
                    generateText();
                }

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showError() {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Failed to Download please check internet connection", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void downloadMethod() {
        if (!mtn500mb1){
            download5_1();
            reset();
        }
        if (!mtn500mb2){
            download5_2();
            reset();
        }
        if (!mtn500mb3){
            download5_3();
            reset();
        }
        if (!mtn500mb4){
            download5_4();
            reset();
        }
        if (!mtn500mb5){
            download5_5();
            reset();
        }
        if (!mtn500mb6){
            download5_6();
            reset();
        }
        if (!mtn500mb7){
            download5_7();
            reset();
        }
        if (!mtn500mb8){
            download5_8();
            reset();
        }
        if (!mtn500mb9){
            download5_9();
            reset();
        }
        if (!mtn500mb10){
            download5_10();
            reset();
        }

    }

    public void reset() {

        mtn500mb1 = true;
        mtn500mb2 = true;
        mtn500mb3 = true;
        mtn500mb4 = true;
        mtn500mb5 = true;
        mtn500mb6 = true;
        mtn500mb7 = true;
        mtn500mb8 = true;
        mtn500mb9 = true;
        mtn500mb10 = true;

    }

    public void ready(){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "READY!!!", Snackbar.LENGTH_LONG);
        snackbar.show();
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    public void failedLoad() {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Failed to Load please check Internet Connection", Snackbar.LENGTH_LONG);
        snackbar.show();
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }


    // Downloading \\
    //Download Configs
    public void download5_1() {
        mtn500mb1 = true;
        storageReference= FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("MTN500mb_1.ehil");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()    {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(MainActivity.this, "MTN500mb_1", ".ehil", Environment.DIRECTORY_DOWNLOADS,url);
                showAd();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError();
            }

        });

    }

    public void download5_2() {
        mtn500mb2 = true;
        storageReference= FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("MTN500mb_2.ehil");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()    {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(MainActivity.this, "MTN500mb_2", ".ehil", Environment.DIRECTORY_DOWNLOADS,url);
                showAd();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError();
            }

        });

    }

    public void download5_3() {
        mtn500mb3 = true;
        storageReference= FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("MTN500mb_3.ehil");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()    {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(MainActivity.this, "MTN500mb_3", ".ehil", Environment.DIRECTORY_DOWNLOADS,url);
                showAd();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError();

            }

        });

    }

    public void download5_4() {
        mtn500mb4 = true;
        storageReference= FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("MTN500mb_4.ehil");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()    {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(MainActivity.this, "MTN500mb_4", ".ehil", Environment.DIRECTORY_DOWNLOADS,url);
                showAd();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError();
            }

        });

    }

    public void download5_5() {
        mtn500mb5 = true;
        storageReference= FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("MTN500mb_5.ehil");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()    {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(MainActivity.this, "MTN500mb_5", ".ehil", Environment.DIRECTORY_DOWNLOADS,url);
                showAd();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError();
            }

        });

    }

    public void download5_6() {
        mtn500mb6 = true;
        storageReference= FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("MTN500mb_6.ehil");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()    {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(MainActivity.this, "MTN600mb_6", ".ehil", Environment.DIRECTORY_DOWNLOADS,url);
                showAd();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError();
            }

        });

    }

    public void download5_7() {
        mtn500mb7 = true;
        storageReference= FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("MTN500mb_7.ehil");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()    {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(MainActivity.this, "MTN500mb_7", ".ehil", Environment.DIRECTORY_DOWNLOADS,url);
                showAd();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError();
            }

        });

    }

    public void download5_8() {
        mtn500mb8 = true;
        storageReference= FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("MTN500mb_8.ehil");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()    {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(MainActivity.this, "MTN500mb_8", ".ehil", Environment.DIRECTORY_DOWNLOADS,url);
                showAd();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError();
            }

        });

    }

    public void download5_9() {
        mtn500mb9 = true;
        storageReference= FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("MTN500mb_9.ehil");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()    {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(MainActivity.this, "MTN500mb_9", ".ehil", Environment.DIRECTORY_DOWNLOADS,url);
                showAd();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError();
            }

        });

    }

    public void download5_10() {
        mtn500mb10 = true;
        storageReference= FirebaseStorage.getInstance().getReference();
        ref=storageReference.child("MTN500mb_10.ehil");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()    {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(MainActivity.this, "MTN500mb_10", ".ehil", Environment.DIRECTORY_DOWNLOADS,url);
                showAd();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError();

            }

        });

    }

    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);


        Objects.requireNonNull(downloadmanager).enqueue(request);
    }


    // ADS \\
    private void showAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "DOWNLOADING...", Snackbar.LENGTH_LONG);
            snackbar.show();

        }
    }

    // Banner
    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    private void loadBanner() {
        adView = new AdView(this);
        adView.setAdUnitId(AD_UNIT_ID);
        adContainerView.removeAllViews();
        adContainerView.addView(adView);

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);

        AdRequest adRequest =
                new AdRequest.Builder().build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        // Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        float adWidthPixels = adContainerView.getWidth();

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }

        int adWidth = (int) (adWidthPixels / density);

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

    // Video Ad
    private void loadRewardedVideoAd() {
        // real - ca-app-pub-8279863701405798/9589841951
        // test - ca-app-pub-3940256099942544/5224354917
        mRewardedVideoAd.loadAd("ca-app-pub-8279863701405798/9589841951",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        ready();

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {


    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onRewarded(RewardItem rewardItem) {
        if (tokenNumber < 10) {
            tokenNumber = (tokenNumber + 1);
            token.setText(getString(R.string.tokens) + tokenNumber);
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Token has been Generated", Snackbar.LENGTH_LONG);
            snackbar.show();

        } else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "You cannot Generate more then 10 Tokens", Snackbar.LENGTH_LONG);
            snackbar.show();

        }

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        failedLoad();

    }

    @Override
    public void onRewardedVideoCompleted() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();

        }

        loadRewardedVideoAd();
    }

    // Support menu \\
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_generate) {

            if (mRewardedVideoAd.isLoaded()) {
                generateToken();
            } else {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Wait until you see READY or check internet connection", Snackbar.LENGTH_LONG);
                snackbar.show();

            }


            return true;
        }

        if (id == R.id.action_retry) {
            if (mRewardedVideoAd.isLoaded()) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Already loaded...", Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Loading...", Snackbar.LENGTH_LONG);
                snackbar.show();
                loadRewardedVideoAd();

            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
