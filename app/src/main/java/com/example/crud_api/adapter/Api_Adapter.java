package com.example.crud_api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.crud_api.R;
import com.example.crud_api.model.Student;

import java.util.List;

public class Api_Adapter extends RecyclerView.Adapter<Api_Adapter.MyViewHolder> implements Filterable {
    List<Student> students;
    private Context context;
    RecyclerViewClickListener mListener;
//    CustomFilter filter;
public Api_Adapter(List<Student> students, Context context) {
    this.students = students;


    this.context = context;

}
    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view, mListener);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(students.get(position).getName());
        holder.tv_student_code.setText(students.get(position).getStudent_code());
        holder.tv_grade.setText(students.get(position).getGrade());
        holder.tv_date.setText(students.get(position).getDate());

        holder.tv_major.setText(students.get(position).getMajor());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_launcher_background);
        requestOptions.error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .load(students.get(position).getImage())
                .apply(requestOptions)
                .into(holder.ImvStudent);

//        final Boolean love = pets.get(position).getLove();

//        if (love){
//            holder.mLove.setImageResource(R.drawable.likeon);
//        } else {
//            holder.mLove.setImageResource(R.drawable.likeof);
//        }

    }


    @Override
    public int getItemCount() {
        return students.size();
    }



public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;

        private ImageView ImvStudent;
        private TextView tv_name, tv_student_code, tv_grade,tv_date,tv_major;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            ImvStudent = itemView.findViewById(R.id.imv_student);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_student_code = itemView.findViewById(R.id.tv_student_code);
            tv_grade = itemView.findViewById(R.id.tv_grade);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_major = itemView.findViewById(R.id.tv_major);

        }


    @Override
    public void onClick(View view) {

    }
}
        public interface RecyclerViewClickListener {

        }
    }

