package com.example.root.rock3test2.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.rock3test2.R;
import com.example.root.rock3test2.model.Product;

import java.util.List;

/**
 * Created by root on 9/2/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Product> moviesList;
    public Context context;

    public RecyclerAdapter(List<Product> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context=context;
    }

    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_row, parent, false);

        return new RecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.MyViewHolder holder, final int position) {

        Product dataList = moviesList.get(position);
       // int year=Integer.valueOf(dataList.getFirstappearance());

         /*   holder.txtName.setText(dataList.getName());
            holder.txtDueDate.setText(dataList.getFirstappearance());
            holder.txtPriority.setText(dataList.getTeam());
           */ holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeItem(position);

                }
            });
            holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editItem(position);
                }
            });

    }
    public void removeItem(int position) {
       // Const.Products.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void editItem(final int position){
        //pass the 'context' here
      /*  final Dialog alertDialog = new Dialog(context);

        alertDialog.setCancelable(true);
        alertDialog.setContentView(R.layout.lay);
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        final EditText edtName=(EditText)alertDialog.findViewById(R.id.edtName);
        final EditText edtDueDate=(EditText)alertDialog.findViewById(R.id.edtDueDate);
        final EditText edtPriority=(EditText)alertDialog.findViewById(R.id.edtPriority);
        Button btnUpdate=(Button)alertDialog.findViewById(R.id.btnUpdate);
        Button btnCancel=(Button)alertDialog.findViewById(R.id.btnCancel);

        edtName.setText(moviesList.get(position).getName());
        edtName.setTextColor(context.getResources().getColor(R.color.red));
        edtDueDate.setText(moviesList.get(position).getFirstappearance());
        edtPriority.setText(moviesList.get(position).getTeam());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(edtName.getText().toString().equals("")){
                    Toast.makeText(context,"Please enter Name",Toast.LENGTH_SHORT).show();
                }else if(edtDueDate.getText().toString().equals("")){
                    Toast.makeText(context,"Please enter DueDate",Toast.LENGTH_SHORT).show();
                }else if(edtPriority.getText().toString().equals("")){
                    Toast.makeText(context,"Please enter Priority",Toast.LENGTH_SHORT).show();
                }else {
                    Const.Products.get(position).setName(edtName.getText().toString());
                    Const.Products.get(position).setFirstappearance(edtDueDate.getText().toString());
                    Const.Products.get(position).setTeam(edtPriority.getText().toString());
                    //notifyItemChanged(position);
                    ((MainActivity) context).updateViewPager();
                    alertDialog.cancel();
                }
            }
        });



        alertDialog.show();
        */
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName,txtDueDate,txtPriority;
        public ConstraintLayout lyMainRow;
        public ImageView btnDelete,btnEdit;
       // final LinearLayout.LayoutParams params;
        public MyViewHolder(View view) {
            super(view);
            /*lyMainRow=(ConstraintLayout)view.findViewById(R.id.lyMainRow);
            txtName=(TextView)view.findViewById(R.id.txtName);
            txtDueDate=(TextView)view.findViewById(R.id.txtDueDate);
            txtPriority=(TextView)view.findViewById(R.id.txtPriority);
            btnDelete=(ImageView)view.findViewById(R.id.btnDelete);
            btnEdit=(ImageView)view.findViewById(R.id.btnEdit);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,         ViewGroup.LayoutParams.WRAP_CONTENT);
            /*title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);*/
        }

        private void Layout_hide() {
         //   params.height = 0;
            //itemView.setLayoutParams(params); //This One.
           // lyMainRow.setLayoutParams(params);   //Or This one.

        }

    }
}
