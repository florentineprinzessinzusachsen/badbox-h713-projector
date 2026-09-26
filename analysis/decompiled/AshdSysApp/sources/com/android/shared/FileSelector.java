package com.android.shared;

import a.a.b.n;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.sysapp.R;
import java.io.File;
import java.io.FileFilter;

/* JADX INFO: loaded from: classes.dex */
public class FileSelector extends Activity implements AdapterView.OnItemClickListener {
    public File b;
    public LayoutInflater c;
    public ListView e;
    public String f;
    public StorageManager h;
    public StorageVolume[] i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f49a = "CMUpdate2FileSelector";
    public final b d = new b(null);
    public String g = "backnull";
    public boolean j = true;

    public class b extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public File[] f50a;
        public boolean b = false;
        public File c;

        public class a implements View.OnFocusChangeListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ C0006b f51a;

            public a(b bVar, C0006b c0006b) {
                this.f51a = c0006b;
            }

            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                this.f51a.b.setAutoMarquee(z);
            }
        }

        /* JADX INFO: renamed from: com.android.shared.FileSelector$b$b, reason: collision with other inner class name */
        public class C0006b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public ImageView f52a;
            public AutoMarqueeTextView b;
            public TextView c;

            public /* synthetic */ C0006b(b bVar, a aVar) {
            }
        }

        public /* synthetic */ b(a aVar) {
        }

        public void a(int i) {
        }

        public void a(File[] fileArr) {
            this.b = false;
            this.f50a = fileArr;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (this.b) {
                return 1;
            }
            File[] fileArr = this.f50a;
            if (fileArr == null) {
                return 0;
            }
            return fileArr.length;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            if (this.b) {
                return this.c;
            }
            File[] fileArr = this.f50a;
            if (fileArr == null) {
                return null;
            }
            return fileArr[i];
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            if (this.b) {
                return 0L;
            }
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewInflate;
            int i2;
            ImageView imageView;
            AutoMarqueeTextView autoMarqueeTextView;
            int i3;
            if (view == null) {
                viewInflate = FileSelector.this.c.inflate(R.layout.file_list_item, (ViewGroup) null);
                C0006b c0006b = new C0006b(this, null);
                c0006b.f52a = (ImageView) viewInflate.findViewById(R.id.image);
                c0006b.b = (AutoMarqueeTextView) viewInflate.findViewById(R.id.file_name);
                c0006b.c = (TextView) viewInflate.findViewById(R.id.file_size);
                viewInflate.setTag(c0006b);
            } else {
                viewInflate = view;
            }
            C0006b c0006b2 = (C0006b) viewInflate.getTag();
            c0006b2.c.setText("");
            File file = this.b ? this.c : this.f50a[i];
            if (file.getName().endsWith("backnull")) {
                c0006b2.f52a.setImageResource(R.drawable.file_back_icon);
                c0006b2.b.setText(R.string.back);
                return viewInflate;
            }
            if (FileSelector.this.b()) {
                imageView = c0006b2.f52a;
                i2 = R.drawable.litter_disk;
            } else {
                boolean zIsDirectory = file.isDirectory();
                i2 = R.drawable.file_file_icon;
                if (!zIsDirectory && file.isFile()) {
                    TextView textView = c0006b2.c;
                    double length = file.length();
                    Double.isNaN(length);
                    Double.isNaN(length);
                    Double.isNaN(length);
                    String str = String.format("%.2f %s ", Double.valueOf(length / 1.073741824E9d), "GB");
                    if (str.startsWith("0.") || str.startsWith("0,")) {
                        Double.isNaN(length);
                        Double.isNaN(length);
                        Double.isNaN(length);
                        str = String.format("%.2f %s ", Double.valueOf(length / 1048576.0d), "MB");
                        if (str.startsWith("0.") || str.startsWith("0,")) {
                            Double.isNaN(length);
                            Double.isNaN(length);
                            Double.isNaN(length);
                            str = String.format("%.2f %s ", Double.valueOf(length / 1024.0d), "KB");
                        }
                    }
                    textView.setText(str);
                    imageView = c0006b2.f52a;
                    i2 = R.drawable.file_zip_icon;
                } else {
                    imageView = c0006b2.f52a;
                }
            }
            imageView.setImageResource(i2);
            Log.i(FileSelector.this.f49a, "filename=" + file.getName() + ",file.getAbsolutePath=" + file.getAbsolutePath());
            if (!file.getAbsolutePath().equals("/mnt/internal_sd") && !file.getAbsolutePath().equals("/mnt/sdcard") && !file.getAbsolutePath().equals("/storage/emulated/0")) {
                if (file.getAbsolutePath().equals("/mnt/usb_storage") || file.getAbsolutePath().equals("/mnt/media_rw")) {
                    autoMarqueeTextView = c0006b2.b;
                    i3 = R.string.USB_disk;
                } else {
                    c0006b2.b.setText(file.getName());
                }
                viewInflate.setOnFocusChangeListener(new a(this, c0006b2));
                return viewInflate;
            }
            autoMarqueeTextView = c0006b2.b;
            i3 = R.string.local_disk;
            autoMarqueeTextView.setText(i3);
            viewInflate.setOnFocusChangeListener(new a(this, c0006b2));
            return viewInflate;
        }
    }

    public class c implements FileFilter {
        public /* synthetic */ c(a aVar) {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            if (file == null || FileSelector.this.i == null) {
                return false;
            }
            if (file.getAbsolutePath().equals("/mnt/sdcard") || file.getAbsolutePath().equals("/mnt/media_rw")) {
                return true;
            }
            if (!file.getAbsolutePath().equals("/mnt/internal_sd") && !file.getAbsolutePath().equals("/mnt/external_sd")) {
                for (StorageVolume storageVolume : FileSelector.this.i) {
                    String path = storageVolume.getPath();
                    if (file.getPath().equals(path) || path.contains(file.getPath())) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public class d implements FileFilter {
        public /* synthetic */ d(FileSelector fileSelector, a aVar) {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            if (file == null) {
                return false;
            }
            if (file.isDirectory()) {
                return true;
            }
            return file.getPath().toLowerCase().endsWith(".zip");
        }
    }

    public boolean a(String str) {
        for (StorageVolume storageVolume : this.i) {
            String path = storageVolume.getPath();
            Log.i(this.f49a, "path=" + path);
            if (str.equals(path)) {
                return true;
            }
        }
        return false;
    }

    public File[] a() {
        this.i = this.h.getVolumeList();
        StorageVolume[] storageVolumeArr = this.i;
        File[] fileArr = new File[storageVolumeArr.length];
        int i = 0;
        for (StorageVolume storageVolume : storageVolumeArr) {
            String path = storageVolume.getPath();
            Log.i(this.f49a, "path=" + path);
            File file = new File(path);
            if (file.canRead()) {
                fileArr[i] = file;
                i++;
            }
        }
        if (i == this.i.length) {
            return fileArr;
        }
        File[] fileArr2 = new File[i];
        if (i >= 0) {
            System.arraycopy(fileArr, 0, fileArr2, 0, i);
        }
        return fileArr2;
    }

    public boolean b() {
        return false;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        File file = this.b;
        if (file == null || this.j || file.getPath().equals(this.f)) {
            this.j = true;
            super.onBackPressed();
            return;
        }
        if (a(this.b.getPath())) {
            this.d.a(0);
            this.d.a(a());
            this.j = true;
            return;
        }
        this.b = this.b.getParentFile();
        this.d.a(0);
        a aVar = null;
        if (this.b.getPath().equals(this.f)) {
            this.d.a(this.b.listFiles(new c(aVar)));
        } else {
            this.d.a(this.b.listFiles(new d(this, aVar)));
        }
        this.j = false;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (n.f13a) {
            Log.i(this.f49a, "FileSelector onCreate()");
        }
        this.c = LayoutInflater.from(this);
        requestWindowFeature(1);
        setContentView(R.layout.file_list);
        this.e = (ListView) findViewById(R.id.file_list);
        this.e.setAdapter((ListAdapter) this.d);
        this.e.setOnItemClickListener(this);
        this.h = (StorageManager) getSystemService("storage");
        this.i = this.h.getVolumeList();
        File[] fileArrA = a();
        if (fileArrA == null || fileArrA.length <= 0) {
            setResult(0);
            return;
        }
        b bVar = this.d;
        bVar.b = false;
        bVar.f50a = fileArrA;
        bVar.notifyDataSetChanged();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        File file = (File) adapterView.getItemAtPosition(i);
        a aVar = null;
        if (file.getName().equals(this.g)) {
            File file2 = this.b;
            if (file2 == null || file2.getPath().equals(this.f)) {
                this.j = true;
                super.onBackPressed();
                return;
            } else if (a(this.b.getPath())) {
                this.j = true;
                this.d.a(a());
                return;
            } else {
                this.b = this.b.getParentFile();
                if (this.b.getPath().equals(this.f)) {
                    this.d.a(this.b.listFiles(new c(aVar)));
                } else {
                    this.d.a(this.b.listFiles(new d(this, aVar)));
                }
            }
        } else {
            if (!file.isDirectory()) {
                if (file.isFile()) {
                    Intent intent = new Intent();
                    intent.putExtra("file", file.getPath());
                    Log.i(this.f49a, "selectFile.getPath()=" + file.getPath());
                    setResult(-1, intent);
                    this.j = false;
                    finish();
                    return;
                }
                return;
            }
            this.b = file;
            b bVar = (b) adapterView.getAdapter();
            bVar.a(i);
            File[] fileArrListFiles = file.listFiles(new d(this, aVar));
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                File file3 = new File(file.getAbsolutePath() + "/" + this.g);
                if (file3.getName().endsWith(FileSelector.this.g)) {
                    bVar.b = true;
                    bVar.c = file3;
                } else {
                    bVar.b = false;
                }
                bVar.f50a = file3.listFiles();
                bVar.notifyDataSetChanged();
            } else {
                bVar.b = false;
                bVar.f50a = fileArrListFiles;
                bVar.notifyDataSetChanged();
            }
        }
        this.j = false;
    }
}
