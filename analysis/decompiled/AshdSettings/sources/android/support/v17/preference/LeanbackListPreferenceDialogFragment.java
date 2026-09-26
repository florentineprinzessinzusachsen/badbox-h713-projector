package android.support.v17.preference;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v14.preference.MultiSelectListPreference;
import android.support.v17.leanback.widget.VerticalGridView;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.TextView;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class LeanbackListPreferenceDialogFragment extends LeanbackPreferenceDialogFragment {
    public static LeanbackListPreferenceDialogFragment newInstanceSingle(String str) {
        Bundle bundle = new Bundle(5);
        bundle.putString(LeanbackPreferenceDialogFragment.ARG_KEY, str);
        LeanbackListPreferenceDialogFragment leanbackListPreferenceDialogFragment = new LeanbackListPreferenceDialogFragment();
        leanbackListPreferenceDialogFragment.setArguments(bundle);
        return leanbackListPreferenceDialogFragment;
    }

    public static LeanbackListPreferenceDialogFragment newInstanceMulti(String str) {
        Bundle bundle = new Bundle(5);
        bundle.putString(LeanbackPreferenceDialogFragment.ARG_KEY, str);
        LeanbackListPreferenceDialogFragment leanbackListPreferenceDialogFragment = new LeanbackListPreferenceDialogFragment();
        leanbackListPreferenceDialogFragment.setArguments(bundle);
        return leanbackListPreferenceDialogFragment;
    }

    @Override // android.support.v17.preference.LeanbackPreferenceDialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DialogPreference preference = getPreference();
        if (!(preference instanceof ListPreference) && !(preference instanceof MultiSelectListPreference)) {
            throw new IllegalArgumentException("Preference must be a ListPreference or MultiSelectListPreference");
        }
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.leanback_list_preference_fragment, viewGroup, false);
        VerticalGridView verticalGridView = (VerticalGridView) viewInflate.findViewById(android.R.id.list);
        verticalGridView.setWindowAlignment(3);
        verticalGridView.setFocusScrollStrategy(0);
        verticalGridView.setAdapter(onCreateAdapter());
        verticalGridView.requestFocus();
        DialogPreference preference = getPreference();
        CharSequence dialogTitle = preference.getDialogTitle();
        if (!TextUtils.isEmpty(dialogTitle)) {
            ((TextView) viewInflate.findViewById(R.id.decor_title)).setText(dialogTitle);
        }
        CharSequence dialogMessage = preference.getDialogMessage();
        if (!TextUtils.isEmpty(dialogMessage)) {
            TextView textView = (TextView) viewInflate.findViewById(android.R.id.message);
            textView.setVisibility(0);
            textView.setText(dialogMessage);
        }
        return viewInflate;
    }

    public RecyclerView.Adapter onCreateAdapter() {
        DialogPreference preference = getPreference();
        if (preference instanceof MultiSelectListPreference) {
            MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) preference;
            return new AdapterMulti(multiSelectListPreference.getEntries(), multiSelectListPreference.getEntryValues(), multiSelectListPreference.getValues());
        }
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            return new AdapterSingle(listPreference.getEntries(), listPreference.getEntryValues(), listPreference.getValue());
        }
        throw new IllegalStateException("Unknown preference type");
    }

    public class AdapterSingle extends RecyclerView.Adapter<ViewHolder> implements ViewHolder.OnItemClickListener {
        private final CharSequence[] mEntries;
        private final CharSequence[] mEntryValues;
        private CharSequence mSelectedValue;

        public AdapterSingle(CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2, CharSequence charSequence) {
            this.mEntries = charSequenceArr;
            this.mEntryValues = charSequenceArr2;
            this.mSelectedValue = charSequence;
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leanback_list_preference_item_single, viewGroup, false), this);
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.getWidgetView().setChecked(this.mEntryValues[i].equals(this.mSelectedValue));
            viewHolder.getTitleView().setText(this.mEntries[i]);
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mEntries.length;
        }

        @Override // android.support.v17.preference.LeanbackListPreferenceDialogFragment.ViewHolder.OnItemClickListener
        public void onItemClick(ViewHolder viewHolder) {
            int adapterPosition = viewHolder.getAdapterPosition();
            if (adapterPosition == -1) {
                return;
            }
            CharSequence charSequence = this.mEntryValues[adapterPosition];
            ListPreference listPreference = (ListPreference) LeanbackListPreferenceDialogFragment.this.getPreference();
            if (adapterPosition >= 0) {
                String string = this.mEntryValues[adapterPosition].toString();
                if (listPreference.callChangeListener(string)) {
                    listPreference.setValue(string);
                    this.mSelectedValue = charSequence;
                }
            }
            LeanbackListPreferenceDialogFragment.this.getFragmentManager().popBackStack();
            notifyDataSetChanged();
        }
    }

    public class AdapterMulti extends RecyclerView.Adapter<ViewHolder> implements ViewHolder.OnItemClickListener {
        private final CharSequence[] mEntries;
        private final CharSequence[] mEntryValues;
        private final Set<String> mSelections;

        public AdapterMulti(CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2, Set<String> set) {
            this.mEntries = charSequenceArr;
            this.mEntryValues = charSequenceArr2;
            this.mSelections = new HashSet(set);
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leanback_list_preference_item_multi, viewGroup, false), this);
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.getWidgetView().setChecked(this.mSelections.contains(this.mEntryValues[i].toString()));
            viewHolder.getTitleView().setText(this.mEntries[i]);
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mEntries.length;
        }

        @Override // android.support.v17.preference.LeanbackListPreferenceDialogFragment.ViewHolder.OnItemClickListener
        public void onItemClick(ViewHolder viewHolder) {
            int adapterPosition = viewHolder.getAdapterPosition();
            if (adapterPosition == -1) {
                return;
            }
            String string = this.mEntryValues[adapterPosition].toString();
            if (this.mSelections.contains(string)) {
                this.mSelections.remove(string);
            } else {
                this.mSelections.add(string);
            }
            MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) LeanbackListPreferenceDialogFragment.this.getPreference();
            if (multiSelectListPreference.callChangeListener(new HashSet(this.mSelections))) {
                multiSelectListPreference.setValues(new HashSet(this.mSelections));
            } else if (this.mSelections.contains(string)) {
                this.mSelections.remove(string);
            } else {
                this.mSelections.add(string);
            }
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ViewGroup mContainer;
        private final OnItemClickListener mListener;
        private final TextView mTitleView;
        private final Checkable mWidgetView;

        public interface OnItemClickListener {
            void onItemClick(ViewHolder viewHolder);
        }

        public ViewHolder(@NonNull View view, @NonNull OnItemClickListener onItemClickListener) {
            super(view);
            this.mWidgetView = (Checkable) view.findViewById(R.id.button);
            this.mContainer = (ViewGroup) view.findViewById(R.id.container);
            this.mTitleView = (TextView) view.findViewById(android.R.id.title);
            this.mContainer.setOnClickListener(this);
            this.mListener = onItemClickListener;
        }

        public Checkable getWidgetView() {
            return this.mWidgetView;
        }

        public TextView getTitleView() {
            return this.mTitleView;
        }

        public ViewGroup getContainer() {
            return this.mContainer;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.mListener.onItemClick(this);
        }
    }
}
