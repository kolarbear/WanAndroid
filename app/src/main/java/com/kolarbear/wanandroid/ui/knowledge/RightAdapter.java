package com.kolarbear.wanandroid.ui.knowledge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/25.
 */

public class RightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder>{

    private List<KnowledgeBean.ChildrenBean> datas = new ArrayList<>();

    private List<String> headers;

    private List<Integer> positions = new ArrayList<>();
    OnItemClickListener listener;
    @Inject
    public RightAdapter() {
    }

    public void setDatas(List<KnowledgeBean.ChildrenBean> datas)
    {
        if (datas!=null&&datas.size()>0)
        {
            this.datas = datas;
            preparePositions();
            notifyDataSetChanged();
        }
    }

    public void setHeaders(List<String> headers)
    {
        this.headers = headers;
    }

    private int getTagPosition(int position)
    {
        if (position==0)
            return 0;
        for (int i = 0; i < positions.size(); i++) {
            if (datas.get(position).getParentChapterId()==datas.get(positions.get(i)).getParentChapterId())
            {
                return i;
            }
        }
        return 0;
    }

    private void preparePositions()
    {
        for (int i = 0; i < datas.size(); i++) {
            if (i==0)
            {
                positions.add(i);
                continue;
            }
            if (i<datas.size()-1)
            {
                if (datas.get(i).getParentChapterId()!=datas.get(i+1).getParentChapterId())
                {
                    positions.add(i+1);
                }
            }
        }
    }

    public void setListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_knowledge_right, parent, false);

        return new ContentViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ContentViewHolder contentHolder = (ContentViewHolder) holder;
            contentHolder.tv.setText(datas.get(position).getName());
            contentHolder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null)
                        listener.click(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public long getHeaderId(int position) {
        for (int i = 0; i < positions.size(); i++) {
            if (datas.get(position).getParentChapterId()==datas.get(positions.get(i)).getParentChapterId())
                return i;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right_header, parent, false);

        return new HeaderViewHolder(inflate);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
        TextView itemView = (TextView) headerHolder.itemView;
        itemView.setText(headers.get(getTagPosition(position)));
    }


    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
    private class ContentViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public ContentViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_name);
        }
    }
    interface OnItemClickListener{
        void click(int position);
    }
}
