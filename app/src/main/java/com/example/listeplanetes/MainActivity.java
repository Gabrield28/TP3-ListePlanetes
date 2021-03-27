package com.example.listeplanetes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    PlaneteAdapter adapter;
    Data data;
    Button verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Initializing the listView
        listView = (ListView) findViewById(R.id.listView);

        // Initializing, disabling and setting up the listener for the verify button
        verify = (Button) findViewById(R.id.button);
        verify.setEnabled(false);
        verify.setOnClickListener(verifySizes);

        // Initializing the data for the planets
        data = new Data();

        // Initializing the adapter
        adapter = new PlaneteAdapter(this, data);

        // Setting up the adapter for the listView
        listView.setAdapter(adapter);
    }

    private View.OnClickListener verifySizes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Récupérer les tailles entrées et les tailles réeles (pour la comparaison)
            ArrayList sizes = adapter.getSelectedSizes();
            ArrayList realSizes = adapter.getCorrectSizes();

            // Comparaison
            boolean correct = true;
            for (int i = 0; i < sizes.size(); i++) {
                int size = (int) sizes.get(i);
                int realSize = (int) realSizes.get(i);
                if (size != realSize) {
                    correct = false;
                    break;
                }
            }
            if (correct == true) {
                Toast.makeText(MainActivity.this, "Bien joué! Tout est juste.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Il y a au moins une erreur, réessayez.", Toast.LENGTH_SHORT).show();
            }
        }
    };
}