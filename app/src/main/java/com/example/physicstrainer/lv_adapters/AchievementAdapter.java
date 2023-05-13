package com.example.physicstrainer.lv_adapters;

import static com.example.physicstrainer.R.mipmap.first_achievement;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.physicstrainer.R;

public class AchievementAdapter extends BaseAdapter {
    Context context;

    public AchievementAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.achievement_element, viewGroup, false);
        }

        TextView description = (TextView) view.findViewById(R.id.tvDescription_AchievementElement);
        TextView name = (TextView) view.findViewById(R.id.tvName_AchievementElement);
        ImageView image = (ImageView) view.findViewById(R.id.IV_AchievementElement);

        description.setText("Хорошее начало!");
        name.setText("За решение первого блока вопросов");
        //image.setImageDrawable(Drawable.createFromPath("D:\\Android_Projects\\PhysicsTrainer\\app\\src\\main\\res\\mipmap-xhdpi\\first_achievement.png"));



        return view;
    }
}
