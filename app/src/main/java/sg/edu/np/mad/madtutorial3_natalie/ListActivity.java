package sg.edu.np.mad.madtutorial3_natalie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DialogTitle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "List Activity";
    public String myusername = "MyUsername";
    public String mydescription = "MyDescription";
    public Boolean followed = true;
    dbHandler dbHandler = new dbHandler(this, null, null, 1);
    private int randomOTP(){
        Random ran = new Random();
        int value = ran.nextInt(10000000);
        return value;
    }
    private ArrayList<User> userslist;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.Recycler);
        userslist = new ArrayList<>();
        AlertDialog.Builder buildalert = new AlertDialog.Builder(this);
        buildalert.setTitle("Profile");
        buildalert.setMessage("Madness");
        buildalert.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String txtmsg = String.valueOf(randomOTP());
                Intent myintent = new Intent(ListActivity.this, MainActivity.class);
                myintent.putExtra("Username", txtmsg);
                startActivity(myintent);
            }
        });
        buildalert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        setuserinfo();
        setAdapter();


    }
    private void setAdapter(){
        RecyclerAdapter adapter = new RecyclerAdapter(userslist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setuserinfo() {
        for (int i = 0; i < 20; i++) {
            String username = String.valueOf(randomOTP());
            String description = String.valueOf(randomOTP());
            User use = new User();
            use.setId(i);
            use.setFollowed(true);
            use.setName(username);
            use.setDescription(description);
            dbHandler.addUsers(use);

        }
        userslist = dbHandler.showall();

    }
}
