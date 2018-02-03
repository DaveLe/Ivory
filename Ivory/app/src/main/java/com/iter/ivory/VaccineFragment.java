package com.iter.ivory;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iter.ivory.dummy.DummyContent;
import com.iter.ivory.dummy.DummyContent.DummyItem;

public class VaccineFragment extends Fragment {
    private static final String ARG_VIEW_PERSONAL = "arg_view_personal";
    private Boolean mViewPersonal = true;

    private OnListFragmentInteractionListener mListener;

    public VaccineFragment() {
    }

    // create a new VaccineFragment and send if view is personal or suggested
    public static VaccineFragment newInstance(boolean personal) {
        VaccineFragment fragment = new VaccineFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_VIEW_PERSONAL, personal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mViewPersonal = getArguments().getBoolean(ARG_VIEW_PERSONAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vaccine_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
            recyclerView.setAdapter(new MyVaccineRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
