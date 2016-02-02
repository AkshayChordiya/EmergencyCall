package com.pccoe.emergency;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EmergencyAdapter emergencyAdapter;
    private ArrayList<Emergency> mEmergencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmergencyList = new ArrayList<>();
        emergencyAdapter = new EmergencyAdapter(this, mEmergencyList);
        ListView emergencyList = (ListView) findViewById(R.id.listView);
        emergencyList.setAdapter(emergencyAdapter);
        emergencyList.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNumber:
                addNumber();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addNumber() {
        View view = getLayoutInflater().inflate(R.layout.dialog_add_number, null, false);
        final EditText name = (EditText) view.findViewById(R.id.editName);
        final EditText number = (EditText) view.findViewById(R.id.editNumber);
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.addNumber)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        emergencyAdapter.add(new Emergency(name.getText().toString(), Integer.parseInt(number.getText().toString())));
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        alertDialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final Emergency emergency = mEmergencyList.get(position);
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.performAction)
                .setMessage(emergency.getName() + ":" + emergency.getNumber())
                .setPositiveButton(R.string.callNumber, new DialogInterface.OnClickListener() {
                    @SuppressWarnings("MissingPermission")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent .setData(Uri.parse("tel:+91-" + emergency.getNumber()));
                        startActivity(callIntent);
                    }
                })
                .setNegativeButton(R.string.messageNumber, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        Uri data = Uri.parse("sms:" + emergency.getNumber());
                        intent.setData(data);
                        startActivity(intent);
                    }
                })
                .create();
        alertDialog.show();
    }
}
