package me.tandeneck.blogdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import me.tandeneck.blogdemo.entity.HomeEntity;
import me.tandeneck.blogdemo.seekbar.SeekBarActivity;
import me.tandeneck.blogdemo.service.ServiceMainActivity;

public class HomeActivity extends BaseActivity {

    private RecyclerView mRV;
    private int[] mBgRes = {R.drawable.home_item_bg_1,
            R.drawable.home_item_bg_2,
            R.drawable.home_item_bg_3,
            R.drawable.home_item_bg_4,
            R.drawable.home_item_bg_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initView() {
        mRV = findViewById(R.id.rv);
        mRV.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
    }

    private void initData() {
        Random random = new Random();
        List<HomeEntity> datas = Arrays.asList(new HomeEntity("SeekBar", mBgRes[random.nextInt(5)], SeekBarActivity.class),
                new HomeEntity("Service", mBgRes[random.nextInt(5)], ServiceMainActivity.class));
        HomeAdapter homeAdapter = new HomeAdapter(this,datas);
        mRV.setAdapter(homeAdapter);
    }


    private static class HomeAdapter extends RecyclerView.Adapter<HomeItemViewHolder> {

        private Context mContext;
        private List<HomeEntity> mDatas = new ArrayList<>();


        public HomeAdapter(Context context, List<HomeEntity> datas) {
            mContext = context;
            mDatas = datas;
        }


        @NonNull
        @Override
        public HomeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_home, parent, false);
            return new HomeItemViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull HomeItemViewHolder holder, final int position) {
            holder.mTv.setText(mDatas.get(position).getTitle());
            holder.mIv.setImageResource(mDatas.get(position).getRes());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext,mDatas.get(position).getClazz()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas == null ? 0 : mDatas.size();
        }

    }


    private static class HomeItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIv;
        private TextView mTv;

        public HomeItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mTv = itemView.findViewById(R.id.tv);
        }
    }


}
