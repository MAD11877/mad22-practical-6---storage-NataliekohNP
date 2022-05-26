package sg.edu.np.mad.madtutorial3_natalie;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main Activity";
    User user = new User();
    public String username;
    public String descript;
    private Boolean follow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent receiving = getIntent();
        username = receiving.getStringExtra("Username");
        descript = receiving.getStringExtra("description");
        follow = receiving.getBooleanExtra("follow", false);
        TextView desc = findViewById(R.id.desc);
        desc.setText(descript);
        TextView username2 = findViewById(R.id.Username);
        username2.setText(username);



        Button mybutton = findViewById(R.id.button);
        if(follow){
            mybutton.setText("Unfollow");
        }
        else{
            mybutton.setText("Follow");
        }
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(follow == true){
                    mybutton.setText("Unfollow");
                    follow = false;
                    Toast toasttext = Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT);
                    toasttext.show();


                }
                else{
                    Toast toasttext2 = Toast.makeText(getApplicationContext(), "unfollowed", Toast.LENGTH_SHORT);
                    toasttext2.show();
                    mybutton.setText("Follow");
                    follow = true;
                }
            }
        });
    }
}
