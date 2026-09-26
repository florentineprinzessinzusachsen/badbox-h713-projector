package m1;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.os.Bundle;
import j2.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends AbstractAccountAuthenticator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f1442a = 0;

    @Override // android.accounts.AbstractAccountAuthenticator
    public final Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String str, String str2, String[] strArr, Bundle bundle) {
        i.e(accountAuthenticatorResponse, "response");
        i.e(str, "accountType");
        return new Bundle();
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public final Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) {
        i.e(accountAuthenticatorResponse, "response");
        i.e(account, "account");
        return new Bundle();
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public final Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String str) {
        i.e(accountAuthenticatorResponse, "response");
        i.e(str, "accountType");
        return new Bundle();
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public final Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) {
        i.e(accountAuthenticatorResponse, "response");
        i.e(account, "account");
        i.e(str, "authTokenType");
        return new Bundle();
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public final String getAuthTokenLabel(String str) {
        i.e(str, "authTokenType");
        return "";
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public final Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strArr) {
        i.e(accountAuthenticatorResponse, "response");
        i.e(account, "account");
        i.e(strArr, "features");
        return new Bundle();
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public final Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) {
        i.e(accountAuthenticatorResponse, "response");
        i.e(account, "account");
        i.e(str, "authTokenType");
        return new Bundle();
    }
}
