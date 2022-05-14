package com.example.restos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class HomeActivity extends AppCompatActivity {
    private TextView txtName;
    private TextView txtMakanan;
    private TextView txtJma;
    private TextView txtHma;
    private TextView txtMinuman;
    private TextView txtJmi;
    private TextView txtHmi;
    private TextView txtSubtot;
    private TextView txtPajak;
    private TextView txtTotal;
    private TextView txtTunai;
    private TextView txtKembalian;

    void initUI() {

        txtName = findViewById(R.id.txt_name);
        EditText editDate = findViewById(R.id.edit_date);
        txtMakanan = findViewById(R.id.txt_makanan);
        txtJma = findViewById(R.id.txt_jma);
        txtHma = findViewById(R.id.txt_hma);
        txtMinuman = findViewById(R.id.txt_minuman);
        txtJmi = findViewById(R.id.txt_jmi);
        txtHmi = findViewById(R.id.txt_hmi);
        txtSubtot = findViewById(R.id.txt_subtot);
        txtPajak = findViewById(R.id.txt_pajak);
        txtTotal = findViewById(R.id.txt_total);
        txtTunai = findViewById(R.id.txt_tunai);
        txtKembalian = findViewById(R.id.txt_kembali);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US);
        String currentDate = dateFormat.format(new Date());
        editDate.setText(currentDate);
        editDate.setEnabled(false);

    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initUI();


        if(getIntent().getStringExtra("nama") != null){
            txtName.setText("Nama Customer : "+getIntent().getStringExtra("nama"));
        }
        if(getIntent().getStringExtra("makanan") != null){
            txtMakanan.setText(""+getIntent().getStringExtra("makanan"));
        }
        if(getIntent().getStringExtra("hargama") != null){
            txtHma.setText(""+getIntent().getStringExtra("hargama"));
        }
        if(getIntent().getStringExtra("jma") != null){
            txtJma.setText(""+getIntent().getStringExtra("jma"));
        }
        if(getIntent().getStringExtra("minuman") != null){
            txtMinuman.setText(""+getIntent().getStringExtra("minuman"));
        }
        if(getIntent().getStringExtra("hargami") != null){
            txtHmi.setText(""+getIntent().getStringExtra("hargami"));
        }
        if(getIntent().getStringExtra("jmi") != null){
            txtJmi.setText(""+getIntent().getStringExtra("jmi"));
        }
        if(getIntent().getStringExtra("subtot") != null){
            txtSubtot.setText("SUBTOTAL : "+getIntent().getStringExtra("subtot"));
        }
        if(getIntent().getStringExtra("pajak") != null){
            txtPajak.setText("PAJAK : "+getIntent().getStringExtra("pajak"));
        }
        if(getIntent().getStringExtra("total") != null){
            txtTotal.setText("TOTAL BAYAR :"+getIntent().getStringExtra("total"));
        }
        if(getIntent().getStringExtra("tunai") != null){
            txtTunai.setText("TUNAI :"+getIntent().getStringExtra("tunai"));
        }
        if(getIntent().getStringExtra("kembali") != null){
            txtKembalian.setText("KEMBALIAN :"+getIntent().getStringExtra("kembali"));
        }


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}