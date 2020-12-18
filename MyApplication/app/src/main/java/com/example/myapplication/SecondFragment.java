package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {
    TextView text;
    Button button1, button2, button3, restart, back;
    Scene[] scenes;
    int current_scene = 0;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
    void generate(View view){
        text = (TextView) view.findViewById(R.id.scene_description);
        button1 = (Button) view.findViewById(R.id.scene_first_choice);
        button2 = (Button) view.findViewById(R.id.scene_second_choice);
        button3 = (Button) view.findViewById(R.id.scene_third_choice);
        restart = (Button) view.findViewById(R.id.restart);
        back = (Button) view.findViewById(R.id.back);
        scenes = new Scene[10];
        for (int i = 0; i < 10; i++){
            scenes[i] = new Scene();
            scenes[i].scene_num = i;
        }
        scenes[0].child1 = 1;
        scenes[0].child2 = 2;
        scenes[0].child3 = 3;
        scenes[1].child1 = 4;
        scenes[1].child2 = 5;
        scenes[2].child1 = 6;
        scenes[3].child1 = 7;
        scenes[4].child1 = 8;
        scenes[6].child1 = 9;
        scenes[0].scene_progenitor = -1;
        scenes[1].scene_progenitor = 0;
        scenes[2].scene_progenitor = 0;
        scenes[3].scene_progenitor = 0;
        scenes[4].scene_progenitor = 1;
        scenes[5].scene_progenitor = 1;
        scenes[6].scene_progenitor = 2;
        scenes[7].scene_progenitor = 3;
        scenes[8].scene_progenitor = 4;
        scenes[9].scene_progenitor = 6;
        scenes[0].quest_text = "Вы видите перед собой развилку";
        scenes[0].button1_text = "Повернуть направо";
        scenes[0].button2_text = "Повернуть налево";
        scenes[0].button3_text = "Идти прямо";
        scenes[1].quest_text = "Вы пошли направо и снова столкнулись с выбором";
        scenes[1].button1_text = "Повернуть направо";
        scenes[1].button2_text = "Повернуть налево";
        scenes[5].quest_text = "Тупик";
        scenes[4].quest_text = "Вы видите впереди свет";
        scenes[4].button1_text = "Идти дальше";
        scenes[8].quest_text = "Факелы озоряют каменную стену, вы снова пошли не туда";
        scenes[2].quest_text = "Предчувствие подсказывает, что вы на правильном пути";
        scenes[2].button1_text = "Продолжить путь";
        scenes[6].quest_text = "Уже виднеется выход";
        scenes[6].button1_text = "Идти дальше";
        scenes[9].quest_text = "Вы выбрались";
        scenes[3].quest_text = "Что-то впереди слишком темно";
        scenes[3].button1_text = "Продолжить путь";
        scenes[7].quest_text = "Вы так и не нашли в этой темноте дорогу";
    }
    void make_scene(View view){
        text.setText(scenes[current_scene].quest_text);
        if(scenes[current_scene].child1 == -1){
            button1.setVisibility(view.GONE);
        } else {
            button1.setVisibility(view.VISIBLE);
            button1.setText(scenes[current_scene].button1_text);
        }
        if(scenes[current_scene].child2 == -1){
            button2.setVisibility(view.GONE);
        } else {
            button2.setVisibility(view.VISIBLE);
            button2.setText(scenes[current_scene].button2_text);
        }
        if(scenes[current_scene].child3 == -1){
            button3.setVisibility(view.GONE);
        } else {
            button3.setVisibility(view.VISIBLE);
            button3.setText(scenes[current_scene].button3_text);
        }
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generate(view);
        make_scene(view);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_scene = scenes[current_scene].scene_progenitor;
                if (current_scene == -1){
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                } else {
                    make_scene(view);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_scene = scenes[current_scene].child2;
                make_scene(view);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_scene = scenes[current_scene].child3;
                make_scene(view);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_scene = scenes[current_scene].child1;
                make_scene(view);
            }
        });
    }
}