package com.example.sherlock.heltho.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherlock.heltho.R;
import com.example.sherlock.heltho.data.userData;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static android.app.Activity.RESULT_OK;

/**
 * Created by sherlock on 30/5/17.
 */

public class profilePage extends Fragment implements View.OnClickListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    private static final int PICK_IMAGE = 100;
    public String mCurrentPhotoPath;
    ImageView edit_dp;
    CircularImageView user_profile_pic;
    Button address_btn;
    Button designation_btn;
    Button bank_btn;
    Button identity_btn;
    Button vehicle_btn;
    TextView username;
    TextView userEmailID;
    TextView address_tv;
    TextView designation_tv;
    TextView bank_tv;
    TextView identity_tv;
    TextView vehicle_tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_profile, container, false);
        address_btn = (Button) view.findViewById(R.id.address_btn);
        bank_btn = (Button) view.findViewById(R.id.bank_btn);
        designation_btn = (Button) view.findViewById(R.id.work_btn);
        identity_btn = (Button) view.findViewById(R.id.id_btn);
        vehicle_btn = (Button) view.findViewById(R.id.vehicle_btn);
        userEmailID = (TextView) view.findViewById(R.id.user_EMAILID);
        username = (TextView)view.findViewById(R.id.user_profile_name);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        address_btn.setOnClickListener(this);
        bank_btn.setOnClickListener(this);
        designation_btn.setOnClickListener(this);
        identity_btn.setOnClickListener(this);
        vehicle_btn.setOnClickListener(this);


        address_tv = (TextView) view.findViewById(R.id.address_tv);
        designation_tv = (TextView) view.findViewById(R.id.work_tv);
        bank_tv = (TextView) view.findViewById(R.id.bank_tv);
        identity_tv = (TextView) view.findViewById(R.id.id_tv);
        vehicle_tv = (TextView) view.findViewById(R.id.vehicle_tv);

        //setProfileInfo();

        edit_dp = (ImageView) view.findViewById(R.id.edit_dp);
        user_profile_pic = (CircularImageView) view.findViewById(R.id.user_profile_photo);

        if((1==2)&& !userData.profile_pic_url.equals(null) ){
            Picasso.with(getActivity()).setIndicatorsEnabled(true);
            Picasso.with(getActivity()).load(userData.profile_pic_url).
                    placeholder(R.drawable.com_facebook_profile_picture_blank_portrait)
                    .error(R.drawable.userdp).into(user_profile_pic);

        }

        edit_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_photo_selection_dialog();
            }
        });
        return view;
    }

    public void setProfileInfo(){
        username.setText(userData.first_name + " " + userData.last_name);
        userEmailID.setText(userData.email);
        address_tv.setText(userData.address);
        designation_tv.setText("");
        bank_tv.setText("Account number : "+userData.bank_account_no+"\r\n"+
                        "IFSC Code : "+userData.bank_ifsc_cose+"\r\n"+
                        "Branch Code : "+userData.bank_branch_code+"\r\n"+
                        "Branch Name : "+userData.bank_branch_name+"\r\n"+
                        "Account Holder name : "+userData.bank_account_holder);
        identity_tv.setText("PAN : "+userData.pan+"\r\n"+
                            "Aadhar Number : "+userData.aadhar+"\r\n");
        vehicle_tv.setText("Vehicle Model : "+userData.model+"\r\n"+
                            "Vehicle Maker : "+userData.maker+"\r\n"+
                            "Registration Number : "+userData.reg_number+"\r\n"+
                            "Vehicle Number : "+userData.vehicle_number);
    }


    public void create_photo_selection_dialog() {
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.dialog_for_photo_option_selection, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());
        alertDialogBuilder.setView(promptsView);
        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        Button camera_btn = (Button) promptsView.findViewById(R.id.camera);
        Button gallery_btn = (Button) promptsView.findViewById(R.id.gallery);
        Button remove_btn = (Button) promptsView.findViewById(R.id.remove);

        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    dispatchTakePictureIntent();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Camera hardware not available", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            }
        });

        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
                alertDialog.dismiss();
            }
        });


        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_profile_pic.setImageDrawable(getResources().getDrawable(R.drawable.userdp));
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void openGallery() {
        Intent gallery =
                new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }


    public void create_dialog(final String x) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.edit_dialog_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                if (x.equals("add")) {
                                    address_tv.setText(userInput.getText());
                                } else if (x.equals("work")) {
                                    designation_tv.setText(userInput.getText());
                                } else if (x.equals("bank")) {
                                    bank_tv.setText(userInput.getText());
                                } else if (x.equals("id")) {
                                    identity_tv.setText(userInput.getText());
                                } else if (x.equals("vehicle")) {
                                    vehicle_tv.setText(userInput.getText());
                                    ;
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                Log.e("photofile", photoFile.toString());
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.example.sanjeev.adzippy.fileprovider",
                        photoFile);
                mCurrentPhotoPath = photoFile.getAbsolutePath();
                List<ResolveInfo> resInfoList = getActivity().getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    getActivity().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                Log.e("photoURI", photoURI.toString());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = user_profile_pic.getWidth();
        int targetH = user_profile_pic.getHeight();
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        Log.e("hhh", "QWERTY");
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        user_profile_pic.setImageBitmap(bitmap);
        Log.e("hhh", "QWERTY5555");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            galleryAddPic();
            setPic();
        } else if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            user_profile_pic.setImageURI(imageUri);
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
        Log.e("hhh", "QWERTY3333");
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onClick(View v) {
        Log.e("ID", Integer.toString(v.getId()));
        switch (v.getId()) {
            case R.id.address_btn:
                create_dialog("add");
                break;
            case R.id.work_btn:
                create_dialog("work");
                break;
            case R.id.bank_btn:
                create_dialog("bank");
                break;
            case R.id.id_btn:
                create_dialog("id");
                break;
            case R.id.vehicle_btn:
                create_dialog("vehicle");
                break;
        }
    }
}
