package com.example.buygo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class UploadFragment extends Fragment {

    private Bitmap selecetedImage;
    private ImageView imageView1, imageView2, imageView3,imageView4;
    private Button saleButton;
    private EditText produktName,produktPrice,produktStiation,produktInformation;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Uri imageData/*,imageData2,imageData3,imageData4*/;
    private FirebaseFirestore firebaseFire;
    private FirebaseAuth auth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_upload,container,false);
            imageView1 = viewGroup.findViewById(R.id.imageView2);
            imageView2 = viewGroup.findViewById(R.id.imageView3);
            imageView3 = viewGroup.findViewById(R.id.imageView4);
            imageView4 = viewGroup.findViewById(R.id.imageView5);
            saleButton = viewGroup.findViewById(R.id.sale_btn);
            produktName = viewGroup.findViewById(R.id.produktNameText);
            produktPrice = viewGroup.findViewById(R.id.produktPriceText);
            produktStiation = viewGroup.findViewById(R.id.produltStiationText);
            produktInformation=viewGroup.findViewById(R.id.produktInformationText);

            firebaseStorage = FirebaseStorage.getInstance();
            storageReference = firebaseStorage.getReference();
            firebaseFire = FirebaseFirestore.getInstance();
            auth = FirebaseAuth.getInstance();


            saleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (imageData != null){

                        UUID uuid = UUID.randomUUID();
                        final String imageName = "images/" + uuid + ".jpg";

                        storageReference.child(imageName ).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                StorageReference  newReference = FirebaseStorage.getInstance().getReference(imageName);
                                newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String dowlandUrl = uri.toString();

                                        FirebaseUser firebaseUser = auth.getCurrentUser();
                                        String userMail = firebaseUser.getEmail();
                                        String name = produktName.getText().toString();
                                        String price = produktPrice.getText().toString();
                                        String stiation = produktStiation.getText().toString();
                                        String information = produktInformation.getText().toString();

                                        HashMap<String,Object> postData = new HashMap<>();
                                        postData.put("usermail",userMail);
                                        postData.put("downloadurl",dowlandUrl);
                                        postData.put("date", FieldValue.serverTimestamp());
                                        postData.put("name",name);
                                        postData.put("price",price);
                                        postData.put("stiaton", stiation);
                                        postData.put("information",information);

                                        firebaseFire.collection("Posts").add(postData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {

                                                Toast.makeText(getContext(),"İlan yüklendi!", Toast.LENGTH_LONG).show();

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Toast.makeText(getContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();

                                            }
                                        });

                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(getContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();

                            }
                        });

                    }

                }
            });


            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    }else{
                        Intent intentToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intentToGalery,1);
                    }

                }
            });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }else{
                    Intent intentToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intentToGalery,2);
                }

            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }else{
                    Intent intentToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intentToGalery,3);
                }

            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }else{
                    Intent intentToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intentToGalery,4);
                }

            }
        });

        return viewGroup;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1){
            if(grantResults.length > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
                Intent intentToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGalery, 1);
            }/*else if (grantResults.length > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
                Intent intentToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGalery, 2);
            }else if (grantResults.length > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
                Intent intentToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGalery, 3);
            }else {
                Intent intentToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGalery, 4);
            }*/

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && data != null){

            imageData = data.getData();

            try {
                    selecetedImage = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(),imageData);
                    switch (requestCode){
                        case 1: imageView1.setImageBitmap(selecetedImage);break;
                        case 2: imageView2.setImageBitmap(selecetedImage);break;
                        case 3: imageView3.setImageBitmap(selecetedImage);break;
                        case 4: imageView4.setImageBitmap(selecetedImage);break;
                }



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
