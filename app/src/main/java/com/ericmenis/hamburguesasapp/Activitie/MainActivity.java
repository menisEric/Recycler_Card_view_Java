package com.ericmenis.hamburguesasapp.Activitie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ericmenis.hamburguesasapp.Adapter.BrandAdapter;
import com.ericmenis.hamburguesasapp.Model.brand;
import com.ericmenis.hamburguesasapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private BrandAdapter adapter;

    private List<brand> brands;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        brands = this.getAllBrand();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new BrandAdapter(brands, R.layout.recycler_view_fruit_item, this ,new BrandAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(brand br, int position) {
                br.addQuantity(1);
                adapter.notifyItemChanged(position);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_brand:
                int position = brands.size();
                brands.add(position, new brand("Plum " + (++counter), "Brand added by the user", R.drawable.logomarcas, R.mipmap.ic_dinero, 0));
                adapter.notifyItemInserted(position);
                layoutManager.scrollToPosition(position);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<brand> getAllBrand() {
        return new ArrayList<brand>() {{
            add(new brand("Nike", "Nike description", R.drawable.logonike, R.mipmap.ic_nike, 0));
            add(new brand("Apple", "Apple description", R.drawable.logoapple, R.mipmap.ic_apple, 0));
            add(new brand("Adidas", "Adidas description", R.drawable.logoadidas, R.mipmap.ic_adidas, 0));
            add(new brand("Samsung", "Samsung description", R.drawable.logosamsung, R.mipmap.ic_samsung, 0));
            add(new brand("Chevrolet", "Chevrolet description", R.drawable.logochevrolet, R.mipmap.ic_chevrolet, 0));
            add(new brand("Intel", "Intel description", R.drawable.logointel, R.mipmap.ic_intel, 0));
        }};
    }


}
