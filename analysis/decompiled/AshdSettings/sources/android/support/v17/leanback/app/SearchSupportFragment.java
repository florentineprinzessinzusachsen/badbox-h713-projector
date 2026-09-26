package android.support.v17.leanback.app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.speech.SpeechRecognizer;
import android.support.v17.leanback.R;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.SearchBar;
import android.support.v17.leanback.widget.SearchOrbView;
import android.support.v17.leanback.widget.SpeechRecognitionCallback;
import android.support.v17.leanback.widget.VerticalGridView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.CompletionInfo;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SearchSupportFragment extends Fragment {
    private static final String ARG_PREFIX = SearchSupportFragment.class.getCanonicalName();
    private static final String ARG_QUERY = ARG_PREFIX + ".query";
    private static final String ARG_TITLE = ARG_PREFIX + ".title";
    static final int AUDIO_PERMISSION_REQUEST_CODE = 0;
    static final boolean DEBUG = false;
    private static final String EXTRA_LEANBACK_BADGE_PRESENT = "LEANBACK_BADGE_PRESENT";
    static final int QUERY_COMPLETE = 2;
    static final int RESULTS_CHANGED = 1;
    static final long SPEECH_RECOGNITION_DELAY_MS = 300;
    static final String TAG = "SearchSupportFragment";
    private Drawable mBadgeDrawable;
    private ExternalQuery mExternalQuery;
    private boolean mIsPaused;
    private OnItemViewClickedListener mOnItemViewClickedListener;
    OnItemViewSelectedListener mOnItemViewSelectedListener;
    private boolean mPendingStartRecognitionWhenPaused;
    SearchResultProvider mProvider;
    ObjectAdapter mResultAdapter;
    RowsSupportFragment mRowsSupportFragment;
    SearchBar mSearchBar;
    private SpeechRecognitionCallback mSpeechRecognitionCallback;
    private SpeechRecognizer mSpeechRecognizer;
    int mStatus;
    private String mTitle;
    final ObjectAdapter.DataObserver mAdapterObserver = new ObjectAdapter.DataObserver() { // from class: android.support.v17.leanback.app.SearchSupportFragment.1
        @Override // android.support.v17.leanback.widget.ObjectAdapter.DataObserver
        public void onChanged() {
            SearchSupportFragment.this.mHandler.removeCallbacks(SearchSupportFragment.this.mResultsChangedCallback);
            SearchSupportFragment.this.mHandler.post(SearchSupportFragment.this.mResultsChangedCallback);
        }
    };
    final Handler mHandler = new Handler();
    final Runnable mResultsChangedCallback = new Runnable() { // from class: android.support.v17.leanback.app.SearchSupportFragment.2
        @Override // java.lang.Runnable
        public void run() {
            if (SearchSupportFragment.this.mRowsSupportFragment != null && SearchSupportFragment.this.mRowsSupportFragment.getAdapter() != SearchSupportFragment.this.mResultAdapter && (SearchSupportFragment.this.mRowsSupportFragment.getAdapter() != null || SearchSupportFragment.this.mResultAdapter.size() != 0)) {
                SearchSupportFragment.this.mRowsSupportFragment.setAdapter(SearchSupportFragment.this.mResultAdapter);
                SearchSupportFragment.this.mRowsSupportFragment.setSelectedPosition(0);
            }
            SearchSupportFragment.this.updateSearchBarVisibility();
            SearchSupportFragment.this.mStatus |= 1;
            if ((SearchSupportFragment.this.mStatus & 2) != 0) {
                SearchSupportFragment.this.updateFocus();
            }
            SearchSupportFragment.this.updateSearchBarNextFocusId();
        }
    };
    private final Runnable mSetSearchResultProvider = new Runnable() { // from class: android.support.v17.leanback.app.SearchSupportFragment.3
        @Override // java.lang.Runnable
        public void run() {
            if (SearchSupportFragment.this.mRowsSupportFragment == null) {
                return;
            }
            ObjectAdapter resultsAdapter = SearchSupportFragment.this.mProvider.getResultsAdapter();
            if (resultsAdapter != SearchSupportFragment.this.mResultAdapter) {
                boolean z = SearchSupportFragment.this.mResultAdapter == null;
                SearchSupportFragment.this.releaseAdapter();
                SearchSupportFragment.this.mResultAdapter = resultsAdapter;
                if (SearchSupportFragment.this.mResultAdapter != null) {
                    SearchSupportFragment.this.mResultAdapter.registerObserver(SearchSupportFragment.this.mAdapterObserver);
                }
                if (!z || (SearchSupportFragment.this.mResultAdapter != null && SearchSupportFragment.this.mResultAdapter.size() != 0)) {
                    SearchSupportFragment.this.mRowsSupportFragment.setAdapter(SearchSupportFragment.this.mResultAdapter);
                }
                SearchSupportFragment.this.executePendingQuery();
            }
            SearchSupportFragment.this.updateSearchBarNextFocusId();
            if (SearchSupportFragment.this.mAutoStartRecognition) {
                SearchSupportFragment.this.mHandler.removeCallbacks(SearchSupportFragment.this.mStartRecognitionRunnable);
                SearchSupportFragment.this.mHandler.postDelayed(SearchSupportFragment.this.mStartRecognitionRunnable, SearchSupportFragment.SPEECH_RECOGNITION_DELAY_MS);
            } else {
                SearchSupportFragment.this.updateFocus();
            }
        }
    };
    final Runnable mStartRecognitionRunnable = new Runnable() { // from class: android.support.v17.leanback.app.SearchSupportFragment.4
        @Override // java.lang.Runnable
        public void run() {
            SearchSupportFragment.this.mAutoStartRecognition = false;
            SearchSupportFragment.this.mSearchBar.startRecognition();
        }
    };
    String mPendingQuery = null;
    boolean mAutoStartRecognition = true;
    private SearchBar.SearchBarPermissionListener mPermissionListener = new SearchBar.SearchBarPermissionListener() { // from class: android.support.v17.leanback.app.SearchSupportFragment.5
        @Override // android.support.v17.leanback.widget.SearchBar.SearchBarPermissionListener
        public void requestAudioPermission() {
            PermissionHelper.requestPermissions(SearchSupportFragment.this, new String[]{"android.permission.RECORD_AUDIO"}, 0);
        }
    };

    public interface SearchResultProvider {
        ObjectAdapter getResultsAdapter();

        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    @Override // android.support.v4.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 0 && strArr.length > 0 && strArr[0].equals("android.permission.RECORD_AUDIO") && iArr[0] == 0) {
            startRecognition();
        }
    }

    public static Bundle createArgs(Bundle bundle, String str) {
        return createArgs(bundle, str, null);
    }

    public static Bundle createArgs(Bundle bundle, String str, String str2) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(ARG_QUERY, str);
        bundle.putString(ARG_TITLE, str2);
        return bundle;
    }

    public static SearchSupportFragment newInstance(String str) {
        SearchSupportFragment searchSupportFragment = new SearchSupportFragment();
        searchSupportFragment.setArguments(createArgs(null, str));
        return searchSupportFragment;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        if (this.mAutoStartRecognition) {
            this.mAutoStartRecognition = bundle == null;
        }
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.lb_search_fragment, viewGroup, false);
        this.mSearchBar = (SearchBar) ((FrameLayout) viewInflate.findViewById(R.id.lb_search_frame)).findViewById(R.id.lb_search_bar);
        this.mSearchBar.setSearchBarListener(new SearchBar.SearchBarListener() { // from class: android.support.v17.leanback.app.SearchSupportFragment.6
            @Override // android.support.v17.leanback.widget.SearchBar.SearchBarListener
            public void onSearchQueryChange(String str) {
                if (SearchSupportFragment.this.mProvider != null) {
                    SearchSupportFragment.this.retrieveResults(str);
                } else {
                    SearchSupportFragment.this.mPendingQuery = str;
                }
            }

            @Override // android.support.v17.leanback.widget.SearchBar.SearchBarListener
            public void onSearchQuerySubmit(String str) {
                SearchSupportFragment.this.submitQuery(str);
            }

            @Override // android.support.v17.leanback.widget.SearchBar.SearchBarListener
            public void onKeyboardDismiss(String str) {
                SearchSupportFragment.this.queryComplete();
            }
        });
        this.mSearchBar.setSpeechRecognitionCallback(this.mSpeechRecognitionCallback);
        this.mSearchBar.setPermissionListener(this.mPermissionListener);
        applyExternalQuery();
        readArguments(getArguments());
        if (this.mBadgeDrawable != null) {
            setBadgeDrawable(this.mBadgeDrawable);
        }
        if (this.mTitle != null) {
            setTitle(this.mTitle);
        }
        if (getChildFragmentManager().findFragmentById(R.id.lb_results_frame) == null) {
            this.mRowsSupportFragment = new RowsSupportFragment();
            getChildFragmentManager().beginTransaction().replace(R.id.lb_results_frame, this.mRowsSupportFragment).commit();
        } else {
            this.mRowsSupportFragment = (RowsSupportFragment) getChildFragmentManager().findFragmentById(R.id.lb_results_frame);
        }
        this.mRowsSupportFragment.setOnItemViewSelectedListener(new OnItemViewSelectedListener() { // from class: android.support.v17.leanback.app.SearchSupportFragment.7
            @Override // android.support.v17.leanback.widget.BaseOnItemViewSelectedListener
            public void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                SearchSupportFragment.this.updateSearchBarVisibility();
                if (SearchSupportFragment.this.mOnItemViewSelectedListener != null) {
                    SearchSupportFragment.this.mOnItemViewSelectedListener.onItemSelected(viewHolder, obj, viewHolder2, row);
                }
            }
        });
        this.mRowsSupportFragment.setOnItemViewClickedListener(this.mOnItemViewClickedListener);
        this.mRowsSupportFragment.setExpand(true);
        if (this.mProvider != null) {
            onSetSearchResultProvider();
        }
        return viewInflate;
    }

    private void resultsAvailable() {
        if ((this.mStatus & 2) != 0) {
            focusOnResults();
        }
        updateSearchBarNextFocusId();
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        VerticalGridView verticalGridView = this.mRowsSupportFragment.getVerticalGridView();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.lb_search_browse_rows_align_top);
        verticalGridView.setItemAlignmentOffset(0);
        verticalGridView.setItemAlignmentOffsetPercent(-1.0f);
        verticalGridView.setWindowAlignmentOffset(dimensionPixelSize);
        verticalGridView.setWindowAlignmentOffsetPercent(-1.0f);
        verticalGridView.setWindowAlignment(0);
        verticalGridView.setFocusable(false);
        verticalGridView.setFocusableInTouchMode(false);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.mIsPaused = false;
        if (this.mSpeechRecognitionCallback == null && this.mSpeechRecognizer == null) {
            this.mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());
            this.mSearchBar.setSpeechRecognizer(this.mSpeechRecognizer);
        }
        if (this.mPendingStartRecognitionWhenPaused) {
            this.mPendingStartRecognitionWhenPaused = false;
            this.mSearchBar.startRecognition();
        } else {
            this.mSearchBar.stopRecognition();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        releaseRecognizer();
        this.mIsPaused = true;
        super.onPause();
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        releaseAdapter();
        super.onDestroy();
    }

    public RowsSupportFragment getRowsSupportFragment() {
        return this.mRowsSupportFragment;
    }

    private void releaseRecognizer() {
        if (this.mSpeechRecognizer != null) {
            this.mSearchBar.setSpeechRecognizer(null);
            this.mSpeechRecognizer.destroy();
            this.mSpeechRecognizer = null;
        }
    }

    public void startRecognition() {
        if (this.mIsPaused) {
            this.mPendingStartRecognitionWhenPaused = true;
        } else {
            this.mSearchBar.startRecognition();
        }
    }

    public void setSearchResultProvider(SearchResultProvider searchResultProvider) {
        if (this.mProvider != searchResultProvider) {
            this.mProvider = searchResultProvider;
            onSetSearchResultProvider();
        }
    }

    public void setOnItemViewSelectedListener(OnItemViewSelectedListener onItemViewSelectedListener) {
        this.mOnItemViewSelectedListener = onItemViewSelectedListener;
    }

    public void setOnItemViewClickedListener(OnItemViewClickedListener onItemViewClickedListener) {
        if (onItemViewClickedListener != this.mOnItemViewClickedListener) {
            this.mOnItemViewClickedListener = onItemViewClickedListener;
            if (this.mRowsSupportFragment != null) {
                this.mRowsSupportFragment.setOnItemViewClickedListener(this.mOnItemViewClickedListener);
            }
        }
    }

    public void setTitle(String str) {
        this.mTitle = str;
        if (this.mSearchBar != null) {
            this.mSearchBar.setTitle(str);
        }
    }

    public String getTitle() {
        if (this.mSearchBar != null) {
            return this.mSearchBar.getTitle();
        }
        return null;
    }

    public void setBadgeDrawable(Drawable drawable) {
        this.mBadgeDrawable = drawable;
        if (this.mSearchBar != null) {
            this.mSearchBar.setBadgeDrawable(drawable);
        }
    }

    public Drawable getBadgeDrawable() {
        if (this.mSearchBar != null) {
            return this.mSearchBar.getBadgeDrawable();
        }
        return null;
    }

    public void setSearchAffordanceColors(SearchOrbView.Colors colors) {
        if (this.mSearchBar != null) {
            this.mSearchBar.setSearchAffordanceColors(colors);
        }
    }

    public void setSearchAffordanceColorsInListening(SearchOrbView.Colors colors) {
        if (this.mSearchBar != null) {
            this.mSearchBar.setSearchAffordanceColorsInListening(colors);
        }
    }

    public void displayCompletions(List<String> list) {
        this.mSearchBar.displayCompletions(list);
    }

    public void displayCompletions(CompletionInfo[] completionInfoArr) {
        this.mSearchBar.displayCompletions(completionInfoArr);
    }

    public void setSpeechRecognitionCallback(SpeechRecognitionCallback speechRecognitionCallback) {
        this.mSpeechRecognitionCallback = speechRecognitionCallback;
        if (this.mSearchBar != null) {
            this.mSearchBar.setSpeechRecognitionCallback(this.mSpeechRecognitionCallback);
        }
        if (speechRecognitionCallback != null) {
            releaseRecognizer();
        }
    }

    public void setSearchQuery(String str, boolean z) {
        if (str == null) {
            return;
        }
        this.mExternalQuery = new ExternalQuery(str, z);
        applyExternalQuery();
        if (this.mAutoStartRecognition) {
            this.mAutoStartRecognition = false;
            this.mHandler.removeCallbacks(this.mStartRecognitionRunnable);
        }
    }

    public void setSearchQuery(Intent intent, boolean z) {
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
        if (stringArrayListExtra == null || stringArrayListExtra.size() <= 0) {
            return;
        }
        setSearchQuery(stringArrayListExtra.get(0), z);
    }

    public Intent getRecognizerIntent() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.PARTIAL_RESULTS", true);
        if (this.mSearchBar != null && this.mSearchBar.getHint() != null) {
            intent.putExtra("android.speech.extra.PROMPT", this.mSearchBar.getHint());
        }
        intent.putExtra(EXTRA_LEANBACK_BADGE_PRESENT, this.mBadgeDrawable != null);
        return intent;
    }

    void retrieveResults(String str) {
        if (this.mProvider.onQueryTextChange(str)) {
            this.mStatus &= -3;
        }
    }

    void submitQuery(String str) {
        queryComplete();
        if (this.mProvider != null) {
            this.mProvider.onQueryTextSubmit(str);
        }
    }

    void queryComplete() {
        this.mStatus |= 2;
        focusOnResults();
    }

    void updateSearchBarVisibility() {
        this.mSearchBar.setVisibility(((this.mRowsSupportFragment != null ? this.mRowsSupportFragment.getSelectedPosition() : -1) <= 0 || this.mResultAdapter == null || this.mResultAdapter.size() == 0) ? 0 : 8);
    }

    void updateSearchBarNextFocusId() {
        if (this.mSearchBar == null || this.mResultAdapter == null) {
            return;
        }
        this.mSearchBar.setNextFocusDownId((this.mResultAdapter.size() == 0 || this.mRowsSupportFragment == null || this.mRowsSupportFragment.getVerticalGridView() == null) ? 0 : this.mRowsSupportFragment.getVerticalGridView().getId());
    }

    void updateFocus() {
        if (this.mResultAdapter != null && this.mResultAdapter.size() > 0 && this.mRowsSupportFragment != null && this.mRowsSupportFragment.getAdapter() == this.mResultAdapter) {
            focusOnResults();
        } else {
            this.mSearchBar.requestFocus();
        }
    }

    private void focusOnResults() {
        if (this.mRowsSupportFragment == null || this.mRowsSupportFragment.getVerticalGridView() == null || this.mResultAdapter.size() == 0 || !this.mRowsSupportFragment.getVerticalGridView().requestFocus()) {
            return;
        }
        this.mStatus &= -2;
    }

    private void onSetSearchResultProvider() {
        this.mHandler.removeCallbacks(this.mSetSearchResultProvider);
        this.mHandler.post(this.mSetSearchResultProvider);
    }

    void releaseAdapter() {
        if (this.mResultAdapter != null) {
            this.mResultAdapter.unregisterObserver(this.mAdapterObserver);
            this.mResultAdapter = null;
        }
    }

    void executePendingQuery() {
        if (this.mPendingQuery == null || this.mResultAdapter == null) {
            return;
        }
        String str = this.mPendingQuery;
        this.mPendingQuery = null;
        retrieveResults(str);
    }

    private void applyExternalQuery() {
        if (this.mExternalQuery == null || this.mSearchBar == null) {
            return;
        }
        this.mSearchBar.setSearchQuery(this.mExternalQuery.mQuery);
        if (this.mExternalQuery.mSubmit) {
            submitQuery(this.mExternalQuery.mQuery);
        }
        this.mExternalQuery = null;
    }

    private void readArguments(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (bundle.containsKey(ARG_QUERY)) {
            setSearchQuery(bundle.getString(ARG_QUERY));
        }
        if (bundle.containsKey(ARG_TITLE)) {
            setTitle(bundle.getString(ARG_TITLE));
        }
    }

    private void setSearchQuery(String str) {
        this.mSearchBar.setSearchQuery(str);
    }

    static class ExternalQuery {
        String mQuery;
        boolean mSubmit;

        ExternalQuery(String str, boolean z) {
            this.mQuery = str;
            this.mSubmit = z;
        }
    }
}
