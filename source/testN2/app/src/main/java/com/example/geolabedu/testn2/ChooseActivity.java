package com.example.geolabedu.testn2;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.geolabedu.testn2.database.VehiclContracts;
import com.example.geolabedu.testn2.database.VehicleData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ChooseActivity extends ActionBarActivity {

    String path;
    long l;
    ImageView imageView;
    EditText editText,editText1,editText2,editText3;
    String st;
    Button bt;
    public static CardView cardView;
    Uri url;
    Spinner spinner,spinner1;
    ArrayAdapter<CharSequence> spinnerAdapter;
    String categ,modeli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);


        spinner= (Spinner) findViewById(R.id.modeli);
        spinner1= (Spinner) findViewById(R.id.weli);
        cardView= (CardView) findViewById(R.id.cardlist_item);
        editText1= (EditText) findViewById(R.id.gvari);
        editText2= (EditText) findViewById(R.id.mail);
        editText= (EditText) findViewById(R.id.saxeli);
        editText3= (EditText) findViewById(R.id.nomeri);
        bt= (Button) findViewById(R.id.button);
        imageView= (ImageView) findViewById(R.id.imageView);


        spinnerfill();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                String string=st;
                String name= String.valueOf(editText.getText());
                String fname=String.valueOf(editText1.getText());
                String mail=String.valueOf(editText2.getText());
                String nomeri=String.valueOf(editText3.getText());
                modeli=spinner1.getSelectedItem().toString();
                VehicleData vehicleData=new VehicleData(name,fname,string,mail,nomeri,categ,modeli);
                List list=new ArrayList();
                list.add(vehicleData);

                ContentValues values=new ContentValues();
                VehicleData data= (VehicleData) list.get(0);
                values.put(VehiclContracts.VEHICLE_PERSON_NAME, String.valueOf(data.getName()));
                values.put(VehiclContracts.VEHICLE_PERSON_FNAME, String.valueOf(data.getFname()));
                values.put(VehiclContracts.VEHICLE_IMAGE, data.getImage());
                values.put(VehiclContracts.VEHICLE_PERSON_EMAIL,data.getMail());
                values.put(VehiclContracts.VEHICLE_PERSON_PHONE,data.getNomeri());
                values.put(VehiclContracts.VEHICLE_CATEGORY,data.getCateg());
                values.put(VehiclContracts.VEHICLE_MODEL,data.getModeli());
                FirstActivity.sqLiteDatabase.insert(VehiclContracts.VEHICLE_TABLE_NAME, null, values);

//                Cursor c = FirstActivity.sqLiteDatabase.query(VehiclContracts.VEHICLE_TABLE_NAME, null, null, null, null, null, null);
//
//                if(c.moveToFirst()){
//                    do {
//                        String name1 = c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_PERSON_NAME));
//                        String fname1 = c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_PERSON_FNAME));
//                        String image1 = c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_IMAGE));
//                        String mail1=c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_PERSON_EMAIL));
//                        String nomeri1=c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_TABLE_NAME));
//                        VehicleData item2 = new VehicleData(name1,fname1,image1,mail1,nomeri1);
//                        list2=new ArrayList();
//                        list2.add(item2);
//                    } while(c.moveToNext());
//                }
//                selectBD();
                Intent intent=new Intent(ChooseActivity.this,FirstActivity.class);
//                intent.putExtra("list", (Serializable) list2);
                startActivity(intent);
            }
        });
    }

    private void spinnerfill() {
        spinnerAdapter=ArrayAdapter.createFromResource(ChooseActivity.this, R.array.spinner_array_categ, android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //aq unda davwero rom meore spinneri iyos pirvelze damokidebuli
                int count=parent.getCount();
                categ= (String) spinner.getItemAtPosition(position);
                switch (position){
                    case 0:
                        ArrayAdapter adapter=ArrayAdapter.createFromResource(ChooseActivity.this,R.array.spinner_array_model_null,android.R.layout.simple_spinner_item);
                        spinner1.setAdapter(adapter);
                        break;
                    case 1:
                        ArrayAdapter adapter_bmw=ArrayAdapter.createFromResource(ChooseActivity.this,R.array.spinner_array_model_bmw,android.R.layout.simple_spinner_item);
                        spinner1.setAdapter(adapter_bmw);
                        break;
                    case 2:
                        ArrayAdapter adapter_merc=ArrayAdapter.createFromResource(ChooseActivity.this,R.array.spinner_array_model_merc,android.R.layout.simple_spinner_item);
                        spinner1.setAdapter(adapter_merc);
                        break;
                    //dasamatebelia case-ebi :)))))))))
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,1);
        l=calendar.getTimeInMillis();

        AlertDialog.Builder builder = new AlertDialog.Builder(ChooseActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), l + ".jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals(l+".jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);


                    imageView.setImageBitmap(bitmap);



                    url=getImageUri(ChooseActivity.this,bitmap);
//                    Test.image.add(url);
//                    fillTest();
//
//                    Intent intent=new Intent(getApplicationContext(),FirstActivity.class);
//
//
                    st=getRealPathFromURI(url);


//                    FirstActivity firstActivity = null;
//                    firstActivity.set(url);

                    path= android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath + "");

//                url=getImageUri(FirstActivity.this,thumbnail);
//                Test.image.add(url);

                imageView.setImageBitmap(thumbnail);
            }
        }
//        fillTest();
//        CustomAdapter adapter=new CustomAdapter(this,list);
//        listView.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose, menu);
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
    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
