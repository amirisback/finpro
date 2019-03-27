package org.d3ifcool.dosen.activities.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.dosen.R;

public class DosenBimbinganInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_bimbingan_info);

        CardView cardView = findViewById(R.id.frg_mhs_pa_cardview_bimbingan);
        CardView cardViewMonev = findViewById(R.id.frg_mhs_pa_cardview_monev);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DosenBimbinganInfoActivity.this, DosenBimbinganDetailActivity.class);
                startActivity(intent);
            }
        });

        cardViewMonev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DosenBimbinganInfoActivity.this, DosenMahasiswaMonevDetailActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();

        if (i == android.R.id.home){
            finish();
        }else if (i == R.id.toolbar_menu_ubah){

        }else if (i == R.id.toolbar_menu_hapus){

        }

        return super.onOptionsItemSelected(item);
    }
}
