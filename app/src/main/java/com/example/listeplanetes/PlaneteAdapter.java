package com.example.listeplanetes;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

class PlaneteAdapter extends BaseAdapter {
    private int SPINNER_POSITION = 1;
    private int CHECKBOX_POSITION = 2;

    private Activity context;
    private ArrayList<Planete> dataSet;
    private Button verify;

    public PlaneteAdapter(Activity context, Data data) {
        this.context = context;
        this.dataSet = data.getDataSet();
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int arg0) {
        return dataSet.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.listitem, null);
        }

        TextView nomPlanete = (TextView) itemView.findViewById(R.id.textView);
        final CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        final Spinner spinner = (Spinner) itemView.findViewById(R.id.spinner);

        verify = (Button) context.findViewById(R.id.button);

        nomPlanete.setText(dataSet.get(position).getNom());

        //  installer l'adaptateur pour la liste déroulante (spinner)
//        String[] taillePlanetes = getTaillePlanetes();
        ArrayList planetSizes = getCorrectSizes();
        final ArrayAdapter<String> spinAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, planetSizes);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinAdapter);

        checkBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) (compoundButton, b) -> {
            CheckBox checkBox1 = (CheckBox) compoundButton.findViewById(R.id.checkbox);
            spinner.setEnabled(!checkBox1.isChecked());
            spinAdapter.notifyDataSetChanged();

            // Si tous les checkbox sont cochés, activer le bouton verify
            verify.setEnabled(areAllCheckBoxesChecked());
        });

        return itemView;
    }

    private boolean areAllCheckBoxesChecked() {
        Boolean flag = true;
        for (int i = 0; i < getCount(); i++) {
            // La liste des planetes (lignes)
            ViewGroup parent = context.findViewById(R.id.listView);
            // La liste des Views (colonnes)
            LinearLayout listItem = (LinearLayout) parent.getChildAt(i);
            // Le checkbox de la ligne (i + 1)
            CheckBox cb = (CheckBox) listItem.getChildAt(CHECKBOX_POSITION);
            if (!cb.isChecked()) {
                flag = false;
                break;
            }
        }
        return flag;
    };

    public ArrayList getSelectedSizes() {
        ArrayList returned = new ArrayList();
        for (int i = 0; i < getCount(); i++) {
            // La liste des planetes (lignes)
            ViewGroup parent = context.findViewById(R.id.listView);
            // La liste des Views (colonnes)
            LinearLayout listItem = (LinearLayout) parent.getChildAt(i);
            // Le spinner de la ligne (i + 1)
            Spinner s = (Spinner) listItem.getChildAt(SPINNER_POSITION);
            // La valeur choisie de ce spinner
            int addedValue = Integer.parseInt(s.getSelectedItem().toString());
            returned.add(addedValue);
        }
        return returned;
    }

    public ArrayList getCorrectSizes() {
        ArrayList returned = new ArrayList();
        for (Planete planet : dataSet) {
            returned.add(planet.getTaille());
        }
        return returned;
    }
}
