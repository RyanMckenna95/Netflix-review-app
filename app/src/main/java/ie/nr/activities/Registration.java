package ie.nr.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.PasswordAuthentication;

import ie.nr.R;

public class Registration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String email,password;
    private EditText mail, pass;
    private Button registration;
    private FirebaseApp app;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);

        mail=findViewById(R.id.Reg_email);
        pass=findViewById(R.id.Reg_password);
        registration=findViewById(R.id.RegBtn);


        mAuth = FirebaseAuth.getInstance();


        email=mail.getText().toString();
        password=pass.getText().toString();
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(email, password);
            }
        });


    }

    private void registerUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

}
