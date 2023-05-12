package com.example.physicstrainer.lv_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.physicstrainer.R;
import com.example.physicstrainer.helpers.ThemeHelper;
import com.example.physicstrainer.helpers.TheoryHelper;
import com.example.physicstrainer.serialize.Theme;
import com.example.physicstrainer.serialize.Theory;

import java.util.List;

public class TheoryAdapter extends BaseAdapter {
    List<Theme> themeList;
    Context context;
    protected com.example.physicstrainer.Application app = new com.example.physicstrainer.Application();

    public TheoryAdapter(Context context){
        this.themeList = ThemeHelper.getTheme();
        this.context = context;
    }

    @Override
    public int getCount() {
        return themeList.size();
    }

    @Override
    public Object getItem(int i) {
        return themeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lv_theory, viewGroup, false);
        }

        TextView tv_title = (TextView) view.findViewById(R.id.LV_T_Title);
        TextView tv_text = (TextView) view.findViewById(R.id.LV_T_Text);


        // КОСТЫЛЬ ПОМЕНЯТЬ
        List<Theory> theory = TheoryHelper.getTheoryLessons();
        tv_title.setText(tv_title.getText() + " Силы в механике");
        tv_text.setText(theory.get(0).getName());

        return view;
    }
}
