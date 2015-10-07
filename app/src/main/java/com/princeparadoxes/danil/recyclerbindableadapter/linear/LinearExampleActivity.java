package com.princeparadoxes.danil.recyclerbindableadapter.linear;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.princeparadoxes.danil.recyclerbindableadapter.MainViewHolder;
import com.princeparadoxes.danil.recyclerbindableadapter.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class LinearExampleActivity extends AppCompatActivity implements MainViewHolder.ActionListener {

    private static final int COUNT_ITEMS = 10;
    @Bind(R.id.linear_example_recycle)
    RecyclerView linearExampleRecycler;
    @BindString(R.string.linear_example_header_1)
    String headerOne;
    @BindString(R.string.linear_example_header_2)
    String headerTwo;
    @BindString(R.string.linear_example_footer_1)
    String footerOne;
    @BindString(R.string.linear_example_footer_2)
    String footerTwo;
    private LinearExampleAdapter linearExampleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_example);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        linearExampleRecycler.setLayoutManager(layoutManager);
        linearExampleRecycler.setItemAnimator(new DefaultItemAnimator());

        linearExampleAdapter = new LinearExampleAdapter(this, layoutManager);
        linearExampleAdapter.setActionListener(this);
        linearExampleAdapter.addHeader(inflateHeaderFooter(headerOne));
        linearExampleAdapter.addHeader(inflateHeaderFooter(headerTwo));
        linearExampleAdapter.addFooter(inflateHeaderFooter(footerOne));
        linearExampleAdapter.addFooter(inflateHeaderFooter(footerTwo));
        linearExampleRecycler.setAdapter(linearExampleAdapter);
    }

    private View inflateHeaderFooter(String tittle) {
        CardView headerFooter = (CardView) getLayoutInflater().inflate(
                R.layout.linear_example_header, linearExampleRecycler, false);
        headerFooter.setUseCompatPadding(true);
        TextView tittleView = (TextView) headerFooter.findViewById(R.id.linear_example_header_tittle);
        View addButton = headerFooter.findViewById(R.id.linear_example_header_add);
        View clearButton = headerFooter.findViewById(R.id.linear_example_header_clear);
        tittleView.setText(tittle);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearExampleAdapter.add(linearExampleAdapter.getRealItemCount());
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearExampleAdapter.clear();
            }
        });
        return headerFooter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < COUNT_ITEMS; i++) {
            list.add(i);
        }
        linearExampleAdapter.addAll(list);
    }

    @Override
    public void onMoveToTop(int position) {
        linearExampleAdapter.moveChildTo(position, 0);
    }

    @Override
    public void remove(int position) {
        linearExampleAdapter.removeChild(position);
    }

    @Override
    public void up(int position) {
        linearExampleAdapter.moveChildTo(position, position - 1);
    }

    @Override
    public void down(int position) {
        linearExampleAdapter.moveChildTo(position, position + 1);
    }
}
