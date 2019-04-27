package me.yluo.ruisiapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.yluo.ruisiapp.R;
import me.yluo.ruisiapp.activity.ChatActivity;
import me.yluo.ruisiapp.activity.UserDetailActivity;
import me.yluo.ruisiapp.listener.ListItemLongClickListener;
import me.yluo.ruisiapp.model.FriendData;
import me.yluo.ruisiapp.widget.CircleImageView;

/**
 * Created by free2 on 16-4-12.
 * 好友列表
 */
public class FriendAdapter extends BaseAdapter {

    private List<FriendData> datas;
    private Context context;
    private ListItemLongClickListener listener;

    public FriendAdapter(Context context, List<FriendData> datas, ListItemLongClickListener l) {
        this.datas = datas;
        this.context = context;
        this.listener = l;
    }

    @Override
    protected int getDataCount() {
        return datas.size();
    }

    @Override
    protected int getItemType(int pos) {
        return 0;
    }

    @Override
    protected BaseViewHolder getItemViewHolder(ViewGroup parent, int viewType) {
        return new FriendViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false));
    }

    private class FriendViewHolder extends BaseViewHolder {
        CircleImageView user_image;
        TextView user_name, user_info, is_online;
        View container;

        FriendViewHolder(View itemView) {
            super(itemView);
            user_image = itemView.findViewById(R.id.logo);
            user_name = itemView.findViewById(R.id.user_name);
            user_info = itemView.findViewById(R.id.user_info);
            is_online = itemView.findViewById(R.id.is_online);
            container = itemView.findViewById(R.id.list_item);

            user_image.setOnClickListener(v -> userImage_click());

            container.setOnClickListener(v -> item_click());
        }

        @Override
        void setData(final int position) {
            FriendData single = datas.get(position);
            user_name.setText(single.userName);
            user_info.setText(single.info);
            is_online.setVisibility(single.isOnline() ? View.VISIBLE : View.GONE);
            Picasso.get().load(single.imgUrl).placeholder(R.drawable.image_placeholder).into(user_image);
            container.setOnLongClickListener(view -> {
                if (listener != null) {
                    listener.onItemLongClick(container, position);
                    return true;
                }
                return false;
            });
        }

        void userImage_click() {
            FriendData single = datas.get(getAdapterPosition());
            String username = single.userName;
            UserDetailActivity.openWithAnimation((Activity) context, username, user_image, single.uid);
        }

        void item_click() {
            String uid = datas.get(getAdapterPosition()).uid;
            String username = datas.get(getAdapterPosition()).userName;
            String url = "home.php?mod=space&do=pm&subop=view&touid=" + uid + "&mobile=2";
            ChatActivity.open(context, username, url);
        }

    }
}
