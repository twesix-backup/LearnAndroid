package com.example.twesix.learn.android;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messages.add(new Message("message", 0));
        messages.add(new Message("message", 1));
        messages.add(new Message("message message message message message message message message message message message message message message message message message message message message ", 0));
        messages.add(new Message("message", 1));
        messages.add(new Message("message", 0));
        messages.add(new Message("message", 0));
        messages.add(new Message("message message message message message message message message message message message message message message message message message message message ", 1));
        messages.add(new Message("message", 1));

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final MessageAdapter messageAdapter = new MessageAdapter(messages);
        recyclerView.setAdapter(messageAdapter);

        Button send = findViewById(R.id.send);
        final EditText input = findViewById(R.id.input);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messages.add(new Message(input.getText().toString(), 0));
                messageAdapter.notifyItemInserted(messages.size() - 1);
                recyclerView.scrollToPosition(messages.size() - 1);
            }
        });
    }
}

class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>
{
    private List<Message> messageList;
    MessageAdapter(List<Message> list)
    {
        this.messageList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout recv;
        TextView recv_title;
        TextView recv_content;

        LinearLayout send;
        TextView send_title;
        TextView send_content;

        ViewHolder(View view)
        {
            super(view);
            recv = view.findViewById(R.id.recv);
            recv_title = view.findViewById(R.id.recv_title);
            recv_content = view.findViewById(R.id.recv_content);
            send = view.findViewById(R.id.send);
            send_title = view.findViewById(R.id.send_title);
            send_content = view.findViewById(R.id.send_content);
        }
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_item, viewGroup, false);
        MessageAdapter.ViewHolder viewHolder = new MessageAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        if (message.type == message.SEND)
        {
            holder.recv.setVisibility(View.GONE);
            holder.send_title.setText("sender");
            holder.send_content.setText(message.message);
        }
        else if (message.type == message.RECV)
        {
            holder.send.setVisibility(View.GONE);
            holder.recv_title.setText("receiver");
            holder.recv_content.setText(message.message);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}

class Message
{
    final int SEND = 0;
    final int RECV = 1;
    String message;
    int type;

    Message(String message, int type)
    {
        this.message = message;
        this.type = type;
    }
}
