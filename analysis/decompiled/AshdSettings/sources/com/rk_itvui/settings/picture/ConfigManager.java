package com.rk_itvui.settings.picture;

import android.content.Context;
import android.util.Log;
import com.rk_itvui.settings.picture.model.SettingItem;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes.dex */
public class ConfigManager {
    public static final String DEFAULT_VALUE = "--";
    private static final String TAG = "ConfigManager";
    private static final String XML_KEY = "key";
    private static final String XML_KEY_CLASS = "class";
    private static final String XML_KEY_LEVEL = "level";
    private static final String XML_KEY_MAX = "max";
    private static final String XML_KEY_MIN = "min";
    private static final String XML_KEY_STEP = "step";
    private static final String XML_KEY_TITLE = "title";
    private static final String XML_KEY_TYPE = "type";
    private static volatile ConfigManager instance;
    private Context mContext;
    private ArrayList<SettingItem> mSettingTree = new ArrayList<>();
    private Map<String, SettingItem> mSettingMap = new HashMap<String, SettingItem>() { // from class: com.rk_itvui.settings.picture.ConfigManager.1
    };
    private boolean mInitialized = false;

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    private ConfigManager() {
    }

    public void init(Context context) {
        this.mContext = context;
    }

    private SettingItem parseXmlElement(Element element, Map<String, SettingItem> map) {
        int iStringToType;
        SettingItem settingItem;
        SettingItem settingItem2;
        if (element == null || (iStringToType = SettingItem.stringToType(element.getAttribute(XML_KEY_TYPE))) != 2) {
            return null;
        }
        SettingItem settingItem3 = new SettingItem(element.getAttribute("key"), iStringToType, element.getAttribute(XML_KEY_TITLE), (String) null, element.getAttribute(XML_KEY_CLASS));
        map.put(settingItem3.getGroup(), settingItem3);
        ArrayList<SettingItem> childList = settingItem3.getChildList();
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem.getNodeType() == 1 && nodeItem.getNodeName().equals("Setting")) {
                Element element2 = (Element) nodeItem;
                Integer numValueOf = Integer.valueOf(SettingItem.stringToType(element2.getAttribute(XML_KEY_TYPE)));
                String attribute = element2.getAttribute("key");
                String attribute2 = element2.getAttribute(XML_KEY_TITLE);
                switch (numValueOf.intValue()) {
                    case 1:
                    case 5:
                        settingItem = new SettingItem(attribute, numValueOf.intValue(), attribute2);
                        break;
                    case 2:
                        settingItem = parseXmlElement(element2, this.mSettingMap);
                        break;
                    case 3:
                        settingItem2 = new SettingItem(attribute, numValueOf.intValue(), attribute2, Integer.valueOf(element2.getAttribute(XML_KEY_MIN).equals("") ? 0 : Integer.valueOf(element2.getAttribute(XML_KEY_MIN)).intValue()).intValue(), Integer.valueOf(element2.getAttribute(XML_KEY_MAX).equals("") ? 1 : Integer.valueOf(element2.getAttribute(XML_KEY_MAX)).intValue()).intValue(), Integer.valueOf(element2.getAttribute(XML_KEY_STEP).equals("") ? 1 : Integer.valueOf(element2.getAttribute(XML_KEY_STEP)).intValue()).intValue());
                        settingItem = settingItem2;
                        break;
                    case 4:
                        settingItem2 = new SettingItem(attribute, numValueOf.intValue(), attribute2, element2.getAttribute(XML_KEY_LEVEL));
                        settingItem = settingItem2;
                        break;
                    default:
                        settingItem = null;
                        break;
                }
                if (settingItem != null) {
                    childList.add(settingItem);
                }
            }
        }
        return settingItem3;
    }

    public void parseXml() {
        Element documentElement;
        SettingItem xmlElement;
        try {
            InputStream inputStreamOpen = this.mContext.getResources().getAssets().open("setting_tree.xml");
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStreamOpen);
            inputStreamOpen.close();
            document.getDocumentElement().normalize();
            documentElement = document.getDocumentElement();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            documentElement = null;
        }
        if (documentElement != null) {
            NodeList childNodes = documentElement.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node nodeItem = childNodes.item(i);
                if (nodeItem.getNodeType() == 1 && nodeItem.getNodeName().equals("Setting") && (xmlElement = parseXmlElement((Element) nodeItem, this.mSettingMap)) != null) {
                    this.mSettingTree.add(xmlElement);
                }
            }
            this.mInitialized = true;
        }
    }

    private void prinfItem(SettingItem settingItem, int i) {
        Log.d("prinf", "d=" + i + " key=" + settingItem.getKey() + " type=" + settingItem.getType());
        Iterator<SettingItem> it = settingItem.getChildList().iterator();
        while (it.hasNext()) {
            prinfItem(it.next(), i + 1);
        }
    }

    public Map<String, SettingItem> getSettingMap() {
        return this.mSettingMap;
    }

    public boolean getInitState() {
        return this.mInitialized;
    }

    public static String getResString(Context context, String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        try {
            return context.getResources().getString(Class.forName(context.getPackageName() + ".R$string").getField(str).getInt(null));
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] getResArrayString(Context context, String str) {
        String[] strArr = new String[0];
        if (str == null || str.equals("")) {
            return strArr;
        }
        try {
            return context.getResources().getStringArray(Class.forName(context.getPackageName() + ".R$array").getField(str).getInt(null));
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return strArr;
        }
    }
}
