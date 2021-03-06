package com.example.geolabedu.testn2;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.geolabedu.testn2.Adapter.MyRecyclerAdapter;
import com.example.geolabedu.testn2.database.DBHelper;
import com.example.geolabedu.testn2.database.VehiclContracts;
import com.example.geolabedu.testn2.database.VehicleData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;


public class FirstActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener{

    String path;
    long l;
    ImageView viewImage;
    DBHelper dbHelper;
    RecyclerView recyclerView;
    public static SQLiteDatabase sqLiteDatabase;
    android.support.v7.widget.Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        dbHelper=new DBHelper(this);
        sqLiteDatabase=dbHelper.getWritableDatabase();

        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView= (NavigationView) findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);//akontrolebs ghiaa tu ara
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);//itokshi ar vici ras aketebs
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        //statusbar change color
        Window window =getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.statusrose));

//        if (getIntent().hasExtra("list")) {
//            ArrayList data = (ArrayList) getIntent().getExtras().getSerializable("list");
//            MyRecyclerAdapter adapter=new MyRecyclerAdapter(this, data);
//            recyclerView.setAdapter(adapter);
//        }
        /*
        * orderby date
        * */
        if(sqLiteDatabase.isDatabaseIntegrityOk()){
            Cursor c = sqLiteDatabase.query(VehiclContracts.VEHICLE_TABLE_NAME, null, null, null, null, null, null);

            ArrayList list=new ArrayList();
            if(c.moveToFirst()){
                do {
                    String name = c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_PERSON_NAME));
                    String fname = c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_PERSON_FNAME));
                    String image = c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_IMAGE));
                    String mail=c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_PERSON_EMAIL));
                    String nomeri=c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_PERSON_PHONE));
                    String categ=c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_CATEGORY));
                    String modeli=c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_MODEL));
                    String weli=c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_AGE));
                    Integer ID=c.getInt(c.getColumnIndex(VehiclContracts.VEHICLE_ID));
                    String decsk=c.getString(c.getColumnIndex(VehiclContracts.VEHICLE_DESCRIPTION));
                    VehicleData item2 = new VehicleData(ID,name,fname,image,mail,nomeri,categ,modeli,weli,decsk);

                    list.add(item2);
                 } while(c.moveToNext());
        }
            MyRecyclerAdapter adapter=new MyRecyclerAdapter(this, list);
            recyclerView.setAdapter(adapter);

        }else{
            ArrayList list=new ArrayList();
            VehicleData data=new VehicleData(null,null,null,null,null,null,null,null,null);
            list.add(data);
            MyRecyclerAdapter myRecyclerAdapter=new MyRecyclerAdapter(this, list);
            recyclerView.setAdapter(myRecyclerAdapter);
        }


//        for(int i=0;i <datas.size();i++){
//            VehicleData vehicleData=datas.get(i);
//            values.put(VehiclContracts.VEHICLE_ID,vehicleData.getId());
//
//            values.put(VehiclContracts.VEHICLE_MODEL,vehicleData.getModel());
//
//            values.put(VehiclContracts.VEHICLE_PERSON_NAME,vehicleData.getName());
//
//            db.insert(VehiclContracts.VEHICLE_TABLE_NAME,null,values);
//        }


//        listView= (ListView) findViewById(R.id.listView);
//        viewImage=(ImageView)findViewById(R.id.imageView);
//        CustomAdapter adapter=new CustomAdapter(this,list);
//        listView.setAdapter(adapter);
//        adapter=new MyRecyclerAdapter(list);
//        recyclerView.setAdapter(adapter);

        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                selectImage();
                Intent intent=new Intent(FirstActivity.this,ChooseActivity.class);
                startActivity(intent);

            }
        });


    }



    private void selectImage() {

            final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.SECOND,1);
            l=calendar.getTimeInMillis();

            AlertDialog.Builder builder = new AlertDialog.Builder(FirstActivity.this);
            builder.setTitle("Add Photo!");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (options[item].equals("Take Photo"))
                    {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File f = new File(android.os.Environment.getExternalStorageDirectory(), l+".jpg");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                        startActivityForResult(intent, 1);

                    }
                    else if (options[item].equals("Choose from Gallery"))
                    {
                        Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 2);

                    }
                    else if (options[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
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


                    //viewImage.setImageBitmap(bitmap);



//                    url=getImageUri(FirstActivity.this,bitmap);
//                    Test.image.add(url);
//                    fillTest();
//
//                    Intent intent=new Intent(getApplicationContext(),FirstActivity.class);
//
//
//                    String st=getRealPathFromURI(url);
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

               // url=getImageUri(FirstActivity.this,thumbnail);
                //Test.image.add(url);

                viewImage.setImageBitmap(thumbnail);
            }
        }
//        fillTest();
//        CustomAdapter adapter=new CustomAdapter(this,list);
//        listView.setAdapter(adapter);

    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigationview_item_1:
                Toast.makeText(this,"click_1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationview_item_2:
                Toast.makeText(this,"click_2",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
