package com.example.todolist.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.todolist.R;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SettingFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button wifiBtn;
    private Button onOffBtn;
    private Button brightnessBtn;
    private static final int CAMERA_REQUEST = 50;
    private boolean flashLightStatus = false;



    public SettingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.setting_fragment,container,false);

        wifiBtn = view.findViewById(R.id.wifiButton);
        onOffBtn = view.findViewById(R.id.on_off_Button);
        brightnessBtn = view.findViewById(R.id.brightnessButton);
        wifiBtn.setOnClickListener(this);
        onOffBtn.setOnClickListener(this);

        final boolean hasCameraFlash = getActivity().getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean isEnabled = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;

        onOffBtn.setText("Light ON");
        onOffBtn.setEnabled(!isEnabled);
//        imageFlashlight.setEnabled(isEnabled);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wifiButton:
                Toast.makeText(getContext(), "Wifi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.on_off_Button:
                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA},
                        CAMERA_REQUEST);
                String text = onOffBtn.getText().toString();
                if(text.equals("Light ON")){
                    flashLightOn();
                    onOffBtn.setText("Light Off");
                }else if(text.equals("Light Off")){
                    flashLightOff();
                    onOffBtn.setText("Light ON");
                }
                break;
            case R.id.brightnessButton:
                Toast.makeText(getContext(), "Working!!", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            flashLightStatus = true;
//            imageFlashlight.setImageResource(R.drawable.btn_switch_on);
        } catch (CameraAccessException e) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager)getActivity().getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false);
            }
            flashLightStatus = false;
//            imageFlashlight.setImageResource(R.drawable.btn_switch_off);
        } catch (CameraAccessException e) {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case CAMERA_REQUEST :
                if (grantResults.length > 0  &&  grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    onOffBtn.setEnabled(false);

//                    imageFlashlight.setEnabled(true);
                } else {
                    Toast.makeText(getContext(), "Permission Denied for the Camera", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
