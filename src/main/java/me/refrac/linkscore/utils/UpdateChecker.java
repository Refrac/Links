package me.refrac.linkscore.utils;

import java.net.*;

import me.refrac.linkscore.Core;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class UpdateChecker
{

    public Core plugin;
    private String version;

    public UpdateChecker(Core plugin) {
        this.plugin = plugin;
        this.version = this.getLatestVersion();
    }

    @SuppressWarnings("unused")
    public String getLatestVersion() {
        try {
            final int resource = 70888;
            final HttpURLConnection con = (HttpURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=70888").openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.getOutputStream().write("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=70888".getBytes( StandardCharsets.UTF_8 ));
            final String version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            if (version.length() <= 7) {
                return version;
            }
        }
        catch (Exception ex) {
            System.out.println("---------------------------------");
            this.plugin.getLogger().info("Failed to check for a update!");
            System.out.println("---------------------------------");
        }
        return null;
    }

    public boolean isConnected() {
        return this.version != null;
    }

    public boolean hasUpdate() {
        return !this.version.equals(this.plugin.getDescription().getVersion());
    }
}