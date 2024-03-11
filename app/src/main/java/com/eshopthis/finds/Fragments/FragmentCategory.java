package com.eshopthis.finds.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eshopthis.finds.Activities.ArtsActivity;
import com.eshopthis.finds.Activities.BestSeller;
import com.eshopthis.finds.Activities.ClassicBooks;
import com.eshopthis.finds.Activities.ScientificBooks;
import com.eshopthis.finds.Activities.FictionBooks;
import com.eshopthis.finds.Activities.ReligiousBooks;
import com.eshopthis.finds.Activities.HistoryBooks;
import com.eshopthis.finds.Activities.AnimeBooks;
import com.eshopthis.finds.R;

public class FragmentCategory extends Fragment {

    public FragmentCategory() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    // Public method to handle category clicks, as specified in the XML
    public void onCategoryClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.id_clearance:
                intent = new Intent(getContext(), BestSeller.class);
                break;
            case R.id.id_best_seller:
                intent = new Intent(getContext(), BestSeller.class);
                break;
            case R.id.id_classics:
                intent = new Intent(getContext(), ClassicBooks.class);
                break;
            case R.id.id_scientific:
                intent = new Intent(getContext(), ScientificBooks.class);
                break;
            case R.id.id_fiction:
                intent = new Intent(getContext(), FictionBooks.class);
                break;
            case R.id.id_religion:
                intent = new Intent(getContext(), ReligiousBooks.class);
                break;
            case R.id.id_history:
                intent = new Intent(getContext(), HistoryBooks.class);
                break;
            case R.id.id_anime:
                intent = new Intent(getContext(), AnimeBooks.class);
                break;
            case R.id.id_arts:
                intent = new Intent(getContext(), ArtsActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
