package com.example.cookbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cookbook.Adapter.CategoryAdapter;
import com.example.cookbook.Adapter.ReceptAdapter;
import com.example.cookbook.Model.Category;
import com.example.cookbook.Model.Recept;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import kotlin.collections.ArrayDeque;

public class HomeActivity extends AppCompatActivity {
    private ImageView addCook,settingBtn;
    RecyclerView categoryRecycler, ReceptRecycler;
    CategoryAdapter categoryAdapter;
    static ReceptAdapter receptAdapter;
    static List<Recept> receptList = new ArrayList<>();
    static List<Recept> fullReceptsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Завтрак"));
        categoryList.add(new Category(2, "Обед"));
        categoryList.add(new Category(3, "Ужин"));
        categoryList.add(new Category(4, "Закуски"));
        categoryList.add(new Category(5, "Соусы"));

        setCategoryRecycler(categoryList);

        receptList.add(new Recept(1, "sendvi4", "Сэндвич с омлетом, помидорами и сыром","ЭНЕРГЕТИЧЕСКАЯ ЦЕННОСТЬ НА ПОРЦИЮ\nКАЛОРИЙНОСТЬ - 513 ККАЛ\nБЕЛКИ - 26 ГРАММ\nЖИРЫ - 33 ГРАММ\nУГЛЕВОДЫ - 32 ГРАММ\n\n" +
                "ИНГРЕДИЕНТЫ На одну порцию\nПомидоры - 100 г\nКрасный лук - 35 г\nЗелень - 5 г\nСыр моцарелла - 35 г\nСыр чеддер - 1,5 столовых ложек\nКуриное яйцо - 1 штука\nТортильи - 1 штука\nРастительное масло - по вкусу\nСоль - по вкусу\nПаприка - по вкусу\n\nИНСТРУКЦИЯ ПРИГОТОВЛЕНИЯ\nВремя приготовления 20 МИНУТ\nШаг 1 - Подготовить все ингредиенты.\nШаг 2 - Измельчить лук и зелень. Помидор нарезать на сектора, вырезать из них сердцевину с семенами, а филе помидоров нарезать небольшими кубиками.\nШаг 3 - Смешать овощи и зелень, добавив немного паприки.\nШаг 4 - Натереть моцареллу и чеддер на крупной терке.\nШаг 5 - Яйцо посолить и взбить вилкой.\nШаг 6 - На сковороде разогреть немного растительного масла и вылить взбитое яйцо.\nШаг 7 - Когда омлет схватится, выложить на него тортилью и перевернуть все вместе, чтобы тортилья оказалась внизу, а яйцо наверху.\nШаг 8 - Посыпать омлет тертым сыром, сверху на половину омлета выложить овощной салат.\nШаг 9 - Сложить тортилью пополам и прогреть на сковороде, чтобы сыр расплавился, а тортилья подрумянилась. Готовый сэндвич разрезать и сразу же есть.","#424345", 1));
        receptList.add(new Recept(2, "xinkaly", "Хинкали","ЭНЕРГЕТИЧЕСКАЯ ЦЕННОСТЬ НА ПОРЦИЮ\nКАЛОРИЙНОСТЬ - 513 ККАЛ\nБЕЛКИ - 26 ГРАММ\nЖИРЫ - 33 ГРАММ\nУГЛЕВОДЫ - 32 ГРАММ\n\n" +
                "ИНГРЕДИЕНТЫ На одну порцию\nПомидоры - 100 г\nКрасный лук - 35 г\nЗелень - 5 г\nСыр моцарелла - 35 г\nСыр чеддер - 1,5 столовых ложек\nКуриное яйцо - 1 штука\nТортильи - 1 штука\nРастительное масло - по вкусу\nСоль - по вкусу\nПаприка - по вкусу\n\nИНСТРУКЦИЯ ПРИГОТОВЛЕНИЯ\nВремя приготовления 20 МИНУТ\nШаг 1 - Подготовить все ингредиенты.\nШаг 2 - Измельчить лук и зелень. Помидор нарезать на сектора, вырезать из них сердцевину с семенами, а филе помидоров нарезать небольшими кубиками.\nШаг 3 - Смешать овощи и зелень, добавив немного паприки.\nШаг 4 - Натереть моцареллу и чеддер на крупной терке.\nШаг 5 - Яйцо посолить и взбить вилкой.\nШаг 6 - На сковороде разогреть немного растительного масла и вылить взбитое яйцо.\nШаг 7 - Когда омлет схватится, выложить на него тортилью и перевернуть все вместе, чтобы тортилья оказалась внизу, а яйцо наверху.\nШаг 8 - Посыпать омлет тертым сыром, сверху на половину омлета выложить овощной салат.\nШаг 9 - Сложить тортилью пополам и прогреть на сковороде, чтобы сыр расплавился, а тортилья подрумянилась. Готовый сэндвич разрезать и сразу же есть.","#424345", 2));

        fullReceptsList.addAll(receptList);

        setReceptRecycler(receptList);

        addCook = (ImageView)findViewById(R.id.addcook_plusik);
        settingBtn = (ImageView)findViewById(R.id.btn_setting);

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingBtnIntent = new Intent(HomeActivity.this, btnSetting_Activity.class);
                startActivity(settingBtnIntent);
            }
        });


        addCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addcook_plusikIntent = new Intent(HomeActivity.this, AddCook_Activity.class);
                startActivity(addcook_plusikIntent);
            }
        });

    }

    private void setReceptRecycler(List<Recept> receptList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        ReceptRecycler = findViewById(R.id.ReceptRecycler);
        ReceptRecycler.setLayoutManager(layoutManager);

        receptAdapter = new ReceptAdapter(this, receptList);
        ReceptRecycler.setAdapter(receptAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    public static void showReceptByCategory(int category){

        receptList.clear();
        receptList.addAll(fullReceptsList);

        List<Recept> filterRecept = new ArrayList<>();

        for(Recept c : receptList) {
            if(c.getCategory() == category)
                filterRecept.add(c);
        }

        receptList.clear();
        receptList.addAll(filterRecept);
        receptAdapter.notifyDataSetChanged();
    }
}