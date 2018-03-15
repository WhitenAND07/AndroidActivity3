package com.example.jordiblanco.androidactivity3;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Intent in;
    private static final int RESULT_LOAD_IMAGE =0, RESULT_LOAD_CONTACT=1;
    private final String lat = "41.6082";
    private final String lon = "0.6231";
    private final String url = "http://www.eps.udl.cat/";
    private final String address = "Carrer de Jaume II, 69, Lleida";
    private final String texttoSearch = "escuela politecnica superior";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button10 = findViewById(R.id.button10);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
    }
    public void onClick (View v){
        switch (v.getId()){
            case R.id.button1:
                Toast.makeText(this, getString(R.string.toastLocC), Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+ lat + ',' + lon+"(EPS)"));
                startActivity(in);
                break;
            case R.id.button2:
                Toast.makeText(this, getString(R.string.toastLocAd), Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
                startActivity(in);
                break;
            case R.id.button3:
                Toast.makeText(this, "Accediendo a la web", Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(in);
                break;
            case R.id.button4:
                Toast.makeText(this, "Buscando en Google", Toast.LENGTH_LONG).show();
                //in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q=" + texttoSearch));
                in = new Intent(Intent.ACTION_WEB_SEARCH);
                in.putExtra(SearchManager.QUERY, texttoSearch);
                startActivity(in);
                break;
            case R.id.button5:
                Toast.makeText(this, getString(R.string.toastCall), Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ getString(R.string.phoneContact)));
                startActivity(in);
                break;
            case R.id.button6:
                Toast.makeText(this, getString(R.string.toastDial), Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ getString(R.string.phoneContact)));
                startActivity(in);
                break;
            case R.id.button7:
                Toast.makeText(this, getString(R.string.toastSMS), Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + getString(R.string.phoneContact)));
                in.putExtra("sms_body","We are learning Android!");
                startActivity(in);
                break;
            case R.id.button8:
                Toast.makeText(this, getString(R.string.toastEmail), Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + getString(R.string.emailContact)));
                in.putExtra(Intent.EXTRA_SUBJECT, "demo");
                in.putExtra(Intent.EXTRA_TEXT, "We are learning Android!");
                startActivity(in);
                break;
            case R.id.button9:
                Toast.makeText(this, getString(R.string.toastContacts), Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_PICK);
                in.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(in, RESULT_LOAD_CONTACT);
                break;
            case R.id.button10:
                Toast.makeText(this, getString(R.string.toastGallery), Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(in, RESULT_LOAD_IMAGE);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri path = data.getData();
            ImageView image = findViewById(R.id.image);
            image.setImageURI(path);
        }
        if (requestCode == RESULT_LOAD_CONTACT && resultCode == RESULT_OK && null != data){
            TextView text = (TextView) findViewById(R.id.textContact);
            Uri uri = data.getData();
            String[] proj = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
            Cursor cursor = getContentResolver().query(uri,proj,null,null, null);
            if(cursor != null && cursor.moveToFirst()){
                int numIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                text.setText(cursor.getString(numIndex));
            }
        }
    }
}
