package org.kodejava.example.util;

import java.util.prefs.Preferences;

public class RegistryDemo {
    public static final String PREF_KEY = "org.kodejava";

    public static void main(String[] args) {
        //
        // Write Preferences information to HKCU (HKEY_CURRENT_USER),
        // HKCU\Software\JavaSoft\Prefs\org.kodejava
        //
        Preferences userPref = Preferences.userRoot();
        userPref.put(PREF_KEY, "www.kodejava.org");

        //
        // Below we read back the value we've written in the code above.
        //
        System.out.println("Preferences = "
                + userPref.get(PREF_KEY, PREF_KEY + " was not found."));

        //
        // Write Preferences information to HKLM (HKEY_LOCAL_MACHINE),
        // HKLM\Software\JavaSoft\Prefs\org.kodejava
        //
        Preferences systemPref = Preferences.systemRoot();
        systemPref.put(PREF_KEY, "www.kodejava.org");

        //
        // Read back the value we've written in the code above.
        //
        System.out.println("Preferences = "
                + systemPref.get(PREF_KEY, PREF_KEY + " was not found."));
    }
}
