package com.android.settingslib.drawer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.settingslib.R;

/* JADX INFO: loaded from: classes.dex */
public class ProfileSelectDialog extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String ARG_SELECTED_TILE = "selectedTile";
    private Tile mSelectedTile;

    public static void show(FragmentManager fragmentManager, Tile tile) {
        ProfileSelectDialog profileSelectDialog = new ProfileSelectDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_SELECTED_TILE, tile);
        profileSelectDialog.setArguments(bundle);
        profileSelectDialog.show(fragmentManager, "select_profile");
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSelectedTile = (Tile) getArguments().getParcelable(ARG_SELECTED_TILE);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.choose_profile).setAdapter(UserAdapter.createUserAdapter(UserManager.get(activity), activity, this.mSelectedTile.userHandle), this);
        return builder.create();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        UserHandle userHandle = this.mSelectedTile.userHandle.get(i);
        this.mSelectedTile.intent.putExtra(SettingsDrawerActivity.EXTRA_SHOW_MENU, true);
        this.mSelectedTile.intent.addFlags(32768);
        getActivity().startActivityAsUser(this.mSelectedTile.intent, userHandle);
        ((SettingsDrawerActivity) getActivity()).onProfileTileOpen();
    }
}
