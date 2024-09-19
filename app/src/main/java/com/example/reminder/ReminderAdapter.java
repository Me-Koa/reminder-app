package com.example.reminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private ArrayList<Reminder> reminderList;

    public ReminderAdapter(ArrayList<Reminder> reminderList) {
        this.reminderList = reminderList;
    }


    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        Reminder reminder = reminderList.get(position);
        holder.timeTextView.setText(reminder.getTime());
        holder.messageTextView.setText(reminder.getMessage());
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }


    public static class ReminderViewHolder extends RecyclerView.ViewHolder {
        TextView timeTextView, messageTextView;



        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.reminderTime);
            messageTextView = itemView.findViewById(R.id.reminderMessage);
        }

    }


}
