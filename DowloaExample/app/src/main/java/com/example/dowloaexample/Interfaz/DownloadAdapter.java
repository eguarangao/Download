package com.example.dowloaexample.Interfaz;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dowloaexample.R;
import com.example.dowloaexample.model;

import java.util.ArrayList;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder> {
 Context context;
 private ArrayList<model> list ;
 private AdapterView.OnItemClickListener onClickListener;
    public DownloadAdapter(Context context, ArrayList<model> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista =inflater.inflate(R.layout.base,parent,false);
        return  new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull final DownloadAdapter.ViewHolder holder, final int position) {

        holder.name.setText(list.get(position).getName());
        holder.date.setText(list.get(position).getDate());
        holder.description.setText(list.get(position).getDescription());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              downloadPdf(holder.name.getContext(),list.get(position).getName(),".pdf",DIRECTORY_DOWNLOADS,list.get(position).getPad());
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public void downloadPdf ( Context context, String file, String fileExten, String destinationDirecto,String url)
    {
       DownloadManager downloadManager =(DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirecto,file+fileExten);

        downloadManager.enqueue(request);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView description;
        TextView date;
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.lblLink);
            name = itemView.findViewById(R.id.lblName);
            date = itemView.findViewById(R.id.lblDate);
            button = itemView.findViewById(R.id.button);


        }

    }
}
