package com.example.rhizoma_alismatis.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.rhizoma_alismatis.R;
import com.example.rhizoma_alismatis.models.UserInfo;
import com.example.rhizoma_alismatis.utils.ImageFormat;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class UsersAdapter extends ArrayAdapter<UserInfo> {
    private final int resourceId;

    public UsersAdapter(@NonNull Context context, int resource, List<UserInfo> users) {
        super(context, resource, users);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserInfo userInfo = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        RoundedImageView imageView = view.findViewById(R.id.user_component_image);
        TextView userName = view.findViewById(R.id.user_component_name);
        TextView uuid = view.findViewById(R.id.user_component_uuid);

        imageView.setImageBitmap(ImageFormat.base64ToBitmap(userInfo.UserIcon));
        userName.setText(userInfo.UserName);
        uuid.setText(userInfo.UserId);

        return view;
    }
}
