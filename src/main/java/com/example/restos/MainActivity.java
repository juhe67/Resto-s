package com.example.restos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private EditText editName,editJma,editJmi,editTunai,
            editSubtot,editPajak,editTotal,editKembalian,editKet;
    private Button btnOrder,btnBayar,btnReset,btnCetak,btnChicken,btnBurger,btnFries,btnIce,btnOreo,btnFanta;
    private RadioGroup groupMakan,groupMinum;
    private String makanan,minuman,nama,jma,jmi,tunai;
    private double hargama,jumlahma,hargami,jumlahmi,subtot,pajak,total,bayar,kembalian;



    void initUI(){

        editName = findViewById(R.id.edit_name);
        editJma = findViewById(R.id.edit_jma);
        editJmi = findViewById(R.id.edit_jmi);
        editSubtot = findViewById(R.id.edit_subtot);
        editPajak = findViewById(R.id.edit_pajak);
        editTotal = findViewById(R.id.edit_total);
        editKet = findViewById(R.id.edit_ket);
        editKembalian = findViewById(R.id.edit_kembalian);
        editTunai = findViewById(R.id.edit_tunai);
        groupMakan = findViewById(R.id.group_makanan);
        groupMinum = findViewById(R.id.group_minuman);
        btnOrder = findViewById(R.id.btn_order);
        btnBayar = findViewById(R.id.btn_bayar);
        btnReset = findViewById(R.id.btn_reset);
        btnCetak = findViewById(R.id.btn_cetak);
        btnChicken = findViewById(R.id.btn_chicken);
        btnBurger = findViewById(R.id.btn_burger);
        btnFries = findViewById(R.id.btn_fries);
        btnFanta = findViewById(R.id.btn_fanta);
        btnIce = findViewById(R.id.btn_ice);
        btnOreo= findViewById(R.id.btn_oreo);

        editSubtot.setEnabled(false);
        editPajak.setEnabled(false);
        editTotal.setEnabled(false);
        editKet.setEnabled(false);
        editKembalian.setEnabled(false);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        btnOrder.setOnClickListener(v -> {

            nama = editName.getText().toString();
            jma = editJma.getText().toString();
            jmi = editJmi.getText().toString();

            int selectedMakan = groupMakan.getCheckedRadioButtonId();
            if (selectedMakan == btnChicken.getId()) {
                makanan = "Honey Garlic Chicken Rice";
                hargama = 35000;
            } else if (selectedMakan == btnBurger.getId()) {
                makanan = "Beef Burger";
                hargama = 30000;
            } else if (selectedMakan == btnFries.getId()){
                makanan = "Regular Fries";
                hargama = 25000;
            }else {
                Toast.makeText(MainActivity.this,"Silahkan Pilih Makanan",Toast.LENGTH_LONG).show();
            }

            int selectedMinum = groupMinum.getCheckedRadioButtonId();
            if (selectedMinum == btnIce.getId()) {
                minuman = "Ice Cream Cone";
                hargami = 10000;
            } else if (selectedMinum == btnOreo.getId()) {
                minuman = "Flurry Oreo";
                hargami = 18000;
            } else if (selectedMinum == btnFanta.getId()){
                minuman = "Fanta Float";
                hargami = 15000;
            } else {
                Toast.makeText(MainActivity.this,"Silahkan Pilih Minuman",Toast.LENGTH_LONG).show();
            }


            jumlahma = Double.parseDouble(jma);
            jumlahmi = Double.parseDouble(jmi);

            subtot = ( (hargama * jumlahma) + (hargami * jumlahmi) );
            pajak = (subtot * 0.01);
            total = (subtot+pajak);

            editSubtot.setText(formatRupiah(subtot));
            editPajak.setText(formatRupiah(pajak));
            editTotal.setText(formatRupiah(total));

        });

        btnBayar.setOnClickListener(v -> {

            tunai = editTunai.getText().toString();
            bayar = Double.parseDouble(tunai);
            kembalian = (bayar - total);

            if (bayar == total) {
                editKet.setText("Uang Anda Pas" );
                editKembalian.setText(" 0 ");
            }
            else if (bayar > total){
                editKet.setText("Tunggu Kembalian");
                editKembalian.setText(formatRupiah(kembalian));
            } else {
                Toast.makeText(MainActivity.this,"Uang Anda Kurang"+formatRupiah(kembalian),Toast.LENGTH_LONG).show();

            }

        });

        btnReset.setOnClickListener(v -> {

            editName.setText("");
            groupMakan.clearCheck();
            editJma.setText("");
            groupMinum.clearCheck();
            editJmi.setText("");
            editSubtot.setText("");
            editPajak.setText("");
            editTotal.setText("");
            editTunai.setText("");
            editKet.setText("");
            editKembalian.setText("");

            Toast.makeText(MainActivity.this,"Data Sudah Direset",Toast.LENGTH_LONG).show();
        });

        btnCetak.setOnClickListener(v -> {
            String subtot = editSubtot.getText().toString();
            String pajak = editPajak.getText().toString();
            String total = editTotal.getText().toString();
            String kembali = editKembalian.getText().toString();

            String tunai = editTunai.getText().toString();
            

            if( !nama.equals("")&&
                    !jma.equals("") && !jmi.equals("")&& !subtot.equals("")&&
                    !pajak.equals("") && !kembali.equals("")
               
            ){

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);

                intent.putExtra("nama",nama);
                intent.putExtra("makanan",makanan);
                intent.putExtra("jma", jma);
                intent.putExtra("hargama",formatRupiah(hargama));
                intent.putExtra("minuman",minuman);
                intent.putExtra("jmi",jmi);
                intent.putExtra("hargami",formatRupiah(hargami));
                intent.putExtra("subtot",subtot);
                intent.putExtra("pajak",pajak);
                intent.putExtra("total",total);
                intent.putExtra("tunai",tunai);
                intent.putExtra("kembali",kembali);

                startActivity(intent);

            } else {
                Toast.makeText(MainActivity.this,"Data tidak boleh Kosong",Toast.LENGTH_LONG).show();
            }
        });
    }
    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

}