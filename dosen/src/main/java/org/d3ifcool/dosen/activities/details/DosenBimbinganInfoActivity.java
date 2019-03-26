package org.d3ifcool.dosen.activities.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.dosen.R;

public class DosenBimbinganInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_bimbingan_info);

        CardView cardView = findViewById(R.id.frg_mhs_pa_cardview_bimbingan);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DosenBimbinganInfoActivity.this, DosenBimbinganDetailActivity.class);
                startActivity(intent);
            }
        });


    }
}
