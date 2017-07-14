package com.fredrare.pm;

import com.fredrare.pm.state.State;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AndroidFirebase implements AntSmasher.FirebaseService {

    @Override
    public void addToFirebase(String user,int score) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("leaderboard");
        reference.child(user).setValue(score);
    }
}
