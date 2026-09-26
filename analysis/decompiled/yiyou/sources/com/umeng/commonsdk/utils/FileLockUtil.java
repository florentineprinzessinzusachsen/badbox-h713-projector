package com.umeng.commonsdk.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* JADX INFO: loaded from: classes.dex */
public class FileLockUtil {
    private final Object lockObject = new Object();

    /* JADX WARN: Code duplicated, block: B:22:0x0022 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private static FileLock getFileLock(String str) {
        FileChannel channel;
        try {
            channel = new RandomAccessFile(str, "rw").getChannel();
            try {
                return channel.lock();
            } catch (FileNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return null;
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                if (channel != null) {
                    channel.close();
                }
                return null;
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            channel = null;
        } catch (IOException e6) {
            e = e6;
            channel = null;
        }
    }

    public void doFileOperateion(File file, FileLockCallback fileLockCallback, Object obj) {
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(file.getAbsolutePath());
                if (fileLock != null) {
                    try {
                        try {
                            fileLockCallback.onFileLock(file.getName(), obj);
                            try {
                                fileLock.release();
                                fileLock.channel().close();
                            } catch (IOException e2) {
                                e = e2;
                                e.printStackTrace();
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            try {
                                fileLock.release();
                                fileLock.channel().close();
                            } catch (IOException e4) {
                                e = e4;
                                e.printStackTrace();
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        throw th;
                    }
                }
            }
        }
    }

    public void doFileOperateion(File file, FileLockCallback fileLockCallback, int i) {
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(file.getAbsolutePath());
                if (fileLock != null) {
                    try {
                        try {
                            fileLockCallback.onFileLock(file, i);
                            try {
                                fileLock.release();
                                fileLock.channel().close();
                            } catch (Throwable th) {
                                th = th;
                                th.printStackTrace();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            try {
                                fileLock.release();
                                fileLock.channel().close();
                            } catch (Throwable th2) {
                                th = th2;
                                th.printStackTrace();
                            }
                        }
                    } catch (Throwable th3) {
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                        throw th3;
                    }
                }
            }
        }
    }

    public void doFileOperateion(File file, FileLockCallback fileLockCallback) {
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(file.getAbsolutePath());
                if (fileLock != null) {
                    try {
                        try {
                            fileLockCallback.onFileLock(file.getName());
                            try {
                                fileLock.release();
                                fileLock.channel().close();
                            } catch (IOException e2) {
                                e = e2;
                                e.printStackTrace();
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            try {
                                fileLock.release();
                                fileLock.channel().close();
                            } catch (IOException e4) {
                                e = e4;
                                e.printStackTrace();
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        throw th;
                    }
                }
            }
        }
    }

    public void doFileOperateion(String str, FileLockCallback fileLockCallback) {
        File file = new File(str);
        if (file.exists()) {
            synchronized (this.lockObject) {
                FileLock fileLock = getFileLock(str);
                if (fileLock != null) {
                    try {
                        try {
                            fileLockCallback.onFileLock(file.getName());
                            try {
                                fileLock.release();
                                fileLock.channel().close();
                            } catch (IOException e2) {
                                e = e2;
                                e.printStackTrace();
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            try {
                                fileLock.release();
                                fileLock.channel().close();
                            } catch (IOException e4) {
                                e = e4;
                                e.printStackTrace();
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            fileLock.release();
                            fileLock.channel().close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        throw th;
                    }
                }
            }
        }
    }
}
