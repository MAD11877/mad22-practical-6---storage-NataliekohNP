package sg.edu.np.mad.madtutorial3_natalie;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<User> userlist;
    public RecyclerAdapter(ArrayList<User> userlist){
        this.userlist = userlist;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtname ;
        private TextView descriptiontxt;
        private ImageView imageview2;
        public MyViewHolder(final View view){
            super(view);
            txtname = view.findViewById(R.id.nametxt);
            descriptiontxt = view.findViewById(R.id.description);
            imageview2 = view.findViewById(R.id.profile);
        }
    }
    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String name = userlist.get(position).getName();
        String des = userlist.get(position).getDescription();
        boolean follow  = true;
        holder.txtname.setText("Name: " + name);
        holder.descriptiontxt.setText("Description: "+ des);

        holder.imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder buildalert = new AlertDialog.Builder(view.getContext());
                buildalert.setTitle("Profile");
                buildalert.setMessage("Madness");
                buildalert.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent myintent = new Intent(view.getContext(), MainActivity.class);
                        myintent.putExtra("Username", name);
                        myintent.putExtra("description", des);
                        myintent.putExtra("follow", follow);
                        view.getContext().startActivity(myintent);
                    }
                });
                buildalert.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){

                    }
                });
                buildalert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        int size = userlist.size();
        return size;

    }
}
