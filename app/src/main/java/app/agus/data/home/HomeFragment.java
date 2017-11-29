package app.agus.data.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.agus.data.R;
import app.agus.data.model.Kendaraan;

/**
 * Created by ghost on 25/11/17.
 */

public class HomeFragment extends Fragment{

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    RecyclerView recyclerView;
    List<Kendaraan> kendaraanList;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.list_data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        mFirebaseInstance = FirebaseDatabase.getInstance();
        String data = mFirebaseInstance.getReference("kendaraan").toString();
        Log.e("data", data);
        mFirebaseInstance.getReference("kendaraan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("data", String.valueOf(dataSnapshot.getValue()));
                kendaraanList = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    String key = data.getKey();
                    Kendaraan kendaraan = dataSnapshot.child(key).getValue(Kendaraan.class);
                    assert kendaraan != null;
                    kendaraan.setKey(key);
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

        return view;
    }
}
