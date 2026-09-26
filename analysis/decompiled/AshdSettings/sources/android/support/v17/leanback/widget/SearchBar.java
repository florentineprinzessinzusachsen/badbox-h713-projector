package android.support.v17.leanback.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.v17.leanback.R;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SearchBar extends RelativeLayout {
    static final boolean DEBUG = false;
    static final int DEFAULT_PRIORITY = 1;
    static final float DEFAULT_RATE = 1.0f;
    static final int DO_NOT_LOOP = 0;
    static final float FULL_LEFT_VOLUME = 1.0f;
    static final float FULL_RIGHT_VOLUME = 1.0f;
    static final String TAG = "SearchBar";
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;
    private AudioManager mAudioManager;
    boolean mAutoStartRecognition;
    private int mBackgroundAlpha;
    private int mBackgroundSpeechAlpha;
    private Drawable mBadgeDrawable;
    private ImageView mBadgeView;
    private Drawable mBarBackground;
    private int mBarHeight;
    private final Context mContext;
    final Handler mHandler;
    private String mHint;
    private final InputMethodManager mInputMethodManager;
    private boolean mListening;
    private SearchBarPermissionListener mPermissionListener;
    boolean mRecognizing;
    SearchBarListener mSearchBarListener;
    String mSearchQuery;
    SearchEditText mSearchTextEditor;
    SparseIntArray mSoundMap;
    SoundPool mSoundPool;
    SpeechOrbView mSpeechOrbView;
    private SpeechRecognitionCallback mSpeechRecognitionCallback;
    private SpeechRecognizer mSpeechRecognizer;
    private final int mTextColor;
    private final int mTextColorSpeechMode;
    private final int mTextHintColor;
    private final int mTextHintColorSpeechMode;
    private String mTitle;

    public interface SearchBarListener {
        void onKeyboardDismiss(String str);

        void onSearchQueryChange(String str);

        void onSearchQuerySubmit(String str);
    }

    public interface SearchBarPermissionListener {
        void requestAudioPermission();
    }

    public SearchBar(Context context) {
        this(context, null);
    }

    public SearchBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SearchBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: android.support.v17.leanback.widget.SearchBar.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i2) {
                SearchBar.this.stopRecognition();
            }
        };
        this.mHandler = new Handler();
        this.mAutoStartRecognition = false;
        this.mSoundMap = new SparseIntArray();
        this.mRecognizing = false;
        this.mContext = context;
        Resources resources = getResources();
        LayoutInflater.from(getContext()).inflate(R.layout.lb_search_bar, (ViewGroup) this, true);
        this.mBarHeight = getResources().getDimensionPixelSize(R.dimen.lb_search_bar_height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.mBarHeight);
        layoutParams.addRule(10, -1);
        setLayoutParams(layoutParams);
        setBackgroundColor(0);
        setClipChildren(false);
        this.mSearchQuery = "";
        this.mInputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        this.mTextColorSpeechMode = resources.getColor(R.color.lb_search_bar_text_speech_mode);
        this.mTextColor = resources.getColor(R.color.lb_search_bar_text);
        this.mBackgroundSpeechAlpha = resources.getInteger(R.integer.lb_search_bar_speech_mode_background_alpha);
        this.mBackgroundAlpha = resources.getInteger(R.integer.lb_search_bar_text_mode_background_alpha);
        this.mTextHintColorSpeechMode = resources.getColor(R.color.lb_search_bar_hint_speech_mode);
        this.mTextHintColor = resources.getColor(R.color.lb_search_bar_hint);
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mBarBackground = ((RelativeLayout) findViewById(R.id.lb_search_bar_items)).getBackground();
        this.mSearchTextEditor = (SearchEditText) findViewById(R.id.lb_search_text_editor);
        this.mBadgeView = (ImageView) findViewById(R.id.lb_search_bar_badge);
        if (this.mBadgeDrawable != null) {
            this.mBadgeView.setImageDrawable(this.mBadgeDrawable);
        }
        this.mSearchTextEditor.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: android.support.v17.leanback.widget.SearchBar.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    SearchBar.this.showNativeKeyboard();
                }
                SearchBar.this.updateUi(z);
            }
        });
        final Runnable runnable = new Runnable() { // from class: android.support.v17.leanback.widget.SearchBar.3
            @Override // java.lang.Runnable
            public void run() {
                SearchBar.this.setSearchQueryInternal(SearchBar.this.mSearchTextEditor.getText().toString());
            }
        };
        this.mSearchTextEditor.addTextChangedListener(new TextWatcher() { // from class: android.support.v17.leanback.widget.SearchBar.4
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (SearchBar.this.mRecognizing) {
                    return;
                }
                SearchBar.this.mHandler.removeCallbacks(runnable);
                SearchBar.this.mHandler.post(runnable);
            }
        });
        this.mSearchTextEditor.setOnKeyboardDismissListener(new SearchEditText.OnKeyboardDismissListener() { // from class: android.support.v17.leanback.widget.SearchBar.5
            @Override // android.support.v17.leanback.widget.SearchEditText.OnKeyboardDismissListener
            public void onKeyboardDismiss() {
                if (SearchBar.this.mSearchBarListener != null) {
                    SearchBar.this.mSearchBarListener.onKeyboardDismiss(SearchBar.this.mSearchQuery);
                }
            }
        });
        this.mSearchTextEditor.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: android.support.v17.leanback.widget.SearchBar.6
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((3 == i || i == 0) && SearchBar.this.mSearchBarListener != null) {
                    SearchBar.this.hideNativeKeyboard();
                    SearchBar.this.mHandler.postDelayed(new Runnable() { // from class: android.support.v17.leanback.widget.SearchBar.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SearchBar.this.submitQuery();
                        }
                    }, 500L);
                    return true;
                }
                if (1 == i && SearchBar.this.mSearchBarListener != null) {
                    SearchBar.this.hideNativeKeyboard();
                    SearchBar.this.mHandler.postDelayed(new Runnable() { // from class: android.support.v17.leanback.widget.SearchBar.6.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SearchBar.this.mSearchBarListener.onKeyboardDismiss(SearchBar.this.mSearchQuery);
                        }
                    }, 500L);
                    return true;
                }
                if (2 != i) {
                    return false;
                }
                SearchBar.this.hideNativeKeyboard();
                SearchBar.this.mHandler.postDelayed(new Runnable() { // from class: android.support.v17.leanback.widget.SearchBar.6.3
                    @Override // java.lang.Runnable
                    public void run() {
                        SearchBar.this.mAutoStartRecognition = true;
                        SearchBar.this.mSpeechOrbView.requestFocus();
                    }
                }, 500L);
                return true;
            }
        });
        this.mSearchTextEditor.setPrivateImeOptions("EscapeNorth=1;VoiceDismiss=1;");
        this.mSpeechOrbView = (SpeechOrbView) findViewById(R.id.lb_search_bar_speech_orb);
        this.mSpeechOrbView.setOnOrbClickedListener(new View.OnClickListener() { // from class: android.support.v17.leanback.widget.SearchBar.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchBar.this.toggleRecognition();
            }
        });
        this.mSpeechOrbView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: android.support.v17.leanback.widget.SearchBar.8
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    SearchBar.this.hideNativeKeyboard();
                    if (SearchBar.this.mAutoStartRecognition) {
                        SearchBar.this.startRecognition();
                        SearchBar.this.mAutoStartRecognition = false;
                    }
                } else {
                    SearchBar.this.stopRecognition();
                }
                SearchBar.this.updateUi(z);
            }
        });
        updateUi(hasFocus());
        updateHint();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mSoundPool = new SoundPool(2, 1, 0);
        loadSounds(this.mContext);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        stopRecognition();
        this.mSoundPool.release();
        super.onDetachedFromWindow();
    }

    public void setSearchBarListener(SearchBarListener searchBarListener) {
        this.mSearchBarListener = searchBarListener;
    }

    public void setSearchQuery(String str) {
        stopRecognition();
        this.mSearchTextEditor.setText(str);
        setSearchQueryInternal(str);
    }

    void setSearchQueryInternal(String str) {
        if (TextUtils.equals(this.mSearchQuery, str)) {
            return;
        }
        this.mSearchQuery = str;
        if (this.mSearchBarListener != null) {
            this.mSearchBarListener.onSearchQueryChange(this.mSearchQuery);
        }
    }

    public void setTitle(String str) {
        this.mTitle = str;
        updateHint();
    }

    public void setSearchAffordanceColors(SearchOrbView.Colors colors) {
        if (this.mSpeechOrbView != null) {
            this.mSpeechOrbView.setNotListeningOrbColors(colors);
        }
    }

    public void setSearchAffordanceColorsInListening(SearchOrbView.Colors colors) {
        if (this.mSpeechOrbView != null) {
            this.mSpeechOrbView.setListeningOrbColors(colors);
        }
    }

    public String getTitle() {
        return this.mTitle;
    }

    public CharSequence getHint() {
        return this.mHint;
    }

    public void setBadgeDrawable(Drawable drawable) {
        this.mBadgeDrawable = drawable;
        if (this.mBadgeView != null) {
            this.mBadgeView.setImageDrawable(drawable);
            if (drawable != null) {
                this.mBadgeView.setVisibility(0);
            } else {
                this.mBadgeView.setVisibility(8);
            }
        }
    }

    public Drawable getBadgeDrawable() {
        return this.mBadgeDrawable;
    }

    public void displayCompletions(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new CompletionInfo(arrayList.size(), arrayList.size(), it.next()));
            }
        }
        displayCompletions((CompletionInfo[]) arrayList.toArray(new CompletionInfo[arrayList.size()]));
    }

    public void displayCompletions(CompletionInfo[] completionInfoArr) {
        this.mInputMethodManager.displayCompletions(this.mSearchTextEditor, completionInfoArr);
    }

    public void setSpeechRecognizer(SpeechRecognizer speechRecognizer) {
        stopRecognition();
        if (this.mSpeechRecognizer != null) {
            this.mSpeechRecognizer.setRecognitionListener(null);
            if (this.mListening) {
                this.mSpeechRecognizer.cancel();
                this.mListening = false;
            }
        }
        this.mSpeechRecognizer = speechRecognizer;
        if (this.mSpeechRecognitionCallback != null && this.mSpeechRecognizer != null) {
            throw new IllegalStateException("Can't have speech recognizer and request");
        }
    }

    public void setSpeechRecognitionCallback(SpeechRecognitionCallback speechRecognitionCallback) {
        this.mSpeechRecognitionCallback = speechRecognitionCallback;
        if (this.mSpeechRecognitionCallback != null && this.mSpeechRecognizer != null) {
            throw new IllegalStateException("Can't have speech recognizer and request");
        }
    }

    void hideNativeKeyboard() {
        this.mInputMethodManager.hideSoftInputFromWindow(this.mSearchTextEditor.getWindowToken(), 0);
    }

    void showNativeKeyboard() {
        this.mHandler.post(new Runnable() { // from class: android.support.v17.leanback.widget.SearchBar.9
            @Override // java.lang.Runnable
            public void run() {
                SearchBar.this.mSearchTextEditor.requestFocusFromTouch();
                SearchBar.this.mSearchTextEditor.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, SearchBar.this.mSearchTextEditor.getWidth(), SearchBar.this.mSearchTextEditor.getHeight(), 0));
                SearchBar.this.mSearchTextEditor.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, SearchBar.this.mSearchTextEditor.getWidth(), SearchBar.this.mSearchTextEditor.getHeight(), 0));
            }
        });
    }

    private void updateHint() {
        String string = getResources().getString(R.string.lb_search_bar_hint);
        if (!TextUtils.isEmpty(this.mTitle)) {
            if (isVoiceMode()) {
                string = getResources().getString(R.string.lb_search_bar_hint_with_title_speech, this.mTitle);
            } else {
                string = getResources().getString(R.string.lb_search_bar_hint_with_title, this.mTitle);
            }
        } else if (isVoiceMode()) {
            string = getResources().getString(R.string.lb_search_bar_hint_speech);
        }
        this.mHint = string;
        if (this.mSearchTextEditor != null) {
            this.mSearchTextEditor.setHint(this.mHint);
        }
    }

    void toggleRecognition() {
        if (this.mRecognizing) {
            stopRecognition();
        } else {
            startRecognition();
        }
    }

    public boolean isRecognizing() {
        return this.mRecognizing;
    }

    public void stopRecognition() {
        if (this.mRecognizing) {
            this.mSearchTextEditor.setText(this.mSearchQuery);
            this.mSearchTextEditor.setHint(this.mHint);
            this.mRecognizing = false;
            if (this.mSpeechRecognitionCallback != null || this.mSpeechRecognizer == null) {
                return;
            }
            this.mSpeechOrbView.showNotListening();
            if (this.mListening) {
                this.mSpeechRecognizer.cancel();
                this.mListening = false;
                this.mAudioManager.abandonAudioFocus(this.mAudioFocusChangeListener);
            }
            this.mSpeechRecognizer.setRecognitionListener(null);
        }
    }

    public void setPermissionListener(SearchBarPermissionListener searchBarPermissionListener) {
        this.mPermissionListener = searchBarPermissionListener;
    }

    public void startRecognition() {
        if (this.mRecognizing) {
            return;
        }
        if (!hasFocus()) {
            requestFocus();
        }
        if (this.mSpeechRecognitionCallback != null) {
            this.mSearchTextEditor.setText("");
            this.mSearchTextEditor.setHint("");
            this.mSpeechRecognitionCallback.recognizeSpeech();
            this.mRecognizing = true;
            return;
        }
        if (this.mSpeechRecognizer == null) {
            return;
        }
        if (getContext().checkCallingOrSelfPermission("android.permission.RECORD_AUDIO") != 0) {
            if (Build.VERSION.SDK_INT >= 23 && this.mPermissionListener != null) {
                this.mPermissionListener.requestAudioPermission();
                return;
            }
            throw new IllegalStateException("android.permission.RECORD_AUDIO required for search");
        }
        this.mRecognizing = true;
        if (this.mAudioManager.requestAudioFocus(this.mAudioFocusChangeListener, 3, 3) != 1) {
            Log.w(TAG, "Could not get audio focus");
        }
        this.mSearchTextEditor.setText("");
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.PARTIAL_RESULTS", true);
        this.mSpeechRecognizer.setRecognitionListener(new RecognitionListener() { // from class: android.support.v17.leanback.widget.SearchBar.10
            @Override // android.speech.RecognitionListener
            public void onBeginningOfSpeech() {
            }

            @Override // android.speech.RecognitionListener
            public void onBufferReceived(byte[] bArr) {
            }

            @Override // android.speech.RecognitionListener
            public void onEndOfSpeech() {
            }

            @Override // android.speech.RecognitionListener
            public void onEvent(int i, Bundle bundle) {
            }

            @Override // android.speech.RecognitionListener
            public void onReadyForSpeech(Bundle bundle) {
                SearchBar.this.mSpeechOrbView.showListening();
                SearchBar.this.playSearchOpen();
            }

            @Override // android.speech.RecognitionListener
            public void onRmsChanged(float f) {
                SearchBar.this.mSpeechOrbView.setSoundLevel(f < 0.0f ? 0 : (int) (f * 10.0f));
            }

            @Override // android.speech.RecognitionListener
            public void onError(int i) {
                switch (i) {
                    case 1:
                        Log.w(SearchBar.TAG, "recognizer network timeout");
                        break;
                    case 2:
                        Log.w(SearchBar.TAG, "recognizer network error");
                        break;
                    case 3:
                        Log.w(SearchBar.TAG, "recognizer audio error");
                        break;
                    case 4:
                        Log.w(SearchBar.TAG, "recognizer server error");
                        break;
                    case 5:
                        Log.w(SearchBar.TAG, "recognizer client error");
                        break;
                    case 6:
                        Log.w(SearchBar.TAG, "recognizer speech timeout");
                        break;
                    case 7:
                        Log.w(SearchBar.TAG, "recognizer no match");
                        break;
                    case 8:
                        Log.w(SearchBar.TAG, "recognizer busy");
                        break;
                    case 9:
                        Log.w(SearchBar.TAG, "recognizer insufficient permissions");
                        break;
                    default:
                        Log.d(SearchBar.TAG, "recognizer other error");
                        break;
                }
                SearchBar.this.stopRecognition();
                SearchBar.this.playSearchFailure();
            }

            @Override // android.speech.RecognitionListener
            public void onResults(Bundle bundle) {
                ArrayList<String> stringArrayList = bundle.getStringArrayList("results_recognition");
                if (stringArrayList != null) {
                    SearchBar.this.mSearchQuery = stringArrayList.get(0);
                    SearchBar.this.mSearchTextEditor.setText(SearchBar.this.mSearchQuery);
                    SearchBar.this.submitQuery();
                }
                SearchBar.this.stopRecognition();
                SearchBar.this.playSearchSuccess();
            }

            @Override // android.speech.RecognitionListener
            public void onPartialResults(Bundle bundle) {
                ArrayList<String> stringArrayList = bundle.getStringArrayList("results_recognition");
                if (stringArrayList == null || stringArrayList.size() == 0) {
                    return;
                }
                SearchBar.this.mSearchTextEditor.updateRecognizedText(stringArrayList.get(0), stringArrayList.size() > 1 ? stringArrayList.get(1) : null);
            }
        });
        this.mListening = true;
        this.mSpeechRecognizer.startListening(intent);
    }

    void updateUi(boolean z) {
        if (z) {
            this.mBarBackground.setAlpha(this.mBackgroundSpeechAlpha);
            if (isVoiceMode()) {
                this.mSearchTextEditor.setTextColor(this.mTextHintColorSpeechMode);
                this.mSearchTextEditor.setHintTextColor(this.mTextHintColorSpeechMode);
            } else {
                this.mSearchTextEditor.setTextColor(this.mTextColorSpeechMode);
                this.mSearchTextEditor.setHintTextColor(this.mTextHintColorSpeechMode);
            }
        } else {
            this.mBarBackground.setAlpha(this.mBackgroundAlpha);
            this.mSearchTextEditor.setTextColor(this.mTextColor);
            this.mSearchTextEditor.setHintTextColor(this.mTextHintColor);
        }
        updateHint();
    }

    private boolean isVoiceMode() {
        return this.mSpeechOrbView.isFocused();
    }

    void submitQuery() {
        if (TextUtils.isEmpty(this.mSearchQuery) || this.mSearchBarListener == null) {
            return;
        }
        this.mSearchBarListener.onSearchQuerySubmit(this.mSearchQuery);
    }

    private void loadSounds(Context context) {
        for (int i : new int[]{R.raw.lb_voice_failure, R.raw.lb_voice_open, R.raw.lb_voice_no_input, R.raw.lb_voice_success}) {
            this.mSoundMap.put(i, this.mSoundPool.load(context, i, 1));
        }
    }

    private void play(final int i) {
        this.mHandler.post(new Runnable() { // from class: android.support.v17.leanback.widget.SearchBar.11
            @Override // java.lang.Runnable
            public void run() {
                SearchBar.this.mSoundPool.play(SearchBar.this.mSoundMap.get(i), 1.0f, 1.0f, 1, 0, 1.0f);
            }
        });
    }

    void playSearchOpen() {
        play(R.raw.lb_voice_open);
    }

    void playSearchFailure() {
        play(R.raw.lb_voice_failure);
    }

    private void playSearchNoInput() {
        play(R.raw.lb_voice_no_input);
    }

    void playSearchSuccess() {
        play(R.raw.lb_voice_success);
    }

    @Override // android.view.View
    public void setNextFocusDownId(int i) {
        this.mSpeechOrbView.setNextFocusDownId(i);
        this.mSearchTextEditor.setNextFocusDownId(i);
    }
}
