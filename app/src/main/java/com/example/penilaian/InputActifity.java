package com.example.penilaian;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.penilaian.databinding.ActivityInputActifityBinding;
import com.example.penilaian.models.MahasiswaModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputActifity extends AppCompatActivity {

    private ActivityInputActifityBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInputActifityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //database initializer
        reference = FirebaseDatabase.getInstance().getReference("nilai");

        //get id button
        binding.submit.setOnClickListener(view -> storeData());
        binding.hasil.setOnClickListener(view -> {
            startActivity(new Intent(this,ResultActivity.class));
            finish();
        });
    }

    //Mengirim data ke database
    private void storeData(){
        //get value dari input text
        String nama = binding.nama.getEditableText().toString();
        String nim = binding.nim.getEditableText().toString();
        String mk = binding.mk.getEditableText().toString();
        String tugas = binding.tugas.getEditableText().toString();
        String quiz = binding.quiz.getEditableText().toString();
        String uts = binding.uts.getEditableText().toString();
        String uas = binding.uas.getEditableText().toString();

        //Warning
        if (nama.isEmpty() ||
        nim.isEmpty() ||
        mk.isEmpty() ||
        tugas.isEmpty() ||
        quiz.isEmpty() ||
        uts.isEmpty() ||
        uas.isEmpty()) {
            binding.nama.setError("Data Masih Kosong!");
            binding.nim.setError("Data Masih Kosong!");
            binding.mk.setError("Data Masih Kosong!");
            binding.tugas.setError("Data Masih Kosong!");
            binding.quiz.setError("Data Masih Kosong!");
            binding.uts.setError("Data Masih Kosong!");
            binding.uas.setError("Data Masih Kosong!");
        } else {
            //get each nilai
            int nTugas = Integer.parseInt(tugas);
            int nQuiz = Integer.parseInt(quiz);
            int nUTS = Integer.parseInt(uts);
            int nUAS = Integer.parseInt(uas);

            if (nTugas > 100 || nQuiz > 100 || nUTS > 100 || nUAS > 100){
                binding.tugas.setError("Tidak Bisa Melebihi 100!");
                binding.quiz.setError("Tidak Bisa Melebihi 100!");
                binding.uts.setError("Tidak Bisa Melebihi 100!");
                binding.uas.setError("Tidak Bisa Melebihi 100!");
            }
            //mengirim data ke firebase
            MahasiswaModel mahasiswaModel = new MahasiswaModel(nama,mk,Integer.parseInt(nim),Float.parseFloat(tugas),Float.parseFloat(quiz),Float.parseFloat(uts),Float.parseFloat(uas));
            String id = reference.push().getKey();

            try {
                assert id != null;
                reference.child(id).setValue(mahasiswaModel);
                startActivity(new Intent(getApplicationContext(),ResultActivity.class));
                finish();
            } catch (RuntimeException e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }
}