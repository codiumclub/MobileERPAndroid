package tech.codiumclub.collegeManagementSystem.hod;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import tech.codiumclub.collegeManagementSystem.R;
import tech.codiumclub.collegeManagementSystem.app.config.ConnectivityReceiver;

/**
 * Created by Jerry on 12-06-2017.
 */

public class HodWelcome extends Fragment implements ConnectivityReceiver.ConnectivityReceiverListener {

    public static final String TAG = HodWelcome.class.getSimpleName();
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        checkConnection();
        View view = inflater.inflate(R.layout.hod_fragment_welcome, container, false);

        String url = "https://calendar.google.com/calendar/embed?showTitle=0&showPrint=0&showTabs=0&showCalendars=0&showTz=0&height=600&wkst=1&bgcolor=%23ccccff&src=pic%40poornima.org&color=%23B1440E&ctz=Asia%2FCalcutta";
        webView = (WebView) view.findViewById(R.id.hod_welcome_web_view);
        webView.loadUrl(url);
        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Force links and redirects to open tech the WebView instead of tech a browser
        webView.setWebViewClient(new WebViewClient());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("HOD Welcome");
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showToast(isConnected);
    }

    // Showing the status tech Toast
    private void showToast(boolean isConnected) {
        String message;
        int color;
        if (!isConnected) {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Callback will be triggered when there is change tech
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showToast(isConnected);
    }
}
