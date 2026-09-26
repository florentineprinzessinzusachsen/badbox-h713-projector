package a.a.b;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.android.sysapp.UpdateService;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a extends j {
    public Context f;
    public String g;
    public String h;
    public String i = null;
    public String j = null;
    public h k;

    public a(Context context, Handler handler) {
        this.f = context;
        this.k = new h(this.f);
    }

    @Override // a.a.b.j
    public void a() {
        String str = n.c;
        Log.v("CMUpdate2CheckingTask", "send post to server");
        try {
            HttpPost httpPost = new HttpPost(str);
            ArrayList arrayList = new ArrayList();
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setSoTimeout(basicHttpParams, 100000);
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
            httpPost.setEntity(new UrlEncodedFormEntity(arrayList, "UTF-8"));
            HttpResponse httpResponseExecute = defaultHttpClient.execute(httpPost);
            Log.i("CMUpdate2CheckingTask", "response status:  " + httpResponseExecute.getStatusLine().getStatusCode());
            if (httpResponseExecute.getStatusLine().getStatusCode() == 200) {
                a(k.a(httpResponseExecute.getEntity().getContent()));
            } else {
                this.c = 1;
                Log.i("CMUpdate2CheckingTask", "mErrorCode = ERROR_UNDISCOVERY_NEW_VERSION");
            }
        } catch (Exception e) {
            this.c = 3;
            e.printStackTrace();
        }
        Log.i("CMUpdate2CheckingTask", "1th__mErrorCode=" + this.c);
    }

    @Override // a.a.b.j
    public void c() {
        Intent intent;
        int i;
        String strA;
        Log.v("CMUpdate2CheckingTask", "ErrorCode=" + this.c);
        if (this.c == 0) {
            k.a();
            String str = this.j;
            if (str == null || str.equals("") || !str.trim().endsWith("MB")) {
                strA = n.f + "/update.zip";
            } else {
                try {
                    strA = k.a(((long) Integer.parseInt(str.replaceAll("MB", "").trim())) << 10);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    strA = n.f + "/update.zip";
                }
            }
            n.h = strA;
            h hVar = this.k;
            String str2 = n.h;
            SharedPreferences.Editor editorEdit = hVar.f8a.edit();
            editorEdit.putString("download_target", str2);
            editorEdit.commit();
            String[] strArr = (String[]) this.e;
            h hVar2 = this.k;
            String str3 = strArr[1];
            SharedPreferences.Editor editorEdit2 = hVar2.f8a.edit();
            editorEdit2.putString("download_URL", str3);
            editorEdit2.commit();
            h hVar3 = this.k;
            String str4 = strArr[0];
            SharedPreferences.Editor editorEdit3 = hVar3.f8a.edit();
            editorEdit3.putString("package_md5", str4);
            editorEdit3.commit();
            h hVar4 = this.k;
            String str5 = strArr[2];
            SharedPreferences.Editor editorEdit4 = hVar4.f8a.edit();
            editorEdit4.putString("package_descriptor", str5);
            editorEdit4.commit();
            this.k.b(UpdateService.p);
            h hVar5 = this.k;
            String str6 = this.i;
            SharedPreferences.Editor editorEdit5 = hVar5.f8a.edit();
            editorEdit5.putString("update_log", str6);
            editorEdit5.commit();
            Log.v("CMUpdate2CheckingTask", "Discover new version");
            UpdateService.v = false;
            intent = new Intent(this.f, (Class<?>) UpdateService.class);
            if (UpdateService.p) {
                Log.i("CMUpdate2CheckingTask", "Force update Mode!");
                i = 102;
            } else {
                Log.i("CMUpdate2CheckingTask", "Normal update Mode!");
                i = 103;
            }
        } else {
            Log.i("CMUpdate2CheckingTask", "no new version");
            UpdateService.v = true;
            SharedPreferences.Editor editorEdit6 = this.k.f8a.edit();
            editorEdit6.putString("update_log", "null");
            editorEdit6.commit();
            intent = new Intent(this.f, (Class<?>) UpdateService.class);
            i = 110;
        }
        intent.putExtra("start_command", i);
        this.f.startService(intent);
    }

    public final void a(String str) {
        boolean z;
        Log.i("CMUpdate2CheckingTask", "parserJson  info=" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("code");
            Log.i("CMUpdate2CheckingTask", "parserJson  code=" + iOptInt);
            if (iOptInt == 200 && str.contains("update_model")) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                String strOptString = jSONObjectOptJSONObject.optString("version");
                Log.i("CMUpdate2CheckingTask", "parserJson  otaversion=" + strOptString);
                String strOptString2 = jSONObjectOptJSONObject.optString("package_url");
                String strOptString3 = jSONObjectOptJSONObject.optString("package_md5");
                String strOptString4 = jSONObjectOptJSONObject.optString("update_model");
                String strOptString5 = jSONObjectOptJSONObject.optString("device_mode");
                String strOptString6 = jSONObjectOptJSONObject.optString("update_desc");
                String strOptString7 = jSONObjectOptJSONObject.optString("targent_version");
                String strOptString8 = null;
                try {
                    strOptString8 = jSONObjectOptJSONObject.optString("package_size");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i("CMUpdate2CheckingTask", "parserJson  device_model=" + strOptString5);
                if (strOptString5.equals(m.f12a)) {
                    String str2 = m.d;
                    try {
                        if (strOptString7.contains(str2)) {
                            if (strOptString7.contains(",")) {
                                String[] strArrSplit = strOptString7.split(",");
                                int length = strArrSplit.length;
                                for (int i = 0; i < length && !strArrSplit[i].equals(str2); i++) {
                                }
                            }
                            z = true;
                        } else {
                            z = false;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (z) {
                        String str3 = m.d;
                        if (strOptString.length() == str3.length() && strOptString.compareTo(str3) > 0) {
                            UpdateService.p = strOptString4.equals("1");
                            String[] strArr = new String[4];
                            this.h = strOptString3;
                            this.g = strOptString2;
                            this.i = strOptString6;
                            if (TextUtils.isEmpty(strOptString8)) {
                                this.j = "512M";
                            } else {
                                this.j = strOptString8;
                            }
                            strArr[0] = this.h;
                            strArr[1] = this.g;
                            strArr[2] = this.i;
                            strArr[3] = this.j;
                            this.e = strArr;
                            this.c = 0;
                            return;
                        }
                        Log.i("CMUpdate2CheckingTask", "服务端版本小于本地版本，服务端版本：" + strOptString + ",本地版本：" + m.d);
                    }
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        this.c = 1;
    }
}
