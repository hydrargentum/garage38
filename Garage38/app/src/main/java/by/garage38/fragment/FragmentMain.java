package by.garage38.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.garage38.Const;
import by.garage38.R;


public class FragmentMain extends Fragment {

    ViewHolder mVh;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static FragmentMain newInstance() {
        FragmentMain fragment = new FragmentMain();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentMain() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d(FragmentMain.class.getSimpleName(),"onCreate");
    }


    private static class ViewHolder {
        WebView  webView;
        Button   refreshButton;
        TextView textView;
        ProgressBar progress;

        public ViewHolder(View rootContainer)
        {
            webView = (WebView) rootContainer.findViewById(R.id.webView);
            refreshButton = (Button) rootContainer.findViewById(R.id.btnRefresh);
            textView = (TextView) rootContainer.findViewById(R.id.txtRefreshTime);
            progress = (ProgressBar) rootContainer.findViewById(R.id.progress);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_main, container, false);
        mVh = new ViewHolder(content);
        mVh.webView.getSettings().setJavaScriptEnabled(true);
        mVh.webView.loadUrl(Const.MAINPAGE_URL);
        mVh.webView.setWebViewClient(webViewClient);

        mVh.refreshButton.setOnClickListener(onRefreshClickListener);
        return content;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mVh = null;
    }

    View.OnClickListener onRefreshClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(mVh != null)
            {
                mVh.webView.reload();
            }
        }
    };



    WebViewClient webViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
            if(mVh != null)
            {
                mVh.progress.setVisibility(View.VISIBLE);
                //ViewUtil.crossfadeTo(view, mViewHolder.progress, AppEnvironment.getShortAnimationTime());
            }
        };

        @Override
        public void onPageFinished(WebView view, String url) {
            if(mVh != null)
            {
                mVh.progress.setVisibility(View.GONE);

                String datetime = DATE_FORMAT.format(new Date());
                String datetimeResult = getResources().getText(R.string.btn_refresh_time).toString();

                mVh.textView.setText(String.format(datetimeResult, datetime));

                //ViewUtil.crossfadeTo(mViewHolder.progress, view, AppEnvironment.getShortAnimationTime());

                //if(url.contains(SUCCESS_URL))
                //{
                //    view.loadUrl("javascript:window.api.getHTML(document.getElementsByTagName('body')[0].innerHTML);");
                //}
            }
        };
    };
}
