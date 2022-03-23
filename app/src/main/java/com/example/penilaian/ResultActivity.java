package com.example.penilaian;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.penilaian.Adapters.ListAdapter;
import com.example.penilaian.databinding.ActivityResultBinding;
import com.example.penilaian.models.MahasiswaViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private final ArrayList<MahasiswaViewModel> list = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.model.setLayoutManager(new GridLayoutManager(this,2));
        binding.add.setOnClickListener(view -> startActivity(new Intent(this, InputActifity.class)));

        getData();
    }

    private void getData(){
        //database initializer
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("nilai");

        //ambil data dari database
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot data : snapshot.getChildren()){
                        try {
                            MahasiswaViewModel MahasiswaViewModel = data.getValue(MahasiswaViewModel.class);
                            list.add(MahasiswaViewModel);
                            adapter = new ListAdapter(list);
                            binding.model.setAdapter(adapter);
                        } catch (RuntimeException e){
                            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}