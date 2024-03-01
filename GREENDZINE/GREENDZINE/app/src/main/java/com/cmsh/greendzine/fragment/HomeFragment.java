package com.cmsh.greendzine.fragment;

import android.content.res.AssetManager;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cmsh.greendzine.R;
import com.cmsh.greendzine.databinding.FragmentHomeBinding;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        try {
            loadData();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return binding.getRoot();
    }
    private void loadData() throws JSONException {
        String jsonData = loadJSON("dashboard.json");
        if (jsonData != null) {
            JSONObject jsonObject = new JSONObject(jsonData);
            binding.monday.setProgress(jsonObject.getInt("Monday"));
            binding.txtMonday.setText(jsonObject.getInt("Monday")+"%");

            binding.tuesday.setProgress(jsonObject.getInt("Tuesday"));
            binding.txtTuesday.setText(jsonObject.getInt("Tuesday")+"%");

            binding.wednesday.setProgress(jsonObject.getInt("Wednesday"));
            binding.txtWednesday.setText(jsonObject.getInt("Wednesday")+"%");

            binding.thursday.setProgress(jsonObject.getInt("Thursday"));
            binding.txtThursday.setText(jsonObject.getInt("Thursday")+"%");

            binding.friday.setProgress(jsonObject.getInt("Friday"));
            binding.txtFriday.setText(jsonObject.getInt("Friday")+"%");

            binding.saturday.setProgress(jsonObject.getInt("Saturday"));
            binding.txtSaturday.setText(jsonObject.getInt("Saturday")+"%");
        }
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