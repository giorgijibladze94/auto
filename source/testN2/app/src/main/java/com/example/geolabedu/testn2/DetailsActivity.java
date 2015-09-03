package com.example.geolabedu.testn2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geolabedu.testn2.database.VehicleData;

public class DetailsActivity extends AppCompatActivity {

    int ID;
    ImageView imageView;
    TextView textView,textView1,textView2,textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView= (ImageView) findViewById(R.id.imageView3);
        textView= (TextView) findViewById(R.id.saxeli);
        textView1= (TextView) findViewById(R.id.gvari);
        textView2= (TextView) findViewById(R.id.mail);
        textView3= (TextView) findViewById(R.id.nomeri);


        VehicleData data= (VehicleData) getIntent().getExtras().getSerializable("data");
        imageView.setImageURI(Uri.parse(data.getImage()));
        textView.setText(data.getName());
        textView1.setText(data.getFname());
        textView2.setText(data.getMail());
        textView3.setText(data.getNomeri());

        ID=data.getID();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings)
        {
            Toast.makeText(this,"click",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,ChooseActivity.class);
            intent.putExtra("edit",ID);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
