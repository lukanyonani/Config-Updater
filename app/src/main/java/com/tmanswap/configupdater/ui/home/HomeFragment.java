package com.tmanswap.configupdater.ui.home;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.tmanswap.configupdater.R;

import java.util.Objects;

public class HomeFragment extends Fragment {
    //switch
    boolean mtn200mb1;
    boolean mtn200mb2;
    boolean mtn200mb3;
    boolean mtn200mb4;
    boolean mtn200mb5;

    boolean mtn100mb1;
    boolean mtn100mb2;
    boolean mtn100mb3;
    boolean mtn100mb4;
    boolean mtn100mb5;

    // Expanding Layouts \\
    private ConstraintLayout networksViewExpanded;
    //
    private ConstraintLayout mtnViewExpanded;
    private ConstraintLayout cellcViewExpanded;
    private ConstraintLayout vodacomViewExpanded;
    private ConstraintLayout telkomViewExpanded;
    //
    private ConstraintLayout instructionsViewExpanded;
    private ConstraintLayout socialMediaViewExpanded;

    // Buttons \\
    private Button networksArrowButton;
    //
    private Button mtnArrowButton;
    private Button cellcArrowButton;
    private Button vodacomArrowButton;
    private Button telkomArrowButton;
    //
    private Button instructionsArrowButton;
    private Button socialMediaArrowButton;

    // CardViews \\
    private CardView networksCardView;
    //
    private CardView mtnCard;
    private CardView cellcCard;
    private CardView vCard;
    private CardView tCard;
    //
    private CardView instructionsCardView;
    private CardView socialMediaCardView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        networksViewExpanded = root.findViewById(R.id.networksViewExpanded);
        //
        mtnViewExpanded = root.findViewById(R.id.mtnViewExpanded);
        cellcViewExpanded = root.findViewById(R.id.cellcViewExpanded);
        vodacomViewExpanded = root.findViewById(R.id.vodacomViewExpanded);
        telkomViewExpanded = root.findViewById(R.id.telkomViewExpanded);
        //
        instructionsViewExpanded = root.findViewById(R.id.instructionsViewExpanded);
        socialMediaViewExpanded = root.findViewById(R.id.socialMediaViewExpanded);

        networksArrowButton = root.findViewById(R.id.networksArrowButton);
        //
        mtnArrowButton = root.findViewById(R.id.mtnArrowButton);
        cellcArrowButton = root.findViewById(R.id.cellcArrowButton);
        vodacomArrowButton = root.findViewById(R.id.vodacomArrowButton);
        telkomArrowButton = root.findViewById(R.id.telkomArrowButton);
        //
        instructionsArrowButton = root.findViewById(R.id.instructionsArrowButton);
        socialMediaArrowButton = root.findViewById(R.id.socialMediaArrowButton);

        networksCardView = root.findViewById(R.id.networksCardView);
        //
        mtnCard = root.findViewById(R.id.mtnCard);
        cellcCard = root.findViewById(R.id.cellcCard);
        tCard = root.findViewById(R.id.tCard);
        vCard = root.findViewById(R.id.vCard);
        //
        instructionsCardView = root.findViewById(R.id.instructionsCardView);
        socialMediaCardView = root.findViewById(R.id.socialMediaCardView);

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
        mtn200mb1 = true;
        mtn200mb2 = true;
        mtn200mb3 = true;
        mtn200mb4 = true;
        mtn200mb5 = true;

        mtn100mb1 = true;
        mtn100mb2 = true;
        mtn100mb3 = true;
        mtn100mb4 = true;
        mtn100mb5 = true;


        //Buttons
        Button dwnl_Mtn2_1 = root.findViewById(R.id.mtn1_200_Button);
        Button dwnl_Mtn2_2 = root.findViewById(R.id.mtn2_200_Button);
        Button dwnl_Mtn2_3 = root.findViewById(R.id.mtn3_200_Button);
        Button dwnl_Mtn2_4 = root.findViewById(R.id.mtn4_200_Button);
        Button dwnl_Mtn2_5 = root.findViewById(R.id.mtn5_200_Button);

        Button dwnl_Mtn1_1 = root.findViewById(R.id.mtn1_100_Button);
        Button dwnl_Mtn1_2 = root.findViewById(R.id.mtn2_100_Button);
        Button dwnl_Mtn1_3 = root.findViewById(R.id.mtn3_100_Button);
        Button dwnl_Mtn1_4 = root.findViewById(R.id.mtn4_100_Button);
        Button dwnl_Mtn1_5 = root.findViewById(R.id.mtn5_100_Button);


        //Download Buttons
        dwnl_Mtn2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn200mb1 = false;

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                // Setting Alert Dialog Title
                alertDialogBuilder.setTitle("Confirm");
                // Setting Alert Dialog Message
                alertDialogBuilder.setMessage("In order to Download you have to Watch a Video Ad first");
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                       // if (mRewardedVideoAd.isLoaded()) {
                       //     mRewardedVideoAd.show();
                       // }
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        dwnl_Mtn2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn200mb2 = false;
                confirm();

            }
        });


        dwnl_Mtn2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn200mb3 = false;
                confirm();

            }
        });

        dwnl_Mtn2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn200mb4 = false;
                confirm();

            }
        });

        dwnl_Mtn2_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn200mb5 = false;
                confirm();

            }
        });

        dwnl_Mtn1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn100mb1 = false;
                confirm();

            }
        });

        dwnl_Mtn1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn100mb2 = false;
                confirm();

            }
        });

        dwnl_Mtn1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn100mb3 = false;
                confirm();

            }
        });

        dwnl_Mtn1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn100mb4 = false;
                confirm();

            }
        });

        dwnl_Mtn1_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtn100mb5 = false;
                confirm();

            }
        });

        return root;
    }

    private void confirm() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Confirm");
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("In order to Download you have to Watch a Video Ad first");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //if (mRewardedVideoAd.isLoaded()) {
                //    mRewardedVideoAd.show();
                //}
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    //Final
    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);


        Objects.requireNonNull(downloadmanager).enqueue(request);
    }
















}