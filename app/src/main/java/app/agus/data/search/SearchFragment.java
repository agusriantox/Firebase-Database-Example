package app.agus.data.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.agus.data.R;
import app.agus.data.home.HomeAdapter;
import app.agus.data.model.Kendaraan;

/**
 * Created by ghost on 25/11/17.
 */

public class SearchFragment extends Fragment{

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    RecyclerView recyclerView;


    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.list_data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        EditText editText = view.findViewById(R.id.search_query);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 3)search(editText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    private void search(String query) {
        Log.e("query",query);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("kendaraan");
        mFirebaseDatabase.orderByChild("nama")
                .startAt(query)
                .endAt(query+"\uf8ff")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("data",dataSnapshot.toString());

                List<Kendaraan> kendaraanList = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    String key = data.getKey();
                    Kendaraan kendaraan = dataSnapshot.child(key).getValue(Kendaraan.class);
                    assert kendaraan != null;
                    kendaraan.setKey(key);
                    if (kendaraan.getNama().startsWith(query) || kendaraan.getNama().endsWith(query))
                        kendaraanList.add(kendaraan);
                    Log.e("key",key);
                }
                HomeAdapter adapter = new HomeAdapter(getActivity(),kendaraanList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
