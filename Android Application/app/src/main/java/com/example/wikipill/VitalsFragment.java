package com.example.wikipill;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikipill.Math.Fft;
import com.example.wikipill.Math.Fft2;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class VitalsFragment extends Fragment {

    private static final AtomicBoolean processing = new AtomicBoolean(false);
    private SurfaceView preview = null;
    private static SurfaceHolder previewHolder = null;
    private static Camera camera = null;
    private static PowerManager.WakeLock wakeLock = null;
    FragmentManager fragmentManager;


    ProgressBar ProgHeart;
    public int ProgP = 0;
    public int inc = 0;

    public int Beats = 0;
    public double bufferAvgB = 0;

    private static long startTime = 0;
    private double SamplingFreq;

    private static Double[] RedBlueRatio;
    public int o2;
    double Stdr = 0;
    double Stdb = 0;
    double sumred = 0;
    double sumblue = 0;


    public double Gen, Agg, Hei, Wei;
    public double Q = 4.5;
    private static int SP = 0, DP = 0;

    public ArrayList<Double> GreenAvgList = new ArrayList<>();
    public ArrayList<Double> RedAvgList = new ArrayList<>();
    public ArrayList<Double> BlueAvgList = new ArrayList<>();
    public int counter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vitals, container, false);

        Hei = 160;
        Wei = 60;
        Agg = 20;
        Gen = 1;

        if (Gen == 1) {
            Q = 5;
        }

        preview = view.findViewById(R.id.preview);
        previewHolder = preview.getHolder();
        previewHolder.addCallback(surfaceCallback);
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        ProgHeart =  view.findViewById(R.id.VSPB);
        ProgHeart.setProgress(0);


        PowerManager pm = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");

        return view;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    @Override
    public void onResume() {
        super.onResume();

        wakeLock.acquire();

        camera = Camera.open();

        camera.setDisplayOrientation(90);

        startTime = System.currentTimeMillis();
    }
    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;

    }

    private Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void onPreviewFrame(byte[] data, Camera cam) {
            if (data == null) throw new NullPointerException();
            Camera.Size size = cam.getParameters().getPreviewSize();
            if (size == null) throw new NullPointerException();

            if (!processing.compareAndSet(false, true)) return;

            int width = size.width;
            int height = size.height;

            double GreenAvg;
            double RedAvg;
            double BlueAvg;

            GreenAvg = ImageProcessingVitals.decodeYUV420SPtoRedBlueGreenAvg(data.clone(), height, width, 3); //Getting Green intensity after applying image processing on frame data, 3 stands for green

            RedAvg = ImageProcessingVitals.decodeYUV420SPtoRedBlueGreenAvg(data.clone(), height, width, 1); //Getting Red intensity after applying image processing on frame data, 1 stands for red
            sumred = sumred + RedAvg; //Summing Red intensity for the whole period of recording which is 30 second

            BlueAvg = ImageProcessingVitals.decodeYUV420SPtoRedBlueGreenAvg(data.clone(), height, width, 2); //Getting Blue intensity after applying image processing on frame data, 2 stands for blue
            sumblue = sumblue + BlueAvg; //Summing Red intensity for the whole period of recording which is 30 second

            GreenAvgList.add(GreenAvg);
            RedAvgList.add(RedAvg);
            BlueAvgList.add(BlueAvg);

            ++counter; //counts the number of frames for the whole period of recording " 30 s "


            if (RedAvg < 200) {
                inc = 0;
                ProgP = inc;
                counter = 0;
                ProgHeart.setProgress(ProgP);
                processing.set(false);
            }


            long endTime = System.currentTimeMillis();
            double totalTimeInSecs = (endTime - startTime) / 1000d; //convert time to seconds to be compared with 30 seconds
            if (totalTimeInSecs >= 30) {

                Double[] Green = GreenAvgList.toArray(new Double[GreenAvgList.size()]);
                Double[] Red = RedAvgList.toArray(new Double[RedAvgList.size()]);
                Double[] Blue = BlueAvgList.toArray(new Double[BlueAvgList.size()]);

                SamplingFreq = (counter / totalTimeInSecs); //calculating sampling frequency

                double HRFreq = Fft.FFT(Green, counter, SamplingFreq);
                double bpm = (int) ceil(HRFreq * 60);
                double HR1Freq = Fft.FFT(Red, counter, SamplingFreq);
                double bpm1 = (int) ceil(HR1Freq * 60);



                double meanr = sumred / counter;
                double meanb = sumblue / counter;


                for (int i = 0; i < counter - 1; i++) {

                    Double bufferb = Blue[i];

                    Stdb = Stdb + ((bufferb - meanb) * (bufferb - meanb));

                    Double bufferr = Red[i];

                    Stdr = Stdr + ((bufferr - meanr) * (bufferr - meanr));

                }

                double varr = sqrt(Stdr / (counter - 1));
                double varb = sqrt(Stdb / (counter - 1));

                double R = (varr / meanr) / (varb / meanb);

                //estimating SPo2
                double spo2 = 100 - 5 * (R);

                o2 = (int) (spo2);


                if ((bpm > 45 || bpm < 200) ) {
                    if ((bpm1 > 45 || bpm1 < 200) ) {

                        bufferAvgB = (bpm + bpm1) / 2;

                    } else {
                        bufferAvgB = bpm;
                    }
                } else if ((bpm1 > 45 || bpm1 < 200) ) {

                    bufferAvgB = bpm1;

                }



                Beats = (int) bufferAvgB;

                double ROB = 18.5;
                double ET = (364.5 - 1.23 * Beats);
                double BSA = 0.007184 * (Math.pow(Wei, 0.425)) * (Math.pow(Hei, 0.725));
                double SV = (-6.6 + (0.25 * (ET - 35)) - (0.62 * Beats) + (40.4 * BSA) - (0.51 * Agg));
                double PP = SV / ((0.013 * Wei - 0.007 * Agg - 0.004 * Beats) + 1.307);
                double MPP = Q * ROB;


            }

            if ((Beats != 0)  && (o2 != 0) ) {
//                VitalsResult ldf = new VitalsResult ();
//                Bundle args = new Bundle();
//                args.putString("BPM", Integer.toString(Beats));
//                args.putString("SPO2", Integer.toString(o2));
//                ldf.setArguments(args);
//
//                getFragmentManager().beginTransaction().add(R.id.container_frag, ldf).commit();

                Bundle bundle = new Bundle();
                bundle.putString("BPM", Integer.toString(Beats));
                bundle.putString("SPO2", Integer.toString(o2));
                Fragment fragment = null;
                fragment = new VitalsResult();
                fragment.setArguments(bundle);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container_frag,fragment).commit();


            }

            if (RedAvg != 0) {
                ProgP = inc++ / 34;
                ProgHeart.setProgress(ProgP);
            }
            processing.set(false);
        }
    };

    private SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(previewHolder);
                camera.setPreviewCallback(previewCallback);
            } catch (Throwable t) {

            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

            Camera.Size size = getSmallestPreviewSize(width, height, parameters);
            if (size != null) {
                parameters.setPreviewSize(size.width, size.height);
            }

            camera.setParameters(parameters);
            camera.startPreview();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
        }
    };

    private static Camera.Size getSmallestPreviewSize(int width, int height, Camera.Parameters parameters) {
        Camera.Size result = null;

        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size;
                } else {
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;
                    if (newArea < resultArea) result = size;
                }
            }
        }

        return result;
    }




}