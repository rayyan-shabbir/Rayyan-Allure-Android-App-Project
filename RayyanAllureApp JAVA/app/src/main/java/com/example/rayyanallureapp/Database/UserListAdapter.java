package com.example.rayyanallureapp.Database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rayyanallureapp.R;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    // Interface for Delete click listener
    public interface OnDeleteClickListener {
        void OnDeleteClickListener(User myUser);
    }

    private final LayoutInflater layoutInflater;
    private Context context;
    private List<User> users;
    private OnDeleteClickListener onDeleteClickListener;

    public UserListAdapter(Context context, OnDeleteClickListener listener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        if (this.users != null) {
            User user = this.users.get(position);
            holder.setData(user.getName(), user.getFavPlace(), user.getFriend(), position);

            // For edit and delete
            holder.setListeners();
        } else {
            // Covers the case of data not being ready yet.
            holder.userNameView.setText("No User exists");
            holder.userPlaceView.setText("No Place exists");
            holder.userFriendView.setText("No Friend exists");
        }
    }

    @Override
    public int getItemCount() {
        if (this.users != null)
            return this.users.size();
        else return 0;
    }

    // Function for Fetching users and displaying in RV
    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView userNameView, userPlaceView, userFriendView;
        private int position;
        private ImageView imgDelete, imgEdit;

        public UserViewHolder(View itemView) {
            super(itemView);
            userNameView = itemView.findViewById(R.id.txtUserName);
            userPlaceView = itemView.findViewById(R.id.txtUserPlace);
            userFriendView = itemView.findViewById(R.id.txtUserFriend);
            imgDelete = itemView.findViewById(R.id.rowDelete);
            imgEdit = itemView.findViewById(R.id.rowEdit);
        }

        public void setData(String name, String place, String friend, int position) {
            userNameView.setText(name);
            userPlaceView.setText(place);
            userFriendView.setText(friend);
            this.position = position;
        }

        public void setListeners() {
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DatabaseEditActivity.class);
                    intent.putExtra(DatabaseEditActivity.USER_ID, users.get(position).getId());
                    ((Activity)context).startActivityForResult(intent, DatabaseActivity.UPDATE_USER_ACTIVITY_REQUEST_CODE);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(users.get(position));
                    }
                }
            });
        }
    }
}
