package com.cmsh.greendzine.fragment;

import android.content.res.AssetManager;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cmsh.greendzine.R;
import com.cmsh.greendzine.adapter.RvEmployeeAdapter;
import com.cmsh.greendzine.databinding.FragmentEmployeeBinding;
import com.cmsh.greendzine.response.EmployeeResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFragment extends Fragment {
    FragmentEmployeeBinding binding;
    RvEmployeeAdapter adapter;
    Gson gson;
    List<EmployeeResponse> fullList = new ArrayList<>();
    List<EmployeeResponse> filteredList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee, container, false);
        loadData();
        setupRecyclerView();
        setupSearch();
        return binding.getRoot();
    }

    private void loadData() {
        String jsonData = loadJSON("employee.json");
        if (jsonData != null) {
            gson = new Gson();
            Type listType = new TypeToken<List<EmployeeResponse>>() {}.getType();
            fullList = gson.fromJson(jsonData, listType);
            filteredList.addAll(fullList);
        }
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.employeeList.setLayoutManager(linearLayoutManager);
        adapter = new RvEmployeeAdapter(filteredList);
        binding.employeeList.setAdapter(adapter);
    }

    private void setupSearch() {
        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                searchEmployees(s.toString());
            }
        });
    }

    private void searchEmployees(String query) {
        filteredList.clear();
        for (EmployeeResponse employee : fullList) {
            if (employee.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(employee);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private String loadJSON(String filename) {
        String json = null;
        try {
            AssetManager assetManager = getActivity().getAssets();
            InputStream inputStream = assetManager.open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}