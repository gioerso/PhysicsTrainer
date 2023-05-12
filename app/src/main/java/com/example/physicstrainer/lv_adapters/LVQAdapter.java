package com.example.physicstrainer.lv_adapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.physicstrainer.QuestionList;
import com.example.physicstrainer.R;
import com.example.physicstrainer.serialize.Block;

import java.util.List;

public class LVQAdapter extends BaseAdapter {
    List<QuestionList> list;
    List<Block> bList;
    Context context;
    protected com.example.physicstrainer.Application app = new com.example.physicstrainer.Application();

    public LVQAdapter(Context Context, Application App){
        this.context = Context;
        this.app = (com.example.physicstrainer.Application) App;

        bList = app.getBlocksList();
        list = app.getFullList();
    }

    // костыль
    public int getBlockCount() {
        return bList.size();
    }
    public Object getBlockItem(int i) {
        return bList.get(i);
    }
    public long getBlockItemId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return list.size();
        //return bList.size();
    }

    @Override
    public Object getItem(int i) {
        //return bList.get(i);
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lv_q, viewGroup, false);
        }

        TextView tv_title = (TextView) view.findViewById(R.id.TV_Q_Title);
        TextView tv_text = (TextView) view.findViewById(R.id.TV_Q_Text);

        //tv_title.setText("Раздел: "+list.get(i).GetTitle());
        //tv_text.setText(list.get(i).GetText());

        // !!!ПОМЕНЯТЬ!!!
        tv_title.setText("Раздел: Силы в механике");
        tv_text.setText(list.get(i).GetText());

        return view;
    }
}
