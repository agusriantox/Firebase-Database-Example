package app.agus.data.add;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.agus.data.R;
import app.agus.data.model.Kendaraan;

/**
 * Created by ghost on 25/11/17.
 */

public class AddFragment extends Fragment {

    public AddFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    EditText etName;
    EditText etNoPolisi;
    EditText etNoHP;
    EditText etAddress;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    String kendaraan_id;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        etName = v.findViewById(R.id.et_name);
        etNoPolisi = v.findViewById(R.id.et_no_polisi);
        etNoHP = v.findViewById(R.id.et_phone);
        etAddress = v.findViewById(R.id.et_address);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference("kendaraan");

        Button btnSubmit = v.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(view ->{
            if (checkField()){
                String nama = etName.getText().toString();
                String no_polisi = etNoPolisi.getText().toString();
                String no_hp = etNoHP.getText().toString();
                String alamat = etNoPolisi.getText().toString();
                createUser(nama,no_polisi,no_hp,alamat);
            }
        });

        return v;
    }

    private boolean checkField() {
        boolean isOk = true;
        if (etName.getText().toString().isEmpty()){
            etName.setError("Mohon isi field ini");
            isOk = false;
        }

        if (etNoPolisi.getText().toString().isEmpty()){
            etName.setError("Mohon isi field ini");
            isOk = false;
        }

        if (etNoHP.getText().toString().isEmpty()){
            etName.setError("Mohon isi field ini");
            isOk = false;
        }

        if (etAddress.getText().toString().isEmpty()){
            etName.setError("Mohon isi field ini");
            isOk = false;
        }

        return isOk;
    }

    private void createUser(String nama, String no_polisi, String no_hp, String alamat) {
//        userId = mFirebaseDatabase.push();
        Kendaraan kendaraan = new Kendaraan(nama, no_polisi, no_hp, alamat);

        mFirebaseDatabase.push().setValue(kendaraan);
        addUserChangeListener();
    }

    private void addUserChangeListener() {
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Kendaraan kendaraan = dataSnapshot.getValue(Kendaraan.class);

                if (kendaraan == null) {
                    Log.e("data", "data is null");
                    return;
                }
                Toast.makeText(getActivity(), "Berhasil menambah data", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("data error", databaseError.toString());
            }
        });
    }
}
