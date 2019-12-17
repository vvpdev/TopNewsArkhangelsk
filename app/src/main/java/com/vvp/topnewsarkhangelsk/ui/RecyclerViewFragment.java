package com.vvp.topnewsarkhangelsk.ui;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.vvp.topnewsarkhangelsk.R;
import com.vvp.topnewsarkhangelsk.adapters.RecyclerViewPostsAdapter;
import com.vvp.topnewsarkhangelsk.interfaces.RecViewPostsInterface;
import com.vvp.topnewsarkhangelsk.presenters.RecViewPostPresenter;
import com.vvp.topnewsarkhangelsk.utils.TextConstants;

import java.util.Objects;

public class RecyclerViewFragment extends MvpAppCompatFragment implements RecViewPostsInterface {


    @InjectPresenter
    RecViewPostPresenter presenter;

    //UI
    private RecyclerView recyclerViewPosts;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerViewPostsAdapter adapter;
    private ProgressBar progressLoad;


    // для уведомлений
    private Snackbar snackbarMessage;
    private TextView textError;


    private boolean startReceiver;



    //  приемник сообщений об изменении подключения к сети
    private final String BROADCAST_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    private IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if (BROADCAST_ACTION.equals(action)) {

                    //checkConnection();
            }
        }
    };




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewPosts = view.findViewById(R.id.recyclerViewPosts);
        textError = view.findViewById(R.id.textError);
        textError.setVisibility(View.GONE);       // пока скрываем
        progressLoad = view.findViewById(R.id.progressLoad);
        progressLoad.setVisibility(View.GONE);    // пока скрываем

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewPosts.setLayoutManager(layoutManager);
        adapter = new RecyclerViewPostsAdapter(presenter.getArrayPosts(), getActivity());

        //Objects.requireNonNull(getActivity()).registerReceiver(receiver,intentFilter);
    }




    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getActivity()).registerReceiver(receiver, intentFilter);
    }


    @Override
    public void onPause() {
        super.onPause();
        Objects.requireNonNull(getActivity()).unregisterReceiver(receiver);
    }



    // вывод сообщений через snackabr
    @Override
    public void showMessage(String message) {

        snackbarMessage = Snackbar.make(recyclerViewPosts, message, Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
        snackbarMessage.show();
    }


    // текст ошибки на TextView
    @Override
    public void showErrorOnTextView(boolean show, String message) {

        if (show){
            textError.setVisibility(View.VISIBLE);
            textError.setText(message);
        }

        else {
            textError.setVisibility(View.GONE);
        }
    }



    @Override
    public void initRecycclerView() {

        textError.setVisibility(View.GONE);

        // прицепляем адаптер
        recyclerViewPosts.setAdapter(adapter);
    }


    // управление прогресс баром
    @Override
    public void showProgressDialog(Boolean show) {

        if (show){
            progressLoad.setVisibility(View.VISIBLE);
        }
        else {
            progressLoad.setVisibility(View.GONE);
        }
    }



    @Override
    public void checkConnection() {

        // проверка подключения
        ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();

        if (networkInfo != null && Objects.requireNonNull(networkInfo).isConnected()){

            Log.i(TextConstants.TAG, "есть инет");

            presenter.loadData(true);
        }

        else {

            Log.i(TextConstants.TAG, "нет инета");
            presenter.loadData(false);
        }
    }


}
