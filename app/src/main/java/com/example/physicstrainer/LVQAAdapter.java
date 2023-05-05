package com.example.physicstrainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LVQAAdapter extends BaseAdapter {
    List<String> list;
    Context context;
    protected com.example.physicstrainer.Application app;

    public LVQAAdapter(Context Context, android.app.Application App){
        this.context = Context;
        this.app = (com.example.physicstrainer.Application) App;

        list = app.getAnswers(0);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
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

        tv_title.setText(i+1 + ".");
        tv_text.setText(list.get(i));


        return view;
    }
}
